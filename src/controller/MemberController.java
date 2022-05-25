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
        // /members//[target]
        switch (request.getTarget()){
            case "join":
                saveMember();
                break;
            case "login":
                login(request);
                break;
            default:
                System.out.println("올바른 요청을 보내주세요.");
                break;
        }
    }

    // 메서드 생성

    // 회원가입 메서드
    public void saveMember(){
        System.out.println(" === 회원가입 === ");

        System.out.println(" == 아이디를 입력해 주세요. == ");
        String loginId = sc.nextLine().trim();

        if (memberService.isExistsByLoginId(loginId)){
            System.out.println("이미 존재하는 아이디 입니다 !");
            return;
        }

        System.out.println(" == 비밀번호를 입력해 주세요. == ");
        String password = sc.nextLine().trim();

        System.out.println(" == 이름 == ");
        String name = sc.nextLine().trim();

        int memberId = memberService.saveMember(loginId, password, name);

        System.out.println("환영합니다, " + memberId + "번 째 회원님 !");
    }


    // 로그인 메서드
    public void login(Request request){

        System.out.println(" === 로그인 === ");

        System.out.print("★ 아이디 : ");
        String loginId = sc.nextLine().trim();

        if (!memberService.isExistsByLoginId(loginId)){
            System.out.println("존재하지 않는 계정입니다. 다시 한 번 확인해 주세요. ");
            return;
        }

        System.out.print("★ 비밀번호 : ");
        String password = sc.nextLine().trim();

        if (!memberService.isCorrectInfo(loginId, password)){
            System.out.println("아이디 혹은 비밀번호가 정확하지 않습니다. 다시 한 번 확인해 주세요.");
            return;
        }

        request.login(loginId);

        System.out.println(loginId + "님 반갑습니다 :) !");
    }

}
