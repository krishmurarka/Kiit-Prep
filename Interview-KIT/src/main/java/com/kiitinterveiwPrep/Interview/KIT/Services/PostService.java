package com.kiitinterveiwPrep.Interview.KIT.Services;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Post;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostDto;

import java.util.List;

public interface PostService {


    //create
    PostDto createPost(PostDto postDto,Integer userId,Integer categoryId,Integer companyId);

    //update
    PostDto updatePost(PostDto postDto,Integer postId);

    //delete
    void deletePost(Integer postId);

    //getAll posts
    List<PostDto> getAllPost();

    //get single post

    PostDto getPostById(Integer postId);

    // get all post by category

    List<PostDto> getPostsByCategory(Integer categoryId);

    // get all post by user
    List<PostDto> getPostsByUser(Integer userId);

    // get all post by Company

    List<PostDto> getPostByCompany(Integer companyId);

    //Search Posts
    List<PostDto> searchPosts(String keyword);




}
