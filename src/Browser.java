import java.io.IOException;
import java.util.ArrayList;
import org.jsoup.*;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Browser {
	
	private Document home;
	private ArrayList<String> urlCollection;
	private String departmentURL;
	
	//Sets up the browser
	//the browser starts at the dept playlist home.
	public Browser(String department){
		departmentURL = byDepartment(department);
		try{
			home = Jsoup.connect(departmentURL).get();
		}
		catch(IOException e){
			System.out.println("Cannot connect to site");
		}
	}
	
	public String byDepartment(String department){
		String AmericanURL = 
				"https://www.cc-seas.columbia.edu/wkcr/playlist?field_department_nid=52";
		if (department == "American"){
			departmentURL = AmericanURL;
		}
		return departmentURL;
	}
	
	//makes an ArrayList of URLs on a page (home)
	public ArrayList<String> getUrl(){
		ArrayList<String> urlCollection = new ArrayList<String>();
		Elements htmlURL = 
			home.select
			("td.views-field.views-field-field-station-program-nid");
		Elements links = htmlURL.select("a");
		for (Element link : links){
			String absUrl = link.attr("abs:href");
			urlCollection.add(absUrl);
		}
		//System.out.println(urlCollection);
		return urlCollection;
	}
}
