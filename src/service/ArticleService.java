package service;

import data.Article;
import infra.Container;
import repository.ArticleRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

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

    public List<Article> getArticles(){

        List<Article> articles = articleRepository.getArticles();

        List<Article> reversedArticle = new ArrayList<>();

        for (Article article : articles){
            reversedArticle.add(article);
        }

        Collections.reverse(reversedArticle);

        return reversedArticle;
    }


}
