package vip.fairy.utils;

import vip.fairy.Log4jConfigReloadExample;

import java.io.File;

public class PathUtils {

    public static final String getConfigPath() {
        File file = new File(Log4jConfigReloadExample.class.getResource("/log4j-config.xml").getPath());
return  file.getAbsolutePath();
    }
}
