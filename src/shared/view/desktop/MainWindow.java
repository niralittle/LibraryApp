package shared.view.desktop;

import controller.client.ClientController;
import shared.model.vo.Book;
import shared.model.vo.OrderEntry;
import shared.model.vo.User;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by niralittle on 28.10.2014.
 */
public abstract class MainWindow {

    private static JFrame view;
    private static String USER;
    private static int USER_ID;

    private static ActionListener loginListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            String username = userText.getText();
            String password = new String(passwordText.getPassword());
            User user = ClientController.getInstance().authorize(username, password);
            if (user != null) {
                USER_ID = user.getId();
                if (user.isAdmin()) {
                    placeAdminComponents();
                } else {
                    placeCatalogComponents();
                }
                USER = username;
            } else {
                JOptionPane.showMessageDialog(view,
                        (username.isEmpty() && password.isEmpty())
                                ? "Fill all the blanks to proceed"
                                : "Unsuccessful, please check credentials",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        }
    };


    private static JTextField userText;
    private static JPasswordField passwordText;

    public static void main(String[] args) {
        placeLoginComponents();
    }

    private static void placeLoginComponents() {
        view = new JFrame("Log in to Library");
        view.setSize(300, 450);
        view.setLocationRelativeTo(null);
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
        final List<Book> books = ClientController.getInstance().getBookCatalogData();

        view.setVisible(false);
        view.getContentPane().removeAll();
        view.setSize(400, 500);

        JPanel panel = new JPanel();
        view.add(panel);
        view.setTitle("Book Catalog");

        panel.setLayout(new BorderLayout());

        String[] listData = new String[books.size()];
        for (int i = 0; i < listData.length; i++) {
            listData[i] = books.get(i).getTitle() + " (" + books.get(i).getAuthors() + ", "
                    + books.get(i).getNumberOfPages() + " pages)";
        }
        final JList<String> list = new JList<>(listData);

        JScrollPane listPane = new JScrollPane(list);

        JPanel topHalf = new JPanel();
        topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.LINE_AXIS));
        JPanel listContainer = new JPanel(new GridLayout(1, 1));
        listContainer.setBorder(BorderFactory.createTitledBorder("List"));
        listContainer.add(listPane);

        topHalf.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        topHalf.add(listContainer);

        topHalf.setPreferredSize(new Dimension(100, 430));
        view.add(topHalf, BorderLayout.NORTH);
        JButton sendButton = new JButton("Send order to processing");
        sendButton.setBounds(10, 80, 80, 25);
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (list.getSelectedIndices().length == 0) {
                    JOptionPane.showMessageDialog(view,
                            "Select at least one book.",
                            "Warning",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    List<Book> orderedBooks = new ArrayList<>();
                    for (int index : list.getSelectedIndices()) {
                        orderedBooks.add(books.get(index));
                    }
                    OrderEntry oe = new OrderEntry();
                    oe.setUserId(USER_ID);
                    oe.setBooks(orderedBooks);
                    ClientController.getInstance().createNewOrder(oe);
                    JOptionPane.showMessageDialog(view,
                            "Order created.",
                            "Order",
                            JOptionPane.INFORMATION_MESSAGE);

                }
            }
        });
        panel.add(sendButton);
        JPanel bottomHalf = new JPanel(new BorderLayout());
        bottomHalf.setPreferredSize(new Dimension(450, 135));

        view.setVisible(true);

        JOptionPane.showMessageDialog(view,
                "Welcome to Library app.",
                "Welcome",
                JOptionPane.INFORMATION_MESSAGE);
    }

    private static void placeAdminComponents() {
        final List<OrderEntry> orders = ClientController.getInstance().getOEData();

        view.setVisible(false);
        view.getContentPane().removeAll();
        view.setSize(400, 500);

        JPanel panel = new JPanel();
        view.add(panel);
        view.setTitle("Orders");

        panel.setLayout(new BorderLayout());

        String[] listData = new String[orders.size()];
        for (int i = 0; i < listData.length; i++) {
            listData[i] = String.format("ID: %d, userID: %d, DATE: %s", orders.get(i).getId(),
                    orders.get(i).getUserId(), orders.get(i).getWaitingSince().toString());
        }
        final JList<String> list = new JList<>(listData);
        list.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);


        JScrollPane listPane = new JScrollPane(list);

        JPanel topHalf = new JPanel();
        topHalf.setLayout(new BoxLayout(topHalf, BoxLayout.LINE_AXIS));
        JPanel listContainer = new JPanel(new GridLayout(1, 1));
        listContainer.setBorder(BorderFactory.createTitledBorder("List"));
        listContainer.add(listPane);

        topHalf.setBorder(BorderFactory.createEmptyBorder(5, 5, 0, 5));
        topHalf.add(listContainer);

        topHalf.setPreferredSize(new Dimension(100, 250));
        view.add(topHalf, BorderLayout.NORTH);
        JTable books = new JTable();
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(new String[]{"ID", "Title", "Authors"});
        model.addRow(new String[]{"", "", ""});
        books.setModel(model);


        panel.add(new JScrollPane(books));
        JPanel bottomHalf = new JPanel(new BorderLayout());
        bottomHalf.setPreferredSize(new Dimension(450, 135));

        view.setVisible(true);

        JOptionPane.showMessageDialog(view,
                "Welcome to Library app.",
                "Welcome",
                JOptionPane.INFORMATION_MESSAGE);

        list.getSelectionModel().addListSelectionListener(new PrivateListener(orders, books));


    }

    private static class PrivateListener implements ListSelectionListener {
        private List<OrderEntry> orders;
        private JTable table;
        public PrivateListener(List<OrderEntry> orders, JTable table) {
            this.orders = orders;
            this.table = table;
        }

        @Override
        public void valueChanged(ListSelectionEvent e) {
            if (!e.getValueIsAdjusting()) {
                int index = ((DefaultListSelectionModel) e.getSource()).getAnchorSelectionIndex();
                DefaultTableModel tableModel = (DefaultTableModel) (table.getModel());
                while (tableModel.getRowCount() > 0) {
                    tableModel.removeRow(0);
                }
                for (Book book : orders.get(index).getBooks()) {
                    tableModel.addRow(new Object[]{book.getId(), book.getTitle(), book.getAuthors()});
                }
                tableModel.fireTableDataChanged();
                table.setModel(tableModel);
                table.repaint();

            }
        }
    }
}
