package com.green.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.green.menus.dto.MenuDTO;
import com.green.menus.mapper.MenuMapper;

@Controller 
public class MenuController {
	
	@Autowired
	private  MenuMapper  menuMapper;
	
	// 메뉴 목록 조회  http://localhost:9090/Menus/List
	@RequestMapping("/Menus/List")   
	public   String   list( Model model  ) {
		// 조회한결과를 ArrayList 로 돌려준디
		List<MenuDTO> menuList = menuMapper.getMenuList(); // ArrayList 결과를받는다
		System.out.println(menuList);
		
		model.addAttribute("msg", "하하");
		model.addAttribute("menuList", menuList);
		
		return "menus/list";   // /WEB-INF/views/menus/list.jsp
	}
	
	// /Menus/WriteForm
	@RequestMapping("/Menus/WriteForm")
	public String writeForm(  ) {
		return "menus/write";  // write.jsp로 이동
	}
	
	// /Menus/Write?menu_id=MENU04&menu_name=GIT&menu_seq=4
	// http://localhost:8080/Menus/Write? menu_id=MENU04 & menu_name=GIT   & menu_seq=4
	//                                    menu_id=String   menu_name=Stirng  menu_seq=int
	// /Menus/Write
	@RequestMapping("/Menus/Write")
//	public String write( String menu_id, String menu_name, int menu_seq ) {
	public String write( MenuDTO menuDTO, Model model ) { 
		// MenuDTO.java 파일의 생성자 getter/setter Tostring 이용 이유 값들을 다 정의하고있는 파일이 MenuDTO이기 때문
		
		// 넘어온 값 (테이블에서 추가한 자료)
		System.out.println( "menu_id="   + menuDTO.getMenu_id() );
		System.out.println( "menu_name=" + menuDTO.getMenu_name() );
		System.out.println( "menu_seq="  + menuDTO.getMenu_seq() );
		
		// DB에 저장
		menuMapper.insertMenu( menuDTO ); // MenuDTO 의 menuDTO 라고 부여한 이름의 값을 넣는다
		        // .insertMenu 는 MenuMapper.java에 연결됨
		
		// 다시 조회 -> menuList 
		List<MenuDTO> menuList = menuMapper.getMenuList();
		
		model.addAttribute("menuList", menuList);
		
		return "menus/list";
	}
	
}













