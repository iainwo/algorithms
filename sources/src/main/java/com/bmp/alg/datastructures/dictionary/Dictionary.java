package com.bmp.alg.datastructure.dictionary;

/**
 * An dictionary-style interface stipulating fundamental search, insert and
 * delete operations.
 *
 * @version   %I%, %G%
 * @author    Iain Wong
 * @since     JDK1.9
 */
public interface Dictionary<T> {

  /**
   * Inserts an element into the collection.
   * @param x the element to insert
   * @return true if the op is successful
   */
  boolean insert(T x);

  /**
   * Search for the element in the Dictionary.
   * @param x search for an object equivalent to this param in the Dictionary
   * @return the Dictionary's version of the object if found.
   */
  T search(T x);

  /**
   * Delete the equivalent element found in the Dictionary.
   * @param x search for an object equivalent to this param in the Dictionary
   * @return the Dictionary's version, which was deleted.
   */
  T delete(T x);
}
