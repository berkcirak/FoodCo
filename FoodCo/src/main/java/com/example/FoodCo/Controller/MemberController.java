package com.example.FoodCo.Controller;

import com.example.FoodCo.Dto.MemberDTO;
import com.example.FoodCo.Entity.Member;
import com.example.FoodCo.Exception.IdNotFoundException;
import com.example.FoodCo.Repository.MemberRepository;
import com.example.FoodCo.Service.MemberService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;

    public MemberController(MemberService memberService,MemberRepository memberRepository){
        this.memberService=memberService;
        this.memberRepository=memberRepository;
    }

    @PostMapping ("/register")
    public Member saveMember(@RequestBody Member member){
        return memberService.addMember(member);
    }
    @PostMapping("/login")
    public String login(@RequestBody Member member){
        return memberService.verify(member);
    }
    @GetMapping("/list/{memberId}")
    public Member getMember(@PathVariable int memberId){
        return memberService.getMember(memberId);
    }
    @GetMapping("/list")
    public List<Member> getMembers(){
        return memberService.getMembers();
    }
    @PutMapping("/update/{memberId}")
    public Member updateMember(@PathVariable int memberId, @RequestBody MemberDTO memberDTO) throws IdNotFoundException {

        return memberService.updateMember(memberId,memberDTO);
    }


    @DeleteMapping("/delete/{memberId}")
    public void deleteMember(@PathVariable int memberId) throws IdNotFoundException {
        memberService.deleteMember(memberId);
    }


}
