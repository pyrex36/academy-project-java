package monopoly.gameplay;

import monopoly.player.MonopolyPlayer;

public interface MonopolyTile {
    String getName();

    void onActivate(MonopolyPlayer player, MonopolyController controller);


}
