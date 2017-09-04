package bootstrap;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.SimpleAdapter;



import org.apache.http.NameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.Node;

public class AllIPsActivity extends ListActivity {

    // Progress Dialog
    private ProgressDialog pDialog;
    private String[] ipList;
    // Creating JSON Parser object
    JSONParser jParser = new JSONParser();

    ArrayList<HashMap<String, String>> ipsList;

    // url to get all IPs
    private static String url_all_ips = "https://axelczuck.000webhostapp.com/get_all_bootstrap.php";
    // JSON Node names
    private static final String TAG_DB = "bootstraps";
    private static final String TAG_IP = "IP";
    private static final String TAG_ID = "ID";

    // ips JSONArray
    JSONArray ips = null;

    public AllIPsActivity() throws JSONException {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Hashmap for ListView
        ipsList = new ArrayList<>();

        // Loading IPs in Background Thread
        new LoadAllIPs().execute();

    }

    /**
     * Background Async Task to Load all ips by making HTTP Request
     * */
    public class LoadAllIPs extends AsyncTask<String, String, String> {

        /**
         * Before starting background thread Show Progress Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(AllIPsActivity.this);
            pDialog.setMessage("Loading IPs. Please wait...");
            pDialog.setIndeterminate(false);
            pDialog.setCancelable(false);
            pDialog.show();
        }
        /**
         * getting all IPs from url with a GET-Request
         * */
        protected String doInBackground(String... args) {
            // Building Parameters
            List<NameValuePair> paramsGet = new ArrayList<>();
            // getting JSON string from URL
            JSONObject jsonGET = null;
            try {
                jsonGET = jParser.makeHttpRequest(url_all_ips, "GET", paramsGet);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            // Check your log cat for JSON reponse
            Log.d("All IPs: ", jsonGET.toString());

            /**
             * Parse the returned JSON-Object into Strings
             **/
            try {
                // IPs found

                // Getting Array of IPs
                ips = jsonGET.getJSONArray(TAG_DB);
                ipList = new String[ips.length()];
                // looping through All IPs
                for (int i = 0; i < ips.length(); i++) {
                    JSONObject c = ips.getJSONObject(i);

                    // Storing each json item in variable
                    String ip = c.getString(TAG_IP);
                    //Retrieved the correct IPs?
                    Log.d("Single IPs: ", ip);

                    ipList[i] = ip;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }


        public String[] getInitializedIpList(){
            doInBackground("");
            return getIpList();

        }
        /**
         * After completing background task Dismiss the progress dialog
         * **/
        /*
        protected void onPostExecute(String file_url) {
            // dismiss the dialog after getting all IPs
            pDialog.dismiss();
            // updating UI from Background Thread and create ListView
            runOnUiThread(new Runnable() {
                public void run() {

                     // Updating parsed JSON data into ListView

                    ListAdapter adapter = new SimpleAdapter(
                            AllIPsActivity.this, ipsList,
                            R.layout.list_item, new String[] { TAG_ID,
                            TAG_IP},
                            new int[] { R.id.pid, R.id.name });
                    // updating listView
                    setListAdapter(adapter);
                }
            });

        } */

    }
    //Methode um die IP Liste zu bekommen
    public String[] getIpList(){
        return ipList;
    }

    public String[] start(){
        LoadAllIPs allIPs = new LoadAllIPs();
        return allIPs.getInitializedIpList();
    }


}
