package Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AllReviews extends JPanel{
    private JTable reviews;
    private JButton back = new JButton("Back");
    private JTextField reviewid = new JTextField(10);
    private JButton delete = new JButton("Delete it");
    String[] colName ={"Review id", "Content", "Review score"};
    DefaultTableModel tableModel = new DefaultTableModel(colName,0){
        @Override
        public boolean isCellEditable(int row, int column){
            return false;
        }
    };
    private JPanel jp = new JPanel();
    public AllReviews(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        jp.add(back);
        reviews = new JTable(tableModel);
        jp.add(new JLabel("If you want to delete a review: "));
        jp.add(reviewid);
        jp.add(delete);
        this.add(jp);
        JScrollPane jScrollPane = new JScrollPane(reviews);
        this.add(jScrollPane);
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

    public String getReviewID(){return reviewid.getText();}
    public void addListnertoDeleteButton(ActionListener listener){delete.addActionListener(listener);}
    public void addListnertoBackButton(ActionListener listener){back.addActionListener(listener);}
}
