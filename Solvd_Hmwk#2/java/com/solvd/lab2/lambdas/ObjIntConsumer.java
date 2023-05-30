package com.solvd.lab2.lambdas;

//Used in classes: Diagnostic
public interface ObjIntConsumer<T> {
    void accept(T ref, int value); //T represents any type while ref represents
    //reference and value represents the integer
    //that will be checked within List.
}