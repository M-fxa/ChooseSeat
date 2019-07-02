package feishi;

/*
 * ѧ����
 * Password:����
 * UserType:�û����ͣ�trueΪ����Ա��falseΪѧ���û�
 * Set����:���ڸ���ѧ����Ϣ�ķ���
 */
public class Student extends User{
	private String Password;
	private boolean UserType;
	private boolean Using;
	private String SeatNum;

	Student()
	{
		super();
		Password = "NULL";
		UserType = false;
		SeatNum = "0";// ��ѡ�������к���ѧ����������
		Using = false;
	}
	Student(String AN,String N,boolean G,String PW,boolean UT,String SN,boolean US)
	{
		super(AN,N,G);
		Password = PW;
		UserType = UT;
		SeatNum = SN;
		Using = US;
	}
	
	Student(Student s)
	{
		super(s);
		this.Password=s.getPassword();
		this.UserType=s.getUserType();
		this.Using=s.getUsing();
		this.SeatNum=s.getSeatNum();
	}
	
	String getAccountNum() {
		return super.getAccountNum();
	}
	void setAccount(String AN) {
		super.setAccountNum(AN);
	}
	
	String getPassword() {
		return this.Password;
	}
	void setPassword(String Pa) {
		Password=Pa;
	}
	
	String getSeatNum() {
		return this.SeatNum;
	}
	void setSeatNum(String SN) {
		this.SeatNum = SN;
	}
	
	void setName(String Na) {
		super.setName(Na);
	}
	
	void setGender(boolean Ge) {
		super.setGender(Ge);
	}
		
	void setUserType(boolean UT) {
		UserType=UT;
	}
	
	boolean getUserType()
	{
		return this.UserType;
	}
	
	void setUsing(boolean U) {
		this.Using=U;
	}
	
	boolean getUsing() {
		return this.Using;
	}
	
	void Set(String AN,String N,boolean G,String PW,boolean UT,String SN)
	{
		super.Set(AN,N,G);
		Password = PW;
		UserType = UT;
		SeatNum = SN;
	}

}
