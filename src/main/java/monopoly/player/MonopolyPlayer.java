package monopoly.player;

import game.Player;
import monopoly.gameplay.MonopolyController;
import monopoly.gameplay.MonopolyProperty;

public abstract class MonopolyPlayer implements Player {
	
	private int mMoney;
	private int mPosition;
	private final String mName;
	
	public MonopolyPlayer(String name, int mMoney, int mPosition) {
		this.mName = name;
		this.mMoney = mMoney;
		this.mPosition = mPosition;
	}
	
	@Override 
	public String getName() {
		return mName;
	}
	public int getPosition() {
		return mPosition;
	}
	public void setPosition(int newPosition) {
		mPosition = newPosition;
	}
	public int getMoney() {
		return mMoney;
	}
	public void setMoney(int mMoney) {
		this.mMoney = mMoney;
	}
	abstract public void handlePropertyBuy(MonopolyProperty property, MonopolyController controller);
	
	public final boolean isBankrupt() {
		return mMoney <= 0;
	}
	@Override
	public String toString() {
		return "Name: %s; Money: %d; Position: %d".formatted(mName, mMoney, mPosition);
	}

}
