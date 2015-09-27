
public class Bird extends Pet {
	//this class extends Pet, and will either take on or update its variables and methods
	boolean canTalk;  //new specific boolean added for Bird
	
	Bird(String sp, String b, String n, String s, String a, String g, String c, Shelter l, boolean ct)
	{
		super(sp, b, n, s, a, g, c, l);
		canTalk = ct;
	}  //end constructor
	
	//methods not shown here from Pet can also be called using a Bird object
	//getters (extended thru Pet)
	public boolean canTalk()
	{
		return canTalk;
	}  //end canTalk
	
	//setters (extended thru Pet)
	public void setCanTalk(boolean condition)
	{
		canTalk = condition;
	}  //end setCanTalk
}  //end Bird
