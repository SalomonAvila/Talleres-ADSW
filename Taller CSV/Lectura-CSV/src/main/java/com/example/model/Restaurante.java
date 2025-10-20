package src.main.java.com.example.model;

import java.util.ArrayList;

public class Restaurante {
    private String nombre;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<Plato> platos;

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public ArrayList<Plato> getPlatos() {
        return platos;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setPlatos(ArrayList<Plato> platos) {
        this.platos = platos;
    }
}
