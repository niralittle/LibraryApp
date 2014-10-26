package shared.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niralittle on 26.10.2014.
 */
public abstract class PingPong implements Serializable{

    private Map<String, String> params;

    public PingPong() {}

    public void setParams(Map params) {
        this.params = params;
    }

    public void addParams(String name, String value) {
        if (params == null) {
            params = new HashMap<String, String>();
        }
        params.put(name, value);
    }

    public Map getParams() {
        return params;
    }

    public static class ClientRequest extends PingPong {}
    public static class ServerResponse extends PingPong {}
}
