package com.example;

import com.example.controller.RestauranteController;

public class Main {
    public static void main(String[] args) {
        try{
            RestauranteController restauranteController = new RestauranteController();
            restauranteController.iniciar();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
