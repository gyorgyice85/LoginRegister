package connection;

public class RoutHelper {

    protected String ip;
    protected double x,y;
    protected int id;


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

    public String getIP(){
        return ip;
    }

    public Double getX(){
        return x;
    }

    public Double getY(){
        return y;
    }

    public Integer getID(){
        return id;
    }
}
