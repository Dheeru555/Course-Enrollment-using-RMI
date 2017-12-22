import java.rmi.*;
import java.rmi.server.*;
import java.util.Random;

import java.net.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class DevineniP2Servant extends UnicastRemoteObject implements DevineniP2RMIInterface
{
public static int finish = 0;
private String Course_No;
private String title;
private String time;
private String Room_No;
private String capacity;
private String Description;
private String credits;
private String faculty;
private String Sec;
private String Day;
private String Status;
private String TimeId;
private String dupcapacity;

private int cnt = 0;

//private int finish = 0;

private String selCourse;

public DevineniP2RMIInterface client=null;

ArrayList<String> det = new ArrayList<String>();
ArrayList<String> Fulldet = new ArrayList<String>();
ArrayList<Integer> BindChecker = new ArrayList<Integer>();

public DevineniP2Servant() throws RemoteException
{
//out.println("In default constructor");
}

public void CourseDetails()
{

File file = new File("CourseDetails.txt");

		try {
            Scanner scanner = new Scanner(file);
			
            while (scanner.hasNextLine()) {
			
                String line = scanner.nextLine();				
				String[] lineArray = line.split("\\|",-1);
			    if(lineArray[0].equals(Course_No))
				{
					Course_No=lineArray[0];
					title=lineArray[1];
					Description=lineArray[2];
					credits=lineArray[3];
					faculty=lineArray[4];
					break;
				}
				
				
            }
			if (Course_No.equals(""))
			{
				System.out.println("Please Check the file data is correct or the line no you have given");
			}
				
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
}


public void fileCount(int counter)
{
	cnt = counter;
}

public int fileCounter() throws RemoteException
{
	return cnt;
}

public void Timings()
{

Random rand = new Random();
int  n = rand.nextInt(4) + 1;
String id = String.valueOf(n);

File file1 = new File("Timings.txt");

		try {
            Scanner scanner = new Scanner(file1);
			
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();				
				String[] lineArray = line.split("\\|",-1);
			    if(lineArray[0].equals(id))
				{
					TimeId=lineArray[0];
					Day=lineArray[1];
					time=lineArray[2];					
				}
				
            }
			if (TimeId.equals(""))
			{
				System.out.println("Please Check the file data is correct or the timeID is correct");
			}
				
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
}

public void Offered(int lineNo)
{
int temp=0;
File file2 = new File("Offered.txt");

		try {
            Scanner scanner = new Scanner(file2);
			
            while (scanner.hasNextLine()) {
				temp++;
                String line = scanner.nextLine();				
				String[] lineArray = line.split("\\|",-1);
			    if(temp == lineNo)
				{
					Course_No=lineArray[0];
					Sec=lineArray[1];
					TimeId=lineArray[2];
					capacity=lineArray[3];
					Status=lineArray[4];					
				}
				
				
            }
			temp++;
			if (Course_No.equals(""))
			{
				System.out.println("Please Check the course number is already has already there or not");
			}
				
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
}


public void countCheck(int lineNo)
{
	int temp=0;
File file4 = new File("Offered.txt");

		try {
            Scanner scanner = new Scanner(file4);
			
            while (scanner.hasNextLine()) {
			temp++;
                String line = scanner.nextLine();				
				String[] lineArray = line.split("\\|",-1);
			    if(temp == lineNo)
				{
					dupcapacity=lineArray[3];
					break;
				}
				
				
            }
			temp = 0;
			if (dupcapacity.equals(""))
			{
				System.out.println("Please Check the file data is correct or the line no you have given");
			}
			if (dupcapacity.equals("0"))
			{
				BindChecker.add(lineNo);
			}
				
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
}

public void addCourses()
{
det.add(Course_No);
det.add(title);
det.add(Description);
}

public void FullDetails()
{
Fulldet.add(Course_No);
Fulldet.add(title);
Fulldet.add(Description);
Fulldet.add(time);
Fulldet.add(capacity);
Fulldet.add(credits);
Fulldet.add(faculty);
Fulldet.add(Sec);
Fulldet.add(Day);
Fulldet.add(Status);
Fulldet.add(TimeId);

}

public void AddBinders(ArrayList<Integer> killer)
{
	BindChecker = killer;
	
	
}

public ArrayList<Integer> Binders() throws RemoteException
{
	//System.out.println("Called here in binders");
	return (BindChecker);
}

public ArrayList<String> FullCourseDetails() throws RemoteException
{
	
	
	return (Fulldet);
}

public ArrayList<String> ListOfCourses() throws RemoteException
{
return(det);
}

public void setCapacityCount(String cnt) throws RemoteException
{
	capacity = cnt;
	System.out.println("Capacity count is:"+cnt);
}
public String getCapacityCount() throws RemoteException
{
	return capacity;
}
public void selectCourse(String course) throws RemoteException
{
	
	selCourse = course;
	System.out.println("Selected course is:"+ selCourse);
}
public String getCourseNo() throws RemoteException
{
	
	return Course_No;
}

public void setClient(DevineniP2RMIInterface c)throws RemoteException{
		client=c;
	}
 
	public DevineniP2RMIInterface getClient() throws RemoteException{
		return client;
	}
	
	
	public void testIt(int finisher) throws RemoteException
	{
		System.out.println("came here");
		finish = finisher;
	}

	public int doIt() throws RemoteException
	{
		return finish;
	}
	public void updateDetails(String cno)throws RemoteException
	{
		File fileToBeModified = new File("Offered.txt");
         
        String oldContent = "";
		String temp1="",temp2="",temp3="",temp4="";
		 
        BufferedReader reader = null;
         
        FileWriter writer = null;
         
        try
        {
            reader = new BufferedReader(new FileReader(fileToBeModified));
             
            //Reading all the lines of input text file into oldContent
             
            String line = reader.readLine();
             
            while (line != null) 
            {
                String[] lineArray1 = line.split("\\|",-1);
				if(lineArray1[0].equals(cno) && lineArray1[4].equals("open"))
				{
					temp1=lineArray1[0];
					temp2=lineArray1[1];
					temp3=lineArray1[2];
					temp4=lineArray1[4];
					System.out.println("File update is under processing");
					System.out.println(temp1 + temp2 + temp3 + temp4);
					
					//System.out.println("capacity value is:" + );
					
					oldContent = oldContent + temp1 + "|" + temp2 + "|"+ temp3 + "|" + capacity + "|" + temp4 + System.lineSeparator();
				}
				else
				{
				oldContent = oldContent + line + System.lineSeparator();
				//System.out.println("----------->" + oldContent);
				}
                line = reader.readLine();
            }
             
            //Replacing oldString with newString in the oldContent
             
            //String newContent = oldContent + temp1 + "|" + temp2 + "|"+ temp3 + "|" + capacity + "|" + temp4;
			String newContent = oldContent;
			System.out.println("Check new content " + newContent);
             
            //Rewriting the input text file with newContent
             
            writer = new FileWriter(fileToBeModified);
             
            writer.write(newContent);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
		 finally
        {
            try
            {
                //Closing the resources
                 
                reader.close();
                 
                writer.close();
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }
	}
	
	}
	
