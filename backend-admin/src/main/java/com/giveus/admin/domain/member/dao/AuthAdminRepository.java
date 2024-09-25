package com.giveus.admin.domain.member.dao;

import com.giveus.admin.domain.member.domain.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthAdminRepository extends JpaRepository<Admin, Integer> {
    Optional<Admin> findByProviderAndKey(String provider, String key);

    Optional<Admin> findByProviderAndEmail(String provider, String email);

    Optional<Admin> findByAdminNo(int adminNo);

}
