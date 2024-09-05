package com.example.FoodCo.Service;

import com.example.FoodCo.Dto.MemberDTO;
import com.example.FoodCo.Entity.Member;
import com.example.FoodCo.Exception.IdNotFoundException;
import com.example.FoodCo.Repository.MemberRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private AuthenticationManager authenticationManager;
    private JWTService jwtService;

    public MemberService(MemberRepository memberRepository, BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager, JWTService jwtService){
        this.memberRepository=memberRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.jwtService=jwtService;
        this.authenticationManager=authenticationManager;
    }

    public Member addMember(Member theMember){
        theMember.setRegisterDate(LocalDateTime.now());
        theMember.setPassword(bCryptPasswordEncoder.encode(theMember.getPassword()));
        return memberRepository.save(theMember);
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
        //düzenlenecek
        Member existingMember=memberRepository.findById(id).orElseThrow(()->new IdNotFoundException("member is not found"));
        existingMember.setGender(memberDTO.getGender());
        existingMember.setAge(memberDTO.getAge());
        existingMember.setEmail(memberDTO.getEmail());
        existingMember.setLastName(memberDTO.getLastName());
        existingMember.setFirstName(memberDTO.getFirstName());
        return memberRepository.save(existingMember);

    }
    public void deleteMember(int id) throws IdNotFoundException {
        //düzenlenecek
        Member member=memberRepository.findById(id).orElseThrow(()->new IdNotFoundException("Id not found member: "+id));
        memberRepository.deleteById(id);
    }
    // login for security
    public String verify(Member member){
        Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getUsername(), member.getPassword()));
        if (authentication.isAuthenticated()){
            return jwtService.generateToken(member.getUsername());
        }
        return "fail";
    }
    public Member getAuthenticatedMember() throws UsernameNotFoundException {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (principal instanceof UserDetails){
            username=((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        return memberRepository.findByUsername(username);
    }

}
