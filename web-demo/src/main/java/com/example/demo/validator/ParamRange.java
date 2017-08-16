package com.example.demo.validator;

import java.util.Random;


public interface ParamRange<T> {

    Random rdm = new Random();

    boolean isInRange(T value);

    String getDesc();

    String getBaseSample();

    boolean isCompatible(Class<?> type);
}
