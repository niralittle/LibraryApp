package shared.model.vo;

/**
 * Created by Катерина on 26.10.2014.
 */
public abstract class EntityWithId {

    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
