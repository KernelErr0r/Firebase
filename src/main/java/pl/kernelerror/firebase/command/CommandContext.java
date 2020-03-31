package pl.kernelerror.firebase.command;

public class CommandContext {
    private final String[] arguments;

    public CommandContext(String[] arguments) {
        this.arguments = arguments;
    }

    public String[] getArguments() {
        return arguments.clone();
    }
}