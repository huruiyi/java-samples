package vip.fairy;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import org.apache.log4j.xml.DOMConfigurator;

public class Log4jChangeWatcherService implements Runnable {

  private String configFileName;
  private String fullFilePath;

  public Log4jChangeWatcherService(final String filePath) {
    this.fullFilePath = filePath;
  }

  //This method will be called each time the log4j configuration is changed
  public void configurationChanged(final String file) {
    System.out.println("Log4j configuration file changed. Reloading logging levels !!");
    DOMConfigurator.configure(file);
  }

  public void run() {
    try {
      register(this.fullFilePath);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private void register(final String file) throws IOException {
    final int lastIndex = file.lastIndexOf(File.separator);
    String dirPath = file.substring(0, lastIndex + 1);
    String fileName = file.substring(lastIndex + 1, file.length());
    this.configFileName = fileName;
    configurationChanged(file);
    startWatcher(dirPath, fileName);
  }

  private void startWatcher(String dirPath, String file) throws IOException {
    final WatchService watchService = FileSystems.getDefault().newWatchService();
    //Define the file and type of events which the watch service should handle
    Path path = Paths.get(dirPath);
    path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);
    Runtime.getRuntime().addShutdownHook(new Thread(() -> {
      try {
        watchService.close();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }));
    WatchKey key = null;
    while (true) {
      try {
        key = watchService.take();
        for (WatchEvent<?> event : key.pollEvents()) {
          if (event.context().toString().equals(configFileName)) {
            configurationChanged(dirPath + file);
          }
        }
        boolean reset = key.reset();
        if (!reset) {
          System.out.println("Could not reset the watch key.");
          break;
        }
      } catch (Exception e) {
        System.out.println("InterruptedException: " + e.getMessage());
      }
    }
  }
}
