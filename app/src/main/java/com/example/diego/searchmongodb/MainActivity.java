package com.example.diego.searchmongodb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v7.widget.SearchView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    EditText usernameEditText, nameEditText, emailEditText;
    ArrayList<MyContact> returnValues = new ArrayList<MyContact>();

//if use RecyclerView.adapter , you cannot access any methods that you added to your adapter. IDK why
//    public RecyclerView.Adapter adapter;

    //right way to do it
    public Adapter adapter;

    private RecyclerView searchRecyclerView;
    private List<ListItem> listItems;

    //first try with the mlab, everything on the same collection
    //private static final String URL_DATA = "https://api.mlab.com/api/1/databases/hourtech/collections/hourTech?apiKey=qqa6rAFdcOvgeTYXIRzlwvgXwm31LQpF";

    //second try, keywords in a separated collection
    private static final String URL_DATA = "https://api.mlab.com/api/1/databases/hourtech/collections/keywords?apiKey=qqa6rAFdcOvgeTYXIRzlwvgXwm31LQpF";

    //json for test
    //private static final String URL_DATA = "https://jsonplaceholder.typicode.com/users";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);


        //get the data from mlab, right now it's only getting only one value
        //TODO: REMOVE THIS
//        fetch();


        searchRecyclerView = (RecyclerView) findViewById(R.id.searchRecyclerView);
        searchRecyclerView.setHasFixedSize(true);
        searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        listItems = new ArrayList<>();

        loadRecyclerViewData();

    }

    @Override
    //search icon on top
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);


        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                return false;
            }
        });
        return true;

    }


    private void loadRecyclerViewData() {

        StringRequest stringRequest =
                new StringRequest(Request.Method.GET, URL_DATA, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.e("response from mlab is: ", "" + response);

                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                JSONArray keywords = o.getJSONArray("keywords");

                                for (int k = 0; k < keywords.length(); k++) {
                                    String key = keywords.getString(k);
                                    String test;
                                    ListItem item = new ListItem(
                                            test = key,
                                            test = key,
                                            test = key

                                    );
                                    listItems.add(item);
                                }
                            }

                            adapter = new Adapter(listItems, getApplicationContext());
                            searchRecyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.d("Got exception", e.getMessage());

                        }
                    }
                },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
//                                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_LONG).show();
                                Log.d("Got error", error.getMessage());

                            }
                        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }


    //reset all the textfields, not going to use
    //TODO: REMOVE THIS
    public void reset(View v) {
        usernameEditText.setText("");
        nameEditText.setText("");
        emailEditText.setText("");
        Toast.makeText(this, "Reset done", Toast.LENGTH_SHORT).show();
    }


    //to save on mongo, not going to use on mobile
    //TODO: REMOVE THIS
    public void save(View v) {

        MyContact contact = new MyContact();

        contact.setUsername(usernameEditText.getText().toString());
        contact.setName(nameEditText.getText().toString());
        contact.setEmail(emailEditText.getText().toString());

        MongoLabSaveContact tsk = new MongoLabSaveContact();
        tsk.execute(contact);

        Toast.makeText(this, "Saved to MongoDB!!", Toast.LENGTH_SHORT).show();

        usernameEditText.setText("");
        nameEditText.setText("");
        emailEditText.setText("");
    }

    //first try
    //TODO: REMOVE THIS
    final class MongoLabSaveContact extends AsyncTask<Object, Void, Boolean> {

        @Override
        protected Boolean doInBackground(Object... params) {
            MyContact contact = (MyContact) params[1];
            Log.d("contact", "" + contact);

            try {


                SupportData sd = new SupportData();
                URL url = new URL(sd.buildContactsSaveURL());

                Log.d("url", "" + url);

                HttpURLConnection connection = (HttpURLConnection) url
                        .openConnection();
                connection.setRequestMethod("PUT");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");

                OutputStreamWriter osw = new OutputStreamWriter(
                        connection.getOutputStream());

                osw.write(sd.createContact(contact));
                osw.flush();
                osw.close();

                Log.d("Response code", "" + (connection.getResponseCode()));
                if (connection.getResponseCode() < 205) {

                    return true;
                } else {
                    return false;

                }

            } catch (Exception e) {
                e.getMessage();
                Log.d("Got error", e.getMessage());
                return false;

            }

        }

    }
    
    //TODO: REMOVE THIS
    public void fetch() {

        GetContactsAsyncTask task = new GetContactsAsyncTask();

        try {
            returnValues = task.execute().get();
            MyContact FetchedData = (MyContact) returnValues.toArray()[0];

            usernameEditText.setText(FetchedData.getUsername());
            nameEditText.setText(FetchedData.getName());
            emailEditText.setText(FetchedData.getEmail());

            Toast.makeText(this, "Fetched from MongoDB!!", Toast.LENGTH_SHORT).show();
            Log.e("Fetched from MongoDB!!", "!!");


        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

    }


}