package hello.core.singletonTest;

public class SingletonService {

    /*
    * static을 쓰면 클래스 레벨에 올라가기 때문에 딱 하나만 존재함
    * 자바가 실행될 때 static 영역을 확인하고 내부적으로 실행해서 객체를 생성해서 값을 생성한다.
    */
    private static final SingletonService instance = new SingletonService();

    /* 의존 관계상 클라이언트가 구체 글래스에 의존하게 함 -> DIP 위반
    * 이미 위에서 new 로 객체 생성함
    */
    public static SingletonService getInstance() {
        return instance;
    }

    /* 외부에서 객체 생성 막음 */
    private SingletonService() {

    }

    public void logic() {
        System.out.println("싱글톤 객체 로직 호출");
    }
}
