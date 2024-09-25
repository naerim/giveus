package com.giveus.auth.repository;

import com.giveus.auth.entity.Member;
import com.giveus.auth.entity.MemberDevice;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthMemberDeviceRepository extends JpaRepository<MemberDevice, Integer> {
    Optional<MemberDevice> findByDeviceToken(String deviceToken);

    void deleteByMemberNoAndDeviceToken(int memberNo, String deviceToken);
}
