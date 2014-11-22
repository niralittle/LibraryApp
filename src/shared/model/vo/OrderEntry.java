package shared.model.vo;

import java.sql.Date;
import java.util.List;

/**
 * Created by Катерина on 26.10.2014.
 */
public class OrderEntry extends EntityWithId {


    private Date waitingSince;
    private Date dueDate;
    private int status;
    private List<Book> books;
    private int userId;

    public OrderEntry(int id, Date waitingSince, Date dueDate, int status) {
        super(id);
        this.waitingSince = waitingSince;
        this.dueDate = dueDate;
        this.status = status;
    }

    public OrderEntry() {}

    public Date getWaitingSince() {
        return waitingSince;
    }

    public void setWaitingSince(Date waitingSince) {
        this.waitingSince = waitingSince;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
