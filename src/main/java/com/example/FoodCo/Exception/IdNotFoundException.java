package com.example.FoodCo.Exception;

public class IdNotFoundException extends Exception{
    public IdNotFoundException(String errorMessage){
        super(errorMessage);
    }
}
