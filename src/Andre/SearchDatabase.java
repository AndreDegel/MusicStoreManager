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
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        pack();
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
                ev = new EntryValidation();
                ev.setOkButtonText("Search Album");
                ev.entrySearchString("Song");
            }
        });
        byArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ev = new EntryValidation();
                ev.setOkButtonText("Search Album");
                ev.entrySearchString("Artist");
            }
        });
        consignorByNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ev = new EntryValidation();
                ev.setOkButtonText("Search Consignor");
                ev.entrySearchString("Last Name");
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
        basementByConsignorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ev = new EntryValidation();
                ev.basement = true;
                ev.setOkButtonText("Search Album");
                ev.entrySearchID("Consignor");
            }
        });
        basementBySongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ev = new EntryValidation();
                ev.basement = true;
                ev.setOkButtonText("Search Album");
                ev.entrySearchString("Song");
            }
        });
        basementByArtistButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ev = new EntryValidation();
                ev.basement = true;
                ev.setOkButtonText("Search Album");
                ev.entrySearchString("Artist");
            }
        });
    }

    public void openSearch(ResultSet rst) {
        SearchDataModel sdm = new SearchDataModel(rst);
        SearchTable st = new SearchTable(sdm);
    }
}

