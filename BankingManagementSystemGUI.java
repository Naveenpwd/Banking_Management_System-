import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class Account {
    private String accountNumber;
    private String accountHolderName;
    private double balance;

    public Account(String accountNumber, String accountHolderName, double balance) {
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            JOptionPane.showMessageDialog(null, "Insufficient balance!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

class Bank {
    private List<Account> accounts;

    public Bank() {
        accounts = new ArrayList<>();
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccount(String accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}

public class BankingManagementSystemGUI extends JFrame implements ActionListener {
    private JLabel lblAccountNumber, lblAccountHolderName, lblBalance, lblAmount;
    private JTextField txtAccountNumber, txtAccountHolderName, txtBalance, txtAmount;
    private JButton btnDeposit, btnWithdraw, btnClear;
    private Bank bank;

    public BankingManagementSystemGUI() {
        setTitle("Banking Management System");
        setSize(500, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        lblAccountNumber = new JLabel("Account Number:");
        lblAccountHolderName = new JLabel("Account Holder Name:");
        lblBalance = new JLabel("Balance:");
        lblAmount = new JLabel("Amount:");

        txtAccountNumber = new JTextField();
        txtAccountHolderName = new JTextField();
        txtBalance = new JTextField();
        txtAmount = new JTextField();

        btnDeposit = new JButton("Deposit");
        btnWithdraw = new JButton("Withdraw");
        btnClear = new JButton("Clear");

        btnDeposit.addActionListener(this);
        btnWithdraw.addActionListener(this);
        btnClear.addActionListener(this);

        JPanel panel = new JPanel(new GridLayout(5, 2));
        panel.add(lblAccountNumber);
        panel.add(txtAccountNumber);
        panel.add(lblAccountHolderName);
        panel.add(txtAccountHolderName);
        panel.add(lblBalance);
        panel.add(txtBalance);
        panel.add(lblAmount);
        panel.add(txtAmount);
        panel.add(btnDeposit);
        panel.add(btnWithdraw);

        add(panel, BorderLayout.CENTER);
        add(btnClear, BorderLayout.SOUTH);

        bank = new Bank();
        bank.addAccount(new Account("1001", "John Doe", 5000));
        bank.addAccount(new Account("1002", "Jane Smith", 10000));
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDeposit) {
            String accountNumber = txtAccountNumber.getText();
            double amount = Double.parseDouble(txtAmount.getText());
            Account account = bank.getAccount(accountNumber);
            if (account != null) {
                account.deposit(amount);
                txtBalance.setText(String.valueOf(account.getBalance()));
                JOptionPane.showMessageDialog(null, "Deposit successful!");
            } else {
                JOptionPane.showMessageDialog(null, "Invalid account number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnWithdraw) {
            String accountNumber = txtAccountNumber.getText();
            double amount = Double.parseDouble(txtAmount.getText());
            Account account = bank.getAccount(accountNumber);
            if (account != null) {
                account.withdraw(amount);
                txtBalance.setText(String.valueOf(account.getBalance()));
            } else {
                JOptionPane.showMessageDialog(null, "Invalid account number!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == btnClear) {
            txtAccountNumber.setText("");
            txtAccountHolderName.setText("");
            txtBalance.setText("");
            txtAmount.setText("");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BankingManagementSystemGUI bankingSystem = new BankingManagementSystemGUI();
            bankingSystem.setVisible(true);
        });
    }
}