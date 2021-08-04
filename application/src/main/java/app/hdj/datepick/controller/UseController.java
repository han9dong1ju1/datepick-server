package app.hdj.datepick.controller;

import app.hdj.datepick.data.Service.UserService;
import app.hdj.datepick.data.repository.UserRepositoryImp;
import app.hdj.datepick.domain.dto.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
class UseController {

    @Autowired
    UserService userService;

    @GetMapping("user")
    public String findUserById(@RequestParam("id") long id){
        //test
        System.out.println("in");
        System.out.println(userService.findById(id).toString());
        System.out.println("out");
        return userService.findById(id).getNickname();
    }
}

