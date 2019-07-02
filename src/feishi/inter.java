package feishi;

import java.awt.*;
import javax.swing.JFrame;
import javax.swing.JEditorPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.JTextPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import java.io.*;


public class inter {

	private JFrame main_frame;
	private JTextField account_textField;
	private JPasswordField passwordField;

	private JTextField Bl_textField;
	int Num;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
			
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student[] user_data = new Student[10];
					boolean seat_data[] = new boolean[12];
					Record[] record_data = new Record[10];
					inter window = new inter(user_data,seat_data,record_data);
					window.main_frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
					
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public inter(Student user_data[],boolean seat_data[],Record record_data[]) {
		initialize(user_data,seat_data,record_data);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	void initialize(final Student user_data[],final boolean seat_data[],final Record record_data[]) {
		main_frame = new JFrame();
		main_frame.setBounds(400, 200, 300, 231);
		main_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		final CardLayout card = new CardLayout(0, 0); 
		main_frame.getContentPane().setLayout(card);
		main_frame.setTitle("座位管理系统");
		
		for(int j = 0;j<12;j++)
		{
			seat_data[j] = true;
		}
		
		BufferedReader br=null;
		try {
			
			br=new BufferedReader(new FileReader("user.txt"));
			String line = null;
			int x=0;
			while((line=br.readLine())!=null)
			{
				user_data[x]=new Student();
				String[] datas = line.split("=");
					user_data[x].setAccountNum(datas[0]);
					user_data[x].setPassword(datas[1]);
					user_data[x].setName(datas[2]);
					
					if(datas[3].equals("true"))
						user_data[x].setGender(true);
					else
						user_data[x].setGender(false);
					user_data[x].setSeatNum(datas[4]);
					if(datas[5].equals("true"))
						user_data[x].setUserType(true);
					else
						user_data[x].setUserType(false);
					
					if(!user_data[x].getUserType())
					{
						if(datas[6].equals("true"))
							user_data[x].setUsing(true);
						else
							user_data[x].setUsing(false);
					}
					record_data[x]=new Record(user_data[x]);
					x++;
					
				}
			
			}
		catch(FileNotFoundException e)
		{
		//	System.out.println("file not found");
		}
		catch(IOException e)
		{
			System.out.println("io exception");
		}
		finally
		{
			if(br!=null)
				try {
					br.close();
				}
			catch(IOException e)
			{
				System.out.println("登陆释放资源失败");
			}
		}


		//创建文件代码
		
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("Record.txt"));
			bw.write("record");
			bw.newLine();
			bw.flush();
		}catch(IOException e){
			System.out.println("记录失败");
		}finally {
			if(bw != null) {
				try {
					bw.close();
				}catch(IOException e) {
					System.out.println("释放记录失败了");
				}
			}
		}
		
		//创建文件代码
				
		
		JPanel Login = new JPanel();
		main_frame.getContentPane().add(Login, "Login");
		Login.setLayout(new BorderLayout(0, 0));
		
		JPanel login_panel = new JPanel();
		Login.add(login_panel, BorderLayout.NORTH);
		
		JLabel login_Label = new JLabel("账号");
		login_panel.add(login_Label);
		
		account_textField = new JTextField();
		login_panel.add(account_textField);
		account_textField.setColumns(20);
		
