package com.ctf.ezchain;

import com.caucho.hessian.io.Hessian2Input;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.Executors;

public class Index {
    public Index() {
    }

    public static void main(String[] args) throws Exception {
        System.out.println("server start");
        HttpServer server = HttpServer.create(new InetSocketAddress(8090), 0);
        server.createContext("/", new Index.MyHandler());
        server.setExecutor(Executors.newCachedThreadPool());
        server.start();
    }

    static class MyHandler implements HttpHandler {
        MyHandler() {
        }

        public void handle(HttpExchange t) throws IOException {
            String query = t.getRequestURI().getQuery();
            Map<String, String> queryMap = this.queryToMap(query);
            String response = "Welcome to HFCTF 2022";
            if (queryMap != null) {
                String token = (String)queryMap.get("token");
                String secret = "HFCTF2022";
                System.out.println(token);
                if (Objects.hashCode(token) == secret.hashCode() && !secret.equals(token)) {
                    InputStream is = t.getRequestBody();
                    System.out.println(is.read());

                    byte[] is1 = Base64.getDecoder().decode(String.valueOf(is));
                    ByteArrayInputStream is2 = new ByteArrayInputStream(is1);

                    try {



                        Hessian2Input input = new Hessian2Input(is2);
                        input.readObject();
                    } catch (Exception var9) {
                        response = "oops! something is wrong";
                    }
                } else {
                    response = "your token is wrong";
                }
            }

            t.sendResponseHeaders(200, (long)response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }

        public Map<String, String> queryToMap(String query) {
            if (query == null) {
                return null;
            } else {
                Map<String, String> result = new HashMap();
                String[] var3 = query.split("&");
                int var4 = var3.length;

                for(int var5 = 0; var5 < var4; ++var5) {
                    String param = var3[var5];
                    String[] entry = param.split("=");
                    if (entry.length > 1) {
                        result.put(entry[0], entry[1]);
                    } else {
                        result.put(entry[0], "");
                    }
                }

                return result;
            }
        }
    }
}

