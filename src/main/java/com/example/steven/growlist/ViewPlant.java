package com.example.steven.growlist;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;


public class ViewPlant extends AppCompatActivity {

    private TextView GenusText;
    private TextView SpeciesText;
    private TextView QuantityText;
    private TextView NotesText;
    private ImageView mImageView;
    private PlantDBHelper dbHelper;
    private long receivedPlantId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("ViewPlant:", "Welcome to ViewPlant");

        setContentView(R.layout.activity_view);

        dbHelper = new PlantDBHelper(this);

        try {
            //get intent to get plant id
            receivedPlantId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Plant queriedPlant = dbHelper.getPlant(receivedPlantId);
        GenusText = findViewById(R.id.UserGenus);
        SpeciesText = findViewById(R.id.UserSpecies);
        QuantityText = findViewById(R.id.UserQuantity);
        NotesText = findViewById(R.id.UserNotes);
        mImageView = findViewById(R.id.imagePreview);
        String temp = "";
        temp = "Genus: " + queriedPlant.getGenus();
        GenusText.setText(temp);
        GenusText.setTextSize(TypedValue.COMPLEX_UNIT_SP,30);
        temp = "Species: " + queriedPlant.getSpecies();
        SpeciesText.setText(temp);
        SpeciesText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
        temp = "Quantity: " + queriedPlant.getQuantity();
        QuantityText.setText(temp);
        QuantityText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        temp = "Notes: " + queriedPlant.getNotes();
        NotesText.setText(temp);
        NotesText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        int orientation = 0;
        String mCurrentPhotoPath = queriedPlant.getImage();
        try {
            ExifInterface exif = new ExifInterface(mCurrentPhotoPath);
            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,1);
            if (orientation == 1) orientation = 0;
            else if (orientation == 3) orientation = 180;
            else if (orientation == 6) orientation = 90;
            else if (orientation == 8) orientation = 270;
            Log.d("Orientation: ", Integer.toString(orientation));
        } catch (IOException e){
            e.printStackTrace();
        }

        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        //BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        bmOptions.inJustDecodeBounds = false;
        bmOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions);
        Matrix matrix = new Matrix();
        matrix.postRotate(orientation);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
        mImageView.setImageBitmap(bitmap);

    }

}
