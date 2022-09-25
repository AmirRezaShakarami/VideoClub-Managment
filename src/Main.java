
import java.util.Scanner;

public class Main {
	@SuppressWarnings("unused")
	private static String garbage;

	public static void main(String[] args) {
		System.out.print("Hello Boss!\n My name is ( RoboClub )");
		System.out.println("\n I'm here to help You with managing your Video Club");
		System.out.println(" Hope we will have a good experience together");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("\n@RoboClub:\n What can i do for you?");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//passing Scanner to methods to avoid leaking of Heap memory
		//I tried scanner.close() method but always some errors happened
		Scanner scanner = new Scanner(System.in);
		mainMenuMethod(scanner);
	}
	
	public static void mainMenuMethod(Scanner scanner) {
		
		
		short menuCommand;
		boolean loopToMainMenu = true;
		boolean wrongCommand = false;
		
		do {
			System.out.println("\n\t_Main Menu_____________________");
			System.out.println("\t 1.Members management");
			System.out.println("\t 2.Videos management");
			System.out.println("\t 3.Renting managment");
			System.out.println("\t 0.Exit");
			System.out.println("\n@RoboClub:\n Input a number & hit (Enter) key");
			System.out.print(" Input: ");
			// safe input
			do {
				while (! scanner.hasNextShort()) {
					// wrong commands collection
					garbage = scanner.nextLine();
					System.out.println(" Ops! you must input a (Number)");
					System.out.print(" Input: ");
				}
				menuCommand = scanner.nextShort();
				garbage = scanner.nextLine();
				if(menuCommand<0 || menuCommand>3) {
					wrongCommand = true;
					System.out.println(" Ops! input a (Valid) number");
					System.out.print(" Input: ");
				}
				else
					wrongCommand = false;	
			}while(wrongCommand==true);
			System.out.println("———————————————————————————————————————————————————————————");
			switch(menuCommand) {
			case 1:
				memberManagmentMethod(scanner);
				break;
			case 2:
				videosManagmentMethod(scanner);
				break;
			case 3:
				rentingManagmentMethod(scanner);
				break;
			case 0:
				System.out.println("@RoboClub:\n Hope you back soon :-)");
				loopToMainMenu = false;
				break;
				// I used to put safe input method here but then I moved it up to switch-case  
			default:
				break;
			}
		} while( loopToMainMenu==true );
	}
	
	public static void memberManagmentMethod(Scanner scanner) {
		scanner.reset();
		short menuCommand;
		boolean loopToMemberManagmentMenu = true;
		boolean wrongCommand = false;
		
		do {
			System.out.println("\n\t_Members Managment____________________");
			System.out.println("\t 1.Add a new member");
			System.out.println("\t 2.Search members");
			System.out.println("\t 3.Edit a profile");
			System.out.println("\t 4.Delete a profile");
			System.out.println("\t 0.Return to MainMenu");
			System.out.print(" Input: ");
			do {
				while (! scanner.hasNextShort()) {
					garbage = scanner.nextLine();
					System.out.print(" Ops! you must input a ( Number ): ");
				}
				menuCommand = scanner.nextShort();
				garbage = scanner.nextLine();
				if(menuCommand<0 || menuCommand>4) {
					wrongCommand = true;
					System.out.print(" Ops! input a ( Valid ) number: ");
				}
				else
					wrongCommand = false;	
			}while(wrongCommand==true);
			System.out.println("———————————————————————————————————————————————————————————");
			switch(menuCommand) {
			case 1:
				addMemberMethod(scanner);
				break;
			case 2:
				searchMemberMethod(scanner);
				break;
			case 3:
				editMemberMethod(scanner);
				break;
			case 4:
				deleteMemberMethod(scanner);
				break;
			case 0:
				loopToMemberManagmentMenu = false;
				break;
			}
			
		} while(loopToMemberManagmentMenu == true);
	}
	
	public static void addMemberMethod(Scanner scanner) {
		scanner.reset();
		
		Member member = new Member();
		/*set first name .nextLine for inputing names with spaces 
		 * .toLowerCase for using .equals in searchByNameMethod
		 */
		System.out.print(" Enter FirstName: ");
		member.setFirstName(scanner.nextLine().toLowerCase());
		//set last name
		System.out.print(" Enter LastName: ");
		member.setLastName(scanner.nextLine().toLowerCase());
		//set age
		short age;
		do {
			System.out.print(" Enter age: ");
			//safe input
			while (! scanner.hasNextShort()) {
				garbage = scanner.nextLine();
				System.out.print("@RoboClub:\n Ops! must input a number: ");
			}
			age = scanner.nextShort();
			garbage = scanner.nextLine();
		} while ( member.setAge(age) == false );
		//set gender
		Character gender;
		do {
			System.out.print(" Enter gender(M/F): ");
			gender = scanner.next().toUpperCase().charAt(0);
		} while ( member.setGender(gender) == false );
		System.out.println("———————————————————————————————————————————————————————————");
		//Member Equality Check
		boolean memberEqualityCheck = false;
		for (Member previousMembers : MemberArrayList.getMemberList()) {
			
			if (member.equals(previousMembers)) {
				memberEqualityCheck = true;
				System.out.println("\n@RoboClub:\n This User already exist !");
				previousMembers.getFullInfo();
			}
		}
		if(memberEqualityCheck==false) {
			member.setId();
			MemberArrayList.getMemberList().add(member);		
			System.out.println("\n@RoboClub:\n Member added successfully");
			if(MemberArrayList.getMemberList().indexOf(member) == 0)
				System.out.println(" Congrats! for your first Club Member :-)");
			System.out.println(" Member's #ID: ("+ member.getId() +")");
			System.out.println("\n\t      !!WARNING!!\n #ID Will be need for future interactions");
		}
	}

