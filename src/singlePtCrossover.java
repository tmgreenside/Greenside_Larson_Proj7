import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class singlePtCrossover {

    public Children crossover(City[] p1, City[] p2){
        Random rand = new Random();
        int size = p1.length;
        int point1 = rand.nextInt(size);
        City[] child = new City[10];
        City[] child2 = new City[10];
        for(int i = 0; i < point1; i++){
            child[i] = p1[i];
        }
        for(int i = point1; i < child2.length; i++){
            child2[i] = p1[i];
        }
        //System.out.println(Arrays.toString(child));
        int childIndex1 = 0;
        int parentIndex1 = 0;
        int childIndex2 = 0;
        int parentIndex2 = 0;
        while(childIndex1 < 10 && parentIndex1 < 10){
            if(!Arrays.asList(child).contains(p2[parentIndex1])){
                if(child[childIndex1] == null) {
                    child[childIndex1] = p2[parentIndex1];
                    childIndex1++;
                    parentIndex1++;
                }else {
                    childIndex1++;
                }
            }else {
                if(child[childIndex1] != null){
                    childIndex1++;
                    parentIndex1++;
                }else {
                    parentIndex1++;
                }
            }
        }
        //System.out.println(Arrays.toString(child2));
        while(childIndex2 < 10 && parentIndex2 < 10){
            if(!Arrays.asList(child2).contains(p2[parentIndex2])){
                if(child2[childIndex2] == null) {
                    child2[childIndex2] = p2[parentIndex2];
                    childIndex2++;
                    parentIndex2++;
                }else {
                    childIndex2++;
                }
            }else {
                if(child2[childIndex2] != null){
                    childIndex2++;
                    parentIndex2++;
                }else {
                    parentIndex2++;
                }
            }
        }

        //System.out.println("Child1:  " + Arrays.toString(child));
        //System.out.println("Child2:  " + Arrays.toString(child2));
        Children children = new Children(child, child2);
        return children;
    }
}
