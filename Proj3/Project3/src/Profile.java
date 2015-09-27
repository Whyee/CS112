
public class Profile {
	protected String type;
	protected String name;
	protected String imageFile;
	
	Profile(String n, String img)
	{
		name = n;
		imageFile = img;
	}  //end constructor
	
	//getters
	public String getType()
	{
		return type;
	}  //end getType
	
	public String getName()
	{
		return name;
	}  //end getName
	
	public String getImgSource()
	{
		return imageFile;
	}  //end getImgSource
	
	//setters
	public void setName(String n)
	{
		name = n;
	} //end setName
	
	public void setImage(String i)
	{
		imageFile = i;
	}  //end setImage
	
	@Override
	public String toString()
	{
		return name + " " + imageFile;
	}
	
}  //end Profile
