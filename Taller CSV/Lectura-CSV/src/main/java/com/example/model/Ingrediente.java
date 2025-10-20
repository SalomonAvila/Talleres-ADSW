package src.main.java.com.example.model;

public class Ingrediente {
    private String nombre;
    private int precio;
    private String medidaPorUnidad;

    public Ingrediente(String nombre, int precio, String medidaPorUnidad) {
        setNombre(nombre);
        setPrecio(precio);
        setMedidaPorUnidad(medidaPorUnidad);
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public void setMedidaPorUnidad(String medidaPorUnidad) {
        this.medidaPorUnidad = medidaPorUnidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public String getMedidaPorUnidad() {
        return medidaPorUnidad;
    }

    @Override
    public String toString() {
        return String.format("""
                \n
                --------------------
                %s
                Precio: %d
                Medida: %s
                --------------------
                """,nombre,precio,medidaPorUnidad);
    }
}
