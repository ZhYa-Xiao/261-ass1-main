package comp261.assig1;
import java.util.ArrayList;
import java.util.List;

//Elements in file: route_id,service_id,trip_id,trip_headsign,direction_id,block_id,shape_id,wheelchair_accessible,bikes_allowed,etm_id

public class Trip {
    private String stop_pattern_id;
    private String stopId;
    private int stopSequence;
    private String timepoint;
    private ArrayList<String> stops;
    private List<Stop> stopsOnTrip;

    public Trip(String stop_pattern_id, String tripId, int stopSequence, String timepoint) {
        this.stop_pattern_id = stop_pattern_id;
        this.stopId = tripId;
        this.stopSequence = stopSequence;
        this.timepoint = timepoint;
    }

    public Trip(String stop_pattern_id , List<Stop> stopsOnTrip) {
        this.stop_pattern_id = stop_pattern_id;
        this.stopsOnTrip = stopsOnTrip;
    }

    public  String getStop_pattern_id(){
    return stop_pattern_id;
}

    public String getStopId() {
        return stopId;
    }

    public ArrayList<String> getStops() {
        return stops;
    }

    public int getStopSequence() {
        return stopSequence;
    }

    public String getTimepoint() {
        return timepoint;
    }

    public void setStops(ArrayList<String> stops) {
        this.stops = stops;
    }

}
