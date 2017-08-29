package com.example.somaro.loginregister;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ShareCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.ion.builder.Builders;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class UserAreaActivity extends Activity {
    public static final int IMAGE_GALLERY_REQUEST = 20;
    Button btnTakePhoto;
    private ImageView imageView ;

    File pictureDirectory ;
    static final int REQUEST_TAKE_PHOTO = 1;
    static final int REQUEST_IMAGE_CAPTURE =1;
    private static final int CAMERA_PHOTO = 111;
    static final int CAM_REQUEST = 1 ;
    private String mImageFileLocation = "";
    private static final int ACTIVITY_START_CAMERA_APP = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);


        imageView = (ImageView) findViewById(R.id.imageView);

        //final EditText etUsername = (EditText) findViewById(R.id.etUsername);
        //final EditText etAge = (EditText) findViewById(R.id.etAge);
        final TextView welcomeMsg = (TextView) findViewById(R.id.tvWelcomeMsg);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String username = intent.getStringExtra("username");
        //int age = intent.getIntExtra("age", -1);

        String message = name + " welcome to your user area";
        welcomeMsg.setText(message);
        // etUsername.setText(username);
        // etAge.setText(age + "");
    }
    public void btnSaveClicked (View v) {

        Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera_intent, CAM_REQUEST);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);
        saveImageFile(bitmap) ;
        }


    public String saveImageFile(Bitmap bitmap) {
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

    private String getFilename() {
        File file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
       File dir = new File(file, "AlexAAAA");
        dir.mkdir();
        String uriSting = (dir.getAbsolutePath() + "/"
                + System.currentTimeMillis() + ".jpg");
        return uriSting;
    }

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