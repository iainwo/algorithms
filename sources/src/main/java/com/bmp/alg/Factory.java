package com.bmp.alg;

import java.lang.reflect.Array;

/**
 * Factory class for creating resources required by algorithms and
 * datastructures. Handles instantiation of generics.
 */
public class Factory<T> {
  
  Class<T> clazz; // for resource instantiation

  /**
   * Privatized constructor because the factory cannot work with out a
   * class type.
   */
  private Factory() {throw new UnsupportedOperationException();}

  /**
   * Creates a factory class for instantiating generically-typed
   * resources.
   * @param clazz the class type to use in instantiation
   */
  public Factory(Class<T> clazz) {
    this.clazz = clazz;
  }
  
  /**
   * Create a new array of this factory's class type.
   * @param n   the size of the array to create
   * @return    the instantiated array
   */
  public T[] newArray(int n) {
    return (T[])Array.newInstance(clazz, n);
  }
}
