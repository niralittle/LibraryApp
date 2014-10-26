package shared.model.vo;

import java.sql.Date;

/**
 * Created by Катерина on 26.10.2014.
 */
public class OrderEntry extends EntityWithId {

    public enum OEStates {

        FRESH(1),
        COMPLETED(2),
        RECEIVED(3),
        CANCELLED(4);

        private int id;

        OEStates(int id) {
            this.id = id;
        }

        public int getId() {
            return this.id;
        }
    }

    private Date waitingSince;
    private Date dueDate;
    private int status;

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
}
