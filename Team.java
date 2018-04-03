import java.io.File;
import java.util.Scanner;
import java.util.*;

public class Team {

	private File aFile;
	private Scanner input; 
	Player thisPlayer; 
	Team aTeam;
	private ArrayList<Player> players;
	private ArrayList<Player> team;
	private ArrayList<Player> newTeam =  new ArrayList<Player>();
	private HashMap<String,Integer> nbaTeams = new HashMap<String,Integer>();
	private HashMap<String,Boolean> playerStatus = new HashMap<String, Boolean>();
	private String response;
	Scanner scan = new Scanner(System.in);
	
	public void readData() {
		players = new ArrayList<Player>();;
		try {
			aFile = new File("Untitled.txt");
			input = new Scanner(aFile);			
		}
		catch(Exception ex ) {
			System.out.println(ex);
		}
		
		input.useDelimiter(",");
		int i = 0; 	
		
		int numberOfPlayersRead = 0;
		
		while(input.hasNext())
		{
			int rank = input.nextInt();
			String playerName = input.next();
			String position = input.next();
			int playerAge = input.nextInt();
			String team = input.next();
			int gamesPlayed = input.nextInt();
			double minutesPlayed = input.nextDouble();

			
			Player aPlayer = new Player(rank, playerName, position, playerAge, team, gamesPlayed,
					minutesPlayed);
			players.add(aPlayer);
			
			input.nextLine();
			
			numberOfPlayersRead++;
		}
		
		System.out.println(numberOfPlayersRead + " players read from the file and added to the ArrayList Players");
		
	}
	

	
	public Team() {
		//code here
		
		readData();
		//readSalaryData();
		
//		createCustomTeam();
//		
//		viewUniqueTeam();
//		
//		viewNbaTeamRoster();
		
//		for (Player aPlayer : players)
//		{
//			if (checkPlayerStatus(aPlayer) == false)
//			{
//				System.out.println(aPlayer.getPlayerName());
//			}
//		}
		
		addingPlayerStatus();
	}

	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Team aTeam = new Team();
	}

	public void createCustomTeam () {
		
		int counter = 0;
		while (counter < 6)
		{
			System.out.println("Please enter the name of the player to add >");
			String name = scan.nextLine();
			
			for (Player aPlayer : players)
			{
				if (aPlayer.getPlayerName().equals(name))
					{
						newTeam.add(aPlayer);
						System.out.println(aPlayer.getPlayerName() + " has been added to your team.");
						counter++;
					}
			}
			if (counter == 5) {
				System.out.println("Your team is now complete.");
				printCustomTeam();
			}
		}
	}
	
	public void printCustomTeam() {
		for (Player aPlayer : newTeam)
		{
			System.out.println("Name: " + aPlayer.getPlayerName() + " " + "Position: " + aPlayer.getPosition());
		}
	}
	
	public void viewUniqueTeam() {
		for (Player aPlayer : players)
		{
			String team = aPlayer.getTeam();
			
			Integer value = nbaTeams.get(team);
			
			if (value == null)
			{
				nbaTeams.put(team, 1);
			}
			else {
				nbaTeams.put(team, value+1);
			}
		}
		
		for (String team : nbaTeams.keySet())
		{
			System.out.println(team + " has " + nbaTeams.get(team) + " players.");
		}


		
	}
	
	public void viewNbaTeamRoster() {
		System.out.println("Enter the name of team to view >");
		String team = scan.nextLine(); 
		
		System.out.println(team + " :");
		
		for (Player aPlayer : players)
		{
			if (aPlayer.getTeam().equals(team))
			{
				System.out.println(aPlayer.getPlayerName());
			}				
		}
		
		
	}
	
	//This method checks if the player is apart of the Atl Hawks
	public boolean checkPlayerStatus (Player thisPlayer) { 
		String team = thisPlayer.getTeam();
		
		if (team.equals("Atlanta Hawks"))
		{
			return true;
		}
		
		return false;
	}
	
	//Places the players into a hashMap with a key name and boolean t/f (t == they are on the atl hawks, f == not on atl hawks)
	public void addingPlayerStatus() {
		boolean x = false;
		for (Player aPlayer : players)
		{
			String name = aPlayer.getPlayerName();
			//if true, puts true
			if (checkPlayerStatus(aPlayer))
			{
				x = true;
				playerStatus.put(name, checkPlayerStatus(aPlayer));
			}
			//if false, puts false
			if (!checkPlayerStatus(aPlayer))
			{
				//assigns aPlayer object to thisPlayer
				thisPlayer = aPlayer;
				playerStatus.put(name, checkPlayerStatus(aPlayer));
				
			}
		}
		
		if (!x) {
			players.remove(thisPlayer);
		}
		
		printPlayerStatus();
	}
	
	public void printPlayerStatus() {
		for (String playerName : playerStatus.keySet())
		{
			System.out.println(playerName + " " + playerStatus.get(playerName));
		}
	}
}
