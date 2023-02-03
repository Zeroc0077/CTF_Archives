package gdufs.challenge.web.controller;

import gdufs.challenge.web.model.Info;
import gdufs.challenge.web.model.UserInfo;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Base64;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import org.nibblesec.tools.SerialKiller;
import org.springframework.beans.factory.xml.BeanDefinitionParserDelegate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
/* loaded from: web-0.0.1-SNAPSHOT.jar:BOOT-INF/classes/gdufs/challenge/web/controller/MainController.class */
public class MainController {
    @GetMapping({"/index"})
    public String index(@CookieValue(value = "data", required = false) String cookieData) {
        if (cookieData != null && !cookieData.equals("")) {
            return "redirect:/hello";
        }
        return BeanDefinitionParserDelegate.INDEX_ATTRIBUTE;
    }

    @PostMapping({"/index"})
    public String index(@RequestParam("username") String username, @RequestParam("password") String password, HttpServletResponse response) {
        UserInfo userinfo = new UserInfo();
        userinfo.setUsername(username);
        userinfo.setPassword(password);
        Cookie cookie = new Cookie("data", serialize(userinfo));
        cookie.setMaxAge(2592000);
        response.addCookie(cookie);
        return "redirect:/hello";
    }

    @GetMapping({"/hello"})
    public String hello(@CookieValue(value = "data", required = false) String cookieData, Model model) {
        if (cookieData == null || cookieData.equals("")) {
            return "redirect:/index";
        }
        Info info = (Info) deserialize(cookieData);
        if (info != null) {
            model.addAttribute("info", info.getAllInfo());
            return "hello";
        }
        return "hello";
    }

    private String serialize(Object obj) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            oos.close();
            return new String(Base64.getEncoder().encode(baos.toByteArray()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private Object deserialize(String base64data) {
        ByteArrayInputStream bais = new ByteArrayInputStream(Base64.getDecoder().decode(base64data));
        try {
            ObjectInputStream ois = new SerialKiller(bais, "serialkiller.conf");
            Object obj = ois.readObject();
            ois.close();
            return obj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
