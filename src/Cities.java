
import java.util.ArrayList;
import java.lang.Math;
import java.util.Arrays;
import java.util.Collections;

public class Cities {
    City[] parent1;
    City[] parent2;

    public Cities() {
        this.parent1 = new City[10];
        this.parent2 = new City[10];
    }

    public void generateCities(){
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
        System.out.print("Parent 1: ");
        System.out.println(Arrays.toString(parent1));
    }

    public void generateParent2(){
        parent2 = parent1.clone();
        Collections.shuffle(Arrays.asList(parent2));
        System.out.print("Parent 2: ");
        System.out.println(Arrays.toString(parent2));
    }

    public double calculateDistance(City[] cities){
        int x;
        int y;
        double totalDistance = 0;
        for(int i = 0; i < cities.length - 2; i++){
            //distance = sqrt((x2 -x1)^2 +(y2 -y1)^2)
            x = (cities[i].x_coor - cities[i+1].x_coor) * (cities[i].x_coor - cities[i+1].x_coor);
            y = (cities[i].y_coor - cities[i+1].y_coor) * (cities[i].y_coor - cities[i+1].y_coor);
            totalDistance += Math.sqrt(x + y);
        }
        int finalStopx = (cities[cities.length - 1].x_coor - cities[0].x_coor) * (cities[cities.length - 1].x_coor - cities[0].x_coor);
        int finalStopy = (cities[cities.length - 1].y_coor - cities[0].y_coor) * (cities[cities.length - 1].y_coor - cities[0].y_coor);
        totalDistance += Math.sqrt(finalStopx + finalStopy);
        return totalDistance;
    }

    public void generateCrossoverChild(City[] parent1, City[] parent2){
        singlePtCrossover singlePtCros = new singlePtCrossover();
        City[] child = singlePtCros.crossover(parent1, parent2);
        double distance = calculateDistance(child);
        System.out.println("distance for child is = " + distance);
    }
    public void generate2PtCrossoverChild(City[] parent1, City[] parent2){
        DoublePtCrossover doublePtCrossover = new DoublePtCrossover();
        City[] child = doublePtCrossover.doubleCrossover(parent1,parent2);
        double distance = calculateDistance(child);
        System.out.println("distance for child is  " + distance);
    }
}
