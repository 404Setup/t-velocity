package one.tranic.t.velocity;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.ProxyServer;
import one.tranic.t.base.TBase;
import one.tranic.t.base.TInterface;
import one.tranic.t.base.command.source.SystemCommandSource;
import one.tranic.t.base.player.Player;
import one.tranic.t.utils.Collections;
import one.tranic.t.utils.minecraft.Platform;
import one.tranic.t.velocity.command.source.VelocityConsoleSource;
import one.tranic.t.velocity.player.VelocityPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

@SuppressWarnings("unused")
public class TVelocity implements TInterface<CommandSource, com.velocitypowered.api.proxy.Player> {
    private static boolean initialized = false;
    private final Platform[] supportedPlatforms = new Platform[]{Platform.Velocity};
    private final ProxyServer server;

    public TVelocity(ProxyServer proxyServer) {
        this.server = proxyServer;
        enable();
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static @NotNull ProxyServer getServer() {
        return ((TVelocity) TBase.INSTANCE).server;
    }

    @Override
    public void enable() {
        if (initialized) return;
        initialized = true;
    }

    @Override
    public void disable() {
        initialized = false;
    }

    @Override
    public Platform[] getSupportedPlatforms() {
        return supportedPlatforms;
    }

    @Override
    public SystemCommandSource<CommandSource, com.velocitypowered.api.proxy.Player> getConsoleSource() {
        return new VelocityConsoleSource();
    }

    @Override
    public @Nullable Player<com.velocitypowered.api.proxy.Player> getPlayer(@NotNull String name) {
        return VelocityPlayer.createPlayer(name);
    }

    @Override
    public @Nullable Player<com.velocitypowered.api.proxy.Player> getPlayer(@NotNull UUID uuid) {
        return VelocityPlayer.createPlayer(uuid);
    }

    @Override
    public @NotNull List<Player<com.velocitypowered.api.proxy.Player>> getOnlinePlayers() {
        final List<Player<com.velocitypowered.api.proxy.Player>> players = Collections.newArrayList();

        for (var p : TVelocity.getServer().getAllPlayers())
            players.add(VelocityPlayer.createPlayer(p));
        return players;
    }

    @Override
    public @NotNull List<com.velocitypowered.api.proxy.Player> getPlatformOnlinePlayers() {
        return Collections.newArrayList(TVelocity.getServer().getAllPlayers());
    }

    @Override
    public @NotNull List<String> getOnlinePlayersName() {
        final List<String> players = Collections.newArrayList();
        for (var p : TVelocity.getServer().getAllPlayers())
            players.add(p.getUsername());
        return players;
    }
}
