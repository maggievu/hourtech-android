package com.example.diego.staticcapstone;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Search_home extends MainActivity implements LoginModal.LoginListener {


    EditText searchInput;
    ImageButton searchBtn;
    Button btn_login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_home);

        searchInput = findViewById(R.id.searchInput);
        searchBtn = findViewById(R.id.searchBtn);
        btn_login = findViewById(R.id.btn_login);
        searchInput.setText("");


    }

    @Override
    protected void onResume() {
        super.onResume();
        searchInput.setText("");
        searchInput.clearFocus();
    }
 public void loginModal(View view){
     LoginModal loginmodal = new LoginModal();
     loginmodal.show(getSupportFragmentManager(), "login");

 }
    public void afterLogin(View view) {

        String search = searchInput.getText().toString();
        //start the intent to load the search screen
        Intent intent = new Intent(getApplicationContext(), Search.class);
        if (search != null && search.isEmpty()) {
            Toast.makeText(this, "Please Type Something", Toast.LENGTH_SHORT).show();
        } else {
            intent.putExtra("querySearch", search);
            startActivity(intent);
        }


    }


    @Override
    public void onButtonClicked(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}