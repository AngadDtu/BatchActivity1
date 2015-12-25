package com.codingblocks.stocks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by nagarro on 05/09/15.
 */
public class StudentArrayAdapter extends ArrayAdapter<Student> {

    LayoutInflater inflater;

    public StudentArrayAdapter(Context context, List<Student> objects, LayoutInflater inflater) {
        super(context, 0, objects);
        this.inflater = inflater;
    }

    public static class ViewHolder {
        TextView rtv;
        TextView ltv;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null){
            convertView = inflater.inflate(R.layout.list_view_item, null);
            TextView rtv = (TextView) convertView.findViewById(R.id.rightTextView);
            TextView ltv = (TextView) convertView.findViewById(R.id.leftTextView);
            ViewHolder vh = new ViewHolder();
            vh.ltv = ltv;
            vh.rtv = rtv;
            convertView.setTag(vh);
        }
        ViewHolder vh = (ViewHolder)convertView.getTag();
        vh.rtv.setText("r" + getItem(position).batchName);
        vh.ltv.setText("l" + getItem(position).name);
        return convertView;
    }
}
