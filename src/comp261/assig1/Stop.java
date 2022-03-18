package comp261.assig1;


// decide the data structure for stops
public class Stop {
    //probably always have these three    
    private GisPoint loc;
    private String name;
    private String id;
    private  String  id_des;
    //Todo: add additional data structures


        
    // Constructor
        public Stop(String id,String name,String  id_des,GisPoint loc) {
        this.name = name;
        this.id = id;
        this.id_des = id_des;
        this.loc = loc;
        }

    public GisPoint getLoc() {
        return loc;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    public String getId_des() {
        return id_des;
    }
    // add getters and setters etc

    public void setLoc(GisPoint loc) {
        this.loc = loc;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(String id) {
        this.id = id;
    }
}
