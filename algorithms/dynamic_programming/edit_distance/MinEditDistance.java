package com.iain.practice;

import java.util.Objects;

public class Word {
	public static void main(String[] args) {
		Word apple = new Word("apple");
		Word orange = new Word("orange");

        int d = apple.getMinEdits(orange);
        System.out.println(""
                    + apple
                    + " requires at min [" + d + "] edits to become "
                    + orange);  
    }

    String w; // string-backed word
    
    /**
    * Privatized constructor. Words require an underlying string to be created.
    * See factory method create()
    */
    private Word() {
    	w = "";
    }
    
    /**
    * This is the default constructor.
    * Words require and underlying string, containing their characters.
    * Use this constructor to create words with their characters.
    */
    public Word(String w) {
    	this.w = w;
    }

	/**
	* Find the minimum number of edits (delete, insert, replace) required
	* to change this word into the parameterized word.
	* @param x the target word
	* @return the min number of edits
	*/
	public int getMinEdits(Word x) {
		if (null == x || 0 == x.size()) return size();
		if (null == w || 0 == size()) return x.size();

		int[][] a = new int[size()][x.size()];
		for (int i=0; i<size(); i++) {
			for (int j=0; j<x.size(); j++) {

                if (0 == i && 0 == j) {
                    a[i][j] = getSubCost(i, x, j);
				} else if (0 == i) {
				    a[i][j] = a[i][j-1] + 1; // when this word is empty
				} else if (0 == j) {
				    a[i][j] = a[i-1][j] + 1; // when param word is empty
                } else {
    				int subCost = a[i-1][j-1] + getSubCost(i, x, j);
    				int delCost = 1 + a[i-1][j];
    				int addCost = 1 + a[i][j-1];
    
    				a[i][j] = getMin(subCost, delCost, addCost);
                }
			}
		}
		for (int i=0; i<size(); i++) {
			for (int j=0; j<x.size(); j++) {
			    System.out.print(a[i][j] + "\t");
			}
			System.out.println("");
		}
		return a[size()-1][x.size()-1];
	}

	/**
	* Select the smallest input.
	* @param variable number of ints to compare
	* @return the smallest int
	*/
	private static int getMin(int... series) {
	    int y = 0xFF;
	    for (int val : series) y = Math.min(y, val);
	    return y;
    } 
	/**
	* Calculate the cost required to equalize two words at their given indices.
	* If the words are the same at - no change is required and there is no cost.
	* If the words differ at their indices, one of those indices will need to be substituted
	* for the others character.
	* @param i the index within this word
	* @param x the word to compare
	* @param j the index of the target word
	* @return the cost of the substitution
	*/
	private int getSubCost(int i, Word x, int j) {
		boolean isEqual = Objects.equals(
					getChar(i),
					x.getChar(j));
        return (isEqual) ? 0 : 1;
    }

	/**
	* Return the size of this word.
	* @return the number of letters in this word.
	*/
	public int size() {
		return w.length();
	}
	
	/**
	* Get the char from the underlying string.
	* @param i the index
	*/
	public char getChar(int i) {
		if (null == w || i >= size()) throw new IndexOutOfBoundsException();
		return w.charAt(i);
	}
	
	/**
	 * Pretty the String func.
	 */
	@Override
	public String toString() {
	    return "Word [" + w + "]";
	}
}
