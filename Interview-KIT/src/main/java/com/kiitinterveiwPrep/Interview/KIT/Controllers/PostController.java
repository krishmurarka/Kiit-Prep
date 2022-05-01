package com.kiitinterveiwPrep.Interview.KIT.Controllers;

import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostDto;
import com.kiitinterveiwPrep.Interview.KIT.Services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
    // get by User
    @GetMapping("/user/{userId}/posts")
    public  ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){

        List<PostDto> postDtoList = this.postService.getPostsByUser(userId);
        return  ResponseEntity.ok(postDtoList);
    }
    //get by Category
    @GetMapping("/category/{categoryId}/posts")
    public  ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtoList = this.postService.getPostsByCategory(categoryId);
        return  ResponseEntity.ok(postDtoList);
    }
    //get by Company
    @GetMapping("/company/{companyId}/posts")
    public  ResponseEntity<List<PostDto>> getPostsByCompany(@PathVariable Integer companyId){
        List<PostDto> postDtoList = this.postService.getPostByCompany(companyId);
        return  ResponseEntity.ok(postDtoList);
    }
    // Get All Posts
    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> getAllPosts(){

        List<PostDto> postDtoList = this.postService.getAllPost();
        return ResponseEntity.ok(postDtoList);
    }

    // Get Post Details By id
    @GetMapping("/posts/{postId}")
    public  ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = this.postService.getPostById(postId);
        return  ResponseEntity.ok(postDto);
    }
}
