package Owner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ReservationsView extends JPanel {
    private JTable reservations;
    private JButton back = new JButton("Back");
    String[] colNames = {"ReservationID","Reservation Date", "Number of customers","customer phone"};
    DefaultTableModel tableModel = new DefaultTableModel(colNames,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    private JScrollPane scrollPane;

    public ReservationsView(){
        reservations = new JTable(tableModel);
        scrollPane = new JScrollPane(reservations);
        this.add(back);
        this.add(scrollPane);
    }

    public void setTableView(ArrayList<Object[]> objects){
        if(tableModel.getRowCount() > 0){
            for (int i = tableModel.getRowCount() -1; i>-1; i--)
                tableModel.removeRow(i);
        }
        for(int i = 0; i<objects.size(); i++){
            tableModel.addRow(objects.get(i));
        }
    }
    public void addListinerToBackButton(ActionListener listener){back.addActionListener(listener);}
    public void addMouseListenerToTable(MouseListener listener){reservations.addMouseListener(listener);}

}
