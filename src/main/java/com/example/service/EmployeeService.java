package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.Employee;
import com.example.repository.EmployeeRepository;

/**
 * 従業員情報を操作するサービス.
 * 
 * @author kazuki.naito
 *
 */
@Service
@Transactional
public class EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	/**
	 * 従業員情報を全権取得する.
	 * 
	 * @return 全従業員の情報
	 */
	public List<Employee> showList(){
		return employeeRepository.findAll();
	}
	
	/**
	 * 主キー検索し、従業員情報を取得する.
	 * 
	 * @param id 検索するための主キー
	 * @return 従業員情報
	 */
	public Employee showDetail(Integer id) {
		return employeeRepository.load(id);
	}

}
