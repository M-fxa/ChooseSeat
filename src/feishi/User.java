package feishi;

/*
 * User类:Student类,Manager类,Blacklist类的父类
 * AccountNum:用户的账号
 * Name:用户的姓名
 * Gender:用户的性别
 * Set方法:重置用户的信息
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
