package ru.company.restController;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.company.mapper.UserMapper;
import ru.company.model.User;
import ru.company.service.RequestStatisticService;

import java.text.ParseException;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private UserMapper mapper;
    private RequestStatisticService statisticService;

    public RestController(UserMapper mapper, RequestStatisticService statisticService) {
        this.mapper = mapper;
        this.statisticService = statisticService;
    }

    @GetMapping("user/save")
    public List<User> getAll() {

        return statisticService.getAllUsers();
    }

    // Общее количество посещений за текущие сутк.
    @GetMapping("users/today")
    public String getCountToday() {

        return statisticService.getCountUsersToday();
    }

    //Количество уникальных пользователей за текущие сутки.
    @GetMapping("users/uniq")
    public String getCountUniq() {

        return statisticService.getCountUniqUsersToday();
    }

    //Общее количество посещений за указанный период.
    @GetMapping("users/period")
    public String getCountUsersForPeriod(@RequestParam String firstDate, @RequestParam String lastDate) throws ParseException {

        return statisticService.getCountUsersForPeriod(firstDate, lastDate);
    }

    //Количество уникальных пользователей за указанный период.
    @GetMapping("users/period/uniq")
    public String getCountUniqUsersForPeriod(@RequestParam String firstDate, @RequestParam String lastDate) throws ParseException {

        return statisticService.getCountUniqUsersForPeriod(firstDate, lastDate);
    }

    //Количество постоянных пользователей за указанный период
    // (пользователей, которые за период просмотрели не менее 10 различных страниц).
    @GetMapping("users/period/regular")
    public String getCountRegularUsersForPeriod(@RequestParam String firstDate, @RequestParam String lastDate) throws ParseException {

        return statisticService.getCountRegularUsersForPeriod(firstDate, lastDate);
    }

}
