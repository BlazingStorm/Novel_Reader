package scrape.addon.NovelBin.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scrape.addon.common.model.Chapter;
import scrape.addon.common.model.ChapterContent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChapterHelper extends BaseNovelService {

    public List<Chapter> getChapters(String path) throws IOException {
        String url = path.startsWith("http") ? path : baseUrl + path;
        Document document = fetchDocument(url);

        Elements elements = document.select("ul.list-chapter li a");
        List<Chapter> chapters = new ArrayList<>();

        for (Element element : elements) {
            String chapTitle = element.text();
            String rawHref = element.attr("href");

            if (chapTitle.isBlank() || rawHref.isBlank()) continue;

            String href = "http://localhost:8080/scrape/chapter?source=NovelBin&path=" + normalizeUrl(rawHref);
            chapters.add(new Chapter(chapTitle, href));
        }
        return chapters;
    }

    public ChapterContent getChapterContent(String path) throws IOException {
        String url = path.startsWith("http") ? path : baseUrl + path;
        Document document = fetchDocument(url);

        Element titleElement = document.selectFirst("h2.chapter-title");
        String title = (titleElement != null) ? titleElement.text() : "Untitled";

        Element contentElement = document.selectFirst("#chr-content");
        StringBuilder contentBuilder = new StringBuilder();

        if (contentElement != null) {
            Elements paragraphs = contentElement.select("p");
            if (!paragraphs.isEmpty()) {
                for (Element p : paragraphs) {
                    contentBuilder.append(p.text()).append("\n\n");
                }
            } else {
                contentBuilder.append(contentElement.text());
            }
        } else {
            contentBuilder.append("No content found.");
        }

        return new ChapterContent(title, contentBuilder.toString());
    }
}
