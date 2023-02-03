package gdufs.challenge.web.invocation;

import gdufs.challenge.web.model.Info;
import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/* loaded from: web-0.0.1-SNAPSHOT.jar:BOOT-INF/classes/gdufs/challenge/web/invocation/InfoInvocationHandler.class */
public class InfoInvocationHandler implements InvocationHandler, Serializable {
    private Info info;

    public InfoInvocationHandler(Info info) {
        this.info = info;
    }

    @Override // java.lang.reflect.InvocationHandler
    public Object invoke(Object proxy, Method method, Object[] args) {
        try {
            if (method.getName().equals("getAllInfo") && !this.info.checkAllInfo().booleanValue()) {
                return null;
            }
            return method.invoke(this.info, args);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
