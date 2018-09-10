package Owner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class Restaurant extends JPanel{
    private JTable singleRestaurant;
    private JButton back = new JButton("Back");
    String[] columNames = {"Restaurant", "Address", "score","Phone Number",  "OwnerID"};
    DefaultTableModel tableModel = new DefaultTableModel(columNames,0);
    private JButton allReservations = new JButton("Show all the Reservations");
    private JButton allReviews = new JButton("Show all the Reviews");
    private JButton menu = new JButton("Menu");

    public Restaurant(){
        singleRestaurant = new JTable(tableModel);
        this.add(allReservations);
        this.add(allReviews);
        this.add(menu);
        this.add(back);
        JScrollPane jScrollPane = new JScrollPane(singleRestaurant);
        this.add(jScrollPane);
    }

    public void setTableView(Object[] objects){
        tableModel.addRow(objects);
    }

    public void addActionlistenerToBackButton(ActionListener listener){back.addActionListener(listener);}
    public void addActionListnerToallReservationsButton (ActionListener listener){allReservations.addActionListener(listener);}

    public void addActionListnerTomenuButton (ActionListener listener){menu.addActionListener(listener);}

    public void addActionListnerToallReviewsButton (ActionListener listener){allReviews.addActionListener(listener);}
}
