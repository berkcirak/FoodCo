package com.example.FoodCo.Service;

import com.example.FoodCo.Dto.MemberDTO;
import com.example.FoodCo.Entity.Member;
import com.example.FoodCo.Entity.Role;
import com.example.FoodCo.Entity.User;
import com.example.FoodCo.Exception.IdNotFoundException;
import com.example.FoodCo.Repository.MemberRepository;
import com.example.FoodCo.Repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

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
    public Member getMemberById(int memberId)throws IdNotFoundException{
        return memberRepository.findById(memberId).orElseThrow( () -> new IdNotFoundException("Id not found member: "+memberId));
    }
    public Member getMember(int id){
        return memberRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }
    public Member updateMember(int id, MemberDTO memberDTO) throws IdNotFoundException {

        Member existingMember=memberRepository.findById(id).orElseThrow(()->new IdNotFoundException("member is not found"));
        existingMember.setGender(memberDTO.getGender());
        existingMember.setAge(memberDTO.getAge());
        existingMember.setEmail(memberDTO.getEmail());
        existingMember.setLastName(memberDTO.getLastName());
        existingMember.setFirstName(memberDTO.getFirstName());

        User user=existingMember.getUser();
        user.setUsername(memberDTO.getUser().getUsername());
        user.setPassword(bCryptPasswordEncoder.encode(memberDTO.getUser().getPassword()));
        existingMember.setUser(user);
        return memberRepository.save(existingMember);


        /*Optional<Member> optionalMember = memberRepository.findById(id);

        if (optionalMember.isPresent()) {
            Member toMember = optionalMember.get();
            User toUser = optionalMember.get().getUser();
            toMember.setAge(memberDTO.getAge());
            toMember.setGender(memberDTO.getGender());
            toMember.setEmail(memberDTO.getEmail());
            toMember.setFirstName(memberDTO.getFirstName());
            toMember.setLastName(memberDTO.getLastName());


            toUser.setUsername(optionalMember.get().getUser().getUsername());
            toUser.setPassword(bCryptPasswordEncoder.encode(optionalMember.get().getUser().getPassword()));
            toUser.setAuthorities(optionalMember.get().getUser().getAuthorities());
            toMember.setUser(toUser);
            memberRepository.save(toMember);
            return toMember;
        } else {
            throw new EntityNotFoundException("Member not found with id: " + id);
        } */
    }
    public void deleteMember(int id) throws IdNotFoundException {
        Member member=memberRepository.findById(id).orElseThrow(()->new IdNotFoundException("Id not found member: "+id));
        memberRepository.deleteById(id);
    }

}
