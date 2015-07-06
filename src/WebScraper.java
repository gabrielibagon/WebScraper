import org.jsoup.*;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;


public class WebScraper {
	public static void main(String[] args) throws FileNotFoundException{
		String department = "American";
		Browser b = new Browser(department);
		Playlist p = new Playlist();
		ArrayList<String> urlCollection = b.getUrl();	
		ArrayList<String> list1 = new ArrayList<String>();
		//connecting to the webpage
		for (String url : urlCollection){
			
			try{
				Document doc = Jsoup.connect(url).get();
				list1 = p.extractPlaylist(doc);
			}
			catch(IOException e){
				System.out.println("Cannot connect to site");
			}
			
		}
		p.addPlaylists(list1);
		ArrayList<String> totalPlaylists = p.getLists();
		PrintWriter out1 = new PrintWriter("hello.txt");
		out1.println(totalPlaylists);
		out1.close();

//		ArrayList<String> totalPlaylists = p.getLists();
//		for (String artist : totalPlaylists){
//			System.out.println(artist);
//		}
		
		//creates new playlist object for individual webpage
		p.playCount();
		
	}
}
