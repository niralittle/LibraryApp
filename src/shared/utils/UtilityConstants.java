package shared.utils;

/**
 * Created by niralittle on 23.11.2014.
 */
public class UtilityConstants {

    public static final String LOGIN = "login";
    public static final String PASSWORD = "password";
    public static final String BOOKS = "books";
    public static final String NEW_ENTRY = "newEntry";
    public static final String BREAK_CONNECTION = "breakConnection";
    public static final String SUCCESS = "success";
    public static final String COMMAND = "command";
    public static final String ERROR = "error";
    public static final String ORDERS = "orders";
    public static final String USER = "user";

    public enum Command {
        CHECK_CREDENTIALS("checkCredentials", 1),
        GET_BOOK_CATALOG("getBookCatalog", 2),
        ADD_ORDER("addOrder", 3),
        GET_ORDERS("getOrders", 4);

        private String name;
        private int ord;

        public static int find(String name) {
            for (Command c : Command.values()) {
                if (c.name.equals(name)) {
                    return c.ord;
                }
            }
            return -1;
        }

        Command(String name, int ord) {
            this.name = name;
            this.ord = ord;
        }

        public int getNumber() {
            return ord;
        }
    }


}
