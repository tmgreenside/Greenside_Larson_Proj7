public class City {
    int x_coor;
    int y_coor;

    @Override
    public String toString() {
        return ("x= " + x_coor + " y= " + y_coor);
    }

    public City(int x_coor, int y_coor) {
        this.x_coor = x_coor;
        this.y_coor = y_coor;
    }

    public int getX_coor() {

        return x_coor;
    }

    public void setX_coor(int x_coor) {
        this.x_coor = x_coor;
    }

    public int getY_coor() {
        return y_coor;
    }

    public void setY_coor(int y_coor) {
        this.y_coor = y_coor;
    }
}
