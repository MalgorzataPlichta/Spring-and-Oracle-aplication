package bdbt_bdba_project.SpringApplication;

import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@org.springframework.context.annotation.Configuration
public class Configuration implements WebMvcConfigurer {


    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main_admin").setViewName("admin/main_admin");
        registry.addViewController("/main_user").setViewName("user/main_user");
        registry.addViewController("/add_Klub").setViewName("admin/add_Klub");
        registry.addViewController("/showKluby").setViewName("admin/showKluby");
        registry.addViewController("/editKluby").setViewName("admin/editKluby");
        registry.addViewController("/editUserData").setViewName("user/editUserData");
        registry.addViewController("/showUsers").setViewName("admin/showUsers");
    }

}
