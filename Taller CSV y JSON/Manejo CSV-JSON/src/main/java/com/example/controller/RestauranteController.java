package src.main.java.com.example.controller;

import src.main.java.com.example.utils.ManejadorArchivo;
import src.main.java.com.example.model.Restaurante;

import java.util.Scanner;

public class RestauranteController {

    private Restaurante restauranteCSV;
    private Restaurante restauranteJSON;


    public void iniciar() throws Exception {
        restauranteCSV = new Restaurante();
        restauranteJSON = new Restaurante();
        Scanner sc = new Scanner(System.in);
        String nombre;
        System.out.println("Ingrese el nombre del restaurante: ");
        nombre = sc.nextLine();
        restauranteCSV.setNombre(nombre);
        try {
            restauranteCSV.setIngredientes(ManejadorArchivo.leerArchivoIngredientesCSV("src/main/resources/ingredientes.csv"));
            restauranteCSV.setPlatos(ManejadorArchivo.leerArchivoPlatosCSV("src/main/resources/platos.csv",restauranteCSV.getIngredientes()));
        }catch (Exception e) {
            throw new Exception("Error leyendo alguno de los archivos CSV");
        }
        try{
            restauranteJSON.setIngredientes(ManejadorArchivo.leerArchivoIngredientesJSON("src/main/resources/ingredientes.json"));
            restauranteJSON.setPlatos(ManejadorArchivo.leerArchivoPlatosJSON("src/main/resources/platos.json",restauranteJSON.getIngredientes()));
        }catch (Exception e) {
            throw new Exception("Error leyendo alguno de los archivos JSON");
        }
        if(restauranteCSV.getIngredientes().size() != restauranteJSON.getIngredientes().size()){
            throw new Exception("La cantidad de ingredientes es diferente");
        }
        if(restauranteCSV.getPlatos().size() != restauranteJSON.getPlatos().size()){
            throw new Exception("La cantidad de platos es diferente");
        }
        int opcion = 0;
        while(opcion != -1) {
            int i = 0;
            for (i = 0; i < restauranteJSON.getPlatos().size(); i++) {
                System.out.println((i + 1) + ". " + restauranteJSON.getPlatos().get(i).getNombre());
            }
            System.out.println((i + 1) + ". Salir");

            opcion = sc.nextInt();
            if (opcion > 0 && opcion <= restauranteJSON.getPlatos().size()) {
                System.out.println("Cuantas unidades? ");
                int unidades = sc.nextInt();
                restauranteJSON.registrarVenta(restauranteJSON.getPlatos().get(opcion - 1).getNombre(), unidades);
                restauranteCSV.registrarVenta(restauranteCSV.getPlatos().get(opcion - 1).getNombre(), unidades);
            } else {
                opcion = -1;
            }
        }
        ManejadorArchivo.escribirVentasCSV(restauranteCSV.getPlatos(),"src/main/resources/resultados.csv");
        ManejadorArchivo.escribirVentasJSON(restauranteJSON.getPlatos(),"src/main/resources/resultados.json");
    }
}
