package shared.model.vo;

/**
 * Created by Катерина on 26.10.2014.
 */
public class LibUser extends EntityWithId {

    private boolean isAdmin;
    private String login;
    private String password;

    public LibUser(int id, int isAdmin, String login, String password) {
        super(id);
        setIsAdmin(isAdmin);
        this.login = login;
        this.password = password;
    }

    public LibUser() {}

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(int isAdmin) {
        this.isAdmin = (1 == isAdmin);
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
