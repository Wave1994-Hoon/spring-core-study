package hello.core.singletonTest;

public class StatefulService {

    private int price;  // 상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        this.price = price;  // 여기가 문제 !!
    }

    /* stateless */
    public int order2(String name, int price) {
        System.out.println("name = " + name + "price = " + price);
        this.price = price;
        return price;
    }

    public int getPrice() {
        return price;
    }

}
