package com.carrot.project.board.domain;

import java.sql.Date;

public class Board {
	int board_id;
	String title;
	String file_name;
	String save_path;
	Date reg_date;
	
	public Board() {
		super();
	}
	public Board(int board_id, String title, String file_name, String save_path, Date reg_date) {
		super();
		this.board_id = board_id;
		this.title = title;
		this.file_name = file_name;
		this.save_path = save_path;
		this.reg_date = reg_date;
	}
	public int getBoard_id() {
		return board_id;
	}
	public void setBoard_id(int board_id) {
		this.board_id = board_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getFile_name() {
		return file_name;
	}
	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}
	public String getSave_path() {
		return save_path;
	}
	public void setSave_path(String save_path) {
		this.save_path = save_path;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
}
