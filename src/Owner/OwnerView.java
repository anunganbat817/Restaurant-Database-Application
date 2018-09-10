package Owner;

import javax.swing.*;

public class OwnerView extends JPanel {
    private ListOfRestaurants lor;
    private ReservationsView reserViews;
    private Restaurant restaurant;
    private SingleReservationView singleReservationView;
    private AllReviews allReviews;
    private addFoodView addFood;
    private Menu menu;
    private UpdateFood updateFood;

    public OwnerView(){
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        lor = new ListOfRestaurants();
        reserViews = new ReservationsView();
        restaurant = new Restaurant();
        addFood = new addFoodView();
        allReviews = new AllReviews();
        menu = new Menu();
        singleReservationView = new SingleReservationView();
        updateFood = new UpdateFood();
        this.add(lor);
    }

    public void setSingleReservationView(String reservationID, Integer cost){
        singleReservationView.setFields(reservationID,cost.toString());
    }

    public void showUpdateFoodView(){
        this.removeAll();
        this.add(updateFood);
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
        this.add(restaurant);
        this.repaint();
        this.revalidate();
    }

    public void showAllReviews(){
        this.removeAll();
        this.add(allReviews);
        this.repaint();
        this.revalidate();
    }

    public void showallReservation(){
        this.removeAll();
        this.add(reserViews);
        this.repaint();
        this.revalidate();
    }

    public void showAllRests(){
        this.removeAll();
        this.add(lor);
        this.repaint();
        this.revalidate();
    }
    public void showAddFood(){
        this.removeAll();
        this.add(addFood);
        this.repaint();
        this.revalidate();
    }

    public void showSingleReservation(){
        this.removeAll();
        this.add(singleReservationView);
        this.repaint();
        this.revalidate();
    }

    public ListOfRestaurants getLor(){return lor;}

    public ReservationsView getReserViews() {
        return reserViews;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public SingleReservationView getSingleReservationView() {
        return singleReservationView;
    }

    public AllReviews getAllReviews() {
        return allReviews;
    }

    public addFoodView getAddFood() {
        return addFood;
    }

    public Menu getMenu() {
        return menu;
    }

    public UpdateFood getUpdateFood(){return updateFood;}
}
