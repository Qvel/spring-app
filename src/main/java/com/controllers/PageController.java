package com.controllers;

import com.dao.UsersDAO;
import com.entity.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class PageController {

    private UserService userService;
    private UsersDAO usersDAO;

    @Autowired
    public PageController(UserService userService,UsersDAO usersDAO) {
        this.userService = userService;
        this.usersDAO = usersDAO;
    }

    @GetMapping
    @RequestMapping(value="/")
    public String mainPage(){
        return "index";
    }

    @GetMapping
    @RequestMapping(value="/json",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getJson(@RequestHeader(value="User-Agent") String userAgent){
        return new ResponseEntity<>(userAgent,HttpStatus.ACCEPTED);
    }

    @GetMapping
    @RequestMapping(value="/getUsers",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsers(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping
    @RequestMapping(value="/writeJsonToFile",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> writeUsers(){
        String directory = "C:" + File.separator + "Users"
                + File.separator + "szhura" + File.separator + "work" + File.separator
                + "home projects" + File.separator + "someidea" + File.separator
                + "src" + File.separator + "main" + File.separator + "webapp"
                + File.separator + "WEB-INF" + File.separator + "users.json";

        ObjectMapper objectMapper = new ObjectMapper();
        List<User> users = userService.getAllUsers();
        try {
            System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(users));
            objectMapper.writeValue(new File(directory), users);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok("writed");
    }

    @GetMapping
    @RequestMapping(value="/getUsersWithCookie",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getUsersWithCookie(HttpServletResponse response){
        Cookie userCookie = new Cookie("testCookie","list of users");
        response.addCookie(userCookie);
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @GetMapping
    @RequestMapping(value="/jpa",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsersFromBd(){
         List<User> users = new ArrayList<>();
         usersDAO.findAll().forEach(
                 users::add
         );
        return ResponseEntity.ok(users);
    }

    @GetMapping
    @RequestMapping(value="/jpaLike",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<User>> getAllUsersFromBdByName(){
        return ResponseEntity.ok(usersDAO.findByNameIgnoreCaseContaining("Max"));
    }


}
