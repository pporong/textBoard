package controller;

import infra.Container;
import infra.Request;
import service.ArticleService;

import java.util.Scanner;

public class ArticleController implements Controller {

    // 필드 생성
    private Scanner sc;
    private ArticleService articleService;

    public ArticleController(){
        this.sc = Container.sc;
        this.articleService = Container.articleService;
    }




    @Override
    public void execute(Request request) {

        // /articles/write -> 회원가입
        switch (request.getTarget()){
            case "write":
                write(request);
                break;
            default :
                System.out.println("존재하지 않는 페이지 입니다.");
                break;
        }

    }

    // 메서드 생성

    // 게시글 작성 메서드
    public void write(Request request){
        System.out.println("== 게시글 작성 ==");

        System.out.print("* 제목 : ");
        String title = sc.nextLine().trim();
        // 제목 조건
        if (title.length() == 0){
            System.out.println("!! 제목을 입력하여 주시기 바랍니다. !!");
        }

        System.out.print("* 내용 : ");
        String body = sc.nextLine().trim();
        // 내용 조건
        if (body.length() < 5){
            System.out.println("!! 5글자 이상 작성해야 합니다. !!");
            return;
        }

        String author = request.getLogonMember();
        int articleId = articleService.write(title, body, author);

        System.out.println("'" + author+"' 님의 " + articleId + " 번째 글이 작성되었습니다. :) ");
    }





}
