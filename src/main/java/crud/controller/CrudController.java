package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CrudController {
    private UserService userService;

    @Autowired
    public CrudController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = {"/", "/welcome"}, method = RequestMethod.GET)
    public String getHello(Model model) {
        model.addAttribute("title", "welcome");
        model.addAttribute("message", "Aloha, man!");
        return "welcome";
    }

    @GetMapping("/login")//когда будет вызван метод Get по юрлу /login сработает данный метод
    public String login() {
        return "login";
    }

    @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
    public String t() {
        return "test";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET) //возвращать объект
    public Model listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUser());
        return model;
    }

    @RequestMapping(value = "/admin/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() != null) {//есть ли какие-то другие способы проверки на существование объектов в базе ?
//            userService.addUser(user);
            userService.updateUser(user);
        } else {
            userService.addUser(user);
        }
        return "redirect:/admin";
    }

    @RequestMapping("/admin/remove/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);

        return "redirect:/admin";
    }

    @RequestMapping("/admin/edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listUsers", userService.listUser());
        return "admin";
    }

    @RequestMapping(value = "/user/{id}")
    public String userData(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "user";
    }

    @RequestMapping(path = "/user", method = RequestMethod.GET)
    public Model getUserPage(Model model) {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", userService.findByUsername(authUser.getName()));
        return model;
    }


}
