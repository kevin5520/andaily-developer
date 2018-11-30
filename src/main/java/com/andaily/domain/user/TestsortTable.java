package com.andaily.domain.user;

//(WANG Hanlin) create table format for variable count and nick_name create get set function
public class TestsortTable {

	String nick_name;
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	
	int count;
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
