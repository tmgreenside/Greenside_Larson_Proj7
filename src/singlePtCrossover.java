import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class singlePtCrossover {

    public static int MUTATION_RATE = 10;
    
    public Children crossover(City[] p1, City[] p2){
        Random rand = new Random();
        int size = p1.length;
        int point1 = rand.nextInt(size);
        City[] child = new City[20];
        City[] child2 = new City[20];
        
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
        while(childIndex1 < 20 && parentIndex1 < 20){
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
        
        //System.out.println("Child1:  " + Arrays.toString(child));
        //System.out.println("Child2:  " + Arrays.toString(child2));
        Children children = new Children(child, child2);
        return children;
    }
}
