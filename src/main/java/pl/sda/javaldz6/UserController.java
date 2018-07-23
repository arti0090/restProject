package pl.sda.javaldz6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import pl.sda.javaldz6.model.User;
import pl.sda.javaldz6.service.UserService;
import pl.sda.javaldz6.service.UserServiceAnnotationSingleton;

import javax.validation.Valid;
import java.net.BindException;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Controller
public class UserController {

    private Logger log = Logger.getLogger(String.valueOf(UserController.class));

    @Autowired
    @Qualifier("userServiceAnnotationSingleton")
    private pl.sda.javaldz6.service.UserService userServiceAnnotationSingleton;

    @GetMapping(value = "/users")
    public String getUsers(Model model) {
        model.addAttribute("users", userServiceAnnotationSingleton.getAll());
        return "users";
    }

//    @GetMapping(value = "/users1")
//    public ModelAndView getUsersModel(Model model) {
//        ModelAndView mv = new ModelAndView();
//        mv.addObject("users", userServiceAnnotationSingleton.getAll());
//        mv.setViewName("users");
//        return mv;
//    }

    @GetMapping(value = "/addUser")
    public String showUserForm(Model model) {
        model.addAttribute("koala", new User());
        return "user";
    }


    @GetMapping(value = "/searchUsers")
    public String getUsersWithFilter(Model model, @RequestParam(required = false, defaultValue = "") String name) {
        if (name.equals("")){
            model.addAttribute("users",userServiceAnnotationSingleton.getAll());
        }
        else {
            model.addAttribute("users", userServiceAnnotationSingleton.getAll()
                    .stream()
                    .filter(user -> user.getName()
                            .equals(name))
                    .collect(Collectors.toList()));
        }
        return "usersWithFilter";
    }

    @PostMapping(value = "/saveUser")
    public String submit(@Valid @ModelAttribute ("koala") User user, BindingResult result, @ModelAttribute("index") String index) {     // User user to koala z formularza w user.jsp
       log.info("received index: " +index);
       if (result.hasErrors()) {
           return  "addUser";
       }
       log.info("Add index value: " +index);

        userServiceAnnotationSingleton.addUser(user.getName(),user.getAge());
        return  "redirect:/users";
    }

    @GetMapping(value ="/user/{id}/update")
    public String showUserFormForUpdate(@PathVariable("id") int index, Model model) {
        model.addAttribute("users",userServiceAnnotationSingleton.getAll());
        return "user";

    }


}