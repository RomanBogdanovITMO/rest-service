package ru.company.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.company.mapper.UserMapper;
import ru.company.model.User;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Calendar;


@org.springframework.stereotype.Controller
public class Controller {


    private UserMapper mapper;

    public Controller(UserMapper mapper) {

        this.mapper = mapper;
    }

    @GetMapping("/create")
    private String createUser(Model model) {

        model.addAttribute("user", new User());

        return "index";
    }

    //@PostMapping(path = "/{user/save}")
    @PostMapping("/create/save")
    private String saveUser(@ModelAttribute User user, HttpServletRequest request) {

        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy"); //dd.MM.yyyy
        String lastDate = sdf.format(Calendar.getInstance().getTime());
        user.setDateUser(lastDate);
        user.setIdPage(request.getRequestURI());
        mapper.insert(user);

        return "page";
    }
}
