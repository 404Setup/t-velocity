package one.tranic.t.velocity.player;

import one.tranic.t.base.player.Player;
import one.tranic.t.utils.Collections;
import one.tranic.t.velocity.TVelocity;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class VelocityPlayers {
    public static @NotNull List<Player<?>> getOnlinePlayers() {
        final List<Player<?>> players = Collections.newArrayList();

        for (var p : TVelocity.getServer().getAllPlayers())
            players.add(VelocityPlayer.createPlayer(p));
        return players;
    }

    public static @NotNull List<? extends com.velocitypowered.api.proxy.Player> getPlatformOnlinePlayers() {
        return Collections.newArrayList(TVelocity.getServer().getAllPlayers());
    }

    public static @NotNull List<String> getOnlinePlayersName() {
        final List<String> players = Collections.newArrayList();
        for (var p : TVelocity.getServer().getAllPlayers())
            players.add(p.getUsername());
        return players;
    }
}
