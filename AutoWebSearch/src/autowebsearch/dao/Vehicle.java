package autowebsearch.dao;

import java.util.Date;

public class Vehicle {

	private String make;
	private String model;
	private String equipmentLevel;
	private Date firstRegistration;
	private int kms, CV, CO2, askingPrice;
	private double particles;
	private boolean brandNew;
	private String link;
	private String description;
	private String timeToLive;
	private String publicationDate;
	private String comments;
	
	public Vehicle(){
		this.brandNew = false;
		this.particles = -1.0;
	}
	
	public int getAgeInDays(){
		Date currentDate = new Date();
		if(this.getFirstRegistration().before(currentDate))
			return 0;

		double diff = Math.abs(currentDate.getTime() - this.getFirstRegistration().getTime());

		double days = diff / (24 * 60 * 60 * 1000);

		days = Math.floor(days);
		return (int)days;
	}
	
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getEquipmentLevel() {
		return equipmentLevel;
	}
	public void setEquipmentLevel(String equipmentLevel) {
		this.equipmentLevel = equipmentLevel;
	}
	public Date getFirstRegistration() {
		return firstRegistration;
	}
	public void setFirstRegistration(Date firstRegistration) {
		this.firstRegistration = firstRegistration;
	}
	public int getKms() {
		return kms;
	}
	public void setKms(int kms) {
		this.kms = kms;
	}
	public int getCV() {
		return CV;
	}
	public void setCV(int cV) {
		CV = cV;
	}
	public int getCO2() {
		return CO2;
	}
	public void setCO2(int cO2) {
		CO2 = cO2;
	}
	public int getAskingPrice() {
		return askingPrice;
	}
	public void setAskingPrice(int askingPrice) {
		this.askingPrice = askingPrice;
	}
	public double getParticles() {
		return particles;
	}
	public void setParticles(double particles) {
		this.particles = particles;
	}
	public boolean isBrandNew() {
		return brandNew;
	}
	public void setBrandNew(boolean brandNew) {
		this.brandNew = brandNew;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeToLive() {
		return timeToLive;
	}

	public void setTimeToLive(String timeToLive) {
		this.timeToLive = timeToLive;
	}

	public String getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(String publicationDate) {
		this.publicationDate = publicationDate;
	}

	public String getComments() {
		return comments == null ? "" : comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}
}
