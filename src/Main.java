import infra.Application;

    // 게시판 설명
// article/list -> 게시글 목록 = 게시글을 전부 조회해서 보여줌 ; 목록을 보여주고 끝(GET)
// article/write -> 게시글 작성 폼 = 어떤 데이터를 입력받아 배열에 저장
//                  ; 게시글 작성 폼을 보여주고(GET) / 데이터를 입력받아 DB에 저장(POST)


public class Main {

    public static void main(String[] args) {

        Application app = new Application("ppodaezz.com");
        app.run();

    }


}