	public static void searchMemberMethod (Scanner scanner) {
		scanner.reset();
		short menuCommand;
		boolean wrongCommand;
		boolean loopToMemberDetailMenu = true;
		
		do {
			System.out.println("\n\t_Search Members_____________________");
			System.out.println("\t 1.Search by ID");
			System.out.println("\t 2.Search by Name");
			System.out.println("\t 3.Show all members");
			System.out.println("\t 4.Show number of All members");
			System.out.println("\t 0.Return to Members Managment Menu");	
			System.out.print(" Input: ");
			do {
				while (! scanner.hasNextShort()) {
					garbage = scanner.nextLine();
					System.out.print(" Ops! you must input a ( Number ): ");
				}
				menuCommand = scanner.nextShort();
				garbage = scanner.nextLine();
				if(menuCommand<0 || menuCommand>4) {
					wrongCommand = true;
					System.out.print(" Ops! input a ( Valid ) number: ");
				}
				else
					wrongCommand = false;	
			}while(wrongCommand==true);
			System.out.println("———————————————————————————————————————————————————————————");
			switch(menuCommand) {
				//1.search by ID
			case 1:
				System.out.print(" Enter member's ID: ");
				while (! scanner.hasNextLong()) {
					garbage = scanner.nextLine();
					System.out.print("@RoboClub:\n\t Ops! must input a number:");
				}
				long inputID = scanner.nextLong();
				garbage = scanner.nextLine();
				memberSearchByIdMethod(inputID);
				break;
				//2.search by name
			case 2:
				System.out.print(" Enter member's First or Last name: ");
				String inputName = scanner.nextLine().toLowerCase();
				String membersName;
				boolean nameCheck = false;
				int j=0;
				for (Member member : MemberArrayList.getMemberList()) {
					membersName = member.getFullName();
					if (membersName.contains(inputName)) {
						nameCheck = true;
						System.out.println(" ("+ (++j) + ')');
						member.getFullInfo();
					}
					
				}
				if (nameCheck == false)
					System.out.println("@RoboClub:\n No such name exist");
				break;
			case 3:
				if (MemberArrayList.getMemberList().isEmpty())
					System.out.println("@RoboClub:\n No member exist");
				else {
					int i=0;
					for (Member member : MemberArrayList.getMemberList()) {
						System.out.println(" ("+ (++i) + ')');
						member.getFullInfo();
					}	
				}
				break;
			case 4:
				System.out.println("@RoboClub:\n There is ( "+ MemberArrayList.getMemberList().size() +" ) Members ");
				break;
			case 0:
				loopToMemberDetailMenu = false;
				break;
			default:
				System.out.println("@RoboClub:\n\t Ops! input a valid number");
			}
			
		} while(loopToMemberDetailMenu == true);
	}
	
	public static boolean memberSearchByIdMethod(long inputID) {
		
		long membersID;
		boolean idCheck = false;
		
		for (Member member : MemberArrayList.getMemberList()) {
			membersID = member.getId();
			if (inputID == membersID) {
				idCheck = true;
				member.getFullInfo();
			}
		}
		if (idCheck == false)
			System.out.println("@RoboClub:\n No such ID exist");
		
		return idCheck;
	}
	
