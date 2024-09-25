package com.giveus.auth.repository;

import com.giveus.auth.entity.Member;
import com.giveus.auth.entity.MemberSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberSettingRepository extends JpaRepository<MemberSetting, Integer> {

    Optional<MemberSetting> findMemberSettingByMember(Member member);

}
