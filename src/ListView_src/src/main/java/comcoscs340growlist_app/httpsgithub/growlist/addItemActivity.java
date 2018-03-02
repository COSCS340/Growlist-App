package com.example.byen.localgrowlistproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

//This is blank for now
public class addItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //for now, the activity will be static. If longer descriptions are needed,
        // this can be changed
        setContentView(R.layout.activity_add_item);


    }
    //perform some checking on the input
    //add to database
    //return to main activity
    //Main activity still needs to be modified to receive the input
    public void onClick(View view) {
        //may want to do some checks/verification on the input before returning
        Intent toMain = new Intent(this, MainActivity.class);

        final EditText speciesName = (EditText) findViewById(R.id.speciesNameInput);
        final EditText speciesDescription = (EditText) findViewById(R.id.speciesDescriptionInput);

        String sendName = speciesName.getText().toString();
        String sendDescription = speciesDescription.getText().toString();

        toMain.putExtra("addItemName", sendName);
        toMain.putExtra("addItemDescription", sendDescription);

        startActivity(toMain);
    }
}