	public static void editMemberMethod(Scanner scanner) {
		scanner.reset();
		
		System.out.print(" Enter member's ID: ");
		while(! scanner.hasNextLong()) {
			garbage = scanner.nextLine();
			System.out.println("Ops! You must input a (Number):");
		}
		long inputID = scanner.nextLong();
		garbage = scanner.nextLine();
		
		boolean idCheck = memberSearchByIdMethod(inputID);
		
		if (idCheck == true) {
			
			for (Member member : MemberArrayList.getMemberList()) {
				
				if (inputID == member.getId()) {
					Member memberCopy = copyOfMember(inputID);
					short menuCommand;
					boolean wrongCommand;
					boolean loopToEditProfileMenu = true;
					do {
						System.out.println("\n\t_Edit Profile_____________________");
						System.out.println("\t 1.Edit Firstname");
						System.out.println("\t 2.Edit Lastname");
						System.out.println("\t 3.Edit age");
						System.out.println("\t 4.Edit gender");
						System.out.println("\t 5.Edit all informations");
						System.out.println("\t 0.return to Members Management Menu");
						System.out.print(" Input: ");
						do {
							while (! scanner.hasNextShort()) {
								garbage = scanner.nextLine();
								System.out.print(" Ops! you must input a ( Number ): ");
							}
							menuCommand = scanner.nextShort();
							garbage = scanner.nextLine();
							if(menuCommand<0 || menuCommand>5) {
								wrongCommand = true;
								System.out.print(" Ops! input a ( Valid ) number: ");
							}
							else
								wrongCommand = false;	
						}while(wrongCommand==true);
						System.out.println("———————————————————————————————————————————————————————————");
						switch (menuCommand) {
						case 1:
							System.out.print(" Enter Firstname: ");
							memberCopy.setFirstName(scanner.nextLine().toLowerCase());
							memberCopy.setId();
							boolean memberEqualityCheck = false;
							for (Member previousMember : MemberArrayList.getMemberList()) {
								if (memberCopy.equals(previousMember)) {
									memberEqualityCheck = true;
									System.out.println("\\n@RoboClub:\\n This Member already exist !");
									previousMember.getFullInfo();
								}
							}
							if (memberEqualityCheck == false) {
								member.setFirstName(memberCopy.getFirstName());
								member.setId();
								System.out.println("@RoboClub:\n Edited Successfully");
								System.out.println(" New #ID: ("+ member.getId() +")");
								member.getFullInfo();
							}
							break;
						case 2:
							System.out.print(" Enter Lastname: ");
							memberCopy.setLastName(scanner.nextLine().toLowerCase());
							memberCopy.setId();
							memberEqualityCheck = false;
							for (Member previousMember : MemberArrayList.getMemberList()) {
								if (memberCopy.equals(previousMember)) {
									memberEqualityCheck = true;
									System.out.println("\\n@RoboClub:\\n This Member already exist !");
									previousMember.getFullInfo();
								}
							}
							if (memberEqualityCheck == false) {
								member.setLastName(memberCopy.getLastName());
								member.setId();
								System.out.println("@RoboClub:\n Edited Successfully");
								System.out.println(" New #ID: ("+ member.getId() +")");
								member.getFullInfo();
							}
							break;
						case 3:
							memberEqualityCheck = false;
							while (! scanner.hasNextShort()) {
								garbage = scanner.nextLine();
								System.out.print("@RoboClub:\n Ops! must input a number:");
							}
							short inputAge;
							do {
								System.out.print(" Enter Age number: ");
								inputAge = scanner.nextShort();
								garbage = scanner.nextLine();
							} while (memberCopy.setAge(inputAge) == false);
							for (Member previousMember : MemberArrayList.getMemberList()) {
								if (memberCopy.equals(previousMember)) {
									memberEqualityCheck = true;
									System.out.println("\\n@RoboClub:\\n This Member already exist !");
									previousMember.getFullInfo();
								}
							}
							if (memberEqualityCheck == false) {
								member.setAge(memberCopy.getAge());
								member.setId();
								System.out.println("@RoboClub:\n Edited Successfully");
								System.out.println(" New #ID: ("+ member.getId() +")");
								member.getFullInfo();
							}
							break;
						case 4:
							memberEqualityCheck = false;
							char inputGender;
							do {
								System.out.print(" Enter Gender(M/F): ");
								inputGender = scanner.next().toUpperCase().charAt(0);
							} while (memberCopy.setGender(inputGender) == false);
							for (Member previousMember : MemberArrayList.getMemberList()) {
								if (memberCopy.equals(previousMember)) {
									memberEqualityCheck = true;
									System.out.println("\\n@RoboClub:\\n This Member already exist !");
									previousMember.getFullInfo();
								}
							}
							if (memberEqualityCheck == false) {
								member.setGender(memberCopy.getGender());
								member.setId();
								System.out.println("@RoboClub:\n Edited Successfully");
								System.out.println(" New #ID: ("+ member.getId() +")");
								member.getFullInfo();
							}
							break;
						case 5:
							memberEqualityCheck = false;
							System.out.print(" Enter Firstname: ");
							memberCopy.setFirstName(scanner.nextLine().toLowerCase());
							System.out.print(" Enter Lastname: ");
							memberCopy.setLastName(scanner.nextLine().toLowerCase());
							
							System.out.print(" Enter Age number: ");
							while (! scanner.hasNextShort()) {
								garbage = scanner.nextLine();
								System.out.print("@RoboClub:\n\t Ops! must input a number:");
							}
							do {
								inputAge = scanner.nextShort();
								garbage = scanner.nextLine();
							} while (memberCopy.setAge(inputAge) == false);
							
							do {
								System.out.print(" Enter Gender(M/F): ");
								inputGender = scanner.next().toUpperCase().charAt(0);
							} while (memberCopy.setGender(inputGender) == false);
							
							for (Member previousMember : MemberArrayList.getMemberList()) {
								if (memberCopy.equals(previousMember)) {
									memberEqualityCheck = true;
									System.out.println("\\n@RoboClub:\\n This Member already exist !");
									previousMember.getFullInfo();
								}
							}
							if (memberEqualityCheck == false) {
								member.setId();
								System.out.println("@RoboClub:\n Edited Successfully");
								System.out.println(" New #ID: ("+ member.getId() +")");
								member.getFullInfo();
							}
							break;
						case 0:
							loopToEditProfileMenu = false;
							break;
						default:
							System.out.println("@RoboClub:\n Ops! input a valid number");
						}
					} while(loopToEditProfileMenu == true);
				}
			}	
		}
	}
	
	public static void deleteMemberMethod(Scanner scanner) {
		scanner.reset();
		
		System.out.print(" Enter member's ID: ");
		while (! scanner.hasNextLong()) {
			garbage = scanner.nextLine();
			System.out.print(" Ops! you must input a (Number): ");
		}
		long inputID = scanner.nextLong();
		garbage = scanner.nextLine();
		
		boolean idCheck = memberSearchByIdMethod(inputID);
		boolean rentingCheck = false;
		for (Member member : MemberArrayList.getMemberList()) {
			if (inputID == member.getId())
				if(!(member.getRentedVideo()==null)) {
					System.out.println("\n@RoboClub:\n This member has a Rented Video, Must return it first");
					rentingCheck = true;
				}
		}
		if (idCheck == true && rentingCheck == false) {
			String command;
			do {
				System.out.print(" Are you sure want to delete this member!? (y/n): ");
				command = scanner.next().toLowerCase();
			}while(!(command.charAt(0) == 'y') || (command.charAt(0) == 'n'));
			if (command.charAt(0) == 'y')
				MemberArrayList.deleteMember(inputID);
			else
				System.out.println("delete Action Canceled");
		}
	}

