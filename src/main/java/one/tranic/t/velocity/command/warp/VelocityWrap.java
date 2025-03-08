package one.tranic.t.velocity.command.warp;

import one.tranic.t.base.command.simple.SimpleCommand;
import one.tranic.t.velocity.command.source.VelocitySource;

import java.util.List;


/**
 * A wrapper class that adapts a {@link SimpleCommand} to work within the Velocity platform
 * by implementing the {@link com.velocitypowered.api.command.SimpleCommand} interface.
 * <p>
 * This class facilitates the execution, completion suggestions, and permission checking
 * for commands using a {@link VelocitySource} as the source context.
 * <p>
 * It leverages an instance of a {@link SimpleCommand} with a {@link VelocitySource} type
 * to effectively handle command functionalities, including execution and suggestions,
 * while adhering to Velocity's command structure.
 */
@SuppressWarnings("unused")
public class VelocityWrap implements com.velocitypowered.api.command.SimpleCommand {
    private final SimpleCommand<VelocitySource> command;

    public VelocityWrap(one.tranic.t.base.command.simple.SimpleCommand<VelocitySource> command) {
        this.command = command;
    }

    @Override
    public void execute(Invocation invocation) {
        this.command.execute(new VelocitySource(invocation));
    }

    @Override
    public List<String> suggest(Invocation invocation) {
        return this.command.suggest(new VelocitySource(invocation));
    }

    @Override
    public boolean hasPermission(Invocation invocation) {
        return this.command.hasPermission(new VelocitySource(invocation));
    }
}
