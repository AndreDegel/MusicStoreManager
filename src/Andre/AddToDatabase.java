package Andre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

/**
 * Created by Andre on 5/2/2015.
 */
public class AddToDatabase extends JFrame{
    private JPanel addPanel;
    private JButton addConsignorButton;
    private JButton addAlbumButton;
    private JButton addToBasementButton;
    private JButton closeButton;
    private JButton updateConsignorButton;

    public static DatabaseController dbc = Main.db;
    public EntryValidation ev;
    public static ResultSet rs;
    public static SearchDataModel older;
    public static SearchDataModel newer;

    public AddToDatabase() {
        super("Add to Database");
        setContentPane(addPanel);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        addConsignorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ev = new EntryValidation();
                ev.entryConsignor();
            }
        });

        addToBasementButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateBasementData();
                BasementManager bm = new BasementManager(older, newer);
            }
        });
    }
    // Method to update the changing data in the table
    // static so it can be called from the Basement Manager
    public static void updateBasementData() {
        rs = dbc.basementDue();
        older = new SearchDataModel(rs);
        rs = dbc.selectAllBasement();
        newer = new SearchDataModel(rs);
    }
}


