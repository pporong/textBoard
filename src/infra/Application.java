package infra;

import controller.Controller;

import java.util.Scanner;

public class Application {

    private Scanner sc = Container.sc;
    private boolean isActive = true;       //while문을 돌려서 true면 실행, false면 종료;


    public void run(){

        while (isActive){
            System.out.println("명령어 : ");
            String inputUri = sc.nextLine().trim();

            Request request = new Request(inputUri);

            Controller controller = getController(request.getControllerCode());

            if(controller != null) {
                controller.execute(request);
            } else {
                System.out.println("올바른 URI를 입력하여 주세요.");
            }

        }

    }

    // 메서드 생성
    public Controller getController(String code){

        switch (code){
            case "system":
                return Container.systemController;
            default:
                return null;
        }


    }



}
