package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.form.InsertAdministratorForm;
import com.example.service.AdministratorService;

/**
 * @author ka711 管理者情報を操作するコントローラ
 *
 */
@Controller
@RequestMapping("")
public class AdministratorController {
	@Autowired
	private AdministratorService administratorService;

	/**
	 * administrator/insert.htmlにフォワードする
	 * 
	 * @param form  管理者登録するための情報
	 * @param model リクエストスコープ
	 * @return フォワード先
	 */
	@GetMapping("/toInsert")
	public String toInsert(InsertAdministratorForm form, Model model) {
		model.addAttribute("form", form);
		return "administrator/insert";

	}

}
