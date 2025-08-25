package scrape.addon.common.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor @Getter @Setter
public class ChapterContent {
    private String title;
    private List<String> content;

    public ChapterContent(String title, List<String> string) {
        this.title = title;
        this.content = string;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getContent() {
        return content;
    }

    public void setContent(List<String> content) {
        this.content = content;
    }
}
