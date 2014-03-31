package com.prolific.swag.app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Danish556 on 3/31/14.
 */
public class AdapterClass extends ArrayAdapter<ListDispRow> {

    private final Context context;
    private final ArrayList<ListDispRow> itemsArrayList;

    public AdapterClass(Context context, ArrayList<ListDispRow> itemsArrayList) {

        super(context, R.layout.row, itemsArrayList);

        this.context = context;
        this.itemsArrayList = itemsArrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

// 1. Create inflater
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

// 2. Get rowView from inflater
        View rowView = inflater.inflate(R.layout.row, parent, false);
        rowView.setId(itemsArrayList.get(position).getList_Disp_id_Row());
// 3. Get the two text view from the rowView
        TextView labelView = (TextView) rowView.findViewById(R.id.label);
        TextView valueView = (TextView) rowView.findViewById(R.id.value);

// 4. Set the text for textView
        labelView.setText(itemsArrayList.get(position).getList_Disp_Tile_Row());
        valueView.setText(itemsArrayList.get(position).getList_Disp_Author_Row());

// 5. retrn rowView
        return rowView;
    }
}


