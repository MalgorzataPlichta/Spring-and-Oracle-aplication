package bdbt_bdba_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AppController{

    @Autowired
    private KlubyDAO dao;
    @Controller
    public class DashboardController
    {
        @RequestMapping
                ("/main"
                )
        public String defaultAfterLogin
                (HttpServletRequest request) {
            if
            (request.isUserInRole
                    ("ADMIN")) {
                return "redirect:/main_admin";
            }
            else if
            (request.isUserInRole
                            ("USER")) {
                return "redirect:/main_user";
            }
            else
            {
                return "redirect:/index";
            }
        }
    }



    @RequestMapping("/")
    public String viewHomePage(Model model){
        List<Kluby> klubyList= dao.list();
        model.addAttribute("klubyList",klubyList);
        return "index";
    }

    @RequestMapping("/showKluby")
    public String viewAdminKlub(Model model){
        List<Kluby> klubyList= dao.list();
        model.addAttribute("klubyList",klubyList);
        return "admin/showKluby";
    }
    @RequestMapping("/add_Klub")
    public String vievAdminAddingPanel(Model model){
        Kluby klub =new Kluby();
        model.addAttribute("klub",klub);
        return "admin/add_Klub";
    }
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(@ModelAttribute("klub")Kluby klub){
        dao.save(klub);
        return "/";
    }
    @RequestMapping("/edit/{nr_klubu}")
    public ModelAndView showEditForm(@PathVariable(name = "nr_klubu")int nr_klubu){
        ModelAndView mav = new ModelAndView("admin/editKluby");
        Kluby kluby = dao.get(nr_klubu);
        mav.addObject("kluby",kluby);
        return mav;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("Kluby") Kluby kluby){
        if (dao.update(kluby)==1) {

            return "redirect:showKluby";
        }
        if (dao.update(kluby)==0){
            return "redirect:errors/504";

        }
        else
        return "redirect:/";

    }



    @RequestMapping("/delete/{nr_klubu}")
    public String delete(@PathVariable(name = "nr_klubu") int nr_klubu){
        dao.delete(nr_klubu);

        return "redirect:/";
    }

    @RequestMapping("/about")
    public String viewPage(){
        return "about";
    }



    @RequestMapping(value={"/main_admin"})
    public String showAdminPage(Model model) {
        return "admin/main_admin";
    }

    @RequestMapping(value={"/main_user"})
    public String showUserPage(Model model) {
        return "user/main_user";
    }

    //@RequestMapping(value={"/add_Klub"})
   // public String showAddingPanel(Model model) {
     //   return "admin/add_Klub";
   // }




}