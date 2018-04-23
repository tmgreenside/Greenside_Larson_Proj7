import com.oracle.tools.packager.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

public class GA {
    ArrayList<City[]> paths = new ArrayList<>();

    public void addArrays(City[] path){
        paths.add(path);
    }

    //generate 8 cities and create the first path
    public void generateFirstSetOfPaths(){
        City[] parent1 = new City[8];
        parent1[0] = (new City(50,50, "a"));
        parent1[1] = (new City(20, 89, "b"));
        parent1[2] = (new City(50, 60, "c"));
        parent1[3] = (new City(2,3, "d"));
        parent1[4] = (new City(20,79, "e"));
        parent1[5] = (new City(69, 30, "f"));
        parent1[6] = (new City(48,0, "g"));
        parent1[7] = (new City(30,99, "h"));

        //add this path to the array list of paths
        paths.add(parent1);
        int counter = 0;
        //generate 19 more paths so that we have a population of 8
        while(counter < 19){
            //copy parent 1 into parent 2
            City[] parent2 = new City[8];
            parent2 = parent1.clone();

            //shuffle parent2 so that it is different than parent 1
            Collections.shuffle(Arrays.asList(parent2));
            boolean doNotAdd = false;
            //loop through the array list and see if parent 2 is already in the list,
            //if it is not we add it
            for (City[] path:paths) {
                if(Arrays.equals(path,parent2)){
                    doNotAdd = true;
                }
            }
            if(!doNotAdd){
                paths.add(parent2);
                counter++;
                //System.out.println(Arrays.toString(parent2));
            }
        }
        Cities cities = new Cities();
//        for (City[] path:paths) {
//            System.out.println(Arrays.toString(path));
//        }
        System.out.println("************************");
        System.out.println("Initial distance: " + cities.calculateDistance(paths.get(0)));
        System.out.println("************************");
    }

