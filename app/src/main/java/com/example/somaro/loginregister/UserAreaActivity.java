package com.example.somaro.loginregister;

import android.app.Activity;
import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserAreaActivity extends Activity {
    public static final int IMAGE_GALLERY_REQUEST = 20;
    Button btnTakePhoto;
    private ImageView imageView ;
    static final int CAM_REQUEST = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_area);

        btnTakePhoto = (Button) findViewById(R.id.btnTakePhoto) ;
        imageView = (ImageView) findViewById(R.id.imageView);


        btnTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                //File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                //String pictureName = getPictureName();
                //File imageFile = new File(pictureDirectory, pictureName);
                // Uri pictureUri = Uri.fromFile(imageFile);
                //camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);

                startActivityForResult(camera_intent, CAM_REQUEST);


            }
            private String getPictureName(){
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String timestamp = sdf.format(new Date());
                return "PlantPlacesImage" + timestamp + ".jpg";
            }


        });


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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) throws NullPointerException {
        /*
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST){
                Uri imageUri = data.getData();

                InputStream inputStream ;

                try {
                    inputStream = getContentResolver().openInputStream(imageUri);

                    Bitmap image = BitmapFactory.decodeStream(inputStream);

                    imageView.setImageBitmap(image);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    Toast.makeText(this, "Unable to open image", Toast.LENGTH_LONG).show();
                }
            }
        }
*/

        super.onActivityResult(requestCode, resultCode, data);
        Bitmap bitmap = (Bitmap)data.getExtras().get("data");
         imageView.setImageBitmap(bitmap);
    }

    public void onImageGalleryClick(View v){
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);

        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);

        photoPickerIntent.setDataAndType(data, "image/*");


        startActivityForResult(photoPickerIntent,IMAGE_GALLERY_REQUEST );
    }


}