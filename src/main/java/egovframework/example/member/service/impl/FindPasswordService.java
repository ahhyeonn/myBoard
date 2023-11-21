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

import egovframework.example.member.dao.MemberDao;
import egovframework.example.member.dao.impl.MemberDaoImpl;
import egovframework.example.member.vo.MemberVo;


@Service
public class FindPasswordService {
	
	@Autowired
	private MemberDao memberDao;

	// 임시 비밀번호 생성
	public String makeRandomNumber() {
		char[] charSet = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F',
				'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };

		String tempNum = "";

		// 문자 배열 길이의 값을 랜덤으로 10개를 뽑아 구문을 작성함
		int idx = 0;
		for (int i = 0; i < 10; i++) {
			idx = (int) (charSet.length * Math.random());
			tempNum += charSet[idx];
		}
//		System.out.println("##########################################");
//		System.out.println("================임시 비밀번호 : " + tempNum);

		return tempNum;

	}


	// 이메일 양식
	public String findPw(String email) {

		// 선언1(랜덤번호 발생)
		String tempPassword = makeRandomNumber();
//		System.out.println("/////////////////////////////////////////");
//		System.out.println("========tempPassword: " + tempPassword);

		String setFrom = "ao22hi@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력
		String toMail = email;
		String title = "임시 비밀번호 발급 이메일입니다."; 	// 이메일 제목
		String content = "비밀번호 까먹지 마세요!" + 		// html 형식으로 작성
				"<br><br>" + "임시 비밀번호는 " + tempPassword + "입니다."; // 이메일 내용 삽입
		
		//임시 비밀번호 DB update
		MemberVo vo = new MemberVo();				//MemberVo를 불러와서
		vo.setMemMail(email);						//memMail
		vo.setMemPw(tempPassword);					//memPw에 tempPassword를 넣어줌
		memberDao.savePw(vo);						//memberDao에 가서 savePw 쿼리를 불러옴
		
		mailSend(setFrom, toMail, title, content);	//mailSend 메소드로 gogo
		
		return tempPassword;
	}
	
	/*
	// 임시 비밀번호로 변경
	public void updatePassword(String str, String userEmail) {
		System.out.println("///////////////////여기까지 오나요(임시 비밀번호 변경)/////////////////////");
		
		String memberPassword = str;
        Long memberId = mr.findByMemberEmail(userEmail).getId();
        mmr.updatePassword(memberId,memberPassword);
		
		
		MemberDaoImpl memberDao = new MemberDaoImpl();
		
		memberDao.savePw();
		if (member != null) {
			
			// 사용자 정보가 있으면 비밀번호 업데이트
			member.setPassword(newPassword);
			memberDao.updateMemberPassword(member);
		} else {
			
			// 사용자 정보가 없을 경우 예외 처리 또는 적절한 로직 추가
			System.out.println("해당 이메일을 가진 사용자를 찾을 수 없습니다.");
		}
	}	
	*/

	// 이메일 전송 메소드
	public void mailSend(String setFrom, String toMail, String title, String content) {
		
		/*
		//임시 비밀번호로 update
		String tempPw2 = makeRandomNumber();
		MemberDaoImpl memberDao = new MemberDaoImpl();
		memberDao.savePw(vo);
		System.out.println("/////////////////////////////////////");
		System.out.println("memberVo: " + vo);
		System.out.println("////////////임시 비밀번호로 변경///////////");
		*/

		// dispatcher-servlet에 있는 properties값 가져오기
		Properties props = System.getProperties();

		// 설정된 name값이랑 동일하게 해야함(props.put("name","value");)
		props.put("host", "smtp.gmail.com");
		props.put("port", "587");
		props.put("username", "ao22hi@gmail.com");
		props.put("password", "nibaxscnnubnilif");

		props.put("mail.transport.protocol", "smtp");
		props.put("mail.smtp.auth", "true"); 
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.debug", "true");
		props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
		props.put("mail.smtp.ssl.protocols", "TLSv1.2");

		// properties 값을 session에 담아옴
		Session session = Session.getDefaultInstance(props);

		// 선언2(JavaMailSenderImpl)
		JavaMailSenderImpl mailSender = new JavaMailSenderImpl();

		// javaMailSender
		mailSender.setHost("smtp.gmail.com");
		mailSender.setPort(587);
		mailSender.setUsername("ao22hi@gmail.com");
		mailSender.setPassword("nibaxscnnubnilif");
		mailSender.setJavaMailProperties(props);

		MimeMessage message = mailSender.createMimeMessage();

		try {

			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(setFrom);
			helper.setTo(toMail);
			helper.setSubject(title);
			helper.setText(content, true);
			mailSender.send(message);

		} catch (MessagingException e) {
			e.printStackTrace();

		}
	}
	
}







