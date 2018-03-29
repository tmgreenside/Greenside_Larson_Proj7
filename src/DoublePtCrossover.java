import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class DoublePtCrossover {

    public Children doubleCrossover(City[] array1, City[] array2){
        City[] child = new City[20];
        City[] child2 = new City[20];
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));

        Random rand = new Random();
        int size = array1.length;
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


        for(int i = 0; i < points.get(0); i++){
            child[i] = array1[i];
        }
        for(int i = points.get(1); i < child2.length; i++){
            child[i] = array1[i];
        }
        for(int i = points.get(0); i < points.get(1); i++){
            child2[i] = array1[i];
        }

        int childIndex = 0;
        int parentIndex = 0;
        int childIndex2 = 0;
        int parentIndex2 = 0;
        //System.out.println(Arrays.toString(child));
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
        System.out.println("CHILD: " + Arrays.toString(child));
        System.out.println("CHILD: " + Arrays.toString(child2));
        Children children = new Children(child, child2);
        return children;
    }

}
