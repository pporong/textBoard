package controller;

import infra.Container;
import infra.Request;
import service.MemberService;

import java.util.Scanner;

public class MemberController implements Controller {

    private Scanner sc;
    private MemberService memberService;

    public MemberController(){
        this.sc = Container.sc;
        this.memberService = Container.memberService;
    }



    // /members/join -> 회원가입
    @Override
    public void execute(Request request) {

        switch (request.getTarget()){
            case "join":
                saveMember();
                break;
            default:
                System.out.println("올바른 요청을 보내주세요.");
                break;
        }
    }

    // 메서드 생성
    public void saveMember(){
        System.out.println(" === 회원가입 === ");

        System.out.println(" == 아이디를 입력해 주세요. == ");
        String loginId = sc.nextLine().trim();

        System.out.println(" == 비밀번호를 입력해 주세요. == ");
        String password = sc.nextLine().trim();

        System.out.println(" == 이름 == ");
        String name = sc.nextLine().trim();

        int memberId = memberService.saveMember(loginId, password, name);

        System.out.println("환영합니다, " + memberId + "번 째 회원님 !");
    }

}
