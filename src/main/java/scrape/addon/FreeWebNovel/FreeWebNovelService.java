package scrape.addon.FreeWebNovel;

import org.springframework.stereotype.Service;
import scrape.addon.FreeWebNovel.Service.BookDetailsHelper;
import scrape.addon.FreeWebNovel.Service.ChapterHelper;
import scrape.addon.FreeWebNovel.Service.ScrapeHelper;
import scrape.addon.FreeWebNovel.Service.SearchHelper;
import scrape.addon.common.NovelSource;
import scrape.addon.common.model.Book;
import scrape.addon.common.model.BookDetails;
import scrape.addon.common.model.Chapter;
import scrape.addon.common.model.ChapterContent;

import java.io.IOException;
import java.util.List;

@Service
public class FreeWebNovelService implements NovelSource {

    private final ScrapeHelper scrapeHelper = new ScrapeHelper();
    private final ChapterHelper chapterHelper = new ChapterHelper();
    private final SearchHelper searchHelper = new SearchHelper();
    private final BookDetailsHelper bookDetailsHelper=new BookDetailsHelper();

    @Override
    public String getSourceName() {
        return "freewebnovel";
    }

    @Override
    public List<Book> scrapeNovels() throws IOException {
        return scrapeHelper.scrapeNovels();
    }

    @Override
    public List<Book> search(String keyword) throws IOException {
        return searchHelper.search(keyword);
    }

    @Override
    public BookDetails getBookDetails(String path) throws Exception {
        return bookDetailsHelper.getBookDetails(path);
    }

    @Override
    public ChapterContent getChapterContent(String path) throws IOException {
        return chapterHelper.getChapterContent(path);
    }
}
