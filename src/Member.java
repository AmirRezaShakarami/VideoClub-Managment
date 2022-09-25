import java.util.Objects;

public class Member {
	private String firstName;
	private String lastName;
	private String fullName;
	private short age;
	private char gender;
	private Video rentedVideo = null;
	private Long id;
	
	public void setId() {
		this.id = (long) hashCode();
	}
	public Long getId() {
		return id;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getFirstName() {
		return firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getFullName() {
		fullName = this.firstName +" "+ this.lastName;
		return fullName;
	}
	
	public boolean setAge(short age) {
		/*base of highest Life expectancy in the World according to:
		  https://ourworldindata.org/life-expectancy
		*/
		if(age<7 || age>85) {
			System.out.println("@RoboClub: Seriously? "+ age +" years old?! input real age");
			return false;
		}
		else
			this.age = age;
		return true;
	}
	public short getAge() {
		return age;
	}
	
	public boolean setGender(Character gender) {
		char Male = 'M';
		char Female = 'F';
		if (gender.equals(Male) || gender.equals(Female)) {
			this.gender = gender;
			return true;
		}
		else
			System.out.println("@RoboClub: Enter a valid gender");
		return false;
	}
	public char getGender() {
		return gender;
	}
	public void setRentedVideo(Video rentedVideo) {
		this.rentedVideo = rentedVideo;
	}
	public void printRentedVideo() {
		if (rentedVideo == null)
			System.out.println("Nothing yet");
		else
			System.out.println(rentedVideo.getName()+"("+rentedVideo.getId()+")");
	}
	public Video getRentedVideo() {
		return rentedVideo;
	}
	public void getFullInfo() {
		System.out.println("  • —————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————— •");
		System.out.print("  | FullName: "+ getFullName());
		System.out.print("   Age: "+ age);
		System.out.print("   Gender: "+ gender);
		System.out.print("   ID: ("+ id +")");
		System.out.print("   Rented Video: ");printRentedVideo();
		System.out.println("  • —————————————————————————————————————————————————————————————————————————————————————————————————————————————————————————— •");
	}
	@Override
	public int hashCode() {
		return Objects.hash(age, firstName, fullName, gender, lastName, rentedVideo);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Member other = (Member) obj;
		return age == other.age && Objects.equals(firstName, other.firstName)
				&& Objects.equals(fullName, other.fullName) && gender == other.gender
				&& Objects.equals(lastName, other.lastName) && Objects.equals(rentedVideo, other.rentedVideo);
	}
	

}