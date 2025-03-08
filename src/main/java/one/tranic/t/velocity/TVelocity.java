package one.tranic.t.velocity;

import com.velocitypowered.api.proxy.ProxyServer;
import one.tranic.t.base.TBase;
import one.tranic.t.base.command.source.CommandSource;
import one.tranic.t.base.command.source.SystemCommandSource;
import one.tranic.t.base.player.Player;
import one.tranic.t.base.player.Players;
import one.tranic.t.utils.Reflect;
import one.tranic.t.velocity.command.source.VelocityConsoleSource;
import one.tranic.t.velocity.player.VelocityPlayer;
import one.tranic.t.velocity.player.VelocityPlayers;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.UUID;
import java.util.function.Function;
import java.util.function.Supplier;

public class TVelocity {
    private static boolean initialized = false;
    private static ProxyServer server;

    public static void init(ProxyServer proxyServer) {
        if (initialized) return;
        server = proxyServer;
        try {
            Reflect.assignToStaticFieldIfUninitialized(TBase.class, "getConsoleSourceSupplier", (Supplier<CommandSource<?, ?>>) TVelocity::getVelocityConsoleSource, false);
            Reflect.assignToStaticFieldIfUninitialized(Players.class, "getPlayerWithStringMethod", (Function<String, Player<?>>) VelocityPlayer::createPlayer, false);
            Reflect.assignToStaticFieldIfUninitialized(Players.class, "getPlayerWithUUIDMethod", (Function<UUID, Player<?>>) VelocityPlayer::createPlayer, false);
            Reflect.assignToStaticFieldIfUninitialized(Players.class, "getOnlinePlayersMethod", (Supplier<List<Player<?>>>) VelocityPlayers::getOnlinePlayers, false);
            Reflect.assignToStaticFieldIfUninitialized(Players.class, "getPlatformOnlinePlayersMethod", (Supplier<List<?>>) VelocityPlayers::getPlatformOnlinePlayers, false);
            Reflect.assignToStaticFieldIfUninitialized(Players.class, "getOnlinePlayersNameMethod", (Supplier<List<String>>) VelocityPlayers::getOnlinePlayersName, false);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        initialized = true;
    }

    public static void disable() {
        initialized = false;
        server = null;
    }

    public static boolean isInitialized() {
        return initialized;
    }

    public static @NotNull ProxyServer getServer() {
        return server;
    }

    public static SystemCommandSource<?, ?> getVelocityConsoleSource() {
        return new VelocityConsoleSource();
    }
}
