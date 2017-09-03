package app;



import android.app.Application;
import android.content.Context;

import source.DateiMemoDbHelper;
import source.DatabaseManager;


/**
 * Created by en on 21.08.17.
 */

public class App extends Application {
    private static Context context;
    private static DateiMemoDbHelper dbHelper;

    @Override
    public void onCreate()
    {
        super.onCreate();
        context = this.getApplicationContext();
        dbHelper = new DateiMemoDbHelper();
        DatabaseManager.initializeInstance(dbHelper);

    }


    public static Context getContext(){
        return context;
    }
}
