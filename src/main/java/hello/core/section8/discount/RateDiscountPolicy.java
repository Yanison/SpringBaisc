package hello.core.section8.discount;

import hello.core.section8.member.Grade;
import hello.core.section8.member.Member;

public class RateDiscountPolicy implements DiscountPolicy{

    private int discountPercent = 10;
    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * discountPercent /100;
        }else {
            return 0;
        }
    }
}
