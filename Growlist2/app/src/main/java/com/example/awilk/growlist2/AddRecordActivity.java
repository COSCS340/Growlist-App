package com.example.awilk.growlist2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.awilk.growlist2.PlantDBHelper;
import com.example.awilk.growlist2.Plant;

public class AddRecordActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mClassification1EditText;
    private EditText mClassification2EditText;
    private EditText mImageEditText;
    private Button mAddBtn;

    private PlantDBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_record);

        //init
        mNameEditText = (EditText)findViewById(R.id.userName);
        mClassification1EditText = (EditText)findViewById(R.id.userClassification1);
        mClassification2EditText = (EditText)findViewById(R.id.userClassification2);
        mImageEditText = (EditText)findViewById(R.id.userProfileImageLink);
        mAddBtn = (Button)findViewById(R.id.addNewUserButton);

        //listen to add button click
        mAddBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //call the save plant method
                if(savePlant() == true){
                    //finally redirect back home
                    // NOTE you can implement an sqlite callback then redirect on success delete
                    goBackHome();
                }

            }
        });

    }

    private boolean savePlant(){
        String name = mNameEditText.getText().toString().trim();
        String classification1 = mClassification1EditText.getText().toString().trim();
        String classification2 = mClassification2EditText.getText().toString().trim();
        String image = mImageEditText.getText().toString().trim();


        if(name.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter a name", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(classification1.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an classification1", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(classification2.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an classification2", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(image.isEmpty()){
            //error name is empty
            Toast.makeText(this, "You must enter an image link", Toast.LENGTH_SHORT).show();
            return false;
        }

        //create new plant
        dbHelper = new PlantDBHelper(this);
        Plant plant = new Plant(name, classification1, classification2, image);
        dbHelper.saveNewPlant(plant);

        return true;

    }

    private void goBackHome(){
        startActivity(new Intent(AddRecordActivity.this, MainActivity.class));
    }
}
