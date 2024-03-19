package com.comic.basic.repository;

import com.comic.basic.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


// HpaRepository를 상속받을 때 뒤에 <1,2>를 사용할때 규칙
// 1 = Entity클래스 이름
// 2 = pk의 타입
@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
 //이메일로 회원 정보 조회(select * from member_table where member_email=?
    // 규칙만 잘지켜서 메소드를 만들면 jpa가 쿼리를 자동으로 작성해준다.
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
// Entity객체로 리턴해줘야함,
}
