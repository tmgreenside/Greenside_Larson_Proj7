import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GA {
    ArrayList<City[]> paths = new ArrayList<>();
    Random rand = new Random();
    
    public void addArrays(City[] path){
        paths.add(path);
    }
    public void generateFirstSetOfPaths(){
        City[] parent1 = new City[10];
        parent1[0] = (new City(50,50));
        parent1[1] = (new City(20, 89));
        parent1[2] = (new City(50, 60));
        parent1[3] = (new City(28,35));
        parent1[4] = (new City(20,79));
        parent1[5] = (new City(69, 30));
        parent1[6] = (new City(48,98));
        parent1[7] = (new City(30,99));
        parent1[8] = (new City(29,80));
        parent1[9] = (new City(40,20));
        paths.add(parent1);
        int counter = 0;
        while(counter < 50){
            City[] parent2 = new City[10];
            parent2 = parent1.clone();
            Collections.shuffle(Arrays.asList(parent2));
            if(!Arrays.asList(parent2).contains(parent2)){
                paths.add(parent2);
                counter++;
                System.out.println(Arrays.toString(parent2));
            }
        }
    }

//top down
    public void generateChildrenDoublePoint(){
        Children childSet;
        DoublePtCrossover doublePtCross = new DoublePtCrossover();
        int pairs = paths.size()/2;
        System.out.println("Number of pairs = " + pairs);
        int counter = 0;
        while(counter < (pairs * 2 - 1)){
            childSet = doublePtCross.doubleCrossover(paths.get(counter),paths.get(counter+1));
            if(!Arrays.equals(childSet.getFirstChild(),childSet.getSecondChild())) {
                boolean doNotAdd = false;
                for (City[] path:paths) {
                    if(Arrays.equals(path,childSet.getFirstChild())){
                        doNotAdd = true;
                    }
                }
                if(!doNotAdd){
                    paths.add(counter, childSet.getFirstChild());
                    counter++;
                }
                boolean doNotAdd2 = false;
                for (City[] path:paths) {
                    if(Arrays.equals(path,childSet.getSecondChild())){
                        doNotAdd2 = true;
                    }
                }
                if(!doNotAdd2){
                    paths.add(counter, childSet.getSecondChild());
                    counter++;
                }
            }
        }
        System.out.println("Size of arrayList: " + (paths.size() - 1));
        System.out.println("*******************************************************************************************************");
        for (City[] path:paths) {
            System.out.println(Arrays.toString(path));
        }
    }
    //top down
    public void generateChildrenSinglePoint(){
        Children childSet;
        singlePtCrossover singlePoint = new singlePtCrossover();
        int pairs = paths.size()/2;
        System.out.println("Number of pairs = " + pairs);
        int counter = 0;
        while(counter < (pairs * 2 - 1)){
            childSet = singlePoint.crossover(paths.get(counter),paths.get(counter+1));
            if(!Arrays.equals(childSet.getFirstChild(),childSet.getSecondChild())) {
                boolean doNotAdd = false;
                for (City[] path:paths) {
                    if(Arrays.equals(path,childSet.getFirstChild())){
                        doNotAdd = true;
                    }
                }
                if(!doNotAdd){
                    paths.add(counter, childSet.getFirstChild());
                    counter++;
                    System.out.println("ADDED" + counter);
                }
                boolean doNotAdd2 = false;
                for (City[] path:paths) {
                    if(Arrays.equals(path,childSet.getSecondChild())){
                        doNotAdd2 = true;
                    }
                }
                if(!doNotAdd2){
                    paths.add(counter, childSet.getSecondChild());
                    counter++;
                    System.out.println("ADDED" + counter);
                }
            }
        }
        System.out.println("Size of arrayList: " + (paths.size() - 1));
        System.out.println("*******************************************************************************************************");
        for (City[] path:paths) {
            System.out.println(Arrays.toString(path));
        }
    }

    public void elimination(){
        Cities cities = new Cities();
        int size = paths.size()/2;
        System.out.println("Size: " + size);
        for (int i = 0; i < paths.size() - 1; i++) {
            for(int j = 0; j < paths.size() - i - 1; j++){
                double distance1 = cities.calculateDistance(paths.get(j));
                double distance2 = cities.calculateDistance(paths.get(j + 1));
                if(distance1 > distance2){
                    City[] temp = Arrays.copyOf(paths.get(j), 10);
                    paths.set(j, paths.get(j+1));
                    paths.set(j+1, temp);
                }
            }
        }
        System.out.println("***************************************************************************************************************");
        for(int i = 0; i < size; i++){
            paths.remove(i);
        }
        for (City[] path:paths) {
            System.out.println(Arrays.toString(path));
        }
    }
    
    /*
    Description of algorithm from De Palma's slides:
    
    While ( < 16 mating pairs)
    {
      Do twice:
        Randomly select subset of the population
        Select  1 parent at random from subset
      Add parents to set of mating pairs
    }
    */
    
    /**
     * Returns an instance of class Children
     */
    public void tournamentPairingDoublePt() {
        Children childSet;
        DoublePtCrossover doublePtCross = new DoublePtCrossover();
        int pairs = paths.size()/2;
        int counter = 0;
        ArrayList<Integer> usedIndeces = new ArrayList<>();
        while(counter < pairs) {
            int index1;
            int index2;
            do {
                index1 = rand.nextInt(paths.size());
                index2 = rand.nextInt(paths.size());
            } while (index1 == index2 || usedIndeces.contains(index1) || usedIndeces.contains(index2));
            
            // Add parents to set of mating pairs
            childSet = doublePtCross.doubleCrossover(paths.get(index1),paths.get(index2));
            paths.add(counter,childSet.getFirstChild());
            paths.add(counter,childSet.getSecondChild());
        }
    }
    
    /**
     * Returns an instance of class Children
     */
    public void tournamentPairingSinglePt() {
        Children childSet;
        singlePtCrossover singlePtCross = new singlePtCrossover();
        int pairs = paths.size()/2;
        int counter = 0;
        ArrayList<Integer> usedIndeces = new ArrayList<>();
        while(counter < pairs) {
            int index1;
            int index2;
            do {
                index1 = rand.nextInt(paths.size());
                index2 = rand.nextInt(paths.size());
            } while (index1 == index2 || usedIndeces.contains(index1) || usedIndeces.contains(index2));
            
            // Add parents to set of mating pairs
            childSet = singlePtCross.crossover(paths.get(index1),paths.get(index2));
            paths.add(counter,childSet.getFirstChild());
            paths.add(counter,childSet.getSecondChild());
        }
    }
}
