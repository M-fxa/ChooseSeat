package feishi;

/*
 * Manager��:����Ա��
 * Password:����
 * UserType:�û�����
 * Set:���ù���Ա��Ϣ
 */
public class Manager extends User{
	private String Password;
	private boolean UserType;
	Manager()
	{
		super();
		Password = "NULL";
		UserType = true;
	}
	Manager(String AN,String N,boolean G,String PW,boolean UT)
	{
		super(AN,N,G);
		Password = PW;
		UserType = UT;
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
		super.setAccountNum(Pa);
	}
	
	void setUserType(boolean UT) {
		UserType=UT;
	}
	
	boolean getUserType()
	{
		return this.UserType;
	}
	
	void Set(String AN,String N,boolean G,String PW,boolean UT)
	{
		super.Set(AN, N, G);
		Password = PW;
		UserType = UT;
	}
	
}
