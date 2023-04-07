package org.example;

import java.util.*;

public class RubberDucksAlgorithm {

    public static void main(String[] args) {

        // create the list of ducks
        List<Duck> ducks = new ArrayList<>();
        ducks.add(new Duck("duck1", 5, 4));
        ducks.add(new Duck("duck2", 7, 6));
        ducks.add(new Duck("duck3", 2, 1));
        ducks.add(new Duck("duck4", 8, 5));
        ducks.add(new Duck("duck5", 4, 3));
        ducks.add(new Duck("duck6", 6, 7));
        ducks.add(new Duck("duck7", 9, 9));
        ducks.add(new Duck("duck8", 1, 2));
        ducks.add(new Duck("duck9", 5, 4));
        ducks.add(new Duck("duck10", 7, 6));
        ducks.add(new Duck("duck11", 8, 8));
        ducks.add(new Duck("duck12", 3, 3));
        ducks.add(new Duck("duck13", 4, 5));
        ducks.add(new Duck("duck14", 6, 7));
        ducks.add(new Duck("duck15", 2, 1));
        ducks.add(new Duck("duck16", 1, 2));
        ducks.add(new Duck("duck17", 5, 4));
        ducks.add(new Duck("duck18", 8, 6));
        ducks.add(new Duck("duck19", 9, 8));
        ducks.add(new Duck("duck20", 3, 2));

        // set the weight limit
        int widthLimit = 50;

        // solve the backpack problem
        List<Duck> selectedDucks = solveBackpackProblem(ducks, widthLimit);

        // print the selected ducks
        int sumOfHeight = 0;

        for (Duck duck : selectedDucks) {
            sumOfHeight += duck.getHeight();
        }
        System.out.println("Maximum available sum of the heights of the ducks used: ");
        System.out.println(sumOfHeight);


    }

    public static List<Duck> solveBackpackProblem(List<Duck> ducks, int widthLimit) {

        // create the memoization table
        int[][] memo = new int[ducks.size() + 1][widthLimit + 1];

        // fill the table
        for (int i = 1; i <= ducks.size(); i++) {
            for (int j = 1; j <= widthLimit; j++) {
                if (ducks.get(i - 1).getWidth() > j) {
                    memo[i][j] = memo[i - 1][j];
                } else {
                    memo[i][j] = Math.max(memo[i - 1][j], memo[i - 1][j - ducks.get(i - 1).getWidth()] + ducks.get(i - 1).getHeight());
                }
            }
        }

        // trace back to find the selected ducks
        List<Duck> selectedDucks = new ArrayList<>();
        int i = ducks.size();
        int j = widthLimit;
        while (i > 0 && j > 0) {
            if (memo[i][j] != memo[i - 1][j]) {
                selectedDucks.add(ducks.get(i - 1));
                j = j - ducks.get(i - 1).getWidth();
            }
            i = i - 1;
        }

        // reverse the order of the selected ducks
        Collections.reverse(selectedDucks);

        return selectedDucks;
    }

}

class Duck {
    private String name;
    private int height;
    private int width;

    public Duck(String name, int height, int width) {
        this.height = height;
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {

        return height;
    }

    public int getWidth() {
        return width;
    }


}





