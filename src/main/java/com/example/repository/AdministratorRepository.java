package com.example.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Administrator;

/**
 * administratorsテーブルを操作するリポジトリ.
 * 
 * @author ka711 
 */
@Repository
public class AdministratorRepository {
	/**
	 * administratorオブジェクトを生成するローマッパー.
	 */
	private static final RowMapper<Administrator> ADMINISTRATOR_ROW_MAPPER = (rs, i) -> {
		Administrator administrator = new Administrator();
		administrator.setId(rs.getInt("id"));
		administrator.setName(rs.getString("name"));
		administrator.setMailAddress(rs.getString("mail_address"));
		administrator.setPassword(rs.getString("password"));
		return administrator;
	};

	@Autowired
	private NamedParameterJdbcTemplate template;

	/**
	 * 管理者情報を登録します.
	 * 
	 * @param administrator 管理者情報
	 */
	public void insert(Administrator administrator) {
		String sql = "INSERT INTO administrators (name,mail_address,password) VALUES (:name,:mailAddress,:password);";
		SqlParameterSource param = new BeanPropertySqlParameterSource(administrator);
		template.update(sql, param);
	}

	/**
	 * メールアドレスとパスワードが一致する従業員情報を検索します.
	 * 
	 * @param mailAddres 照合したいメールアドレス
	 * @param password   照合したいパスワード
	 * @return 管理者情報(検索されなかった場合はnullを返します)
	 */
	public Administrator findByMaidAddressAndPassword(String mailAddres, String password) {
		String sql = "SELECT id,name,mail_address,password FROM administrators WHERE mail_address = :mailAddress AND password = :password;";
		SqlParameterSource param = new MapSqlParameterSource().addValue("mailAddres", mailAddres).addValue("password",
				password);
		List<Administrator> administratorList = template.query(sql, param, ADMINISTRATOR_ROW_MAPPER);
		if (administratorList.size() == 0) {
			return null;
		}
		return administratorList.get(0);

	}

}
