//package com.example.gateway.api;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.server.ServerHttpRequest;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//import reactor.core.publisher.Mono;
//
//import java.util.Map;
//
//@RestController
//public class GatewayController {
//
//    private final ObjectMapper objectMapper = new ObjectMapper();
//
//    @PostMapping("/modify-body")
//    public Mono<ResponseEntity<String>> modifyBody(ServerHttpRequest request, @RequestBody Map<String, Object> body) {
//        // 기존 바디에서 userId 추가
//        String userId = "some-user-id"; // 사용자 ID를 가져오는 로직 추가
//        body.put("userId", userId);
//
//        // 새로운 바디로 변환
//        String newBody;
//        try {
//            newBody = objectMapper.writeValueAsString(body);
//        } catch (Exception e) {
//            return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Body parsing error"));
//        }
//
//        // 원래는 여기에 downstream service로 새로운 body를 보낼 로직이 들어가야 함
//        // 이 예시에서는 단순히 body를 반환합니다.
//        return Mono.just(ResponseEntity.ok(newBody));
//    }
//}
