import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
// extend JFrame to create gui app
public class ExpenseTracker extends JFrame {
    private ArrayList<Category> expenses; // array to hold categories
    private DefaultListModel<String> expenseListModel; // to manage the data for the list in hui
    private JList<String> expenseList;  // to display the list
    private JTextField categoryNameField; // text field for category name
    private JTextField spendingLimitField;// text field for expense name

    public ExpenseTracker() {
    	//constructor
        expenses = new ArrayList<>();
        expenseListModel = new DefaultListModel<>();
        expenseList = new JList<>(expenseListModel);
        createAndShowGUI();
    }

    private void createAndShowGUI() {
    	// setting up gui
        setTitle("Expense Tracker");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // creating gui components (buttons & text fields)
        categoryNameField = new JTextField(20);
        spendingLimitField = new JTextField(10);
        JButton addButton = new JButton("Add Category");
        JButton editButton = new JButton("Edit Selected Category");

        // creating gui of expense list
        JPanel listPanel = new JPanel();
        listPanel.add(new JScrollPane(expenseList));
        add(listPanel, BorderLayout.CENTER);

        // input and output panels creation
        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Category Name:"));
        inputPanel.add(categoryNameField);
        inputPanel.add(new JLabel("Spending Limit:"));
        inputPanel.add(spendingLimitField);
        inputPanel.add(addButton);
        inputPanel.add(editButton);
        add(inputPanel, BorderLayout.SOUTH);

        // create buttons and perform action when button is pressed (for both add and edit expenses)
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addExpense();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editExpense();
            }
        });

        setVisible(true); // to make the gui window pop up
    }

    public void addExpense() { // function of adding expenses
        String description = categoryNameField.getText().trim(); // trim to remove leading and trailing spaces

        // check if the category name is empty to pop up an error if so
        if (description.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a category name.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // check if the character length is satisfied and display error if not
        if (description.length() > 20) {
            JOptionPane.showMessageDialog(this, "Category name must be 20 characters or less.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }
        double amount;
        try {
            amount = Double.parseDouble(spendingLimitField.getText()); // convert the amount to user input
            if (amount <= 0 || amount>10000) { // check if the amount range is satisfied and display error if not
                JOptionPane.showMessageDialog(this, "You cannot enter above 10000 or a negative value or a 0 for the spending limit.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException e) {
          // show error message if the spending limit field was empty or not a number
            JOptionPane.showMessageDialog(this, "Please enter a valid number for the spending limit.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Category expense = new Category(description, amount); // creating new instance of category with the user input
        expenses.add(expense); // adding the category to the list
        expenseListModel.addElement(expense.toString());
        categoryNameField.setText(""); // making the text fields empty for the next user entry
        spendingLimitField.setText("");
        System.out.println("Expense added!"); // displaying on the normal console (for experimental purposes)
    }

    public void editExpense() {
        int index = expenseList.getSelectedIndex(); // to select what the user clicked on from the list
        if (index != -1 && index < expenses.size()) {
            String description = JOptionPane.showInputDialog("Enter new description:"); // the rest of the logic is same as add expense
            if (description == null || description.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Category name cannot be empty.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (description.length() > 20) {
                JOptionPane.showMessageDialog(this, "Category name must be 20 characters or less.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            double amount;
            try {
                String amountString = JOptionPane.showInputDialog("Set new spending limit:");
                amount = Double.parseDouble(amountString);

                if (amount <= 0 || amount>10000) {
                	JOptionPane.showMessageDialog(this, "You cannot enter above 10000 or a negative value or a 0 for the spending limit.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Please enter a valid number for the spending limit.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Category expense = expenses.get(index);
            expense.setDescription(description);
            expense.setAmount(amount);
            expenseListModel.set(index, expense.toString());
            System.out.println("Expense updated!");
        } else {
            JOptionPane.showMessageDialog(this, "Invalid index selected.", "Invalid Selection", JOptionPane.ERROR_MESSAGE);
        }
    }


    
}
