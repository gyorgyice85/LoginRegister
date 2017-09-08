package connection;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by Cedric on 06.09.2017.
 */

public class FileTransferActivity extends Activity{

    Socket socket = null;
    Client client = new Client();
    File file = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new FileTransferThread(socket, file).execute();
    }

    public FileTransferActivity(Socket socket, File file){
        this.socket = socket;
        this.file   = file;
    }

    class FileTransferThread extends AsyncTask<String, String, String> {
        Socket socket = null;
        File file = null;

        public FileTransferThread(Socket socket, File file){
            this.socket = socket;
            this.file   = file;
        }
        protected String doInBackground(String... args) {

            try {

                client.sendImageAsByteArray(socket, file);

                socket.close();

            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
