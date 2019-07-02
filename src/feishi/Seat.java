package feishi;

/**座位信息类*/
public class Seat {
	boolean SeatState = true;
	int SeatNum;
	
	Seat(boolean Se,int SN){
//		System.out.println("构造座位信息");
		this.SeatState = Se;
		this.SeatNum = SN;
	}
	
	void Set(){
		this.SeatState = false; 
	}
	
	void Free(){
		this.SeatState = true;
	}
}
