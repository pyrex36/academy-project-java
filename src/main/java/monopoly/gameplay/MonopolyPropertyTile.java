package monopoly.gameplay;

import monopoly.player.MonopolyPlayer;

public class MonopolyPropertyTile implements MonopolyTile {

    private final MonopolyProperty mProperty;

    public MonopolyPropertyTile(MonopolyProperty mProperty) {
        this.mProperty = mProperty;
    }

    @Override
    public String getName() {
        return mProperty.name();
    }

    @Override
    public void onActivate(MonopolyPlayer player, MonopolyController controller) {
        if (controller.isPropertyAssigned(mProperty) && !controller.getOwner(mProperty).equals(player)) {
            controller.handlePayRent(player, controller.getOwner(mProperty), mProperty);
        } else {
            player.handlePropertyBuy(mProperty, controller);
        }
    }
}
