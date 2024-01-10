package com.springboot.api.controller;

import com.springboot.api.dto.MemberDto;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/post-api")
public class PostController {

    // Request Mapping으로 구현하기
    @RequestMapping(value="/domain", method = RequestMethod.POST)
    public String postExample() {
        return "Hello Post API";
    }

    // http://localhost:8080/api/v1/post-api/member
    // RequestBody와 Map을 활용한 POST API 구현
    @PostMapping(value = "/member")
    public String postMember(@RequestBody Map<String,Object> postData) {
        StringBuilder sb = new StringBuilder();

        postData.entrySet().forEach(map -> {
            sb.append(map.getKey() + " : " + map.getValue() + "\n");
        });

        return sb.toString();
    }

    // http://localhost:8080/api/v1/post-api/member2
    // DTO 객체를 활용한 Post API 구현
    @PostMapping(value = "/member2")
    public String postMemberDto(@RequestBody MemberDto memberDto) {
        return memberDto.toString();
    }
}
