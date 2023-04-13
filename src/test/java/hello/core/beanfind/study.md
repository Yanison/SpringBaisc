# Bean 등록하고 조회하기.

```
AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
TestConfig.class -> @Bean으로 등록한 설정 정보들
```

AnnotationConfigApplicationContext를 사용하여서 BeanFactory에서 제공해주는 Bean 조회 기능들을 살펴보았다.
<br>
사용해본 기능들은 아래와 같다. 
* getBeanDefinitionNames(): 스프링에 등록된 모든 빈 이름을 조회한다.
* getBean() : 빈 이름으로 빈 객체(인스턴스)를 조회한다.
* 스프링 내부에서 사용하는 Bean은 getRole() 로 구분할 수 있다.
* 'ROLE_APPLICATION' : 일반적으로 사용자가 정의한 Bean
* 'ROLE_INFRASTRUCTURE' : 스프링이 내부에서 사용하는 빈

