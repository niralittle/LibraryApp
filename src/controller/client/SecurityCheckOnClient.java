package controller.client;

import java.io.IOException;

/**
 * Created by niralittle on 26.10.2014.
 */
public class SecurityCheckOnClient {

    public static Result ifUserExists(String username, String password) throws IOException {
        ConnectionEstablisher.connect();
        return ConnectionEstablisher.executeLoginAttempt(username, password);
    }

    static class Result {
        private boolean checkPassed;
        private boolean isAdmin;

        public Result(boolean check, boolean isAdmin) {
            this.checkPassed = check;
            this.isAdmin = isAdmin;
        }

        public boolean isCheckPassed() {
            return checkPassed;
        }

        public boolean isAdmin() {
            return isAdmin;
        }
    }
}
