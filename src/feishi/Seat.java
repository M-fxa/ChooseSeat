package feishi;

/**��λ��Ϣ��*/
public class Seat {
	boolean SeatState = true;
	int SeatNum;
	
	Seat(boolean Se,int SN){
//		System.out.println("������λ��Ϣ");
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
