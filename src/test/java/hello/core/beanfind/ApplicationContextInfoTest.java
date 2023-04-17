package hello.core.beanfind;

import hello.core.section8.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * AnnotationConfigApplicationContext 에서 제공해는 빈 출력 메소드
 * getBeanDefinitionNames(): 스프링에 등록된 모든 빈 이름을 조회한다.
 * getBean() : 빈 이름으로 빈 객체(인스턴스)를 조회한다.
 *
 * 스프링 내부에서 사용하는 Bean은 getRole() 로 구분할 수 있다.
 * 'ROLE_APPLICATION' : 일반적으로 사용자가 정의한 Bean
 * 'ROLE_INFRASTRUCTURE' : 스프링이 내부에서 사용하는 빈
 */
public class ApplicationContextInfoTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean(){
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName: beanDefinitionNames) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "object = " + bean);
            }
            }
            //cmd + d -> 메소드 복사하기
            @Test
            @DisplayName("내가 App개발을 위해 등록한 모든 빈 출력하기")
            void findApplicationBean(){
                String[] beanDefinitionNames = ac.getBeanDefinitionNames();
                for (String beanDefinitionName: beanDefinitionNames) {
                    BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);
                    if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                        /**
                         * beanDefinition에서 Role이 ROLE_APPLICATION인 Bean들을 가져온다.
                         */
                        Object bean = ac.getBean(beanDefinitionName);
                        System.out.println("name = " + beanDefinitionName + "object = " + bean);
                    }

                }


    }
}
