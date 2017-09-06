package connection;

public class RoutHelper {

    private String ip;
    private double x,y;
    private int id;


    public RoutHelper(String ip, double x, double y, int id){
        this.ip = ip;
        this.x  = x;
        this.y  = y;
        this.id = id;
    }

    public String toString(){
        return ("IP : " + ip + "\n" +
                "X  : " + x  + "\n" +
                "Y  : " + y  + "\n" +
                "id : " + id);
    }

}
