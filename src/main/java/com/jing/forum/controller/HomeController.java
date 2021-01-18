package com.jing.forum.controller;

import com.jing.forum.entity.DiscussPost;
import com.jing.forum.entity.Page;
import com.jing.forum.entity.User;
import com.jing.forum.service.DisCussPostService;
import com.jing.forum.service.LikeService;
import com.jing.forum.service.UserService;
import com.jing.forum.util.ForumConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class HomeController implements ForumConstant {

    @Autowired
    private DisCussPostService disCussPostService;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeService likeService;

    @RequestMapping(path = "/index", method = RequestMethod.GET)
    public String getIndexPage(Model model, Page page){
        // page由dispatcherServlet初始化，并自动装到model里
        // SpringMVC自动实例化model和page，并注入model
        page.setRows(disCussPostService.findDiscussPostRows(0));
        page.setPath("/index");

        List<DiscussPost> list = disCussPostService.findDiscussPosts(0, page.getOffset(), page.getLimit());
        List<Map<String, Object>> discussPosts = new ArrayList<>();
        for (DiscussPost discussPost : list) {
            Map<String, Object> map = new HashMap<>();
            map.put("post", discussPost);
            User user = userService.findUserById(discussPost.getUserId());
            map.put("user", user);
            long likeCount = likeService.findEntityLikeCount(ENTITY_TYPE_POST, discussPost.getId());
            map.put("likeCount", likeCount);
            discussPosts.add(map);
        }

        model.addAttribute("discussPosts", discussPosts);

        return "/index";
    }

    @RequestMapping(path = "/error", method = RequestMethod.GET)
    public String getErrorPage() {
        return "/error/500";
    }

}
