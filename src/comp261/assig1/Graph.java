package comp261.assig1;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Graph {

    //Todo add your data structures here
    private ArrayList<Stop> stops;
    private ArrayList<Trip> trips;
    private ArrayList<Edge> edges = new ArrayList<Edge>();
    private Map< String , Stop > stopMap;
    private Map<String, List<Stop>> tripMap = new HashMap<String, List<Stop>>();
    private double[][] adjacencyMatrix;
// constructor post parsing
    public Graph(ArrayList<Stop> stopsList, ArrayList<Trip> tripsList) {
        this.stops = stopsList;
        this.trips = tripsList;
        buildStopList();
    }

    // constructor with parsing
    public Graph(File stopFile, File tripFile) {
        //Todo: instantiate your data structures here

        //Then you could parse them using the Parser
        this.stops = Parser.parseStops(stopFile);
        this.trips = Parser.parseTrips(tripFile);

        buildStopList();
        buildEdge();
        buildTripData();
    }

    // buildStoplist from other data structures
    private void buildStopList() {
        adjacencyMatrix = new double[stops.size()][stops.size()];
        stopMap = new HashMap<String, Stop>();
        for (Stop stop: stops) {
            String a = stop.getId();
            stopMap.put(stop.getId(),stop);
        }
        for (int i = 0; i < stops.size(); i++) {
            for (int j = 0; j < stops.size(); j++) {
                adjacencyMatrix[i][j] = 1;
            }
        }
        /*stopMap = new HashMap<String , Stop>();
        for (Stop stop: stops) {
            stopMap.put(stop.getId(),stop);
        }*/
       // Todo: you could use this sort of method to create additional data structures

    }

    // buildRouteData into stops
    private void buildTripData(){
        // Todo: this could be used for trips
        int trips_len = trips.size();
        List<Stop> stopsOnTrip = new ArrayList<Stop>();
        stopsOnTrip.add(stopMap.get(trips.get(0).getStopId()));
        for (int i = 1; i < trips_len ; i++) {
            int a = trips.get(i).getStopSequence();
            int b = trips.get(i - 1).getStopSequence();
            if(trips.get(i).getStopSequence() > trips.get(i-1).getStopSequence()){
                if( stopMap.get(trips.get(i).getStopId()) != null){
                    stopsOnTrip.add(stopMap.get(trips.get(i).getStopId()));
                }
            }else{
                tripMap.put(trips.get(i-1).getStop_pattern_id(),stopsOnTrip);
                stopsOnTrip = new ArrayList<Stop>();
                if( stopMap.get(trips.get(i).getStopId()) != null){
                    stopsOnTrip.add(stopMap.get(trips.get(i).getStopId()));
                }
            }
        }
    }
    public void buildEdge(){
        int trips_l = trips.size();
        for (int i = 1; i < trips_l ; i++) {
            if(trips.get(i).getStopSequence() > trips.get(i-1).getStopSequence()){
               Stop to = stopMap.get(trips.get(i-1).getStopId());
               Stop from = stopMap.get(trips.get(i).getStopId());
               if(to != null && from != null ){
                   Edge edge = new Edge(to,from,trips.get(i).getStop_pattern_id());
                   edges.add(edge);
               }
            }
        }
    }
    public void buildAdjacencyMatrix() {

    }

    //getter for the adjacency matrix
    public double[][] getAdjacencyMatrix() {
        return adjacencyMatrix;
    }
    // Todo: getters and setters


    public Map<String, Stop> getStopMap() {
        return stopMap;
    }

    public Map<String, List<Stop>> getTripMap() {
        return tripMap;
    }

    public ArrayList<Stop> getStops() {
        return stops;
    }

    public ArrayList<Trip> getTrips() {
        return trips;
    }

    public ArrayList<Edge> getEdges() {
        return edges;
    }
}
