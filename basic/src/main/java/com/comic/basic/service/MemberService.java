package com.comic.basic.service;

import com.comic.basic.entity.MemberEntity;
import com.comic.basic.member.Member;
import com.comic.basic.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    public void save(Member member) {
        // repository의 save메소드 호출(조건. entity객체를 넘겨줘야 함)
        // 1. member -> entity변환
        // 2. repository의 save 메소드 호출
        MemberEntity memberEntity = MemberEntity.toMemberEntity(member);
        memberRepository.save(memberEntity);
    }

    public Member login(Member member){
        /*
        1. 회원이 입력한 이메일로 DB에서 조회를 함
        2. DB에서 조회한 비밀번호와 사용자가 입력한 비밀버호가 일치하는지 판단
         */
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(member.getMemberEmail());
        if(byMemberEmail.isPresent()){
            //조회결과가 있다.
            MemberEntity memberEntity = byMemberEmail.get();
            if(memberEntity.getMemberPassword().equals(member.getMemberPassword())){
                // 비밀번호 일치
                // entity -> member 변환 후 리턴
                Member memberDto = Member.toMember(memberEntity);
                return memberDto;
            }
            else {
                return null;
            }
        }else {
            return null;
        }
    }

    public List<Member> findAll(){
        List<MemberEntity> memberEntity = memberRepository.findAll();
        List<Member> list = new ArrayList<>();
        for (MemberEntity entity : memberEntity) {
            Member member = Member.toMember(entity);
            list.add(member);
        }
        return list;
    }

    public Member findById(Long id) {
           Optional<MemberEntity> optionalMemberEntity = memberRepository.findById(id);
           if(optionalMemberEntity.isPresent()){
        Member member = Member.toMember(optionalMemberEntity.get());
        return member;
           }
           else
           {
               return null;
           }

    }

    public Member updateForm(String myEmail){
        Optional<MemberEntity> optionalMemberEntity = memberRepository.findByMemberEmail(myEmail);
        if (optionalMemberEntity.isPresent()) {
            return Member.toMember(optionalMemberEntity.get());
        } else {
            return null;
        }
    }

    // save매소드:
    //id가 없으면 insert 쿼리를 실행
    //id가 있으면 DB 에서 id가 있는 객체가 업데이트 쿼리를 날려준다.
    //기존의 toMemberEntity 메소드는 id를 지정하지 않고, 자동으로 지정되었다.
    //반면 새로운 toUpdateMemberEntity 메소드는 id를 직접 지정해줬다.
    public void update(Member member) {
        memberRepository.save(MemberEntity.toUpdateMemberEntity(member));
    }

    public void deleteById(Long id) {
        memberRepository.deleteById(id);
    }
    public String emailCheck(String memberEmail) {
        Optional<MemberEntity> byMemberEmail = memberRepository.findByMemberEmail(memberEmail);
        if (byMemberEmail.isPresent()) {
            // 조회결과가 있다 -> 사용할 수 없다.
            return null;
        } else {
            // 조회결과가 없다 -> 사용할 수 있다.
            return "ok";
        }
    }
}

