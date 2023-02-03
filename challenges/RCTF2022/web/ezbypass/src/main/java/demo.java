import ognl.Ognl;
import ognl.OgnlException;

/**
 * @author zeroc
 * @date 2022/12/26 19:52
 */
public class demo {
    public static void main(String[] argv) throws OgnlException {
        Object object = Ognl.getValue("@java.lang.Character@toString(39)", null);
        System.out.println(object);
        System.out.println("\u0027");
        System.out.println(("a").replace((char) 97, (char) 39));
        System.out.println(byte[].class.getName());
    }
}
