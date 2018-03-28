import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class Tournament {
    ArrayList<City[]> paths;

    public void addArrays(City[] path){
        paths.add(path);
    }

    public void elimination(){
        int numberToDelete = paths.size() / 2;
        int[] randomIndices = new int[numberToDelete];
        Random rand = new Random();
        int totalIndicesAdded = 0;
        while (totalIndicesAdded < numberToDelete){
            int randomIndex = rand.nextInt(numberToDelete);
            if(!Arrays.asList(randomIndices).contains(randomIndex)){
                randomIndices[totalIndicesAdded] = randomIndex;
                totalIndicesAdded++;
            }
        }
        for(int i = 0; i < numberToDelete; i++){
            randomIndices[i] = rand.nextInt(numberToDelete);
        }
        for (int integer: randomIndices) {
            paths.remove(integer);
        }
    }
}
