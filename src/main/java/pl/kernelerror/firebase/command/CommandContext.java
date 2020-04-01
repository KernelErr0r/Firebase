package pl.kernelerror.firebase.command;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommandContext {
    private final CommandInfo commandInfo;
    private final String[] arguments;

    private final String incorrectUsageMessage;

    public CommandContext(CommandInfo commandInfo, String[] arguments) {
        this.commandInfo = commandInfo;
        this.arguments = arguments;

        this.incorrectUsageMessage = commandInfo.incorrectUsageMessage()
                .replaceFirst(Pattern.quote("{USAGE}"), Matcher.quoteReplacement(commandInfo.usage()));
    }

    public Player getPlayer(int index) throws ValidationException {
        Validate.correctIndex(arguments, index, incorrectUsageMessage);

        Player player = Bukkit.getPlayer(arguments[index]);

        if (player == null) {
            throw new ValidationException(incorrectUsageMessage);
        }

        return player;
    }

    public String getString(int index) throws ValidationException {
        Validate.correctIndex(arguments, index, incorrectUsageMessage);

        return arguments[0];
    }

    public double getDouble(int index) throws ValidationException {
        Validate.correctIndex(arguments, index, incorrectUsageMessage);

        try {
            return Integer.parseInt(arguments[index]);
        } catch (NumberFormatException exception) {
            throw new ValidationException(incorrectUsageMessage);
        }
    }

    public int getInteger(int index) throws ValidationException {
        Validate.correctIndex(arguments, index, incorrectUsageMessage);

        try {
            return Integer.parseInt(arguments[index]);
        } catch (NumberFormatException exception) {
            throw new ValidationException(incorrectUsageMessage);
        }
    }

    public boolean hasArgument(int index) {
        return index >= 0 && index < arguments.length;
    }

    public CommandInfo getCommandInfo() {
        return commandInfo;
    }

    public String[] getArguments() {
        return arguments.clone();
    }
}