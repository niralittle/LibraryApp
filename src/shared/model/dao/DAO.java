package shared.model.dao;

import shared.model.vo.EntityWithId;

import java.util.List;
import java.util.Map;

/**
 * Created by niralittle on 26.10.2014.
 */
public interface DAO {

    public abstract EntityWithId findById(int id);

    public List<EntityWithId> getByQuery(int page, int size, Map<String, String> params);
}
