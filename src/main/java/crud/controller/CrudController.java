package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
//@RequestMapping("/")// дефолтный ЮРЛ для всех методов, ниже уточнение
public class CrudController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView AllUsers() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        return modelAndView;
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public String listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUser());
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() == 0) {
            userService.addUser(user);
        } else {
            userService.updateUser(user);
        }
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") int id) {
        userService.removeUser(id);

        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listUsers", userService.listUser());
        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userdata";
    }
}
