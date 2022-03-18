package comp261.assig1;

import java.util.ArrayList;

public class QuadTree {
    public Node root;
    public int deepth;//maximum deepth
    Rectangle rec;// the largest rectangle in the map

    public QuadTree(int deepth,Rectangle rec,Node root){
        this.deepth = deepth;
        this.rec = rec;
        this.root = root;

    }

    public boolean createBranch(Node node,int depth,ArrayList<Stop> stops){
        double x = node.x;
        double y = node.y;

        double left = node.rec.left;
        double right = node.rec.right;
        double top = node.rec.top;
        double bot = node.rec.bot;

        double length = right - left;
        double width = bot - top;

        //create banch untill depth to 0
        if(depth != 0){
            //dive the rectangle into 4 areas(4 quadrants)
            Node tmp1 = new Node(x - length/4,y - width/4,new Rectangle(top,top + width/2,left,left + length/2));//first quadrant
            Node tmp2 = new Node(x + length/4,y - width/4,new Rectangle(top,top + width/2,right - length/2,right));//second quadrant
            Node tmp3 = new Node(x - length/4,y + width/4,new Rectangle(top + width/2,bot,left,left + length/2));//third quadrant
            Node tmp4 = new Node(x + length/4,y + width/4,new Rectangle(top + width/2,bot,right + width/2,right));//fourth quadrant

            depth --;//deep --

            tmp1.load(stops);
            tmp2.load(stops);
            tmp3.load(stops);
            tmp4.load(stops);

            //System.out.println(tmp1);
            //System.out.println("station2 number:" + tmp2.stopNum);
            //System.out.println("station3 number:" + tmp3.stopNum);
            //System.out.println("station4 number:" + tmp4.stopNum);

            //can be inprove
            createBranch(tmp1,depth,stops);
            createBranch(tmp2,depth,stops);
            createBranch(tmp3,depth,stops);
            createBranch(tmp4,depth,stops);


            //add child to this node
            node.LEFT_TOP = tmp1;
            node.RIGHT_TOP = tmp2;
            node.LEFT_BOT = tmp3;
            node.RIGHT_BOT = tmp4;

//            System.out.println("station1 number:" + tmp1.stopNum);
//            System.out.println("station2 number:" + tmp2.stopNum);
//            System.out.println("station3 number:" + tmp3.stopNum);
//            System.out.println("station4 number:" + tmp4.stopNum);
        }
        return true;
    }

    public void insert(Node node){

    }

    public void load(ArrayList<Node> array){
        for(int i = 0;i < array.size();i ++){
               this.insert(array.get(i));
        }
    }

}

class Rectangle {
    public double top;
    public double bot;
    public double left;
    public double right;

    public Rectangle(double top,double bot,double left,double right){
        this.top = top;
        this.bot = bot;
        this.left = left;
        this.right = right;
    }

    //Determine if a node is inside a Rec
    public boolean Iscontained(double x,double y){
        if((x < right) && (x >left) && (y < bot)  && (y > top)){
            System.out.println("true");
            return true;
        }

        return false;
    }
}

class Node {
    private ArrayList<Stop> stations = new ArrayList<>();

    public int deepth = 0;
    public int stopNum = 0; //the number of child;
    public Rectangle rec = new Rectangle(0,0,0,0); //rec where the node locates in

    //location infor
    public double x;
    public double y;

    //child
    public Node LEFT_TOP = null;
    public Node LEFT_BOT = null;
    public Node RIGHT_TOP = null;
    public Node RIGHT_BOT = null;


    public Node(double x,double y,Rectangle rec){
        this.x = x;
        this.y = y;
        this.rec = rec;//rec is obtained by x, y calculation
    }

    //load stops to this rectangle/node
    public ArrayList<Stop> load(ArrayList<Stop> stops){
        for(int i = 0; i < stops.size();i++){
            //check every stop if in the rec,if true -> add
            if(this.rec.Iscontained(stops.get(i).getLoc().lat,stops.get(i).getLoc().lon)){
                this.stations.add(stops.get(i));
                this.stopNum ++;
                System.out.println("add finished");
            }
        }
        return this.stations;
    }
}

