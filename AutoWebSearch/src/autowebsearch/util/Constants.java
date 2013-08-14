package autowebsearch.util;

import java.util.Hashtable;

public class Constants {

	public static enum FUEL_TYPE {DIESEL, GASOLINA, GPL, GASOLEO, GASÓLEO, NA};
	public static enum GEARBOX_TYPE {MANUAL, AUTOMÁTICA, AUTOMATICA, NA};
	
	public static final String FILE_EXPORT_LOCATION = "c:\\";
	
	public static final String [][] MAKE_MODEL = 
		{	{"AUDI","A4 AVANT"},
			{"VOLKSWAGEN","PASSAT VARIANT"},
			{"VOLVO","V60"}
		};
	
	public static final String SAPO_SEARCH_QUERY = "http://services.sapo.pt/Auto/RSS?q=";
	public static enum PROPERTIES {BRAND, MODEL, PRICE, REGISTRATION, KM, POWER, CC, FUEL , KIND, COLOR_EXT, COLOR_IN, GEARBOX, DOOR_NUM, PLACES, ORIGIN, WARRANTY };
	public static final Hashtable<String, PROPERTIES> SAPO_PROPERTIES = new Hashtable<String, PROPERTIES>(){
		private static final long serialVersionUID = -5946368331502265930L;

	{
		put("Preço", PROPERTIES.PRICE);
		put("Primeiro Registo", PROPERTIES.REGISTRATION);
		put("Quilómetros", PROPERTIES.KM);
		put("Potência", PROPERTIES.POWER);
		put("Cilindrada", PROPERTIES.CC);
		put("Combustível", PROPERTIES.FUEL);
		put("Tipo", PROPERTIES.KIND);
		put("Cor exterior", PROPERTIES.COLOR_EXT);
		put("Cor interior", PROPERTIES.COLOR_IN);
		put("Caixa de Velocidades", PROPERTIES.GEARBOX);
		put("Portas", PROPERTIES.DOOR_NUM);
		put("Lugares", PROPERTIES.PLACES);
		put("Origem", PROPERTIES.ORIGIN);
		put("Garantia", PROPERTIES.WARRANTY);
	}};	
	
	public static final int DAY = 15;
	public static final Hashtable<String, Integer> MONTHS_PT = new Hashtable<String, Integer>() {
		private static final long serialVersionUID = 1L;
	{
		put ("Janeiro", 1);
		put ("Fevereiro", 2);
		put ("Março", 3);
		put ("Abril", 4);
		put ("Maio", 5);
		put ("Junho", 6);
		put ("Julho", 7);
		put ("Agosto", 8);
		put ("Setembro", 9);
		put ("Outubro", 10);
		put ("Novembro", 11);
		put ("Dezembro", 12);
	}};
}
