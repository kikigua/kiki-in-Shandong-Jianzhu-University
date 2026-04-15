package Sort;

public class ShellSortT {
	public static void main(String[] args) {
		long startTime=System.currentTimeMillis();
		int len=30000;
		int []tmp = null;
		int[] arr=new int[len];
		for(int i=0;i<len;i++)
		{
			arr[i]=(int)(Math.random()*100000);
			
		}
		ShellSort.shellSort(arr);
	
	for(int i=0;i<len;i++) {
		System.out.println(arr[i]);
		
	}
	long endTime=System.currentTimeMillis();
	System.out.println("运行时间");
	System.out.println(endTime-startTime);
}
}
