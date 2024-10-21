package monopoly.gameplay;

import java.util.List;
import java.util.Map;
import monopoly.player.MonopolyPlayer;


public class MonopolyController {
	private static final int NUMBER_OF_DICE = 2;
	private static final int DEFAULT_MONEY = 1000;
	
	private final List<MonopolyPlayer> mPlayers;
	
	private final List<MonopolyTile> mTiles;
	
	private final Map<MonopolyProperty, MonopolyPlayer> mPropertyToPlayerMap;
	
	private final Dice mDie = new Dice(1, 6);
	
	private final IOHandler mIOHandler;
	
	private int mTurnNumber = 1;
	
	public MonopolyController(List<MonopolyPlayer> players, List<MonopolyTile> tiles, IOHandler ioHandler) {
		mPlayers = players;
		this.mPropertyToPlayerMap = new HashMap<>();
		this.mIOHandler = ioHandler;
		this.mTiles = tiles;
	}
	@Override
	public void startGame() {
		mIOHandler.writeMessage("Game has started,");
		mIOHandle.writeMessage("Players:");
		mIOHandler.writeMessage("=====================");
	}

}
	@Override
	public void step() {
		if (isFinished()) {
			return;
		}
		mTurnNumber++;
		List<MonopolyPlayer> activePlayers = getActivePlayers();
		for (MonopolyPlayer player : activePlayers) {
			int playerPoll = mDie.rollMultiple(NUMBER_OF_DICE);
			int newPosition = player.getPosition() + playerRoll;
			if (newPosition >= mTiles.size()) {
				mIOHandler.writeMessage("Player %s passed go, $200 earned!".formatted(player.getName()));
				player.setMoney(player.getMoney() + 200);
			}
			player.setPosition((newPosition % mTiles.size()));
			mIOHandler.writeMessage("%s is now in the tile %s on position %d".formatted(player.getName(), tile.getName(), player.getPosition()));
			tile.onActivate(player, this);
		}
	}
	@Override
	public void endGame() {
		MonopolyPlayer winner = mPlayer.stream()
										.filter(player -> !player.isBankrupt())
										.findFirst()
										.orElseThrow(IllegalStateException::new);
		mIOHandler.writeMessage("Game is done; Winner is %s".formatted(winner.getName()));
	}
	public boolean buyHouse(MonopolyProperty property, MonopolyPlayer player) {
		if (player.getMoney() > property.costOfHouse()) {
			assignProperty(player, property);
			player.setMoney(player.getMoney() - property.costOfHouse());
			property.addHouse();
			mIOHandler.writeMessage("%s bought house on %s for %d".formatted(player.getName(), property.name(), property.costOfHouse()));
			return true;
		} else {
			return false;
		}
	}
	
	public void assignProperty(MonopolyPlayer player, MonopolyProperty property) {
		mPropertyToPlayerMap.put(property, player);
	}
	public boolean isPropertyAssigned(Monopoly property) {
		return mPropertyToPlayerMap.containsKey(property);
	}
	
	public List<MonopolyProperty> getPropertiesForPlayer(MonopolyPlayer player) {
		retrun mPropertyToPlayerMap.entrySet()
									.stream()
									.filter(entry -> entry.getValue().equals(player))
									.map(Map.Entry::getKey)
									.toList();
	}
	
	public MonopolyPlayer getOwner(MonopolyProperty property) {
		return mPropertyToPlayerMap.get(property);
	}
	
	public void handlePayRent(MonopolyPlayer payee, MonopolyPLayer owner, MonopolyProperty rentingProperty) {
		int cost = rentingProperty.getLandingCost();
		mIOHandler.writeMessage("%s has to pay %s %d for landing on %s".formatted(payee.getName(), owner.getName(), cost, rentingProperty.name()));
		int finalMoney = payee.getMoney() - cost;
		if (finalMoney > 0) {
			payee.setMoney(finalMoney);
			owner.setMoney(owner.getMoney() + cost);
			mIOHandler.writeMessage("%s payed %s %d".formatted(payee.getName(), owner.getName(), cost));
		} else {
            // sell off properties; starting from lowest cost
            List<MonopolyProperty> properties = getPropertiesForPlayer(payee)
                    .stream()
                    .sorted(Comparator.comparing(MonopolyProperty::buybackRate))
                    .collect(Collectors.toCollection(ArrayList::new));


            for (MonopolyProperty property : properties) {
                mPropertyToPlayerMap.remove(property);
                finalMoney += property.buybackRate();
                mIOHandler.writeMessage("%s was forced to sell property %s for $%d".formatted(payee.getName(), property.name(), property.buybackRate()));
                if (finalMoney > 0) {
                    break;
                }
            }
            payee.setMoney(finalMoney);
            if (finalMoney > 0) {
                owner.setMoney(owner.getMoney() + cost);
            } else {
                owner.setMoney(owner.getMoney() + cost + finalMoney); // reduce the amount gained by the amount short
            }
        }

        if (payee.getMoney() <= 0) {
            mIOHandler.writeMessage("%s is bankrupt".formatted(payee.getName()));
        }
    }

    public boolean isFinished() {
        return getActivePlayers().size() <= 1;
    }

    public List<MonopolyPlayer> getPlayers() {
        return mPlayers;
    }

    public int getTurn() {
        return mTurnNumber;
    }

    private List<MonopolyPlayer> getActivePlayers() {
        return mPlayers.stream()
                .filter(player1 -> !player1.isBankrupt())
                .toList();
    }
}
