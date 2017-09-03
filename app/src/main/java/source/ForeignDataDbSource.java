package source;
/**
 * Created by en on 13.08.17.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;


import android.content.ContentValues;
import android.database.Cursor;

import source.DatabaseManager;
import source.DateiMemoDbHelper;
import model.Node;
import model.ForeignData;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static android.R.attr.id;

public class ForeignDataDbSource {
    private static final String LOG_TAG = ForeignDataDbSource.class.getSimpleName();

    private SQLiteDatabase database;
    private DateiMemoDbHelper dbHelper;
    private DateiMemoDbSource dateiMemoDbSource;
    private ForeignData foreignData;

    //Array
    private String[] columns_ForeignData = {
            DateiMemoDbHelper.COLUMN_FOTOID,
            DateiMemoDbHelper.COLUMN_UID,
            DateiMemoDbHelper.COLUMN_PUNKTX,
            DateiMemoDbHelper.COLUMN_PUNKTY,
            //DateiMemoDbHelper.COLUMN_CHECKED,
            DateiMemoDbHelper.COLUMN_IP
    };

    public ForeignDataDbSource(){
        foreignData = new ForeignData();
    }
     /*
    *
    * For single table
    *
    * */

//    public ForeignDataDbSource(Context context) {
//        Log.d(LOG_TAG, "Unsere DataSource erzeugt jetzt den dbHelper.");
//        dbHelper = new DateiMemoDbHelper(context);
//    }
//
//    //mit getWritableDatabase öffnet man die Verbindung DB
//    public void open() {
//        Log.d(LOG_TAG, "Eine Referenz auf die Datenbank wird jetzt angefragt.");
//        database = dbHelper.getWritableDatabase();
//        Log.d(LOG_TAG, "Datenbank-Referenz erhalten. Pfad zur Datenbank: " + database.getPath());
//    }
//
//    public void close() {
//        dbHelper.close();
//        Log.d(LOG_TAG, "Datenbank mit Hilfe des DbHelpers geschlossen.");
//    }

    /*
   *
   *
   *           Converting List to Double -- List to Integer -- List to Long
   *
   * */
    public double listToDouble(List<Double> list){
        double[] tmp = new double[list.size()];
        double ret = 0;

        for (int i = 0; i < list.size(); ++i) { //iterate over the elements of the list
            tmp[i] = Double.valueOf(list.get(i));
        }
        for (int j = 0; j < tmp.length; ++j) {
            ret = tmp[j];
        }

        return ret;
    }

    public int listToInt(List<Integer> list){
        int[] tmp = new int[list.size()];
        int ret = 0;

        for (int i = 0; i < list.size(); ++i) { //iterate over the elements of the list
            tmp[i] = Integer.valueOf(list.get(i));
        }
        for (int j = 0; j < tmp.length; ++j) {
            ret = tmp[j];
        }

        return ret;
    }

    public long listToLong(List<Long> list){
        long[] tmp = new long[list.size()];
        long ret = 0;

        for (int i = 0; i < list.size(); ++i) { //iterate over the elements of the list
            tmp[i] = Long.valueOf(list.get(i));
        }
        for (int j = 0; j < tmp.length; ++j) {
            ret = tmp[j];
        }

        return ret;
    }


    //
    //==================================================================================================================
    //

    //
    //private long uid;
    //private boolean checked;
    //private int fotoId;
    //private double punktX;
    //private double punktY;
    //private String foreignIp;
    //
    //----------------------------- Insert, delete, update, get values in Table ---------------------------------
    //
    //
    /*
    *
    *                                             Insert Data
    *
    *
    * */
    public int createForeignData(ForeignData foreignData) {
        database = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(DateiMemoDbHelper.COLUMN_FID, foreignData.getUid());
        //values.put(DateiMemoDbHelper.COLUMN_CHECKED, foreignData.isChecked());
        values.put(DateiMemoDbHelper.COLUMN_FOTOID, foreignData.getFotoId());
        values.put(DateiMemoDbHelper.COLUMN_PUNKTX, foreignData.getPunktX());
        values.put(DateiMemoDbHelper.COLUMN_PUNKTY, foreignData.getPunktY());
        values.put(DateiMemoDbHelper.COLUMN_IP, foreignData.getForeignIp());


        //
        //insert row
        //insert muss long
        //
        int foreign_Id = (int)database.insert(DateiMemoDbHelper.TABLE_FOREIGNDATA_LIST, null, values);
        DatabaseManager.getInstance().closeDatabase();
        //
        //dataId
        //insert data in Array
        //
//        Cursor cursor = database.query(DateiMemoDbHelper.TABLE_FOREIGNDATA_LIST,
//                columns_ForeignData, DateiMemoDbHelper.COLUMN_UID + "=" + foreign_Id ,
//                null, null, null, null);
//
//        cursor.moveToFirst();
//        foreignData = cursorToForeignData(cursor);
//        cursor.close();

        return foreign_Id;
    }

    /*
    *
    *
    *                                           Delete data
    *
    *
    *
    * */
    public void deleteForeignData() {
        database = DatabaseManager.getInstance().openDatabase();
        database.delete(DateiMemoDbHelper.TABLE_FOREIGNDATA_LIST, null, null);
        DatabaseManager.getInstance().closeDatabase();
        //Log.d(LOG_TAG, "Eintrag gelöscht! ID: " + foreignData.getUid() + " Inhalt: " + foreignData.toString());
    }
    /*
    *
    * ==================================================================================================================
    * */

    /*
    *
    *
    *               Hilfklasse für Update Methode und Insert Methode
    *
    * */
