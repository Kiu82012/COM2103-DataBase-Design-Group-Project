import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
public class RecruitmentManagementSystem {
	
	private static final PreparedStatement PreparedStatement = null;
	static Scanner kb = new Scanner(System.in);
	static String id; //get user id
	int admin; //check if is admin
	int x = 0; //for checking whether it has record 
	Date SystemDate = new Date();
	int doPosting;
	
	public void dateUpdate() {
		try {
		Class.forName("org.mariadb.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitmentmanagementsystem","s216741","123");
        Statement stmt = con.createStatement();
        stmt.executeUpdate("update SYSTEM set SYSTEM_DATE=date(now())");
        ResultSet rs = stmt.executeQuery("select SYSTEM_DATE from SYSTEM");

        while (rs.next()) {
            for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print("Date: " + rs.getString(i + 1) + " ");
                SystemDate = rs.getDate(1); // set date
            }
            System.out.println();
        }
        stmt.close();
        con.close();
    } catch (Exception e) {
        System.out.println(e);
    }
}
	
	public boolean doLogin() {
        boolean login = false;
        System.out.println("------------------------------------------------------------------------------------------");

        System.out.println("Please enter your sid");
        String sid = kb.next(); //get user id from user
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Please enter your password");
        String password = kb.next(); //get password from user

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitmentmanagementsystem","s216741","123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select Password from User_Detail where User_ID = " + sid); //get pw from database
            rs.next();
            String pw = rs.getString(1); //set password to pw
            if (pw.equals(password)) { //check password
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("Login Successful");
                login = true; //set login to true
                id = sid; // set the id to sid to set the identity
            } else {
                System.out.println("------------------------------------------------------------------------------------------");
                System.out.println("Login failed"); //by wrong pw
                System.out.println("------------------------------------------------------------------------------------------");
            }
            ResultSet rss = stmt.executeQuery("select Admin from User_Detail where User_ID = " + sid);
            rss.next();
            admin = rss.getInt(1);
            
           

            stmt.close();
            con.close();
	        
        }catch (Exception e) {
            System.out.println("------------------------------------------------------------------------------------------");
            System.out.println("Login failed"); //by wrong pw
            System.out.println("------------------------------------------------------------------------------------------");
        }
        return login;
    }

	public void doDateAdjustment() {
        String date;
        if (admin == 1) {
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("Please enter the date(YYYY-MM-DD):");
        date = kb.next();
        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitmentmanagementsystem","s216741","123");
            Statement stmt = con.createStatement();
            stmt.executeUpdate("update SYSTEM set SYSTEM_DATE= '" + date + "'");
            ResultSet rs = stmt.executeQuery("select SYSTEM_DATE from SYSTEM");
            while (rs.next()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i++) {
                    System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                    System.out.print("Sucessfully adjusted the date: " + rs.getString(i + 1) + " ");
                }
                SystemDate = rs.getDate(1);
                System.out.println();
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Please enter the correct date format");
        }
        } else {
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("You are not the admin");
        }
    }
	
	public void doDisplayApplicant() {
		try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitmentmanagementsystem","s216741","123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from applicant"); //select all the applicant 

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-25s %-25s %-25s %-50s %-50s", "ID", "Name", "email", "Phone","Experience","Education");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i = i + 7) { //to show all column
                    System.out.format("%-10s %-25s %-25s %-25s %-50s %-50s", rs.getString(i + 1), rs.getString(i + 2), rs.getString(i + 3), rs.getString(i + 4), rs.getString(i + 5),rs.getString(i + 6));
                }
                System.out.println();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public void doDisplayEmployer() {
		try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitmentmanagementsystem","s216741","123");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from employer"); //select all the employer 

            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-10s %-15s %-15s %-25s", "User_ID", "User_Name", "Contact","Email");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            while (rs.next()) {
                for (int i = 0; i < rs.getMetaData().getColumnCount(); i = i + 5) { //to show all column
                    System.out.format("%-10s %-15s %-15s %-25s", rs.getString(i + 1), rs.getString(i + 2), rs.getString(i + 3),rs.getString(i + 4));
                }
                System.out.println();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            }
            stmt.close();
            con.close();
        } catch (Exception e) {
            System.out.println(e);
        }
	}
	
	public void doSearching() {
		String target;
		System.out.print("Please enter the date (YYYY-MM-DD): ");
		target = kb.next();
		LocalDate date = LocalDate.parse(target, DateTimeFormatter.ISO_LOCAL_DATE);
		try {	
			Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitmentmanagementsystem","s216741","123");
            Statement stmt = con.createStatement();
           
            ResultSet rs = stmt.executeQuery("select * from job where date_published = '"+date+"'");
            
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-25s %-25s %-25s %-35s %-50s %-25s", "ID", "Name","Description","Date Published","Salary","Academic Requirements","Company");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(rs.next()) {
            	for (int i = 0; i < rs.getMetaData().getColumnCount(); i = i+8 ) {
            		System.out.format("%-20s %-25s %-25s %-25s %-35s %-50s %-25s",rs.getString(i + 1),rs.getString(i + 2),rs.getString(i + 3),rs.getString(i +4),rs.getString(i + 5),rs.getString(i + 6),rs.getString(i + 7));
            	}
            }
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            stmt.close();
            con.close();            
		}catch(Exception e) {
			System.out.println(e);
		}          
	}
	
	public void doPosting() {
		String name,description,salary,academic,company;
		boolean done = true;
		long num = 0;
		ResultSet rs ;
        
    
		try {
			Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitmentmanagementsystem","s216741","123");
            Statement stmt = con.createStatement();
           

		if(admin==1) {
			do {
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Please enter the job name: ");
				kb.nextLine();
				name = kb.nextLine();
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Please enter the description: ");
				description = kb.nextLine();
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Please enter the Salary (HKD) : ");
				salary = kb.nextLine();
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Please enter the academic requirment: ");
				academic = kb.nextLine();
				System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
				System.out.println("Please enter the company name: ");
				company = kb.nextLine();
				done = false;
			}while(done);
			
			rs = stmt.executeQuery("insert into job (name, description, date_published, salary, `academic requirements`, company) values ( '"+name+"','"+description+"','"+SystemDate+"','HKD "+salary+"','"+academic+"','"+company+"');");
			System.out.println("Enter Succeed!");
			 rs=stmt.executeQuery("Update system set doposting=1 ");
			rs=stmt.executeQuery("select * from job");
			System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.printf("%-20s %-35s %-25s %-25s %-35s %-70s %-25s", "ID", "Name","Description","Date Published","Salary","Academic Requirements","Company");
            System.out.println();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            while(rs.next()) {
            	for (int i = 0; i < rs.getMetaData().getColumnCount(); i = i+8 ) {
            		System.out.format("%-20s %-35s %-25s %-25s %-35s %-70s %-25s",rs.getString(i + 1),rs.getString(i + 2),rs.getString(i + 3),rs.getString(i +4),rs.getString(i + 5),rs.getString(i + 6),rs.getString(i + 7));
            		System.out.println();
                    
            	}
            }
        
		}
		else 
			System.out.println("You are not admin!");
		stmt.close();
        con.close();
		} catch (Exception e) {
					System.out.println(e);
				}
		}
	
	public void doCounting() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitmentmanagementsystem","s216741","123");
            Statement stmt = con.createStatement();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            System.out.println("Please enter the required job description: ");
            kb.nextLine();
            String target = kb.nextLine();
            System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            ResultSet rs = stmt.executeQuery("select count(*) from job where description ='" + target +"'"); //find the no. that fulfill required
            rs.next();
            int fulfilltarget = rs.getInt(1);  
            System.out.println(fulfilltarget);
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void doDisplayHistory() {
		try {
			Class.forName("org.mariadb.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitmentmanagementsystem","s216741","123");
            Statement stmt = con.createStatement();
            System.out.println("Please enter the id of the target applicant: ");
            kb.nextLine();
            String target = kb.nextLine();
            
            ResultSet rs = stmt.executeQuery("select id,date_of_application,job_id from application where applicant_id1 = '"+target+"'");
            ResultSet rss = stmt.executeQuery("select id,date_of_application,job_id from application where applicant_id1 = '"+target+"'");
            if(rss.next()) {
            	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
            	System.out.printf("%-10s %-25s %-20s", "id", "date_of_application","job_id");
            	System.out.println();
            	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");            	
            }
            while(rs.next()) {
            	for(int i = 0; i < rs.getMetaData().getColumnCount(); i = i+4) {
            		System.out.format("%-10s %-25s %-20s ", rs.getString(i + 1), rs.getString(i + 2), rs.getString(i + 3) );
            	}
            	x++;
                System.out.println();
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");           
            }
            if (x == 0) {
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("No such record");
            } else if (x != 0) {
                x = 0;
            }
            stmt.close();
            con.close();
		}catch(Exception e) {
			System.out.println(e);
		}
	}
	
	public void doDisplayMenu(){
        System.out.printf("%-20s %-30s %n" , "Command", "Discription");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-30s %n" , "1", "Display all applicants");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-30s %n" , "2", "Display all employers");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-30s %n" , "3", "Display all jobs within specific date range");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-30s %n" , "4", "Posting job description");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-30s %n" , "5", "Counting number of job match with description");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-30s %n" , "6", "Display history of an applicant");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-30s %n" , "7", "Adjust date");
        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.printf("%-20s %-30s %n" , "8", "Exit");
        
    
    }
	
	public void doAutoReminder() {
	    try {
	        Class.forName("org.mariadb.jdbc.Driver");
	        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/recruitmentmanagementsystem", "s216741", "123");
	        Statement stmt = con.createStatement();

	        
	        ResultSet rs;
	        boolean stop=false;
	        while (!stop) {
	            rs = stmt.executeQuery("select doPosting from System;");
	            int x = 0;
	            int userid=0;
	            boolean fit = false;
	            
	            if (rs.next()) {
	                x = rs.getInt("doPosting");
	            }
	            
	            if (x == 1) {
	                rs = stmt.executeQuery("select id from job order by id desc limit 1;");//done
	                int latestid=0;
	                if(rs.next()) {
	                     latestid = rs.getInt("id");
	               }
					
	                    rs = stmt.executeQuery("select description from job where id=" + latestid + ";");
	                    
	                    
	                    String target = "";
	                    if(rs.next()) {
	                        target = rs.getString("description");
	                    }
	                    for (int i = 1; i <= 10; i++) {
	                        rs = stmt.executeQuery("select experience from applicant where id=" + i + ";");
	                        String ad = "";
	                        if(rs.next()) {
	                        ad = rs.getString("experience");
	                        	if (target.equals(ad)) {
	                        		fit = true;
	                        		userid = i;
	                        	}
	                        
	                        }
	                    }
	                if(userid==Integer.parseInt(id))
	                	System.out.println("A new job that matches with you is entered.");
	               stmt.executeQuery("update System set doPosting= 0;");
	            }
	            stop=true;
	        }
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	}
	
	public void process() {
        boolean end = false;

        if (doLogin() == true) {
        	System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
        	doDisplayMenu();
            while (!end) {
                               
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.println("Please enter your command");
                System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                System.out.print(">");
                String command = kb.next();
                switch (command) {
                    case "1":
                        doDisplayApplicant();
                        break;

                    case "2":
                        doDisplayEmployer();
                        break;

                    case "3":
                        doSearching();
                        break;

                    case "4":
                        doPosting();
                        break;

                    case "5":
                        doCounting();
                        break;

                    case "6":
                        doDisplayHistory();
                        break;

                    case "7":
                    	doDateAdjustment();
                        break;

                    case "8":
                        end = true;
                        System.out.println("------------------------------------------------------------------------------------------");
                        System.out.println("Good Bye");
                        System.out.println("------------------------------------------------------------------------------------------");
                        System.exit(0);

                    default:
                        System.out.println("------------------------------------------------------------------------------------------");
                        System.out.println(id);
                        System.out.println("------------------------------------------------------------------------------------------------------------------------------------------------------");
                        doDisplayMenu();
                        break;
                }

            }
        } else {

        }
    }
	
	 public static void main(String[] args) {
		        // TODO code application logic here
			 RecruitmentManagementSystem program = new RecruitmentManagementSystem();
			  System.out.println("Please enter your user id first");
		        id=kb.next();
		        program.dateUpdate();
		        
		        program.doAutoReminder();
		        program.process();
		        
		        
		     
		       
		    }
	
}
