package hello.core.section9.order;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(

        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
        /**
         * @Configuration 붙은 객체는 Scan 제외
         * @Configuration 객체를 살펴보면 @Componet가 붙어있다.
        */
)
public class AutoAppConfig {

}
