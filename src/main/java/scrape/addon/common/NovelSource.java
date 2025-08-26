    package scrape.addon.common;




    import scrape.addon.common.model.Book;
    import scrape.addon.common.model.BookDetails;
    import scrape.addon.common.model.ChapterContent;

    import java.io.IOException;
    import java.util.List;

    public interface NovelSource {
        String getSourceName();
        List<Book> scrapeNovels() throws IOException;
        List<Book> search(String query) throws Exception;
        BookDetails getBookDetails(String path) throws Exception;
        ChapterContent getChapterContent(String url) throws Exception;
    }
