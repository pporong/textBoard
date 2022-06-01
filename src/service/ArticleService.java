package service;

import data.Article;
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

    public Article findById(int id){
        return articleRepository.findById(id);
    }

    public void delete(Article article){
        articleRepository.delete(article);
    }




}