	public static void videosManagmentMethod(Scanner scanner) {
		scanner.reset();
		short menuCommand;
		boolean wrongCommand;
		boolean loopToFilmManagmentMethod = true;

		do {
			System.out.println("\n\t_Video Managmet_________________________");
			System.out.println("\t 1.Add a Video");
			System.out.println("\t 2.Search Videos");
			System.out.println("\t 3.Edit a Video's info");
			System.out.println("\t 4.Delete a Video");
			System.out.println("\t 0.Return to mainMenu");
			System.out.print(" Input: ");
			do {
				while (! scanner.hasNextShort()) {
					garbage = scanner.nextLine();
					System.out.print("@RoboClub: Ops! you must input a (Number): ");
				}
				menuCommand = scanner.nextShort();
				garbage = scanner.nextLine();
				if(menuCommand<0 || menuCommand>4) {
					wrongCommand = true;
					System.out.print("@RoboClub: Ops! input a (Valid) number: ");
				}
				else
					wrongCommand = false;	
			}while(wrongCommand==true);
			System.out.println("———————————————————————————————————————————————————————————");
			switch(menuCommand) {
			case 1:
				addVideoMethod(scanner);
				break;
			case 2:
				searchVideoMethod(scanner);
				break;
			case 3:
				editVideoMethod(scanner);
				break;
			case 4:
				deleteVidoeMethod(scanner);
				break;
			case 0:
				loopToFilmManagmentMethod = false;
				break;
			}
			
		} while(loopToFilmManagmentMethod == true);
		
	}
	
	public static void addVideoMethod(Scanner scanner) {
		scanner.reset();
		
		Video video = new Video();
		//set Name
		System.out.print(" Enter Video name: ");
		video.setName(scanner.nextLine().toLowerCase());
		//set DirectorName
		System.out.print(" Enter Video's Director name: ");
		video.setDirector(scanner.nextLine().toLowerCase());
		//set Stars names
		System.out.print(" Enter Video's Super Stars name: ");
		video.setStars(scanner.nextLine().toLowerCase());
		
		//set Type
		boolean typeCheck;
		boolean wrongCommand;
		short typeChoice;
		do {	
			typeCheck = true;		
			System.out.println(" Choose Type from: ");
			System.out.println("  1.Short-Movie   2.Long-Movie   3.Serial");
			do {
				while (! scanner.hasNextShort()) {
					garbage = scanner.nextLine();
					System.out.print(" Ops! you must input a (Number) ");
				}
				typeChoice = scanner.nextShort();
				garbage = scanner.nextLine();
				if(typeChoice<1 || typeChoice>3) {
					wrongCommand = true;
					System.out.print(" Ops! input a (Valid) number ");
				}
				else
					wrongCommand = false;	
			}while(wrongCommand==true);
			switch(typeChoice) {
			
			case 1:
				video.setType(1);
				break;
			case 2:
				video.setType(2);
				break;
			case 3:
				video.setType(3);
				break;
			default:
				System.out.println("@RoboClub:\n\t Ops! Please input a number from 1 to 3: ");
				typeCheck = false;
			}
		} while (typeCheck == false);
		//set Genre
		boolean genreIndexCheck;
		short index=0;
		do {	
			System.out.println(" Choose Genre from :");
			System.out.println("  1.Action\t2.Adventure\t3.Animation\t4.Biografy");
			System.out.println("  5.Comedy\t6.Crime\t"+"\t7.Drama"+"\t\t8.Documentary");
			System.out.println("  9.Family\t10.Fantasy\t11.History\t12.Horror"); 
			System.out.println("  13.Musical\t14.Mystry\t15.Romance\t16.Sci_fi");
			System.out.println("  17.Sport\t18.War\t\t19.Western");
			
			while (! scanner.hasNextShort()) {
				garbage = scanner.nextLine();
				System.out.print("@RoboClub:\n\t Ops! must input a Number: ");
			}
			short genreChoice = scanner.nextShort();
			garbage = scanner.nextLine();
			if (genreChoice<1 || genreChoice>19) {
				System.out.println("@RoboClub:\n Ops! Please input a Number from 1 to 19: ");
				genreIndexCheck = false;
			}
			else {
				index = --genreChoice;
				genreIndexCheck = true;
			}
		} while (genreIndexCheck == false);
		video.setGenre(index);
		//set IMDb rate
		short rateInput;
		do {
			System.out.print(" Type Movie's IMDb rate from 1 to 10: ");
			while ( !scanner.hasNextShort() ) {
				garbage = scanner.nextLine();
				System.out.print("@RoboClub:\n Ops! must input a Number: ");
			}
			rateInput = scanner.nextShort();
			garbage = scanner.nextLine();
			}while (video.setIMDbRate(rateInput) == false);
		
		//set Release Year
		int rYear;
		do {
			System.out.print(" Enter Release Year: ");
			while (! scanner.hasNextInt()) {
				garbage = scanner.nextLine();
				System.out.print("\n Ops! You must input a ( Number ): ");
			}
			rYear = scanner.nextInt();
			garbage = scanner.nextLine();
		} while ( video.setReleaseYear(rYear) == false );
		System.out.println("———————————————————————————————————————————————————————————");
		//set ID
		video.setId();
		//Video Equality Check
		boolean videoEqualityCheck = false;
		for (Video previousVideos : VideoArrayList.getVideoList()) {
			if (video.equals(previousVideos)) {
				videoEqualityCheck = true;
				System.out.println("\n@RoboClub:\n This Video already exist !");
				previousVideos.getFullInfo();
			}
		}
		//add to Video ArrayList
		if (videoEqualityCheck == false) {
			VideoArrayList.getVideoList().add(video);
			System.out.println("\n@RoboClub:\n Video added successfully");
			System.out.println(" Video's #ID:("+ video.getId() +")");
			System.out.println("\n\t      !!WARNING!!\n #ID Will be need for future interactions");
		}

	}
	
