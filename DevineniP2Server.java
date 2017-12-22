import java.rmi.*;
import java.rmi.server.*;
import java.util.ArrayList;


import java.net.*;
import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

public class DevineniP2Server
{

	public static ArrayList<Integer> BindChecker = new ArrayList<Integer>();
	public static ArrayList<Integer> BindChecker1 = new ArrayList<Integer>();
	
	
	public void Reader()
{
int temp=0;
File file2 = new File("Offered.txt");
BindChecker.clear();
		try {
            Scanner scanner = new Scanner(file2);
			
            while (scanner.hasNextLine()) {
				temp++;
                String line = scanner.nextLine();				
				String[] lineArray = line.split("\\|",-1);
			    if(lineArray[4].equals("closed"))
				{
				
					BindChecker.add(temp-1);					
				}
				
				
            }
			temp++;
				
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
}

	
	
	
	public void updater()
	{
		int temp=0, fixer=0;
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
                temp++;
				String[] lineArray1 = line.split("\\|",-1);
				if(lineArray1[3].equals("0") && lineArray1[4].equals("open"))
				{
					temp1=lineArray1[0];
					temp2=lineArray1[1];
					temp3=lineArray1[2];
					temp4=lineArray1[4];
					System.out.println("File update is under processing");
					System.out.println(temp1 + temp2 + temp3 + temp4);
					
					
					BindChecker1.add(temp-1);
					fixer = 1;
					//System.out.println("capacity value is:" + );
					int ty = Integer.parseInt(temp2)+1;
					
					oldContent = oldContent + temp1 + "|" + temp2 + "|"+ temp3 + "|" + "0" + "|" + "closed" + System.lineSeparator();
					oldContent = oldContent + temp1 + "|" + String.valueOf(ty) + "|"+ temp3 + "|" + "5" + "|" + temp4 + System.lineSeparator();
				System.out.println("----------->" + oldContent);	
					
				}
				else
				{
				oldContent = oldContent + line + System.lineSeparator();
				//System.out.println("----------->" + oldContent);
				}
                line = reader.readLine();
            }
             
            
			String newContent = oldContent;
			//System.out.println("Check new content" + newContent);
             
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
	
	
	
	
	
	
	
	public static void main(String[] argv)
	{
		
		
		DevineniP2Server p1 = new DevineniP2Server();
		
		
	while(true)
	{		
	p1.updater();
	p1.Reader();
	int lines = 0;
	int tt = 1;
	
	try
	{
	BufferedReader reader = new BufferedReader(new FileReader("Offered.txt"));
		lines = 0;
		while (reader.readLine() != null) lines++;
		reader.close();
	}
	catch(Exception e)
		{
		System.out.println("Objects failed here:"+ e);
		}
	
	
	
	DevineniP2Servant obj[] = new DevineniP2Servant[lines];	
	try
	{
		DevineniP2Servant  s1 = new DevineniP2Servant();
		
		
		//System.out.println("lines are:" + lines);
		s1.fileCount(lines);
		s1.AddBinders(BindChecker);
		
		String url2 = "rmi://"+"10.0.0.112"+":"+"10110"+ "/" + "test";
	    Naming.rebind(url2,s1);
		
		
		
	
	for(int i=0; i<=lines-1; i++)
	{	
for(Integer ji : BindChecker){
	if(ji == i)
	{
		tt=0;
						//System.out.println("Skip the course");
	}
}


//System.out.println("File count is:" + lines);
	if(tt == 1)
	{
	obj[i] = new DevineniP2Servant();
	obj[i].Offered(i+1);
	obj[i].CourseDetails();
	obj[i].Timings();
	obj[i].addCourses();
	obj[i].FullDetails();
	ArrayList<String> arr = obj[i].ListOfCourses();
	/*for(String det : arr){
        System.out.print(det+"  ");			
    }
	System.out.print("\n");
	
	ArrayList<String> arr1 = obj[i].FullCourseDetails();
	for(String Fulldet : arr1){
        System.out.print(Fulldet+"  ");			
    }
	System.out.print("\n");*/
	
	String url = "rmi://"+"10.0.0.112"+":"+"10110"+ "/" + "obj" + String.valueOf(i);
	//System.out.println(url);
	Naming.rebind(url,obj[i]);
	//System.out.println(url);
	}
	tt=1;
	}
	
	
	
	for(Integer ji : BindChecker1){
		System.out.println("Bind Checker value is:" + ji);
						
		String url1 = "rmi://"+"10.0.0.112"+":"+"10110"+ "/" + "obj" + String.valueOf(ji);
		
	   Naming.unbind(url1);
	   System.out.println("Unbinder");
	   
	
	}	
	BindChecker1.clear();
	//System.out.println("Valuer"+BindChecker1);
	
	
	
		
	}	
	catch(Exception e)
		{
		System.out.println("Objects failed here:"+ e);
		}
	}	
	
	
	
	
	
		
	}

}