package ua.kpi.carrentals.controller.commands;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kpi.carrentals.controller.PagePath;
import ua.kpi.carrentals.entitiesnew.Place;
/**
 * SearchCommand class is the ICommand interface
 * implementation. This command realizes redirection to the search page
 * with fields where can select option place get and return.
 *
 * @author Tkachuk
 * @see ICommand
 */
public class SearchCommand implements ICommand{
	private static Logger logger=Logger.getLogger(SearchCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CarrentalsPU");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();	
		List<Place> listPlaces=em.createNamedQuery("Place.sortedByCity", Place.class).getResultList();
		tx.commit();
		em.close();
		emf.close();
		
		Place [] places=new Place[listPlaces.size()];
		places=listPlaces.toArray(places);
		request.setAttribute("places", places);

		logger.info("Redirection to the search page");
		
		String page=PagePath.getInstance().getPage(PagePath.SEARCH_PAGE_PATH);
		return page;
	}

}
