package src.main.java.com.example.controller;

import src.main.java.com.example.utils.ManejadorArchivo;
import src.main.java.com.example.model.Restaurante;

import java.util.Scanner;

public class RestauranteController {

    private Restaurante restaurante;

    public void iniciar() throws Exception {
        restaurante = new Restaurante();
        Scanner sc = new Scanner(System.in);
        String nombre;
        System.out.println("Ingrese el nombre del restaurante: ");
        nombre = sc.nextLine();
        restaurante.setNombre(nombre);
        try {
            restaurante.setIngredientes(ManejadorArchivo.leerArchivoIngredientes("src/main/resources/ingredientes.csv"));
            restaurante.setPlatos(ManejadorArchivo.leerArchivoPlatos("src/main/resources/platos.csv",restaurante.getIngredientes()));
        }catch (Exception e) {
            throw new Exception("Error leyendo alguno de los archivos");
        }
        System.out.println("Bienvenido a " + restaurante.getNombre());
        System.out.println("Se cargaron con exito " + restaurante.getPlatos().size() + " platos");
        System.out.println("Se cargaron con exito " + restaurante.getIngredientes().size() + " ingredientes");
    }
}
