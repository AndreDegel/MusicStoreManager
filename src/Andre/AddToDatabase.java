package Andre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    private JButton updateConsignorButton1;

    public AddToDatabase() {
        super("Add to Database");
        setContentPane(addPanel);
        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);


        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
}
