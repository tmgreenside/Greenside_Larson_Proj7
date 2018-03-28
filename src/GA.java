import java.util.ArrayList;
import java.util.Arrays;

public class GA {
    ArrayList<City[]> paths = new ArrayList<>();

    public void addArrays(City[] path){
        paths.add(path);
    }
    //top down
    public void generateChildrenDoublePoint(){
        Children childSet;
        DoublePtCrossover doublePtCross = new DoublePtCrossover();
        int pairs = paths.size()/2;
        int counter = 0;
        while(counter < pairs){
            childSet = doublePtCross.doubleCrossover(paths.get(counter),paths.get(counter+1));
            if(!Arrays.asList(paths).contains(childSet.getFirstChild()) && !Arrays.asList(paths).contains(childSet.getSecondChild())){
                paths.add(counter,childSet.getFirstChild());
                paths.add(counter,childSet.getSecondChild());
                counter = counter + 2;
            }
        }
    }
    //top down
    public void generateChildrenSinglePoint(){
        Children childSet;
        singlePtCrossover singlePoint = new singlePtCrossover();
        int pairs = paths.size()/2;
        int counter = 0;
        while(counter < pairs){
            childSet = singlePoint.crossover(paths.get(counter),paths.get(counter+1));
            if(!Arrays.asList(paths).contains(childSet.getFirstChild()) && !Arrays.asList(paths).contains(childSet.getSecondChild())){
                paths.add(counter,childSet.getFirstChild());
                paths.add(counter+1,childSet.getSecondChild());
                counter = counter + 2;
            }

        }
    }

    public void elimination(){
        Cities cities = new Cities();
        int size = paths.size()/2;
        for (int i = 0; i < paths.size() - 1; i++) {
            for(int j = 0; j < paths.size() - i - 1; j++){
                double distance1 = cities.calculateDistance(paths.get(j));
                double distance2 = cities.calculateDistance(paths.get(j + 1));
                if(distance1 > distance2){
                    City[] temp = paths.get(j);

                }
            }


        }
    }
}
