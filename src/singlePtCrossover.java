import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class singlePtCrossover {
    public City[] crossover(City[] p1, City[] p2){
        Random rand = new Random();
        int size = p1.length;
        int point2 = rand.nextInt(size);
        int point1 = rand.nextInt(size);
        City[] child = new City[10];
        if(point2 < point1){
            for(int i = point2; i < point1; i++){
                child[i] = p1[i];
            }
        }else{
            for(int i = point1; i < point2; i++){
                child[i] = p1[i];
            }
        }
        System.out.println(Arrays.toString(child));
        int childIndex = 0;
        int parentIndex = 0;
        while(childIndex < 10){
            if(!Arrays.asList(child).contains(p2[parentIndex])){
                if(child[childIndex] == null) {
                    child[childIndex] = p2[parentIndex];
                    childIndex++;
                    parentIndex++;
                }else {
                    childIndex++;
                }
            }else {
                if(child[childIndex] != null){
                    childIndex++;
                    parentIndex++;
                }else {
                    parentIndex++;
                }
            }
        }
//        for(int i = 0; i < size; i++){
//            if(child[i] == null && !Arrays.asList(child).contains(p2[i])){
//                child[i] = p2[i];
//            }
//        }
        System.out.println("Child:  " + Arrays.toString(child));
        return child;
    }
}
