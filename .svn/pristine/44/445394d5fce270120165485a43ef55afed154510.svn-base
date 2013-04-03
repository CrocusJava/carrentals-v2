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
import ua.kpi.carrentals.entitiesnew.Orderlist;
/**
 * OrderlistCommand class is the ICommand interface implementation. This command
 * realizes redirection to the orders list page. The execution of this command
 * reads all orders from database sorted by id and sets a result page
 * as orders list page.
 * 
 * @author Tkachuk
 * @see ICommand
 */
public class OrderlistCommand implements ICommand{
	private static Logger logger=Logger.getLogger(OrderlistCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CarrentalsPU");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		List<Orderlist> listOrder = em.createNamedQuery("Orderlist.sortedById", Orderlist.class).getResultList();
		tx.commit();
		em.close();
		emf.close();
		
		request.setAttribute("orders", listOrder);
		
		logger.info("Redirection to the order list page for Administrator");
		
		String page=PagePath.getInstance().getPage(PagePath.ORDERLIST_PAGE_PATH);
		return page;
	}

}