	public static void searchVideoMethod(Scanner scanner) {
		scanner.reset();
		short menuCommand;
		boolean wrongCommand;
		boolean loopToVideoDetailMenu = true;
		
		do {
			System.out.println("\n\t_Search Videos_____________________");
			System.out.println("\t 1.Search by ID");
			System.out.println("\t 2.Search by Video Name");
			System.out.println("\t 3.Search by Director");
			System.out.println("\t 4.Search by Super Stars");
			System.out.println("\t 5.Show all Videos");
			System.out.println("\t 6.Show number of All Videos");
			System.out.println("\t 0.Return to Video Managment Menu");
			System.out.print(" Input: ");
			do {
				while (! scanner.hasNextShort()) {
					garbage = scanner.nextLine();
					System.out.print("@RoboClub:\n Ops! you must input a (Number): ");
				}
				menuCommand = scanner.nextShort();
				garbage = scanner.nextLine();
				if(menuCommand<0 || menuCommand>6) {
					wrongCommand = true;
					System.out.print("@RoboClub:\n Ops! input a (Valid) number: ");
				}
				else
					wrongCommand = false;	
			}while(wrongCommand==true);
			System.out.println("———————————————————————————————————————————————————————————");
			switch(menuCommand) {
				//1.search by ID
			case 1:
				System.out.print(" Enter Video's ID: ");
				while (! scanner.hasNextLong()) {
					garbage = scanner.nextLine();
					System.out.println("@RoboClub:\n Ops! must input a (Number):");
				}
				long inputID = scanner.nextLong();
				garbage = scanner.nextLine();
				videoSearchByIdMethod(inputID);
				break;
				//2.search by name
			case 2:
				System.out.print(" Enter Video's name: ");
				String inputName = scanner.nextLine().toLowerCase();
				String VideosName;
				int i=0;
				for (Video video : VideoArrayList.getVideoList()) {
					VideosName = video.getName();
					if (VideosName.contains(inputName)) {
						System.out.println(" ("+ (++i) + ')');
						video.getFullInfo();
					}
					else
						System.out.println("@RoboClub:\n No such name exist");
				}
				break;
				//3.search by director
			case 3:
				System.out.print(" Enter Director name: ");
				String inputDirectorName = scanner.nextLine().toLowerCase();
				String directorName;
				int x=0;
				for (Video video : VideoArrayList.getVideoList()) {
					directorName = video.getDirector();
					if (directorName.contains(inputDirectorName)) {
						System.out.println(" ("+ (++x) + ')');
						video.getFullInfo();
					}
					else
						System.out.println("@RoboClub:\n No such name exist");
				}
				break;
				//4.search by stars
			case 4:
				System.out.print(" Enter Stars names: ");
				String inputStarsName = scanner.nextLine().toLowerCase();
				String StarsName;
				int z=0;
				for (Video video : VideoArrayList.getVideoList()) {
					StarsName = video.getStars();
					if (StarsName.contains(inputStarsName)) {
						System.out.println(" ("+ (++z) + ')');
						video.getFullInfo();
					}
					else
						System.out.println("@RoboClub:\n No such names exist");
				}
				break;
			case 5:
				if (VideoArrayList.getVideoList().isEmpty())
					System.out.println("@RoboClub:\n No Video exist");
				else {
					int j=0;
					for (Video video : VideoArrayList.getVideoList()) {
						System.out.println(" ("+ (++j) + ')');
						video.getFullInfo();
					}
				}
				break;
			case 6:
				System.out.println("@RoboClub:\n There is ("+ VideoArrayList.getVideoList().size() +") Videos");
				break;
			case 0:
				loopToVideoDetailMenu = false;
				break;
			default:
				System.out.println("@RoboClub:\n Ops! input a (Valid) number");
			}
			
		} while(loopToVideoDetailMenu == true);
		
	}

	public static boolean videoSearchByIdMethod(long inputID) {
		long videosID;
		boolean idCheck = false;
		
		for (Video video : VideoArrayList.getVideoList()) {
			videosID = video.getId() ;
			if (inputID == videosID) {
				idCheck = true;
				video.getFullInfo();
			}
		}
		if (idCheck == false)
			System.out.println("@RoboClub:\n No such ID exist");
		
		return idCheck;
	}
	
