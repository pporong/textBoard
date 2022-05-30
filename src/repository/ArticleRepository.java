package repository;

import data.Article;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;

public class ArticleRepository {

    private int articleId = 0;

    private List<Article> store = new ArrayList<>();

    public int saveArticle(String title, String body, String author){

        articleId += 1;

        Article article = new Article(articleId, title, body, author);
        store.add(article);

        return articleId;
    }



}
