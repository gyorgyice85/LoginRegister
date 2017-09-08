package com.example.somaro.loginregister.gui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import connection.Client;
import model.Node;

/**
 * Created by Cedric on 06.09.2017.
 */

public class SendNodeActivity extends Activity {

    Socket socket = null;
    Client client = new Client();
    Node node = new Node();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new ClientThread().execute();
    }

    public SendNodeActivity(Socket socket, Node node) {
        this.socket = socket;
        this.node   = node;
    }

    class ClientThread extends AsyncTask<String, String, String> {

        protected String doInBackground(String... args) {

            try {

                client.sendNodeAsByteArray(socket, node);

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
