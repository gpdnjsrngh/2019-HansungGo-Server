package kr.ac.hansung.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import kr.ac.hansung.model.Member;

@Repository
public class MemberDAO {
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	public void setDataSource(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public int getRowCount() {
		String sqlStatement = "select count(*) from members";
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
	}
	
	public int getRowCount(String id, String name, String phone) {
		String sqlStatement = String.format("select count(*) from members where id='%s' and name='%s' and phone='%s'", id, name, phone);
		return jdbcTemplate.queryForObject(sqlStatement, Integer.class);
	}
	
	//query and return a single object
	public Member getMember(String id, String phone) {
		String sqlStatement = "select * from members where id = ? and phone =?";
		//Offer�ϳ��� �����ϰ� ������ queryforObject
		return jdbcTemplate.queryForObject(sqlStatement, new Object[] {id, phone},
				new RowMapper<Member>() {

					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						
						Member member = new Member();
						
						member.setId(rs.getString("id"));
						member.setName(rs.getString("name"));
						member.setPhone(rs.getString("phone"));
						member.setCoinCount(rs.getString("coinCount"));
						member.setUpdateTime(rs.getString("updateTime"));
						
						return member;
					}
				}	
		);
	}
	
	//query and return a single object
	public List<Member> getMembers() {
		String sqlStatement = "select * from members order by updateTime asc";
		//Offer�ϳ��� �����ϰ� ������ queryforObject
		return jdbcTemplate.query(sqlStatement,
				new RowMapper<Member>() {

					@Override
					public Member mapRow(ResultSet rs, int rowNum) throws SQLException {
						// TODO Auto-generated method stub
						Member member = new Member();
						
						member.setId(rs.getString("id"));
						member.setName(rs.getString("name"));
						member.setPhone(rs.getString("phone"));
						member.setCoinCount(rs.getString("coinCount"));
						member.setUpdateTime(rs.getString("updateTime"));
						
						return member;
					}
				}	
		);
	}
	
	public boolean insert(Member member) {
		System.out.println(member.toString());
		
		String id = member.getId();
		String name = member.getName();
		String phone = member.getPhone();
		String coinCount = member.getCoinCount();
		String updateTime = member.getUpdateTime();
		
		String sqlStatement = "insert into members (id, name, phone, coinCount, updateTime) values (?,?,?,?,?)";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {id, name, phone, coinCount, updateTime})==1);
	}
	
	public boolean update(Member member) {
		String id = member.getId();
		String name = member.getName();
		String phone = member.getPhone();
		String coinCount = member.getCoinCount();
		String updateTime = member.getUpdateTime();
		
		String sqlStatement = "update members set coinCount=?, updateTime=? where id=? and name=? and phone=?";
		
		return (jdbcTemplate.update(sqlStatement, new Object[] {coinCount, updateTime, id, name, phone})==1);
	}
	/*
	public boolean delete(int id) {
		String sqlStatement = "delete from offers where id=?";
		return (jdbcTemplate.update(sqlStatement, new Object[] {id})==1);
	}*/
}
