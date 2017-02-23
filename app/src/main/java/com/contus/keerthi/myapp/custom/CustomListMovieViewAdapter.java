package com.contus.keerthi.myapp.custom;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.contus.keerthi.myapp.POJO.MovieRowItem;
import com.contus.keerthi.myapp.R;

import java.util.List;

/**
 * Created by user on 8/2/17.
 */

public class CustomListMovieViewAdapter extends ArrayAdapter<MovieRowItem> {

    Context context;

    public CustomListMovieViewAdapter(Context context, int resourceId, List<MovieRowItem> items)
    {
        super(context,resourceId,items);
        this.context =context;
    }

    private class ViewHolder {

        TextView txtTitle,txtDesc;
    }

    public View getView(int position,View convertView,ViewGroup parent)
    {
        ViewHolder holder = null;
        MovieRowItem movieRowItem =getItem(position);

        LayoutInflater mInflator = (LayoutInflater)context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if(convertView == null){

            convertView = mInflator.inflate(R.layout.movie_list_item,null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView)convertView.findViewById(R.id.number);
            holder.txtTitle = (TextView)convertView.findViewById(R.id.name);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder)convertView.getTag();
        }

        holder.txtDesc.setText(movieRowItem.getDesc());
        holder.txtTitle.setText(movieRowItem.getTitle());


        return convertView;
    }
}