		JPanel pw_panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) pw_panel.getLayout();
		flowLayout.setHgap(2);
		Login.add(pw_panel, BorderLayout.CENTER);
		
		JLabel pw_Label = new JLabel("密码");
		pw_panel.add(pw_Label);
		
		passwordField = new JPasswordField();
		passwordField.setColumns(20);
		pw_panel.add(passwordField);
		
		JPanel pw_btn_panel = new JPanel();
		Login.add(pw_btn_panel, BorderLayout.SOUTH);
		
		JButton btn_loin = new JButton("登录");
		btn_loin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				boolean flag = false;
				BufferedReader br1=null;
				try {
					
					br1=new BufferedReader(new FileReader("blacklist.txt"));
					String line = null;
					
					while((line=br1.readLine())!=null)
					{
						
						String[] datas = line.split(":");
						
						if(datas[1].equals(account_textField.getText()))
						{
							javax.swing.JOptionPane.showMessageDialog(null, "该用户在黑名单中!");
							flag=true;
							break;
						}
						
					}
				}
				catch(FileNotFoundException e)
				{
//					System.out.println("file not found");
				}
				catch(IOException e)
				{
					System.out.println("io exception");
				}
				finally
				{
					if(br1!=null)
						try {
							br1.close();
						}
					catch(IOException e)
					{
						System.out.println("登陆释放资源失败");
					}
				}
				
				/************************************************/
				if(flag==false)
				{
					
					for(int c=0;c<user_data.length;c++)
					{
						if(user_data[c].getAccountNum().equals(account_textField.getText()) && user_data[c].getPassword().equals(passwordField.getText()))
						{
							Num=c;
							javax.swing.JOptionPane.showMessageDialog(null, "登陆成功!");
							card.show(main_frame.getContentPane(), "menu");
							break;
						}
					}
				}
			
			}			
		});

		pw_btn_panel.add(btn_loin);
		
		
		JPanel chooser = new JPanel();
		main_frame.getContentPane().add(chooser, "chooser");
		GridBagLayout gbl_chooser = new GridBagLayout();
		gbl_chooser.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_chooser.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_chooser.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_chooser.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		chooser.setLayout(gbl_chooser);
		
		JLabel choose_Label1 = new JLabel("请选座");
		GridBagConstraints gbc_choose_Label1 = new GridBagConstraints();
		gbc_choose_Label1.insets = new Insets(0, 0, 5, 5);
		gbc_choose_Label1.gridx = 0;
		gbc_choose_Label1.gridy = 0;
		chooser.add(choose_Label1, gbc_choose_Label1);
