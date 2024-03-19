package com.comic.basic.entity;

import com.comic.basic.member.Member;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
@Table(name = "member_table")
public class MemberEntity {
    @Id //pk 지정
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(unique = true) //unique 제약조건 추가
    private String memberEmail;

    @Column
    private String memberName;

    @Column
    private String memberPassword;


    public static MemberEntity toMemberEntity(Member member){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setMemberEmail(member.getMemberEmail());
        memberEntity.setMemberName(member.getMemberName());
        memberEntity.setMemberPassword(member.getMemberPassword());
        return memberEntity;
    }

    public static MemberEntity toUpdateMemberEntity(Member member){
        MemberEntity memberEntity = new MemberEntity();
        memberEntity.setId(member.getId());
        memberEntity.setMemberEmail(member.getMemberEmail());
        memberEntity.setMemberName(member.getMemberName());
        memberEntity.setMemberPassword(member.getMemberPassword());
        return memberEntity;
    }
}
