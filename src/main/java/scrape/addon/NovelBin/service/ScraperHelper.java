package scrape.addon.NovelBin.service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scrape.addon.common.model.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScraperHelper extends BaseNovelService {

    public List<Book> scrapeNovels() throws IOException {
        String url = baseUrl + "/sort/latest";
        Document document = fetchDocument(url);

        Elements rows = document.select("div.row");
        List<Book> books = new ArrayList<>();

        for (Element row : rows) {

            String title = row.select("h3.novel-title a").text();
            String rawHref = row.select("h3.novel-title a").attr("href");

            if (title.isBlank() || rawHref.isBlank()) continue;


            String href = "http://localhost:8080/scrape/chapters?source=NovelBin&path=" + normalizeUrl(rawHref);


            Element imgEl = row.selectFirst(".col-xs-3 img");
            String img = "";
            if (imgEl != null) {

                img = imgEl.hasAttr("src") ? imgEl.attr("src") : imgEl.attr("data-src");


                if (!img.startsWith("http")) {
                    img = resolveImageUrl(img);
                }
            }

            books.add(new Book(title, href, img));
        }

        return books;
    }
}


