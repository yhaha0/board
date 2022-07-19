package com.carrot.project.board.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.carrot.project.board.domain.Board;
import com.carrot.project.board.service.BoardService;


@Controller
public class BoardController {
	@Autowired private BoardService bs;
	
	@RequestMapping("/")
	public String home() {
		return "home";
	}
	
	@RequestMapping("/upload")
	public String upload(String title, MultipartFile mfile, HttpSession session) {
		String save_path = "/images";
		String upload_path = (String)session.getServletContext().getRealPath("/resources"+save_path);
		System.out.println(upload_path);
		
		String file_name = UUID.randomUUID().toString() +"_"+mfile.getOriginalFilename();
		
		bs.insert(new Board(0, title, file_name, save_path, null));

		try {
			mfile.transferTo(new File(upload_path, file_name));
		} catch(IOException ie) {
			ie.printStackTrace();
		} 

		return "redirect:/list";
	}	
	
	@RequestMapping("/list")
	public String list(Model model) {
		model.addAttribute("boardList", bs.getList());
		return "boardList";
	}
	
	@RequestMapping("/selectOne")
	@ResponseBody
	public void selectOne(HttpServletResponse rs, int board_id) {
		Board vo = bs.selectOne(board_id);
		
		/*
		StringBuffer sb = new StringBuffer();
		sb.append("<?xml version='1.0' encoding='UTF-8'?>");
		sb.append("<result>");
		sb.append("<board_id>"+vo.getBoard_id()+"</board_id>");
		sb.append("<title>"+vo.getTitle()+"</title>");
		sb.append("<file_name>"+vo.getFile_name()+"</file_name>");
		sb.append("<save_path>"+vo.getSave_path()+"</save_path>");
		sb.append("<reg_date>"+vo.getReg_date()+"</reg_date>");
		sb.append("</result>");
		String result = sb.toString();
		*/
		 
		JSONObject jso = new JSONObject();
		rs.setContentType("text/html; charset=utf-8");
		jso.put("board_id", vo.getBoard_id());
		jso.put("title", vo.getTitle());
		jso.put("file_name", vo.getFile_name());
		jso.put("save_path", vo.getSave_path());
		jso.put("reg_date", vo.getReg_date());
		String result = jso.toString();
		
		try {
			PrintWriter pw = rs.getWriter();
			pw.print(jso);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/delete")
	public String delete(int board_id, HttpSession session) {
		Board vo = bs.selectOne(board_id);
		String upload_path = (String)session.getServletContext().getRealPath("/resources");
		bs.delete(board_id);
		File file = new File(upload_path+vo.getSave_path()+"\\"+vo.getFile_name());
		if(file.exists()) {
			file.delete();
		}
		return "redirect:/list";
	}
	
}
