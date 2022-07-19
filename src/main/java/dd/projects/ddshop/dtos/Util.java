package dd.projects.ddshop.dtos;

import dd.projects.ddshop.AppConfiguration;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class Util {

    private static final MessageSource messageSource = new AppConfiguration().messageSource();

    public static String getMessage(final String message, Object[] params) {
       return messageSource.getMessage(message,params, Locale.ENGLISH);
    }
}
