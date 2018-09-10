package Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;

public class SingleRestaurant extends JPanel {
    private JTable singleRestaurant;
    String[] columNames = {"Restaurant", "Address", "Phone Number", "score"};
    DefaultTableModel tableModel = new DefaultTableModel(columNames,0);
    private JButton allReviews = new JButton("Show all the Reviews");
    private JButton menu = new JButton("Menu");
    private JButton writeReview = new JButton("Write a review");
    private JButton back = new JButton("Go back");
    private JPanel jPanel = new JPanel();

    public SingleRestaurant(){
        singleRestaurant = new JTable(tableModel);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        jPanel.add(back);
        jPanel.add(writeReview);
        jPanel.add(allReviews);
        jPanel.add(menu);
        this.add(jPanel);
        JScrollPane jScrollPane = new JScrollPane(singleRestaurant);
        this.add(jScrollPane);
    }

    public void setTableView(Object[] objects){
        if(tableModel.getRowCount() > 0){
            for (int i = tableModel.getRowCount() -1; i>-1; i--)
                tableModel.removeRow(i);
        }
        tableModel.addRow(objects);
    }

    public void listenOnBackButton(ActionListener listener){back.addActionListener(listener);}
    public void addActiontoWriteReviewButton(ActionListener listener){
        writeReview.addActionListener(listener);
    }

    public void addActionListnerTomenuButton (ActionListener listener){menu.addActionListener(listener);}

    public void addActionListnerToallReviewsButton (ActionListener listener){allReviews.addActionListener(listener);}

}
