package Owner;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllReviews extends JPanel {
    private JTable reviews;
    private JButton back = new JButton("Back");
    String[] colName ={"Review id", "Content", "Review score"};

    DefaultTableModel tableModel = new DefaultTableModel(colName,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };

    public AllReviews(){
        this.add(back);
        reviews = new JTable(tableModel);

    }

    public void setTableView(ArrayList<Object[]> objects){
        if(tableModel.getRowCount() > 0){
            for (int i = tableModel.getRowCount() -1; i>-1; i--)
                tableModel.removeRow(i);
        }
        JScrollPane jScrollPane = new JScrollPane(reviews);
        this.add(jScrollPane);
        for(int i = 0; i<objects.size(); i++){
            tableModel.addRow(objects.get(i));
        }
    }
    public void addListnertoBackButton(ActionListener listener){back.addActionListener(listener);}
}
