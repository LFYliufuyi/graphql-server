package net.likeyun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * 程序启动
 */
@SpringBootApplication
@Import(BaseConfig.class)
public class WebSiteApp {
    public static void main(String[] args) {
        SpringApplication.run(WebSiteApp.class, args);
    }
}
