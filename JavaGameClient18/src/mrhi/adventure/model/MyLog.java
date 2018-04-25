package mrhi.adventure.model;

public class MyLog {
	public static boolean print = true;
	
	public static void log(String str)
	{
		if(print==true)
			System.out.println(str);
	}
}
