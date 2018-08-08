package com.airline.controllers;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.airline.models.Pilot;
import com.airline.models.PilotRank;
import com.airline.service.PilotService;

/**
 * Servlet implementation class CreatePilotAndAddToFlight
 */
@WebServlet("/CreatePilotAndAddToFlight")
public class CreatePilotAndAddToFlight extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//inject the Enterprise Java Bean PilotService
	@EJB
	PilotService ps;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreatePilotAndAddToFlight() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		//input parameters from user
		String fName = request.getParameter("first name");
		String lName = request.getParameter("last name");
		Integer licence = Integer.parseInt(request.getParameter("licence"));
		
		String rank = request.getParameter("pilot_rank");
		
		String flightId = request.getParameter("fid");
		//create new pilot with the input parameters
		Pilot p = new Pilot();
		
		p.setFirstName(fName);
		p.setLastName(lName);
		p.setPilotLicence(licence);
		p.setPilotRank(PilotRank.valueOf(rank));
		
		//call the EJB object and add the pilot object and flight object
		ps.addNewPilotToFlight(p, flightId);
		
		response.sendRedirect("flights");


		


	}

}
