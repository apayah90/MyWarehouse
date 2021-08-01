package com.pujiy.mywarehouse.inventoryjournal;

import java.util.ArrayList;
import java.util.HashSet;

public class hashsettt {

    // Create ArrayList of Integer
    public ArrayList<Integer> Numbers
            = new ArrayList<Integer>();

   Numbers

    /*Converting List to HashSet using constructor.
     */

    HashSet<Integer> hashSetNumbers
            = new HashSet<Integer>(Numbers);

		System.out.println("Unique Values of ArrayList");

    // iterate through HashSet
		for (Integer strNumber : hashSetNumbers)
            System.out.println(strNumber);
}
