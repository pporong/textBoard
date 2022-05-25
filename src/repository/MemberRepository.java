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

}
