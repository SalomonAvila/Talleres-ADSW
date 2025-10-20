package src.main.java.com.example.model;
import java.util.ArrayList;


public class Plato {
    private String nombre;
    private ArrayList<Ingrediente> ingredientes;
    private ArrayList<Integer> cantidades;
    private int precio = 0;
    private int ventas = 0;

    public Plato(String nombre, ArrayList<Ingrediente> ingredientes, ArrayList<Integer> cantidades) {
        setNombre(nombre);
        if((ingredientes.size() == cantidades.size()) && (!ingredientes.isEmpty())) {
            setIngredientes(ingredientes);
            setCantidades(cantidades);
        }
        calcularPrecio();
    }

    private void calcularPrecio() {
        for(Ingrediente ingrediente : ingredientes) {
            precio  += ingrediente.getPrecio();
        }
        precio *= 2;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIngredientes(ArrayList<Ingrediente> ingredientes) {
        this.ingredientes = ingredientes;
    }

    public void setCantidades(ArrayList<Integer> cantidades) {
        this.cantidades = cantidades;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setVentas(int ventas) {this.ventas = ventas;}

    public String getNombre() {
        return nombre;
    }

    public ArrayList<Ingrediente> getIngredientes() {
        return ingredientes;
    }

    public ArrayList<Integer> getCantidades() {
        return cantidades;
    }

    public int getPrecio() {
        return precio;
    }

    public int getVentas() {return ventas;}

    public void aumentarVenta(int cantidad){
        ventas += cantidad;
    }

    public int calcularGanancia(){
        return (precio/2)*ventas;
    }

    @Override
    public String toString() {
        return String.format("""
                --------------
                %s
                Precio: %d
                Ingredientes:
                %s
                """, nombre,precio, ingredientes);
    }
}