/****************************************************************************************************/	
		

		
		final JButton btn_seat1 = new JButton("座位01");
		btn_seat1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[0]=false;
				btn_seat1.setEnabled(false);
				user_data[Num].setSeatNum("01");
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"01"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_7.gridx = 0;
		gbc_btnNewButton_7.gridy = 1;
		chooser.add(btn_seat1, gbc_btnNewButton_7);
		
		final JButton btn_seat2 = new JButton("座位02");
		btn_seat2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[1]=false;
				user_data[Num].setSeatNum("02");
				btn_seat2.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"02"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_9 = new GridBagConstraints();
		gbc_btnNewButton_9.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_9.gridx = 2;
		gbc_btnNewButton_9.gridy = 1;
		chooser.add(btn_seat2, gbc_btnNewButton_9);
		
		final JButton btn_seat3 = new JButton("座位03");
		btn_seat3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[2]=false;
				user_data[Num].setSeatNum("03");
				btn_seat3.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"03"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_15 = new GridBagConstraints();
		gbc_btnNewButton_15.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_15.gridx = 4;
		gbc_btnNewButton_15.gridy = 1;
		chooser.add(btn_seat3, gbc_btnNewButton_15);
		
		final JButton btn_seat4 = new JButton("座位04");
		btn_seat4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[3]=false;
				user_data[Num].setSeatNum("04");
				btn_seat4.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"04"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_10 = new GridBagConstraints();
		gbc_btnNewButton_10.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_10.gridx = 0;
		gbc_btnNewButton_10.gridy = 3;
		chooser.add(btn_seat4, gbc_btnNewButton_10);
		
		final JButton btn_seat5 = new JButton("座位05");
		btn_seat5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[4]=false;
				user_data[Num].setSeatNum("05");
				btn_seat5.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"05"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_8 = new GridBagConstraints();
		gbc_btnNewButton_8.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_8.gridx = 2;
		gbc_btnNewButton_8.gridy = 3;
		chooser.add(btn_seat5, gbc_btnNewButton_8);
		
		final JButton btn_seat6 = new JButton("座位06");
		btn_seat6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[5]=false;
				user_data[Num].setSeatNum("06");
				btn_seat6.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"06"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_17 = new GridBagConstraints();
		gbc_btnNewButton_17.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_17.gridx = 4;
		gbc_btnNewButton_17.gridy = 3;
		chooser.add(btn_seat6, gbc_btnNewButton_17);
		
		final JButton btn_seat7 = new JButton("座位07");
		btn_seat7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[6]=false;
				user_data[Num].setSeatNum("07");
				btn_seat7.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"07"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_11 = new GridBagConstraints();
		gbc_btnNewButton_11.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_11.gridx = 0;
		gbc_btnNewButton_11.gridy = 5;
		chooser.add(btn_seat7, gbc_btnNewButton_11);
		
		final JButton btn_seat8 = new JButton("座位08");
		btn_seat8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[7]=false;
				user_data[Num].setSeatNum("08");
				btn_seat8.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"08"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_12 = new GridBagConstraints();
		gbc_btnNewButton_12.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_12.gridx = 2;
		gbc_btnNewButton_12.gridy = 5;
		chooser.add(btn_seat8, gbc_btnNewButton_12);
		
		final JButton btn_seat9 = new JButton("座位09");
		btn_seat9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[8]=false;
				user_data[Num].setSeatNum("09");
				btn_seat9.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"09"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_18 = new GridBagConstraints();
		gbc_btnNewButton_18.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_18.gridx = 4;
		gbc_btnNewButton_18.gridy = 5;
		chooser.add(btn_seat9, gbc_btnNewButton_18);
		
		final JButton btn_seat10 = new JButton("座位10");
		btn_seat10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[9]=false;
				user_data[Num].setSeatNum("10");
				btn_seat10.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"10"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_13 = new GridBagConstraints();
		gbc_btnNewButton_13.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_13.gridx = 0;
		gbc_btnNewButton_13.gridy = 7;
		chooser.add(btn_seat10, gbc_btnNewButton_13);
		
		final JButton btn_seat11 = new JButton("座位11");
		btn_seat11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[10]=false;
				user_data[Num].setSeatNum("11");
				btn_seat11.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"11"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
		});
		GridBagConstraints gbc_btnNewButton_14 = new GridBagConstraints();
		gbc_btnNewButton_14.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_14.gridx = 2;
		gbc_btnNewButton_14.gridy = 7;
		chooser.add(btn_seat11, gbc_btnNewButton_14);
		
		final JButton btn_seat12 = new JButton("座位12");
		btn_seat12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				seat_data[11]=false;
				user_data[Num].setSeatNum("12");
				btn_seat12.setEnabled(false);
				BufferedWriter bw = null;
				try {
					bw = new BufferedWriter(new FileWriter("Record.txt",true));
					bw.append("X:"+user_data[Num].getAccountNum()+"="+"12"+"="+new Date());
					bw.newLine();
					bw.flush();
				}catch(IOException e){
					System.out.println("记录失败");
				}finally {
					if(bw != null) {
						try {
							bw.close();
						}catch(IOException e) {
							System.out.println("释放记录失败了");
						}
					}
				}
				record_data[Num].start();//设置时间
				javax.swing.JOptionPane.showMessageDialog(null, "选座成功!");
				card.show(main_frame.getContentPane(), "menu");
			}
			
		});
		GridBagConstraints gbc_btnNewButton_19 = new GridBagConstraints();
		gbc_btnNewButton_19.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_19.gridx = 4;
		gbc_btnNewButton_19.gridy = 7;
		chooser.add(btn_seat12, gbc_btnNewButton_19);
		
		JPanel menu = new JPanel();
		main_frame.getContentPane().add(menu, "menu");
		
		JButton btn_choose = new JButton("               选座               ");
		btn_choose.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getUsing())
				{
					javax.swing.JOptionPane.showMessageDialog(null, "该用户已选座!");
				}
				else
				{
					user_data[Num].setUsing(true);
					card.show(main_frame.getContentPane(), "chooser");
				}
			}
		});
		menu.add(btn_choose);
		
		JButton btn_cancel = new JButton("               退座               ");
		btn_cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(user_data[Num].getUsing() || user_data[Num].getUserType())
				{
					if(!user_data[Num].getUserType())
						user_data[Num].setUsing(false);
					card.show(main_frame.getContentPane(), "cancelseat");
	
					card.show(main_frame.getContentPane(), "mune");
				}
				else
					javax.swing.JOptionPane.showMessageDialog(null, "该用户未选座!");
			}
		});
		menu.add(btn_cancel);
		
		JButton btn_blacklist = new JButton("         添加黑名单         ");
		btn_blacklist.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//********************在此处填写修改个人信息的代码**********************************************************
				card.show(main_frame.getContentPane(), "blacklist");
			}
		});
		menu.add(btn_blacklist);
		
		JButton btn_exit = new JButton("           退出登录           ");
		btn_exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//******************返回登录的界面的卡片中去*****************************************************************
				javax.swing.JOptionPane.showMessageDialog(null, "退出登录成功!");
				card.show(main_frame.getContentPane(), "Login");
				account_textField.setText("");
				passwordField.setText("");
			}
		});
		menu.add(btn_exit);
		
		JPanel cancelseat = new JPanel();
		main_frame.getContentPane().add(cancelseat, "cancelseat");
		GridBagLayout gbl_panel_6 = new GridBagLayout();
		gbl_panel_6.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gbl_panel_6.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel_6.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_6.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		cancelseat.setLayout(gbl_panel_6);
		
		JLabel cancel_Label_1 = new JLabel("请选择座位:");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 0;
		cancelseat.add(cancel_Label_1, gbc_lblNewLabel_5);
