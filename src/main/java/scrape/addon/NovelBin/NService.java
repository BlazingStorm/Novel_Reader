package scrape.addon.NovelBin;

import org.springframework.stereotype.Service;

import scrape.addon.FreeWebNovel.Service.BookDetailsHelper;
import scrape.addon.NovelBin.service.ChapterHelper;
import scrape.addon.NovelBin.service.ScraperHelper;
import scrape.addon.NovelBin.service.SearchHelper;
import scrape.addon.common.NovelSource;
import scrape.addon.common.model.Book;
import scrape.addon.common.model.BookDetails;
import scrape.addon.common.model.Chapter;
import scrape.addon.common.model.ChapterContent;

import java.io.IOException;
import java.util.List;

@Service
public class NService implements NovelSource {

    private final ScraperHelper scrapeHelper = new ScraperHelper();
    private final ChapterHelper chapterHelper = new ChapterHelper();
    private final SearchHelper searchHelper = new SearchHelper();
    private final BookDetailsHelper bookDetailsHelper=new BookDetailsHelper();

    @Override
    public String getSourceName() {
        return "NovelBin";
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
    public BookDetails getBookDetails(String path) throws IOException {
        return bookDetailsHelper.getBookDetails(path);
    }

    @Override
    public ChapterContent getChapterContent(String path) throws IOException {
        return chapterHelper.getChapterContent(path);
    }
}
