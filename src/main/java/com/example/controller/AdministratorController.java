package com.example.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Administrator;
import com.example.form.InsertAdministratorForm;
import com.example.form.LoginForm;
import com.example.service.AdministratorService;

import jakarta.servlet.http.HttpSession;

/**
 * 管理者情報を操作するコントローラ.
 * 
 * @author ka711
 *
 */
@Controller
@RequestMapping("/")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;
	
	@Autowired
	private HttpSession session;

	/**
	 * administrator/insert.htmlにフォワードする.
	 * 
	 * @param form  リクエストスコープ
	 * @return フォワード先
	 */
	@GetMapping("/toInsert")
	public String toInsert(InsertAdministratorForm form) {
		return "administrator/insert";

	}

	/**
	 * 管理者情報を登録する.
	 * 
	 * @param form 管理者登録するための情報
	 * @return リダイレクト先
	 */
	@PostMapping("/insert")
	public String insert(InsertAdministratorForm form) {
		Administrator administrator = new Administrator();
		BeanUtils.copyProperties(form, administrator);
		administratorService.insert(administrator);
		return "redirect:/";
	}
	
	/**
	 *  administrator/loginにフォワードする.
	 * 
	 * @param form リクエストスコープ
	 * @return フォワード先
	 */
	@GetMapping("/")
	public String toLogin(LoginForm form) {
		return "administrator/login";
	}
	
	@PostMapping("/login")
	public String login(LoginForm form,Model model) {
		Administrator administrator = administratorService.login(form.getMailAddress(), form.getPassword());
		if(administrator == null) {
			String alertMessage = "メールアドレスまたはパスワードが間違っています";
			model.addAttribute("alertMessage", alertMessage);
			return "administrator/login";
		}
	
		session.setAttribute("administrator", administrator.getName());
		return "redirect:/employee/showList";
		
	}

}
