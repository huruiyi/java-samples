package vip.fairy;

import java.lang.invoke.MethodHandles;
import java.text.MessageFormat;
import java.util.Calendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Substituting Parameters!
 */
public class App {

  private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

  public static void main(String[] args) {
    String user = "john";
    String application = "gateway";

    // Crafting a message without substitution.
    // Not a good idea as the String concatenation and evaluation will happen irrespective of whether
    // logging level is permissible or not to be logged.
    logger.info("Bad experience for user {} at time {}", user, Calendar.getInstance().getTime());

    // Substitution with one formatting anchor and one argument
    logger.info("Bad experience for user {}", user);

    // If you happen to forget to provide a substituting object
    logger.info("Bad experience for user {}");

    // Substitution with two formatting anchors and two arguments
    logger.info("Bad experience for user {} at time {}", user, Calendar.getInstance().getTime());

    // Substitution with three formatting anchors and three arguments
    logger.info("Bad experience for user {} at time {} while accessing {}", user, Calendar.getInstance().getTime(), application);

    // Escaping formatting anchor
    logger.info("ERROR CODE \\{}; Bad experience for user {} at time {}", user, Calendar.getInstance().getTime());

    // Formatting anchor with data inside; no problem
    logger.info("ERROR CODE {22}; Bad experience for user {} at time {}", user, Calendar.getInstance().getTime());

    // Crafting a message with Java's own MessageFormatter.
    // Not a good idea as per SLF4J's documentation.
    // 1. SLF4J's implementation is 10 times faster than that of MessageFormat.
    // 2. Moreover to make sure that the evaluation happens only if that particular logging
    // level is allowed, you need to do a check.
    if (logger.isInfoEnabled()) {
      String message = MessageFormat.format("Bad experience for user {0} at time {1} while accessing {2}",
          user, Calendar.getInstance().getTime(), application);
      logger.info(message);
    }
  }
}
