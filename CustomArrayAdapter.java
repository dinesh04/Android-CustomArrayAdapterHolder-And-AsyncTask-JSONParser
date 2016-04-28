package com.example.dineshb.customadapterwithasyncjsonparser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by dineshb on 4/28/2016.
 */
public class CustomArrayAdapter extends ArrayAdapter {
    int count=0;
    public CustomArrayAdapter(Context context, int resource, List objects) {
        super(context, resource, objects);
    }

    private  class Holder{
        TextView mText;
        int i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater=LayoutInflater.from(getContext());
        View customView;
        if(convertView == null){

            customView=inflater.inflate(R.layout.custom_text_view,parent,false);
            String singlename=(String)getItem(position);
            Holder holder =new Holder();
            holder.mText=(TextView) customView.findViewById(R.id.mcustom_name_textview);
            holder.mText.setText(singlename);
            customView.setTag(holder);
        }
        else {
            customView=convertView;
            Holder holder=(Holder)customView.getTag();
            String singlename=(String)getItem(position);
            holder.mText.setText(singlename);
        }

        return customView;

    }
    public void  notifyDataSetChanged() {
        super.notifyDataSetChanged();
    }

    @Override
    public void setNotifyOnChange(boolean notifyOnChange) {
        super.setNotifyOnChange(notifyOnChange);
    }
}
