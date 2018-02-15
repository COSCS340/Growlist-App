package comcoscs340growlist_app.httpsgithub.growlist;

/**
 * Created by awilk on 2/15/2018.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class growlistAdapter extends ArrayAdapter<String>{
    public growlistAdapter(Context context, String[] plants) {
        super(context, R.layout.growlist_row ,plants);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // default -  return super.getView(position, convertView, parent);
        // add the layout
        LayoutInflater myCustomInflater = LayoutInflater.from(getContext());
        View customView = myCustomInflater.inflate(R.layout.growlist_row, parent, false);
        // get references.
        String singlePlant = getItem(position);
        TextView itemText = (TextView) customView.findViewById(R.id.item_text);
        ImageView plantImage = (ImageView) customView.findViewById(R.id.plantpictureID);

        // dynamically update the text from the array
        itemText.setText(singlePlant);
        // using the same image every time
        plantImage.setImageResource(R.drawable.plantpicture);
        // Now we can finally return our custom View or custom item
        return customView;
    }
}