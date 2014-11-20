package shared.view.desktop;

import controller.client.LoginController;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by niralittle on 28.10.2014.
 */
public abstract class MainWindow {
    private static JFrame loginView;
    private static ActionListener loginListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String user = userText.getText();
            String password = new String(passwordText.getPassword());
            LoginController.verifyLoginPassword(user, password);
            //loginView.removeAll();
            loginView.setVisible(false);
            placeCatalogComponents();


        }
    };



    private static JTextField userText;
    private static JPasswordField passwordText;

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                placeLoginComponents();
            }
        });
    }

    private static void placeLoginComponents() {
        loginView = new JFrame("Log in to Library");
        loginView.setSize(300, 160);
        loginView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        loginView.add(panel);


        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(loginListener);
        panel.add(loginButton);
        loginView.setVisible(true);
    }

    private static void placeCatalogComponents() {
        loginView.setSize(300, 500);

        JPanel panel = new JPanel();
        loginView.add(panel);


        panel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText = new JTextField(20);
        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText = new JPasswordField(20);
        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        JButton loginButton = new JButton("login");
        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(loginListener);
        panel.add(loginButton);
        loginView.setVisible(true);
    }
}
