package scrape.addon.NovelBin.service;

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

        BookDetails details = new BookDetails();


        Element titleEl = document.selectFirst("div.desc h3.title");
        if (titleEl != null) {
            details.setTitle(titleEl.text().trim());
        }


        Element coverEl = document.selectFirst("div.book img.lazy");
        if (coverEl != null) {
            String imageUrl = coverEl.attr("src");
            if (!imageUrl.startsWith("http")) {
                imageUrl = baseUrl + imageUrl;
            }
            details.setCoverImage(imageUrl);
        }


        Element authorEl = document.selectFirst("ul.info-meta li:has(h3:matchesOwn(Author)) a");
        if (authorEl != null) {
            details.setAuthor(authorEl.text().trim());
        }

        // Genres
        List<String> genres = new ArrayList<>();
        Elements genreEls = document.select("ul.info-meta li:has(h3:matchesOwn(Genre)) a");
        for (Element g : genreEls) {
            genres.add(g.text().trim());
        }
        details.setGenres(genres);


        Element statusEl = document.selectFirst("ul.info-meta li:has(h3:matchesOwn(Status)) a");
        if (statusEl != null) {
            details.setStatus(statusEl.text().trim());
        }


        Element descEl = document.selectFirst("div#tab-description, div.desc-text");
        if (descEl != null) {
            details.setDescription(descEl.text().trim());
        }


        List<Chapter> chapters = chapterHelper.getChapters(path);
        details.setChapters(chapters);
        return details;
    }
}
