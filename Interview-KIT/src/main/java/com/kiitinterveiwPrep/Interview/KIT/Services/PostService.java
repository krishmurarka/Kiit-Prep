package com.kiitinterveiwPrep.Interview.KIT.Services;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Post;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostDto;

import java.util.List;

public interface PostService {


    //create
    Post createPost(PostDto postDto);

    //update
    Post updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //getAll posts
    List<Post> getAllPost();

    //get single post

    Post getPostById(Integer postId);

    // get all post by category

    List<Post> getPostsByCategory(Integer categoryId);

    // get all post by user
    List<Post> getPostsByUser(Integer userId);

    //Search Posts
    List<Post> searchPosts(String keyword);




}
