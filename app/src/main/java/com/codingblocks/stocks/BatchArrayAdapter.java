package com.codingblocks.stocks;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by nagarro on 06/09/15.
 */
public class BatchArrayAdapter extends ArrayAdapter<Batch> {

    Context context;

    public BatchArrayAdapter(Context context, List<Batch> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    public static class BatchViewHolder {
        TextView batchName;
        TextView courseName;
        TextView seats;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.batch_item_layout, null);
            BatchViewHolder vh = new BatchViewHolder();
            vh.batchName = (TextView)convertView.findViewById(R.id.batchName);
            vh.courseName = (TextView)convertView.findViewById(R.id.courseName);
            vh.seats = (TextView)convertView.findViewById(R.id.seats);
            convertView.setTag(vh);
        }

        Batch b = getItem(position);
        BatchViewHolder vh = (BatchViewHolder)convertView.getTag();
        vh.seats.setText(b.currentlyFilled + "/" + b.capacity);
        vh.courseName.setText(b.courseName);
        vh.batchName.setText(b.name);
        if (b.isOpen) {
            convertView.setBackgroundColor(Color.WHITE);
        } else {
            convertView.setBackgroundColor(Color.LTGRAY);
        }
        return convertView;
    }
}
