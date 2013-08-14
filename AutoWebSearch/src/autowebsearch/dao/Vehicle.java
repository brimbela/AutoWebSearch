package autowebsearch.dao;

import java.text.ParseException;
import java.util.Date;

import autowebsearch.util.Constants;
import autowebsearch.util.Constants.FUEL_TYPE;
import autowebsearch.util.DateUtil;

public class Vehicle {

	private String make;
	private String model;
	private String kind;
	private String equipmentLevel;
	private Date firstRegistration;
	private int kms, CV, CO2, askingPrice, CC, doorNum, places;
	private double particles;
	private boolean brandNew;
	private String link;
	private String description;
	private String timeToLive;
	private String publicationDate;
	private String comments;
	private String outColor, inColor;
	private String warranty;
	private String origin;
	private Constants.FUEL_TYPE fuel;
	private Constants.GEARBOX_TYPE gearbox;
	
	public Vehicle(){
		this.brandNew = false;
		this.particles = kms = CV = CO2 = askingPrice = CC = doorNum = places -1;
	}
	
	public int getAgeInDays() throws ParseException{
		Date currentDate = new Date();
		if(this.getFirstRegistration().before(currentDate))
			return 0;

		double diff = Math.abs(currentDate.getTime() - this.getFirstRegistration().getTime());

		double days = diff / (24 * 60 * 60 * 1000);

		days = Math.floor(days);
		return (int)days;
	}
	
	public double getAgeInYears() throws ParseException{
		Date currentDate = new Date();
		if(this.getFirstRegistration().before(currentDate))
			return 0;

		double diff = Math.abs(currentDate.getTime() - this.getFirstRegistration().getTime());

		double days = diff / (24 * 60 * 60 * 1000);

		days = Math.floor(days);
		return days/365.00;
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
		return equipmentLevel == null ? "" : equipmentLevel;
	}
	public void setEquipmentLevel(String equipmentLevel) {
		this.equipmentLevel = equipmentLevel;
	}
	public Date getFirstRegistration() throws ParseException {
		return (Date) (firstRegistration == null ? "" : DateUtil.convertStringToDate("01-01-1900 00:00"));
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
		return link == null ? "" : link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getDescription() {
		return description == null ? "" : description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTimeToLive() {
		return timeToLive == null ? "" : timeToLive;
	}

	public void setTimeToLive(String timeToLive) {
		this.timeToLive = timeToLive;
	}

	public String getPublicationDate() {
		return publicationDate == null ? "" : publicationDate;
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

	public int getCC() {
		return CC;
	}

	public void setCC(int cC) {
		CC = cC;
	}

	public Constants.FUEL_TYPE getFuel() {
		return fuel == null ? FUEL_TYPE.NA : fuel;
	}

	public void setFuel(Constants.FUEL_TYPE fuel) {
		this.fuel = fuel;
	}

	public String getKind() {
		return kind == null ? "" : kind;
	}

	public void setKind(String kind) {
		this.kind = kind;
	}

	public String getInColor() {
		return inColor == null ? "" : inColor;
	}

	public void setInColor(String inColor) {
		this.inColor = inColor;
	}

	public String getOutColor() {
		return outColor == null ? "" : outColor;
	}

	public void setOutColor(String outColor) {
		this.outColor = outColor;
	}

	public Constants.GEARBOX_TYPE getGearbox() {
		return gearbox == null ? Constants.GEARBOX_TYPE.NA : gearbox;
	}

	public void setGearbox(Constants.GEARBOX_TYPE gearbox) {
		this.gearbox = gearbox;
	}

	public String getWarranty() {
		return warranty == null ? "" : warranty;
	}

	public void setWarranty(String warranty) {
		this.warranty = warranty;
	}

	public int getDoorNum() {
		return doorNum;
	}

	public void setDoorNum(int doorNum) {
		this.doorNum = doorNum;
	}

	public int getPlaces() {
		return places;
	}

	public void setPlaces(int places) {
		this.places = places;
	}

	public String getOrigin() {
		return origin == null ? "" : origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
}
