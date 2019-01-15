package com.bmp.alg.datastructure.dictionary;

import com.bmp.alg.Factory;

/**
 * This class is backed by an array.
 * <p>
 * That array has a bijective relationship
 * with all the elements in Universe `U` - which is also bijective with
 * all the keys of Universe `U`. Each element in `U` has a unique key.
 * <p>
 * The DirectAddressTable class has this asymptotic analysis
 * <ul>
 * <li>worst case insert: O(1)
 * <li>worst case search: O(1)
 * <li>worst case delete: O(1)
 * </ul>
 * <p>
 * Also note that the implementation stores the actual inserted object, 
 * rather than the key or satellite reference to that Object.
 *
 * @version   %I%, %G%
 * @author    Iain Wong
 * @since     JDK1.9
 * @see       Dictionary
 */
public class DirectAddressTable<T> implements Dictionary<T> {

  /**
   * object used to signify "null" elements
   */
  protected T nil;
  
  /**
   * number of elements in the table
   */
  private int n;

  /**
   * The backing array
   */
  private T[] a;

  /**
   * Factory for instantiating collections of this classes typified generic.
   */
  protected Factory<T> f;

  /**
   * Create a empty DirectAddressTable object.
   * @param clazz the class type of data
   */
  public DirectAddressTable(Class<T> clazz) {
    f = new Factory<T>(clazz);

    // by instantiating the backing array, 
    // reduces the number of null-check edge cases
    a = f.newArray(1);
  }

  /**
   * Create an empty DirectAddressTable object and configure a nil object.
   * @param clazz   the class type of data
   * @param nil     the object/key used to mark empties in the backing array
   */
  public DirectAddressTable(Class<T> clazz, T nil) {
    this(clazz);
    this.nil = nil;
  }

  public boolean insert(T x) {
    if (a.length < n + 1) resize();
    a[address(x)] = x;
    n++;

    return true;
  }

  public T search(T x) {
    if (a.length < address(x)) return null;
    return a[address(x)];
  }

  public T delete(T x) {
    if (a.length < address(x)) return null;
    T y = a[address(x)];
    a[address(x)] = nil;
    n--;
    if (a.length >= 3*n) resize();

    return (y != nil) ? y : nil;
  }

  /**
   * Get the direct address using the bit representation of the object.
   * Will fail when word-length of object is greater than 4-bytes.
   * @param x the object for which to get the direct address
   * @return the direct address in the backing array for this element
   */
  protected int address(T x) {
    byte[] xArr = (byte[])x;
    int bitAddr = (int) xArr[3] << 24
                  ^ xArr[2] << 16
                  ^ xArr[1] << 8
                  ^ xArr[0];
    return bitAddr;
  }

  /**
   * Resize the backing array to accomodate twice the number of elements
   * currently stored. This method should be called when the number of elments
   * is <=1/3 or > n+1; so as to ammortize the insert and delete operations to
   * have a cost of O(1).
   */
  protected void resize() {
    T[] b = f.newArray(Math.max(1, 2*n));
    for (int i=0; i<n; i++)
      b[i] = a[i];
    a = b;
  }
}


