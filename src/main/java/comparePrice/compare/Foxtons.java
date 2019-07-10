package comparePrice.compare;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import comparePrice.compare.PropertyDao;
//import comparePrice.compare.Property;


public class Foxtons extends Thread{
	PropertyDao propertyDao;
	
	
	// Constructor 
    public Foxtons(){
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
    	
	  String viewLinkStart = "https://www.foxtons.co.uk";
	  
    	//Name of item that we want to scrape
        String itemName = "property";

        //Download HTML document from website
        Document doc = Jsoup.connect("https://www.foxtons.co.uk/properties-for-sale/london" + itemName).get();
        
        //Get all of the items on the page
        Elements items = doc.select(".property_holder");
        
        //Work through the items
        for(int i=0; i<items.size(); ++i){
        	
        //Get the link for property image
        Elements img_link = items.get(i).select("a[href]");
        String imglink = img_link.attr("data-src");
        
        //Get the property description
        Elements descriptions = items.get(i).getElementsByTag("b");
        String description = descriptions.text();
        
        //Get the location of property
        Elements locations = items.get(i).getElementsByTag("h6");
        String location = locations.text();

       
        //Get the longitude
        Elements longitude = items.get(i).select("div");
        String longitud = longitude.attr("data-lon");
        
        //Get the latitude
        Elements latitude = items.get(i).select("div");
        String latitud = latitude.attr("data-lat");
        
       //Get the price of property 
       Elements price = items.get(i).select("data");
       String price1 = price.text();
        
        //Get the link to view the property
        Elements view_link = items.get(i).select("a.property_photo_holder");
        String viewlink = viewLinkStart + view_link.attr("href");
             

        Property property = new Property();
        property.setAgentId(1);     
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
