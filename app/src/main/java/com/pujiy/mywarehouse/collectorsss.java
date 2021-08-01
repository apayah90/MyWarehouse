package com.pujiy.mywarehouse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Collectors;

public class collectorsss {


    public static void main(String[] args) {
        ArrayList<Integer> Numbers = new ArrayList<>();
        ArrayList<Integer> NumbersUnique = new ArrayList<>();

        Numbers.add(1);
        Numbers.add(2);
        Numbers.add(1);
        Numbers.add(4);
        Numbers.add(2);
        Numbers.add(8);
        Numbers.add(8);
        Numbers.add(9);

        /*Converting List to HashSet using constructor.
         */

        HashSet<Integer> hashSetNumbers
                = new HashSet<Integer>(Numbers);

        System.out.println("Unique Values of ArrayList");

        // iterate through HashSet
        for (Integer strNumber : hashSetNumbers)
            NumbersUnique.add(strNumber);

        for (Integer strNumberUnique : NumbersUnique)
            System.out.println("Unique"+ strNumberUnique);

    }





}
