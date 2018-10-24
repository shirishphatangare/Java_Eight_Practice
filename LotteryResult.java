import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;


public class LotteryResult {
	List<Ticket> tickets;
	Ticket winningTicket;
	
	public LotteryResult() {
		this.tickets = new ArrayList<>();
	}

	public Ticket getWinningTicket() {
		return winningTicket;
	}

	public void setWinningTicket(Ticket winningTicket) {
		this.winningTicket = winningTicket;
	}
	
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		LotteryResult lottery = new LotteryResult();
		lottery.readInput();
		
		int numberTickets = lottery.getTickets().size();
		FutureTask[] tasks = new FutureTask[numberTickets];
		
		for(int i=0; i<numberTickets; i++){
			Result callableResult = new Result(lottery.getTickets().get(i),lottery.getWinningTicket());
			tasks[i] = new FutureTask<String>(callableResult);
			new Thread(tasks[i]).start();
		}
		
		System.out.println("--------------------------------------");
		
		for(int i=0; i<numberTickets; i++){
			System.out.println(tasks[i].get());
			System.out.println("--------------------------------------");
		}

	}
	
	private void readInput(){
		Scanner scanner = new Scanner(System.in);
		//System.out.println("Please Enter Number of Tickets: ");
		int numberTickets = scanner.nextInt();
		int ticketCounter = 1;
		
		while(ticketCounter  <= numberTickets) {
			//System.out.println("Please Enter Numbers for Ticket" + ticketCounter);
			//System.out.println("1 2 3 4 5 MB MP(0 OR 1)");
			
			scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
			String line = scanner.nextLine();
			String[] lineSplit = line.split("\\s+");
			int[] numbers = new int[5];
			
			for(int i=0;i<5;i++){
				numbers[i] = Integer.parseInt(lineSplit[i]);
			}
			
			int megaBall = Integer.parseInt(lineSplit[5]);
			int megaPlier = Integer.parseInt(lineSplit[6]);
			String ticketName = "Ticket" + ticketCounter++;
			tickets.add(new Ticket(ticketName,numbers,megaBall,megaPlier));
		}
		System.out.println("***********************************************************");
		System.out.println("Please Enter Winning Numbers: ");
		System.out.println("1 2 3 4 5 MB MP(Actual MP)");
		String winLine = scanner.nextLine();
		String[] winNumbers = winLine.split("\\s+");
		int[] result = new int[5];
		
		for(int i=0;i<5;i++){
			result[i] = Integer.parseInt(winNumbers[i]);
		}
		
		int megaBall = Integer.parseInt(winNumbers[5]);
		int megaPlier = Integer.parseInt(winNumbers[6]);
		winningTicket = new Ticket("Winning - Ticket",result,megaBall,megaPlier);
		setWinningTicket(winningTicket);
		
		scanner.close();
	}

}


class Result implements Callable<String> {
	Ticket ticket;
	Ticket winningTicket;
	int mainNumbersCount = 0;
	int megaBallcount = 0;
	int megaplier = 1;
	
	
	public Result(Ticket ticket,Ticket winningTicket){
		this.ticket = ticket;
		this.winningTicket = winningTicket;
	}
	
	@Override
	public String call() throws Exception {
		int [] mainNumbers = ticket.getMainNumbers();
		int [] winningNumbers = winningTicket.getMainNumbers();
		
		int mainMegaBall = ticket.getMegaBall();
		int winningMegaBall = winningTicket.getMegaBall();
		
		for(int i=0;i<mainNumbers.length;i++){
			for(int j=0;j<winningNumbers.length;j++){
				if(mainNumbers[i] == winningNumbers[j]){
					mainNumbersCount++;
					break;
				}
			}
		}
		if(mainMegaBall == winningMegaBall){
			megaBallcount++;
		}
		
		
		if(ticket.getMegaplier() > 0){
			megaplier = winningTicket.getMegaplier();
		}
		
		String criteria = mainNumbersCount + " + " + megaBallcount;
		String prizeAmount = getPrizeAmount(criteria,megaplier);
		
		return "Result for "+ ticket.getTicketName() + " - " + criteria  + " - " + prizeAmount;
	}
	
	private String getPrizeAmount(String criteria, int megaplier){
		int amount = 1;
		switch(criteria){
			case "0 + 1": { amount = amount * 2 *  megaplier; break;}  
			case "1 + 1": { amount = amount * 4 *  megaplier; break;} 
			case "2 + 1": { amount = amount * 10 *  megaplier; break;} 
			case "3 + 0": { amount = amount * 10 *  megaplier; break;} 
			case "3 + 1": { amount = amount * 200 *  megaplier; break;} 
			case "4 + 0": { amount = amount * 500 *  megaplier; break;} 
			case "4 + 1": { amount = amount * 10_000 *  megaplier; break;} 
			case "5 + 0": { amount = amount * 1_000_000 *  megaplier; break;} 
			case "5 + 1": return " JACKPOT!! ";
			default: return "No Prize";
		}
		return "$" + String.valueOf(amount);
	}
	
}

class Ticket {
	String ticketName;
	int[] mainNumbers;
	int megaBall;
	int megaplier;
	
	public Ticket(String ticketName,int[] mainNumbers,int megaBall,int megaplier){
		this.ticketName = ticketName;
		this.mainNumbers = mainNumbers;
		this.megaBall = megaBall;
		this.megaplier = megaplier;
	}
	
	public String getTicketName() {
		return ticketName;
	}

	public void setTicketName(String ticketName) {
		this.ticketName = ticketName;
	}

	public int[] getMainNumbers() {
		return mainNumbers;
	}

	public void setMainNumbers(int[] mainNumbers) {
		this.mainNumbers = mainNumbers;
	}

	public int getMegaBall() {
		return megaBall;
	}

	public void setMegaBall(int megaBall) {
		this.megaBall = megaBall;
	}

	public int getMegaplier() {
		return megaplier;
	}

	public void setMegaplier(int megaplier) {
		this.megaplier = megaplier;
	}
	
}