    //top down
    public void generateChildrenDoublePoint(){
        Children childSet;
        DoublePtCrossover doublePtCross = new DoublePtCrossover();
        int pairs = paths.size()/2;
        //System.out.println("Number of pairs = " + pairs);
        int counter = 0;
        int numberAdded = 0;
        while(numberAdded < (pairs * 2)){
            //generate 2 children from the first two paths
            childSet = doublePtCross.doubleCrossover(paths.get(counter),paths.get(counter+1));
            //ensure the 2 children are not equal
            if(!Arrays.equals(childSet.getFirstChild(),childSet.getSecondChild())) {
                boolean doNotAdd = false;
                //check if child1 and child2 are in the list of paths
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
                //if child1 and child2 are not in the list of paths we can add it to the array list, paths
                if(!doNotAdd){
                    paths.add(childSet.getSecondChild());
                    numberAdded++;
                    paths.add(childSet.getFirstChild());
                    numberAdded++;
                    //increment counter by 2 so that each parent is only mated once
                    counter = counter + 2;
                }
            }
        }
        //System.out.println("Size of arrayList: " + (paths.size() - 1));
        //System.out.println("*******************************************************************************************************");
        for (City[] path:paths) {
            //System.out.println(Arrays.toString(path));
        }
    }
    //top down
    //
    public void generateChildrenSinglePoint() {
        Children childSet;
        singlePtCrossover singlePoint = new singlePtCrossover();
        int pairs = paths.size() / 2;
        //childSet = singlePoint.crossover(paths.get(0),paths.get(1));
        //System.out.println("Number of pairs = " + pairs);
        int counter = 0;
        int numberAdded = 0;
        while(numberAdded < (pairs * 2)){
            //System.out.println("Current Counter: " + counter);
            //System.out.println("Number added: " + numberAdded);
            childSet = singlePoint.crossover(paths.get(counter),paths.get(counter+1));
            if(!Arrays.equals(childSet.getFirstChild(),childSet.getSecondChild())) {
                boolean doNotAdd = false;
                for (City[] path:paths) {
                    if(Arrays.equals(path,childSet.getFirstChild())){
                        doNotAdd = true;
                    }
                }
                for (City[] path: paths) {
                    if(Arrays.equals(path,childSet.getSecondChild())){
                        doNotAdd = true;
                    }
                }
                if(!doNotAdd){
                    paths.add(childSet.getFirstChild());
                    numberAdded++;
                    paths.add(childSet.getSecondChild());
                    numberAdded++;
                    counter = counter + 2;
                    //System.out.println("ADDED" + counter);
                }
            }
        }
        //System.out.println("Size of arrayList: " + (paths.size() - 1));
        //System.out.println("*******************************************************************************************************");
//        for(City[] path:paths) {
//            System.out.println(Arrays.toString(path));
//        }
    }
    //Tournament
    public void generateChildrenDoublePointTournament(){
        Children childSet;
        City[] parent1;
        City[] parent2;
        int pairs = paths.size() / 2;
        Random random = new Random();
        int randomNum;
        int randBound = pairs * 2;
        DoublePtCrossover doublePt = new DoublePtCrossover();
        int counter = 0;
        int numberAdded = 0;
        ArrayList<City[]> pathsCopy = new ArrayList<>();
        pathsCopy.addAll(paths);
        while (numberAdded < (pairs * 2)){
            randomNum = random.nextInt(randBound);
            parent1 = pathsCopy.get(randomNum);
            pathsCopy.remove(randomNum);
            randBound = pathsCopy.size();
            randomNum = random.nextInt(randBound);
            parent2 = pathsCopy.get(randomNum);
            pathsCopy.remove(randomNum);
            randBound = pathsCopy.size();
            Log.debug("Negative bound + " + randBound);
            childSet = doublePt.doubleCrossover(parent1, parent2);
            if(!Arrays.equals(childSet.getFirstChild(),childSet.getSecondChild())) {
                boolean doNotAdd = false;
                for (City[] path : paths) {
                    if (Arrays.equals(path, childSet.getFirstChild())) {
                        doNotAdd = true;
                    }
                }
                for (City[] path : paths) {
                    if (Arrays.equals(path, childSet.getSecondChild())) {
                        doNotAdd = true;
                    }
                }
                if (!doNotAdd) {
                    paths.add(childSet.getFirstChild());
                    numberAdded++;
                    paths.add(childSet.getSecondChild());
                    numberAdded++;
                    counter = counter + 2;
                }else{
                    randBound = randBound + 2;
                    pathsCopy.add(parent1);
                    pathsCopy.add(parent2);
                }
            }else{
                randBound = randBound + 2;
                pathsCopy.add(parent1);
                pathsCopy.add(parent2);
            }
        }
    }
    public void generateChildrenSinglePointTournament(){
        Children childSet;
        City[] parent1;
        City[] parent2;
        int pairs = paths.size() / 2;
        Random random = new Random();
        int randomNum;
        int randBound = pairs * 2;
        singlePtCrossover singlePt = new singlePtCrossover();
        int counter = 0;
        int numberAdded = 0;
        ArrayList<City[]> pathsCopy = new ArrayList<>();
        pathsCopy.addAll(paths);
        while (numberAdded < (pairs * 2)){
            Log.debug("Negative bound + " + randBound);
            randomNum = random.nextInt(randBound);
            parent1 = pathsCopy.get(randomNum);
            pathsCopy.remove(randomNum);
            randBound = pathsCopy.size();
            Log.debug("Negative bound + " + randBound);
            randomNum = random.nextInt(randBound);
            parent2 = pathsCopy.get(randomNum);
            pathsCopy.remove(randomNum);
            randBound = pathsCopy.size();
            Log.debug("Negative bound + " + randBound);
            childSet = singlePt.crossover(parent1, parent2);
            if(!Arrays.equals(childSet.getFirstChild(),childSet.getSecondChild())) {
                boolean doNotAdd = false;
                for (City[] path : paths) {
                    if (Arrays.equals(path, childSet.getFirstChild())) {
                        doNotAdd = true;
                    }
                }
                for (City[] path : paths) {
                    if (Arrays.equals(path, childSet.getSecondChild())) {
                        doNotAdd = true;
                    }
                }
                if (!doNotAdd) {
                    paths.add(childSet.getFirstChild());
                    numberAdded++;
                    paths.add(childSet.getSecondChild());
                    numberAdded++;
                    counter = counter + 2;
                }else{
                    randBound = randBound + 2;
                    pathsCopy.add(parent1);
                    pathsCopy.add(parent2);
                }
            }else{
                randBound = randBound + 2;
                pathsCopy.add(parent1);
                pathsCopy.add(parent2);
            }
        }
    }

