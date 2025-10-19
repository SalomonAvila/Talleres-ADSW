package src.main.java.com.example;
import java.util.ArrayList;

public class Restaurante {
    public static void main(String[] args) throws Exception {
        //ArrayList para guardar los ingredientes
        ArrayList<Ingrediente> ingredientes;
        try {
            ingredientes = ManejadorArchivo.leerArchivoIngredientes("src/main/resources/ingredientes.csv");
        }catch (Exception e) {
            throw new Exception("Error en el archivo del ingrediente");
        }

        //ArrayList para guardar los platos
        ArrayList<Plato> platos;
        try{
            platos = ManejadorArchivo.leerArchivoPlatos("src/main/resources/platos.csv",ingredientes);
        }catch (Exception e) {
            throw new Exception("Error en el archivo del plato");
        }

        System.out.println("Se cargaron con exito " + platos.size() + " platos");
        System.out.println("Se cargaron con exito " + ingredientes.size() + " ingredientes");


    }
}
