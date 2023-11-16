package egovframework.example.member.service.impl;

import java.util.Properties;
import java.util.Random;

import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service("findPassword")
public class FindPasswordService {
	 /*
	//임시 비밀번호 만들기
	public String makeTempPassword() {
		
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
                'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

        String tempNum = "";
		
        //문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
        int idx = 0;
        for (int i = 0; i < 10; i++) {
            idx = (int) (charSet.length * Math.random());
            tempNum += charSet[idx];
        }
        
        System.out.println("================임시 비밀번호 : " +  tempNum);
        
        return tempNum;
		
	}
	*/
	
	// 난수 발생
	public int makeRandomNumber() {
		// 난수의 범위 111111 ~ 999999 (6자리 난수)
		Random r = new Random();
		int checkNum = r.nextInt(888888) + 111111;
		System.out.println("임시 비밀번호 : " + checkNum);

		return checkNum;
		
	}
	
	
	//임시 비밀번호로 변경
//	public void updatePassword(String str,)
	
	
	
	// 이메일 양식
	public String findPw(String email) {
		
		//선언1(랜덤번호 발생)
//		String tempPassword = makeTempPassword();
		int tempPassword = makeRandomNumber();
		
		String setFrom = "ao22hi@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력
		String toMail = email;
		String title = "임시 비밀번호 발급 이메일 입니다."; // 이메일 제목
		String content = "임시 비밀번호가 발급되었습니다." + // html 형식으로 작성 !
				"<br><br>" + "임시 비밀번호는 " + tempPassword + "입니다."; // 이메일 내용 삽입
		mailSend(setFrom, toMail, title, content);
		
		return Integer.toString(tempPassword);
	}
	

	// 이메일 전송 메소드
	public void mailSend(String setFrom, String toMail, String title, String content) {
		
		//dispatcher-servlet에 있는 properties값 가져오기
		Properties props = System.getProperties();
		
		//설정된 name값이랑 동일하게 해야함(props.put("name","value");)
		props.put("host", "smtp.gmail.com"); 
		props.put("port", "587"); 
		props.put("username", "ao22hi@gmail.com"); 
		props.put("password", "nibaxscnnubnilif"); 
//		props.put("javaMailProperties","javaMailProperties"); 
		
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        props.put("mail.smtp.ssl.protocols", "TLSv1.2");
        
        //properties 값을 session에 담아옴
        Session session = Session.getDefaultInstance(props);
		
        //선언2(JavaMailSenderImpl)
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
		
		//javaMailSender
		mailSender.setHost("smtp.gmail.com"); 
		mailSender.setPort(587); 
		mailSender.setUsername("ao22hi@gmail.com"); 
		mailSender.setPassword("nibaxscnnubnilif"); 
		mailSender.setJavaMailProperties(props);
		
		//MimeMessage - createMimeMessage
		MimeMessage message = mailSender.createMimeMessage();
		
		// true 매개값을 전달하면 multipart 형식의 메세지 전달이 가능.문자 인코딩 설정도 가능하다.
		try {
			
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			// true 전달 > html 형식으로 전송 , 작성하지 않으면 단순 텍스트로 전달.
			helper.setText(content, true);
			mailSender.send(message);
			
		} catch (MessagingException e) {
			e.printStackTrace();
			
		}
	}

}
