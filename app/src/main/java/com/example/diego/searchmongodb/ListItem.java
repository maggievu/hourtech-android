package com.example.diego.searchmongodb;

public class ListItem {

    private String username, name, email, imageUrl;

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

//    public String getImageUrl() {
//        return imageUrl;
//    }

    public ListItem(String username, String name, String email) {
        this.username = username;
//        this.name = name;
//        this.email = email;
//        this.imageUrl = imageUrl;
    }
}