	public static void editVideoMethod(Scanner scanner) {
		scanner.reset();
		
		System.out.print(" Enter Video's ID: ");
		while(! scanner.hasNextLong()) {
			garbage = scanner.nextLine();
			System.out.println("@RoboClub:\n Ops! You must input a (Number):");
		}
		long inputID = scanner.nextLong();
		garbage = scanner.nextLine();
		
		boolean idCheck = videoSearchByIdMethod(inputID);
		
		if (idCheck == true) {
			
			for (Video video : VideoArrayList.getVideoList()) {
				
				if (inputID==video.getId()) {
					// make a copy of object to make changes on it
					Video videoCopy = copyOfVideo(inputID);
					short menuCommand;
					boolean wrongCommand;
					boolean loopToEditVideoMenu = true;
					do {
						System.out.println("\n\t_Edit Video_____________________");
						System.out.println("\t 1.Edit Name");
						System.out.println("\t 2.Edit Director");
						System.out.println("\t 3.Edit Stars");
						System.out.println("\t 4.Edit Type");
						System.out.println("\t 5.Edit Genre");
						System.out.println("\t 6.Edit Release Year");
						System.out.println("\t 7.Edit IMDb Rate");
						System.out.println("\t 8.Edit All info");
						System.out.println("\t 0.return to Video Management Menu");
						System.out.print(" Input: ");
						do {
							while (! scanner.hasNextShort()) {
								garbage = scanner.nextLine();
								System.out.print("@RoboClub: Ops! you must input a (Number): ");
							}
							menuCommand = scanner.nextShort();
							garbage = scanner.nextLine();
							if(menuCommand<0 || menuCommand>8) {
								wrongCommand = true;
								System.out.print("@RoboClub: Ops! input a (Valid) number: ");
							}
							else
								wrongCommand = false;	
						}while(wrongCommand==true);
						System.out.println("———————————————————————————————————————————————————————————");
						switch (menuCommand) {
						case 1:
							System.out.print(" Enter new Name: ");
							videoCopy.setName(scanner.nextLine().toLowerCase());
							videoCopy.setId();
							//Video Equality Check
							boolean videoEqualityCheck = false;
							for (Video previousVideo : VideoArrayList.getVideoList()) {
								if (videoCopy.equals(previousVideo)) {
									videoEqualityCheck = true;
									System.out.println("\n@RoboClub:\n This Video already exist !");
									previousVideo.getFullInfo();
								}
							}
							if (videoEqualityCheck == false) {
								// if you use video = videoCopy you must remove video and add newOne in ArrayList
								// that means ArrayList must handle a lot of copies and pushes objects so there's another way
								video.setName(videoCopy.getName());
								video.setId();
								System.out.println("@RoboClub:\n Edited Successfully");
								System.out.println(" New #ID: ("+ video.getId() +")");
								video.getFullInfo();
							}
							break;
						case 2:
							System.out.print(" Enter new Director's Name: ");
							videoCopy.setDirector(scanner.nextLine().toLowerCase());
							videoCopy.setId();
							videoEqualityCheck = false;
							for (Video previousVideos : VideoArrayList.getVideoList()) {
								if (videoCopy.equals(previousVideos)) {
									videoEqualityCheck = true;
									System.out.println("\n@RoboClub:\n This Video already exist !");
									previousVideos.getFullInfo();
								}
							}
							if (videoEqualityCheck == false) {
								video.setDirector(videoCopy.getDirector());
								video.setId();
								System.out.println("@RoboClub:\n Edited Successfuly");
								System.out.println(" New #ID: ("+ video.getId() +")");
								video.getFullInfo();
							}
							break;
						case 3:
							System.out.print(" Enter New Video's Stars names: ");
							videoCopy.setStars(scanner.nextLine().toLowerCase());
							videoCopy.setId();
							videoEqualityCheck = false;
							for (Video previousVideos : VideoArrayList.getVideoList()) {
								if (videoCopy.equals(previousVideos)) {
									videoEqualityCheck = true;
									System.out.println("\n@RoboClub:\n This Video already exist !");
									previousVideos.getFullInfo();
								}
							}
							if (videoEqualityCheck == false) {
								video.setStars(videoCopy.getStars());
								video.setId();
								System.out.println("@RoboClub:\n Edited Successfuly");
								System.out.println(" New #ID: ("+ video.getId() +")");
								video.getFullInfo();
							}
							break;
						case 4:
							boolean wrongCommand2;
							short typeChoice;	
							System.out.println(" Choose Type from: ");
							System.out.println("  1.Short-Movie   2.Long-Movie   3.Serial");
							do {
								while (! scanner.hasNextShort()) {
									garbage = scanner.nextLine();
									System.out.print(" Ops! you must input a (Number): ");
								}
								typeChoice = scanner.nextShort();
								garbage = scanner.nextLine();
								if(typeChoice<1 || typeChoice>3) {
									wrongCommand2 = true;
									System.out.print(" Ops! input a (Valid) number: ");
								}
								else
									wrongCommand2 = false;	
							}while(wrongCommand2==true);
							switch(typeChoice) {
							
							case 1:
								videoCopy.setType(1);
								break;
							case 2:
								videoCopy.setType(2);
								break;
							case 3:
								videoCopy.setType(3);
								break;
							}
							videoCopy.setId();
							videoEqualityCheck = false;
							for (Video previousVideos : VideoArrayList.getVideoList()) {
								if (videoCopy.equals(previousVideos)) {
									videoEqualityCheck = true;
									System.out.println("\n@RoboClub:\n This Video already exist !");
									previousVideos.getFullInfo();
								}
							}
							if (videoEqualityCheck == false) {
								video.setTypeForEditingMethod(videoCopy.getType());
								video.setId();
								System.out.println("@RoboClub:\n Edited Successfuly");
								System.out.println(" New #ID: ("+ video.getId() +")");
								video.getFullInfo();
							}
							break;
						case 5:
							boolean genreIndexCheck;
							short index=0;
							do {	
								System.out.println(" Choose Genre from :");
								System.out.println("  1.Action\t2.Adventure\t3.Animation\t4.Biografy");
								System.out.println("  5.Comedy\t6.Crime\t"+"\t7.Drama"+"\t\t8.Documentary");
								System.out.println("  9.Family\t10.Fantasy\t11.History\t12.Horror"); 
								System.out.println("  13.Musical\t14.Mystry\t15.Romance\t16.Sci_fi");
								System.out.println("  17.Sport\t18.War\t\t19.Western");
								
								while (! scanner.hasNextShort()) {
									garbage = scanner.nextLine();
									System.out.print("@RoboClub:\n Ops! must input a (Number): ");
								}
								short genreChoice = scanner.nextShort();
								garbage = scanner.nextLine();
								if (genreChoice<1 || genreChoice>19) {
									System.out.println("@RoboClub:\n Ops! Please input a Number (from 1 to 19): ");
									genreIndexCheck = false;
								}
								else {
									index = --genreChoice;
									genreIndexCheck = true;
								}
							} while (genreIndexCheck == false);
							videoCopy.setGenre(index);
							videoCopy.setId();
							videoEqualityCheck = false;
							for (Video previousVideos : VideoArrayList.getVideoList()) {
								if (videoCopy.equals(previousVideos)) {
									videoEqualityCheck = true;
									System.out.println("\n@RoboClub:\n This Video already exist !");
									previousVideos.getFullInfo();
								}
							}
							if (videoEqualityCheck == false) {
								video.setGenreForEditingMethod(videoCopy.getGenre());
								video.setId();
								System.out.println("@RoboClub:\n Edited Successfuly");
								System.out.println(" New #ID: ("+ video.getId() +")");
								video.getFullInfo();
							}
							break;
						case 6:
							System.out.print(" Enter New Release Year: ");
							while (! scanner.hasNextShort()) {
								garbage = scanner.nextLine();
								System.out.print("@RoboClub:\n Ops! must input a number:");
							}
							int inputYear;
							do {
								inputYear = scanner.nextInt();
								garbage = scanner.nextLine();
							} while (videoCopy.setReleaseYear(inputYear) == false);
							videoCopy.setId();
							videoEqualityCheck = false;
							for (Video previousVideos : VideoArrayList.getVideoList()) {
								if (videoCopy.equals(previousVideos)) {
									videoEqualityCheck = true;
									System.out.println("\n@RoboClub:\n This Video already exist !");
									previousVideos.getFullInfo();
								}
							}
							if (videoEqualityCheck == false) {
								video.setReleaseYear(videoCopy.getReleaseYear());
								video.setId();
								System.out.println("@RoboClub:\n Edited Successfuly");
								System.out.println(" New #ID: ("+ video.getId() +")");
								video.getFullInfo();
							}
							break;
						case 7:
							short rateInput2;
							do {
								System.out.print(" Type Movie's IMDb rate from 1 to 10: ");
								while ( !scanner.hasNextShort() ) {
									garbage = scanner.nextLine();
									System.out.print("@RoboClub:\n Ops! must input a Number: ");
								}
								rateInput2 = scanner.nextShort();
								garbage = scanner.nextLine();
								}while (videoCopy.setIMDbRate(rateInput2) == false);
							videoCopy.setId();
							videoEqualityCheck = false;
							for (Video previousVideos : VideoArrayList.getVideoList()) {
								if (videoCopy.equals(previousVideos)) {
									videoEqualityCheck = true;
									System.out.println("\n@RoboClub:\n This Video already exist !");
									previousVideos.getFullInfo();
								}
							}
							if (videoEqualityCheck == false) {
								video.setIMDbRate(videoCopy.getIMDbRate());
								video.setId();
								System.out.println("@RoboClub:\n Edited Successfuly");
								System.out.println(" New #ID: ("+ video.getId() +")");
								video.getFullInfo();
							}
							break;
						case 8:
							addVideoMethod(scanner);
							VideoArrayList.deleteVideo(inputID);
							System.out.println(" and New one Replaced");
							break;
						case 0:
							loopToEditVideoMenu = false;
							break;
						}
					} while(loopToEditVideoMenu == true);
				}
			}	
		}
	}

	
	public static void deleteVidoeMethod(Scanner scanner) {
		scanner.reset();
		
		System.out.print(" Enter Video's ID: ");
		while (! scanner.hasNextLong()) {
			garbage = scanner.nextLine();
			System.out.print(" Ops! you must input a (Number): ");
		}
		long inputID = scanner.nextLong();
		garbage = scanner.nextLine();
		
		boolean idCheck = videoSearchByIdMethod(inputID);		
		if (idCheck == true) {
			String command;
			do {
				System.out.print(" Are you sure want to delete this video!? (y/n): ");
				command = scanner.next().toLowerCase();
			}while(!(command.charAt(0) == 'y') || (command.charAt(0) == 'n'));
			if (command.charAt(0) == 'y')
				VideoArrayList.deleteVideo(inputID);
			else
				System.out.println("delete Action Canceled");
		}
	}
	
