package com.example.demo.controller;

import com.example.demo.entity.Proposal;
import com.example.demo.entity.Student;
import com.example.demo.service.StudentService;
import com.sun.org.apache.bcel.internal.generic.MONITORENTER;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
* @description 
* @author Tim Lin
* @create 2018-07-01 
**/
@Controller

public class StudentController{

    @Autowired
    private StudentService studentService;

    /*
     *注册与登录，需求1
     */

    //注册
    @RequestMapping(value = "/in/reg",method = RequestMethod.GET)
    public String indexStu(Model model){
        model.addAttribute("student",new Student());
        return "Register";
    }

    @RequestMapping(value = "/out/reg", method= RequestMethod.GET)
    public String Register(@ModelAttribute("student") Student student,Model model) {
        studentService.saveStudent(student);
        return "redirect:/in/login";
    }
    //登录
    @RequestMapping(value = "/in/login",method = RequestMethod.GET)
    public String Login(Model model){
        model.addAttribute("student",new Student());
        return "Login";
    }
    @RequestMapping(value = "/out/login",method = RequestMethod.GET)
    public String index(@ModelAttribute("student") Student student){
        if (studentService.compareStudentpassword(student) == 1)
            return "redirect:/in/index";
        else
            return "redirect:/in/error";
    }
    @RequestMapping(value = "/in/error",method = RequestMethod.GET)
    public String Login_error(HttpServletRequest request){
        return "Login_error";
    }


    /*
    *文件读写
     */
    @RequestMapping(value = "/import",method = RequestMethod.GET)
    public String API(HttpServletRequest request){
        request.setAttribute("path","1.txt");
        return "file";
    }

    /*
    *主界面
     */
    @RequestMapping(value = "/in/index",method = RequestMethod.GET)
    public String start(HttpServletRequest request){
        return "starter";
    }

    /*
    *文档管理（需求2）
     */
    //提案查询
    @RequestMapping(value = "/in/pps",method = RequestMethod.GET)
    public String PPS(Model model){
        List<Proposal> list= studentService.getAllProposal();
        model.addAttribute("proposals",list);
        model.addAttribute("proposal",new Proposal());
        return "Pps";
    }

    //提案管理
    @RequestMapping(value = "/out/ppsd",method = RequestMethod.GET)
    public String PPSD(@ModelAttribute("Proposal") Proposal proposal,Model model,HttpServletRequest request){
        //由文档ID读取作者作者名称，由作者名称读取作者信息
        Student student=studentService.searchstudent(studentService.searchstudent_Name(proposal.getId()));
        Proposal proposal1=studentService.searchproposal_Prop(proposal.getId());
        request.setAttribute("proposals",proposal1);
        request.setAttribute("Student",student);
        //测试查看student和proposal内容
        System.out.print(student);
        System.out.print(proposal1);
        return "Ppsd";
    }

    //提案编写
    @RequestMapping(value = "/in/ppw",method = RequestMethod.GET)
    public String PPW(Model model,HttpServletRequest request){
        model.addAttribute("proposal",new Proposal());
        return "Ppw";
    }
    @RequestMapping(value = "out/ppw",method = RequestMethod.GET)
    public String PPW_PPS(@ModelAttribute("proposal")Proposal proposal,HttpServletRequest request){
        studentService.saveProposal(proposal);
        //跳转到提案查询 问题：textarea提交的内容会附带<p>***</p>
        return "redirect:/in/pps";
    }

    //规范查询
    @RequestMapping(value = "/in/nms",method = RequestMethod.GET)
    public String NMS(Model model,HttpServletRequest request){
        List<Proposal> list= studentService.getAllProposal();
        model.addAttribute("proposals",list);
        model.addAttribute("proposal",new Proposal());
        return "Nms";
    }

    //规范编写
    @RequestMapping(value = "/in/nmw",method = RequestMethod.GET)
    public String NMW(Model model,HttpServletRequest request){
        model.addAttribute("proposal",new Proposal());
        return "Nwm";
    }
    @RequestMapping(value = "out/nmw",method = RequestMethod.GET)
    public String NMW_NMS(@ModelAttribute("proposal")Proposal proposal,HttpServletRequest request){
        studentService.saveProposal(proposal);
        //跳转到提案查询 问题：textarea提交的内容会附带<p>***</p>
        return "redirect:/in/nms";
    }

    /*
    *身份管理（需求3）
     */
    @RequestMapping(value = "/in/idm",method = RequestMethod.GET)
    public String IDM(HttpServletRequest request){
        return "Idm";
    }

    /*
    *测试
     */
    @RequestMapping(value = "/stu/test1",method = RequestMethod.GET)
    public String test(HttpServletRequest request){
        return "test";
    }
}
