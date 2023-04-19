# 중복 등록과 충돌

#### 컴포넌트 스캔에서 같은 빈 이름을 등록하면 어떻게 될까?
#### 다음 두가지 상황이 발생된다. 


1. 자동 빈 등록 vs 자동 빈 등록
2. 자동 빈 등록 vs 수동 빈 등록

### 자동 빈 등록 vs 자동 빈 등록
- 스프링 부트는 자동 빈 등록과 수동 빈 등록을 모두 지원한다.
  - 자동 빈 등록 : @ComponentScan
  - 수동 빈 등록 : @Configuration, @Bean

```java
import java.beans.ConstructorProperties;

@Configuration
@ConstructorProperties(
        exclude = @Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
    // 수동 빈 등록
    @Bean(name = "memoryMemberRepository")
    MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
```

이 경우는 수동 빈 등록이 우선권을 가진다.
(수동 빈이 자동 빈을 오버라이딩 해버린다.)

#### "수동 빈 등록시 남는 로그"
```text
Overriding bean definition for bean 'memoryMemberRepository' with a different
definition : replacing

-> memoryMemberRepository라는 이름의 빈을 다른 빈으로 대체하고 있습니다.
```

몰론 개발자가 의도적으로 이런 결과를 기대했다면, 자동 보다는 수동이 우선권을 가지는 것이 좋다.<br>
하지만 현실은 개발자가 와도 의도적으로 설정해서 이런결과가 만들어지기 보다는 여러 설정들이 꼬여서 이런 결과가 만들어지는 경우가 대부분이다.<br>
"그러면 정말 잡기 어려운 버그가 만들어진다. 항상 잡기 어려운 버그는 애매한 버그다"
그래서 최근 스프링 부트에서는 수동 빈 등록과 자동 빈 등록이 충돌이나면 오류가 발생하도록 기본 값을 바꾸었다.
<br>
<br>
#### "수동 빈 등록,자동 빈 등록 오류시 스프링 부트 에러"
```text
'Consider renaming one of the beans or enabling overriding by serring spring.main.allow-bean-definition-overriding=true'
```
<br>
<br>
스프링 부트인 'CoreApplication'을 실행하면 위와 같은 에러가 발생한다.<br>




