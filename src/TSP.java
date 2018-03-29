public class TSP {

    public static void main(String args[]) {

        GA ga = new GA();
        ga.generateFirstSetOfPaths();
        ga.runTopDownDoublePt(3000);



    }

}