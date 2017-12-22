import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;

import java.net.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


class DevineniP2Client
{

public static void main(String[] args)
{


	
int tt = 1;
DevineniP2RMIInterface Imp;
ArrayList<Integer>  BindChecker = new ArrayList<Integer>();
int lineCount = 0;
DevineniP2RMIInterface I1[] = new DevineniP2RMIInterface[20];
ArrayList<String> CourseLists = new ArrayList<String>();

while(true)	
{
try
{

String url1 = "rmi://"+"172.29.76.223"+":"+"10999"+"/" + "test";
Imp = (DevineniP2RMIInterface)Naming.lookup(url1);
BindChecker = Imp.Binders();
//System.out.println("Bind Checker value is:" + BindChecker);
lineCount = Imp.fileCounter();
System.out.println("File Count:" + lineCount);
		
//I1[] = new DevineniP2RMIInterface[lineCount];


//ArrayList<String> CourseLists = new ArrayList<String>();




System.out.println("List of available courses are: ");
System.out.println("Course Number	title	Description");

for(int i=0; i<=lineCount-1; i++)
{
//System.out.println("Bind Checker value is:" + BindChecker);
	for(Integer ji : BindChecker){
		//System.out.println("Bind Checker value is:" + ji);
					if(ji.equals(i))
					{
						tt=0;
						System.out.println("Skip the course");
						
					}
	}
					

if(tt!=0)
{	
try
{
	String url = "rmi://"+"172.29.76.223"+":"+"10999" + "/" + "obj" + String.valueOf(i);
I1[i] = (DevineniP2RMIInterface)Naming.lookup(url);

ArrayList<String> arr = I1[i].ListOfCourses();
	for(String det : arr){
        System.out.print(det+"  ");			
    }
    System.out.print("\n");
	
//System.out.println("Capacity count from server side is:" + I1.getCapacityCount());
//I1.setCapacityCount("4");
}
catch(Exception e)
{
System.out.println("HelloClient exception:" + e);
}
}
tt=1;
}
}
catch(Exception e)
		{
		System.out.println("Objects failed here:"+ e);
		}

						
try
{
	int fixer = 1;
	int fixer2 = 0;
	tt=1;

System.out.println("For enrolling the course pls enter 1, To see full course details pls enter 2, For dropping the course pls enter number 3, To quit press 4");
			Scanner ss = new Scanner(System.in);
			String take = ss.nextLine();
			int n = Integer.parseInt(take);
			int temp=99999,count = 99999;
			
			if(n == 4)
				break;
			
			switch (n) 
        {
            case 1:  System.out.println("Please enter the course number from the above list:");
					  ss = new Scanner(System.in);
					 String take1 = ss.nextLine();
					 fixer = 1;
					 for(int i=0;i<=lineCount-1;i++)
					 {
						 
						 for(Integer ji : BindChecker){
					if(ji.equals(i))
					{
						tt=0;
						System.out.println("Skip the course");
						
					}
	                }
						 if(tt!=0)
						 {
						 if((I1[i].getCourseNo()).equals(take1))
						 {
							 temp = i;
							  
						 }
						 }
							tt=1; 
					 }
					 
					 if(temp == 99999)
					 {
						System.out.println("You didn't entered the correct course. pls check");
						break;						
					 }
					  
					 
					  //ArrayList<String> arr = I1[i].ListOfCourses();
					for(String det : CourseLists){
					if(I1[temp].getCourseNo().equals(det))
					{
						fixer = 0;
						System.out.println("You have already enrolled in the course.");
						break;
					}
					}
					
					if(fixer == 0)
					{
						break;
					}
					
						
					
					
					  //System.out.println("count"+I1[temp].getCapacityCount());
					 count = Integer.parseInt(I1[temp].getCapacityCount());
					 //System.out.println("af cnt");
					 
					 {
						// System.out.println("temp value is"+temp);
						 int var1 = count - 1;
						 I1[temp].setCapacityCount(String.valueOf(var1));
					 }
					 
					
					  
					  System.out.println("Sending course is:"+I1[temp].getCourseNo());
					  
					  String adder = I1[temp].getCourseNo();
					  CourseLists.add(adder);
					  
					//System.out.println("Sending tester value");
					 I1[temp].testIt(1);
						
						//Naming.unbind("rmi://localhost" + "/" + "obj" + String.valueOf(i))
					  
					 I1[temp].updateDetails( I1[temp].getCourseNo());
					 
					 
					 //s1.unbinder(1);		
					 //BindChecker.add(1);
					 //s2.AddBinders(1);
					 
	
					 //System.out.println("value of exiter is: "+ s1.tester());
					 
					 System.out.println("You have successfully enrolled to the course");
                     break;
			case 2:  
			System.out.println("Course Number |	title | Description	 |	time 	|	capcity	 |	credits|faculty|	sec	|	Day	|	Status	|	TimeId");
			for(int k=0;k<=lineCount-1;k++)
					{
						for(Integer ji : BindChecker){
					if(ji.equals(k))
					{
						tt=0;
						System.out.println("Skip the course");
						
					}
	                }
						
					if(tt!=0)	
					{
			         ArrayList<String> arr = I1[k].FullCourseDetails();
					 for(String det : arr){
					System.out.print(det+"  |	");			
					}
					System.out.print("\n");
					}
					tt=1;
					}
					break;
					
			case 3:  
					System.out.println("Below are the courses you have enrolled :");
					
						fixer2 = 0;
					 
					System.out.println(CourseLists);	
					 
					System.out.println("Please choose one among them to drop it :");
					  ss = new Scanner(System.in);
					 take1 = ss.nextLine();
					 for(int i=0;i<=lineCount-1;i++)
					 {
						 for(Integer ji : BindChecker){
					if(ji.equals(i))
					{
						tt=0;
						System.out.println("Skip the course");
						
					}
	                }
					
						if(tt!=0)
						{
						 if((I1[i].getCourseNo()).equals(take1))
						 {
							 temp = i;
							  
						 }
						}
							tt=1; 
					 }
					 
					 
					 if(temp == 99999)
					 {
						System.out.println("You didn't entered the correct course. pls check");
						break;						
					 }
					 
					 
					 for(String det : CourseLists){
					if(I1[temp].getCourseNo().equals(det))
					{
						fixer2 = 1;
						//System.out.println("You haven't given the correct course you have enrolled. pls check");
						//break;
					}
					}
					
					
					if(fixer2 == 0)
					{
						System.out.println("You haven't given the correct course you have enrolled. pls check");
						break;
					}
					
					  
					  //System.out.println("count"+I1[temp].getCapacityCount());
					  count = Integer.parseInt(I1[temp].getCapacityCount());
					 
					
					 
					 {
						// System.out.println("temp value is"+temp);
						 int var1 = count + 1;
						 I1[temp].setCapacityCount(String.valueOf(var1));
					 
					 }
					  
					  //System.out.println("Sending course is:"+I1[temp].getCourseNo());
					  //CourseLists.add(I1[temp].getCourseNo());
					  
					 I1[temp].updateDetails( I1[temp].getCourseNo());
					 
					 
					 int index = CourseLists.indexOf(I1[temp].getCourseNo()); // for your example element would be 2
					 
					 //System.out.println("Index is:"+index);
					 CourseLists.remove(index);
					 
					 
					 
					//System.out.println(CourseLists);	
					 
					 System.out.println("You have successfully dropped from the course");
                     break;
            
            default: System.out.println("Default one:");
                     break;
        }
}
catch(Exception e)
{
System.out.println("HelloClient exception:" + e);
}
}


}

}
