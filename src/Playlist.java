import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class Playlist {
	
	private ArrayList<String> artists;
	private ArrayList<String> totalPlaylists;
	public ArrayList<String> finalArtist;
	public ArrayList<Integer> finalCount;
	
	
	public Playlist(){
		artists = new ArrayList<>();
		totalPlaylists = new ArrayList<>();
		finalArtist = new ArrayList<>();
		finalCount = new ArrayList<>();
	}
	
	//Extracts artist information from playlist
	public ArrayList<String> extractPlaylist(Document doc){
		Elements artistTable = doc.select("td.views-field.views-field-artist");
		for(Element artist : artistTable) {
			   artists.add(artist.html());
		}
		return artists;
	}
	
	//combines the playlists into one huge ArrayList
	//the totalPlaylists Arraylist contains all of the artists of
	//the playlists from one specific department
	public void addPlaylists(ArrayList<String> list){
		for (int i = 0; i < list.size();i++){
			totalPlaylists.add(list.get(i));
		}
	}
	
	public ArrayList<String> getLists(){
		return totalPlaylists;
	}
	
	//generates the counts the plays per artist in total playlists
	public void playCount(){
		System.out.println(totalPlaylists);
		Map<String, Integer> occurences = new HashMap<String, Integer>();
		for (String artist : totalPlaylists){
			occurences.put(artist, occurences.containsKey(artist)
			? occurences.get(artist) + 1 : 1);
		}
		for (Entry<String, Integer> entry : occurences.entrySet()){
			finalArtist.add(entry.getKey());
			finalCount.add(entry.getValue());
		}
	}
	
	public ArrayList<String> getFinalArtist(){
		return finalArtist;
	}
	
	public ArrayList<Integer> getFinalCount(){
		return finalCount;
	}
}
