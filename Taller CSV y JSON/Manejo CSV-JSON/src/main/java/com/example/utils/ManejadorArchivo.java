package src.main.java.com.example.utils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import src.main.java.com.example.model.Ingrediente;
import src.main.java.com.example.model.Plato;
import org.json.simple.JSONArray;

import java.io.*;
import java.util.ArrayList;

public class ManejadorArchivo {

    public static ArrayList<Ingrediente> leerArchivoIngredientesCSV(String archivo) throws IOException {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        String linea;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String[] formato = reader.readLine().split(","); 
            /*
            System.out.println("El formato del archivo es: ");
            for(String campo : formato){
                System.out.print(campo + " | ");
            }
            */
            while((linea = reader.readLine()) != null ){
                String[] tokens = linea.split(",");
                Ingrediente ingrediente = new Ingrediente(tokens[0],Integer.parseInt(tokens[1]),tokens[2]);
                ingredientes.add(ingrediente);
            }
        } catch (Exception e) {
            throw new  IOException();
        }
        return ingredientes;
    }

    public static ArrayList<Plato> leerArchivoPlatosCSV(String archivo, ArrayList<Ingrediente> ingredientes) throws IOException {
        ArrayList<Plato> platos = new ArrayList<>();
        String linea;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            String[] formato = reader.readLine().split(",");
            /*
            System.out.println("El formato del archivo es: ");
            for(String campo : formato){
                System.out.print(campo + " | ");
            }
            */
            while((linea = reader.readLine()) != null ){
                String[] tokens = linea.split(",");
                ArrayList<Ingrediente> ingredientesPlato = new ArrayList<>();
                ArrayList<Integer> cantidades =  new ArrayList<>();
                int cantidadPlatos = Integer.parseInt(tokens[1]);
                int index = 2;
                for(int i = 0; i<cantidadPlatos; i++){
                    for(Ingrediente ingrediente : ingredientes){
                        if(ingrediente.getNombre().equalsIgnoreCase(tokens[index])){
                            ingredientesPlato.add(ingrediente);
                            index++;
                            cantidades.add(Integer.parseInt(tokens[index]));
                            index++;
                            break;
                        }
                    }
                }
                if((!cantidades.isEmpty()) && (cantidades.size() == ingredientesPlato.size()) ){
                    platos.add(new Plato(tokens[0],ingredientesPlato,cantidades));
                }


            }
        } catch (Exception e) {
            throw new IOException();
        }
        return platos;
    }

    public static void escribirVentasCSV(ArrayList<Plato> platos, String archivo) throws IOException {
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(archivo));
            String linea = "NOMBRE DEL PLATO,VENTAS,GANANCIA DEL PLATO";
            writer.write(linea);
            writer.newLine();
            for(Plato plato : platos) {
                writer.write(plato.getNombre()+","+plato.getVentas()+","+plato.calcularGanancia());
                writer.newLine();
            }
            writer.close();
        }catch (Exception e){
            throw new IOException("Error al escribir ventas");
        }
    }

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
