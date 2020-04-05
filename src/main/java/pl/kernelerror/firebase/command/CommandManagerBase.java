package pl.kernelerror.firebase.command;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.panda_lang.utilities.inject.DependencyInjection;
import org.panda_lang.utilities.inject.Injector;
import org.panda_lang.utilities.inject.InjectorController;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public abstract class CommandManagerBase {
    protected final Injector injector;
    protected CommandMap commandMap;

    public CommandManagerBase() {
        this.injector = DependencyInjection.createInjector();

        initializeCommandMap();
    }

    public CommandManagerBase(InjectorController controller) {
        this.injector = DependencyInjection.createInjector(controller);

        initializeCommandMap();
    }

    public abstract void registerCommand(Class<? extends Command> commandClass, InjectorController controller);

    protected PluginCommand createPluginCommand(Plugin plugin, String name) {
        try {
            Constructor<PluginCommand> pluginCommandConstructor = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
            pluginCommandConstructor.setAccessible(true);
            return pluginCommandConstructor.newInstance(name, plugin);
        } catch (Exception _) {
            throw new RuntimeException("Something went wrong");
        }
    }

    private void initializeCommandMap() {
        try {
            PluginManager pluginManager = Bukkit.getPluginManager();
            Class<?> commandManagerClass = pluginManager.getClass();
            Field commandMapField = commandManagerClass.getDeclaredField("commandMap");
            commandMapField.setAccessible(true);
            commandMap = (CommandMap) commandMapField.get(pluginManager);
        } catch (Exception _) {
            throw new RuntimeException("Something went wrong");
        }
    }
}