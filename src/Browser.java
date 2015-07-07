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
	//by department or by show
	public ArrayList<String> byDepartment(String department){
		//American ArrayLists
		ArrayList<String> AmericanURL = new ArrayList<String>();
		ArrayList<String> Across110thStreet = new ArrayList<String>();
		ArrayList<String> AmazingGrace = new ArrayList<String>();
		ArrayList<String> HobosLullaby = new ArrayList<String>();
		ArrayList<String> HonkyTonkin = new ArrayList<String>();
		ArrayList<String> NightTrain = new ArrayList<String>();
		ArrayList<String> Offbeat = new ArrayList<String>();
		ArrayList<String> SomethingInsideOfMe = new ArrayList<String>();
		ArrayList<String> TheMoonshineShow = new ArrayList<String>();
		ArrayList<String> TuesdaysJustAsBad = new ArrayList<String>();
		
		//American ALL [10 pages]
		AmericanURL.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_department_nid=52");
		for (int i = 1; i < 10; i++){
			AmericanURL.add("https://www.cc-seas.columbia.edu/wkcr/playlist?page=" + i + "&field_department_nid=52");
		}
		//American ACROSS 110TH STREET [1 page]
		Across110thStreet.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_station_program_nid=70");
		//American AMAZING GRACE [1 page]
		AmazingGrace.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_station_program_nid=5");
		//American HOBO'S LULLABY [1 page]
		HobosLullaby.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_station_program_nid=2340");
		//American HONKY TONKIN [1 page]
		HonkyTonkin.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_station_program_nid=28");
		//American NIGHT TRAIN [1 page]
		NightTrain.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_station_program_nid=30");
		//American OFFBEAT [2 pages]
		Offbeat.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_station_program_nid=31");
		Offbeat.add("https://www.cc-seas.columbia.edu/wkcr/playlist?page=1&field_station_program_nid=31");
		//American SOMETHING INSIDE OF ME [1 page]
		SomethingInsideOfMe.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_station_program_nid=4899");
		//American THE MOONSHINE SHOW [2 pages]
		TheMoonshineShow.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_station_program_nid=6");
		TheMoonshineShow.add("https://www.cc-seas.columbia.edu/wkcr/playlist?page=1&field_station_program_nid=6");
		//American TUESDAYS JUST AS BAD [2 pages]
		TuesdaysJustAsBad.add("https://www.cc-seas.columbia.edu/wkcr/playlist?field_station_program_nid=29");
		TuesdaysJustAsBad.add("https://www.cc-seas.columbia.edu/wkcr/playlist?page=1&field_station_program_nid=29");
		
		
		if (department == "American"){
			departmentURL = AmericanURL;
		}
		if (department == "Across 110th Street"){
			departmentURL = Across110thStreet;
		}
		if (department == "Amazing Grace"){
			departmentURL = AmazingGrace;
		}
		if (department == "Hobo's Lullaby"){
			departmentURL = HobosLullaby;
		}
		if (department == "HonkyTonkin"){
			departmentURL = HonkyTonkin;
		}
		if (department == "Night Train"){
			departmentURL = NightTrain;
		}
		if (department == "Offbeat"){
			departmentURL = Offbeat;
		}
		if (department == "Something Inside of Me"){
			departmentURL = SomethingInsideOfMe;
		}
		if (department == "The Moonshine Show"){
			departmentURL = TheMoonshineShow;
		}
		if (department == "Tuesday's Just As Bad"){
			departmentURL = TuesdaysJustAsBad;
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
