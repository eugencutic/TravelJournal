package com.cutic.eugen.traveljournal;

import android.app.Activity;
import android.content.Intent;
import android.drm.DrmStore;
import android.net.Uri;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    private static final int PICK_PHOTO_FOR_DESTINATION = 1;
    private ImageView mImageViewDestinationImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.manage_trip_bar));
        mImageViewDestinationImage = (ImageView)findViewById(R.id.img_destination);
    }

    public void btnSaveWithPic(View view) {
        pickImage();
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, PICK_PHOTO_FOR_DESTINATION);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PICK_PHOTO_FOR_DESTINATION) {
            if(resultCode == Activity.RESULT_OK)
            {
                if(data == null) {
                    Toast.makeText(this, "Image Pick Error", Toast.LENGTH_SHORT).show();
                    return;
                }
                Uri imgUri = data.getData();
                Picasso.get().load(imgUri).into(mImageViewDestinationImage);
            }
        }
    }
}
