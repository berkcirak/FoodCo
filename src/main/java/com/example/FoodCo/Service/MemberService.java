package com.example.FoodCo.Service;

import com.example.FoodCo.Entity.Member;
import com.example.FoodCo.Entity.User;
import com.example.FoodCo.Exception.IdNotFoundException;
import com.example.FoodCo.Repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private MemberRepository memberRepository;
    public MemberService(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }

    public Member addMember(Member theMember){
        return memberRepository.save(theMember);
    }
    public List<Member> getMembers(){
        return memberRepository.findAll();
    }
    public Member getMember(int id){
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public Member updateMember(int id, Member theMember){
        Optional<Member> optionalMember=memberRepository.findById(id);
        if (optionalMember.isPresent()){
            Member toMember=optionalMember.get();
            toMember.setAge(theMember.getAge());
            toMember.setGender(theMember.getGender());
            toMember.setEmail(theMember.getEmail());
            toMember.setFirstName(theMember.getFirstName());
            toMember.setLastName(theMember.getLastName());

            User toUser=toMember.getUser();
            User fromUser=theMember.getUser();
            if(toUser != null && fromUser != null){
                toUser.setEnabled(fromUser.isEnabled());
                toUser.setUsername(fromUser.getUsername());
                toUser.setPassword(fromUser.getPassword());
                toUser.setAccountNonLocked(fromUser.isAccountNonLocked());
                toUser.setAccountNonExpired(fromUser.isAccountNonExpired());
                toUser.setCredentialsNonExpired(fromUser.isCredentialsNonExpired());
                toUser.setAuthorities(fromUser.getAuthorities());
                toMember.setUser(toUser);
            }

            memberRepository.save(toMember);
            return toMember;
        }else {
            throw new EntityNotFoundException("Member not found with id: "+id);
        }
    }
    public void deleteMember(int id){
        memberRepository.deleteById(id);
    }

}
