package controller.client;

import java.io.IOException;

/**
 * Created by niralittle on 26.10.2014.
 */
public class SecurityCheckOnClient {

    public static Result ifUserExists(String username, String password) throws IOException {
        SocketConnectionEstablisher.connect();
        return SocketConnectionEstablisher.executeLoginAttempt(username, password);
    }

    static class Result {
        private boolean checkPassed;
        private boolean isAdmin;
        private int userID;

        public Result(boolean check, boolean isAdmin, int userID ) {
            this.checkPassed = check;
            this.isAdmin = isAdmin;
            this.userID = userID;
        }

        public boolean isCheckPassed() {
            return checkPassed;
        }

        public boolean isAdmin() {
            return isAdmin;
        }
        public int userId() {
            return userID;
        }
    }
}
