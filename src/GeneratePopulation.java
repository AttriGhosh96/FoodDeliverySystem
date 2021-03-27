import mapQuest.routeMatrix.RouteMatrix;
import mapQuest.routeMatrix.RouteMatrixInterface;
import pojo.Customer;
import pojo.Location;
import pojo.Restaurant;
import receivedData.Order;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class GeneratePopulation {

    private List<Order> orders;
    private double [][] customerCustomerDistanceMatrix;
    private double [][] customerRestaurantDistanceMatrix;
    private double [][] restaurantRestaurantDistanceMatrix;
    private RouteMatrixInterface routeMatrix;

    public GeneratePopulation(List<Order> orders, double[][] customerCustomerDistanceMatrix, double[][] customerRestaurantDistanceMatrix, double[][] restaurantRestaurantDistanceMatrix) {
        //todo add routematrix
        this.orders = orders;

        this.customerCustomerDistanceMatrix = customerCustomerDistanceMatrix;
        this.customerRestaurantDistanceMatrix = customerRestaurantDistanceMatrix;
        this.restaurantRestaurantDistanceMatrix = restaurantRestaurantDistanceMatrix;
    }

    public GeneratePopulation() {
        initialisation();
    }

    public void initialisation() {
        POCInitialization pocObj = new POCInitialization();
        orders = pocObj.getOrders();



        //customerCustomerDistanceMatrix = new double[noOfUniqueCustomers][noOfUniqueCustomers];
        // when API will be called it will be called with a list of locations

        //customerCustomerDistanceMatrix =new double [][] {{0, 1.115, 1.255, 1.755},
//                                                        {0.859, 0, 1.622, 1.001},
//                                                        {1.519, 1.903, 0, 2.395},
//                                                        {1.169, 0.727, 2.21, 0}};
//
//        //restaurantRestaurantDistanceMatrix = new double[][]{{0, 2.874, 3.212},
//                                                            {2.809, 0, 3.543},
//                                                            {2.149, 2.694, 0}};

        List<Location> customerThenRestaurants =  new ArrayList<>();
        routeMatrix = new RouteMatrix();

        Set<Customer> allUniqueCustomer = getUniqueCustomers();
        Set<Restaurant> allUniqueRestaurant = getUniqueRestaurants();

        //matrix
        int noOfUniqueCustomers = allUniqueCustomer.size();
        int noOfUniqueRestaurants = allUniqueRestaurant.size();

        customerThenRestaurants.addAll(allUniqueCustomer.stream().map(customer->customer.getCustomerLocation()).collect(Collectors.toList()));
        customerThenRestaurants.addAll(allUniqueRestaurant.stream().map(restaurant -> restaurant.getRestaurantLocation()).collect(Collectors.toList()));

        double [][] allToAll = routeMatrix.getAllToAll(customerThenRestaurants) ;

        customerCustomerDistanceMatrix = Utility.extractSubArray(allToAll, 0, noOfUniqueCustomers-1, 0, noOfUniqueCustomers-1);
        restaurantRestaurantDistanceMatrix = Utility.extractSubArray(allToAll, noOfUniqueCustomers, (noOfUniqueCustomers + noOfUniqueRestaurants)-1, noOfUniqueCustomers, (noOfUniqueCustomers + noOfUniqueRestaurants)-1 );
        customerRestaurantDistanceMatrix = Utility.extractSubArray(allToAll, 0, noOfUniqueCustomers-1, noOfUniqueCustomers, (noOfUniqueCustomers + noOfUniqueRestaurants)-1);

        System.out.println("Customer-Customer");
        display(customerCustomerDistanceMatrix);
        System.out.println("Restaurant-Restaurant");
        display(restaurantRestaurantDistanceMatrix);
        System.out.println("Customer-Restaurant");
        display(customerRestaurantDistanceMatrix);


    }


    public Set<Customer> getUniqueCustomers()
    {
        Set<Customer> setOfCustomer = new HashSet<Customer>();

        for( Order order : orders)
        {
            setOfCustomer.add(order.getCustomer());
        }
        return setOfCustomer;
    }

    public Set<Restaurant> getUniqueRestaurants()
    {
        Set<Restaurant> setOfRestaurant = new HashSet<Restaurant>();

        for( Order order : orders)
        {
            setOfRestaurant.add(order.getRestaurant());
        }
        return setOfRestaurant;
    }

    public void display(double [][] array)
    {
        int i,j;
        for(i=0; i<array.length; i++)
        {
            for(j=0; j<array[i].length; j++)
            {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    //creation of initial population

    public static void main(String args[])
    {
        GeneratePopulation obj = new GeneratePopulation();
    }
}
