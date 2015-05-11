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
    private JLabel Label2;
    private JLabel Label3;
    private JLabel Label4;
    private JLabel Label5;
    private JLabel Label6;
    private JLabel Label7;
    private JLabel Label8;
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
                            if (validateString(s, "Album", "Song")) {
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
                            if (validateString(s, "Album", "Artist")) {
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
                        if (validateString(s, "consignor", "Last Name")) {
                            rs = dbc.selectConsignorByLast(s);
                            openSearch(rs);
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

    public boolean validateString(String input, String name, String by) {
        //If a number was returned, try to parse it out of the string and send it to the query
        //if not ignore it
        if (input != null && input.length() > 0 && !input.matches("[1-9]")) {
            return true;
        }
        else {
            JOptionPane.showMessageDialog(null,
                    "You have to enter a "+ name +" or starting character to return something",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
        //show error message if no number entered or letter instead
        return false;
    }

    // Method to open a new Search Table window
    public void openSearch(ResultSet rst) {
        SearchDataModel sdm = new SearchDataModel(rst);
        SearchTable st = new SearchTable(sdm);
    }


}
