package imd.ufrn.br.patinsmania.data;

import java.io.Serializable;

public class Patins implements Serializable {
    private int id;
    private String brand;
    private String model;
    private int size;

    public Patins(int id, String brand, String model, int size) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.size = size;
    }

    public Patins(String brand, String model, int size) {
        this.brand = brand;
        this.model = model;
        this.size = size;
    }

    public int getId() { return id; }

    public void setId(int id) {
        this.id = id;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
