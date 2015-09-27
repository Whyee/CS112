
public class Cat extends Pet {
	//extends vars and methods from class Pet and adds the following new vars and methods
	private boolean isIndoor;
	
	Cat(String sp, String b, String n, String s, String a, String g, String c, Shelter l, boolean isIndoor)
	{
		super(sp, b, n, s, a, g, c, l);
		this.isIndoor = isIndoor;
	}  //end constructor
	
	//getters (rest extended thru Pet)
	public boolean isIndoor()
	{
		return isIndoor;
	}  //end isIndoor
	
	//setters (extended thru Pet)
	public void setIsIndoor(boolean condition)
	{
		isIndoor = condition;
	}  //end setIsIndoor
}  //end Cat
