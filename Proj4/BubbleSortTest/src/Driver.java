
public class Driver {
	public static void main(String[] args)
	{
		int[] toSort = {4,6,1,8,5};
		
		int[] sorted = BubbleSort.sort(toSort);
		
		for (int i = 0; i < sorted.length; i++)
		{
			System.out.println(sorted[i]);
		}
	}
}
