package com.yspjt.dbafe.Controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.yspjt.dbafe.Model.SigModel;
import com.yspjt.dbafe.Service.PasswordEncryptor;
import com.yspjt.dbafe.Service.SiginUpService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@Slf4j
@RequiredArgsConstructor
public class SiginUpController {
	private final SiginUpService siginUpService;
	@Autowired
	private HttpSession session;

	@PostMapping("/login")
	@ResponseBody
	public Map<String, Object> login(@RequestBody SigModel sigModal, HttpSession session) {
		System.out.println("로그인 받긴 함시작 잘됨");

		boolean loginResult = siginUpService.login(sigModal);
		System.out.println("userdto   :  " + sigModal);
		String msg;
		if (loginResult) {
			int user_code = siginUpService.callUserCode(sigModal);
			session.setAttribute("user_code", user_code);
			session.setAttribute("users_id", sigModal.getUser_id());

			System.out.println("로그인 된 아이디 정보" + sigModal.getUser_id());
			System.out.println("성공");
			System.out.println("로그인 한 아이디 랑 코드 값 저장 유무 " + session.getAttribute("user_code")
					+ session.getAttribute("users_id"));
			msg = "성공";
			String userId = (String) session.getAttribute("users_id");
			System.out.println("세션에 잘 등록이 됬는지      :" + userId);

		} else {
			System.out.println("실패");
			msg = "이메일과 비밀번호를 확인해주세요";
		}

		Map<String, Object> response = new HashMap<>();
		response.put("msg", msg);
		response.put("success", loginResult);
		return response;
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate(); // 세션 무효화
		return "redirect:/";
	}

	@PostMapping("/callUserCode")
	@ResponseBody
	public Map<String, Object> callUserCode(SigModel sigModal) {
		String userId = (String) session.getAttribute("users_id");
		sigModal.setUser_id(userId);
		int userCode = siginUpService.callUserCode(sigModal);
		Map<String, Object> response = new HashMap<>();
		response.put("userCode", userCode);
		return response;
	}

	@PostMapping("/joinUp")
	public boolean joinUp(@RequestBody SigModel sigModel) {
		boolean joinUp = siginUpService.joinUp(sigModel);
		System.out.println("회원가입 데이터 확인" + sigModel);
		System.out.println("회원가입 데이터 확인" + joinUp);
		return joinUp;
	}

	@PostMapping("/checkDuplicateId")
	public boolean checkDuplicateId(@RequestBody SigModel sigModel) {
		boolean checkDuplicateId = siginUpService.checkDuplicateId(sigModel);
		;
		System.out.println("아이디 중복확인 확인 한번더 확인 확인" + sigModel);
		System.out.println("checkDuplicateId 데이터 확인" + checkDuplicateId);
		return checkDuplicateId;
	}

	@GetMapping("/callSession")
	public Map<String, Object> callSession() {
		String userId = (String) session.getAttribute("users_id");
		Integer userCode = (Integer) session.getAttribute("user_code");

		Map<String, Object> response = new HashMap<>();
		response.put("user_id", userId);
		response.put("user_code", userCode);

		return response;
	}
}
