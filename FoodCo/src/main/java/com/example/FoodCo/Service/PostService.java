package com.example.FoodCo.Service;

import com.example.FoodCo.Dto.PostDTO;
import com.example.FoodCo.Entity.Member;
import com.example.FoodCo.Entity.Post;
import com.example.FoodCo.Exception.IdNotFoundException;
import com.example.FoodCo.Repository.MemberRepository;
import com.example.FoodCo.Repository.PostRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private MemberService memberService;
    private MemberRepository memberRepository;
    public PostService(PostRepository postRepository,MemberService memberService, MemberRepository memberRepository){
        this.postRepository=postRepository;
        this.memberService=memberService;
        this.memberRepository=memberRepository;
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Post getPost(int id) throws IdNotFoundException{
        return postRepository.findById(id).orElseThrow(()->new IdNotFoundException("Post is not found"));
    }

    public Post addPost(String title, String description, int memberId, MultipartFile image)throws IdNotFoundException{
        String imageName=image.getOriginalFilename();

        Member member=memberRepository.findById(memberId).orElseThrow(() -> new IdNotFoundException("Member not found"));
        Post post=Post.builder()
                .title(title)
                .description(description)
                .member(member)
                .image(imageName)
                .build();
        return postRepository.save(post);

    }

    public Post updatePost(int id, Post post)throws IdNotFoundException{
        Optional<Post> optionalPost=postRepository.findById(id);

        if (optionalPost.isPresent()){
            Post toPost=optionalPost.get();
            toPost.setTitle(post.getTitle());
            toPost.setDescription(post.getDescription());
            toPost.setImage(post.getImage());
            postRepository.save(toPost);
            return toPost;
        } else {
            throw new IdNotFoundException("Post is not found");
        }
    }
    public void deletePost(int id){
        postRepository.deleteById(id);
    }










}
