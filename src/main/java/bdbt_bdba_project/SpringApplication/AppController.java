package bdbt_bdba_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;
import java.util.Map;

@Controller
public class AppController{

    @Autowired
    private KlubyDAO dao;
    @Autowired
    private ZawodnicyDAO dao1;
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
    @RequestMapping("/main_user/{nr_zawodnika}")
    public ModelAndView showUserData(@PathVariable(name = "nr_zawodnika")int nr_zawodnika){
        ModelAndView mav = new ModelAndView("user/main_user");
        Zawodnicy zawodnicy = dao1.getZ(nr_zawodnika);
        mav.addObject("zawodnicy",zawodnicy);
        return mav;
    }

    @RequestMapping(value = "/main_user")
    public String vievUser(Model model, Principal principal){
        String name = principal.getName();
        int name1 = Integer.parseInt(name);
        List<Zawodnicy> zawodnicyList= dao1.list(name1);
        model.addAttribute("zawodnicyList",zawodnicyList);
        return "user/main_user";
    }











    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(@ModelAttribute("Kluby") Kluby kluby){
        dao.update(kluby);
        return "redirect:showKluby";

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

   //@RequestMapping(value={"/main_user"})
   // public String showUserPage(Model model) {
      //  return "user/main_user";
   // }

    //@RequestMapping(value={"/add_Klub"})
   // public String showAddingPanel(Model model) {
     //   return "admin/add_Klub";
   // }




}