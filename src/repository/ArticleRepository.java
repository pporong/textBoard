package repository;

import data.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class ArticleRepository {

    private int articleId = 0;

    private List<Article> store = new ArrayList<>();


    public ArticleRepository(){
        for (int i = 1; i < 54; i++){
            Article article = new Article(i, "젬옥" + i, "내용내용" + i, "admin");
            this.store.add(article);
        }
    }

    // 게시물 불러오는 메서드
    public List<Article> getArticles() {
        return store;
    }

    public int saveArticle(String title, String body, String author){

        articleId += 1;

        Article article = new Article(articleId, title, body, author);
        store.add(article);

        return articleId;
    }

    public Article findById(int id) {
        for (Article article : store){
            if (article.getId() == id){
                return article;
            }
        }
        return null;
    }

    public void delete(Article article){
        store.remove(article);
    }





}
