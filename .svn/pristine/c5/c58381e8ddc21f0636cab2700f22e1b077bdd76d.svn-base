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
import ua.kpi.carrentals.entitiesnew.Place;

/**
 * SearchCarsCommand class is the ICommand interface implementation. The
 * execution of this command show information about cars that matches the input
 * parameters. This command realizes redirection to the cars list page.
 * 
 * @author Tkachuk
 * @see ICommand
 */
public class SearchCarsCommand implements ICommand {
	private static Logger logger = Logger.getLogger(SearchCarsCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String idplace = request.getParameter("idplaceget");
		String price = request.getParameter("pricenotmore");
		Integer notMorePrice = Integer.parseInt(price);
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CarrentalsPU");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Place placeGet = (Place) em.find(Place.class, Integer.valueOf(idplace));
		List<Car> carList = em.createNamedQuery("Car.findByPricePlace", Car.class)
				.setParameter("place", placeGet)
				.setParameter("price", notMorePrice).getResultList();
		tx.commit();
		em.close();
		emf.close();
		
		Car[] cars = new Car[carList.size()];
		cars = carList.toArray(cars);
		request.setAttribute("cars", cars);

		logger.info("Redirection to the cars list from search page");

		String page = PagePath.getInstance().getPage(PagePath.CARS_PAGE_PATH);
		return page;
	}

}
