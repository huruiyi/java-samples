package com.example;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpResponse;

import java.io.IOException;
import java.util.Scanner;

public class HttpTests {

    /**
     * https://www.w3schools.cn/apache_httpclient/index.html
     */
    public static void main(String[] args) throws IOException {
        //Creating a HttpClient object
        CloseableHttpClient httpclient = HttpClients.createDefault();

        //Creating a HttpGet object
        HttpGet httpget = new HttpGet("https://www.w3schools.cn");

        //Printing the method used
        System.out.println("Request Type: " + httpget.getMethod());

        //Executing the Get request
        HttpResponse httpresponse = httpclient.execute(httpget);


        String reasonPhrase = httpresponse.getReasonPhrase();
        System.out.println(reasonPhrase);

        int code = httpresponse.getCode();
        System.out.printf(String.valueOf(code));




    }
}
