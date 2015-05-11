package Andre;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Andre on 5/2/2015.
 */
public class mainMenu extends JFrame{
    private JPanel rootPanel;
    private JButton addToDatabaseButton;
    private JButton searchDatabaseButton;
    private JButton quitButton;

    public mainMenu() {
        super("Music Store Main Menu");
        setContentPane(rootPanel);
        pack();
        setSize(300, 200);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        addToDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddToDatabase add = new AddToDatabase();
            }
        });


        searchDatabaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SearchDatabase search = new SearchDatabase();
            }
        });
    }


}
