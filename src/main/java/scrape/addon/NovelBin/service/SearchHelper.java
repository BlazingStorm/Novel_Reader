package scrape.addon.NovelBin.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scrape.addon.common.model.Book;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class SearchHelper extends BaseNovelService {

    public List<Book> search(String keyword) throws IOException {
        String url = baseUrl + "/search?keyword=" + URLEncoder.encode(keyword, StandardCharsets.UTF_8);
        Document document = fetchDocument(url);

        Elements rows = document.select("div.row");
        List<Book> books = new ArrayList<>();

        for (Element row : rows) {
            String title = row.select("h3.novel-title a").text();
            String rawHref = row.select("h3.novel-title a").attr("href");
            String img = row.select(".col-xs-3 img").attr("src");
            img = resolveImageUrl(img);

            if (title.isBlank() || rawHref.isBlank()) continue;

            String href = "http://localhost:8080/scrape/chapters?source=NovelBin&path=" + normalizeUrl(rawHref);
            books.add(new Book(title, href, img));
        }
        return books;
    }
}
