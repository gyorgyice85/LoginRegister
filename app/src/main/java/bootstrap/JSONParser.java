package bootstrap;

import android.util.Log;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JSONParser {

    public JSONParser() throws JSONException {

    }
    static JSONObject jObj = null;

    /**
     * function to retrieve json from url by GET-request or post data to webserver by POST-Request
     * @param url
     * @param method
     * @param IP
     * @return JSON Object, which is only needed in the GET-request. It consists for the IPs from the webservers database
     * @throws JSONException
     */
    public JSONObject makeHttpRequest(String url, String method,
                                      List<NameValuePair> IP) throws JSONException {

        try {
            // check for request method
            if (method == "POST") {
                URL urlToLink = new URL(url);
                Map<String,Object> param = new LinkedHashMap<>();

                //parse the IP from the IP-List into LinkedHashMap
                param.put("IP", getQuery(IP));

                String postData = postDataSyntax(param);

                //transfer the stringbuilder into byte-array
                byte[] postDataBytes = postData.getBytes("UTF-8");

                //establish connect-parameters and connect
                HttpURLConnection connectionPost = (HttpURLConnection)urlToLink.openConnection();
                setConnectionProperties("POST",connectionPost, postDataBytes);
                //retrieve webserver's datasets
                connectionPost.getOutputStream().write(postDataBytes);

                //little helper to retrieve information from the webserver and print it if something went wrong
                Reader in = new BufferedReader(new InputStreamReader(connectionPost.getInputStream(), "UTF-8"));

                for (int c; (c = in.read()) >= 0;)
                    System.out.print((char)c);

                // check for request method
            } else if (method == "GET") {
                try {
                    //format the URL to adress the webserver correctly (http://192.168.178.78/android_connect/get_all_bootstrap.php?)
                    String parsedString = "";
                    String paramString = URLEncodedUtils.format(IP, "utf-8");
                    url += "?" + paramString;
                    URL urlToLink = new URL(url);

                    HttpURLConnection connGet = (HttpURLConnection)urlToLink.openConnection();
                    setConnectionProperties("GET", connGet, null);

                    //retrieve information and format it correctly
                    InputStream is = connGet.getInputStream();
                    try {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(
                                is, "iso-8859-1"), 8);
                        StringBuilder sb = new StringBuilder();
                        String line = null;
                        while ((line = reader.readLine()) != null) {
                            sb.append(line + "\n");
                        }
                        is.close();
                        parsedString = sb.toString();
                    } catch (Exception e) {
                        Log.e("Buffer Error", "Error converting result " + e.toString());
                    }
                    // parsing the string to a JSON object
                    try {
                        jObj = new JSONObject(parsedString);
                    } catch (JSONException e) {
                        Log.e("JSON Parser", "Error parsing data " + e.toString());
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // return JSON String
        return jObj;
    }
    /**
     * method to transfer the given format (Name: IP, Value: 192.168.178.789 to IP=192.168.178.78
     * @param params
     * @return GET-Request-Header with the expected values
     * @throws UnsupportedEncodingException
     */
    private String getQuery(List<NameValuePair> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;

        for (NameValuePair pair : params) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(pair.getName(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(pair.getValue(), "UTF-8"));
        }
        return result.toString();
    }
    /**
     * organize the POST-Request-Body
     * @param param
     * @return the data of the POST-Request-Body in a String
     * @throws UnsupportedEncodingException
     */
    private String postDataSyntax(Map<String,Object> param) throws UnsupportedEncodingException {
        StringBuilder postData = new StringBuilder();
        for (Map.Entry<String,Object> entry : param.entrySet()) {
            //append POST-body-syntax
            if (postData.length() != 0) postData.append('&');
            postData.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            postData.append('=');
            postData.append(URLEncoder.encode(String.valueOf(entry.getValue()), "UTF-8"));
        }
        return postData.toString();
    }
    /**
     * set the connection properties for "POST" and "GET"
     * @param connection
     * @param conn
     * @param postDataBytes
     * @throws ProtocolException
     */
    private void setConnectionProperties (String connection, HttpURLConnection conn, byte[] postDataBytes) throws ProtocolException {
        if(connection == "POST"){
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
        }else if (connection == "GET"){
            conn.setAllowUserInteraction(false);
            conn.setInstanceFollowRedirects(true);
            conn.setRequestMethod("GET");
        }
    }
}