package activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.example.somaro.loginregister.R;

import org.json.JSONException;

import task.HashXTask;
import task.HashYTask;

/**
 * Created by Joshi on 07.09.2017.
 */

public class NodeActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.)
    }

    private void startHashX() throws JSONException {
        new HashXTask(new HashXTask.AsyncResponse(){
            @Override
            public void processFinish(double d){
                Log.d("HashX in processFinish ", "d"+d);
            }
        }).execute();
    }
}
