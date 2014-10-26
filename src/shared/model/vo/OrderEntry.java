package shared.model.vo;

import java.sql.Date;
import java.util.List;

/**
 * Created by Катерина on 26.10.2014.
 */
public class OrderEntry extends EntityWithId {


    public enum BookStatus {

        COMPLETED(1),
        PREPARED(2),
        RECEIVED(3),
        CANCELLED(4);

        protected int id;

        BookStatus(int num) {
            this.id = id;
        }

        public int detId() {
            return this.id;
        }
    }

    private int userId;
    private Date waitingSince;   // completed orders not picked for 5 days become cancelled
    private Date dueDate;        // when the books are to be returned
    private int status;
    private List<Book> books;

    public OrderEntry(int userId, Date waitingSince, Date dueDate, int status, List<Book> books) {
        this.userId = userId;
        this.waitingSince = waitingSince;
        this.dueDate = dueDate;
        this.status = status;
        this.books = books;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

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

}
