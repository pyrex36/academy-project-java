package monopoly.player;

import monopoly.gameplay.Choice;
import monopoly.gameplay.Choices;
import monopoly.gameplay.MonopolyController;
import monopoly.gameplay.MonopolyProperty;
import monopoly.io.IOHandler;

import java.util.List;

public class HumanPlayer extends MonopolyPlayer {

    private final IOHandler mIOHandler;

    public HumanPlayer(String name, int mMoney, int mPosition, IOHandler ioHandler) {
        super(name, mMoney, mPosition);
        mIOHandler = ioHandler;
    }

    @Override
    public void handlePropertyBuy(MonopolyProperty property, MonopolyController controller) {
        var ignore = new Choice("Ignore");
        var buy = new Choice("Buy");
        Choices choices = new Choices(List.of(ignore, buy ));
        boolean isChoiceValid = false;

        mIOHandler.writeMessage("Cost of house of %s: %d\n".formatted(property.name(), property.costOfHouse()));
        while (!isChoiceValid) {
            mIOHandler.writeChoices(choices);
            Choice choice = mIOHandler.readChoice(choices);
            if (choice.equals(buy)) {
                if (controller.buyHouse(property, this)) {
                    isChoiceValid = true;
                } else {
                    mIOHandler.writeMessage("Invalid choice, not enough funds.");
                }
            } else {
                isChoiceValid = true;
            }
        }
    }
}
