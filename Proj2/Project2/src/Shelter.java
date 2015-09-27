
public class Shelter {
	//defines vars and methods for Shelter objects
	private String name;
	private String street;
	private String city;
	private String state;
	private int zip;
	private String phone;
	
	Shelter(String n, String s, String c, String st, int z, String p)
	{
		name = n;
		street = s;
		city = c;
		state = st;
		zip = z;
		phone = p;
	}  //end constructor
	
	//getters
	public String getName()
	{
		return name;
	}  //end getName
	
	public String getStreet()
	{
		return street;
	}  //end getStreet
	
	public String getCity()
	{
		return city;
	}  //end getCity
	
	public String getState()
	{
		return state;
	}  //end getState
	
	public int getZip()
	{
		return zip;
	}  //end getZip
	
	public String getPhone()
	{
		return phone;
	}  //end getPhone
	
	//setters
	public void setName(String n)
	{
		name = n;
	}  //end setName
	
	public void setStreet(String s)
	{
		street = s;
	}  //end setStreet
	
	public void setCity(String c)
	{
		city = c;
	}  //end setCity
	
	public void setState(String s)
	{
		state = s;
	}  //end setState
	
	public void setZip(int z)
	{
		zip = z;
	}  //end setZip
	
	public void setPhone(String p)
	{
		phone = p;
	}  //end setPhone
	
	public String toString()
	{
		//prints info in a nicely formatted fashion
		return name + ",\n" + street + ",\n" + city + "," + state
				+ " " + zip + ",\n" + phone;
	}  //end toString
}  //end Shelter
