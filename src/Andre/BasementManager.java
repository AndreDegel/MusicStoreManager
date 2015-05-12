package Andre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andre on 5/11/2015.
 */
public class BasementManager extends JFrame{
    private JPanel basementPanel;
    private JButton transferButton;
    private JTable oldRecordTable;
    private JTable basementTable;
    private JButton closeButton;

    protected BasementManager(SearchDataModel old, SearchDataModel toBasement) {
        setContentPane(basementPanel);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(800, 500);

        //Create a data model and tell our table to use it
        oldRecordTable.setModel(old);
        basementTable.setModel(toBasement);
        //Grid color default is white, change it so we can see it
        oldRecordTable.setGridColor(Color.BLACK);
        basementTable.setGridColor(Color.BLACK);
        //Also make the columns larger
        oldRecordTable.getColumnModel().getColumn(0).setWidth(400);
        basementTable.getColumnModel().getColumn(0).setWidth(400);


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        transferButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // invoke the method to transfer all records to the basement
                Main.db.toBasement();
                // Update the data in the resultset and the model and
                // display the updated table
                AddToDatabase.updateBasementData();
                oldRecordTable.setModel(AddToDatabase.older);
                basementTable.setModel(AddToDatabase.newer);
            }
        });
    }


}
