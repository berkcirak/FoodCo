package com.example.FoodCo;

import com.example.FoodCo.Entity.Member;
import com.example.FoodCo.Entity.Role;
import com.example.FoodCo.Entity.User;
import com.example.FoodCo.Repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Set;

@SpringBootApplication
public class FoodCoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FoodCoApplication.class, args);
	}

	private final MemberRepository memberRepository;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	public FoodCoApplication(MemberRepository memberRepository,BCryptPasswordEncoder bCryptPasswordEncoder){
		this.memberRepository=memberRepository;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}
	@Override
	public void run(String... args) throws Exception {

	}
	public void CreateDummyData(){
		User user=User.builder()
				.username("bcirak")
				.password(bCryptPasswordEncoder.encode("6161"))
				.authorities(Set.of(Role.ROLE_MEMBER))
				.isEnabled(true)
				.accountNonLocked(true)
				.accountNonExpired(true)
				.isCredentialsNonExpired(true)
				.build();
		Member member=Member.builder()
				.email("atalayberkcrak@gmail.com")
				.age(22)
				.gender("male")
				.firstName("berk")
				.lastName("cirak")
				.user(user)
				.build();
		memberRepository.save(member);
	}
}
