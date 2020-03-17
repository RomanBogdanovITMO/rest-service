package ru.company.service;

import org.springframework.stereotype.Service;
import ru.company.config.ApplicationConfiguration;
import ru.company.mapper.UserMapper;
import ru.company.model.User;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RequestStatisticService {

    private UserMapper mapper;
    private ApplicationConfiguration configuration;

    public RequestStatisticService(UserMapper mapper, ApplicationConfiguration configuration) {
        this.mapper = mapper;
        this.configuration = configuration;
    }

    public List<User> getAllUsers() {
        return mapper.findAll();
    }

    // Общее количество посещений за текущие сутк.
    public String getCountUsersToday() {

        SimpleDateFormat sdf = new SimpleDateFormat(configuration.getFormatDate()); //dd.MM.yyyy
        String resultDate = sdf.format(Calendar.getInstance().getTime());
        int count = 0;
        for (User users : mapper.findAll()) {
            if (users.getDateUser().equals(resultDate)) {
                count++;
            }
        }

        return "values user today: " + count;
    }

    //Количество уникальных пользователей за текущие сутки.
    public String getCountUniqUsersToday() {

        List<User> list = mapper.findAll();

        return "values Unique users: " + getCountUniqUsers(list);
    }

    public int getDayOfDate(String date) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(configuration.getFormatDate()); //dd.MM.yyyy
        Date dateResult = sdf.parse(date);

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateResult);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        return day;
    }

    //Общее количество посещений за указанный период.
    public String getCountUsersForPeriod(String firstDate, String lastDate) throws ParseException {

        List<User> userList = getUserForCertainPeriod(mapper.findAll(), firstDate, lastDate);

        return "Number of users for a certain period: " + userList.size();
    }

    //Количество уникальных пользователей за указанный период.
    public String getCountUniqUsersForPeriod(String firstDate, String lastDate) throws ParseException {

        List<User> newUserList = getUserForCertainPeriod(mapper.findAll(), firstDate, lastDate);

        return "Number of unique users for a certain period: " + getCountUniqUsers(newUserList);
    }

    //Количество постоянных пользователей за указанный период
    // (пользователей, которые за период просмотрели не менее 10 различных страниц).
    public String getCountRegularUsersForPeriod(String firstDate, String lastDate) throws ParseException {
        List<User> userList = getUserForCertainPeriod(mapper.findAll(), firstDate, lastDate);
        int countRegularUsers = 0;
        for (User users : userList) {
            if (users.getIdPage().equals(configuration.getInfoPost())) {
                countRegularUsers++;
            }
        }
        return "Number of regular users for a certain period: " + countRegularUsers;
    }

    public int getCountUniqUsers(List<User> listUsers) {

        int count = 0;
        boolean flag = false;
        for (int i = 0; i < listUsers.size(); i++) {
            for (int j = 0; j < listUsers.size(); j++) {
                if (listUsers.get(i).getName().equals(listUsers.get(j).getName()) && i != j) {
                    flag = true;
                }
            }
            if (flag == false) {
                count++;
            }
            flag = false;
        }
        return count;
    }

    public List<User> getUserForCertainPeriod(List<User> listUsers, String firstDate, String lastDate) throws ParseException {
        List<User> newUsersList = new ArrayList<>();

        int startResult = getDayOfDate(firstDate);
        int finishResult = getDayOfDate(lastDate);

        for (User users : listUsers) {
            int dayUser = getDayOfDate(users.getDateUser());
            if (dayUser >= startResult && dayUser <= finishResult) {
                newUsersList.add(users);
            }

        }
        return newUsersList;
    }
}
