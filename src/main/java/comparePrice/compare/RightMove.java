package comparePrice.compare;

import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import comparePrice.compare.PropertyDao;
//import comparePrice.compare.Property;


		public class RightMove extends Thread{
			PropertyDao propertyDao;
			
			
			// Constructor 
		    public RightMove(){
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
		 
    	String viewLinkStart = "https://www.rightmove.co.uk";
    	
         //Download HTML document from website
		 Document doc = Jsoup.connect("https://www.rightmove.co.uk/property-for-sale/find.html?searchType=SALE&locationIdentifier=REGION%5E87490&insId=1&radius=0.0&minPrice=&maxPrice=&minBedrooms=&maxBedrooms=&displayPropertyType=&maxDaysSinceAdded=&_includeSSTC=on&sortByPriceDescending=&primaryDisplayPropertyType=&secondaryDisplayPropertyType=&oldDisplayPropertyType=&oldPrimaryDisplayPropertyType=&newHome=&auction=false"  ).get();
		        
		 //Get all of the items on the page
		 Elements items = doc.select(".propertyCard-wrapper");
		        
		 //Work through the items
		 for(int i=0; i<items.size(); ++i){
		        	
		     //Get the link for property image
			 Elements img_link = items.get(i).select("img");
		     String imglink = img_link.attr("src");
		        
		      //Get the property description
		      Elements descriptions = items.get(i).select("h2");
		      String description = descriptions.text();
		        
		       //Get the location of property
		       Elements locations = items.get(i).select(".propertyCard-address");
		       String location = locations.text();

		       
		       //Get the longitude
		       Elements longitude = items.get(i).getElementsByAttribute("ol");
		       String longitud = longitude.attr("data-map-long");
		        
		       //Get the latitude
		       Elements latitude = items.get(i).select("ol");
		       String latitud = latitude.attr("data-map-lat");
		        
		       //Get the price of property 
		       Elements price = items.get(i).select(".propertyCard-priceValue");
		       String price1 = price.text();
		        
		       //Get the link to view the property
		       Elements view_link = items.get(i).select("a[href]");
		       String viewlink = viewLinkStart + view_link.attr("href");
		              

		//Document paginationPage = Jsoup.connect("https://www.foxtons.co.uk/search?submit_type=search&sold=1&location=")
//		    .userAgent("https://www.foxtons.co.uk")
//		    .get();;
//		              
		//Elements pagination = paginationPage.select(".pagination > li > a");
//		    for (Element e:pagination) {
//		        String url = e.attr("abs:href");
//		        //Document page = Jsoup.connect(url).get();
//		        //scrape the page..
//		    }
		        Property property = new Property();
		        property.setAgentId(2);     
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


