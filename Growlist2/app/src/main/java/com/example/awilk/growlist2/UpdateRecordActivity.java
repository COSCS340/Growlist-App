package com.example.awilk.growlist2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.awilk.growlist2.PlantDBHelper;
import com.example.awilk.growlist2.Plant;

public class UpdateRecordActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mClassification1EditText;
    private EditText mClassification2EditText;
    private EditText mImageEditText;
    private Button mUpdateBtn;

    private PlantDBHelper dbHelper;
    private long receivedPlantId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_record);

        //init
        mNameEditText = (EditText)findViewById(R.id.userNameUpdate);
        mClassification1EditText = (EditText)findViewById(R.id.userClassification1Update);
        mClassification2EditText = (EditText)findViewById(R.id.userClassification2Update);
        mImageEditText = (EditText)findViewById(R.id.userProfileImageLinkUpdate);
        mUpdateBtn = (Button)findViewById(R.id.updateUserButton);

        dbHelper = new PlantDBHelper(this);

        try {
            //get intent to get plant id
            receivedPlantId = getIntent().getLongExtra("USER_ID", 1);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /***populate plant data before update***/
        Plant queriedPlant = dbHelper.getPlant(receivedPlantId);
        //set field to this user data
        mNameEditText.setText(queriedPlant.getName());
        mClassification1EditText.setText(queriedPlant.getClassification1());
        mClassification2EditText.setText(queriedPlant.getClassification2());
        mImageEditText.setText(queriedPlant.getImage());



        //listen to add button click to update
        mUpdateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save plant method
                updatePlant();
            }
        });





    }

    private void updatePlant(){
        String name = mNameEditText.getText().toString().trim();
        String age = mClassification1EditText.getText().toString().trim();
        String occupation = mClassification2EditText.getText().toString().trim();
        String image = mImageEditText.getText().toString().trim();


        if(name.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
        }

        if(age.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an age", Toast.LENGTH_SHORT).show();
        }

        if(occupation.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an occupation", Toast.LENGTH_SHORT).show();
        }

        if(image.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an image link", Toast.LENGTH_SHORT).show();
        }

        //create updated plant
        Plant updatedPlant = new Plant(name, age, occupation, image);

        //call dbhelper update
        dbHelper.updatePlantRecord(receivedPlantId, this, updatedPlant);

        //finally redirect back home
        // NOTE you can implement an sqlite callback then redirect on success delete
        goBackHome();

    }

    private void goBackHome(){
        startActivity(new Intent(this, MainActivity.class));
    }
}