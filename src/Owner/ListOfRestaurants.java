package Owner;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseListener;
import java.util.Arrays;
import java.util.Vector;

public class ListOfRestaurants extends JPanel {
    private JTable restaurantLists = new JTable();
    private JTextField allcustomers = new JTextField(50);
    String[] columNames = {"Address", "Restaurant", "score", "Phone Number", "OwnerID"};

    public ListOfRestaurants(){
        this.add(new JLabel("members who left comments"));
        this.add(allcustomers);
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
    }

    public void setAllcustomers(String s){
        allcustomers.setText(s);
    }

    public void addMouseListenerToTable(MouseListener listener){restaurantLists.addMouseListener(listener);}
}
