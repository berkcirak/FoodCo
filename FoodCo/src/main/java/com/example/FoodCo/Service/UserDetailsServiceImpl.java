package com.example.FoodCo.Service;

import com.example.FoodCo.Entity.Member;
import com.example.FoodCo.Entity.MemberPrincipal;
import com.example.FoodCo.Repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private MemberRepository memberRepository;

    public UserDetailsServiceImpl(MemberRepository memberRepository){
        this.memberRepository=memberRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username);
        if (member == null){
            throw new UsernameNotFoundException("user not found");
        }
        return new MemberPrincipal(member);
    }
}
