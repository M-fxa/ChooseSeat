package feishi;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


/**操作记录类*/
public class Record extends Student {

	private Date ChooseTime;
	private Date CancelTime;
	boolean flag;

	
	public Record(String AN,String N,boolean G,String PW,boolean UT,String SN,boolean US) {
//		System.out.println("构造操作记录");
		super(AN,N,G,PW,UT,SN,US);
		this.ChooseTime=new Date();
		flag=false;
	}
	
	public Record(Student stu)
	{
		super(stu);
		flag=false;
		
	}
	
	public void start()
	{
		this.ChooseTime=new Date();
	}
	
	public void end()
	{
		this.CancelTime=new Date();
	}
	
	public String count()
	{
		SimpleDateFormat df = null;
		df = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy",Locale.UK);
		try {
			this.ChooseTime = df.parse(this.ChooseTime.toString());
//			System.out.println(this.ChooseTime.getTime());
		} catch (ParseException e) {
			System.out.println("X");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			this.CancelTime = df.parse(this.CancelTime.toString());
//			System.out.println(this.CancelTime.getTime());
		} catch (ParseException e) {
			System.out.println("X");
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long l=this.CancelTime.getTime()-ChooseTime.getTime();
		long day=l/(24*60*60*1000);
		long hour=(l/(60*60*1000)-day*24);
		long min=((l/(60*1000))-day*24*60-hour*60);
		long s=(l/1000-day*24*60*60-hour*60*60-min*60);
//		System.out.println(s);
		if(s>10)
		{
			flag=true;
//			System.out.println("超时");
		}
//		System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");
		return ""+day+"天"+hour+"小时"+min+"分"+s+"秒";
	}
}
