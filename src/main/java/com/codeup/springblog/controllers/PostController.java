package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/posts")
public class PostController {

    @GetMapping
    @ResponseBody
    public String allPosts(){
        return "Here are all the posts!";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String onePost(@PathVariable long id){
        return "Here is post number " + id;
    }

    @GetMapping("/create")
    @ResponseBody
    public String createPost(){
        return "Here is the form to create a post!";
    }

    @PostMapping("/create")
    @ResponseBody
    public String submitPost(){
        return "You just created a post!";
    }
}
