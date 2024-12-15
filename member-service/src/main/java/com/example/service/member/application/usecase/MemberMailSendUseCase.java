package com.example.service.member.application.usecase;

import com.example.service.member.domain.repository.EmailRepository;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class MemberMailSendUseCase {
    private final JavaMailSender javaMailSender;
    private final EmailRepository emailRepository;
    private static final int CODE_LENGTH = 6;
    // Random 객체를 필드로 선언
    //private final Random random = new Random();
    @Async
    public Future<Void> sendEmail(String to) {
        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper;

        try {
            helper = new MimeMessageHelper(message, true);
            helper.setTo(to);
            helper.setSubject("회원 가입 인증번호");

            String code = createVerificationCode();
            // HTML 이메일 본문 설정
            String htmlContent = createHtmlContent(code);
            helper.setText(htmlContent, true); // true를 주면 HTML 형식으로 전송

            // 메일을 발송합니다.
            javaMailSender.send(message);
            emailRepository.saveEmailCode(to , code);
            //return code;
            return CompletableFuture.completedFuture(null);
        } catch (MessagingException e) {
            throw new MailSendException("이메일 전송 중 오류가 발생했습니다.", e);
        }
    }

    private String createHtmlContent(String code) {
        return "<!DOCTYPE html>\n" +
                "<html lang=\"ko\">\n" +
                "<head>\n" +
                "    <meta charset=\"UTF-8\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <title>인증번호</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "    <h1>안녕하세요!</h1>\n" +
                "    <p>회원 가입을 위한 인증번호는 다음과 같습니다:</p>\n" +
                "    <h2>" + code + "</h2>\n" +
                "    <p>위의 인증번호를 입력하여 인증을 완료해 주세요.</p>\n" +
                "    <p>감사합니다!</p>\n" +
                "</body>\n" +
                "</html>";
    }
    private String createVerificationCode() {
        StringBuilder verificationCode = new StringBuilder(CODE_LENGTH);

        // 길이에 맞게 랜덤한 숫자를 생성
        for (int i = 0; i < CODE_LENGTH; i++) {
            verificationCode.append(ThreadLocalRandom.current().nextInt(10)); // 0~9의 숫자
        }

        return verificationCode.toString();
    }
}
