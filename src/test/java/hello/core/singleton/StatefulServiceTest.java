package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

import static org.junit.jupiter.api.Assertions.*;

class StatefulServiceTest {
    @Test
    void statefulServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext();
        StatefulService statefulService1 = ac.getBean("statefulService",StatefulService.class);
        StatefulService statefulService2 = ac.getBean("statefulService",StatefulService.class);

        // Thread A : user A ordered price as 10000
        statefulService1.order("userA",10000);
        // Thread B: user B ordered price as 20000
        statefulService2.order("userB",20000);

        //
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        /**
         * 공유 필드에 접근하기 때문에 동시성 이슈가 생긴다. 공유자원에 접근할 때 항상 조심하자
         */

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{
        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}