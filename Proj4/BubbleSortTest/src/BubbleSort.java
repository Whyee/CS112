
public class BubbleSort {
	public static int[] sort(int[] arr)
	{
		for (int k = 0; k < arr.length; k++)
		{
			int temp;
			for (int i = 0; i < (arr.length - (k + 1)); i++)
			{
				System.out.println("At " + arr[i]);
				if(arr[i] > arr[i+1])
				{
					System.out.println("Switching " + arr[i] + " and "+ arr[i+1]);
					temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
				}	
				
			}
		}
		return arr;
	}

}
