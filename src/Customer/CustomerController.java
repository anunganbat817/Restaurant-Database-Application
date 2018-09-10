package Customer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class CustomerController {

    public CustomerView cusomerView;
    public CustomerModel customerModel;
    String pk;
    String ownerid;

    public CustomerController(CustomerView customerView, CustomerModel cModel){
        this.cusomerView = customerView;
        this.customerModel = cModel;
        this.pk = null;
        this.ownerid=null;
        setUpView();
    }

    private void setUpView() {
        cusomerView.getAllRestaurants().addMouseListenerToTable(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                pk = table.getValueAt(row,3).toString();
                ownerid = table.getValueAt(row,4).toString();
                if(e.getClickCount()==2){
                    try {
                        Object[] objs = customerModel.searchByResPhone(pk);
                        cusomerView.getSingleRestaurant().setTableView(objs);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    cusomerView.showSingleRestaurant();
                }
            }
        });

        cusomerView.getSingleRestaurant().addActionListnerTomenuButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Object[]> objs = customerModel.searchFood(pk);
                    cusomerView.getMenu().setTableView(objs);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                cusomerView.showMenu();
            }
        });

        cusomerView.getSingleRestaurant().listenOnBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusomerView.showAllRests();
            }
        });
        cusomerView.getMenu().addListinertoMakebutton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> ids = cusomerView.getMenu().getFoodIDs();
                String name = cusomerView.getMenu().getCName();
                String date = cusomerView.getMenu().getDate();
                Integer cusnum=Integer.parseInt(cusomerView.getMenu().getNumberOfCus());
                String p = cusomerView.getMenu().getPhone();
                customerModel.insertReservationCustomer(ids,date,cusnum,p,pk,Integer.parseInt(ownerid),name);
                Integer cost = customerModel.getPrice();
                JOptionPane.showMessageDialog(null,"We have received your reservation! Your total cost is "+ cost);
            }
        });

        cusomerView.getMenu().addListinertoBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusomerView.showSingleRestaurant();
            }
        });

        cusomerView.getSingleRestaurant().addActionListnerToallReviewsButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    ArrayList<Object[]> objs = customerModel.searchReview(pk);
                    cusomerView.getAllReviews().setTableView(objs);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                cusomerView.showAllReviews();
            }
        });

        cusomerView.getAllReviews().addListnertoBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cusomerView.showSingleRestaurant();
            }
        });
    }
}
