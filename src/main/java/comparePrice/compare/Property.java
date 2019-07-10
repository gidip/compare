package comparePrice.compare;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity  //Map to DB Table
@Table(name="property") //DB Table Name
public class Property {


	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;

	@Column(name="img_link")
	private String imgLink;


	@Column(name="description")
	private String description;

	@Column(name="location")
	private String location;

	@Column(name="longitude")
	private String longitude;

	@Column(name="latitude")
	private String latitude;

	@Column(name="price")
	private String price;

	@Column(name="view_link")
	private String viewLink;
	
	@Column(name="agent_id")
	private int agent;
	


	//Getters and setters 
    public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	public int getAgentId() {
		return agent;
	}

	public void setAgentId(int agentId) {
		this.agent = agentId;
	}


	public String getImgLink() {
		return imgLink;
	}


	public void setImgLink(String imgLink) {
		this.imgLink = imgLink;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getLocation() {
		return location;
	}


	public void setLocation(String location) {
		this.location = location;
	}


	public String getLongitude() {
		return longitude;
	}


	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}


	public String getLatitude() {
		return latitude;
	}


	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}


	public String getViewLink() {
		return viewLink;
	}


	public void setViewLink(String viewLink) {
		this.viewLink = viewLink;
	} 
    


	public String getPrice() {
		return price;
	}


	public void setPrice(String price) {
		this.price = price;
	}
	


}
