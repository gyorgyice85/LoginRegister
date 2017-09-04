package bootstrap;

import org.json.JSONException;

/**
 * Created by Joshi on 04.09.2017.
 */

public class TestBoots {

    public static void main(String[] args){
        try {
            AllIPsActivity all = new AllIPsActivity();
            System.out.println(all.start());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
}
