package com.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.DB.DBConnect;
import com.Dao.JobDAO;
import com.entity.Jobs;

@WebServlet("/update")
public class UpdateJoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			int id=Integer.parseInt(req.getParameter("id"));
			String title=req.getParameter("title");
			String location=req.getParameter("location");
			String category=req.getParameter("category");
			String status =req.getParameter("status");
			String desc=req.getParameter("desc");
			
			Jobs j= new Jobs();
			j.setId(id);
			j.setTitle(title);
			j.setDescription(desc);
			j.setLocation(location);
			j.setStatus(status);
			j.setCategory(category);
			
			HttpSession session =req.getSession();
			
			JobDAO dao= new JobDAO(DBConnect.getConn());
			boolean f=dao.updateJob(j);
			
			if(f) {
				session.setAttribute("succMsg", "Job Update Successfully");
				resp.sendRedirect("viewjobs.jsp");
			}else {
				session.setAttribute("succMsg", "Something Wrong on Server");
				resp.sendRedirect("viewjobs.jsp");
				
			}
	}catch(Exception e) {
		e.printStackTrace();
	}
	}
	
}
