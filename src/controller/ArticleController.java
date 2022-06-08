package controller;

import data.Article;
import data.Member;
import infra.Container;
import infra.Request;
import service.ArticleService;
import utils.Util;

import java.time.LocalDateTime;
import java.util.List;
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
            case "detail":
                detail(request);
                break;
            case "delete":
                delete(request);
                break;
            case "modify":
                modify(request);
                break;
            case "list":
                getList(request);
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
            System.out.println("!! 게시글은 5글자 이상 작성해야 합니다. !!");
            return;
        }

        String author = request.getLogonMember();
        int articleId = articleService.write(title, body, author);

        System.out.println("'" + author + "' 님의 " + articleId + " 번째 글이 작성되었습니다. :) ");
    }


    // 게시글 상세보기 메서드
    public void detail(Request request){
        String paramKey = "id";

        if (!Util.hasParam(request, paramKey)){
            System.out.println("!! " + paramKey + " 파라미터가 필요합니다. !!");
            return;
        }

        // ex) article/detail?id=3
        int articleId = request.getParameterIntValue(paramKey);

        Article findArticle = articleService.findById(articleId);

        if (findArticle == null){
            System.out.println("!! 해당 게시번의 게시글이 존재하지 않습니다. :( !!");
            return;
        }

        System.out.println("== " + findArticle.getId() + " 번 게시글 ==");
        System.out.println("* 작성자 : " + findArticle.getAuthor());
        System.out.println("* 제목 : " + findArticle.getTitle());
        System.out.println("* 내용 : " + findArticle.getBody());

    }

    // 게시글 삭제 메서드
    public void delete(Request request){
        String paramKey = "id";

        if (!Util.hasParam(request, paramKey)){
            System.out.println("!! " + paramKey + " 파라미터가 필요합니다. !!");
            return;
        }

        int articleId = request.getParameterIntValue(paramKey);
        Article findArticle = articleService.findById(articleId);

        if (findArticle == null){
            System.out.println("!! 해당 게시물은 존재하지 않습니다. !!");
            return;
        }

        // 권한체크
        if (!request.getLogonMember().equals(findArticle.getAuthor())){
            System.out.println("!! 접근 권한이 없습니다. !! ");
            return;
        }

        articleService.delete(findArticle);

        System.out.println("삭제요청이 정상적으로 처리되었습니다.");

    }

    // 게시글 수정 메서드
    public void modify(Request request){
        String paramKey = "id";

        if (!Util.hasParam(request, paramKey)){
            System.out.println("!! " + paramKey + " 파라미터가 필요합니다. !!");
            return;
        }

        int articleId = request.getParameterIntValue(paramKey);
        Article findArticle = articleService.findById(articleId);

        if (findArticle == null){
            System.out.println("!! 해당 게시물은 존재하지 않습니다. !!");
        }

        if (!request.getLogonMember().equals(findArticle.getAuthor())){
            System.out.println("!! 본인 게시글만 수정 할 수 있습니다. !!");
            return;
        }

        System.out.println("== " + articleId + " 번 게시물 수정 ==");
        System.out.print("변경하고자 하는 제목을 입력해 주세요. \r\n : ");
        String newTitle = sc.nextLine().trim();
        System.out.print("변경하고자 하는 내용을 입력해 주세요.\r\n : ");
        String newBody = sc.nextLine().trim();

        findArticle.setTitle(newTitle);
        findArticle.setBody(newBody);

        findArticle.setUpdateDate(LocalDateTime.now());

        System.out.println("게시물이 성공적으로 수정되었습니다. :)");

    }

    // 게시물 불러오기 메서드 (list)
    public void getList(Request request){
        String paramKey = "page";
        if (!Util.hasParam(request, paramKey)){
            System.out.println("!! " + paramKey + " 파라미터가 필요합니다. !!");
            return;
        }

        List<Article> articles = articleService.getArticles();

        // 게시물이 없을 경우
        if (articles.size() == 0){
            System.out.println("*~*~*~ 게시물 목록 ~*~*~*");
            System.out.println("작성된 게시물이 없습니다.");
            return;
        }

        // 게시물이 5미만일 경우
        if (articles.size() < 5){
            System.out.println("*~*~*~ 게시물 목록 ~*~*~*");
            System.out.println();
            System.out.println("번호 | 제목 | 작성자 | 작성일");
            for (Article article : articles){
                System.out.println(article.getId()
                        + "  |  " + article.getTitle()
                        + "  |  " + article.getAuthor()
                        + "  |  " + article.getReDate());
            }

            System.out.println("(1/1)");

        }

        // 한 페이지당 게시물 5개씩 ㄴㅏ타내기(최신글 순으로)
        int size = 5;
        int currentPage = request.getParameterIntValue(paramKey);

        int startIndex = (currentPage - 1) * size;
        int lastIndex = ((currentPage - 1) * size) + size;

        int lastPage = (int) Math.ceil(articles.size() / size);

        if (currentPage == lastPage){
            lastIndex = articles.size();
        } else if (currentPage > lastPage){
            System.out.println("!! 잘못된 요청입니다. !!");
            return;
        }

        List<Article> pagedArticles =  articles.subList(startIndex, lastIndex);

        System.out.println("*~*~*~ 게시물 목록 ~*~*~*");
        System.out.println();
        System.out.println("번호 | 제목 | 작성자 | 작성일");

        for (Article pagedArticle : pagedArticles){
            System.out.println(pagedArticle.getId()
                    + "  |  " + pagedArticle.getTitle()
                    + "  |  " + pagedArticle.getAuthor()
                    + "  |  " + pagedArticle.getReDate());
        }
        System.out.println("(" + currentPage + "/" + lastPage + ")");

    }



}
