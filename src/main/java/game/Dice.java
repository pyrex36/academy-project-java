package game;

import java.util.Random;

public final class Dice {
    private final int mMinimum;
    private final int mMaximum;

    public Dice(int mMinimum, int mMaximum) {
        this.mMinimum = mMinimum;
        this.mMaximum = mMaximum;
    }

    public int roll() {
        Random random = new Random();
        return (random.nextInt(mMaximum - 1) + mMinimum);
    }

    public int rollMultiple(int numberOfDice) {
        int total = 0;
        for (int i = 0; i < numberOfDice; ++i) {
            total += roll();
        }
        return total;
    }

}
