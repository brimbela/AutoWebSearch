package autowebsearch.action.sapo;

import java.util.List;

import javax.xml.xpath.XPath;

import autowebsearch.dao.Vehicle;
import autowebsearch.service.sapo.SapoSearchService;

public class SapoSearchAction {

	private SapoSearchService sapoSearchService;
	private XPath xpath;
	
	public SapoSearchAction(){
		this.sapoSearchService = new SapoSearchService();
		this.xpath = setXpath();
	}
	
	public void runTestQuery() throws Exception{
		String query = "Brand:Volvo+Model:V60";
		List<Vehicle> resultList = sapoSearchService.getListOfVehicules(query, xpath);
		
		for(Vehicle car : resultList){
			return;
		}
	}
	
	private XPath setXpath(){
		return sapoSearchService.createXPath();
	}

	public void setSapoSearchService(SapoSearchService sapoSearchService) {
		this.sapoSearchService = sapoSearchService;
	}
	
	
}
