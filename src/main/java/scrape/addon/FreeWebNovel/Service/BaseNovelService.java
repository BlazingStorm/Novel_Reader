package scrape.addon.FreeWebNovel.Service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.List;

public abstract class BaseNovelService {
    protected static final String baseUrl = "https://freewebnovel.com";

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

    protected void parseAlternativeTitles(String altText, List<String> alternativeTitles) {
        if (altText != null && !altText.trim().isEmpty()) {
            for (String alt : altText.split(",")) {
                String trimmed = alt.trim();
                if (!trimmed.isEmpty()) {
                    alternativeTitles.add(trimmed);
                }
            }
        }
    }

    protected String resolveImageUrl(String imgUrl) {
        if (imgUrl == null || imgUrl.isBlank()) return "";

        if (!imgUrl.startsWith("http")) {
            if (!imgUrl.startsWith("/")) imgUrl = "/" + imgUrl;
            imgUrl = baseUrl + imgUrl;
        }
        return imgUrl;
    }

}
