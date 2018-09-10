package Owner;

/**
 * Created by Serenanana on 17-11-13.
 */


import java.sql.*;
import java.util.ArrayList;
import java.util.Vector;

public class OwnerModel {

    Connection con;

    public OwnerModel(Connection con){
        this.con = con;
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
            Integer ownerid = all.getInt("ownerid");
            result = new Object[]{address,name,score,phone,ownerid};
        }
        sql.close();
        return result;
    }

    public ArrayList<Object[]> searchReserve(String resphone) throws SQLException{
        ArrayList<Object[]> result = new ArrayList<Object[]>();
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM Reservation where Reservation.resphone='"+resphone+"'");
        while (all.next()) {
            String reservationid = all.getString("reservationid");
            String reservationdate = all.getString("reservationdate");
            String numberofcustomer = all.getString("numberofcustomer");
            String customerphone = all.getString("customerphone");
            Object[] temp = new Object[]{reservationid,reservationdate,numberofcustomer,customerphone};
            result.add(temp);
        }
        sql.close();
        return result;
    }

    public String findCustomerByReview(int oid) throws SQLException{
        Statement sql = con.createStatement();
        String result = "";
        ResultSet all = sql.executeQuery("SELECT cname FROM Customer c,Review r, Restaurant res WHERE c.customerphone=r.customerphone AND r.resphone=res.resphone AND res.ownerid="+oid);
        while (all.next()) {
            String cname = all.getString("cname");
            result = result +" "+ cname;
        }
        sql.close();
        return result;
    }

    public ArrayList<Object[]> searchFoodByReserveid(int reserveid) throws SQLException{
        ArrayList<Object[]> result = new ArrayList<Object[]>();
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM Food,Contain where Food.foodid=Contain.foodid AND Contain.reservationid="+reserveid);
        while (all.next()) {
            String foodid = all.getString("foodid");
            String price =  all.getString("price");
            String fname = all.getString("fname");
            Object[] temp = new Object[]{foodid,price,fname};
            result.add(temp);
        }
        sql.close();
        return result;
    }

    public Integer totalPriceByReserveid(int reserveid) throws SQLException{
        Integer result = 0;
        Statement sql = con.createStatement();
        ResultSet all = sql.executeQuery("SELECT * FROM Food,Contain where Food.foodid=Contain.foodid AND Contain.reservationid="+reserveid);
        while (all.next()) {
            int price =  all.getInt("price");
            result += price;
        }
        sql.close();
        return result;
    }


    public void insertFood(int price, String fname, String resphone) {
        System.out.println(" start insertFood ");
        PreparedStatement ps1;
        try {
            ps1 = con.prepareStatement("INSERT INTO Food (price, fname, resphone) VALUES (?, ?, ?)");
            ps1.setInt(1, price);
            ps1.setString(2,fname);
            ps1.setString(3, resphone);
            ps1.executeUpdate();
            con.commit();
            System.out.println("insertFood successfully");
            ps1.close();
        }
        catch (SQLException ex) {
            System.out.println("insertFood Could not get result: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


    public Vector<Object> searchResByOwnerid(Integer ownerid) throws SQLException{
        Vector<Object> data = new Vector<Object>();
        Statement sql2 = con.createStatement();
        ResultSet all = sql2.executeQuery("SELECT * FROM restaurant where ownerid=" + ownerid);
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
        sql2.close();
        return data;
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

    public String findpopular(String resphone) throws SQLException{
        Statement sql = con.createStatement();
        String query =
                "SELECT Food.fname,COUNT(Contain.reservationid) AS Popularity FROM Contain,Food " +
                        "WHERE Contain.reservationid IN (SELECT r.reservationid FROM Reservation r where resphone='"+resphone+"')" +
                        "AND Contain.foodid=Food.foodid " +
                        "GROUP BY Food.foodid,Food.fname " +
                        "ORDER BY popularity DESC";
        ResultSet all = sql.executeQuery(query);
        String result;
        all.next();
        result = "The most popular food is: " + all.getString("fname");
        result = result + ". It has been ordered " + all.getString("Popularity") + " time(s).";
//        Vector<Object> data = getData(all);
        sql.close();
        return result;
    }

    public Vector<Object> getData(ResultSet all)throws SQLException{
        Vector<Object> data = new Vector<Object>();
        ResultSetMetaData md = all.getMetaData();
        int columns = md.getColumnCount();
        all.next();
        Vector<Object> row = new Vector<Object>(columns);
        for (int i = 1; i <= columns; i++)
        {
            row.addElement(all.getObject(i));
        }
        data.addElement(row);

        return data;
    }

    public void updateFood(int foodid, int price, String fname) {
        System.out.println(" start update food ");
        PreparedStatement ps1;
        try {
            ps1 = con.prepareStatement("UPDATE Food SET price = ?, fname = ? WHERE foodid = ?");
            ps1.setInt(3, foodid);
            ps1.setInt(1, price);
            ps1.setString(2, fname);
            ps1.executeUpdate();
            con.commit();
            System.out.println("food info updated successfully");
            ps1.close();
        }
        catch (SQLException ex) {
            System.out.println("updateFood Could not get result: " + ex.getMessage());
            ex.printStackTrace();
        }
    }


//    public void warningPopup(String message) {
//        JOptionPane.showMessageDialog(null, message, "Warning",
//                JOptionPane.WARNING_MESSAGE);
//    }
}


