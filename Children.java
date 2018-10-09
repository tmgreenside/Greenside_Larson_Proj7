public class Children {
    City[] firstChild;
    City[] secondChild;

    public Children() {
        this.firstChild = new City[10];
        this.secondChild = new City[10];
    }

    public Children(City[] firstChild, City[] secondChild) {
        this.firstChild = firstChild;
        this.secondChild = secondChild;
    }

    public City[] getFirstChild() {
        return firstChild;
    }

    public City[] getSecondChild() {
        return secondChild;
    }
}
