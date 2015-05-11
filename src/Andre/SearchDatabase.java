package Andre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Created by Andre on 5/2/2015.
 */
public class SearchDatabase extends JFrame{
    private JPanel searchPanel;
    private JButton allAlbumsButton;
    private JButton allConsignorsButton;
    private JButton allBasementRecordsButton;
    private JButton consignorByNameButton;
    private JButton byIDButton;
    private JButton byConsignorButton;
    private JButton bySongButton;
    private JButton byArtistButton;
    private JButton closeButton;
    private JButton basementByConsignorButton;
    private JButton basementBySongButton;
    private JButton basementByArtistButton;

    public DatabaseController dbc = Main.db;
    public ResultSet rs;
    public EntryValidation ev;



    public SearchDatabase() {
        super("Search Database");
        setContentPane(searchPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });


        allAlbumsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rs = dbc.selectAllAlbums();
                openSearch(rs);

            }
        });
        allConsignorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rs = dbc.selectAllConsignors();
                openSearch(rs);
            }
        });
        allBasementRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                rs = dbc.selectAllBasement();
                openSearch(rs);
            }
        });
        byConsignorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //open input dialog to search consigner id
                ev = new EntryValidation();
                ev.setOkButtonText("Search Album");
                ev.entrySearchID("Consignor");

            }
        });

        bySongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = inputValidationString("Album", "Song");
                rs = dbc.selectAlbumBySong(s);
                SearchDataModel sdm = new SearchDataModel(rs);
                SearchTable st = new SearchTable(sdm);
            }
        });
        byArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = inputValidationString("Album", "Artist");
                rs = dbc.selectAlbumByArtist(s);
                SearchDataModel sdm = new SearchDataModel(rs);
                SearchTable st = new SearchTable(sdm);
            }
        });
        consignorByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = inputValidationString("consignor", "Last Name");
                rs = dbc.selectConsignorByLast(s);

            }
        });
        byIDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ev = new EntryValidation();
                ev.setOkButtonText("Search Consignor");
                ev.entrySearchID("Consignor");
            }
        });
    }

    public String inputValidationString(String name, String by) {
        String s;
        while (true) {
            s = JOptionPane.showInputDialog(
                    null,
                    "What "+ name + " do you want to look for:",
                    "Search Consignor by " +by,
                    JOptionPane.PLAIN_MESSAGE);
            //If a number was returned, try to parse it out of the string and send it to the query
            //if not ignore it
            if (s != null && s.length() > 0) {
                break;
            }
            else {
                JOptionPane.showMessageDialog(null,
                        "You have to enter a "+ name +" or starting character to return something",
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
            //show error message if no number entered or letter instead
        }
        return s;
    }

    public void openSearch(ResultSet rst) {
        SearchDataModel sdm = new SearchDataModel(rst);
        SearchTable st = new SearchTable(sdm);
    }
}

