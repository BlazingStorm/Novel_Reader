package scrape.addon.FreeWebNovel.Service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scrape.addon.common.model.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapeHelper extends BaseNovelService {

    public List<Book> scrapeNovels() throws IOException {
        String url = baseUrl + "/sort/latest-novel";
        Document document = fetchDocument(url);
        Elements elements = document.select(".con");

        List<Book> books = new ArrayList<>();
        for (Element e : elements) {
            String title = e.select("h3 > a").text();
            String path = e.select("h3 > a").attr("href");
            String link = "http://localhost:8080/scrape/chapters?source=freewebnovel&path=" + path;
            String img = e.select(".pic img").attr("src");
            img = resolveImageUrl(img);


            if (title.isBlank() || path.isBlank() || img.isBlank()) continue;
            books.add(new Book(title, link, img));
        }
        return books;
    }
}
