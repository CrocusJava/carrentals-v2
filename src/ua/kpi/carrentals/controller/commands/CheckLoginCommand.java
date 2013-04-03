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
import ua.kpi.carrentals.entitiesnew.User;

/**
 * CheckLoginCommand class is the ICommand interface implementation. This
 * command realizes user validation. The execution of this command checks if the
 * entered by user password and login are valid. This command realizes
 * redirection to the index page or error page if parameters aren't correct.
 * 
 * @author Tkachuk
 * @see ICommand
 */
public class CheckLoginCommand implements ICommand {
	private static Logger logger = Logger.getLogger(CheckLoginCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		logger.info("Check user login and password");

		String page = null;

		PagePath pagePath = PagePath.getInstance();
		String login = request.getParameter("login");
		String password = request.getParameter("password");

		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("CarrentalsPU");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		User user = null;
		user = em.createNamedQuery("User.findByLogin", User.class)
				.setParameter("login", login).getSingleResult();
		tx.commit();
		em.close();
		emf.close();
		System.out.println(user);
		if (user.getPassword().equals(password)) {
			/**
			 * After received user, enters "role" and "ID" in the session
			 */
			request.getSession().setAttribute("role", user.getRole());
			request.getSession().setAttribute("ID", user.getId());

			if ((user.getRole()).equals("guest")) {
				LocaleConfig langConfig = LocaleConfig.getInstance();
				String message = langConfig
						.getText(LocaleConfig.MESSAGE_ERROR_LOGIN);
				request.setAttribute("message", message);
				logger.info("Wrong user login and password");
				page = pagePath.getPage(PagePath.MESSAGE_PAGE_PATH);
			} else {
				logger.info("Correct user login");
				page = pagePath.getPage(PagePath.INDEX_PAGE_PATH);
			}
		}
		return page;
	}
}
