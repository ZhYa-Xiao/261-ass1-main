package comp261.assig1;

// The edge class represents an edge in the graph.

public class Edge {
    private Stop fromStop;
    private Stop toStop;
    private String tripId;

    //todo: add a constructor

    public Edge(Stop fromStop, Stop toStop,String tripId) {
        this.fromStop = fromStop;
        this.toStop = toStop;
        this.tripId = tripId;
    }


    //todo: add getters and setters


    public Stop getFromStop() {
        return fromStop;
    }

    public void setFromStop(Stop fromStop) {
        this.fromStop = fromStop;
    }

    public Stop getToStop() {
        return toStop;
    }

    public void setToStop(Stop toStop) {
        this.toStop = toStop;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }
}
