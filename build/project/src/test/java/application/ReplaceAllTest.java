package application;


public class ReplaceAllTest {

	public static void main(String[] args) {
		
		String outputFolder = "C:/Users/xm0v/Desktop/Temp/UE6OO/";
		String refinedOutputFolder = outputFolder.replaceAll("/", "//");

		System.out.println(refinedOutputFolder);
		
	}
	
}
