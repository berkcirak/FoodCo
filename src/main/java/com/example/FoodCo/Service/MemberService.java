package com.example.FoodCo.Service;

import com.example.FoodCo.Entity.Member;
import com.example.FoodCo.Entity.Role;
import com.example.FoodCo.Entity.User;
import com.example.FoodCo.Repository.MemberRepository;
import com.example.FoodCo.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final UserRepository userRepository;
    public MemberService(MemberRepository memberRepository,BCryptPasswordEncoder bCryptPasswordEncoder, UserRepository userRepository){
        this.memberRepository=memberRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.userRepository=userRepository;
    }

    public Member addMember(Member theMember){
        User user=User.builder()
                .username(theMember.getUser().getUsername())
                .password(bCryptPasswordEncoder.encode(theMember.getUser().getPassword()))
                .authorities(Collections.singleton(Role.ROLE_MEMBER))
                .isEnabled(true)
                .isCredentialsNonExpired(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .build();
        user=userRepository.save(user);

        Member member=Member.builder()
                .age(theMember.getAge())
                .email(theMember.getEmail())
                .firstName(theMember.getFirstName())
                .lastName(theMember.getLastName())
                .gender(theMember.getGender())
                .registerDate(LocalDateTime.now())
                .user(user)
                .build();
        return memberRepository.save(member);
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
            toMember.setUser(theMember.getUser());

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