/***********************************************************************************************************/		
		JButton btn_Cseat1 = new JButton("座位01");
		btn_Cseat1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("01"))
				{
					seat_data[0]=true;
					btn_seat1.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"01"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_16 = new GridBagConstraints();
		gbc_btnNewButton_16.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_16.gridx = 0;
		gbc_btnNewButton_16.gridy = 1;
		cancelseat.add(btn_Cseat1, gbc_btnNewButton_16);
		
		JButton btn_Cseat2 = new JButton("座位02");
		btn_Cseat2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("02"))
				{
					seat_data[1]=true;
					btn_seat2.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"02"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_20 = new GridBagConstraints();
		gbc_btnNewButton_20.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_20.gridx = 2;
		gbc_btnNewButton_20.gridy = 1;
		cancelseat.add(btn_Cseat2, gbc_btnNewButton_20);
		
		JButton btn_Cseat3 = new JButton("座位03");
		btn_Cseat3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("03"))
				{
					seat_data[2]=true;
					btn_seat3.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"03"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_21 = new GridBagConstraints();
		gbc_btnNewButton_21.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_21.gridx = 4;
		gbc_btnNewButton_21.gridy = 1;
		cancelseat.add(btn_Cseat3, gbc_btnNewButton_21);
		
		JButton btn_Cseat4 = new JButton("座位04");
		btn_Cseat4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("04"))
				{
					seat_data[3]=true;
					btn_seat4.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"04"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_22 = new GridBagConstraints();
		gbc_btnNewButton_22.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_22.gridx = 0;
		gbc_btnNewButton_22.gridy = 3;
		cancelseat.add(btn_Cseat4, gbc_btnNewButton_22);
		
		JButton btn_Cseat5 = new JButton("座位05");
		btn_Cseat5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("05"))
				{
					seat_data[4]=true;
					btn_seat5.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"05"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_23 = new GridBagConstraints();
		gbc_btnNewButton_23.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_23.gridx = 2;
		gbc_btnNewButton_23.gridy = 3;
		cancelseat.add(btn_Cseat5, gbc_btnNewButton_23);
		
		JButton btn_Cseat6 = new JButton("座位06");
		btn_Cseat6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("06"))
				{
					seat_data[5]=true;
					btn_seat6.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"6"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_24 = new GridBagConstraints();
		gbc_btnNewButton_24.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_24.gridx = 4;
		gbc_btnNewButton_24.gridy = 3;
		cancelseat.add(btn_Cseat6, gbc_btnNewButton_24);
		
		JButton btn_Cseat7 = new JButton("座位07");
		btn_Cseat7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("07"))
				{
					seat_data[6]=true;
					btn_seat7.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"07"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_25 = new GridBagConstraints();
		gbc_btnNewButton_25.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_25.gridx = 0;
		gbc_btnNewButton_25.gridy = 5;
		cancelseat.add(btn_Cseat7, gbc_btnNewButton_25);
		
		JButton btn_Cseat8 = new JButton("座位08");
		btn_Cseat8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("08"))
				{
					seat_data[7]=true;
					btn_seat8.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"08"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_26 = new GridBagConstraints();
		gbc_btnNewButton_26.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_26.gridx = 2;
		gbc_btnNewButton_26.gridy = 5;
		cancelseat.add(btn_Cseat8, gbc_btnNewButton_26);
		
		JButton btn_Cseat9 = new JButton("座位09");
		btn_Cseat9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("09"))
				{
					seat_data[8]=true;
					btn_seat9.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"09"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_27 = new GridBagConstraints();
		gbc_btnNewButton_27.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_27.gridx = 4;
		gbc_btnNewButton_27.gridy = 5;
		cancelseat.add(btn_Cseat9, gbc_btnNewButton_27);
		
		JButton btn_Cseat10 = new JButton("座位10");
		btn_Cseat10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("10"))
				{
					seat_data[9]=true;
					btn_seat10.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"10"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_28 = new GridBagConstraints();
		gbc_btnNewButton_28.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_28.gridx = 0;
		gbc_btnNewButton_28.gridy = 7;
		cancelseat.add(btn_Cseat10, gbc_btnNewButton_28);
		
		JButton btn_Cseat11 = new JButton("座位11");
		btn_Cseat11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("11"))
				{
					seat_data[10]=true;
					btn_seat11.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"11"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_29 = new GridBagConstraints();
		gbc_btnNewButton_29.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_29.gridx = 2;
		
		gbc_btnNewButton_29.gridy = 7;
		cancelseat.add(btn_Cseat11, gbc_btnNewButton_29);
		
		JButton btn_Cseat12 = new JButton("座位12");
		btn_Cseat12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getSeatNum().equals("12"))
				{
					seat_data[11]=true;
					btn_seat12.setEnabled(true);
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("Record.txt",true));
						bw.append("T:"+user_data[Num].getAccountNum()+"="+"12"+"="+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					}
					record_data[Num].end();
					if(user_data[Num].getUserType()==false){
						System.out.println(record_data[Num].count());
					}
					if(record_data[Num].flag)
					{
						javax.swing.JOptionPane.showMessageDialog(null, "占座超时!");
						BufferedWriter bw1 = null;
						try {
							bw1 = new BufferedWriter(new FileWriter("blacklist.txt",true));
							bw1.append("User accounte number:"+user_data[Num].getAccountNum()+":"+new Date());
							bw1.newLine();
							bw1.flush();
						}
						catch(IOException e){
							System.out.println("记录失败");
						}
						finally {
							if(bw != null) {
								try {
									bw.close();
								}
								catch(IOException e) {
									System.out.println("释放记录失败了");
								}
							}
						}
						card.show(main_frame.getContentPane(), "Login");
					}
					else
					{
						javax.swing.JOptionPane.showMessageDialog(null, "退座成功!");
						card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "座位错误!");
					card.show(main_frame.getContentPane(), "cancelseat");
				}
			}
		});
		GridBagConstraints gbc_btnNewButton_30 = new GridBagConstraints();
		gbc_btnNewButton_30.gridx = 4;
		gbc_btnNewButton_30.gridy = 7;
		cancelseat.add(btn_Cseat12, gbc_btnNewButton_30);
		
		JPanel blacklist = new JPanel();
		main_frame.getContentPane().add(blacklist, "blacklist");
		blacklist.setLayout(new BorderLayout(0, 0));
		
		JPanel bl_panel1 = new JPanel();
		blacklist.add(bl_panel1, BorderLayout.CENTER);
		
		JLabel bl_label = new JLabel("请输入用户名:");
		bl_panel1.add(bl_label);
		
		Bl_textField = new JTextField();
		bl_panel1.add(Bl_textField);
		Bl_textField.setColumns(20);
		
		JPanel bl_panel2 = new JPanel();
		blacklist.add(bl_panel2, BorderLayout.SOUTH);
		
		JButton btn_ok = new JButton("确定");
		btn_ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(user_data[Num].getUserType())
				{
					BufferedWriter bw = null;
					try {
						bw = new BufferedWriter(new FileWriter("blacklist.txt",true));
						bw.append("User accounte number:"+Bl_textField.getText()+":"+new Date());
						bw.newLine();
						bw.flush();
					}catch(IOException e){
						System.out.println("记录失败");
					}finally {
						if(bw != null) {
							try {
								bw.close();
							}catch(IOException e) {
								System.out.println("释放记录失败了");
							}
						}
					javax.swing.JOptionPane.showMessageDialog(null, "添加成功!");
					card.show(main_frame.getContentPane(), "menu");
					}
				}
				else
				{
					javax.swing.JOptionPane.showMessageDialog(null, "无权限使用本功能!");
					card.show(main_frame.getContentPane(), "menu");
				}
			}
		});
		bl_panel2.add(btn_ok);

	}
}

