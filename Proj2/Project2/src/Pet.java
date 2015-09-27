
public class Pet {
	//below are variables that are general to all the animal types, as specific species will extend this class
	private String species;
	private String breed;
	private String name;
	private String size;
	private String age;
	private String gender;
	private String city;
	private Shelter location;
	
	Pet(String sp, String b, String n, String s, String a, String g, String c, Shelter l)
	{
		species = sp;
		breed = b;
		name = n;
		size = s;
		age = a;
		gender = g;
		city = c;
		location = l;
	}  //end constructor
	
	//getters
	public String getSpecies()
	{
		return species;
	}  //end getSpecies
	
	public String getBreed()
	{
		return breed;
	}  //end getBreed
	
	public String getName()
	{
		return name;
	}  //end getName
	
	public String getSize()
	{
		return size;
	}  //end getSize
	
	public String getAge()
	{
		return age;
	}  //end getAge
	
	public String getGender()
	{
		return gender;
	}  //end getGender
	
	public String getCity()
	{
		return city;
	}  //end getCity
	
	public Shelter getLocation()
	{
		return location;
	}  //end getLocation
	
	//setters
	public void setSpecies(String sp)
	{
		species = sp;
	}  //end setSpecies
	
	public void setBreed(String b)
	{
		breed = b;
	}  //end setBreed
	
	public void setName(String n)
	{
		name = n;
	}  //end setName
	
	public void setSize(String s)
	{
		size = s;
	}  //end setSize
	
	public void setAge(String age)
	{
		this.age = age;
	}  //end setAge
	
	public void setGender(String g)
	{
		gender = g;
	}  //end setGender
	
	public void setCity(String c)
	{
		city = c;
	}  //end setCity
	
	public void setLocation(Shelter l)
	{
		location = l;
	}  //end setLocation
	
	public String toString()
	{
		//prints out the Pet's information in a nicely formatter way.
		return species + ", " + name + ", " + breed + ", " + age + ", " + size + "\nShelter: " + location;
	}  //end toString
	
}  //end Pet
