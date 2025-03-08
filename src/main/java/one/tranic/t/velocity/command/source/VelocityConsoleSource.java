package one.tranic.t.velocity.command.source;

import com.velocitypowered.api.command.CommandSource;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.text.Component;
import one.tranic.t.base.command.source.SystemCommandSource;
import one.tranic.t.velocity.TVelocity;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings("unused")
public class VelocityConsoleSource extends SystemCommandSource<CommandSource, Player> {
    @Override
    public void broadcastMessage(@NotNull Component message) {
        // Unsupported
    }

    @Override
    public void broadcastMessage(@NotNull String message) {
        // Unsupported
    }

    @Override
    public CommandSource getSource() {
        return TVelocity.getServer().getConsoleCommandSource();
    }

    @Override
    public void sendMessage(String message) {
        getSource().sendMessage(Component.text(message));
    }

    @Override
    public void sendMessage(@NotNull Component message) {
        getSource().sendMessage(message);
    }
}
