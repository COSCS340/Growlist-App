package com.example.awilk.growlist2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.LinkedList;
import java.util.List;

import com.example.awilk.growlist2.Plant;


public class PlantDBHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "plants.db";
    private static final int DATABASE_VERSION = 1 ;
    public static final String TABLE_NAME = "Plants";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_PLANT_NAME = "name";
    public static final String COLUMN_PLANT_CLASSIFICATION1 = "classification1";
    public static final String COLUMN_PLANT_CLASSIFICATION2 = "classification2";
    public static final String COLUMN_PLANT_IMAGE = "image";


    public PlantDBHelper(Context context) {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_PLANT_NAME + " TEXT NOT NULL, " +
                COLUMN_PLANT_CLASSIFICATION1 + " NUMBER NOT NULL, " +
                COLUMN_PLANT_CLASSIFICATION2 + " TEXT NOT NULL, " +
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

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PLANT_NAME, plant.getName());
        values.put(COLUMN_PLANT_CLASSIFICATION1, plant.getClassification1());
        values.put(COLUMN_PLANT_CLASSIFICATION2, plant.getClassification2());
        values.put(COLUMN_PLANT_IMAGE, plant.getImage());

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
        }else{
            //filter results by filter option provided
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
                plant.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_NAME)));
                plant.setClassification1(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_CLASSIFICATION1)));
                plant.setClassification2(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_CLASSIFICATION2)));
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

            receivedPlant.setName(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_NAME)));
            receivedPlant.setClassification1(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_CLASSIFICATION1)));
            receivedPlant.setClassification2(cursor.getString(cursor.getColumnIndex(COLUMN_PLANT_CLASSIFICATION2)));
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
        db.execSQL("UPDATE  "+TABLE_NAME+" SET name ='"+ updatedplant.getName() + "', classification1 ='" + updatedplant.getClassification1()+ "', classification2 ='"+ updatedplant.getClassification2() + "', image ='"+ updatedplant.getImage() + "'  WHERE _id='" + plantId + "'");
        Toast.makeText(context, "Updated successfully.", Toast.LENGTH_SHORT).show();


    }




}