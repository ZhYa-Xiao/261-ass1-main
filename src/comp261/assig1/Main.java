package comp261.assig1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main extends Application {

    // save the graph datastructure here as it is easy to get to using Main.graph
    public static Graph graph;

    @Override
    public void start(Stage primaryStage) throws Exception{

        // load the strings for language support
        // currently en_NZ and mi_NZ are supported
        Locale locale = new Locale("en", "NZ");
        ResourceBundle bundle = ResourceBundle.getBundle("comp261/assig1/resources/strings", locale);

        // load the fxml file to set up the GUI
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MapView.fxml"),bundle);
        Parent root = loader.load();

        primaryStage.setTitle(bundle.getString("title")); // set the title of the window from the bundle
        primaryStage.setScene(new Scene(root, 800, 700));
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> {
            System.exit(0);
        });

        // So you do not have to load the files every time you are testing make
        // PATH_TO_DATA_FOLDER something like "D:/git/comp261/2022/code/Assignment 1 solution/data/"
        graph = new Graph(  new File("./data/stops.txt"),
                            new File("./data/stop_patterns.txt"));

        ArrayList<Stop> stops = graph.getStops();//get arraylist

        //get the edge of the biggeest recatangle
        double minx = stops.get(0).getLoc().lat;
        double miny = stops.get(0).getLoc().lon;
        double maxx = stops.get(0).getLoc().lat;
        double maxy = stops.get(0).getLoc().lon;

        //lat = x,lon = y
        for(int i = 0;i < stops.size(); i ++){
            Stop stop = stops.get(i);
            if(stop.getLoc().lat < minx){
                minx = stop.getLoc().lat;
            }

            if(stop.getLoc().lat > maxx){
                maxx = stop.getLoc().lat;
            }

            if(stop.getLoc().lon < miny){
                miny = stop.getLoc().lon;
            }

            if(stop.getLoc().lon > maxy){
                maxy = stop.getLoc().lon;
            }
        }

        System.out.println("max x :" + maxx);
        System.out.println("min x :" + minx);
        System.out.println("max y :" + maxy);
        System.out.println("min y :" + miny);



        //build quadtree
        Rectangle max_rec = new Rectangle(miny,maxy,minx,maxx);
        Node quad_root = new Node((maxx -minx)/2,(maxy-miny)/2,max_rec);
        QuadTree quadTree = new QuadTree(8,max_rec,quad_root);

        ////according to the edge of the biggst rectangule to divede the small rectangle
        quadTree.createBranch(quad_root,8,stops);


        //force the GraphController to draw the graph after loading
        ((GraphController)loader.getController()).drawGraph();

    }


    public static void main(String[] args) {
        launch(args);
    }

}
