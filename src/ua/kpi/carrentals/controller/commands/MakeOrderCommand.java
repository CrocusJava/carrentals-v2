package ua.kpi.carrentals.controller.commands;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
import ua.kpi.carrentals.entitiesnew.Car;
import ua.kpi.carrentals.entitiesnew.Client;
import ua.kpi.carrentals.entitiesnew.Orderlist;
import ua.kpi.carrentals.entitiesnew.Place;
import ua.kpi.carrentals.entitiesnew.User;
/**
 * MakeOrderCommand class is the ICommand interface
 * implementation. The execution of this command to create order in the database.
 * Besides if user isn't client will create new client. This command realizes
 * redirection to the index page.
 *
 * @author Tkachuk
 * @see ICommand
 */
public class MakeOrderCommand implements ICommand{
	private static Logger logger=Logger.getLogger(MakeOrderCommand.class);

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		
		LocaleConfig langConfig=LocaleConfig.getInstance();
		
		Orderlist order=new Orderlist();
		String idcar=request.getParameter("idcar");
		String idplaceget=request.getParameter("idplaceget");
		String idplacereturn=request.getParameter("idplacereturn");
		
		EntityManagerFactory emf=Persistence.createEntityManagerFactory("CarrentalsPU");
		EntityManager em=emf.createEntityManager();
		EntityTransaction tx=em.getTransaction();
		tx.begin();
		Car car=em.find(Car.class, Integer.valueOf(idcar)); 
		Place placeGet=em.find(Place.class, Integer.valueOf(idplaceget)); 
		Place placeReturn=em.find(Place.class, Integer.valueOf(idplacereturn));
		tx.commit();
				
		order.setCar(car);
		order.setPlaceGet(placeGet);
		order.setPleceReturn(placeReturn);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try {
			order.setDateGet(dateFormat.parse(request.getParameter("dateget")));
			order.setDateReturn(dateFormat.parse(request.getParameter("datereturn")));
		} catch (ParseException e) {
			logger.error("Parse exception. Date", e);
			e.printStackTrace();
		}
		if ((request.getParameter("idclient")).equals("null")){
			
			try{
				Client client=new Client();
				tx.begin();
				User user=em.find(User.class, (Integer) request.getSession().getAttribute("ID"));
				tx.commit();
				client.setUser(user);
				client.setPassportseries(request.getParameter("passportseries"));
				client.setPassportnumber(Integer.parseInt(request.getParameter("passportnumber")));
				client.setPhonenumber(Integer.parseInt(request.getParameter("phonenumber")));
				try {
					client.setBirthday(dateFormat.parse(request.getParameter("birthday")));
				} catch (ParseException e) {
					logger.error("Parse exception. Birthday", e);
					e.printStackTrace();
				}
				tx.begin();
				em.persist(client);
				tx.commit();
				
				order.setClient(client);
							
			} catch (RuntimeException e){
				
				logger.info("Runtime exception",e);
			
				String messageNotUpdate=langConfig.getText(LocaleConfig.MESSAGE_FIELDS);
				request.setAttribute("message", messageNotUpdate);
				String page = PagePath.getInstance().getPage(PagePath.MESSAGE_PAGE_PATH);
				return page;
			}
			
		} else {
			String idClient=request.getParameter("idclient");
			tx.begin();
			Client client=em.find(Client.class, Integer.valueOf(idClient));
			tx.commit();
			order.setClient(client);
		}
		tx.begin();
		em.persist(order);
		tx.commit();
		em.close();
		emf.close();
		
		String message=langConfig.getText(LocaleConfig.MESSAGE_ORDER_WAS_CREATE);
		request.setAttribute("message", message);
		
		logger.info("Redirection to the message page");
		
		String page=PagePath.getInstance().getPage(PagePath.MESSAGE_PAGE_PATH);
		return page;
	}

}
