import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class GA {
    ArrayList<City[]> paths = new ArrayList<>();

    public void addArrays(City[] path){
        paths.add(path);
    }

    public void generateFirstSetOfPaths(){
        City[] parent1 = new City[20];
        parent1[0] = (new City(50,50));
        parent1[1] = (new City(20, 89));
        parent1[2] = (new City(50, 60));
        parent1[3] = (new City(2,3));
        parent1[4] = (new City(20,79));
        parent1[5] = (new City(69, 30));
        parent1[6] = (new City(48,0));
        parent1[7] = (new City(30,99));
        parent1[8] = (new City(29,80));
        parent1[9] = (new City(40,20));
        parent1[10] = (new City(50,25));
        parent1[11] = (new City(85,89));
        parent1[12] = (new City(76,60));
        parent1[13] = (new City(32,48));
        parent1[14] = (new City(20,70));
        parent1[15] = (new City(69,29));
        parent1[16] = (new City(47,81));
        parent1[17] = (new City(88,93));
        parent1[18] = (new City(37,21));
        parent1[19] = (new City(10,16));
        paths.add(parent1);
        int counter = 0;
        while(counter < 20){
            City[] parent2 = new City[20];
            parent2 = parent1.clone();
            Collections.shuffle(Arrays.asList(parent2));
            boolean doNotAdd = false;
            for (City[] path:paths) {
                if(Arrays.equals(path,parent2)){
                    doNotAdd = true;
                }
            }
            if(!doNotAdd){
                paths.add(parent2);
                counter++;
                System.out.println(Arrays.toString(parent2));
            }
        }
        DoublePtCrossover doublePtCrossover = new DoublePtCrossover();
        doublePtCrossover.doubleCrossover(paths.get(0),paths.get(1));
        doublePtCrossover.doubleCrossover(paths.get(2),paths.get(3));
        doublePtCrossover.doubleCrossover(paths.get(4),paths.get(5));
        doublePtCrossover.doubleCrossover(paths.get(6),paths.get(7));



    }

    //top down
    public void generateChildrenDoublePoint(){
        Children childSet;
        DoublePtCrossover doublePtCross = new DoublePtCrossover();
        int pairs = paths.size()/2;
        System.out.println("Number of pairs = " + pairs);
        int counter = 0;
        int counter2 = 0;
        int numberAdded = 0;
        while(numberAdded < (pairs * 2)){
            counter2++;
            System.out.println("COUNTER2: " + counter2);
            childSet = doublePtCross.doubleCrossover(paths.get(counter),paths.get(counter+1));
            if(!Arrays.equals(childSet.getFirstChild(),childSet.getSecondChild())) {
                boolean doNotAdd = false;
                for (City[] path:paths) {
                    if(Arrays.equals(path,childSet.getFirstChild())){
                        doNotAdd = true;
                    }
                }
                for (City[] path:paths) {
                    if(Arrays.equals(path,childSet.getSecondChild())){
                        doNotAdd = true;
                    }
                }
                if(!doNotAdd){
                    paths.add(childSet.getSecondChild());
                    numberAdded++;
                    paths.add(childSet.getFirstChild());
                    numberAdded++;
                    counter = counter + 2;
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
                if(distance1 < distance2){
                    City[] temp = Arrays.copyOf(paths.get(j), 20);
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
        City[] city = paths.get(paths.size() - 1);
        System.out.println(Arrays.toString(city));
        System.out.println("Final distance = " + cities.calculateDistance(paths.get(paths.size() - 1)));
    }

    public void runTopDownDoublePt(int numberOfGenerations){
        Cities cities = new Cities();
        for(int i = 0; i < numberOfGenerations; i++){
            generateChildrenDoublePoint();
            elimination();
        }
        System.out.println(Arrays.toString(paths.get(paths.size() - 1)));
        System.out.println("Final distance = " + cities.calculateDistance(paths.get(paths.size() - 1)));
    }
}
