package com.codeup.springblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/albums")
public class AlbumsController {

    @GetMapping
    @ResponseBody
    public String allAlbums(){
        return "This is where you would view all the albums";
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String showOneAlbum(@PathVariable long id){
        return "This is where you would view one album with the id " + id;
    }

    @GetMapping("/create")
    @ResponseBody
    public String createAlbumsForm(){
        return "This is where you would view the form to create an album in the db";
    }
}
