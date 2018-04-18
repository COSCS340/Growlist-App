package com.example.steven.growlist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.media.ThumbnailUtils;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import com.squareup.picasso.Picasso;
import java.io.File;
import java.io.IOException;
import java.util.List;

import com.example.steven.growlist.R;
import com.example.steven.growlist.UpdateRecordActivity;
import com.example.steven.growlist.Plant;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {
    private List<Plant> mPlantsList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView plantGenusTxtV;
        public TextView plantSpeciesTxtV;
        public TextView plantQuantityTxtV;
        public ImageView plantImageImgV;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            plantGenusTxtV = (TextView) v.findViewById(R.id.Genus);
            plantSpeciesTxtV = (TextView) v.findViewById(R.id.Species);
            plantQuantityTxtV = (TextView) v.findViewById(R.id.Quantity);
            plantImageImgV = (ImageView) v.findViewById(R.id.image);




        }
    }

    public void add(int position, Plant plant) {
        mPlantsList.add(position, plant);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mPlantsList.remove(position);
        notifyItemRemoved(position);
    }



    // Provide a suitable constructor (depends on the kind of dataset)
    public PlantAdapter(List<Plant> myDataset, Context context, RecyclerView recyclerView) {
        mPlantsList = myDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public PlantAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,int viewType) {
        // create a new view
        LayoutInflater inflater = LayoutInflater.from(
                parent.getContext());
        View v =
                inflater.inflate(R.layout.single_row, parent, false);
        // set the view's size, margins, paddings and layout parameters
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element

        final Plant plant = mPlantsList.get(position);
        holder.plantGenusTxtV.setText("Genus: " + plant.getGenus());
        holder.plantSpeciesTxtV.setText("Species: " + plant.getSpecies());
        holder.plantQuantityTxtV.setText("Quantity: " + plant.getQuantity());
        String ImagePath = plant.getImage();
        Bitmap bitmap;
        int targetW = 100;//holder.plantImageImgV.getWidth();
        int targetH = 100;//holder.plantImageImgV.getHeight();
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        bmOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(ImagePath, bmOptions);
        int photoW = bmOptions.outWidth;
        int photoH = bmOptions.outHeight;
        int scaleFactor = Math.min(photoW/targetW, photoH/targetH);

        bmOptions.inJustDecodeBounds = false;
        bmOptions.inSampleSize = scaleFactor;
        bmOptions.inPurgeable = true;
        Matrix matrix = new Matrix();
        int orientation = 0;
        try {
            ExifInterface exif = new ExifInterface(ImagePath);
            orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,1);
            if (orientation == 1) orientation = 0;
            else if (orientation == 3) orientation = 180;
            else if (orientation == 6) orientation = 90;
            else if (orientation == 8) orientation = 270;
            Log.d("Orientation: ", Integer.toString(orientation));
        } catch (IOException e){
            e.printStackTrace();
        }
        matrix.postRotate(orientation);
        bitmap = BitmapFactory.decodeFile(ImagePath,bmOptions);
        bitmap = Bitmap.createBitmap(bitmap,0,0, bitmap.getWidth(),
                bitmap.getHeight(),matrix,true);
        holder.plantImageImgV.setImageBitmap(bitmap);

        //Picasso.with(mContext).load(new File(plant.getImage())).into(holder.plantImageImgV);
        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ViewPlant = new Intent(mContext, ViewPlant.class);
                ViewPlant.putExtra("USER_ID", plant.getId());
                mContext.startActivity(ViewPlant);
            }
        });
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setTitle("Choose option");
                builder.setMessage("Update or delete plant?");
                builder.setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        //go to update activity
                        goToUpdateActivity(plant.getId());

                    }
                });
                builder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        PlantDBHelper dbHelper = new PlantDBHelper(mContext);
                        dbHelper.deletePlantRecord(plant.getId(), mContext);

                        mPlantsList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mPlantsList.size());
                        notifyDataSetChanged();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                builder.create().show();
                return true;
            }
        });


    }

    private void goToUpdateActivity(long plantId){
        Intent goToUpdate = new Intent(mContext, UpdateRecordActivity.class);
        goToUpdate.putExtra("USER_ID", plantId);
        mContext.startActivity(goToUpdate);
    }



    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mPlantsList.size();
    }



}