//    private ForeignData cursorToForeignData(Cursor cursor) {
//        int idIndex = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_UID);
//        int idChecked = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CHECKED);
//        int idFotoId = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_FOTOID);
//        int idPunktX = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTX);
//        int idPunktY = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTY);
//        int idForeignIp = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_IP);
//
//
//
//        long uid = cursor.getLong(idIndex);
//
//        int intValueChecked = cursor.getInt(idChecked);
//        boolean isChecked = (intValueChecked != 0);
//
//        int fotoId = cursor.getInt(idFotoId);
//        double punktX = cursor.getDouble(idPunktX);
//        double punktY = cursor.getDouble(idPunktY);
//        String foreignIp = cursor.getString(idForeignIp);
//
//
//
//        ForeignData foreignData = new ForeignData(uid, isChecked, fotoId, punktX, punktY, foreignIp);
//
//        return foreignData;
//    }

    /*
   *           Get
   *
   *
   *           All Data
   *
   *
   *
   * */
     /*
    *           Get
    *
    *
    *           Punkt X
    *
    *
    * */
    public double getPunktXForeign(long uid) {
        database = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_PUNKTX +" FROM " + DateiMemoDbHelper.TABLE_FOREIGNDATA_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_FID + " = " + uid;

        Log.e(LOG_TAG, selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        double punktX;
        punktX = c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTX));
        DatabaseManager.getInstance().closeDatabase();
        return punktX;
    }
    //
    // ================================================================================================================================
    //



    /*
    *           Get
    *
    *
    *           Punkt Y
    *
    *
    * */
    public double getPunktYForeign(long uid) {
        database = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_PUNKTY +" FROM " + DateiMemoDbHelper.TABLE_FOREIGNDATA_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_UID + " = " + uid;

        Log.e(LOG_TAG, selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        double punktY;
        punktY = c.getDouble(c.getColumnIndex(DateiMemoDbHelper.COLUMN_PUNKTY));
        DatabaseManager.getInstance().closeDatabase();
        return punktY;
    }
    //
    // ================================================================================================================================
    //

    /*
    *
    *
    *                   get Foto ID
    *
    *
    * */
    public int getFotoId(long uid) {
        database = DatabaseManager.getInstance().openDatabase();
        //List<long> UidList = new ArrayList<>();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_FOTOID + " FROM " + DateiMemoDbHelper.TABLE_FOREIGNDATA_LIST+ " WHERE "
                + DateiMemoDbHelper.COLUMN_UID + " = " + uid;

        Cursor cursor = database.rawQuery(selectQuery, null);

        cursor.moveToFirst();
        int fotoId;
        fotoId = cursor.getInt(cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_FOTOID));

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();
        return fotoId;
    }
    //
    // ================================================================================================================================
    //

    /*
    *
    *           Get UID
    *
    * */
    public double getUidForeign() {
        database = DatabaseManager.getInstance().openDatabase();
        DatabaseManager.getInstance().closeDatabase();
        return dateiMemoDbSource.getUid();

    }
    //
    // ================================================================================================================================
    //

    /*
    *           Get
    *
    *
    *           Foreign IP
    *
    *
    * */
    public String getforeignIp(long uid) {
        database = DatabaseManager.getInstance().openDatabase();
        String selectQuery = "SELECT "+ DateiMemoDbHelper.COLUMN_IP +" FROM " + DateiMemoDbHelper.TABLE_FOREIGNDATA_LIST + " WHERE "
                + DateiMemoDbHelper.COLUMN_UID + " = " + uid;

        Log.e(LOG_TAG, selectQuery);

        Cursor c = database.rawQuery(selectQuery, null);

        if (c != null)
            c.moveToFirst();
        String foreignIp;
        foreignIp = c.getString(c.getColumnIndex(DateiMemoDbHelper.COLUMN_IP));
        DatabaseManager.getInstance().closeDatabase();
        return foreignIp;
    }
    //
    // ================================================================================================================================
    //

    public List<ForeignData> getAllForeignData() {

        List<ForeignData> ForeignDataList = new LinkedList<ForeignData>();

        //1. query
        String query = "SELECT * FROM " + dbHelper.TABLE_FOREIGNDATA_LIST;

        //2. open Database
        database = DatabaseManager.getInstance().openDatabase();

        Cursor cursor = database.rawQuery(query, null);

//        int idChecked = cursor.getColumnIndex(DateiMemoDbHelper.COLUMN_CHECKED);
//        int intValueChecked = cursor.getInt(idChecked);
//        boolean isChecked = (intValueChecked != 0);


        //3. Durchführen Zeile und füge in List hinzu
        ForeignData foreignData = null;
        if (cursor.moveToFirst()) {
            do {
                foreignData = new ForeignData();
                foreignData.setUid(cursor.getLong(cursor.getColumnIndex(dbHelper.COLUMN_FID)));
                //foreignData.setChecked(isChecked);
                foreignData.setPunktX(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_PUNKTX)));
                foreignData.setPunktY(cursor.getDouble(cursor.getColumnIndex(dbHelper.COLUMN_PUNKTY)));
                foreignData.setForeignIp(cursor.getString(cursor.getColumnIndex(dbHelper.COLUMN_IP)));
                foreignData.setFotoId(cursor.getInt(cursor.getColumnIndex(dbHelper.COLUMN_FOTOID)));


                // Add book to books
                ForeignDataList.add(foreignData);
            } while (cursor.moveToNext());
        }

        cursor.close();
        DatabaseManager.getInstance().closeDatabase();

        return ForeignDataList;
    }

}