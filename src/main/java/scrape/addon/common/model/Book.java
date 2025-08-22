package scrape.addon.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;


public class Book {
    private String title;
    private String link;
    private String img;

    public Book(String title, String link, String img) {
        this.title = title;
        this.link = link;
        this.img = img;
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

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
