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

        List<Chapter> chapters = new ArrayList<>();


        Elements chapterEls = document.select(".list-chapter a");
        for (Element ch : chapterEls) {
            String chapTitle = ch.text().trim();
            String rawHref = ch.attr("href");
            if (chapTitle.isBlank() || rawHref.isBlank()) continue;

            String href = normalizeUrl(rawHref);
            chapters.add(new Chapter(chapTitle, href));
        }
        return chapters;
    }

    public ChapterContent getChapterContent(String path) throws IOException {
        String url = path.startsWith("http") ? path : baseUrl + path;
        Document document = fetchDocument(url);


       String title=document.select(".chr-title").attr("title");

       Elements elements=document.select("#chr-content > p");
       List<String>content=new ArrayList<>();
       for (Element element : elements) {
           content.add(element.text().trim());
       }
       return new ChapterContent(title, content);
    }
}
