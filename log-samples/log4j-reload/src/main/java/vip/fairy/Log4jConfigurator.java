package vip.fairy;

public class Log4jConfigurator {
    //This ensures singleton instance of configurator
    private final static Log4jConfigurator INSTANCE = new Log4jConfigurator();

    public static Log4jConfigurator getInstance() {
        return INSTANCE;
    }

    //This method will start the watcher service of log4j.xml file and also configure the loggers
    public void initilize(final String file) {
        try {
            //Create the watch service thread and start it.
            //I will suggest to use some logic here which will check if this thread is still alive;
            //If thread is killed then restart the thread
            Log4jChangeWatcherService listner = new Log4jChangeWatcherService(file);

            //Start the thread
            new Thread(listner).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