    //eliminate the half of the paths with the longest traversal distance
    public void elimination(){
        Cities cities = new Cities();
        int size = paths.size()/2;
        //System.out.println("Size: " + size);
        //bubble sort algorithm to sort from least to greatest distance
        for (int i = 0; i < paths.size() - 1; i++) {
            for(int j = 0; j < paths.size() - i - 1; j++){
                double distance1 = cities.calculateDistance(paths.get(j));
                double distance2 = cities.calculateDistance(paths.get(j + 1));
                if(distance1 < distance2){
                    City[] temp = Arrays.copyOf(paths.get(j), 8);
                    paths.set(j, paths.get(j+1));
                    paths.set(j+1, temp);
                }
            }
        }
        //System.out.println("***************************************************************************************************************");
        for(int i = 0; i < size; i++){
            paths.remove(i);
        }
        for (City[] path:paths) {
            //System.out.println(Arrays.toString(path));
        }
        City[] city = paths.get(paths.size() - 1);
        //System.out.println(Arrays.toString(city));
        //System.out.println("Final distance = " + cities.calculateDistance(paths.get(paths.size() - 1)));
    }

    public void runTopDownDoublePt(int numberOfGenerations){
        System.out.println("Running top down double point crossover with " + numberOfGenerations + " generations...");
        Cities cities = new Cities();
        for(int i = 0; i < numberOfGenerations; i++){
            generateChildrenDoublePoint();
            elimination();
        }
        System.out.println(Arrays.toString(paths.get(paths.size() - 1)));
        System.out.println("Final distance = " + cities.calculateDistance(paths.get(paths.size() - 1)));
    }

    public void runTopDownSinglePt(int numberOfGenerations){
        System.out.println("Running top down single point crossover with " + numberOfGenerations + " generations...");
        Cities cities = new Cities();
        for(int i = 0; i < numberOfGenerations; i++){
            generateChildrenSinglePoint();
            elimination();
        }
        System.out.println(Arrays.toString(paths.get(paths.size() - 1)));
        System.out.println("Final distance = " + cities.calculateDistance(paths.get(paths.size() - 1 )));
    }

    public void runTournamentSinglePt(int numberOfGenerations){
        System.out.println("Running Tournament Single point crossover with " + numberOfGenerations + " generations...");
        Cities cities = new Cities();
        for(int i = 0; i < numberOfGenerations; i++){
            generateChildrenSinglePointTournament();
            elimination();
        }
        System.out.println(Arrays.toString(paths.get(paths.size() - 1)));
        System.out.println("Final distance = " + cities.calculateDistance(paths.get(paths.size() - 1)));

    }

    public void runTournamentDoublePt(int numberOfGenerations){
        System.out.println("Running Tournament Double Pt crossover with " + numberOfGenerations + " generations...");
        Cities cities = new Cities();
        for(int i = 0; i < numberOfGenerations; i++){
            generateChildrenDoublePointTournament();
            elimination();
        }
        System.out.println(Arrays.toString(paths.get(paths.size() - 1)));
        System.out.println("Final distance = " + cities.calculateDistance(paths.get(paths.size() - 1)));

    }
}