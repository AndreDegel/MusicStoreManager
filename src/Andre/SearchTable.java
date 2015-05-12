package Andre;


import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Andre on 5/2/2015.
 */
public class SearchTable extends JFrame{
    private JPanel tablePanel;
    private JTable searchTable;
    private JButton closeButton;
    private JButton deleteButton;

    public void setDeleteButtonVisible(boolean deleteButton) {
        this.deleteButton.setVisible(deleteButton);
    }

    public JTable getSearchTable() {
        return searchTable;
    }

    protected SearchTable(final SearchDataModel sdm) {
        setContentPane(tablePanel);
        pack();
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
        setSize(800, 300);

        //Create a data model and tell our table to use it
        searchTable.setModel(sdm);
        //Grid color default is white, change it so we can see it
        searchTable.setGridColor(Color.BLACK);
        //Also make the columns larger
        searchTable.getColumnModel().getColumn(0).setWidth(400);
        //Quit application when user closes window, otherwise app keeps running
        //Sometimes this is what you want, sometimes it isn't.


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        //TODO: not working yet
        //delete a consignor
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchTable = getSearchTable();
                int currentRow = searchTable.getSelectedRow();
                boolean delete = sdm.deleteRow(currentRow);
                if (delete) {
                    AddToDatabase.updateConsigners();
                }
                else {
                    JOptionPane.showMessageDialog(rootPane, "Error deleting");
                }
        }
        });
    }
}

class SearchDataModel extends AbstractTableModel {

    ResultSet resultSet;
    int numberOfRows;
    int numberOfColumns;

    SearchDataModel(ResultSet rs) {
        this.resultSet = rs;
            //Figure out number of rows in resultset
            try {
                numberOfRows = 0;
                while (resultSet.next()) {
                    numberOfRows++;
                }
                resultSet.beforeFirst(); //reset cursor to the start

                //Figure out number of columns
                numberOfColumns = resultSet.getMetaData().getColumnCount();

            } catch (SQLException sqle) {
                System.out.println("Error setting up data model" + sqle);
            }
    }

    @Override
    public int getRowCount() {
        return numberOfRows;
    }

    @Override
    public int getColumnCount() {
        return numberOfColumns;
    }

    @Override
    //Fetch value for the cell at (row, col).
    //The table will call toString on the object, so it's a good idea
    //to return a string or something that implements toString in a useful way
    public Object getValueAt(int row, int col) {

        try {
            //Move to this row in the result set. Rows are numbered 1, 2, 3...
            resultSet.absolute(row + 1);
            //And get the column at this row. Columns numbered 1, 2, 3...
            Object o = resultSet.getObject(col + 1);
            return o.toString();
        } catch (SQLException sqle) {
            //Display the text of the error message in the cell
            return sqle.toString();
        }
    }

    @Override
    public String getColumnName(int col){
        //Get from ResultSet metadata, which contains the database column names
        try {
            return resultSet.getMetaData().getColumnName(col + 1);
        } catch (SQLException se) {
            System.out.println("Error fetching column names" + se);
            return "?";
        }
    }

    //Delete row, return true if successful, false otherwise
    public boolean deleteRow(int row){
        try {
            resultSet.absolute(row + 1);
            resultSet.deleteRow();
            //Tell table to redraw itself
            fireTableDataChanged();
            return true;
        }catch (SQLException se) {
            System.out.println("Delete row error " + se);
            return false;
        }
    }


}