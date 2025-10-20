package src.main.java.com.example;
import java.io.*;
import java.util.ArrayList;

public class ManejadorArchivo {

    public static ArrayList<Ingrediente> leerArchivoIngredientes(String archivo) throws IOException {
        ArrayList<Ingrediente> ingredientes = new ArrayList<>();
        String linea;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            /*
            String[] formato = reader.readLine().split(",");   
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

    public static ArrayList<Plato> leerArchivoPlatos(String archivo, ArrayList<Ingrediente> ingredientes) throws IOException {
        ArrayList<Plato> platos = new ArrayList<>();
        String linea;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(archivo));
            /*
            String[] formato = reader.readLine().split(",");
            System.out.println("El formato del archivo es: ");
            for(String campo : formato){
                System.out.print(campo + " | ");
            }
            */
            while((linea = reader.readLine()) != null ){
                String[] tokens = linea.split(",");
                ArrayList<Ingrediente> ingredientesPlato = new ArrayList<>();
                ArrayList<Integer> cantidades =  new ArrayList<Integer>();
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
                if((cantidades.isEmpty()) || (cantidades.size() != ingredientesPlato.size()) ){
                    platos.add(new Plato(tokens[0],ingredientesPlato,cantidades));
                }


            }
        } catch (Exception e) {
            throw new   IOException();
        }
        return platos;
    }

}
