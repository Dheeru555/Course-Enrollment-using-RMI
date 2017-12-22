import java.rmi.*;
import java.util.ArrayList;
public interface DevineniP2RMIInterface extends Remote
{
public ArrayList<String> ListOfCourses() throws RemoteException;
public ArrayList<String> FullCourseDetails() throws RemoteException;
public void setCapacityCount(String cnt) throws RemoteException;
public String getCapacityCount() throws RemoteException;
public void selectCourse(String course) throws RemoteException;
public String getCourseNo() throws RemoteException;

public void updateDetails(String cno)throws RemoteException;

public void testIt(int finisher) throws RemoteException;
public int doIt() throws RemoteException;


public ArrayList<Integer> Binders() throws RemoteException;
public int fileCounter() throws RemoteException;

public void setClient(DevineniP2RMIInterface c)throws RemoteException;
public DevineniP2RMIInterface getClient() throws RemoteException;

}