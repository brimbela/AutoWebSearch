package autowebsearch.action.sapo;

import java.util.List;

import javax.xml.xpath.XPath;

import autowebsearch.dao.Vehicle;
import autowebsearch.service.file.FileService;
import autowebsearch.service.sapo.SapoSearchService;
import autowebsearch.util.DateUtil;

public class SapoSearchAction {

	private SapoSearchService sapoSearchService;
	private FileService fileService;
	private XPath xpath;
	
	public SapoSearchAction(){
		this.sapoSearchService = new SapoSearchService();
		this.fileService = new FileService();
		this.xpath = setXpath();
	}
	
	public void runTestQuery() throws Exception{
		String query = "Brand:Volvo+Model:V60";
		List<Vehicle> resultList = sapoSearchService.getListOfVehicules(query, xpath);
		String date =  DateUtil.getCurrentDate().replace(" ", "_").replace(":", "-");
		fileService.generateCSVCarList(resultList, "teste", "SAPO", date);
	}
	
	private XPath setXpath(){
		return sapoSearchService.createXPath();
	}

	public void setSapoSearchService(SapoSearchService sapoSearchService) {
		this.sapoSearchService = sapoSearchService;
	}
	
	
}
