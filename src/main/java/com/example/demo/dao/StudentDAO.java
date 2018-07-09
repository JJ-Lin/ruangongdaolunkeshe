package com.example.demo.dao;

import com.example.demo.entity.Proposal;
import com.example.demo.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.UUID;

/**
* @description 
* @author Tim Lin
* @create 2018-07-01 
**/
@Mapper
public interface StudentDAO {
    @Select(value="SELECT * FROM student")
    List<Student> getAllStudent();

    @Select(value="SELECT * FROM proposal")
    List<Proposal> getAllProposal();

    @Insert(value="INSERT INTO student(student.`password`,student.`username`,student.`sex`,student.`name`,student.`birthday`,student.`address`,student.`telephone`,student.`people`,student.`branch`,student.`committee`,student.`flat`)\n" + "VALUE(#{password},#{username},#{sex},#{name},#{birthday},#{address},#{telephone},#{people},#{branch},#{committee},#{flat})")
    int insert(Student student);

    //测试：插入文档
    @Insert(value="INSERT INTO proposal(proposal.`PName`,proposal.`AName`,proposal.`TNum`,proposal.`FNum`,proposal.`Time`)\n" + "VALUE(#{PName},#{AName},#{TNum},#{FNum},#{Time})")
    int insert_proposal(Proposal proposal);

    @Select(value="SELECT * FROM student WHERE student.username=#{username}")
    Student search(String username);

    //测试：由ID搜索文档
    @Select(value="SELECT * FROM proposal WHERE proposal.Id=#{Id}")
    Proposal search_Proposal(String Id);

    //测试：由文档ID搜索作者(新建proposal表）
    @Select(value="SELECT AName FROM proposal WHERE proposal.Id=#{Id}")
    String search_Name(String Id);

    @Select(value="SELECT student.flat FROM student WHERE student.username=#{username}")
    Integer searchflat(String username);

    @Delete(value="DELETE FROM student WHERE student.username=#{username}")
    void delete(String username);

    @Select(value="SELECT student.papername FROM student WHERE student.username=#{username}")
    String getpapername(String papername);

    @Select(value="SELECT student.uploadtime FROM student WHERE student.username=#{username}")
    String getuploadtime(String uploadtime);

    @Select(value="SELECT student.paperurl FROM student WHERE student.username=#{username}")
    String getpaperurl(String paperurl);

    @Insert(value="INSERT INTO student(student.`password`,student.`username`,student.`sex`,student.`name`,student.`birthday`,student.`address`,student.`telephone`,student.`people`,student.`branch`,student.`committee`,student.`flat`,student.`papername`,student.`uploadtime`,student.`paperurl`)\n" + "VALUE(#{password},#{username},#{sex},#{name},#{birthday},#{address},#{telephone},#{people},#{branch},#{committee},#{flat},#{papername},#{uploadtime},#{paperurl})")
    void updatepaper(Student student);
}
