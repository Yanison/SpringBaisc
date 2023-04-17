package hello.core.section9.order;

public interface OrderService {
    Order creatOrder(Long memberId, String itemName, int itemPrice);
}
