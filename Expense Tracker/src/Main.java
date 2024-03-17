import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main {

	
	 public static void main(String[] args) {
	        // invoke the SwingUtilities.invokeLater method to ensure that the GUI is created 
	        SwingUtilities.invokeLater(new Runnable() {
	            @Override
	            public void run() { 
	               
	                new ExpenseTracker(); // instance of expense tracker to run
	            }
	        });
	    }

}
