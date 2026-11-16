package com.example.utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import com.example.model.Ingrediente;
import com.example.model.Plato;
import org.json.simple.JSONArray;

import java.io.*;
import java.util.ArrayList;

public class ManejadorArchivo {

    public static ArrayList<Ingrediente> leerArchivoIngredientesJSON(String archivo) throws IOException{
        ArrayList<Ingrediente> ingredientes = new ArrayList<Ingrediente>();
        try{
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(archivo));
            for(Object objeto :  jsonArray){
                JSONObject ingrediente = (JSONObject) objeto;
                Long precio = (Long) ingrediente.get("precio");
                Ingrediente ingredienteUnico = new Ingrediente((String)ingrediente.get("nombre"),precio.intValue(),(String)ingrediente.get("medidaPorUnidad"));
                ingredientes.add(ingredienteUnico);
            }
        }catch(Exception e){
            throw new IOException();
        }
        return ingredientes;
    };

    public static ArrayList<Plato> leerArchivoPlatosJSON(String archivo, ArrayList<Ingrediente> ingredientes) throws IOException{
        ArrayList<Plato> platos = new ArrayList<>();
        try{
            JSONParser parser = new JSONParser();
            JSONArray jsonArray = (JSONArray) parser.parse(new FileReader(archivo));
            for(Object objeto :  jsonArray){
                JSONObject plato = (JSONObject) objeto;
                ArrayList<Ingrediente> ingredientesPlato = new ArrayList<>();
                for(Object nombreIngrediente : (JSONArray)plato.get("ingredientes")){
                    for(Ingrediente ingrediente : ingredientes) {
                        if (ingrediente.getNombre().equalsIgnoreCase(nombreIngrediente.toString())) {
                            ingredientesPlato.add(ingrediente);
                            break;
                        }
                    }
                }
                ArrayList<Integer> cantidades =  new ArrayList<>();
                for(Object cantidad : (JSONArray)plato.get("cantidades")){
                    Long cantidadNumero =  (Long)cantidad;
                    cantidades.add(cantidadNumero.intValue());
                }
                platos.add(new Plato((String)plato.get("nombre"),ingredientesPlato,cantidades));
            }
            return platos;
        }catch(Exception e){
            throw new IOException();
        }
    }

    public static void escribirVentasJSON(ArrayList<Plato> platos, String archivo) throws IOException{
        try{
            JSONArray elementos = new JSONArray();
            JSONArray detalles = new JSONArray();
            int ventasTotales = 0, gananciasTotales = 0;
            for(Plato plato : platos) {
                JSONObject entrada = new JSONObject();
                entrada.put("nombre",plato.getNombre());
                entrada.put("ventas",plato.getVentas());
                ventasTotales += plato.getVentas();
                entrada.put("ganancia",plato.calcularGanancia());
                gananciasTotales += plato.calcularGanancia();
                detalles.add(entrada);
            }
            elementos.add(detalles);
            JSONObject totales = new JSONObject();
            totales.put("ventasTotales",ventasTotales);
            totales.put("gananciasTotales",gananciasTotales);
            elementos.add(totales);
            FileWriter writer = new FileWriter(archivo);
            writer.write(elementos.toString());
            writer.close();
        }catch(Exception e){
            throw new IOException();
        }
    }
}