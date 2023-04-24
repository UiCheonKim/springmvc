package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok"); // void 타입 때문에
    }

    @ResponseBody // -> view 조회를 하기 때문에 @RestController 를 쓰던가 @ResponseBody 를 추가 해주면 됨
    // ok 이라는 문자를 http 응답 메시지에 바로 넣어서 응답해준다.
    @RequestMapping("request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge
    ) {

        // @RequestParam 은 request.getParameter("username") 과 같은 역할
        log.info("memberName = {}, memberAge = {}", memberName, memberAge);

        return "ok"; // @Controller 이면서 return 이 String 이면 viewResolver 가 ok 라는 view 를 찾게 됨
    }

    @ResponseBody // -> view 조회를 하기 때문에 @RestController 를 쓰던가 @ResponseBody 를 추가 해주면 됨
    // ok 이라는 문자를 http 응답 메시지에 바로 넣어서 응답해준다.
    @RequestMapping("request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age
    ) {

        // @RequestParam 은 request.getParameter("username") 과 같은 역할
        log.info("username = {}, age = {}", username, age);

        return "ok"; // @Controller 이면서 return 이 String 이면 viewResolver 가 ok 라는 view 를 찾게 됨
    }

    @ResponseBody // -> view 조회를 하기 때문에 @RestController 를 쓰던가 @ResponseBody 를 추가 해주면 됨
    // ok 이라는 문자를 http 응답 메시지에 바로 넣어서 응답해준다.
    @RequestMapping("request-param-v4")
    public String requestParamV4(String username, int age) {
        // String, int, Integer 등 단순타입이면 @RequestParam 생략가능
        // @RequestParam 은 request.getParameter("username") 과 같은 역할
        log.info("username = {}, age = {}", username, age);

        return "ok"; // @Controller 이면서 return 이 String 이면 viewResolver 가 ok 라는 view 를 찾게 됨
    }

    @ResponseBody // -> view 조회를 하기 때문에 @RestController 를 쓰던가 @ResponseBody 를 추가 해주면 됨
    // ok 이라는 문자를 http 응답 메시지에 바로 넣어서 응답해준다.
    @RequestMapping("request-param-required")
    public String requestParamRequired(
            // require false 는 선택, true 는 필수로 없을 시 400 에러 발생
            @RequestParam(required = false) String username,
            @RequestParam(required = false) Integer age) {
        // @RequestParam 은 request.getParameter("username") 과 같은 역할
        log.info("username = {}, age = {}", username, age);

        return "ok"; // @Controller 이면서 return 이 String 이면 viewResolver 가 ok 라는 view 를 찾게 됨
    }

    @ResponseBody // -> view 조회를 하기 때문에 @RestController 를 쓰던가 @ResponseBody 를 추가 해주면 됨
    // ok 이라는 문자를 http 응답 메시지에 바로 넣어서 응답해준다.
    @RequestMapping("request-param-default")
    public String requestParamDefault(
            // require false 는 선택, true 는 필수로 없을 시 400 에러 발생
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        // @RequestParam 은 request.getParameter("username") 과 같은 역할
        log.info("username = {}, age = {}", username, age);

        return "ok"; // @Controller 이면서 return 이 String 이면 viewResolver 가 ok 라는 view 를 찾게 됨
    }

    @ResponseBody // -> view 조회를 하기 때문에 @RestController 를 쓰던가 @ResponseBody 를 추가 해주면 됨
    // ok 이라는 문자를 http 응답 메시지에 바로 넣어서 응답해준다.
    @RequestMapping("request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        // @RequestParam 은 request.getParameter("username") 과 같은 역할
        log.info("username = {}, age = {}", paramMap.get("username"), paramMap.get("age"));

        return "ok"; // @Controller 이면서 return 이 String 이면 viewResolver 가 ok 라는 view 를 찾게 됨
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v111")
    public String modelAttributeV111(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        // 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다.
        // 그리고 해당 프로퍼티의 setter 를 호출해서 파라미터의 값을 바인딩한다.

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);

        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) {
        // 요청 파라미터의 이름으로 HelloData 객체의 프로퍼티를 찾는다.
        // 그리고 해당 프로퍼티의 setter 를 호출해서 파라미터의 값을 바인딩한다.

        // @ModelAttribute 생략 가능

        log.info("username = {}, age = {}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData);

        return "ok";
    }
}
