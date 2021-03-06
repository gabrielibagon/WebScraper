import org.jsoup.*;
import org.jsoup.nodes.Document;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.io.PrintWriter;
import java.util.Scanner;


public class WebScraper {
	public static void main(String[] args) throws FileNotFoundException{
		System.out.print("Enter department or specific show:");
		Scanner in = new Scanner(System.in);
		String department = in.next();
		Browser b = new Browser(department);
		Playlist p = new Playlist();
		ArrayList<String> urlCollection = b.getURLCollection();	
		ArrayList<String> list1 = new ArrayList<String>();
		
		//connecting to the webpage
		for (String url : urlCollection){
			try{
				Document doc = Jsoup.connect(url).get();
				list1 = p.extractPlaylist(doc);
				//System.out.println("haha");
			}
			catch(IOException e){
				System.out.println("Cannot connect to site");
			}
			
		}
		p.addPlaylists(list1);
		
		p.playCount();
		ArrayList<String> finalArtist = p.getFinalArtist();
		ArrayList<Integer> finalCount = p.getFinalCount();
		PrintWriter out1 = new PrintWriter("TuesdaysJustAsBad.xls");
		for (int i = 0; i < p.getFinalArtist().size(); i++){
			out1.println(finalArtist.get(i) + "\t" + finalCount.get(i));		
		}
		out1.close();
	}
}
