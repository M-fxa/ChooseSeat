package feishi;

/*
 * User��:Student��,Manager��,Blacklist��ĸ���
 * AccountNum:�û����˺�
 * Name:�û�������
 * Gender:�û����Ա�
 * Set����:�����û�����Ϣ
 */
public class User {
	private String AccountNum;
	private String Name;
	private boolean Gender;
	User()
	{
		AccountNum="000";
		Name="NAME";
		Gender=true;
	}
	User(String AN,String N,boolean G)
	{
		AccountNum=AN;
		Name=N;
		Gender=G;
	}
	
	User(User us)
	{
		this.AccountNum=us.getAccountNum();
		this.Name=us.getName();
		this.Gender=us.getGender();
	}
	
	String getAccountNum(){
		return AccountNum;
	}
	
	void setAccountNum(String AN) {
		this.AccountNum = AN;
	}
	
	String getName(){
		return Name;
	}
	
	void setName(String Na) {
		this.Name = Na;
	}
	
	boolean getGender(){
		return Gender;
	}
	
	void setGender(boolean Ge) {
		this.Gender = Ge;
	}
	
	void Set(String AN,String N,boolean G)
	{
		AccountNum=AN;
		Name=N;
		Gender=G;
	}
	
}
