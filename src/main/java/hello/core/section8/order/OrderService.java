package hello.core.section8.order;

public interface OrderService {
    Order creatOrder(Long memberId, String itemName, int itemPrice);
}
