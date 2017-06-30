package com.example.somaro.loginregister;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Somaro on 04.06.2017.
 */

public class RegisterRequeast extends StringRequest {

    // für localhot muss Nur die aktuelle IP einsetzen (ipconfig)
    private static final String REGISTER_REQUEST_URL = "http://agelong-rations.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequeast(String name, String username, String password, int age, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("username", username);
        params.put("password", password);
        params.put("age", age +"");

    }

    @Override
    public Map<String, String> getParams(){
        return params;
    }

}
