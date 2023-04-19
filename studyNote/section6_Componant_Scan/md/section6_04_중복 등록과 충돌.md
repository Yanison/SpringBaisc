# 다양한 의존관계 주입

#### 의존관계 주입은 크게 4가지 방법이 있다.
- 생성자 주입
- 수정자 주입(setter 주입)
- 필드 주입
- 일반 메서드 주입

## 생성자 주입

- 이름 그대로 생성자를 통해서 의존관계를 주입하는 방법이다.
- 지금까지 우리가 사용한 방법이다.
- 특징
  - 생성자 호출 시점에 딱 1번만 호출되는 것을 보장한다.
  - 불변, 필수 의존관계에 사용한다.

```java
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
```
"좋은 아키텍쳐는 Limit, 제한이 있어야 한다."


#### 생성자가 1개일땐 @Autowired를 생략해도 된다.

```java
public class MemberServiceImpl implements MemberService {
    private final MemberRepository memberRepository;
    //@Autowired 생략
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }
}
```

#### 생성자가 1개가 아닐땐 @Autowired를 생략하면 안된다.

```java
import com.sun.org.apache.xpath.internal.operations.Or;

public class OrderServiceImpl implements OrderService {
  private final MemberRepository memberRepository;
  private final DiscountPolicy discountPolicy;

  public OrderServiceImpl(){}

  @Autowired
  public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
    this.memberRepository = memberRepository;
    this.discountPolicy = discountPolicy;
  }
}
```

<br>
#### 수정자 주입(setter 주입)

- setter를 통해서 의존관계를 주입하는 방법이다.
- 특징
  - 선택, 변경 가능성이 있는 의존관계에 사용한다.
  - 자바빈 프로퍼티 규약의 수정자 메서드 방식을 사용한다.

```java
public class OrderServiceImpl implements OrderService {
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Autowired
    public void setDiscountPolicy(DiscountPolicy discountPolicy) {
        this.discountPolicy = discountPolicy;
    }
}
```
<br>

```text
참고로 @Autowired 의 기본 동작은 주입할 대상이 없으면 오류가 발생한다. 
주입할 대상이 없어도 동작하게 하려면 @Autowired(required = false) 처럼 지정하면 된다.

자바빈 프로퍼티, 자바에서는 과거부터 필드의 값을 직접 변경하지 않고, setXxx,getXxx 라는 메서드를 통해서 값을 읽거나
수정하는 규칙을 만들었었는데, 그것이 자바빈 프로퍼티 규칙이다. 더 자세한 내용이 궁금하면 자바 빈 프로퍼티로 검색해보자
```

<br>

#### 자바빈 프로퍼티 규약 예시
```java
public class Member {
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
```
<br>
#### 필드 주입
- 이름 그대로 필드에 바로 주입하는 방법이다.  
- 특징
  - 코드가 간결해서 많은 개발자들이 좋아한다. 그렇지만 외부에서 변경이 불가능해서 테스트 하기 힘들다는 치명적인 단점이 있다.
  - DI 프레임워크가 없으면 아무것도 할 수 없다.
  - public이면 어디서든 변경이 가능하다.
  - 권장하지 않는다.
- 이럴땐 사용해도 괜찮다.
  - 테스트 코드를 간결하게 만들기 위해서
  - @Configuration 과 같이 사용한다.

```java
public class OrderServiceImpl implements OrderService {
    @Autowired private MemberRepository memberRepository;
    @Autowired private DiscountPolicy discountPolicy;
}
```

<br>


#### @Congiguration 과 함께 사용하는 필드 주입
```java
@Component
public class OrderServiceImpl implements OrderService {
  @Autowired private MemberRepository memberRepository;
  @Autowired private DiscountPolicy discountPolicy;

//    @Autowired
//    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
//        this.memberRepository = memberRepository;
//        this.discountPolicy = discountPolicy;
//    }
}

@Configuration
@ComponentScan(
        excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)
)
public class AutoAppConfig {
  @Autowired MemberRepository memberRepository;
  @Autowired DiscountPolicy discountPolicy;

  @Bean(name = "memoryMemberRepository")
  MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}

```

<br>

#### 일반 메서드 주입
- 일반 메서드를 통해서 의존관계를 주입하는 방법이다.
- 특징
  - 한번에 여러 필드를 주입할 수 있다.
  - 일반적으로 잘 사용하지 않는다.
  - 생성자와 수정자 주입으로 대부부 해결하기 때문에 잘 사용하지 않는다.

```java
public class OrderServiceImpl implements OrderService {
    private MemberRepository memberRepository;
    private DiscountPolicy discountPolicy;

    @Autowired // init이라는 메서드를 수정자 주입처럼 주입한다.
    public void init(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }
}
```

<br>

### 정리

#### 의존관계 주입은 다음 4가지 방법이 있다.
- 생성자 주입
- 수정자 주입(setter 주입)
- 필드 주입
- 일반 메서드 주입

생성자와 수정자 주입으로 대부분이 해결된다. 필드주입은 비즈니스 로직에 절대 사용하지 말자.