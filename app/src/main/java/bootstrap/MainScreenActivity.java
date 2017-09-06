package bootstrap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.somaro.loginregister.R;

import model.Node;


public class MainScreenActivity extends Activity {

    Button btnViewIPs;
    Button btnInsertOwnIP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);

        // Buttons
        btnViewIPs = (Button) findViewById(R.id.btnViewIps);
        //btnInsertOwnIP = (Button) findViewById(R.id.btnInsertIP);

        btnViewIPs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Node.class);
                startActivity(i);
            }
        });

        /*btnInsertOwnIP.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i = new Intent (getApplicationContext(), InsertOwnIPActivity.class);
                startActivity(i);
                btnInsertOwnIP.setVisibility(View.INVISIBLE);
            }

        });*/
    }

}
