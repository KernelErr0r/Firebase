package pl.kernelerror.firebase.command;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface CommandInfo {
    String name();
    String permission() default "";
    String usage() default "";
    String[] aliases() default {};

    String noPermissionMessage() default "";
    String incorrectUsageMessage() default "";
}