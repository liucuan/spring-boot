package com.github.tone.dao;

import com.github.tone.domain.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * Created by jenny on 2017/5/23.
 */
@Mapper
public interface UserMapper {


    //    @Select({"<script>\n"
//            + "SELECT * FROM USER WHERE \n"
//            + "<if test='name!=null || name!=\"\"'>\n"
//            + "NAME = #{name}\n"
//            + "</if>\n"
//            + "</script>"})
    @Select({"<script>\n"
            + "SELECT * FROM USER WHERE \n"
            + "<if test=\"@com.github.tone.dao.MyOgnl@isNotEmpty(name)\">\n"
            + "NAME = #{name}\n"
            + "</if>\n"
            + "</script>"})
    User findByName(@Param("name") String name);

    @Insert("INSERT INTO USER(NAME, AGE) VALUES(#{name}, #{age})")
    int insert(@Param("name") String name, @Param("age") Integer age);

    @Insert("UPDATE  USER SET AGE = #{age} WHERE NAME = #{name}")
    int update(@Param("name") String name, @Param("age") Integer age);
}
