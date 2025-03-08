package one.tranic.t.velocity.command.source;

import com.velocitypowered.api.command.SimpleCommand;
import com.velocitypowered.api.proxy.Player;
import net.kyori.adventure.bossbar.BossBar;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import one.tranic.t.base.TBase;
import one.tranic.t.base.command.Operator;
import one.tranic.t.base.command.source.CommandSource;
import one.tranic.t.velocity.player.VelocityPlayer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Locale;

@SuppressWarnings("all")
public class VelocitySource implements CommandSource<com.velocitypowered.api.command.CommandSource, Player> {
    private final SimpleCommand.Invocation invocation;
    private final com.velocitypowered.api.command.CommandSource commandSource;
    private final VelocityPlayer player;

    public VelocitySource(com.velocitypowered.api.command.CommandSource commandSource) {
        this.invocation = null;
        this.commandSource = commandSource;
        this.player = commandSource instanceof Player ? new VelocityPlayer(commandSource) : null;
    }

    public VelocitySource(SimpleCommand.Invocation invocation) {
        this.invocation = invocation;
        this.commandSource = invocation.source();
        this.player = commandSource instanceof Player ? new VelocityPlayer(commandSource) : null;
    }

    @Override
    public Operator getOperator() {
        if (player != null)
            return new Operator(player.getUsername(), player.getUniqueId());
        return TBase.console();
    }

    @Override
    public com.velocitypowered.api.command.CommandSource getSource() {
        return commandSource;
    }

    @Override
    public boolean isPlayer() {
        return player != null;
    }

    @Override
    public String[] getArgs() {
        return invocation.arguments();
    }

    @Override
    public int argSize() {
        return invocation.arguments().length;
    }

    @Override
    public @Nullable Locale getLocale() {
        return player != null ? player.getLocale()
                : Locale.getDefault();
    }

    @Override
    public boolean hasPermission(String permission) {
        return commandSource.hasPermission(permission);
    }

    @Override
    public void sendMessage(String message) {
        commandSource.sendMessage(Component.text(message));
    }

    @Override
    public void sendMessage(@NotNull Component message) {
        commandSource.sendMessage(message);
    }

    @Override
    public void showBossBar(@NotNull BossBar bossBar) {
        if (isPlayer()) player.getSourcePlayer().showBossBar(bossBar);
    }

    @Override
    public void hideBossBar(@NotNull BossBar bossBar) {
        if (isPlayer()) player.getSourcePlayer().hideBossBar(bossBar);
    }

    @Override
    public void clearBossBars() {
        // Unsupported
    }

    @Override
    public void showTitle(@NotNull Title title) {
        if (isPlayer()) player.getSourcePlayer().showTitle(title);
    }

    @Override
    public void clearTitle() {
        if (isPlayer()) player.getSourcePlayer().clearTitle();
    }

    @Override
    public @Nullable VelocityPlayer asPlayer() {
        return player;
    }
}
