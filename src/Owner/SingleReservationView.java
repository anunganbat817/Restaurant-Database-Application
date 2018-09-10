package Owner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SingleReservationView extends JPanel {
    private JTable listOfFood;
    private JButton back = new JButton("Back");
    private JTextField resid = new JTextField(10);
    private JTextField cost = new JTextField(10);
    String[] colName = {"Food ID","Food Price"};
    DefaultTableModel tableModel = new DefaultTableModel(colName,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    public SingleReservationView(){
        listOfFood = new JTable(tableModel);
        this.add(new JLabel("reservation ID: "));
        this.add(resid);
        this.add(new JLabel("total cost: "));
        this.add(cost);
        this.add(back);
    }

    public void setTableView(ArrayList<Object[]> objects){
        if(tableModel.getRowCount() > 0){
            for (int i = tableModel.getRowCount() -1; i>-1; i--)
                tableModel.removeRow(i);
        }
        JScrollPane jScrollPane = new JScrollPane(listOfFood);
        this.add(jScrollPane);
        for(int i = 0; i<objects.size(); i++){
            tableModel.addRow(objects.get(i));
        }
    }

    public void setFields(String id, String rescost){
        cost.setText(rescost);
        resid.setText(id);
    }
    public void addListinerToBackButton(ActionListener listener){back.addActionListener(listener);}

}
