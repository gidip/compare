package comparePrice.compare;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import comparePrice.compare.PropertyDao;
//import comparePrice.compare.Property;


public class PrimeLocation extends Thread{
	PropertyDao propertyDao;
	
	
	// Constructor 
    public PrimeLocation(){
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
 
	    String viewLinkStart = "https://www.primelocation.com";
	    
        //Download HTML document from website
        Document doc = Jsoup.connect("https://www.primelocation.com/for-sale/property/london/?q=london&radius=0&results_sort=highest_price&search_source=refine&view_type=grid"  ).get();
        
        //Get all of the items on the page
        Elements items = doc.select(".listing-results-wrapper");
        
        //Work through the items
        for(int i=0; i<items.size(); ++i){
        	
        //Get the link for property image
        Elements img_link = items.get(i).getElementsByAttribute("data-ajax");
        String imglink = img_link.attr("src");
        
        //Get the property description
        Elements descriptions = items.get(i).getElementsByTag("p");
        String description = descriptions.text();
        
        //Get the location of property
        Elements locations = items.get(i).select("a[href]:containsOwn(,)");
        String location = locations.text();

       
        //Get the longitude
        Elements longitude = items.get(i).getElementsByAttribute("ol");
        String longitud = longitude.attr("data-map-long");
        
        //Get the latitude
        Elements latitude = items.get(i).select("ol");
        String latitud = latitude.attr("data-map-lat");
        
       //Get the price of property 
       Elements price = items.get(i).select("span.price");
       String price1 = price.text();
        
       //Get the link to view the property
       Elements view_link = items.get(i).select("a[href]");
       String viewlink = viewLinkStart + view_link.attr("href");
              

//Document paginationPage = Jsoup.connect("https://www.foxtons.co.uk/search?submit_type=search&sold=1&location=")
//    .userAgent("https://www.foxtons.co.uk")
//    .get();;
//              
//Elements pagination = paginationPage.select(".pagination > li > a");
//    for (Element e:pagination) {
//        String url = e.attr("abs:href");
//        //Document page = Jsoup.connect(url).get();
//        //scrape the page..
//    }
        Property property = new Property();
        property.setAgentId(4);     
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
