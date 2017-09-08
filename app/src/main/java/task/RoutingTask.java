package task;

import android.os.AsyncTask;
import android.util.Log;

/**
 * Created by Joshi on 07.09.2017.
 */

public class RoutingTask extends AsyncTask<String, String , String> {

    //wie mache ich das mit routing und receiveRoutingRequest also wie rufe ich sie jeweils auf? da sie sich gegenseitig aufrufen
    @Override
    protected String doInBackground(String... params) {
        double x = Double.parseDouble(params[0]);
        double y = Double.parseDouble(params[1]);
        double neighbourX, neighbourY;
        double [] distances = new double[4];

        //bei 1 anfangen?
        for(int i=0; i<=distances.length-1 ; i++){

            //Die x und y Werte des Nachbarn von der DB holen
            neighbourX = 1.3;
            neighbourY = 1.6;
            // Nun diese Distanzen berechnen und die am nächsten an dem Punkt zu dem gerouted werden soll.
            distances[i] = computeDistance(x,y,neighbourX,neighbourY);

        }
        int index = compareValues(distances);
        Log.d("Best Index Neighbour: ", " Index: "+ index);

        //// TODO: 14.08.2017 Verbindungsaufbau zu dem Neighbour der an Stelle == Index steht und IP und x,y-Werte übertragen so das dieser weiter routen kann, bzw receive routing request bei ihm aufrufen
        return null;
    }


    private double computeDistance(double x, double y, double neighbourX, double neighbourY) {
        double dis = Math.abs(x - neighbourX) + Math.abs(y - neighbourY);
        return dis;
    }


    private int compareValues(double [] distances){
        int index = 0;
        double temp =  distances[0];
        for(int i= 1 ; i< distances.length; i++){
            if(temp > distances[i]){

                temp = distances[i];
                index = i;
            }
        }
        return index;
    }



}
