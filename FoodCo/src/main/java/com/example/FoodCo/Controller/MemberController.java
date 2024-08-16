package com.example.FoodCo.Controller;

import com.example.FoodCo.Dto.MemberDTO;
import com.example.FoodCo.Entity.Member;
import com.example.FoodCo.Entity.User;
import com.example.FoodCo.Exception.IdNotFoundException;
import com.example.FoodCo.Repository.MemberRepository;
import com.example.FoodCo.Service.MemberService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    private final MemberService memberService;
    private final MemberRepository memberRepository;
    public MemberController(MemberService memberService,MemberRepository memberRepository){
        this.memberService=memberService;
        this.memberRepository=memberRepository;
    }

    @PostMapping ("/add")
    public Member saveMember(@RequestBody Member member){
        return memberService.addMember(member);
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
       /* Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();

        Optional<Member> optionalMember = memberRepository.findMemberByUserId(user.getId());
        if (optionalMember.isPresent()) {
            Member existingMember = optionalMember.get();
            if (existingMember.getId() == memberId) {
                return memberService.updateMember(memberId, memberDTO);
            } else {
                throw new IdNotFoundException("Member found but not updated");
            }
        } else {
            throw new IdNotFoundException("Member not found");
        } */
    }


    @DeleteMapping("/delete/{memberId}")
    public void deleteMember(@PathVariable int memberId) throws IdNotFoundException {

        memberService.deleteMember(memberId);

    /*    Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
        User user=(User) authentication.getPrincipal();
        Optional<Member> theMember=memberRepository.findMemberByUserId(user.getId());

        if (theMember.isPresent()){
            Member existingMember=theMember.get();
            if (existingMember.getId()==memberId){
                memberRepository.deleteById(memberId);
            }
            else{
                throw new RuntimeException("Member is found but not deleted");
            }
        }
        else {
            throw new IdNotFoundException("Member is not found");
        } */


    }


}
