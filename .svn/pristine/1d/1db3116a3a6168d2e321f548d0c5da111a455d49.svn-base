package ua.kpi.carrentals.entitiesnew;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 * Order class is entity that describe all fields that belong order
 * 
 * @author Tkachuk
 */
@Entity
@Table(name = "orderlist")
@NamedQuery(name="Orderlist.sortedById", query="SELECT ol FROM Orderlist ol ORDER BY ol.idOrder")
public class Orderlist implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="idorder")
	protected Integer idOrder;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idcar", nullable=false)
	protected Car car;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idclient", nullable=false)
	protected Client client;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idplaceget", nullable=false)
	protected Place placeGet;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idplacereturn", nullable=false)
	protected Place placeReturn;
	
	@Temporal(TemporalType.DATE)
	@Column(name="dateget")
	protected Date dateGet;
	
	@Temporal(TemporalType.DATE)
	@Column(name="datereturn")
	protected Date dateReturn;
	
	@Column(name="stateorder")
	protected Byte stateOrder;
	
	@Column(name="description")
	protected String description;
	
	@Column(name="orderexecuted")
	protected Byte orderExecuted;
	
	@Column(name="descriptiondamage")
	protected String descriptionDamage;
	
	/**
     * Constructor creates Order with undefined field values
     */
	public Orderlist(){
		
	}
	/**
     * Constructor creates Client with specified field values
     *
     * @param idOrder order id
     * @param Car car
     * @param Client client
     * @param Place place get
     * @param Place place return
     * @param dateGet date get
     * @param dateReturn date return
     * @param stateOrder order state
     * @param description order description in case deviation
     * @param orderExecuted order executed
     * @param descriptionDamage description damage
     * 
     */
	public Orderlist(Integer idOrder, Car car, Client client, 
			Place placeGet,	Place placeReturn, Date dateGet,
			Date dateReturn, Byte stateOrder, String description,
			Byte orderExecuted, String descriptionDamage) {

		this.idOrder = idOrder;
		this.car = car;
		this.client = client;
		this.placeGet = placeGet;
		this.placeReturn = placeReturn;
		this.dateGet = dateGet;
		this.dateReturn = dateReturn;
		this.stateOrder = stateOrder;
		this.description = description;
		this.orderExecuted = orderExecuted;
		this.descriptionDamage = descriptionDamage;
	}

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(Integer idOrder) {
		this.idOrder = idOrder;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Place getPlaceGet() {
		return placeGet;
	}

	public void setPlaceGet(Place placeGet) {
		this.placeGet = placeGet;
	}

	public Place getPlaceReturn() {
		return placeReturn;
	}

	public void setPleceReturn(Place placeReturn) {
		this.placeReturn = placeReturn;
	}

	public Date getDateGet() {
		return dateGet;
	}

	public void setDateGet(Date dateGet) {
		this.dateGet = dateGet;
	}

	public Date getDateReturn() {
		return dateReturn;
	}

	public void setDateReturn(Date dateReturn) {
		this.dateReturn = dateReturn;
	}

	public Byte isStateOrder() {
		return stateOrder;
	}

	public void setStateOrder(Byte stateOrder) {
		this.stateOrder = stateOrder;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Byte isOrderExecuted() {
		return orderExecuted;
	}

	public void setOrderExecuted(Byte orderExecuted) {
		this.orderExecuted = orderExecuted;
	}

	public String getDescriptionDamage() {
		return descriptionDamage;
	}

	public void setDescriptionDamage(String descriptionDamage) {
		this.descriptionDamage = descriptionDamage;
	}

	@Override
	public String toString() {
		StringBuilder desc = new StringBuilder();
		desc.append("Order=").append(idOrder);
		desc.append("\nClient=").append(client);
		desc.append("\nCar=").append(car);
		desc.append("\nPlace get=").append(placeGet);
		desc.append("\nPlace return=").append(placeReturn);
		desc.append("\nState order=").append(stateOrder);
		return desc.toString();
	}

}
