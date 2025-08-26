package scrape.addon.common.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

public class BookDetails {
    private String title;

    private String author;
    private List<String> genres;
    private String status;
    private String coverImage;
    private String description;
    private List<Chapter> chapters;

    public BookDetails() {}
    public BookDetails(String title, String author, List<String> genres, String status, String coverImage, String description, List<Chapter> chapters) {
        this.title = title;
        this.author = author;
        this.genres = genres;
        this.status = status;
        this.coverImage = coverImage;
        this.description = description;
        this.chapters = chapters;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setGenres(List<String> genres) {
        this.genres = genres;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChapters(List<Chapter> chapters) {
        this.chapters = chapters;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public List<String> getGenres() {
        return genres;
    }

    public String getStatus() {
        return status;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public String getDescription() {
        return description;
    }

    public List<Chapter> getChapters() {
        return chapters;
    }
}
