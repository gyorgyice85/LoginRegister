package task;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;

/**
 * Created by Joshi on 07.09.2017.
 */

public class HashYTask extends AsyncTask<String, String , String> {

    private static final long DIVIDER= 2552552552l;
    private double hashY;
    interface AsyncResponse {
        void processFinish(double result);
    }

    private HashYTask.AsyncResponse delegate = null;

    HashYTask(HashYTask.AsyncResponse delegate)throws JSONException {
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute(){
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... params) {

        String ip = params[0];

        String hash2 = umkehren(ip);
        double y = hash2.hashCode();
        if(y < 0){
            y = y/(-DIVIDER);
            hashY = y;
        }else{
            y = y/DIVIDER;
            hashY=y;
        }
        return null;
    }

    private static String umkehren( String ip )
    {
        String umgekehrt = new String();

        for ( int j = ip.length()-1; j >= 0; j-- )
            umgekehrt += ip.charAt(j);

        return umgekehrt;
    }


    @Override
    protected void onPostExecute(String s){
        double d = hashY;
        delegate.processFinish(d);
        Log.d("HashYValue","d: "+d);
    }
}
