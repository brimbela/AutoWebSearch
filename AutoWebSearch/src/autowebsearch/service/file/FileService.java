package autowebsearch.service.file;

import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import autowebsearch.dao.Vehicle;
import autowebsearch.util.Constants;

public class FileService {

	public void generateCSVCarList(List<Vehicle> carList, String fileName, String source, String date) throws IOException, ParseException{
		
		String fileNameWithExtension = Constants.FILE_EXPORT_LOCATION + fileName + "_" +  date +  ".csv";
		FileWriter writer = new FileWriter(fileNameWithExtension);
		//header
		writer.append(source + " - " + date + "\n\n");
		writer.append(Constants.PROPERTIES.BRAND.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.MODEL.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.REGISTRATION.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.PRICE.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.CC.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.POWER.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.FUEL.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.KM.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.KIND.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.PLACES.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.DOOR_NUM.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.GEARBOX.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.WARRANTY.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.COLOR_EXT.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.COLOR_IN.toString());writer.append(";");
		writer.append(Constants.PROPERTIES.ORIGIN.toString());writer.append(";");
		writer.append("OBS");
		writer.append(";\n");
		
		for(Vehicle car : carList){
			
			writer.append(car.getMake());writer.append(";");
			writer.append(car.getModel());writer.append(";");
			writer.append(car.getAgeInYears() + "");writer.append(";");
			writer.append(car.getAskingPrice() + "");writer.append(";");
			writer.append(car.getCC() + "");writer.append(";");
			writer.append(car.getCV() + "");writer.append(";");
			writer.append(car.getFuel().toString());writer.append(";");
			writer.append(car.getKms() + "");writer.append(";");
			writer.append(car.getKind());writer.append(";");
			writer.append(car.getPlaces() + "");writer.append(";");
			writer.append(car.getDoorNum() + "");writer.append(";");
			writer.append(car.getGearbox().toString());writer.append(";");
			writer.append(car.getWarranty());writer.append(";");
			writer.append(car.getOutColor());writer.append(";");
			writer.append(car.getInColor());writer.append(";");
			writer.append(car.getOrigin());writer.append(";");
			writer.append("\n");
		}
		
		writer.flush();
		writer.close();
	}
}
