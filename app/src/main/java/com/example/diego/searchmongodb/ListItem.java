package com.example.diego.searchmongodb;

public class ListItem {

    private String keyword, name, email, imageUrl;

    public String getKeyword() {
        return keyword;
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

    public ListItem(String keyword, String name, String email) {
        this.keyword = keyword;
//        this.name = name;
//        this.email = email;
//        this.imageUrl = imageUrl;
    }
}
