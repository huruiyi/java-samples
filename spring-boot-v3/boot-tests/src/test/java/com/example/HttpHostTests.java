package com.example;

import org.apache.hc.client5.http.async.methods.*;
import org.apache.hc.client5.http.impl.async.CloseableHttpAsyncClient;
import org.apache.hc.client5.http.impl.async.HttpAsyncClients;
import org.apache.hc.client5.http.impl.auth.CredentialsProviderBuilder;
import org.apache.hc.core5.concurrent.FutureCallback;
import org.apache.hc.core5.http.HttpHost;
import org.apache.hc.core5.http.message.StatusLine;
import org.apache.hc.core5.io.CloseMode;

import java.util.concurrent.Future;

public class HttpHostTests {
    public static void main(final String[] args) throws Exception {
        final CloseableHttpAsyncClient httpclient = HttpAsyncClients.custom()
                .setDefaultCredentialsProvider(CredentialsProviderBuilder.create()
                .add(new HttpHost("httpbin.org", 80), "user", "passwd".toCharArray()).build()).build();
        httpclient.start();

        final SimpleHttpRequest request = SimpleRequestBuilder.get("http://httpbin.org/basic-auth/user/passwd").build();

        System.out.println("Executing request " + request);
        final Future<SimpleHttpResponse> future = httpclient.execute(SimpleRequestProducer.create(request), SimpleResponseConsumer.create(), new FutureCallback<SimpleHttpResponse>() {

            @Override
            public void completed(final SimpleHttpResponse response) {
                System.out.println(request + "->" + new StatusLine(response));
                System.out.println(response.getBody());
            }

            @Override
            public void failed(final Exception ex) {
                System.out.println(request + "->" + ex);
            }

            @Override
            public void cancelled() {
                System.out.println(request + " cancelled");
            }

        });
        future.get();

        System.out.println("Shutting down");
        httpclient.close(CloseMode.GRACEFUL);

    }
}
