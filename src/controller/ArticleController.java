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

        switch (request.getTarget()){
            default :
                System.out.println("존재하지 않는 페이지 입니다.");
                break;
        }

    }





}
