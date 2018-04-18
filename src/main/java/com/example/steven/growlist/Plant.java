package com.example.steven.growlist;

public class Plant {

    private long id;
    private String Genus;
    private String Species;
    private String Quantity;
    private String image;
    private String notes;

    public Plant() {
    }

    public Plant(String genus, String species, String quantity, String notes, String image) {
        this.Genus = genus;
        this.Species = species;
        this.Quantity = quantity;
        this.image = image;
        this.notes = notes;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGenus() {
        return Genus;
    }

    public void setGenus(String genus) {
        this.Genus =  genus;
    }

    public String getSpecies() {
        return Species;
    }

    public void setSpecies(String species) {
        this.Species = species;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        this.Quantity = quantity;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNotes() {return notes;}

    public void setNotes(String notes) {this.notes = notes;}
}
