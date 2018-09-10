package Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Vector;

public class AllRestaurants extends JPanel{
    private JTable restaurantLists = new JTable();
    String[] columNames = {"Address", "Restaurant", "score", "Phone Number", "Ownerid"};
    private JLabel points = new JLabel();
    private JButton update = new JButton("Update email");

    public AllRestaurants(){
        this.add(update);
    }

    public void setTableView(Vector objects){
        Vector col = new Vector(Arrays.asList(columNames));
        DefaultTableModel model = new DefaultTableModel(objects, col){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        restaurantLists = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(restaurantLists);
        this.add(scrollPane);
        this.add(points);
    }

    public void setPoints(String p){
        points.setText("You have " +p+ " points");
    }

    public void addListinertoUpdateButton(ActionListener listener){
        update.addActionListener(listener);
    }

    public void addMouseListenerToTable(MouseListener listener){restaurantLists.addMouseListener(listener);}
}
