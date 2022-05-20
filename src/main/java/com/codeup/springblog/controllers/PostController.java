package com.codeup.springblog.controllers;

import com.codeup.springblog.models.Post;
import com.codeup.springblog.models.PostImage;
import com.codeup.springblog.models.User;
import com.codeup.springblog.repositories.PostRepository;
import com.codeup.springblog.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/posts")
public class PostController {

    private final PostRepository postDao;
    private final UserRepository userDao;

    public PostController(PostRepository postDao, UserRepository userDao) {
        this.postDao = postDao;
        this.userDao = userDao;
    }

    @GetMapping("/posts")
    public String index(Model model){
        model.addAttribute("posts", postDao.findAll());
        return "posts/index";
    }

    @GetMapping("/posts/{id}")
    @ResponseBody
    public String show(@PathVariable long id) { return "Here is the post " + id;}

//    @GetMapping("/posts/create")
//    public String create(){ return "posts/create";}
//
//    public String insert(@RequestParam String title, @RequestParam String body,
//                         @RequestParam List<String> urls) {
//        List<PostImage> images = new ArrayList<>();
//        User author = userDao.getById(1L);
//        Post post = new Post(title, body);
//
//        for (String url : urls) {
//            PostImage postImage = new PostImage(url);
//            postImage.setPost(post);
//            images.add(postImage);
//        }
//
//        post.setImages(images);
//
//        post.setUser(author);
//        postDao.save(post);
//
//    }

//    public List<Post> generatePosts(){
//        List<Post> allPosts = new ArrayList<>();
//        Post post1 = new Post(1, "First post", "This is my first post!");
//        Post post2 = new Post(2, "Another post!", "Amazing content!");
//        Post post3 = new Post(3, "Third post", "Fascinating information!");
//        allPosts.add(post1);
//        allPosts.add(post2);
//        allPosts.add(post3);
//        return allPosts;
//    }

//    @GetMapping
//    public String allPosts(Model model){
////        List<Post> allPosts = generatePosts();
//        model.addAttribute("allPosts", allPosts);
//        return "posts/index";
//    }
//
//    @GetMapping("/{id}")
//    public String onePost(@PathVariable long id, Model model){
////        List<Post> allPosts = generatePosts();
//        Post post = null;
//        for (int i = 0; i < allPosts.size(); i++){
//            if (allPosts.get(i).getId() == id){
//                post = allPosts.get(i);
//            }
//        }
//        model.addAttribute("post", post);
//        return "posts/show";
//    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("post", new Post());
        return "posts/create";
    }

    @GetMapping("/api")
    @ResponseBody
    public List<Post> returnPosts(){
        return postDao.findAll();
    }


    @PostMapping("/create")
    public String submitPost(
            @RequestParam(name = "title") String title,
            @RequestParam(name = "body") String body){
        User user = userDao.findAll().get(0);
        Post post = new Post(title, body, user);
        postDao.save(post);
        return "redirect:/posts";
    }

//    @PostMapping
//    public String postDetails(Model model){
//        List<Post> postDetails = generatePosts();
//        model.addAttribute("postDetails", postDetails);
//        return "posts/details";
//    }

    @GetMapping("/posts/add")
    public String addPost(){
        return "posts/add";
    }

    @PostMapping("/add")
    public String addImage(@RequestParam(name = "image_title") String image_title, @RequestParam(name = "url")
            String url, @RequestParam(name = "post_id") Long post_id){
        Post post = postDao.getById(post_id);
        PostImage postImage = new PostImage(image_title, url, post);

//        post.getPostImageList().add(postImage);
        postDao.save(post);
//        postImageDao.save(postImage);
        return "redirect:/posts/details";
    }


    @PostMapping("/posts/create")
    public String create(@ModelAttribute Post post) {
        return "posts/create";
    }
}