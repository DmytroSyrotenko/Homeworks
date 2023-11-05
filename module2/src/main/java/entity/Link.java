package entity;

public class Link {
    Integer indexOfA;
    Integer indexOfB;
    Integer cost;

    public Link() {
    }

    public Link(Integer indexOfA, Integer indexOfB, Integer cost) {
        this.indexOfA = indexOfA;
        this.indexOfB = indexOfB;
        this.cost = cost;
    }

    public Integer getIndexOfA() {
        return indexOfA;
    }

    public void setIndexOfA(Integer indexOfA) {
        this.indexOfA = indexOfA;
    }

    public Integer getIndexOfB() {
        return indexOfB;
    }

    public void setIndexOfB(Integer indexOfB) {
        this.indexOfB = indexOfB;
    }

    @Override
    public String toString() {
        return "Link{" + "indexOfA=" + indexOfA + ", indexOfB=" + indexOfB + ", cost=" + cost + '}';
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }
}