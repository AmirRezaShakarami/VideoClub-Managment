
import java.util.Iterator;
import java.util.ArrayList;
// using singleton
public class MemberArrayList {
	private static ArrayList<Member> memberList = new ArrayList<Member>();
	private MemberArrayList() {}
	public static ArrayList<Member> getMemberList() {
		return memberList;
	}

	/*
	 * I tried to use for-each method to deleteMembers
	 * But this error popped up
	 * error -->"Exception in thread "main" java.util.ConcurrentModificationException
				at java.base/java.util.ArrayList$Itr.checkForComodification(ArrayList.java:1012)
				at java.base/java.util.ArrayList$Itr.next(ArrayList.java:966)
				
		then I searched about it and find the solution in using Iterator instead of for-each
	 */
	public static void deleteMember(long inputID) {
		
		Iterator<Member> iterator = memberList.iterator();
		
		while (iterator.hasNext()) {
			long memberID = iterator.next().getId();
			
			if (inputID == memberID)
				iterator.remove();
		}
		System.out.println("@RoboClub:\n Member deleted");
	}
}
