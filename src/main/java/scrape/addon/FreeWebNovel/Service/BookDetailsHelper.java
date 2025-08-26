package scrape.addon.FreeWebNovel.Service;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import scrape.addon.common.model.BookDetails;
import scrape.addon.common.model.Chapter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BookDetailsHelper extends BaseNovelService {

    private final ChapterHelper chapterHelper = new ChapterHelper();

    public BookDetails getBookDetails(String path) throws IOException {
        String url = path.startsWith("http") ? path : baseUrl + path;
        Document document = fetchDocument(url);

        // Title
        String title = document.select("div.m-desc h1.tit").text();


        // Author
        String author = "";
        Element authorEl = document.selectFirst("ul.info.info-meta li:has(h3:matchesOwn(Author)) a");
        if (authorEl == null) {
            authorEl = document.selectFirst("div.item:has(.glyphicon[title=Author]) .right a");
        }
        if (authorEl != null) {
            author = authorEl.text();
        }

        // Genres
        List<String> genres = new ArrayList<>();
        Elements genreEls = document.select("ul.info.info-meta li:has(h3:matchesOwn(Genre)) a");
        if (genreEls.isEmpty()) {
            genreEls = document.select("div.item:has(.glyphicon[title=Genre]) .right a");
        }
        for (Element g : genreEls) {
            genres.add(g.text());
        }

        // Status
        String status = "";
        Element statusEl = document.selectFirst("ul.info.info-meta li:has(h3:matchesOwn(Status)) span, ul.info.info-meta li:has(h3:matchesOwn(Status)) a");
        if (statusEl == null) {
            statusEl = document.selectFirst("div.item:has(.glyphicon[title=Status]) .right span, div.item:has(.glyphicon[title=Status]) .right a");
        }
        if (statusEl != null) {
            status = statusEl.text();
        }

        // Publisher


        // Description
        String description = "";
        Element descEl = document.selectFirst("div.m-desc div.txt div.inner");
        if (descEl != null) {
            description = descEl.text();
        }


        // Cover image
        String coverImage = document.select("div.m-book1 div.pic img").attr("src");

        // Chapters
        List<Chapter> chapters = chapterHelper.getChapters(path);
        BookDetails bookDetails = new BookDetails(title, author, genres, status, description, coverImage, chapters);
        return bookDetails;
    }
}
