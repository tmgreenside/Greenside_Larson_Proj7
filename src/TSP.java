public class TSP {

    public static void main(String args[]) {
        Cities cities = new Cities();
        cities.generateCities();
        cities.generateParent2();
        double d1 = cities.calculateDistance(cities.parent1);
        double d2 = cities.calculateDistance(cities.parent2);
        System.out.println("distance for parent 1 = " + d1);
        System.out.println("distance for parent 2 = " + d2);
        cities.generateCrossoverChild(cities.parent1, cities.parent2);
        //cities.generate2PtCrossoverChild(cities.parent1, cities.parent2);

    }
}