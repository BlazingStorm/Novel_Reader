package scrape.addon.FreeWebNovel.Service;

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
        Elements elements = document.select("ul#idData li a");

        List<Chapter> chapters = new ArrayList<>();
        for (Element e : elements) {
            String chap = e.text();
            String chapterPath = e.attr("href");
            String link = "http://localhost:8080/scrape/chapter?source=freewebnovel&path=" + chapterPath;
            chapters.add(new Chapter(chap, link));
        }
        return chapters;
    }

    public ChapterContent getChapterContent(String path) throws IOException {
        String url = path.startsWith("http") ? path : baseUrl + path;
        Document document = fetchDocument(url);

        String title = document.select("#article > h4").text();
        Elements paragraphs = document.select("#article > p");

        List<String> contentBuilder = new ArrayList<>();
        for (Element p : paragraphs) {
            contentBuilder.add(p.text());
        }

        return new ChapterContent(title, contentBuilder);
    }
}
