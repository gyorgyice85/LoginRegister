package bootstrap;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Created by Axel on 29.08.2017.
 * inserts own IP into webserver's database
 */

public class InsertOwnIPActivity extends Activity {

    JSONParser jsonParser = new JSONParser();

    public InsertOwnIPActivity() throws JSONException {
    }

    private static String url_set_own_ip = "https://axelczuck.000webhostapp.com/create_bootstrap.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new InsertOwnIP().execute();
    }
    /**
     * call the JSON-Parser for the POST-Request
     */
    class InsertOwnIP extends AsyncTask<String, String, String> {

        protected String doInBackground(String... args){
            String ownIP = getIPAddress(true);
            List<NameValuePair> params = new ArrayList<>();
            params.add(new BasicNameValuePair("IP", ownIP));
            try {
                JSONObject json = jsonParser.makeHttpRequest(url_set_own_ip,
                        "POST", params);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return null;
        }
        /**
         *
         * @param useIPv4
         * @return the IPv4 address of the device, which uses this app
         */
        public String getIPAddress(boolean useIPv4) {
            try {
                List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());

                for (NetworkInterface intf : interfaces) {
                    List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                    for (InetAddress addr : addrs) {
                        if (!addr.isLoopbackAddress()) {
                            String sAddr = addr.getHostAddress();
                            boolean isIPv4 = sAddr.indexOf(':') < 0;
                            // get IPv4
                            if (useIPv4) {
                                if (isIPv4)
                                    return sAddr;
                            } else {
                                //get IPv6
                                if (!isIPv4) {
                                    int delim = sAddr.indexOf('%');
                                    return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                                }
                            }
                        }
                    }
                }
            } catch (Exception ex) { }
            return "";
        }

    }

}
