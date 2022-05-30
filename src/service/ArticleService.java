package service;

import infra.Container;
import repository.ArticleRepository;

public class ArticleService {

    // 필드 생성
    private ArticleRepository articleRepository;

    // 생성자 생성
    public ArticleService(){
        articleRepository = Container.articleRepository;
    }

    public int write(String title, String body, String author){
        return articleRepository.saveArticle(title, body, author);
    }


}
