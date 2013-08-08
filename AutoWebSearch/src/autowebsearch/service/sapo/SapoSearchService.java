package autowebsearch.service.sapo;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.safety.Whitelist;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import autowebsearch.dao.Vehicle;
import autowebsearch.util.Constants;

import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;

public class SapoSearchService {

	public List<Vehicle> getListOfVehicules(String query, XPath xpath) throws Exception{

		URL url = new URL(Constants.SAPO_SEARCH_QUERY + query);
		Document xml = parseResult(url);
		List<Vehicle> carList = new ArrayList<Vehicle>();

		DTMNodeList channels = (DTMNodeList) xpath.evaluate("//channel", xml, XPathConstants.NODESET);

		/**
		 * Por cada elemento <channel>
		 */
		for (int i = 0; i < channels.getLength(); i++) {

			

			Node channel = channels.item(i);
			String link = "";
			String title = xpath.evaluate("title/text()", channel);
			//             link = xpath.evaluate("link/text()", channel);
			//             String description = xpath.evaluate("description/text()", channel);
			//             String timeToLive = xpath.evaluate("ttl/text()", channel);
			//				int startIndex = Integer.parseInt(xpath.evaluate("openSearch:startIndex/text()", channel));
			//				int totalResults = Integer.parseInt(xpath.evaluate("openSearch:totalResults/text()", channel));
			//				int itemsPerPage = Integer.parseInt(xpath.evaluate("openSearch:itemsPerPage/text()", channel));

			DTMNodeList items = (DTMNodeList) xpath.evaluate("item", channel, XPathConstants.NODESET);

			for(int j = 0; j<items.getLength(); j++){
				Vehicle car = new Vehicle();

				Node item = items.item(j);
				title = xpath.evaluate("title/text()", item);
				link = xpath.evaluate("link/text()", item);
				//String enclosure = xpath.evaluate("enclosure/@url", item);
				String guid = xpath.evaluate("guid/text()", item);
				String pubDate = xpath.evaluate("pubDate/text()", item);

				car.setPublicationDate(pubDate);
				car.setLink(guid);
				car.setDescription(title);
				
				carList.add(car);
			}
		}
		return carList;
	}

	private Document parseResult(URL url) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		dbf.setNamespaceAware(true);    //Importante!
		DocumentBuilder db = dbf.newDocumentBuilder();
		return db.parse(url.openStream());
	}

	public String getPageHtml(String url) throws ClientProtocolException, IOException{
		/*http://hc.apache.org/httpcomponents-client-ga/httpclient/examples/org/apache/http/examples/client/ClientWithResponseHandler.java*/
		String responseBody = "";
		HttpClient httpclient = new DefaultHttpClient();
		try {
			HttpGet httpget = new HttpGet(url);
			responseBody = httpclient.execute(httpget, new BasicResponseHandler());
		} finally {
			httpclient.getConnectionManager().shutdown();
		}
		return responseBody;
	}
	
	public void getCarDetails(String html, Vehicle car) throws IOException {
		/*
		 * <ul class="cargen listinfo">
		 * 	<li><strong>Preço:</strong>"     18.000€"</li>
		 * a ordem dos li e' pre-definida, pode ir para uma constante
		 * basta encontrar o cargen listinfo e seguir daí
		 */
		org.jsoup.nodes.Document doc = Jsoup.parse(Jsoup.clean(html, Whitelist.relaxed().addAttributes(":all", "class")));
		
		for(Element el : doc.select("ul[class=chargen listinfo]").select("li")){
			String[] tokens = el.text().split(": ");
			Constants.PROPERTIES property = Constants.SAPO_PROPERTIES.get(tokens[0]);
			switch (property) {
			case PRICE:
				int price = 0;
				try{
					price = Integer.valueOf(tokens[1].replaceAll(".", "").replaceAll("€", "").trim());
				}catch (Exception e) {
					continue;
				}
				car.setAskingPrice(price);
				break;
			case REGISTRATION:
				String[] st = tokens[1].split(" ");
				if(st.length > 0){
					String month = tokens[1].substring(1, tokens[1].length()-1);
					Calendar cal = Calendar.getInstance();
					cal.set(Integer.valueOf(tokens[0]), Constants.MONTHS_PT.get(month), Constants.DAY);
					car.setFirstRegistration(cal.getTime());
				}
				break;
			default:
				break;
			}
		}
	}

	public XPath createXPath(){

		XPath xpath = XPathFactory.newInstance().newXPath();
		xpath.setNamespaceContext(new NamespaceContext() {
			@Override
			public String getNamespaceURI(String prefix) {
				if(prefix.equals("openSearch")){
					return "http://a9.com/-/spec/opensearchrss/1.0/";
				}
				return XMLConstants.NULL_NS_URI;
			}

			@Override
			public String getPrefix(String namespaceURI) {
				if(namespaceURI.equals("http://a9.com/-/spec/opensearchrss/1.0/")){
					return "openSearch";
				}
				return "";
			}

			@SuppressWarnings("rawtypes")
			@Override
			public Iterator getPrefixes(String namespaceURI) {
				ArrayList<String> prefixes = new ArrayList<String>();
				prefixes.add("openSearch");
				return prefixes.iterator();
			}
		});

		return xpath;
	}
}
