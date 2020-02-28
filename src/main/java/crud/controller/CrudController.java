package crud.controller;

import crud.model.User;
import crud.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")// дефолтный ЮРЛ для всех методов, ниже уточнение
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

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }

        return "login";
    }

//    @RequestMapping(value = "/login", method = RequestMethod.GET)
//    public String allUsers() {
//        return "login";
//    }

//    @RequestMapping(value = "/", method = RequestMethod.POST)
//
//    public String allUsers() {
//        return "login";
//    }

    @RequestMapping(value = "/users", method = RequestMethod.GET) //возвращать объект
    public Model listUsers(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("listUsers", userService.listUser());
        return model;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (user.getId() != null) {//есть ли какие-то другие способы проверки на существование объектов в базе ?
//            userService.addUser(user);
            userService.updateUser(user);
        } else {
            userService.addUser(user);
        }
        return "redirect:/users";
    }

    @RequestMapping("/remove/{id}")
    public String removeUser(@PathVariable("id") Long id) {
        userService.removeUser(id);

        return "redirect:/users";
    }

    @RequestMapping("edit/{id}")
    public String editUser(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("listUsers", userService.listUser());
        return "users";
    }

    @RequestMapping("userdata/{id}")
    public String userData(@PathVariable("id") Long id, Model model) {
        model.addAttribute("user", userService.getUserById(id));
        return "userdata";
    }
}
