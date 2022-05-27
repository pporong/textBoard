package controller;

import data.Member;
import infra.Container;
import infra.Request;

import service.MemberService;
import utils.Util;

import javax.swing.text.html.HTMLDocument;
import java.time.LocalDateTime;
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
            case "logout":
                logout(request);
                break;
            case "detail" :
                detail(request);
                break;
            case "modify" :
                modify(request);
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


    // 로그아웃 메서드
    public void logout(Request request){
        String logonMember = request.getLogonMember();
        System.out.println(logonMember + "님 로그아웃 되었습니다.");

        request.logout();
    }

    // detail 메서드
    public void detail(Request request){
        String paramkey = "loginId";

        if (!Util.hasParam(request, paramkey)){
            System.out.println(paramkey + "파라미터가 필요합니다.");
            return;
        }

        String loginId = request.getParameterStrValue(paramkey);

        Member findMember = memberService.getMemberByLoginId(loginId);

        if (findMember == null){
            System.out.println("해당 회원은 존재하지 않는 회원입니다.");
            return;
        }

        System.out.println(" == " + loginId + "님의 정보 ==");
        System.out.println("* ID : " + loginId);
        System.out.println("* NAME : " + findMember.getName());
        System.out.println("* Joined At : " + findMember.getRegDate());

    }

    // modify 메서드
    public void modify(Request request){
        String paramkey = "loginId";

        if (!Util.hasParam(request, paramkey)){
            System.out.println(paramkey + " 파라미터가 필요합니다.");
            return;
        }

        String logonMember = request.getLogonMember();
        String parameterValue = request.getParameterStrValue(paramkey);

        if (!logonMember.equals(parameterValue)){
            System.out.println("본인 정보만 수정할 수 있습니다.");
            return;
        }

        Member findMember = memberService.getMemberByLoginId(parameterValue);

        System.out.print("변경하고자 하는 비밀번호를 입력해 주세요. \r\n : ");
        String newPassword = sc.nextLine().trim();

        findMember.setPassword(newPassword);
        findMember.setUpdateDate(LocalDateTime.now());

        System.out.println("비밀번호가 변경되었습니다. \r\n 다음 로그인부터 반영됩니다.");

    }

}
