package Andre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Created by Andre on 5/11/2015.
 */
public class EntryValidation extends JFrame{
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;
    private JLabel label4;
    private JLabel label5;
    private JLabel label6;
    private JLabel label7;
    private JLabel label8;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField6;
    private JTextField textField7;
    private JTextField textField8;
    private JButton okButton;
    private JButton cancelButton;
    private JPanel entryPanel;

    private boolean isId;
    private boolean isString;
    private boolean isInsert;
    private SearchDatabase sd;
    protected int ID;
    public boolean basement;
    public DatabaseController dbc = Main.db;
    public ResultSet rs;


    public void setOkButtonText(String okButton) {
        this.okButton.setText(okButton);
    }

    public EntryValidation() {
        super("Entry Validation");
        setContentPane(entryPanel);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);

        cancelButton.addActionListener(new ActionListener() {
            @Override
             public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // check for the parameters we want to validate
                // if it is an ID or string, also check where we validate consigner or album
                if (isId) {
                    if (okButton.getText().equalsIgnoreCase("Search Consignor")) {
                        String s = textField1.getText();
                        //If the entered integer is valid show the search result
                        //otherwise just ignore it
                        if (validateInteger(s)) {
                            rs = dbc.selectConsignorById(ID);
                            openSearch(rs);
                        }
                    }
                    if (okButton.getText().equalsIgnoreCase("Search Album")) {
                        String s = textField1.getText();
                        //If the entered integer is valid show the search result
                        //otherwise just ignore it
                        if (validateInteger(s)) {
                            if (basement){
                                rs = dbc.selectBasementByConsignor(ID);
                                openSearch(rs);
                            }
                            else {
                                rs = dbc.selectAlbumByConsignor(ID);
                                openSearch(rs);
                            }
                        }
                    }
                }
                if (isString) {
                    if (okButton.getText().equalsIgnoreCase("Search Album")) {
                        String s = textField1.getText();
                        //If the entered string is valid show the search result
                        //otherwise just ignore it
                        if (label1.getText().equalsIgnoreCase("Song:")){
                            if (validateSearchString(s, "Song")) {
                                if (basement){
                                    rs = dbc.selectBasementBySong(s);
                                    openSearch(rs);
                                }
                                else {
                                    rs = dbc.selectAlbumBySong(s);
                                    openSearch(rs);
                                }
                            }
                        }
                        else {
                            if (validateSearchString(s, "Artist")) {
                                if (basement){
                                    rs = dbc.selectBasementByArtist(s);
                                    openSearch(rs);
                                }
                                else {
                                    rs = dbc.selectAlbumByArtist(s);
                                    openSearch(rs);
                                }
                            }
                        }
                    }
                    else {
                        String s = textField1.getText();
                        //If the entered string is valid show the search result
                        //otherwise just ignore it
                        if (validateSearchString(s, "Last Name")) {
                            rs = dbc.selectConsignorByLast(s);
                            openSearch(rs);
                        }
                    }
                }
                if (isInsert) {
                    String fName = textField1.getText();
                    String lName = textField2.getText();
                    String phone = textField3.getText();
                    String email = textField4.getText();
                    String owned = textField5.getText();
                    String paid = textField6.getText();

                    if (validateString(fName, "First Name", false, false) && validateString(lName, "Last Name", false, false) &&
                            validateString(phone, "Phone Nr.", true, true) && validateString(email, "E-Mail", true, false) &&
                            validateFloat(owned) && validateFloat(paid)) {
                        float own = Float.parseFloat(owned);
                        float pay = Float.parseFloat(paid);
                        // if the insertion is successful return the updated consignor list
                        if (!dbc.addConsignor(fName, lName, phone, email, own, pay)) {
                            rs = dbc.selectAllConsignors();
                            openSearch(rs);
                            clear();
                         }
                        //otherwise show an error
                        else {
                            JOptionPane.showMessageDialog(null,
                                    "Sorry but the consignor could not be added",
                                    "Error", JOptionPane.ERROR_MESSAGE);
                        }

                    }
                }

            }
        });
    }

    //Open entry validator for Id input and validation
    // of database searches
    public void entrySearchID(String table) {
        setSize(300, 200);
        label1.setText(table + " ID:");
        label1.setVisible(true);
        textField1.setVisible(true);
        isId = true;
    }

    public void entrySearchString(String search) {
        setSize(300, 200);
        label1.setText(search + ":");
        label1.setVisible(true);
        textField1.setVisible(true);
        isString = true;
    }

    public void entryConsignor() {
        label1.setText("First Name:");
        label1.setVisible(true);
        textField1.setVisible(true);
        label2.setText("Last Name:");
        label2.setVisible(true);
        textField2.setVisible(true);
        label3.setText("Phone");
        label3.setVisible(true);
        textField3.setVisible(true);
        label4.setText("E-Mail");
        label4.setVisible(true);
        textField4.setVisible(true);
        label5.setText("Money Owned");
        label5.setVisible(true);
        textField5.setVisible(true);
        label6.setText("Money Paid");
        label6.setVisible(true);
        textField6.setVisible(true);
        isInsert = true;
        setSize(300, 200);

    }

    // Validation Method for integer and String
    public boolean validateInteger (String integer) {
        int id;
        try {
            id = Integer.parseInt(integer);
            //catch negative numbers
            if (id > 0) {
                ID = id;
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "The ID must be a positive number starting at 1",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            //show error message if no number entered or letter instead
        } catch (NumberFormatException nfex) {
            JOptionPane.showMessageDialog(null,
                    "This is not a valid consignor number!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    public boolean validateSearchString(String input, String name) {
        //If a string was entered check if it is valid and return the result
        if (input != null && input.length() > 0 && !input.matches("[1-9]")) {
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "You have to enter a "+ name +" or starting character to return something",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        //show error message if no string entered or number instead
        return false;
    }

    public boolean validateString(String input, String name, boolean special, boolean phone) {

        // If we check an email or phone number
        if (special) {
            if (phone) {
                // check if the phone number just contains digits and is 10 characters long
                // http://www.journaldev.com/641/regular-expression-phone-number-validation-in-java
                if (input.matches("\\d{10}")) {
                    return true;
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "You have to enter a valid phone number of 10 numbers in the format 1234561234",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            else if (input != null && input.length() > 0) {
                // Validate Email address. At least to a standart of somebody@something.domain
                String pattern = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"      //http://www.mkyong.com/regular-expressions/how-to-validate-email-address-with-regular-expression/
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"; //represents a valid e-mail pattern
                if (input.matches(pattern)) {
                    return true;
                }
                else {
                    JOptionPane.showMessageDialog(null,
                            "You have to enter an E-Mail in the valid format user@mailserver.domain. Like myMail101@gmail.net",
                            "Error", JOptionPane.ERROR_MESSAGE);
                }

            }
            else {
                JOptionPane.showMessageDialog(null,
                        "You have to enter a "+ name +" to make a new entry into the database",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {
            //If a string was entered check if it is valid and return the result
            if (input != null && input.length() > 0 && !input.matches("[1-9]")) {
                return true;
            }
            else {
                //show error message if no string entered or number instead
                JOptionPane.showMessageDialog(null,
                        "You have to enter a "+ name +" to make a new entry into the database",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        // if it doesn't satisfies the tests, false is returned.
        return false;
    }

    public boolean validateFloat(String input) {
        float id;
        try {
            id = Float.parseFloat(input);
            //catch negative numbers
            if (id >= 0) {
                return true;
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "The number must be a positive number starting at 0",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            //show error message if no number entered or letter instead
        } catch (NumberFormatException nfex) {
            JOptionPane.showMessageDialog(null,
                    "This is not a valid number!",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }

    // Method to open a new Search Table window
    public void openSearch(ResultSet rst) {
        SearchDataModel sdm = new SearchDataModel(rst);
        SearchTable st = new SearchTable(sdm);
    }

    public void clear() {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");
        textField4.setText("");
        textField5.setText("");
        textField6.setText("");
        textField7.setText("");
    }


}
