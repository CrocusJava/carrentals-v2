package ua.kpi.carrentals.controller.commands;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import ua.kpi.carrentals.configuration.LocaleConfig;
import ua.kpi.carrentals.controller.PagePath;
import ua.kpi.carrentals.entitiesnew.Orderlist;

/**
 * UpdateCommand class is the ICommand interface implementation. The execution
 * of this command update all information about order. This command realizes
 * redirection to the index page.
 * 
 * @author Tkachuk
 * @see ICommand
 */
public class UpdateOrderCommand implements ICommand {
	private static Logger logger = Logger.getLogger(UpdateOrderCommand.class);

	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String page;

		String idorder = request.getParameter("idorder");
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CarrentalsPU");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Orderlist order = em.find(Orderlist.class, Integer.valueOf(idorder));
		if (!order.equals(null)) {
			order.setStateOrder(Boolean.parseBoolean(request.getParameter("stateorder"))?(byte)1:(byte)0);
			order.setOrderExecuted(Boolean.parseBoolean(request.getParameter("orderexecuted"))?(byte)1:(byte)0);
			order.setDescription(request.getParameter("description"));
			order.setDescriptionDamage(request.getParameter("descriptiondamage"));
		}
		
		tx.commit();
		em.close();
		emf.close();
		LocaleConfig langConfig = LocaleConfig.getInstance();
		String message = langConfig.getText(LocaleConfig.MESSAGE_ORDER_CHANGE);
		request.setAttribute("message", message);

		logger.info("Order was change by administrator");
		logger.info("Redirection to the message page");

		page = PagePath.getInstance().getPage(PagePath.MESSAGE_PAGE_PATH);
		return page;
	}

}
