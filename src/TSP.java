public class TSP {

    public static void main(String args[]) {

        GA ga = new GA();
        ga.generateFirstSetOfPaths();
        System.out.println("TOP DOWN SINGLE POINT CROSSOVER: ");
        ga.runTopDownSinglePt(5000);

        GA ga2 = new GA();
        ga2.generateFirstSetOfPaths();
        System.out.println("TOP DOWN DOUBLE POINT CROSSOVER: ");
        ga2.runTopDownDoublePt(5000);

        GA ga3 = new GA();
        ga3.generateFirstSetOfPaths();
        System.out.println("TOURNAMENT SINGLE POINT CROSSOVER: ");
        ga3.runTournamentSinglePt(5000);

        GA ga4 = new GA();
        ga4.generateFirstSetOfPaths();
        System.out.println("TOURNAMENT DOUBLE POINT CROSSOVER: ");
        ga4.runTournamentDoublePt(5000);




    }

}