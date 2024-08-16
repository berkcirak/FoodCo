package com.example.FoodCo.Service;

import com.example.FoodCo.Dto.PostDTO;
import com.example.FoodCo.Entity.Member;
import com.example.FoodCo.Entity.Post;
import com.example.FoodCo.Exception.IdNotFoundException;
import com.example.FoodCo.Repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private PostRepository postRepository;
    private MemberService memberService;
    public PostService(PostRepository postRepository,MemberService memberService){
        this.postRepository=postRepository;
        this.memberService=memberService;
    }

    public List<Post> getPosts(){
        return postRepository.findAll();
    }

    public Post getPost(int id) throws IdNotFoundException{
        return postRepository.findById(id).orElseThrow(()->new IdNotFoundException("Post is not found"));
    }

    public Post addPost(PostDTO postDTO)throws IdNotFoundException{
        Member member=memberService.getMember(postDTO.getMemberId());
        if (member!=null){
            Post toSave=new Post();
            toSave.setTitle(postDTO.getTitle());
            toSave.setDescription(postDTO.getDescription());
            toSave.setImage(postDTO.getImage());
            toSave.setMember(member);
            return postRepository.save(toSave);
        }
        else {
            throw new IdNotFoundException("Member is not found");
        }
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
