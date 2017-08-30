package com.example.somaro.loginregister.gui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.somaro.loginregister.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;



public class UserAreaActivity extends Activity {
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private ImageView imageView ;
    static final int CAM_REQUEST = 1 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


        imageView = (ImageView) findViewById(R.id.imageView);
        final TextView welcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");

        String message = name + " welcome to your user area";
        welcomeMsg.setText(message);
    }

    /**
     *  Methode zum starten der Kammera
     *
     * @param v
     */
    public void btnSaveClicked (View v) {

        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, CAM_REQUEST);
    }

    /**
     * Methode zum Anzeigen des Bildes in der App
     * Und Aufruf der anderen Methoden
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
        saveImageFile(bitmap) ;
        }

    /**
     *
     * Methode zum Speichern des Bildes
     *
     * @param bitmap Das Bild
     * @return der Dateiname
     */
    private String saveImageFile(Bitmap bitmap) {
        FileOutputStream out = null;
        String filename = getFilename();
        try {
            out = new FileOutputStream(filename);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return filename;
    }

    /**
     * Methode zum Erstellen des Speicherpfades
     *
     * @return der Speicherpfad
     */
    private String getFilename() {

        File file = new File(Environment.getExternalStorageDirectory() + File.separator+"images");

        File dir = new File(file, "CAN_PICS");
        Toast.makeText(UserAreaActivity.this, dir.toString(), Toast.LENGTH_SHORT).show();
        /*if(dir.mkdirs()){

            String uriSting = (dir.getAbsolutePath() +"/"
                    + System.currentTimeMillis() + ".jpg");
                return uriString;
        }else{
            // TODO: 29.08.2017 throw exception 
        }*/

        dir.mkdirs();
        String uriString = (dir.getAbsolutePath() +"/"
                + System.currentTimeMillis() + ".jpg");
        return uriString;
    }

    /**
     * Öffnen der Foto Gallery
     *
     * @param v
     */
    public void onImageGalleryClick(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        Toast.makeText(UserAreaActivity.this, pictureDirectory.toString(), Toast.LENGTH_SHORT).show();
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);

        photoPickerIntent.setDataAndType(data, "image/*");

        startActivityForResult(photoPickerIntent,IMAGE_GALLERY_REQUEST );
    }

}