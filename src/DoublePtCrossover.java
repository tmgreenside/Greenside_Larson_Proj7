import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class DoublePtCrossover {
    //Constant to hold mutation rate
    public static int MUTATION_RATE = 10;
    public Children doubleCrossover(City[] array1, City[] array2){
        //Arrays to hold new children
        City[] child = new City[20];
        City[] child2 = new City[20];
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
        //Generate random numbers to represent the points that crossover will occure
        Random rand = new Random();
        int size = array1.length;
        //Counter to keep track of points added
        int pointsAdded = 0;
        ArrayList<Integer> points = new ArrayList();
        while(pointsAdded < 2){
            int point = rand.nextInt(size);
            if(!Arrays.asList(points).contains(point)){
                points.add(point);
                pointsAdded++;
            }
        }

        Collections.sort(points);

        //child gets indices, 0 to the first point of the first parent
        for(int i = 0; i < points.get(0); i++){
            child[i] = array1[i];
        }
        //child gets indices between second point and last index of the parent
        for(int i = points.get(1); i < child2.length; i++){
            child[i] = array1[i];
        }
        //child 2 gets indices between point 1 and point 2 of the parent
        for(int i = points.get(0); i < points.get(1); i++){
            child2[i] = array1[i];
        }

        int childIndex = 0;
        int parentIndex = 0;
        int childIndex2 = 0;
        int parentIndex2 = 0;
        //System.out.println(Arrays.toString(child));

        //while loop to fill in the gaps of child with values in parent 2
        while(childIndex < 20 && parentIndex < 20) {
            if (!Arrays.asList(child).contains(array2[parentIndex])) {
                if (child[childIndex] == null) {
                    child[childIndex] = array2[parentIndex];
                    childIndex++;
                    parentIndex++;
                } else {
                    childIndex++;
                }
            } else {
                if (child[childIndex] != null) {
                    childIndex++;
                    parentIndex++;
                } else {
                    parentIndex++;
                }
            }
        }
        //System.out.println(Arrays.toString(child2));
        //while loop to fill in the gaps of the child with values in parent2
        while(childIndex2 < 20 && parentIndex2 < 20) {
            if (!Arrays.asList(child2).contains(array2[parentIndex2])) {
                if (child2[childIndex2] == null) {
                    child2[childIndex2] = array2[parentIndex2];
                    childIndex2++;
                    parentIndex2++;
                } else {
                    childIndex2++;
                }
            } else {
                if (child2[childIndex2] != null) {
                    childIndex2++;
                    parentIndex2++;
                } else {
                    parentIndex2++;
                }
            }
        }

        int mutate;
        for (int num = 0; num < child.length - 2; num++) {
            //random number generated to see if mutation occurs
            mutate = rand.nextInt(100);
            //if mutate < MUTATION_RATE then swap current index with second index
            if(mutate < MUTATION_RATE){
                City city1 = child[num];
                child[num] = child[num + 1];
                child[num + 1] = city1;
            }
        }
        //same for loop as above, but for 2nd child
        for (int num = 0; num < child2.length - 2; num++) {
            mutate = rand.nextInt(100);
            if(mutate < MUTATION_RATE){
                City city1 = child2[num];
                child2[num] = child2[num + 1];
                child2[num + 1] = city1;
            }
        }
        //System.out.println("CHILD: " + Arrays.toString(child));
        //System.out.println("CHILD: " + Arrays.toString(child2));
        Children children = new Children(child, child2);
        return children;
    }

}
