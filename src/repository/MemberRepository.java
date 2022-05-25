package repository;

import data.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberRepository {

    // 필드 생성
    private int memberId = 0;
    // DB 생성 ; 배열로
    private List<Member> store = new ArrayList<>();

    public int saveMember(String loginId, String password, String name){

        memberId += 1;

        Member member = new Member(memberId, loginId, password, name);
        store.add(member);

        return memberId;
    }


    // 회원 아이디로 회원 존재여부 조회
    // 존재하면 true, 존재하지 않으면 false를 반환
    public boolean isExistsByLoginId(String loginId) {
        return getMemberByLoginId(loginId) != null;
    }


    // 로그인 할 아이디로 회원조회
    public Member getMemberByLoginId(String loginId){
        for (Member member : store) {
            if (member.getLoginId().equals(loginId)){
                return member;
            }
        }
        return null;
    }

    // 전체 회원 목록을 불러오는 메서드
    public List<Member> getMembers(){
        return store;
    }

    // 회원 번호로 회원 단건 조회하는 메서드
    public Member getMemberById(int id){
        for (Member member : store) {
            if (member.getId() == id){
                return member;
            }
        }
        return null;
    }
}
