import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ATMInterfaceGUI extends JFrame implements ActionListener {

    JTextField userField;
    JPasswordField pinField;
    JButton loginButton;

    JButton withdrawBtn, depositBtn, transferBtn, balanceBtn, historyBtn, exitBtn;

    double balance = 10000;
    ArrayList<String> transactions = new ArrayList<>();

    ATMInterfaceGUI() {
        setTitle("ATM Interface");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        showLoginScreen();
        setVisible(true);
    }

    void showLoginScreen() {
        getContentPane().removeAll();
        setLayout(new GridLayout(4, 2, 10, 10));

        JLabel title = new JLabel("ATM LOGIN", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 18));

        userField = new JTextField();
        pinField = new JPasswordField();
        loginButton = new JButton("Login");

        add(title);
        add(new JLabel(""));
        add(new JLabel("User ID:"));
        add(userField);
        add(new JLabel("PIN:"));
        add(pinField);
        add(new JLabel(""));
        add(loginButton);

        loginButton.addActionListener(this);

        revalidate();
        repaint();
    }

    void showMenu() {
        getContentPane().removeAll();
        setLayout(new GridLayout(3, 2, 10, 10));

        withdrawBtn = new JButton("Withdraw");
        depositBtn = new JButton("Deposit");
        transferBtn = new JButton("Transfer");
        balanceBtn = new JButton("Check Balance");
        historyBtn = new JButton("History");
        exitBtn = new JButton("Exit");

        JButton[] buttons = {withdrawBtn, depositBtn, transferBtn, balanceBtn, historyBtn, exitBtn};

        for (JButton b : buttons) {
            b.addActionListener(this);
            add(b);
        }

        revalidate();
        repaint();
    }

    public void actionPerformed(ActionEvent e) {

        // LOGIN
        if (e.getSource() == loginButton) {
            String user = userField.getText();
            String pin = new String(pinField.getPassword());

            if (user.equals("admin") && pin.equals("1234")) {
                JOptionPane.showMessageDialog(this, "Login Successful");
                showMenu();
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Login");
            }
        }

        // WITHDRAW
        if (e.getSource() == withdrawBtn) {
            String input = JOptionPane.showInputDialog(this, "Enter amount:");
            try {
                double amt = Double.parseDouble(input);
                if (amt > 0 && amt <= balance) {
                    balance -= amt;
                    transactions.add("Withdrawn ₹" + amt);
                    JOptionPane.showMessageDialog(this, "Success");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            }
        }

        // DEPOSIT
        if (e.getSource() == depositBtn) {
            String input = JOptionPane.showInputDialog(this, "Enter amount:");
            try {
                double amt = Double.parseDouble(input);
                balance += amt;
                transactions.add("Deposited ₹" + amt);
                JOptionPane.showMessageDialog(this, "Deposited Successfully");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            }
        }

        // TRANSFER
        if (e.getSource() == transferBtn) {
            String acc = JOptionPane.showInputDialog(this, "Enter account number:");
            String input = JOptionPane.showInputDialog(this, "Enter amount:");
            try {
                double amt = Double.parseDouble(input);
                if (amt <= balance) {
                    balance -= amt;
                    transactions.add("Transferred ₹" + amt + " to " + acc);
                    JOptionPane.showMessageDialog(this, "Transfer Successful");
                } else {
                    JOptionPane.showMessageDialog(this, "Insufficient Balance");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Invalid Input");
            }
        }

        // BALANCE
        if (e.getSource() == balanceBtn) {
            JOptionPane.showMessageDialog(this, "Balance: ₹" + balance);
        }

        // HISTORY
        if (e.getSource() == historyBtn) {
            if (transactions.isEmpty()) {
                JOptionPane.showMessageDialog(this, "No Transactions");
            } else {
                String msg = "";
                for (String t : transactions) {
                    msg += t + "\n";
                }
                JOptionPane.showMessageDialog(this, msg);
            }
        }

        // EXIT
        if (e.getSource() == exitBtn) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ATMInterfaceGUI());
    }
}
