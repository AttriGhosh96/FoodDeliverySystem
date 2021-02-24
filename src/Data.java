public class Data {

    // Variables defined for each object
    double distance_in_km; //stores the distance between each set of restaurant & customer / restaurant & restaurant / customer &customer
    int time_in_mins; //stores time to travel between each set of restaurant & customer / restaurant & restaurant / customer &customer
    boolean acceptvisit; //whether a visit exists between each set of restaurant & customer / restaurant & restaurant / customer &customer


    //default constructor
    public Data()
    {
        distance_in_km = 0.0;
        time_in_mins = 0;
        acceptvisit = false;
    }
    //parametrized constructor
    public Data(double distance, int time, boolean accept)
    {
        distance_in_km = distance;
        time_in_mins = time;
        acceptvisit = accept;
    }

    //initialization function
    public static void initialization()
    {
        //Object array for Restaurant & Customer
        Data RestaurantCustomer[][] = new Data[3][4];
        Data RestaurantRestaurant[][] = new Data[3][3];
        Data CustomerCustomer[][] = new Data[4][4];
        int i, j;
        // Restaurant & Customer Array
        System.out.println("Restaurant(Rows) & Customer(Columns) Array");
        for(i=0; i<3; i++) {
            for (j = 0; j < 4; j++) {
                RestaurantCustomer[i][j] = new Data(10, 40, false);
                RestaurantCustomer[i][j].display();
            }
            System.out.println();
        }
        System.out.println();

        // Restaurant & Restaurant Array
        System.out.println("Restaurant(Rows) & Restaurant(Columns) Array");
        for(i=0; i<3; i++) {
            for (j = 0; j < 3; j++) {
                RestaurantRestaurant[i][j] = new Data(5, 15, false);
                RestaurantRestaurant[i][j].display();
            }
            System.out.println();
        }
        System.out.println();

        // Customer & Customer Array
        System.out.println("Customer(Rows) & Customer(Columns) Array");
        for(i=0; i<3; i++) {
            for (j = 0; j < 3; j++) {
                CustomerCustomer[i][j] = new Data(7, 20, false);
                CustomerCustomer[i][j].display();
            }
            System.out.println();
        }
        System.out.println();
    }

    //display function
    public  void display()
    {
        System.out.print(distance_in_km + "," + time_in_mins + "," + acceptvisit +"\t");
    }

    //main function
    public static void main(String args[])
    {
        //Data object = new Data();
        initialization();

    }
}
