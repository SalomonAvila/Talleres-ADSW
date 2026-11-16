package com.example.controller;

import com.example.utils.ManejadorArchivo;
import com.example.model.Restaurante;

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
        try{
            restaurante.setIngredientes(ManejadorArchivo.leerArchivoIngredientesJSON("src/main/resources/ingredientes.json"));
            restaurante.setPlatos(ManejadorArchivo.leerArchivoPlatosJSON("src/main/resources/platos.json",restaurante.getIngredientes()));
        }catch (Exception e) {
            throw new Exception("Error leyendo alguno de los archivos JSON");
        }
        int opcion = 0;
        while(opcion != -1) {
            int i = 0;
            for (i = 0; i < restaurante.getPlatos().size(); i++) {
                System.out.println((i + 1) + ". " + restaurante.getPlatos().get(i).getNombre());
            }
            System.out.println((i + 1) + ". Salir");

            opcion = sc.nextInt();
            if (opcion > 0 && opcion <= restaurante.getPlatos().size()) {
                System.out.println("Cuantas unidades? ");
                int unidades = sc.nextInt();
                restaurante.registrarVenta(restaurante.getPlatos().get(opcion - 1).getNombre(), unidades);
            } else {
                opcion = -1;
            }
        }
        ManejadorArchivo.escribirVentasJSON(restaurante.getPlatos(),"src/main/resources/resultados.json");
    }
}