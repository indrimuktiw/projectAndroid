package com.example.acer.uts2;

public class Lowker {
    private int id, image, gaji;
    private String title, shortdesc, address;



    public Lowker(int id, String title, String shortdesc, String address, int gaji, int image) {
        this.id = id;
        this.title = title;
        this.shortdesc = shortdesc;
        this.address = address;
        this.gaji = gaji;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id){
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getShortdesc() {
        return shortdesc;
    }

    public String getAddress() {
        return address;
    }

    public int getGaji() {
        return gaji;
    }

    public int getImage() {
        return image;
    }
}
