package Member;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;

public class MemberController {
    public MemberModel model;
    public MemberView view;
    String pk;
    String ownerid;
    String mphone;
    String mname;
    Integer cost;

    public MemberController(MemberView view, MemberModel model,String mname, String mphone){
        this.model = model;
        this.view = view;
        this.pk = null;
        this.ownerid=null;
        this.mphone = mphone;
        this.mname = mname;
        this.cost = 0;
        setUpView();
    }

    private void setUpView() {
        view.getAllRestaurants().addMouseListenerToTable(new MouseAdapter(){
            public void mousePressed(MouseEvent event){
                    JTable table = (JTable) event.getSource();
                    Point p = event.getPoint();
                    int row = table.rowAtPoint(p);
                    if(event.getClickCount()==2){
                        pk = (String) table.getValueAt(row,3);
                        ownerid = table.getValueAt(row,4).toString();
                        try {
                            Object[] objs = model.searchByResPhone(pk);
                            view.getSingleRestaurant().setTableView(objs);
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        view.showSingleRestaurant();
                    }
            }
        });

        view.getAllRestaurants().addListinertoUpdateButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showUpdateEmail();
            }
        });

        view.getSingleRestaurant().addActionListnerToallReviewsButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                try {
                    ArrayList<Object[]> objs = model.searchReview(pk);
                    view.getAllReviews().setTableView(objs);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                view.showAllReview();
            }
        });

        view.getMenu().addListinertoMakebutton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Integer> ids = view.getMenu().getFoodIDs();
                String date = view.getMenu().getDate();
                Integer cusnum=Integer.parseInt(view.getMenu().getNumberOfCus());
                model.insertReservation(ids,date,cusnum,mphone,pk,Integer.parseInt(ownerid),mname);
                cost = model.getPrice();
                model.updateMemberPoint(mphone,cost);
                JOptionPane.showMessageDialog(null,"We have received your reservation! Your total cost is "+ cost);
            }
        });

        view.getSingleRestaurant().addActionListnerTomenuButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ArrayList<Object[]> objs = model.searchFood(pk);
                    view.getMenu().setTableView(objs);
                    view.getMenu().setName(mname);
                    view.getMenu().setPhone(mphone);
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
                view.showMenu();
            }
        });
        view.getMenu().addListinertoBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showSingleRestaurant();
            }
        });
        view.getAllReviews().addListnertoBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showSingleRestaurant();
            }
        });
        view.getUpdateEmail().addListineronUpdateButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nemail = view.getUpdateEmail().getNewEmail();
                model.updateMemberBasicInfo(mphone,nemail);
                JOptionPane.showMessageDialog(null,"Update email successful");
            }
        });

        view.getUpdateEmail().addListinerOnBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showRestaurants();
            }
        });

        view.getWriteReview().addListneronBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showSingleRestaurant();
            }
        });

        view.getWriteReview().addsubmitListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String s = view.getWriteReview().getscore();
                String c = view.getWriteReview().getContent();
                model.insertReview(pk,mphone,c,Float.parseFloat(s));
                JOptionPane.showMessageDialog(null,"Successully submitted your reivew!");
            }
        });
        view.getAllReviews().addListnertoDeleteButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String reviewid = view.getAllReviews().getReviewID();
                model.deleteRevied(Integer.parseInt(reviewid));
                JOptionPane.showMessageDialog(null,"U just deleted a review");
            }
        });
        view.getSingleRestaurant().listenOnBackButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showAllRests();
                cost = Integer.parseInt(model.getPoint(mphone));
                view.getAllRestaurants().setPoints(cost.toString());
            }
        });
        view.getSingleRestaurant().addActiontoWriteReviewButton(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.showWriteReview();
            }
        });
    }
}
