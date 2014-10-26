package shared.model.vo;


/**
 * Created by Катерина on 26.10.2014.
 */
public class Book extends EntityWithId{

    public Book(String title, String authors, String description, int rating, int numberOfPages, String category) {
        this.title = title;
        this.authors = authors;
        this.description = description;
        this.rating = rating;
        this.numberOfPages = numberOfPages;
        this.category = category;
    }

    public Book() {
        this.rating = -1;
        this.numberOfPages = -1;
    }

    private String title;
    private String authors;
    private String description;
    private int rating;
    private int numberOfPages;
    private String category;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
