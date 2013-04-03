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

import ua.kpi.carrentals.controller.PagePath;
import ua.kpi.carrentals.entitiesnew.Client;
import ua.kpi.carrentals.entitiesnew.User;

/**
 * ProfileCommand class is the ICommand interface
 * implementation. The execution of this command show all information about user from database.
 * This command realizes redirection to the profile page.
 *
 * @author Tkachuk
 * @see ICommand
 */
public class ProfileCommand implements ICommand{
	private static Logger logger=Logger.getLogger(ProfileCommand.class);
	
	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CarrentalsPU");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		User user=em.find(User.class,((Integer)request.getSession().getAttribute("ID")));
		Client client=em.find(Client.class, ((Integer)request.getSession().getAttribute("ID")));
		tx.commit();
		em.close();
		emf.close();
		
		request.setAttribute("user", user);
		request.setAttribute("client", client);
		
		logger.info("Redirection to the profile page");
		
		String page=PagePath.getInstance().getPage(PagePath.PROFILE_PAGE_PATH);
		return page;
	}

}
