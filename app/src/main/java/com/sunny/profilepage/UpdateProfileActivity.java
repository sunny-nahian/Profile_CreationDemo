package com.sunny.profilepage;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import de.hdodenhof.circleimageview.CircleImageView;

public class UpdateProfileActivity extends AppCompatActivity implements View.OnClickListener {
    Intent next_Activity;
    // For SharedPraferances use
    private Button save;
    private EditText userName, userDate, userJob, userSchool;
    // For Image add from Gallery
    private CircleImageView ProfileImage;
    private static final int PICK_IMAGE = 1;
    Uri imageUri;
    Button button;
// End of image Add on Gallery
    private static final int CAMERA_REQUEST = 1888;
    private ImageView camera;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        //For SharePreferences
        save = (Button)findViewById(R.id.saveButtonID);
        userName = (EditText) findViewById(R.id.userNameEditID);
        userDate =(EditText) findViewById(R.id.userDateEditID);
        userJob =(EditText) findViewById(R.id.userJobEditID);
        userSchool =(EditText) findViewById(R.id.userSchoolEditID);
        save.setOnClickListener(this);
        next_Activity = new Intent(this, com.sunny.profilepage.MainActivity.class);

//        //Adding Back Button on tool bar/action Bar
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true); // for action bar adding
//        getSupportActionBar().setDisplayShowHomeEnabled(true);  // for action bar adding

        // For Camera Image Add
        camera =(ImageView)findViewById(R.id.cam_btn);
        camera.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                if (checkSelfPermission(Manifest.permission.CAMERA)!= getPackageManager().PERMISSION_GRANTED)
                {
                   requestPermissions(new String[]{Manifest.permission.CAMERA},MY_CAMERA_PERMISSION_CODE);
                }else {
                    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });
        // for image Add on gallery
        ProfileImage = (CircleImageView) findViewById(R.id.c_image);
        button = (Button) findViewById(R.id.c_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent gallery = new Intent();
                gallery.setType("image/*");
                gallery.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(gallery, "Sellect Picture"), PICK_IMAGE);
            }
        });
    }
//
// For Image  take on Camera 3rd part
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);
        if ((requestCode == MY_CAMERA_PERMISSION_CODE)){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this,"camera permission granted",Toast.LENGTH_SHORT).show();
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }
//
//@Override
//protected void onActivityResult(int requestCode,int resultCode, Intent data){
//        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
//            Bitmap photo = (Bitmap)data.getExtras().get("data");
//            camera.setImageBitmap(photo);
//        }
//}

    //for image add on gallery
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK){
            Bitmap photo = (Bitmap)data.getExtras().get("data");
            camera.setImageBitmap(photo);
        }
        if (requestCode==PICK_IMAGE && resultCode==RESULT_OK){
            imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(),imageUri);
                ProfileImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    } //End of Image add on Gallery 3rd part

//    For SharedPraferences Listiner Add
    @Override
    public void onClick(View view) {

        String username = userName.getText().toString();
        String birthday = userDate.getText().toString();
        String job = userJob.getText().toString();
        String school = userSchool.getText().toString();

        if (username.equals("") && birthday.equals("") && userJob.equals("") && userSchool.equals("")){

            Toast.makeText(getApplicationContext(),"Please enter some data",Toast.LENGTH_SHORT).show();
        }
        else {
// SharedPreferences data Store
            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();  // data write
            editor.putString("usernameKey",username);
            editor.putString("dateKey",birthday);
            editor.putString("jodKey",job);
            editor.putString("schoolkey",school);
            editor.commit();
            Toast.makeText(getApplicationContext(),"Data is Store Successfully",Toast.LENGTH_SHORT).show();
        }
    } // end
    // for Action Bar ADD 2nd part
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {  //for Action bar adding
        if (item.getItemId()==android.R.id.home){
            this.finish();
        }
        return super.onOptionsItemSelected(item);
    }  // End for action bar adding
}
