package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProjectLogger {
    private static ProjectLogger instance;
    private static Logger logger;

    private ProjectLogger() {

    }

    public static Logger getLogger(Class clazz) {
        if (instance == null) {
            instance = new ProjectLogger();
            logger = LoggerFactory.getLogger(clazz);
        }
            return logger;
    }


}
