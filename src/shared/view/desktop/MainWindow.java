package shared.view.desktop;

import controller.client.BookCatalog;
import controller.client.LoginController;
import shared.model.vo.Book;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by niralittle on 28.10.2014.
 */
public abstract class MainWindow {
    private static JFrame view;
    private static ActionListener loginListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String user = userText.getText();
            String password = new String(passwordText.getPassword());
            if (LoginController.verifyLoginPassword(user, password)) {
                view.setVisible(false);
                placeCatalogComponents();
            } else {
                JOptionPane.showMessageDialog(view,
                        "Username or password is not correct!",
                        "Wrong credentials",
                        JOptionPane.ERROR_MESSAGE);
            }        }
    };



    private static JTextField userText;
    private static JPasswordField passwordText;

    public static void main(String[] args) {
        placeLoginComponents();
    }

    private static void placeLoginComponents() {
        view = new JFrame("Log in to Library");
        view.setSize(300, 160);
        view.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        view.add(panel);


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
        view.setVisible(true);
    }

    private static void placeCatalogComponents() {
        view.getContentPane().removeAll();
        view.setSize(300, 500);

        JPanel panel = new JPanel();
        view.add(panel);
        view.setTitle("Book Catalog");

        panel.setLayout(new BorderLayout());
        List<Book> books = BookCatalog.getAllBooks();

        String[] listData = new String[books.size()];
        for (int i = 0; i < listData.length; i++) {
            listData[i] = books.get(i).getTitle() + " (" + books.get(i).getAuthors() + ")";
        }
        JList<String> list = new JList<>(listData);

        JScrollPane listPane = new JScrollPane(list);

        JPanel topHalf = new JPanel();
        topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.LINE_AXIS));
        JPanel listContainer = new JPanel(new GridLayout(1,1));
        listContainer.setBorder(BorderFactory.createTitledBorder("List"));
        listContainer.add(listPane);

        topHalf.setBorder(BorderFactory.createEmptyBorder(5,5,0,5));
        topHalf.add(listContainer);

        topHalf.setPreferredSize(new Dimension(100, 430));
        view.add(topHalf, BorderLayout.NORTH);
        JButton sendButton = new JButton("Send");
        sendButton.setBounds(10, 80, 80, 25);
        //sendButton.addActionListener(sendListener);
        panel.add(sendButton);
        JPanel bottomHalf = new JPanel(new BorderLayout());
        bottomHalf.setPreferredSize(new Dimension(450, 135));

        view.setVisible(true);

        JOptionPane.showMessageDialog(view,
                "Welcome to Library app.",
                "Welcome",
                JOptionPane.INFORMATION_MESSAGE);
    }
}
