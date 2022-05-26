package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;

import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import com.codeup.springblog.services.EmailService;
import com.codeup.springblog.services.StringService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postsDao;
    private final UserRepository usersDao;

    private StringService stringService;

    private EmailService emailService;

    public PostController(PostRepository postsDao, StringService stringService, UserRepository usersDao,
                          EmailService emailService
    ){
        this.stringService = stringService;
        this.postsDao = postsDao;
        this.usersDao = usersDao;
        this.emailService = emailService;
    }

    public List<Post> generatePosts(){
        List<Post> allPosts = new ArrayList<>();
        Post post1 = new Post(1, "First post", "This is my first post!", usersDao.getById(1L));
        Post post2 = new Post(2, "Another post!", "Amazing content!", usersDao.getById(1L));
        Post post3 = new Post(3, "Third post", "Fascinating information!", usersDao.getById(1L));
        allPosts.add(post1);
        allPosts.add(post2);
        allPosts.add(post3);
        return allPosts;
    }

    @GetMapping
    public String allPosts(Model model){
        List<Post> allPosts = postsDao.findAll();

        model.addAttribute("stringService", stringService);
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model){
        Post post = postsDao.findById(id);
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/create")
    public String createPost(Model model){
        model.addAttribute("post", new Post());
        return "/posts/create";
    }

    @PostMapping("/create")
    public String submitPost(
            @ModelAttribute Post post
    ) {
        User user = usersDao.getById(1L);

//        User user = (User) SecurityContextHolder.getContext().getAuthentication().
//                getPrincipal();
//        long userId = user.getId();
        post.setUser(user);

//        emailService.prepareAndSend(post, post.getTitle(), post.getBody());

        postsDao.save(post);
        return "redirect:/posts";
    }


    @GetMapping("/{id}/edit")
    public String editPost(
            @PathVariable(name="id") Long id,
            Model model
    ) {

        System.out.println("id = " + id);

        model.addAttribute("post", postsDao.findById(id));
        return "posts/create";
    }


    @PostMapping("/edit")
    public String doEdit(
            @ModelAttribute Post post
    ) {

        postsDao.save(post);

        return "redirect:/posts/" + post.getId();
    }


    @PostMapping("/delete")
    public String deletePost(
            @ModelAttribute Post post
    ) {
        postsDao.save(post);
        return "redirect:/posts";
    }

}
