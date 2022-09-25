import java.util.Objects;

public class Video {
	private String name;
	private String director;
	//popular (Actors/Actresses) of movie
	private String stars;
	private int releaseYear;
	// rate of movie base of imdb.com website
	private short imdbRate;
	private String genre;
	private long id;
	private types type;
	enum types {
		Short_Movie,Long_movie,Series
	}
	private rentSituations rentSituation = rentSituations.Not_rented;
	public Member membWhoRented;
	enum rentSituations {
		Rented,Not_rented
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setDirector(String director) {
		this.director = director;
	}
	public String getDirector() {
		return director;
	}
	public void setStars(String stars) {
		this.stars = stars;
	}
	public String getStars() {
		return stars;
	}
	public void setType(int typeChoice) {
		
		switch (typeChoice) {
		case 1:
			this.type = types.Short_Movie;
			break;
		case 2:
			this.type = types.Long_movie;
			break;
		case 3:
			this.type = types.Series;
			break;
		}
	}
	public void setTypeForEditingMethod(types type) {
		this.type = type;
	}
	public types getType() {
		return type;
	}
	public void setGenre(short index) {
		String[] genres = {"Action","Adventure","Animation","Biografy","Comedy"
							,"Crime","Drama","Documentary","Family","Fantasy"
							,"History","Horror","Musical","Mystry","Romance"
							,"Sci_fi","Sport","War","Western"};
		for (short i=0; i<genres.length; i++) {
			if(index == i)
				this.genre = genres[i];
		}
	}
	public void setGenreForEditingMethod(String genre ) {
		this.genre = genre;
	}
	public String getGenre() {
		return genre;
	}
	public boolean setIMDbRate(short imdbRate) {
		if (imdbRate < 1 || imdbRate >10) {
			System.out.println("@RoboClub:\n Ops! Please input a (Valid) number ");
			return false;
		}
		else {
			this.imdbRate = imdbRate;
			return true;
		}
	}
	public short getIMDbRate() {
		return imdbRate;
	}
	public void setId() {
		long ID = hashCode();
		this.id = ID;
	}
	public long getId() {
		return id;
	}
	public boolean setReleaseYear(int releaseYear) {
		if(releaseYear<1840 || releaseYear>2020) {
			System.out.println("@RoboClub:\n Must be between 1840 - 2020");
			return false;
		}
		else
			this.releaseYear = releaseYear;
		return true;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setRentSituation(char situation) {
		if(situation == 'R')
			this.rentSituation = rentSituations.Rented;
		if(situation == 'N')
			this.rentSituation = rentSituations.Not_rented;
	}
	public rentSituations getRentSituation() {
		return rentSituation;
	}
	public boolean checkRentSituation () {
		if (this.rentSituation == rentSituations.Rented)
			return true;
		else
			return false;
	}
	public void setMemberWhoRent (long ID) {
		for (Member member : MemberArrayList.getMemberList()) {
			if (ID == member.getId())
				this.membWhoRented = member;
		}
	}
	public void printMemberWhoRent() {
		if (rentSituation == rentSituations.Rented) {
			System.out.print(" by "+membWhoRented.getFullName());
			System.out.print("("+membWhoRented.getId()+")");
		}
	}
	public Member getMemberWhoRented() {
		return this.membWhoRented;
	}
	public void getFullInfo() {
		System.out.println("  • ——————————————————————————————————————————————————————————————————————————————————————————— •");
		System.out.println("  | Name: "+ name.toUpperCase());
		System.out.println("  | Type: "+ type + "		Director: "+ director);
		System.out.println("  | Genre: "+ genre + "		Stars: "+ stars);
		System.out.println("  | IMDb rate: "+ imdbRate +"/10" + "		Release: "+ releaseYear);
		System.out.print("  | Situation: "+ rentSituation); printMemberWhoRent(); System.out.print("\n");
		System.out.println("  | ID: ("+ id +')');
		System.out.println("  • ——————————————————————————————————————————————————————————————————————————————————————————— •");
	}
	@Override //by Eclipse IDE generation tools
	public int hashCode() {
		return Objects.hash(director, genre, imdbRate, name, releaseYear, rentSituation, stars, type);
	}
	@Override //by Eclipse IDE generation tools
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		return Objects.equals(director, other.director) && Objects.equals(genre, other.genre)
				&& imdbRate == other.imdbRate && Objects.equals(name, other.name) && releaseYear == other.releaseYear
				&& rentSituation == other.rentSituation && Objects.equals(stars, other.stars) && type == other.type;
	}
	
}
