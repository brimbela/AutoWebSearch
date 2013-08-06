package autowebsearch.service.sapo;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

import javax.xml.XMLConstants;
import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;

import org.omg.CORBA.Request;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import autowebsearch.util.Constants;

import com.sun.org.apache.xml.internal.dtm.ref.DTMNodeList;

@SuppressWarnings("restriction")
public class SapoSearchService {

	public void doSearch(String query, XPath xpath) throws Exception{
		
		 URL url = new URL(Constants.SAPO_SEARCH_QUERY + query);
		 Document xml = parseResult(url);
		 
		 /**
          * Obt�m os elementos <channel> relativo ao documento xml
          */
         DTMNodeList channels = (DTMNodeList) xpath.evaluate("//channel", xml, XPathConstants.NODESET);

         /**
          * Por cada elemento <channel>
          */
         for (int i = 0; i < channels.getLength(); i++) {
             //Elemento <channel>
             Node channel = channels.item(i);

           
             String title = xpath.evaluate("title/text()", channel);
             String link = xpath.evaluate("link/text()", channel);
             String description = xpath.evaluate("description/text()", channel);
             String language = xpath.evaluate("language/text()", channel);
             String timeToLive = xpath.evaluate("ttl/text()", channel);
             String copyright = xpath.evaluate("copyright/text()", channel);
             int startIndex = Integer.parseInt(xpath.evaluate("openSearch:startIndex/text()", channel));
             int totalResults = Integer.parseInt(xpath.evaluate("openSearch:totalResults/text()", channel));
             int itemsPerPage = Integer.parseInt(xpath.evaluate("openSearch:itemsPerPage/text()", channel));

             System.out.println("--------------------------------------------");
             System.out.println("Canal: " + title + " (" + language + " | " + copyright + ")");
             System.out.println("\t" + description);
             System.out.println("\tURL: " + link);
             System.out.println("\tTime to live: " + timeToLive);
             System.out.println("\tResultados " + startIndex + " - " + (startIndex + itemsPerPage) + " (" + totalResults + ")");

             DTMNodeList items = (DTMNodeList) xpath.evaluate("item", channel, XPathConstants.NODESET);

             for(int j = 0; j<items.getLength(); j++){
                 Node item = items.item(j);

                 title = xpath.evaluate("title/text()", item);
                 link = xpath.evaluate("link/text()", item);
                 DTMNodeList categories = (DTMNodeList) xpath.evaluate("category", item, XPathConstants.NODESET);
                 String enclosure = xpath.evaluate("enclosure/@url", item);
                 String guid = xpath.evaluate("guid/text()", item);
                 String pubDate = xpath.evaluate("pubDate/text()", item);

                 System.out.println();
                 System.out.println("\t# " + title);
                 System.out.println("\t| URL: " + link);
                 System.out.println("\t| Categorias: ");

                 for(int z = 0; z<categories.getLength(); z++){
                     System.out.println("\t|\t" + categories.item(z).getTextContent());
                 }

                 if(enclosure != null){
                     System.out.println("\t| Foto: " + enclosure);
                 }

                 System.out.println("\t| Data de publicação: " + pubDate);
                 System.out.println("\t| Link permanente: " + guid);

             }
         }
		 
	}
	
	private Document parseResult(URL url) throws ParserConfigurationException, SAXException, IOException{
		/**
         * Cria um DocumentBuilderFactory
         */

        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        /**
         * Coloca-o a trabalhar com namespaces
         */
        dbf.setNamespaceAware(true);    //Importante!

        /**
         * Cria um DocumentBuilder para gerar um objeto DOM a partir de String
         */
        DocumentBuilder db = dbf.newDocumentBuilder();

        /**
         * Gera o objeto a partir do stream
         */
        return db.parse(url.openStream());
	}
	
	public String readDetailsPage(URL url){
		
		DefaultHttpClient client = new DefaultHttpClient();
        HttpGet request = new HttpGet(url.toURI());
        HttpResponse response = client.execute(request);
        Request.Get("http://targethost/homepage").execute().returnContent();

        Reader reader = null;
        try {
            reader = new InputStreamReader(response.getEntity().getContent());

            StringBuffer sb = new StringBuffer();
            {
                int read;
                char[] cbuf = new char[1024];
                while ((read = reader.read(cbuf)) != -1)
                    sb.append(cbuf, 0, read);
            }

            return sb.toString();

        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	public XPath createXPath(){
		/**
         * Cria um objeto para suporte XPath no DOM
         */
        XPath xpath = XPathFactory.newInstance().newXPath();

        /**
         * Necess�rio criar um NamespaceContext para os prefixos existentes
         * no documento. Neste caso tratar� apenas de:
         *      xmlns:openSearch="http://a9.com/-/spec/opensearchrss/1.0/"
         */
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
