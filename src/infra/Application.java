package infra;

import controller.Controller;

import java.util.Scanner;

public class Application {

    private Scanner sc = Container.sc;
    private boolean isActive = true;       //while문을 돌려서 true면 실행, false면 종료;

    // 필드생성
    private String applicationName;

    public Application(String applicationName){
        this.applicationName = applicationName;
    }


    public void run(){

        while (isActive){

            String domain = "https://" + applicationName;

//            System.out.println("명령어 : ");
            System.out.print(domain);
            String inputUri = sc.nextLine().trim();

            Request request = new Request(inputUri);

            Filter filter = new Filter(request);

            if (!filter.isValidRequest()){
                System.out.println("잘못된 요청입니다. 다시 확인해 주세요.");
                continue;
            }

            Controller controller = getController(request.getControllerCode());

            if(controller != null) {
                controller.execute(request);
            } else {
                System.out.println("올바른 URI를 입력하여 주세요.");
            }

        }

    }

    // /members
    // 메서드 생성
    public Controller getController(String code){

        switch (code){
            case "system":
                return Container.systemController;
            case "member":
            case "members":
                return Container.memberController;
            default:
                return null;
        }


    }



}
