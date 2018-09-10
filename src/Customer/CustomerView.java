package Customer;

import javax.swing.*;

public class CustomerView extends JPanel {
    private AllRestaurants allRestaurants;
    private singleRestaurant singleRestaurant;
    private Menu menu;
    private allReview allReviews;

    public CustomerView(){
        allRestaurants = new AllRestaurants();
        singleRestaurant = new singleRestaurant();
        menu = new Menu();
        allReviews = new allReview();
        this.add(allRestaurants);
    }
    public void showAllRests(){
        this.removeAll();
        this.add(allRestaurants);
        this.repaint();
        this.revalidate();
    }
    public void showSingleRestaurant(){
        this.removeAll();
        this.add(singleRestaurant);
        this.repaint();
        this.revalidate();
    }
    public void showMenu(){
        this.removeAll();
        this.add(menu);
        this.repaint();
        this.revalidate();
    }

    public Menu getMenu() {
        return menu;
    }

    public Customer.singleRestaurant getSingleRestaurant() {
        return singleRestaurant;
    }

    public AllRestaurants getAllRestaurants() {
        return allRestaurants;
    }

    public allReview getAllReviews() {
        return allReviews;
    }

    public void showAllReviews() {
        this.removeAll();
        this.add(allReviews);
        this.repaint();
        this.revalidate();
    }
}
