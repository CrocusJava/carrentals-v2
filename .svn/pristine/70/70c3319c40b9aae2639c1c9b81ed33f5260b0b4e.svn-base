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
import ua.kpi.carrentals.entitiesnew.Car;
import ua.kpi.carrentals.entitiesnew.Client;
import ua.kpi.carrentals.entitiesnew.Place;
import ua.kpi.carrentals.entitiesnew.User;
/**
 * OrderCommand class is the ICommand interface
 * implementation. The execution of this command set attribute car, place (get) and places (return)
 * for order form. This command realizes redirection to the order page.
 *
 * @author Tkachuk
 * @see ICommand
 */
public class OrderCommand implements ICommand{
	private static Logger logger=Logger.getLogger(OrderCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String idCar=request.getParameter("idcar");
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CarrentalsPU");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Car car=em.find(Car.class, Integer.valueOf(idCar));
		List<Place> listPlaces=em.createNamedQuery("Place.sortedByCity", Place.class).getResultList();
		tx.commit();
		Place placeGet=car.getPlace();
		Place [] places=new Place[listPlaces.size()];
		places=listPlaces.toArray(places);
		
		request.setAttribute("car", car);
		request.setAttribute("place", placeGet);
		request.setAttribute("places", places);

		if ((request.getSession().getAttribute("ID"))==null){
			User user=new User();
			request.getSession().setAttribute("role", user.getRole());
	        request.getSession().setAttribute("ID", user.getId());
		}
		tx.begin();
		Client client=em.find(Client.class, (Integer)request.getSession().getAttribute("ID"));
		tx.commit();
		em.close();
		emf.close();
		
		request.setAttribute("client", client);
		
		logger.info("Redirection to the order page");
		
		String page=PagePath.getInstance().getPage(PagePath.ORDER_PAGE_PATH);
		return page;
	}

}
