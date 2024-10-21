package monopoly;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import monopoly.gameplay.*;
import monopoly.io.MonopolyCSVReader;
import monopoly.io.StdInOutHandler;
import monopoly.player.BotPlayer;
import monopoly.player.HumanPlayer;
import monopoly.player.MonopolyPlayer;

public class MainGame {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("usage: ./main playerName");
			System.exit(1);
		}
		String playerName = args[0];
		HumanPlayer player = new HumanPlayer(playerName, 0, 0, StdInOutHandler.INSTANCE);
		List<MonopolyPlayer> players = new ArrayList<>();
		players.add(player);
		
		for (int i = 0; i < 3; i++) {
			players.add(new BotPlayer("Bot-%d".formatted(i), 0, 0));
		}
		
		List<MonopolyProperty> properties = MonopolyCSVReader.readPropertiesFromCSV("tileData.csv");
		List<MonopolyTile> tiles = properties.stream()
											.map(mProperty -> (MonopolyTile) new MonopolyTile(mProperty))
											.collect(Collectors.toCollection(ArrayList::new));
		
		MonopolyController controller = new MonopolyController(players, tiles, StdInOutHandler.INSTANCE);
		controller.startGame();
		
		Choices choice = new Choices(List.of(new Choice("Continue"), new Choice("Info")));
		while (!controller.isFinished()) {
			StdInOutHandler.INSTANCE.writeMessage("\n>>>>>>>>>>>>> Turn %d <<<<<<<<<<<<<".formatted(controller.getTurn()));
			controller.step();
			Choice choice = null;
			do {
				StdInOutHandler.INSTANCE.writeChoice(choices);
				choice = StdInOutHandler.INSTANCE.readChoice(choices);
				if (choice.name().equals("Info")) {
					printInfo(controller);
				}
			} while (!choice.equals(new Choice("Continue")));
		}
		controller.endGame();
	}
	private static void printInfo(MonopolyController controller) {
		for (MonopolyPlayer p: controller.getPlayers()) {
			StdInOutHandler.INSTANCE.writeMessage(p.toString());
			List<MonopolyProperty> properties = controller.getPropertiesForPlayer(p);
			for (MonopolyProperty property : properties) {
				StdInOutHandler.INSTANCE.writeMessage(" %s has %d house, cost of house %d, buyback %d".formatted(property.name(), property.numOfHouses(), property.costOfHouse(), property.buybackRate()));
			}
		}
	}

}
