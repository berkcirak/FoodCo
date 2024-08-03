package com.example.FoodCo.Repository;

import com.example.FoodCo.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {


    Optional<Member> findByUser_Username(String username);
    Optional<Member> findMemberByUserId(int userId);
}
