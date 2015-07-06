import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.util.ArrayList;

public class Browser {
	
	private Document home;
	private ArrayList<String> urlCollection;
	private ArrayList<String> departmentURL;
	
	//Sets up the browser
	//the browser starts at the dept playlist home.
	public Browser(String department){
		departmentURL = new ArrayList<String>();
		departmentURL = byDepartment(department);
		urlCollection = new ArrayList<String>();
		for (int j = 0; j < 10; j++){
			try{
				home = Jsoup.connect(departmentURL.get(j)).get();
				makeURLCollection(home);
			}
			catch(IOException e){
				System.out.println("Cannot connect to site");
			}
		}
	}
	
//	public void pageBrowser(String[] departmentURL){
//		for (String url : departmentURL){
//			Document home;
//			try{
//				home = Jsoup.connect(url).get();
//				makeURLCollection(home);
//			}
//			catch(IOException e){
//				System.out.println("Cannot connect to site");
//			}
//		}
//	}
	
	public ArrayList<String> byDepartment(String department){
		ArrayList<String> AmericanURL = new ArrayList<String>();
		AmericanURL.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_department_nid=52");
		for (int i = 1; i < 10; i++){
			AmericanURL.add("https://www.cc-seas.columbia.edu/wkcr/playlist?page=" + i + "&field_department_nid=52");
		}
		if (department == "American"){
			departmentURL = AmericanURL;
		}
		return departmentURL;
	}
	
	//makes an ArrayList of URLs on a page (home)
	public void makeURLCollection(Document home){
		Elements htmlURL = 
			home.select
			("td.views-field.views-field-field-station-program-nid");
		Elements links = htmlURL.select("a");
		for (Element link : links){
			String absUrl = link.attr("abs:href");
			urlCollection.add(absUrl);
		}
	}
	
	public ArrayList<String> getURLCollection(){
		return urlCollection;
	}
}
