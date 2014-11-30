package shared.model.vo;

import java.io.Serializable;

/**
 * Created by Катерина on 26.10.2014.
 */
public abstract class EntityWithId implements Serializable {

    protected EntityWithId(int id) {
        this.id = id;
    }
    public EntityWithId() {}
    protected int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
