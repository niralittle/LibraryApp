package controller.client;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Created by niralittle on 26.10.2014.
 */

public class LoginForm {

    private static final String WRONG_CREDENTIALS = "Unsuccessful, please check credentials";
    private static final String EMPTY_FIELDS = "Fill all the blanks to proceed";

    static JLabel yellAtUser = new JLabel();
    final static JLabel userLabel = new JLabel("User");
    static JTextField userText = new JTextField(20);
    final static JLabel passwordLabel = new JLabel("Password");
    static JPasswordField passwordText = new JPasswordField(20);
    static JButton loginButton = new JButton("login");

    private static void placeComponents(final JPanel panel) {
        panel.setLayout(null);

        yellAtUser.setVisible(false);
        panel.add(yellAtUser);

        userLabel.setBounds(10, 10, 80, 25);
        panel.add(userLabel);

        userText.setBounds(100, 10, 160, 25);
        panel.add(userText);

        passwordLabel.setBounds(10, 40, 80, 25);
        panel.add(passwordLabel);

        passwordText.setBounds(100, 40, 160, 25);
        panel.add(passwordText);

        loginButton.setBounds(10, 80, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());
                if (isEmptyOrNull(user) || isEmptyOrNull(password)) {
                    notifyOfError(EMPTY_FIELDS);
                    return;
                }
                SecurityCheckOnClient.Result res;
                try {
                    res = SecurityCheckOnClient.ifUserExists(user, password);
                    if (!res.isCheckPassed()) {
                        panel.setVisible(false);
                        return;
                    }

                } catch (IOException e1) {
                    e1.printStackTrace();
                }
                notifyOfError(WRONG_CREDENTIALS);
            }
        });
        panel.add(loginButton);
    }

    public static boolean isEmptyOrNull(String s) {
        return (s == null) || s.isEmpty();
    }

    private static void notifyOfError(String notification) {
        yellAtUser.setText("Fill all the blanks to proceed");
        yellAtUser.setVisible(true);
    }

    public static void main(String... args) {

    }
}
