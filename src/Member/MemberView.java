package Member;

import javax.swing.*;

public class MemberView extends JPanel{

    private AllRestaurants allRestaurants;
    private updateEmail updateEmail;
    private SingleRestaurant singleRestaurant;
    private Menu menu;
    private AllReviews allReviews;
    private WriteReview writeReview;

    public MemberView(){
        menu = new Menu();
        writeReview = new WriteReview();
        allReviews = new AllReviews();
        singleRestaurant = new SingleRestaurant();
        updateEmail = new updateEmail();
        allRestaurants = new AllRestaurants();
        this.add(allRestaurants);
    }

    public void showAllReview() {
        this.removeAll();
        this.add(allReviews);
        this.repaint();
        this.revalidate();
    }
    public void showWriteReview(){
        this.removeAll();
        this.add(writeReview);
        this.repaint();
        this.revalidate();
    }

    public void showMenu(){
        this.removeAll();
        this.add(menu);
        this.repaint();
        this.revalidate();
    }

    public void showSingleRestaurant(){
        this.removeAll();
        this.add(singleRestaurant);
        this.repaint();
        this.revalidate();
    }

    public void showRestaurants(){
        this.removeAll();
        this.add(allRestaurants);
        this.repaint();
        this.revalidate();
    }

    public void showUpdateEmail(){
        this.removeAll();
        this.add(updateEmail);
        this.repaint();
        this.revalidate();
    }

    public void showAllRests(){
        this.removeAll();
        this.add(allRestaurants);
        this.repaint();
        this.revalidate();
    }
    public WriteReview getWriteReview() {
        return writeReview;
    }

    public AllRestaurants getAllRestaurants() {
        return allRestaurants;
    }

    public SingleRestaurant getSingleRestaurant() {
        return singleRestaurant;
    }

    public updateEmail getUpdateEmail(){
        return updateEmail;
    }

    public Menu getMenu() {
        return menu;
    }

    public AllReviews getAllReviews() {
        return allReviews;
    }
}
