package com.airline.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.airline.models.Flight;
import com.airline.models.Pilot;

/**
 * Session Bean implementation class PilotService
 */
@Stateless
@LocalBean
public class PilotService {
	
	@PersistenceContext(unitName = "airline")
	EntityManager em;

    /**
     * Default constructor. 
     */
    public PilotService() {
        // TODO Auto-generated constructor stub
    }
    
    public void addPilot(Pilot p) {
    	em.persist(p);
    }
    
	public void addNewPilotToFlight(Pilot p, String flightId) {
		
		//persist new pilot to the database
			em.persist(p);
	    	//take the flight number from the user and search the database for it then add teh pilot to it
	    	TypedQuery<Flight> fQuery = em.createNamedQuery("Flight.findById", Flight.class);
	    	
	    	fQuery.setParameter("id", Integer.parseInt(flightId));
	    	
	    	Flight f = fQuery.getSingleResult();
	    	
	    	List<Pilot> pList = f.getPilots();
	    	
	    	pList.add(p);
	    	
	    	f.setPilots(pList);
	    	
	    	p.setFlightForPilot(f);
	    	
	    	
	    }

}
