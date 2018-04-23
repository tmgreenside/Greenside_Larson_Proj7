
import java.util.ArrayList;
import java.lang.Math;
import java.util.Arrays;
import java.util.Collections;

public class Cities {
    City[] parent1;
    City[] parent2;

    public Cities() {
        this.parent1 = new City[20];
        this.parent2 = new City[20];
    }

    public void generateCities(){
//        parent1[0] = (new City(50,50));
//        parent1[1] = (new City(20, 89));
//        parent1[2] = (new City(50, 60));
//        parent1[3] = (new City(2,3));
//        parent1[4] = (new City(20,79));
//        parent1[5] = (new City(69, 30));
//        parent1[6] = (new City(48,0));
//        parent1[7] = (new City(30,99));
//        parent1[8] = (new City(29,80));
//        parent1[9] = (new City(40,20));
//        parent1[10] = (new City(50,25));
//        parent1[11] = (new City(85,89));
//        parent1[12] = (new City(76,60));
//        parent1[13] = (new City(32,48));
//        parent1[14] = (new City(20,70));
//        parent1[15] = (new City(69,29));
//        parent1[16] = (new City(47,81));
//        parent1[17] = (new City(88,93));
//        parent1[18] = (new City(37,21));
//        parent1[19] = (new City(10,16));
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
        String point1;
        String point2;
        String distance;
        double totalDistance = 0;
        for(int i = 0; i < cities.length - 1; i++){
            //distance = sqrt((x2 -x1)^2 +(y2 -y1)^2)
            point1 = cities[i].point;
            point2 = cities[i + 1].point;
//            x = (cities[i].x_coor - cities[i+1].x_coor) * (cities[i].x_coor - cities[i+1].x_coor);
//            y = (cities[i].y_coor - cities[i+1].y_coor) * (cities[i].y_coor - cities[i+1].y_coor);
            distance = point1 + point2;
            //System.out.println("POINT: " + distance);
            totalDistance += getDistance(distance);
            //System.out.println("current distance: " + totalDistance);
        }
//        int finalStopx = (cities[cities.length - 1].x_coor - cities[0].x_coor) * (cities[cities.length - 1].x_coor - cities[0].x_coor);
//        int finalStopy = (cities[cities.length - 1].y_coor - cities[0].y_coor) * (cities[cities.length - 1].y_coor - cities[0].y_coor);
        point1 = cities[cities.length - 1].point;
        point2 = cities[0].point;
        distance = point1 + point2;
        totalDistance += getDistance(distance);
        return totalDistance;
    }

    public double getDistance(String point){
        double dist;
        switch (point) {
            case "ab":
            case "ba":
                dist = 2080.0;
                break;
            case "ac":
            case "ca":
                dist = 991.0;
                break;
            case "ad":
            case "da":
                dist = 326.0;
                break;
            case "ae":
            case "ea":
                dist = 2086.0;
                break;
            case "af":
            case "fa":
                dist = 1080.0;
                break;
            case "ag":
            case "ga":
                dist = 2518.0;
                break;
            case "ah":
            case "ha":
                dist = 166.0;
                break;
            case "bc":
            case "cb":
                dist = 1407.0;
                break;
            case "bd":
            case "db":
                dist = 1883.0;
                break;
            case "be":
            case "eb":
                dist = 993.0;
                break;
            case "bf":
            case "fb":
                dist = 1260.0;
                break;
            case "bg":
            case "gb":
                dist = 944.0;
                break;
            case "bh":
            case "hb":
                dist = 2225.0;
                break;
            case "cd":
            case "dc":
                dist = 669.0;
                break;
            case "ce":
            case "ec":
                dist = 1840.0;
                break;
            case "cf":
            case "fc":
                dist = 148.0;
                break;
            case "cg":
            case "gc":
                dist = 2184.0;
                break;
            case "ch":
            case "hc":
                dist = 1075.0;
                break;
            case "de":
            case "ed":
                dist = 1965.0;
                break;
            case "df":
            case "fd":
                dist = 784.0;
                break;
            case "dg":
            case "gd":
                dist = 2397.0;
                break;
            case "dh":
                dist = 407.0;
                break;
            case "ef":
            case "fe":
                dist = 1787.0;
                break;
            case "eg":
            case "ge":
                dist = 622.0;
                break;
            case "eh":
            case "he":
                dist = 2265.0;
                break;
            case "fg":
            case "gf":
                dist = 2131.0;
                break;
            case "fh":
            case "hf":
                dist = 1187.0;
                break;
            case "gh":
            case "hg":
                dist = 2697.0;
                break;
            default:
                dist = 0.0;
        }
        return dist;
    }
    public void generateCrossoverChild(City[] parent1, City[] parent2){
        singlePtCrossover singlePtCros = new singlePtCrossover();
        //City[] child = singlePtCros.crossover(parent1, parent2);
        //double distance = calculateDistance(child);
        //System.out.println("distance for child is = " + distance);
    }
    public void generate2PtCrossoverChild(City[] parent1, City[] parent2){
        DoublePtCrossover doublePtCrossover = new DoublePtCrossover();
        //City[] child = doublePtCrossover.doubleCrossover(parent1,parent2);
        //double distance = calculateDistance(child);
        //System.out.println("distance for child is  " + distance);
    }
}
