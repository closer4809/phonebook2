package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.PhoneDao;
import com.javaex.util.WebUtil;
import com.javaex.vo.PersonVo;



@WebServlet("/pbc")
public class PhoneController extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("컨트롤러");
		
		//파라미터 actio값을 읽어온다
		String action = request.getParameter("action");
	
		System.out.println(action);
		
		if("list".equals(action)) {
		System.out.println("[글쓰기폼]");
		//리스트업무
		
		//리스트 
		PhoneDao phoneDao = new PhoneDao();
		List<PersonVo> personList = phoneDao.getPersonList();

		System.out.println("controller===============");
		System.out.println(personList);
		
		/*
		//
		request.setAttribute("age", 45);
		request.setAttribute("name", "황일영");
		 */
		
		
		
		//데이터 넣기 -request 어트리뷰트에 데이터를 넣어준다.
		request.setAttribute("pList", personList);
				
		//html작업 ----> jsp에게 시킨다. --> forword 포워드
		RequestDispatcher rd =request.getRequestDispatcher("/WEB-INF/list.jsp");
		rd.forward(request, response);
	
	}else if("wform".equals(action)) {
		System.out.println("[글쓰기폼]");
		
		//writeForm.jsp 포워드 --> 데이터 x
//		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
//		rd.forward(request, response);
	
		WebUtil.forward(request, response, "/WEB-INF/writeForm.jsp");
	
	}else if("insert".equals(action)) {
		System.out.println("[저장]");
	
		//dao --> 저장
		//파라미터를 꺼낸다 name, hp, company
		String name = request.getParameter("name");
		String hp = request.getParameter("hp");
		String company = request.getParameter("company");
		
		//vo 로 묶어준다
		PersonVo personVo = new PersonVo(name, hp, company);
		System.out.println(personVo);
		
		
		//dao personInsert(vo)
		PhoneDao phoneDao = new PhoneDao();
		int count = phoneDao.personInsert(personVo);
		
		//리다이렉트
		response.sendRedirect("/phonebook2/pbc?action=list");
	
	
	}else if("delete".equals(action)) {
		System.out.println("[삭제]");
		
		
		

		PhoneDao phoneDao = new PhoneDao();	
		//파라미터 꺼내기
		int personId = Integer.parseInt( request.getParameter("id") );
		//System.out.println(personId);
		
		
	    //삭제하기 --> phoneDao.delete(3);
		int count = phoneDao.personDelete(personId);
		//System.out.println(count);
		
		
		//리디렉트(리스트요청);
		response.sendRedirect("/phonebook2/pbc?action=list");
	
	}else if("uform".equals(action)) {
		System.out.println("[수정폼]");
		
		//writeForm.jsp 포워드 --> 데이터 x
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/updateForm.jsp");
		rd.forward(request, response);
	
	}else if("update".equals(action)) {
		System.out.println("[수정]");
		
		PhoneDao phoneDao = new PhoneDao();
		//파라미터값 추출(4개)
		int personId = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String hp = request.getParameter("hp");
		String company = request.getParameter("company");
		
		//파라미터를 personVo만들기
		PersonVo personVo = new PersonVo(personId, name, hp, company);
		System.out.println(personVo);
		
		//doa 에 update()를 이용해서 수정
		int count = phoneDao.personUpdate(personVo);
		
		//리스트 리다이렉트
		response.sendRedirect("/phonebook2/pbc?action=list");
	}
		
		
		
	
	
	
	
	
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}