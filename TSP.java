public class TSP {

    public static void main(String args[]) {

        GA ga = new GA();
        ga.generateFirstSetOfPaths();
        System.out.print("TOP DOWN SINGLE POINT CROSSOVER: ");
        ga.runTopDownSinglePt(6000);
        System.out.print(" 1 " + " 5000 generations ");
        ga.printList();
        System.out.println(" " + ga.getFinalDistance());

        GA ga2 = new GA();
        ga2.generateFirstSetOfPaths();
        System.out.println("TOP DOWN DOUBLE POINT CROSSOVER: ");
        ga2.runTopDownDoublePt(5000);
        System.out.print(" 2 " + " 5000 generations ");
        ga2.printList();
        System.out.println(" " + ga2.getFinalDistance());

        GA ga3 = new GA();
        ga3.generateFirstSetOfPaths();
        System.out.println("TOURNAMENT SINGLE POINT CROSSOVER: ");
        ga3.runTournamentSinglePt(6000);
        System.out.print(" 3 " + " 5000 generations ");
        ga3.printList();
        System.out.println(" " + ga3.getFinalDistance());

        GA ga4 = new GA();
        ga4.generateFirstSetOfPaths();
        System.out.println("TOURNAMENT DOUBLE POINT CROSSOVER: ");
        ga4.runTournamentDoublePt(5000);
        System.out.print(" 4 " + " 5000 generations ");
        ga4.printList();
        System.out.println(" " + ga4.getFinalDistance());



        System.out.println();
        System.out.println();
        System.out.println("Run   Num Gens    Circuit  Cost of circuit");
        System.out.print(" 1 " + "   6000 gens   ");
        ga.printList();
        System.out.println(" " + ga.getFinalDistance());
        System.out.print(" 2 " + "   5000 gens   ");
        ga2.printList();
        System.out.println(" " + ga2.getFinalDistance());
        System.out.print(" 3 " + "   6000 gens   ");
        ga3.printList();
        System.out.println(" " + ga3.getFinalDistance());
        System.out.print(" 4 " + "   5000 gens   ");
        ga4.printList();
        System.out.println(" " + ga4.getFinalDistance());





    }

}