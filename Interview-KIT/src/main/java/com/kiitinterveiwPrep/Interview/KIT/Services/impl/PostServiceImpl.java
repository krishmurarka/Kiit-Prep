package com.kiitinterveiwPrep.Interview.KIT.Services.impl;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Category;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Company;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Post;
import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import com.kiitinterveiwPrep.Interview.KIT.Exceptions.ResourceNotFoundException;
import com.kiitinterveiwPrep.Interview.KIT.Payloads.PostDto;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.CategoryRepo;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.CompanyRepo;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.PostRepo;
import com.kiitinterveiwPrep.Interview.KIT.Repositories.UserRepo;
import com.kiitinterveiwPrep.Interview.KIT.Services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepo postRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private CompanyRepo companyRepo;

    @Override
    public PostDto createPost(PostDto postDto,Integer userId,Integer categoryId,Integer companyId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User "," id",userId));
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category "," id",categoryId));
        Company company = this.companyRepo.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company "," id",companyId));
        Post post = this.dtoToPost(postDto);
        post.setAddDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        post.setCompany(company);
        Post createdPost = this.postRepo.save(post);
        return this.PostToDto(createdPost);
    }

    @Override
    public PostDto updatePost(PostDto postDto, Integer postId) {

        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post "," id",postId));
        post.setTitle(postDto.getTitle());
        post.setContent(postDto.getContent());
        post.setAddDate(new Date());
        Post updatedPost = this.postRepo.save(post);
        return PostToDto(updatedPost);
    }

    @Override
    public void deletePost(Integer postId) {
           Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," id",postId));
           this.postRepo.delete(post);
    }

    @Override
    public List<PostDto> getAllPost() {
        List<Post> allpost = this.postRepo.findAll();

        List<PostDto> postDtoList = allpost.stream().map(post -> PostToDto(post)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public PostDto getPostById(Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post"," id",postId));

        return this.PostToDto(post);
    }

    @Override
    public List<PostDto> getPostsByCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category"," id",categoryId));
        List<Post> postList = this.postRepo.findByCategory(category);

        List<PostDto> postDtoList = postList.stream().map(post -> this.PostToDto(post)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User"," id",userId));
        List<Post> postList = this.postRepo.findByUser(user);
        List<PostDto> postDtoList = postList.stream().map(post -> this.PostToDto(post)).collect(Collectors.toList());

        return postDtoList;
    }

    @Override
    public List<PostDto> getPostByCompany(Integer companyId) {

        Company company = this.companyRepo.findById(companyId).orElseThrow(()-> new ResourceNotFoundException("Company"," id",companyId));
        List<Post> postList = this.postRepo.findByCompany(company);
        List<PostDto> postDtoList = postList.stream().map(post -> this.PostToDto(post)).collect(Collectors.toList());
        return postDtoList;
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return null;
    }

    private Post dtoToPost(PostDto postDto){
        Post post = this.modelMapper.map(postDto,Post.class);
        return post;
    }
    private PostDto PostToDto(Post post){
        PostDto postDto = this.modelMapper.map(post,PostDto.class);
        return postDto;
    }
}
