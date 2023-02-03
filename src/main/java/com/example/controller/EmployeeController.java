package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.Employee;
import com.example.form.UpdateEmployeeForm;
import com.example.service.EmployeeService;

/**
 * 従業員情報を操作するコントローラ.
 * 
 * @author kazuki.naito
 *
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService employeeService;

	/**
	 * 従業員情報を取得し、画面に表示させる.
	 * 
	 * @param model リクエストパラメータに保存するためのModelオブジェクト
	 * @return フォワード先（従業員一覧画面）
	 */
	@GetMapping("/showList")
	public String showList(Model model) {
		List<Employee> employeeList = employeeService.showList();
		model.addAttribute("employeeList", employeeList);
		return "employee/list";
	}

	/**
	 * 従業員情報を検索する.
	 * 
	 * @param id    検索するためのID
	 * @param model リクエストパラメータに保存するためのModelオブジェクト
	 * @param form  従業員情報を受け取るためのフォーム
	 * @return フォワード先（従業員詳細画面）
	 */
	@GetMapping("/showDetail")
	public String showDetail(String id, Model model, UpdateEmployeeForm form) {
		Employee employee = employeeService.showDetail(form.getIntId());
		model.addAttribute("employee", employee);
		return "employee/detail";

	}

}
