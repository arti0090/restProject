package pl.sda.javaldz6;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.sda.javaldz6.model.User;
import pl.sda.javaldz6.service.UserService;

@RestController
@RequestMapping(value = "/rest")
public class UserRestController {


    @Autowired
    @Qualifier("userServiceAnnotationSingleton")
    private UserService userServiceAnnotationSingleton;

    @GetMapping(value = "/user/{id}")
    public ResponseEntity<User> getUser(@PathVariable String id) {
        User user = userServiceAnnotationSingleton.getUserById(Integer.valueOf(id));
        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user    ) {  //mapuje wartosc post na usera
            userServiceAnnotationSingleton.addUser(user.getName(),user.getAge());
        return ResponseEntity.ok(user);
    }




}
