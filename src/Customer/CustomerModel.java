package Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class CustomerModel {
    Connection con;
    Integer reservid = 0;
    public CustomerModel(Connection con){
        this.con = con;
    }

    public Vector<Object> allRestaurant() throws SQLException {
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM allrestaurants");
        Vector<Object> data = getData(all);
        sql.close();
        return data;
    }

    public Object[] searchByResPhone(String resphone) throws SQLException{
        Object[] result = new Object[]{};
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM Restaurant where Restaurant.resphone='"+resphone+"'");
        while (all.next()) {
            String address = all.getString("address");
            String name = all.getString("rname");
            Integer score = all.getInt("resscore");
            String phone = all.getString("resPhone");
            String ownerid = all.getString("ownerid");
            result = new Object[]{address,name,score,phone,ownerid};
        }
        sql.close();
        return result;
    }

    public ArrayList<Object[]> searchFood(String resphone) throws SQLException{
        ArrayList<Object[]> result = new ArrayList<Object[]>();
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM Food where Food.resphone='"+resphone+"'");
        while (all.next()) {
            String price = all.getString("price");
            String foodid = all.getString("foodid");
            String fname = all.getString("fname");
            Object[] temp = new Object[]{ foodid, fname,price};
            result.add(temp);
        }
        sql.close();
        return result;
    }

    public Vector<Object> getData(ResultSet all)throws SQLException{
        Vector<Object> data = new Vector<Object>();
        ResultSetMetaData md = all.getMetaData();
        int columns = md.getColumnCount();
        while (all.next()) {
            Vector<Object> row = new Vector<Object>(columns);
            for (int i = 1; i <= columns; i++)
            {
                row.addElement(all.getObject(i));
            }
            data.addElement(row);
        }
        return data;
    }

    public void insertReservationCustomer(ArrayList<Integer> foodid, String date, int numcustomer, String cphone, String rphone, int ownerid, String cname) {
        System.out.println(" start insertReservation for customer ");
        if(!checkCustomerExist(cphone)){
            insertCustomer(cphone,cname);
        }
        try {
            PreparedStatement ps1 = con.prepareStatement("INSERT INTO Reservation (reservationdate,numberofcustomer,customerphone,resphone,cusname,ownerid) VALUES (?,?,?,?,?,?)");
            ps1.setString(1, date);
            ps1.setInt(2,numcustomer);
            ps1.setString(3, cphone);
            ps1.setString(4, rphone);
            ps1.setString(5,cname);
            ps1.setInt(6, ownerid);
            ps1.executeUpdate();
            con.commit();
            ps1.close();
        }
        catch (SQLException ex) {
            System.out.println("insertReservation Could not get result: " + ex.getMessage());
            ex.printStackTrace();
        }
        try {
            Statement sql = con.createStatement();
            ResultSet s = sql.executeQuery("SELECT LAST_INSERT_ID() AS lastid");
            if(s.next())
                reservid = Integer.parseInt(s.getObject(1).toString());
            System.out.println("insertReservation successfully");
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        insertReservationInContain(foodid,reservid);
        //update Contain table to insert its foodid and reservationid
    }

    public void insertReservationInContain(ArrayList<Integer> foodids,int reservationid){
        for(int i=0;i<foodids.size();i++){
            System.out.println(" start insert a food in a contain ");
            PreparedStatement ps1;
            try {
                ps1 = con.prepareStatement("INSERT INTO Contain(foodid,reservationid) VALUES (?, ?)");
                ps1.setInt(1, foodids.get(i));
                ps1.setInt(2,reservationid);
                ps1.executeUpdate();
                con.commit();
                System.out.println("insertContainInstance successfully");
                ps1.close();
            }
            catch (SQLException ex) {
                System.out.println("insertContainInstance Could not get result: " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }

    public boolean checkCustomerExist(String cphone){
        try {
            Statement sql = con.createStatement();
            ResultSet all = sql.executeQuery("SELECT * FROM Customer where Customer.customerphone='"+cphone+"'");
            return all.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void insertCustomer(String cphone, String cname) {
        System.out.println(" start insertCustomer ");

        PreparedStatement ps1;

        try {
            ps1 = con.prepareStatement("INSERT INTO Customer (customerphone,cname) VALUES (?, ?)");

            ps1.setString(1, cphone);
            ps1.setString(2, cname);

            ps1.executeUpdate();
            con.commit();
            System.out.println("insertCustomer successfully");
            ps1.close();
        }
        catch (SQLException ex) {
            System.out.println("insertCustomer Could not get result: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
    //get cost of reservation once "make reservation" clicked
    public int getPrice(){
        Integer result = 0;
        Statement sql = null;
        try {
            sql = con.createStatement();
            ResultSet all = sql.executeQuery("SELECT * FROM Food,Contain where Food.foodid=Contain.foodid AND Contain.reservationid="+reservid);
            while (all.next()) {
                int price =  all.getInt("price");
                result += price;
            }
            sql.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<Object[]> searchReview(String resphone) throws SQLException{
        ArrayList<Object[]> result = new ArrayList<Object[]>();
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM Review where Review.resphone='"+resphone+"'");
        while (all.next()) {
            String reviewid = all.getString("reviewid");
            String content = all.getString("content");
            String reviewscore = all.getString("reviewscore");
            Object[] temp = new Object[]{reviewid, content, reviewscore};
            result.add(temp);
        }
        sql.close();
        return result;
    }

}
