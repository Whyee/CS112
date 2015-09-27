
public class Dog extends Pet {
	//extends the methods and vars of Pet, and then adds/updates the following vars and methods
	private boolean isHunting;
	
	Dog(String sp, String b, String n, String s, String a, String g, String c, Shelter l, boolean isHunting)
	{
		super(sp, b, n, s, a, g, c, l);
		this.isHunting = isHunting;
	}  //end constructor
	
	//getters (extended thru pet)
	public boolean isHunting()
	{
		return isHunting;
	}  //end isHunting
	
	//setters (extended thru Pet)
	public void setisHunting(boolean condition)
	{
		isHunting = condition;
	}  //end setisHunting
}  //end Dog
