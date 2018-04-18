package com.example.steven.growlist;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;
import android.widget.Toast;

import java.sql.PreparedStatement;
import java.util.LinkedList;
import java.util.List;

import com.example.steven.growlist.Plant;


public class PlantDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "plants1.db";
    private static final int DATABASE_VERSION = 3 ;
    public static final String TABLE_NAME = "Plant";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PLANT_SPECIES = "Species";
    public static final String COLUMN_PLANT_GENUS = "Genus";
    public static final String COLUMN_PLANT_QUANTITY = "Quantity";
    public static final String COLUMN_PLANT_NOTES = "notes";
    public static final String COLUMN_PLANT_IMAGE = "image";


    public PlantDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PLANT_GENUS + " TEXT NOT NULL, " +
                COLUMN_PLANT_SPECIES + " TEXT NOT NULL, " +
                COLUMN_PLANT_QUANTITY + " NUMBER NOT NULL, " +
                COLUMN_PLANT_NOTES + " TEXT NOT NULL, " +
                COLUMN_PLANT_IMAGE + " BLOB NOT NULL);"
        );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // you can implement here migration process
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }
    /**create record**/
    public void saveNewPlant(Plant plant) {
        Log.d("Save:", "Saving new Plant");
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLANT_GENUS, plant.getGenus());
        values.put(COLUMN_PLANT_SPECIES, plant.getSpecies());
        values.put(COLUMN_PLANT_QUANTITY, plant.getQuantity());
        values.put(COLUMN_PLANT_NOTES, plant.getNotes());
        values.put(COLUMN_PLANT_IMAGE, plant.getImage());
        Log.d("Values:", "inserted");
        // insert
        db.insert(TABLE_NAME,null, values);
        db.close();
    }

    /**Query records, give options to filter results**/
    public List<Plant> plantsList(String filter) {
        String query;
        if(filter.equals("")){
            //regular query
            query = "SELECT  * FROM " + TABLE_NAME;
        }else if(filter.equals(COLUMN_PLANT_GENUS)){
            //filter results by filter option provided
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter + ", Species";
        }
        else {
            query = "SELECT  * FROM " + TABLE_NAME + " ORDER BY "+ filter;
        }

        List<Plant> plantLinkedList = new LinkedList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Plant plant;

        if (cursor.moveToFirst()) {
            do {
                plant = new Plant();

                plant.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
                plant.setGenus(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_GENUS)));
                plant.setSpecies(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_SPECIES)));
                plant.setQuantity(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_QUANTITY)));
                plant.setNotes(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_NOTES)));
                plant.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_IMAGE)));
                plantLinkedList.add(plant);
            } while (cursor.moveToNext());
        }


        return plantLinkedList;
    }

    /**Query only 1 record**/
    public Plant getPlant(long id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT  * FROM " + TABLE_NAME + " WHERE _id="+ id;
        Cursor cursor = db.rawQuery(query, null);

        Plant receivedPlant = new Plant();
        if(cursor.getCount() > 0) {
            cursor.moveToFirst();

            receivedPlant.setGenus(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_GENUS)));
            receivedPlant.setSpecies(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_SPECIES)));
            receivedPlant.setQuantity(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_QUANTITY)));
            receivedPlant.setNotes(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_NOTES)));
            receivedPlant.setImage(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_IMAGE)));
        }

        return receivedPlant;
    }


    /**delete record**/
    public void deletePlantRecord(long id, Context context) {
        SQLiteDatabase db = this.getWritableDatabase();

        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE _id='"+id+"'");
        Toast.makeText(context, "Deleted successfully.", Toast.LENGTH_SHORT).show();

    }

    /**update record**/
    public void updatePlantRecord(long plantId, Context context, Plant updatedplant) {
        SQLiteDatabase db = this.getWritableDatabase();
        //you can use the constants above instead of typing the column names
        //May want to parametrize the whole statement to prevent crashes
        SQLiteStatement updateStatement = db.compileStatement("UPDATE  "+TABLE_NAME+" SET Genus ='"+ updatedplant.getGenus() + "', Species ='" + updatedplant.getSpecies()+ "', Quantity ='"+ updatedplant.getQuantity() +
                "', Notes =?, image ='" + updatedplant.getImage() + "'  WHERE _id='" + plantId + "'");
        updateStatement.bindString(1, updatedplant.getNotes());
        updateStatement.execute();
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();


    }




}
