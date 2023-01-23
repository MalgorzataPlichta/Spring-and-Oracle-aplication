package bdbt_bdba_project.SpringApplication;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;


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

    @RequestMapping("/showUsers")
    public String viewAdminUsers(Model model){
        List<Zawodnicy> zawodnicyList= dao1.allList();
        model.addAttribute("zawodnicyList",zawodnicyList);
        return "admin/showUsers";
    }
    @RequestMapping("/delete/{nr_klubu}")
    public String delete(@PathVariable(name = "nr_klubu") int nr_klubu){
        dao.delete(nr_klubu);

        return "redirect:/";
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
        return "redirect:showKluby";
    }
    @RequestMapping("/edit/{nr_klubu}")
    public ModelAndView showEditForm(@PathVariable(name = "nr_klubu")int nr_klubu){
        ModelAndView mav = new ModelAndView("admin/editKluby");
        Kluby kluby = dao.get(nr_klubu);
        mav.addObject("kluby",kluby);
        return mav;
    }
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update1(@ModelAttribute("Kluby") Kluby kluby , RedirectAttributes redirAttrs){
        if (dao.update(kluby)==1) {

            return "redirect:showKluby";
        }
        if (dao.update(kluby)==0){
            redirAttrs.addFlashAttribute("error", "The error 500 occurred.");
        }
        return "redirect:DataError";
    }


    @RequestMapping(value = "/update2", method = RequestMethod.POST)
    public String update3(@ModelAttribute("Zawodnicy") Zawodnicy zawodnicy){
        if (dao1.update2(zawodnicy)==1) {

            return "redirect:main_user";
        }
        else if (dao1.update2(zawodnicy)==0){
            return "redirect:DataError";
        }

        return "redirect:/";
    }
    @RequestMapping(value = "/save2", method = RequestMethod.POST)
    public String save(@ModelAttribute("zawodnicy")Zawodnicy zawodnicy){
        dao1.save2(zawodnicy);
        return "/";
    }

    @RequestMapping(value = "/main_user")
    public String vievUser(Model model, Principal principal){
        String name = principal.getName();
        int name1 = Integer.parseInt(name);
        List<Zawodnicy> zawodnicyList= dao1.list(name1);
        model.addAttribute("zawodnicyList",zawodnicyList);
        return "user/main_user";
    }

    @RequestMapping("/editUserData")
    public ModelAndView updateUserData(Principal principal){
        String name = principal.getName();
        int name1 = Integer.parseInt(name);
        ModelAndView mav = new ModelAndView("user/editUserData");
        Zawodnicy zawodnicy = dao1.get2(name1);
        mav.addObject("zawodnicy",zawodnicy);
        return mav;
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