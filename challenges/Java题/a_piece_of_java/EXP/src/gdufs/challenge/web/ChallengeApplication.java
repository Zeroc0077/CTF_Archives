package gdufs.challenge.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan(basePackages = {"gdufs.challenge.web"})
@SpringBootApplication
/* loaded from: web-0.0.1-SNAPSHOT.jar:BOOT-INF/classes/gdufs/challenge/web/ChallengeApplication.class */
public class ChallengeApplication {
    public static void main(String[] args) {
        SpringApplication.run(ChallengeApplication.class, args);
    }
}
