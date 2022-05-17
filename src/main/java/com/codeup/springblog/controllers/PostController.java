package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.repositories.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postDao;

    public PostController(PostRepository postDao) {
        this.postDao = postDao;
    }

    @GetMapping("/posts")
    public String index(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    public List<Post> generatePosts(){
        List<Post> allPosts = new ArrayList<>();
        Post post1 = new Post(1, "First post", "This is my first post!");
        Post post2 = new Post(2, "Another post!", "Amazing content!");
        Post post3 = new Post(3, "Third post", "Fascinating information!");
        allPosts.add(post1);
        allPosts.add(post2);
        allPosts.add(post3);
        return allPosts;
    }

    @GetMapping
    public String allPosts(Model model){
        List<Post> allPosts = generatePosts();
        model.addAttribute("allPosts", allPosts);
        return "posts/index";
    }

    @GetMapping("/{id}")
    public String onePost(@PathVariable long id, Model model){
        List<Post> allPosts = generatePosts();
        Post post = null;
        for (int i = 0; i < allPosts.size(); i++){
            if (allPosts.get(i).getId() == id){
                post = allPosts.get(i);
            }
        }
        model.addAttribute("post", post);
        return "posts/show";
    }

    @GetMapping("/create")
    @ResponseBody
    public String createPost(){
        return "posts/create";
    }


    @PostMapping("/create")
    @ResponseBody
    public String submitPost(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body){
        Post post = new Post(title, body);
        postDao.save(post);
        return "You just created a post!";
    }
}