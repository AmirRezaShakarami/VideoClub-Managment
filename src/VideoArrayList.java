import java.util.ArrayList;
import java.util.Iterator;

public class VideoArrayList {
	private static ArrayList<Video> videoList = new ArrayList<Video>();
	private VideoArrayList() {}
	public static ArrayList<Video> getVideoList() {
		return videoList;
	}
	
	public static void deleteVideo(long inputID) {
		
		Iterator<Video> iterator = videoList.iterator();
		
		while (iterator.hasNext()) {
			long VideoID = iterator.next().getId();
			
			if (inputID == VideoID)
				iterator.remove();
		}
		System.out.println("@RoboClub:\n Video Deleted");
	}
}
