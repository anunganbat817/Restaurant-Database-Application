package Owner;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;

public class OwnerController {
    public OwnerModel model;
    public OwnerView ownerView;
    String pk;

    public OwnerController(OwnerModel model, OwnerView ownerView){
        this.model = model;
        this.ownerView = ownerView;
        this.pk = null;
        setView();
    }

    private void setView() {
        ownerView.getLor().addMouseListenerToTable(new MouseAdapter(){
            public void mousePressed(MouseEvent event){
                JTable table = (JTable) event.getSource();
                Point p = event.getPoint();
                int row = table.rowAtPoint(p);
                if(event.getClickCount()==2){
                    pk = (String) table.getValueAt(row,3);
                    try {
                        Object[] objs = model.searchByResPhone(pk);
                        ownerView.getRestaurant().setTableView(objs);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    ownerView.showSingleRestaurant();
                }
            }
        });

        ownerView.getRestaurant().addActionListnerToallReservationsButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Object[]> objs = model.searchReserve(pk);
                    ownerView.getReserViews().setTableView(objs);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                ownerView.showallReservation();
            }
        });
        ownerView.getRestaurant().addActionlistenerToBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerView.showAllRests();
            }
        });
        ownerView.getReserViews().addMouseListenerToTable(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JTable table = (JTable) e.getSource();
                Point p = e.getPoint();
                int row = table.rowAtPoint(p);
                if(e.getClickCount()==2){
                    Integer reservationID = Integer.parseInt(table.getValueAt(row,0).toString());
                    try {
                        ArrayList<Object[]> objs = model.searchFoodByReserveid(reservationID);
                        int cost = model.totalPriceByReserveid(reservationID);
                        ownerView.getSingleReservationView().setTableView(objs);
                        ownerView.setSingleReservationView(reservationID.toString(),cost);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    ownerView.showSingleReservation();
                }
            }
        });

        ownerView.getRestaurant().addActionListnerToallReviewsButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    ArrayList<Object[]> objs = model.searchReview(pk);
                    ownerView.getAllReviews().setTableView(objs);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                ownerView.showAllReviews();
            }
        });

        ownerView.getAllReviews().addListnertoBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerView.showSingleRestaurant();
            }
        });

        ownerView.getReserViews().addListinerToBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerView.showSingleRestaurant();
            }
        });

        ownerView.getSingleReservationView().addListinerToBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerView.showallReservation();
            }
        });

        ownerView.getRestaurant().addActionListnerTomenuButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Object[]> objs = model.searchFood(pk);
                    String popu = model.findpopular(pk);
                    ownerView.getMenu().setTableView(objs,popu);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                ownerView.showMenu();
            }
        });

        ownerView.getMenu().addActionListnerToaddFoodButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerView.showAddFood();
            }
        });

        ownerView.getMenu().addActionListnerToupdateButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerView.showUpdateFoodView();
            }
        });
        ownerView.getMenu().addActionListnerToupdateButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerView.showSingleRestaurant();
            }
        });
        ownerView.getMenu().addListinertoBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ownerView.showSingleRestaurant();
            }
        });
        ownerView.getAddFood().addActionToaddButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String foodName = ownerView.getAddFood().getFoodName();
                Integer foodPrice = ownerView.getAddFood().getFoodPrice();
                model.insertFood(foodPrice,foodName,pk);
                JOptionPane.showMessageDialog(null,"Food added successully!");
            }
        });

        ownerView.getAddFood().addActionTobackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Object[]> objs = model.searchFood(pk);
                    String  popu = model.findpopular(pk);
                    ownerView.getMenu().setTableView(objs,popu);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                ownerView.showMenu();
            }
        });

        ownerView.getUpdateFood().addActionListnertoUpdateButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer id = Integer.parseInt(ownerView.getUpdateFood().getfoodID());
                String name = ownerView.getUpdateFood().getFoodName();
                Integer price = Integer.parseInt(ownerView.getUpdateFood().getfoodPrice());
                model.updateFood(id,price,name);
                JOptionPane.showMessageDialog(null,"Food updated successully!");
            }
        });

        ownerView.getUpdateFood().addListenertoBack(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Object[]> objs = model.searchFood(pk);
                    String popu = model.findpopular(pk);
                    ownerView.getMenu().setTableView(objs,popu);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                ownerView.showMenu();
            }
        });
    }
}
