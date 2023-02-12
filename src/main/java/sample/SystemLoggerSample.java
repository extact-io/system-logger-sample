package sample;

import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import java.text.MessageFormat;
import java.util.ResourceBundle;

public class SystemLoggerSample {
    private static final ResourceBundle RESOURCE = ResourceBundle.getBundle("message");
    private static final Logger logger = System.getLogger(SystemLoggerSample.class.getName());
    private static final Logger loggerWithResource = System.getLogger(SystemLoggerSample.class.getName(), RESOURCE);

    public static void main(String[] args) {
        logger.log(Level.TRACE, "これはTRACEレベルの出力");
        logger.log(Level.DEBUG, "これはDEBUGレベルの出力");
        logger.log(Level.INFO, "これはINFOレベルの出力");
        logger.log(Level.WARNING, "これはWARNレベルの出力");
        logger.log(Level.ERROR, "これはERRORレベルの出力");

        System.out.println(logger.toString());

        logWithResource1(Level.INFO);
        logWithResource2(Level.WARNING);
        logWithLazyEvaluate(Level.ERROR);
    }

    static void logWithResource1(Level level) {
        logger.log(level, "これは{0}レベルの出力", level.name());
        logger.log(level, RESOURCE, "key", level.name());
        logger.log(level, RESOURCE, "これは{0}レベルの出力", level.name());
    }

    static void logWithResource2(Level level) {
        loggerWithResource.log(level, "key1", level.name());
        loggerWithResource.log(level, "これは{0}レベルの出力", level.name());
        loggerWithResource.log(level, "unknown key..", level.name());
    }

    static void logWithLazyEvaluate(Level level) {
        logger.log(level, () -> MessageFormat.format(RESOURCE.getString("key1"), level.name()));
    }
}
