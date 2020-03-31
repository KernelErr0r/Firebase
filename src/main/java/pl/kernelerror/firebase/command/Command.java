package pl.kernelerror.firebase.command;

import org.bukkit.command.CommandSender;

public interface Command {
    void execute(CommandSender sender, CommandContext context) throws ValidationException;
}