// TODO: delete this file, not goint to use I guess - 26/02





package com.example.diego.searchmongodb;

import android.os.AsyncTask;
import android.util.Log;

import com.mongodb.BasicDBList;
import com.mongodb.DBObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;


public class GetContactsAsyncTask extends AsyncTask<MyContact, Void, ArrayList<MyContact>> {
    static String server_output = null;
    static String temp_output = null;

    @Override
    protected ArrayList<MyContact> doInBackground(MyContact... arg0) {

        ArrayList<MyContact> mycontacts = new ArrayList<MyContact>();

        try
        {
            SupportData sd = new SupportData();
            URL url = new URL(sd.buildContactsFetchURL());
            HttpURLConnection conn = (HttpURLConnection) url
                    .openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");



            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());


            }

            BufferedReader br = new BufferedReader(new InputStreamReader(
                    (conn.getInputStream())));

            while ((temp_output = br.readLine()) != null) {
                server_output = temp_output;
            }

            // create a basic db list
            String mongoarray = "{ DB_output: "+server_output+"}";
            Object o = com.mongodb.util.JSON.parse(mongoarray);


            DBObject dbObj = (DBObject) o;
            BasicDBList contacts = (BasicDBList) dbObj.get("DB_output");
            Log.e("return from mongo",""+ contacts );


            for (Object obj : contacts) {

                //here supposed to be the place to put the itens from mongo into the components, but I DON'T KNOW HOW TO DO IT!!! FUCK
                DBObject userObj = (DBObject) obj;
                Log.e("value is", ""+ obj);

                MyContact temp = new MyContact();
                temp.setUsername(userObj.get("username").toString());
                temp.setName(userObj.get("name").toString());
                temp.setEmail(userObj.get("email").toString());
                mycontacts.add(temp);

            }

        }catch (Exception e) {
            e.getMessage();
        }

        return mycontacts;
    }
}