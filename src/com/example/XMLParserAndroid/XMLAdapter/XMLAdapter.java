package com.example.XMLParserAndroid.XMLAdapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.XMLParserAndroid.R;
import com.example.XMLParserAndroid.XMLModel.XMLModel;
import com.example.XMLParserAndroid.XMLObject.XMLObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.Inflater;

public class XMLAdapter extends BaseAdapter {

    private ArrayList<HashMap<String, String>> xmlDataMap;
    private Activity contex;

    public XMLAdapter(Activity contex, ArrayList<HashMap<String, String>> itemMap) {
        this.contex = contex;
        xmlDataMap = itemMap;
    }

    @Override
    public int getCount() {
        return xmlDataMap.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        if(convertView == null) {
            LayoutInflater layoutInflater = contex.getLayoutInflater();
            view = layoutInflater.inflate(R.layout.item_list_view, null);
        }

        HashMap<String, String> oneRowMap;
        oneRowMap = xmlDataMap.get(position);

        TextView link = (TextView) view.findViewById(R.id.link);
        TextView title = (TextView) view.findViewById(R.id.title);
        TextView time = (TextView) view.findViewById(R.id.time);

        link.setText("LINK: " + oneRowMap.get(XMLModel.LINK));
        title.setText("TITLE: " + oneRowMap.get(XMLModel.TITLE));
        time.setText(oneRowMap.get(XMLModel.TIME));

        return view;

    }
}

























