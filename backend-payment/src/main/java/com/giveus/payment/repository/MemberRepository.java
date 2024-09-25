package com.giveus.payment.repository;

import com.giveus.payment.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Integer> {

    Member findById(int memberNo);
}
