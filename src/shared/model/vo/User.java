package shared.model.vo;

/**
 * Created by Катерина on 26.10.2014.
 */
public class User extends EntityWithId {

    private boolean isAdmin;
    private String login;
    private String password;

    public User(int id, boolean isAdmin, String login, String password) {
        super(id);
        setIsAdmin(isAdmin);
        this.login = login;
        this.password = password;
    }

    public User() {}

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

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
}
