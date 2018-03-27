import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class DoublePtCrossover {
    public City[] doubleCrossover(City[] array1, City[] array2){
        City[] child = new City[10];
        Random rand = new Random();
        int size = array1.length;
        int point2 = rand.nextInt(size);
        int point1 = rand.nextInt(size);
        int point3 = rand.nextInt(size);
        int point4 = rand.nextInt(size);

        ArrayList<Integer> points = new ArrayList();
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        Collections.sort(points);
        System.out.print(Arrays.toString(points.toArray()));
        for(int i = points.get(0); i < points.get(1); i++){
            child[i] = array1[i];
        }
        for(int i = points.get(2); i < points.get(3); i++){
            child[i] = array1[i];
        }
        int childIndex = 0;
        int parentIndex = 0;
        while(childIndex < 10) {
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
        System.out.println(Arrays.toString(child));
        return child;
    }

}
