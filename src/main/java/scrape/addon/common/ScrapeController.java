package scrape.addon.common;

import org.springframework.web.bind.annotation.*;
import scrape.addon.common.model.Book;
import scrape.addon.common.model.Chapter;
import scrape.addon.common.model.ChapterContent;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/scrape")
public class ScrapeController {

    private final Map<String, NovelSource> sources = new HashMap<>();


    public ScrapeController(List<NovelSource> novelSources) {
        for (NovelSource source : novelSources) {
            sources.put(source.getSourceName().toLowerCase(), source);
        }
    }

    @GetMapping("/novels")
    public List<Book> getNovels(@RequestParam String source) throws IOException {
        NovelSource novelSource = sources.get(source.toLowerCase());
        if (novelSource == null) throw new IllegalArgumentException("Unknown source: " + source);
        return novelSource.scrapeNovels();
    }

    @GetMapping("/chapters")
    public List<Chapter> getChapters(@RequestParam String source, @RequestParam String path) throws Exception {
        NovelSource novelSource = sources.get(source.toLowerCase());
        if (novelSource == null) throw new IllegalArgumentException("Unknown source: " + source);
        return novelSource.getChapters(path);
    }

    @GetMapping("/chapter")
    public ChapterContent getChapter(@RequestParam String source, @RequestParam String path) throws Exception {
        NovelSource novelSource = sources.get(source.toLowerCase());
        if (novelSource == null) throw new IllegalArgumentException("Unknown source: " + source);
        return novelSource.getChapterContent(path);
    }

    @GetMapping("/search")
    public List<Book> search(@RequestParam String source, @RequestParam String keyword) throws Exception {
        NovelSource novelSource = sources.get(source.toLowerCase());
        if (novelSource == null) throw new IllegalArgumentException("Unknown source: " + source);
        return novelSource.search(keyword);
    }
}
