package Customer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Vector;

public class AllRestaurants extends JPanel {
    private JTable restaurantLists = new JTable();
    String[] columNames = {"Address", "Restaurant", "score", "Phone Number", "Ownerid"};

    public AllRestaurants(){}

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
    }

    public void addMouseListenerToTable(MouseListener listener){restaurantLists.addMouseListener(listener);}
}
