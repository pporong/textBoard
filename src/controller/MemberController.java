package controller;

import infra.Container;
import service.MemberService;

public class MemberController {

    private MemberService memberService;

    public MemberController(){
        this.memberService = Container.memberService;
    }



}
