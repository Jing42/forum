package com.jing.forum.controller;

import com.jing.forum.service.AlphaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@Controller
@RequestMapping("/alpha")
public class AlphaController {

    private AlphaService alphaService;

    @Autowired
    public void setAlphaService(AlphaService alphaService) {
        this.alphaService = alphaService;
    }

    @RequestMapping("/data")
    @ResponseBody
    public String getData() {
        return alphaService.find();
    }


    @RequestMapping("/http")
    public void http(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getMethod());
        System.out.println(request.getServletPath());
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            String value = request.getHeader(name);
            System.out.println(name + ": " + value);
        }
        System.out.println(request.getParameter("code"));

        response.setContentType("text/html;charset=utf-8");
        try(
            PrintWriter writer = response.getWriter();
            ) {
            writer.write("<h1>This</h1>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping(path="/students", method= RequestMethod.GET)
    @ResponseBody
    public String getStudents(
            @RequestParam(name = "current", required = false, defaultValue = "1") int current,
            @RequestParam(name = "limit", required = false, defaultValue = "1") int limit) {
        return "some students" + current + " " + limit;
    }

    @RequestMapping(path="/student/{id}", method= RequestMethod.GET)
    @ResponseBody
    public String getStudent(@PathVariable("id") int id) {

        return "a Student" + id;
    }

    @RequestMapping(path="/student", method = RequestMethod.POST)
    @ResponseBody
    public String saveStudent(String name, int age) {
        System.out.println(name + " " + age);
        return "success";
    }


    @RequestMapping(path="/teacher", method = RequestMethod.GET)
    public ModelAndView getTeacher() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("name", "cc98");
        mav.addObject("age", 30);
        mav.setViewName("/demo/view");
        return mav;
    }

    @RequestMapping(path="/school", method = RequestMethod.GET)
    public String getSchool(Model model) {
        model.addAttribute("name", "cc99");
        model.addAttribute("age", "78");
        return "/demo/view";
    }

    @RequestMapping(path="/emp", method=RequestMethod.GET)
    @ResponseBody
    public List<Map<String, Object>> getEmp()   {
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> emp = new HashMap<>();
        emp.put("name", "zhangsan");
        emp.put("age", new HashSet<>(Arrays.asList(1,2,3)));
        emp.put("salary", 8000);
        list.add(emp);
        Map<String, Object> emp0 = new HashMap<>();
        emp0.put("name", "wangwu");
        emp0.put("age", new HashSet<>(Arrays.asList(1,2,3)));
        emp0.put("salary", 8000);
        list.add(emp0);
        Map<String, Object> emp1 = new HashMap<>();
        emp1.put("name", "lisi");
        emp1.put("age", Arrays.asList(1,2,3));
        emp1.put("salary", 8000);
        list.add(emp1);
        return list;
    }

}
