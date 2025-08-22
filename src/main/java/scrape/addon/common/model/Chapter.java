package scrape.addon.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


public class Chapter {
    private String title;
    private String link;

    public Chapter(String chap, String link) {
        this.title = chap;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
