package com.programer.nivin.rmdr;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nivin Vincent on 9/24/2017.
 */
public class CustomAdapter extends ArrayAdapter {
    private List<Remind> datalist;
    Context c;
    private int resource_name;
    private LayoutInflater inflater;



    public CustomAdapter(Context context, int resource, List<Remind> objects) {
        super(context, resource, objects);
        datalist=objects;
        resource_name=resource;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {



        if(convertView == null){
            convertView = inflater.inflate(resource_name,null);
        }

            TextView text_name, text_date, categ, certi;
            Button btn;

            text_name = (TextView) convertView.findViewById(R.id.detail_name);
            text_date = (TextView) convertView.findViewById(R.id.detail_date);
            categ = (TextView) convertView.findViewById(R.id.detail_cat);
            certi = (TextView) convertView.findViewById(R.id.detail_cert);
            btn = (Button) convertView.findViewById(R.id.detail_btn_delete);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int id = datalist.get(position).getId();
                    String namee = datalist.get(position).getName();
                    datalist.remove(position);
                    notifyDataSetChanged();
                    Intent in = new Intent("nivin_del");
                    in.putExtra("id", id);
                    getContext().sendBroadcast(in);

                    Log.i("ID : ", "" + id);
                    Log.i("Namee : ", namee);


                }
            });
       /* text_name.setTypeface(typeface);
        text_date.setTypeface(typeface);
        categ.setTypeface(typeface);
        certi.setTypeface(typeface);*/

            text_name.setText(datalist.get(position).getName());
            text_date.setText(datalist.get(position).getDate());
            categ.setText(datalist.get(position).getCategory());
            certi.setText(datalist.get(position).getCertificate());
            //notifyDataSetChanged();


        return convertView;
    }


}







































     /*LayoutInflater inflater;
        Context context;
    ArrayList<Remind> list_data;
    int resource;*/
    /*public CustomAdapter(Context context,int resource,ArrayList<Remind> list) {
        super(context, resource, list);
        this.context=context;
        this.resource=resource;
        list_data=list;

    }*/






   /* @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);
        TextView textView_name = (TextView) view.findViewById(R.id.detail_name);
        TextView textView_date = (TextView) view.findViewById(R.id.detail_date);
        Button buttonDelete = (Button) view.findViewById(R.id.detail_btn_delete);

        Remind remind =list_data.get(position);
        textView_name.setText(remind.getName());
        textView_date.setText(remind.getDate());
        return null;
    }*/

