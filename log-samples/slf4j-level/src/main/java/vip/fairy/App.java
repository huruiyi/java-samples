package vip.fairy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;

import java.lang.invoke.MethodHandles;

public class App {

    private static final Logger logger;

    static {
        String simpleName = MethodHandles.lookup().lookupClass().getSimpleName();
        logger = LoggerFactory.getLogger(simpleName);
    }

    public static void main(String[] args) {
        logger.info("Running the sequence");
        runLoop(1);
        runLoop(0);
        logger.info("Sequence completed");

    }

    public static void runLoop(int number) {
        Marker fatal = MarkerFactory.getMarker("FATAL");
        logger.trace("Run loop started");
        if (number == 0) {
            logger.warn("Number:{}", number);
        }
        for (int token = 1; token <= 10; token++) {
            logger.debug("loop number{}",  token);
            logger.trace("Running the division application");
            try {
                int quotient = token / number;
                logger.trace("Quotient is {}" , quotient);
            } catch (Exception e) {
                logger.error(fatal, "Exception in runLoop", e);
                break;
            }
        }
        logger.trace("Run loop exited");
    }
}
