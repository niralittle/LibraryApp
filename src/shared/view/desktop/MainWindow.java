package shared.view.desktop;

import javax.swing.*;

/**
 * Created by niralittle on 28.10.2014.
 */
public abstract class MainWindow {
    private static JFrame view;

    private static int currentUserID;

    public static JFrame getView() {
        return view;
    }

    public static void setView(JFrame view) {
        MainWindow.view = view;
    }

    public static int getCurrentUserID() {
        return currentUserID;
    }

    public static void setCurrentUserID(int currentUserID) {
        MainWindow.currentUserID = currentUserID;
    }
}
