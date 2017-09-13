package tk.avabin.tdg.beans.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tk.avabin.tdg.beans.dtos.UserDto;
import tk.avabin.tdg.beans.entities.User;
import tk.avabin.tdg.beans.services.Entities.UserService;

/**
 * Created by Avabin on 18.05.2017.
 */
@RestController
@RequestMapping("/api/user")
public class UserRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper mapper;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public User addUser(@RequestBody UserDto object) {
        User user = mapper.map(object, User.class);
        return user;
    }

}
