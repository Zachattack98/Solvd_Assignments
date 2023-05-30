package com.solvd.lab2.lambdas;

//used in enum: Day
public interface Function<T,V> {
    float apply(T t);
}