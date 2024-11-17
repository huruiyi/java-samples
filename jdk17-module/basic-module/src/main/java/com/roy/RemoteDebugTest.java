package com.roy;

import java.util.Scanner;

/**
 * JDK远程代码调试
 * -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=y,address=5005
 */
public class RemoteDebugTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String command="";
        int count = 0;
        do{
            command = scanner.next();
            System.out.println("第"+(++count)+"个指令："+command);
        }while (!"quit".equals(command));
        System.out.println("接收到退出指令");
        System.exit(-1);
    }
}
