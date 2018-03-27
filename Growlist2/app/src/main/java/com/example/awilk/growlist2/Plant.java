package com.example.awilk.growlist2;

/**
 * Created by awilk on 3/27/2018.
 */

public class Plant {

    private long id;
    private String name;
    private String classification1;
    private String classification2;
    private String image;

    public Plant() {
    }

    public Plant(String name, String classification1, String classification2, String image) {
        this.name = name;
        this.classification1 = classification1;
        this.classification2 = classification2;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassification1() {
        return classification1;
    }

    public void setClassification1(String classification1) {
        this.classification1 = classification1;
    }

    public String getClassification2() {
        return classification2;
    }

    public void setClassification2(String classification2) {
        this.classification2 = classification2;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}