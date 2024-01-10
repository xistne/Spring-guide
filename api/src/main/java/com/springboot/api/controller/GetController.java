package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/get-api")
public class GetController {

    private final Logger LOGGER = LoggerFactory.getLogger(GetController.class);

    // http://localhost:8080/api/v1/get-api/hello
    // @RequestMapping으로 구현
    @RequestMapping(value="/hello", method = RequestMethod.GET)
    public String getHello() {
        LOGGER.info("getHello 메서드가 호출되었습니다.");
        return "Hello World";
    }

    // http://localhost:8080/api/v1/get-api/name
    // 매개변수가 없는 Get 메서드 구현
    @GetMapping(value = "/name")
    public String getName() {
        LOGGER.info("getName 메서드가 호출되었습니다.");
        return "Flature";
    }

    // http://localhost:8080/api/v1/get-api/variable1/{String 값}
    // @PathVariable을 사용한 Get 구현
    @GetMapping(value = "/variable1/{variable}")
    public String getVariable1(@PathVariable String variable) {
        LOGGER.info("@PathVariable을 통해 들어온 값 : {}", variable);
        return variable;
    }

    // http://localhost:8080/api/v1/get-api/variable2/{String 값}
    // 변수의 이름과 매겨변수의 이름을 다르게 하고싶을경우
    @GetMapping(value = "/variable2/{variable}")
    public String getVariable2(@PathVariable("variable") String var) {
        return var;
    }

    // http://localhost:8080/api/v1/get-api/request1?name=value1&email=value2&organization=value3
    // @RequestParam을 이용한 Get 메서드 구현
    @ApiOperation(value = "GET 메서드 예제", notes = "@RequestParam을 활용한 GET Method")
    @GetMapping(value = "/request1")
    public String getRequestParam1(
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "이메일", required = true) @RequestParam String email,
            @ApiParam(value = "회사", required = true) @RequestParam String organization) {
        return name + " " + email + " " + organization;
    }

    // http://localhost:8080/api/v1/get-api/request2?key1=value1&key2=value2
    // 쿼리 스트링으로 어떤 값이 들어올 지 모를 때, 매개변수의 항목이 일정하지 않을 경우
    @GetMapping("/request2")
    public String getRequestParam2(@RequestParam Map<String, String> param) {
        StringBuilder sb = new StringBuilder();

        param.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });
        return sb.toString();
    }

    // http://localhost:8080/api/v1/get-api/request3?name=value1&email=value2&organization=value3
    // Dto 객체를 활용한 Get 메서드 구현
    @GetMapping("/request3")
    public String getRequest3(MemberDto memberDto) {
        // return memberDto.getName() + " " + memberDto.getEmail() + " " + memberDto.getOrganization();
        return memberDto.toString();
    }
}
