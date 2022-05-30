package data;

import java.time.LocalDateTime;

public class Article {

    // 아이디
   private int id;

    // 필드 ; 게시글 제목 / 내용
    private String title;
    private String body;

    // 필드 ; 게시글 작성자
    private String author;

    // 필드 ; 게시글 작성날짜
    private LocalDateTime reDate = LocalDateTime.now();
    // 필드 / 게시글 수정날짜
    private LocalDateTime updateDate = LocalDateTime.now();


    // 생성자 생성
    public Article(String title, String body, String author){

        this.title = title;
        this.body = body;
        this.author = author;

    }

    // 게터 세터 생성
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getReDate() {
        return reDate;
    }

    public void setReDate(LocalDateTime reDate) {
        this.reDate = reDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }
}
