package mapQuest.routeMatrix;

import pojo.Location;

import java.util.List;

public interface RouteMatrixInterface {

    public double [][] getAllToAll(List<Location> locations);

}
