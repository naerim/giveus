package com.giveus.user.service;

import com.giveus.user.dto.request.UserRegisterPostReq;
import com.giveus.user.dto.request.UserUpdatePatchReq;
import com.giveus.user.entity.User;
import com.giveus.user.exception.UserNotFoundException;
import com.giveus.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 유저 관련 비즈니스 로직 처리를 위한 서비스 구현 정의.
 */
@Service("userService")
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User createUser(UserRegisterPostReq userRegisterInfo) {
        User user = new User();
        user.setUserId(userRegisterInfo.getId());
        // 보안을 위해서 유저 패스워드 암호화 하여 디비에 저장.
        user.setPassword(passwordEncoder.encode(userRegisterInfo.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUserByUserId(String userId) {
        // 디비에 유저 정보 조회 (userId 를 통한 조회).
        return userRepository.findUserByUserId(userId)
            .orElseThrow(UserNotFoundException::new);
    }

    @Override
    public User updateUser(String userId, UserUpdatePatchReq userUpdateInfo) {
        // 존재하지 않는 회원일경우 예외처리
        User user = userRepository.findUserByUserId(userId)
            .orElseThrow(UserNotFoundException::new);

        user.setDepartment(userUpdateInfo.getDepartment());
        user.setName(userUpdateInfo.getName());
        user.setPosition(userUpdateInfo.getPosition());

        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserByUserId(String userId) {
        // 해당 유저 정보 삭제
        userRepository.deleteByUserId(userId);
    }
}
