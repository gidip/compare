package comparePrice.compare;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import comparePrice.compare.PropertyDao;
//import comparePrice.compare.Property;


	public class Zoopla extends Thread{
		PropertyDao propertyDao;
		
		
		// Constructor 
	    public Zoopla(){
	    	propertyDao = new PropertyDao();
	    	propertyDao.init();
	    	
	    	  try{
	              scrapeProperty();
	          }
	          catch(Exception ex){
	              ex.printStackTrace();
	          }
	    }
	    // Scrapes data from web site
	  public  List<Property> scrapeProperty() throws IOException {
	 
		  String viewLinkStart = "https://www.zoopla.co.uk";
		  
	        //Download HTML document from website
	        Document doc = Jsoup.connect("https://www.zoopla.co.uk/for-sale/property/london/?q=london&radius=0&results_sort=newest_listings&search_source=refine"  ).get();
	        
	        //Get all of the items on the page
	        Elements items = doc.select(".listing-results-wrapper");
	        
	        //Work through the items
	        for(int i=0; i<items.size(); ++i){
	        	
	       //Get the link for property image
	       Elements img_link = items.get(i).select("img[data-ajax]");
	       String imglink = img_link.attr("src");
	        
	       //Get the property description
	       Elements descriptions = items.get(i).select("p");
	       String description = descriptions.text();
	        
	       //Get the location of property
	       Elements locations = items.get(i).select("a.listing-results-address");
	       String location = locations.text();

	       
	       //Get the longitude
	       Elements longitude = items.get(i).getElementsByAttribute("ol");
	       String longitud = longitude.attr("data-map-long");
	        
	       //Get the latitude
	       Elements latitude = items.get(i).select("ol");
	       String latitud = latitude.attr("data-map-lat");
	        
	       //Get the price of property 
	       Elements price = items.get(i).select("a.listing-results-price");
	       String price1 = price.text();
	        
	       //Get the link to view the property
	       Elements view_link = items.get(i).select("a.photo-hover");
	       String viewlink = viewLinkStart + view_link.attr("href");
	              

	//Document paginationPage = Jsoup.connect("https://www.foxtons.co.uk/search?submit_type=search&sold=1&location=")
//	    .userAgent("https://www.foxtons.co.uk")
//	    .get();;
//	              
	//Elements pagination = paginationPage.select(".pagination > li > a");
//	    for (Element e:pagination) {
//	        String url = e.attr("abs:href");
//	        //Document page = Jsoup.connect(url).get();
//	        //scrape the page..
//	    }
	        Property property = new Property();
	        property.setAgentId(3);     
	        property.setImgLink(imglink);
	        property.setDescription(description);
	        property.setLocation(location);
	        property.setLongitude(longitud);
	        property.setLatitude(latitud);
	        property.setPrice(price1);
	        property.setViewLink(viewlink);

	        
	        propertyDao.addProperty(property);

	        }
			return null;
	      
	        
		}
	  
	    
	}


