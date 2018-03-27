
import java.util.ArrayList;

public class TopDown {
    
    ArrayList<City[]> paths;
    ArrayList<Double> pathLengths;
    
    public void addArrays(City[] path) {
        paths.add(path);
    }
    
    public void elimination() {
        Cities cities = new Cities();
        
        for (City[] path : paths) {
            pathLengths.add(cities.calculateDistance(path));
        }
        
        
    }
}
