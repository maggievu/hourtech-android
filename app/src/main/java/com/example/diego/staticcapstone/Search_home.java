package com.example.diego.staticcapstone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class Search_home extends MainActivity implements LoginModal.LoginListener, RegisterModal.RegisterListener {

    private static final String TAG = "Search Home";

    EditText searchInput;
    ImageButton searchBtn;
    Button btn_login;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_home);

        searchInput = findViewById(R.id.searchInput);
        searchBtn = findViewById(R.id.searchBtn);
        btn_login = findViewById(R.id.btn_login);
//TODO: uncoment this line bellow when compile to production
        //        searchInput.setText("");



        //TODO: implements the enter key to submit the search
        searchInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {

                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    Log.e(TAG, "focus no input");
                    onButtonClicked("focus no input");
                    return true;
                }
                return false;
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO: uncoment this line bellow when compile to production

//        searchInput.setText("");
        searchInput.clearFocus();
        searchInput.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) && (i == KeyEvent.KEYCODE_ENTER)) {
                    Log.e(TAG, "focus no input");
                    onButtonClicked("focus no input");
                    return true;
                }
                return false;
            }
        });

    }

    public void loginModal(View view) {
        LoginModal loginmodal = new LoginModal();
        loginmodal.show(getSupportFragmentManager(), "login");

    }
    public void registerModal(View view) {
        RegisterModal registermodal = new RegisterModal();
        registermodal.show(getSupportFragmentManager(), "register");

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