package shared.model.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by niralittle on 26.10.2014.
 */
public interface DAO <T> {

    public abstract T findById(int id);

    public List<T> getByQuery(Map<String, String> params);
}
