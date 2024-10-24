package monopoly.player;

import monopoly.gameplay.MonopolyController;
import monopoly.gameplay.MonopolyProperty;

public class BotPlayer extends MonopolyPlayer {
	
	public BotPlayer(String name, int mMoney, int mPosition) {
		super(name, mMoney, mPosition);
	}
	@Override
	public void handlePropertyBuy(MonopolyProperty property, MonopolyController controller) {
		controller.buyHouse(property, this);
	}
}
