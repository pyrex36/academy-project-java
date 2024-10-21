package monopoly.gameplay;

import java.util.Objects;

public class MonopolyProperty {

    private final int costOfRent;
    private final int costOfHouse;
    private int numOfHouses;
    private final String name;
    private final int buybackRate;

    public MonopolyProperty(int costOfRent, int costOfHouse, int numOfHouses, String name, int buybackRate) {
        this.costOfRent = costOfRent;
        this.costOfHouse = costOfHouse;
        this.numOfHouses = numOfHouses;
        this.name = name;
        this.buybackRate = buybackRate;
    }

    public int costOfRent() {
        return costOfRent;
    }

    public int costOfHouse() {
        return costOfHouse;
    }

    public int numOfHouses() {
        return numOfHouses;
    }

    public String name() {
        return name;
    }

    public int buybackRate() {
        return buybackRate;
    }

    void addHouse() {
        numOfHouses += 1;
    }

    int getLandingCost() {
        return costOfRent * numOfHouses;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (MonopolyProperty) obj;
        return this.costOfRent == that.costOfRent &&
                this.costOfHouse == that.costOfHouse &&
                Objects.equals(this.name, that.name) &&
                this.buybackRate == that.buybackRate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(costOfRent, costOfHouse, name, buybackRate);
    }

    @Override
    public String toString() {
        return "MonopolyProperty[" +
                "costOfRent=" + costOfRent + ", " +
                "costOfHouse=" + costOfHouse + ", " +
                "numOfHouses=" + numOfHouses + ", " +
                "name=" + name + ", " +
                "buybackRate=" + buybackRate + ']';
    }

}
