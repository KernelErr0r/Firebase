package pl.kernelerror.firebase.command;

import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.panda_lang.utilities.inject.InjectorController;
import org.panda_lang.utilities.inject.InjectorException;

import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class SimpleCommandManager extends CommandManagerBase {
    private Plugin plugin;

    public SimpleCommandManager(Plugin plugin) {
        super();

        this.plugin = plugin;
    }

    public SimpleCommandManager(Plugin plugin, InjectorController controller) {
        super(controller);

        this.plugin = plugin;
    }

    @Override
    public void registerCommand(Class<? extends Command> commandClass, InjectorController controller) {
        try {
            Command command = injector.fork(controller)
                    .newInstance(commandClass);
            CommandInfo commandInfo = commandClass.getDeclaredAnnotation(CommandInfo.class);
            PluginCommand pluginCommand = createPluginCommand(plugin, commandInfo.name());

            pluginCommand.setAliases(Arrays.asList(commandInfo.aliases()));
            pluginCommand.setExecutor((commandSender, command1, label, arguments) -> {
                if (commandInfo.permission().equals("") || commandSender.hasPermission(commandInfo.permission())) {
                    try {
                        command.execute(commandSender, new CommandContext(commandInfo, arguments));
                    } catch (ValidationException exception) {
                        commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', exception.getMessage()));
                    }
                } else {
                    commandSender.sendMessage(ChatColor.translateAlternateColorCodes('&', commandInfo.noPermissionMessage()));
                }

                return true;
            });
            commandMap.register(commandInfo.name(), pluginCommand);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | InjectorException exception) {
            throw new CommandException("Something went wrong during the registration of the command", exception);
        }
    }
}