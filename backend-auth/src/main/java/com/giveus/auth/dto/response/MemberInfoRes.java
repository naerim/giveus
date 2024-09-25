package com.giveus.auth.dto.response;

import com.giveus.auth.entity.Member;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
@ToString
public class MemberInfoRes {

    int memberNo;

    String email;

    String name;

    String nickname;

    private LocalDateTime createdAt;

    private String imageUrl;

    private String provider;

    private String snsKey;

    public static MemberInfoRes from(Member member) {
        return MemberInfoRes.builder()
                .memberNo(member.getMemberNo())
                .email(member.getEmail())
                .name(member.getName())
                .nickname(member.getNickname())
                .createdAt(member.getCreatedAt())
                .imageUrl(member.getImageUrl())
                .provider(member.getProvider())
                .snsKey(member.getKey())
                .build();
    }

}
