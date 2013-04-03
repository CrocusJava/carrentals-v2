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
/**
 * CarsCommand class is the ICommand interface implementation. This command
 * realizes redirection to the cars list page. The execution of this command
 * reads all cars from database sorted by price per day and sets a result page
 * as cars list page.
 * 
 * @author Tkachuk
 * @see ICommand
 */
public class CarsCommand implements ICommand {
	private static Logger logger=Logger.getLogger(CarsCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CarrentalsPU");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
			List <Car> carList=em.createNamedQuery("Car.sortedByPrice", Car.class).getResultList();
		tx.commit();
		em.close();
		emf.close();
		
		Car [] cars=new Car[carList.size()];
		cars=carList.toArray(cars);
		request.setAttribute("cars", cars);
		
		logger.info("Redirection to the list cars page");
		
		String page = PagePath.getInstance().getPage(PagePath.CARS_PAGE_PATH);
		return page;
	}

}
