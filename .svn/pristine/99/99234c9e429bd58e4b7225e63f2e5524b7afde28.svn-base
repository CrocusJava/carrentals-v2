package ua.kpi.carrentals.controller.commands;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
import ua.kpi.carrentals.entitiesnew.Client;
import ua.kpi.carrentals.entitiesnew.User;

/**
 * UpdateCommand class is the ICommand interface implementation. The execution
 * of this command update all information about user. If user is a client that
 * update client information, otherwise will be create new client. This command
 * realizes redirection to the index page.
 * 
 * @author Tkachuk
 * @see ICommand
 */
public class UpdateCommand implements ICommand {
	private static Logger logger = Logger.getLogger(UpdateCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		String login = request.getParameter("login");
		LocaleConfig langConfig = LocaleConfig.getInstance();
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CarrentalsPU");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		user = (User) em.createNamedQuery("User.findByLogin", User.class)
				.setParameter("login", login).getSingleResult();
		tx.commit();
		/**
		 * if new login user is exists in database - show error page, otherwise
		 * change information.
		 */
		if (((user.getRole()).equals("user") || (user.getRole())
				.equals("admin"))
				&& (!(user.getId()).equals((Integer) request.getSession()
						.getAttribute("ID")))) {
			String messageError = langConfig
					.getText(LocaleConfig.MESSAGE_ERROR_USER);

			logger.info("Error. User update profile. User with this login already exists");

			request.setAttribute("message", messageError);
		} else {
			user.setRole((String) request.getSession().getAttribute("role"));
			user.setLogin(request.getParameter("login"));
			user.setPassword(request.getParameter("password"));
			user.setEmail(request.getParameter("email"));
			user.setName(request.getParameter("name"));
			user.setSurname(request.getParameter("surname"));
			user.setMiddlename(request.getParameter("middlename"));
			if (!(request.getSession().getAttribute("role")).equals("admin")) {
				Client client = new Client();
				String idClient=request.getParameter("idClient");
				try {
					client.setUser(user);
					client.setPassportseries(request
							.getParameter("passportseries"));
					client.setPassportnumber(Integer.parseInt(request
							.getParameter("passportnumber")));
					client.setPhonenumber(Integer.parseInt(request
							.getParameter("phonenumber")));
					DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					Date date=null;
					try {
						date=dateFormat.parse(request.getParameter("birthday"));
					} catch (ParseException e) {
						logger.error("Parse exception. Birthday", e);
						e.printStackTrace();
					}
					client.setBirthday(date);
					if ((request.getParameter("idClient")).equals("null")) {
						tx.begin();
						em.persist(client);
						tx.commit();
						logger.info("Create new client");
					} else {
						tx.begin();
						client = em.find(Client.class, Integer.valueOf(idClient));
						client.setBirthday(date);
						client.setPassportseries(request
								.getParameter("passportseries"));
						client.setPassportnumber(Integer.parseInt(request
								.getParameter("passportnumber")));
						client.setPhonenumber(Integer.parseInt(request
								.getParameter("phonenumber")));
						tx.commit();
					
						logger.info("Update client");
					}
				} catch (RuntimeException e) {

					logger.info("Runtime exception", e);

					String messageNotUpdate = langConfig
							.getText(LocaleConfig.MESSAGE_FIELDS);
					request.setAttribute("message", messageNotUpdate);
					String page = PagePath.getInstance().getPage(
							PagePath.MESSAGE_PAGE_PATH);
					return page;
				}
			}
		}
		em.close();
		emf.close();
		
		String messageUpdate = langConfig
				.getText(LocaleConfig.MESSAGE_UPDATE_PROFILE);
		request.setAttribute("message", messageUpdate);

		logger.info("Redirection to the message page");

		String page = PagePath.getInstance()
				.getPage(PagePath.MESSAGE_PAGE_PATH);
		return page;
	}
}
