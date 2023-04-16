package com.github.stickerifier.stickerify.logger;

import static ch.qos.logback.core.pattern.color.ANSIConstants.BOLD;
import static ch.qos.logback.core.pattern.color.ANSIConstants.DEFAULT_FG;
import static ch.qos.logback.core.pattern.color.ANSIConstants.ESC_END;
import static ch.qos.logback.core.pattern.color.ANSIConstants.ESC_START;
import static ch.qos.logback.core.pattern.color.ANSIConstants.GREEN_FG;
import static com.github.stickerifier.stickerify.telegram.model.TelegramRequest.NEW_USER;

import ch.qos.logback.classic.pattern.MessageConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.Converter;

import java.util.regex.Pattern;

/**
 * Custom converter class to be used by Logback in order to
 * highlight substrings of the message to be logged.
 *
 * @see Converter
 */
public class NewUserHighlighter extends MessageConverter {

	static final String START_GREEN = changeColorTo(BOLD + GREEN_FG);
	static final String RESET_COLOR = changeColorTo(DEFAULT_FG);
	static final String HIGHLIGHTED_NEW_USER = START_GREEN + NEW_USER + RESET_COLOR;

	@Override
	public String convert(ILoggingEvent event) {
		String message = event.getFormattedMessage();

		if (message != null && message.contains(NEW_USER)) {
			return message.replaceFirst(Pattern.quote(NEW_USER), HIGHLIGHTED_NEW_USER);
		}

		return message;
	}

	private static String changeColorTo(final String color) {
		return ESC_START + color + ESC_END;
	}
}
