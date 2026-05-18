package com.example;

import com.example.controller.RestauranteController;

public class Main {
    public static void main(String[] args) throws Exception {
        try{
            RestauranteController restauranteController = new RestauranteController();
            restauranteController.iniciar();
        }catch (Exception e){
            throw new Exception("Fallo en el controlador");
        }
    }
}
