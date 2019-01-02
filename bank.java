import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Scanner;





public class bank {

	
	public static void main(String args[])
	{
		BufferedReader sn=new BufferedReader(new InputStreamReader(System.in));
		int id,c1,c2,bal;
		String name,q;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=(Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/dbnineb", "root", "");
			con.setAutoCommit(false);
			Statement st=(Statement) con.createStatement();
			System.out.println("Do you want to insert? 1/0");
			c1=Integer.parseInt(sn.readLine());
			boolean ch=true;
			while(ch==true) {
			if(c1==1)
			{
				System.out.println("enter the account nummber");
				id=Integer.parseInt(sn.readLine());
				System.out.println("enter the name");;
				name=sn.readLine();
				System.out.println("enter the balence");
				bal=Integer.parseInt(sn.readLine());
				PreparedStatement ps= con.prepareStatement("insert into bank values(?,?,?)");
				ps.setInt(1, id);
				ps.setString(2, name);
				ps.setInt(3,bal);
				ps.executeUpdate();
				con.commit();
			}
			System.out.println("insert again");
			String c=sn.readLine();
			if(c.equals("y"))
				ch=true;
			else
				ch=false;
			}
			
			boolean ch2=true;
			while(ch2==true)
				{
				Savepoint sp=con.setSavepoint();
				System.out.println("enter the account number");
				int ano=Integer.parseInt(sn.readLine());
				ResultSet rs=st.executeQuery("select balence from bank where ano='"+ano+"'");
				System.out.println("enter the amount to be withdrawn");
				int amt=Integer.parseInt(sn.readLine());
				rs.next();
				int bal1=rs.getInt("balence");
				System.out.println(bal1);
				bal1=bal1-amt;
				q="update bank set balance=balance-? where ano=?";
				PreparedStatement ps1=con.prepareStatement(q);
				ps1.setInt(1, bal1);
				ps1.setInt(2, ano);
				if(bal1<500)
					con.rollback(sp);
				con.releaseSavepoint(sp);
				con.commit();
				
				System.out.println("do more transactions? y/n?");
				String a=sn.readLine();
				if(a.equals("Y"))
					ch2=true;
				else
					ch2=false;
				
				}
			System.out.println("Transaction complete");
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
