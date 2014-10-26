package shared.model.vo;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Катерина on 26.10.2014.
 */
public class User extends EntityWithId {

    public User(String login, String password, List<Integer> orders, boolean isAdmin) {
        this.login = login;
        this.password = password;
        this.orders = orders;
        this.isAdmin = isAdmin;
    }

    public User() {
        this.login = null;
        this.password = null;
        this.orders = new LinkedList<Integer>();
        this.isAdmin = false;
    }

    private String login;
    private String password; // bad, but security isn't the main goal in this lab
    private List<Integer> orders;
    private boolean isAdmin;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Integer> getOrders() {
        return orders;
    }

    public void setOrders(List<Integer> orders) {
        this.orders = orders;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }
}
