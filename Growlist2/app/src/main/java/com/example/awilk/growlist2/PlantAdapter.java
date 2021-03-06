package com.example.awilk.growlist2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import com.example.awilk.growlist2.R;
import com.example.awilk.growlist2.UpdateRecordActivity;
import com.example.awilk.growlist2.Plant;

public class PlantAdapter extends RecyclerView.Adapter<PlantAdapter.ViewHolder> {
    private List<Plant> mPlantsList;
    private Context mContext;
    private RecyclerView mRecyclerV;


    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView plantNameTxtV;
        public TextView plantClassification1TxtV;
        public TextView plantClassification2TxtV;
        public ImageView plantImageImgV;


        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            plantNameTxtV = (TextView) v.findViewById(R.id.name);
            plantClassification1TxtV = (TextView) v.findViewById(R.id.classification1);
            plantClassification2TxtV = (TextView) v.findViewById(R.id.classification2);
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
        holder.plantNameTxtV.setText("Name: " + plant.getName());
        holder.plantClassification1TxtV.setText("Classification1: " + plant.getClassification1());
        holder.plantClassification2TxtV.setText("Classification2: " + plant.getClassification2());
        Picasso.with(mContext).load(plant.getImage()).placeholder(R.mipmap.ic_launcher).into(holder.plantImageImgV);

        //listen to single view layout click
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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