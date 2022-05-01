package com.kiitinterveiwPrep.Interview.KIT.Controllers;

import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostDto;
import com.kiitinterveiwPrep.Interview.KIT.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api")
public class PostController {

    @Autowired
     private PostService postService;
    //create
    @PostMapping("/user/{userId}/category/{categoryId}/company/{companyId}/posts")
    public ResponseEntity<PostDto> createPost(@Valid  @RequestBody PostDto postDto, @PathVariable Integer userId,
                                              @PathVariable Integer categoryId, @PathVariable Integer companyId){



           PostDto createPostDto=  this.postService.createPost(postDto,userId,categoryId,companyId);

           return new ResponseEntity<>(createPostDto, HttpStatus.CREATED);


    }
}
