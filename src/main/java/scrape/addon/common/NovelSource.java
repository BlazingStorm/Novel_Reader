package scrape.addon.common;




import scrape.addon.common.model.Book;
import scrape.addon.common.model.ChapterContent;

import java.io.IOException;
import java.util.List;

public interface NovelSource {
    String getSourceName();
    List<Book> scrapeNovels() throws IOException;
    List<Book> search(String query) throws Exception;
    List<scrape.addon.common.model.Chapter> getChapters(String url) throws Exception;
    ChapterContent getChapterContent(String url) throws Exception;
}