	public static void rentingManagmentMethod(Scanner scanner) {
		scanner.reset();
		
		short rentingManagmentCommand;
		boolean loopToRentingManagmentMethod = true;
		
		do {
			System.out.println("\n\t_Renting Menu_______________________");
			System.out.println("\t 1.Renting a Video");
			System.out.println("\t 2.Returning a Video");
			System.out.println("\t 3.List of rented Videos");
			System.out.println("\t 0.Return to mainMenu");
			System.out.print(" Input: ");
			
			while (! scanner.hasNextShort()) {
				garbage = scanner.nextLine();
				System.out.print("\n Ops! must input a number:");
			}
			rentingManagmentCommand = scanner.nextShort();
			garbage = scanner.nextLine();
			System.out.println("———————————————————————————————————————————————————————————");
	outer:switch(rentingManagmentCommand) {
			
			case 1:
				boolean IdCheck;
				System.out.print(" Enter Video's ID: ");
				while (! scanner.hasNextLong()) {
					garbage = scanner.nextLine();
					System.out.print("\n Ops! must input a number:");
				}
				long vidInputID = scanner.nextLong();
				garbage = scanner.nextLine();
				IdCheck = videoSearchByIdMethod(vidInputID);
				if(IdCheck == false)
					break;
				
				for (Video video : VideoArrayList.getVideoList()) {
					if(video.checkRentSituation()== true) {
						System.out.print("\n@RoboClub:\n This video already rented "); video.printMemberWhoRent(); System.out.print("\n");
						break outer;
					}
				}
				System.out.print("\n Enter ID of member who want rent this video: ");
				while (! scanner.hasNextLong()) {
					garbage = scanner.nextLine();
					System.out.print("\n Ops! must input a number:");
				}
				long membInputID = scanner.nextLong();
				garbage = scanner.nextLine();
				IdCheck = memberSearchByIdMethod(membInputID);
				if(IdCheck == false)
					break;
				
				String command;
				do {
					System.out.print("\n Process renting Action? (y/n): ");
					command = scanner.next().toLowerCase();
				}while(!(command.charAt(0) == 'y') || (command.charAt(0) == 'n'));
				
				if (command.charAt(0) == 'y') {
					for (Video video : VideoArrayList.getVideoList()) {
						if (vidInputID == video.getId()) {
							video.setRentSituation('R');
							video.setMemberWhoRent(membInputID);
							for (Member member : MemberArrayList.getMemberList()) {
								if (membInputID == member.getId())
									member.setRentedVideo(video);
							}
							System.out.print("\n@RoboClub:\n "+video.getName().toUpperCase()+" Successfully Rented"); video.printMemberWhoRent(); System.out.print("\n");
						}
					}
				}
				break;
			case 2:
				boolean IdCheck2;
				System.out.print(" Enter Video's ID: ");
				while (! scanner.hasNextLong()) {
					garbage = scanner.nextLine();
					System.out.print("\n Ops! must input a number:");
				}
				long vidInputID2 = scanner.nextLong();
				garbage = scanner.nextLine();
				IdCheck2 = videoSearchByIdMethod(vidInputID2);
				if(IdCheck2 == false)
					break;
				
				for (Video video : VideoArrayList.getVideoList()) {
					if(video.checkRentSituation()== false) {
						System.out.print("\n@RoboClub:\n This video Not-Rented at all "); 
						break outer;
					}
				}
				
				String command2;
				do {
					System.out.print("\n Process returning Action? (y/n): ");
					command2 = scanner.next().toLowerCase();
				}while(!(command2.charAt(0) == 'y') || (command2.charAt(0) == 'n'));
				
				if (command2.charAt(0) == 'y') {
					for (Video video : VideoArrayList.getVideoList()) {
						if (vidInputID2 == video.getId()) {
							video.setRentSituation('N');
							video.getMemberWhoRented().setRentedVideo(null);
							System.out.println("@RoboClub:\n "+video.getName().toUpperCase()+" Successfully Returned to Club");
						}
					}
				}
				break;
			case 3:
				for (Video videos : VideoArrayList.getVideoList()) {
					if (videos.checkRentSituation() == true)
						videos.getFullInfo();
					else
						System.out.println("RoboClub:\n No Video Rented yet");
				}
				break;
			case 0:
				loopToRentingManagmentMethod = false;
				break;
			default:
				System.out.println("\n Ops! input a valid number");
			}
			
		} while(loopToRentingManagmentMethod == true);
	}
	public static Video copyOfVideo(long VideoID) {
		Video videoCopy = new Video();
		for (Video video : VideoArrayList.getVideoList()) {
			if (VideoID == video.getId()) {
				videoCopy.setName(video.getName());
				videoCopy.setDirector(video.getDirector());
				videoCopy.setStars(video.getStars());
				videoCopy.setTypeForEditingMethod(video.getType());
				videoCopy.setGenreForEditingMethod(video.getGenre());
				videoCopy.setIMDbRate(video.getIMDbRate());
				videoCopy.setReleaseYear(video.getReleaseYear());
			//	if (video.checkRentSituation() == true) {
			//		videoCopy.setRentSituation('R');
			//		videoCopy.setMemberWhoRent(video.getMemberWhoRented().getId());
			//	}
				videoCopy.setId();
			}
		}
		return videoCopy;
	}
	public static Member copyOfMember(Long MemberID) {
		Member memberCopy = new Member();
		for (Member member : MemberArrayList.getMemberList()) {
			if (MemberID == member.getId()) {
				memberCopy.setFirstName(member.getFirstName());
				memberCopy.setLastName(member.getLastName());
				memberCopy.setAge(member.getAge());
				memberCopy.setGender(member.getGender());
			//	if (!(member.getRentedVideo() == null))
			//		memberCopy.setRentedVideo(member.getRentedVideo());
				memberCopy.setId();
			}
		}
		return memberCopy;
	}
}
