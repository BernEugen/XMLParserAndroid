package com.example.XMLParserAndroid;

import android.app.ListActivity;
import android.os.Bundle;
import com.example.XMLParserAndroid.XMLAdapter.XMLAdapter;
import com.example.XMLParserAndroid.XMLModel.XMLModel;
import com.example.XMLParserAndroid.XMLObject.XMLObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MyActivity extends ListActivity {

    private XMLObject xmlObject;
    private XMLAdapter adapter;
    private ArrayList<HashMap<String, String>> xmlParsedList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        xmlObject = new XMLObject();
        try {
            xmlParsedList = xmlObject.readXml(XMLModel.XML_URL);
        } catch (Exception e) {
            e.printStackTrace();
        }

        adapter = new XMLAdapter(this, xmlParsedList);
        setListAdapter(adapter);
    }
}
