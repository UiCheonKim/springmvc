package hello.springmvc.basic.request;

import jakarta.servlet.ServletInputStream;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/request-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        // 항상 stream 은 바이트코드 캐뤽터 셋 넣어야함
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        response.getWriter().write("ok");
    }

    @PostMapping("/request-body-string-v2")
    public void requestBodyStringV2(InputStream inputStream, Writer responseWriter) throws IOException {

        // InputStream(Reader) : Http 요청 메시지 바디의 내용을 직접 조회
        // OutputStream(Writer) : Http 응답 메시지의 바디에 직접 결과 출력

        // 항상 stream 은 바이트코드 캐뤽터 셋 넣어야함
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody = {}", messageBody);

        responseWriter.write("ok");
    }

    @PostMapping("/request-body-string-v3")
    public HttpEntity<String> requestBodyStringV3(HttpEntity<String> httpEntity) throws IOException {

        // 메시지 컨버터 <String>

        // HttpEntity -> Http header, body 정보를 편리하게 조회
        // 메시지 바디 정보를 직접 조회
        // 요청 파라미터를 조회하는 기능과 관계없음(@RequestParam X, @ModelAttribute X)
        // 요청 파마리터 -> get 에 query stream 오는것, post 방식 html form 방식으로 오는것, application/x-www-form-urlencoded 경우에만
        // 응답에도 사용가능, view 조회 X, 헤더정보 포함


        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);

        return new HttpEntity<>("ok");
    }

    @PostMapping("/request-body-string-v33")
    public ResponseEntity<String> requestBodyStringV33(RequestEntity<String> httpEntity) throws IOException {

        // 메시지 컨버터 <String>

        // HttpEntity -> Http header, body 정보를 편리하게 조회
        // 메시지 바디 정보를 직접 조회
        // 요청 파라미터를 조회하는 기능과 관계없음(@RequestParam X, @ModelAttribute X)
        // 요청 파마리터 -> get 에 query stream 오는것, post 방식 html form 방식으로 오는것, application/x-www-form-urlencoded 경우에만
        // 응답에도 사용가능, view 조회 X, 헤더정보 포함


        String messageBody = httpEntity.getBody();
        log.info("messageBody = {}", messageBody);

        return new ResponseEntity<String>("ok", HttpStatus.CREATED);
    }

    @ResponseBody
    @PostMapping("/request-body-string-v4")
    public String requestBodyStringV4(@RequestBody String messageBody) {

        log.info("messageBody = {}", messageBody);

        return "ok";
    }

}
