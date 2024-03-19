package com.comic.basic.member;

import com.comic.basic.entity.MemberEntity;
import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Member {
    private Long id;
    private String memberName;
    private String memberPassword;
    private String memberEmail;

    public static Member toMember(MemberEntity memberEntity){
        Member member = new Member();
        member.setId(memberEntity.getId());
        member.setMemberEmail(memberEntity.getMemberEmail());
        member.setMemberName(memberEntity.getMemberName());
        member.setMemberPassword(memberEntity.getMemberPassword());
        return member;
    }

}
