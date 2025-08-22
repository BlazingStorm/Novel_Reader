package scrape.addon.NovelBin.service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public abstract class BaseNovelService {
    protected static final String baseUrl = "https://novelbin.com";

    protected Document fetchDocument(String url) throws IOException {
        return Jsoup.connect(url)
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
                        + "(KHTML, like Gecko) Chrome/115.0 Safari/537.36")
                .referrer("https://www.google.com")
                .timeout(10000)
                .get();
    }

    protected String normalizeUrl(String href) {
        if (href == null || href.isBlank()) return "";
        if (href.startsWith("http")) return href;
        return baseUrl + href;
    }

    protected String resolveImageUrl(String imgUrl) {
        if (imgUrl == null || imgUrl.isBlank()) return "";
        // Prepend baseUrl if it’s a relative path
        if (!imgUrl.startsWith("http")) {
            if (!imgUrl.startsWith("/")) imgUrl = "/" + imgUrl;
            imgUrl = baseUrl + imgUrl;
        }
        return imgUrl;
    }


}
