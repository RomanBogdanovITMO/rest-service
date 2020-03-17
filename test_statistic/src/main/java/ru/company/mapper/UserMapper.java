package ru.company.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import ru.company.model.User;

import java.util.List;

@Mapper
public interface UserMapper {

    @Insert("insert into users(id,name,dateUser,idPage) values(#{id},#{name},#{dateUser},#{idPage})")
    void insert(User user);

    @Select("select * from users")
    List<User> findAll();
}
