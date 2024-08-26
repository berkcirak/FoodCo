package com.example.FoodCo.Controller;

import com.example.FoodCo.Entity.Post;
import com.example.FoodCo.Exception.IdNotFoundException;
import com.example.FoodCo.Repository.MemberRepository;
import com.example.FoodCo.Service.PostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {


    private PostService postService;
    private MemberRepository memberRepository;
    public PostController(PostService postService,MemberRepository memberRepository){
        this.postService=postService;
        this.memberRepository=memberRepository;
    }


    @PostMapping("/add")
    public ResponseEntity<Post> addPost(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("memberId") int memberId,
            @RequestParam("image")MultipartFile image) throws IdNotFoundException {
        Post post=postService.addPost(title, description, memberId, image);
        return ResponseEntity.ok(post);
    }


    @GetMapping("/list")
    public List<Post> getPosts(){
        return postService.getPosts();
    }
    @GetMapping("/list/{postId}")
    public Post getPost(@PathVariable int postId)throws IdNotFoundException{
        return postService.getPost(postId);
    }

    @PutMapping("/update/{postId}")
    public Post updatePost(@PathVariable int postId, @RequestBody Post post)throws IdNotFoundException {
        return postService.updatePost(postId,post);
    }

    @DeleteMapping("/delete/{postId}")
    public void deletePost(@PathVariable int postId){
        postService.deletePost(postId);
    }


}
