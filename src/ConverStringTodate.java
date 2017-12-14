import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConverStringTodate {
	public static void convertStringToDate(String s) {
		
		List<String> myList = new ArrayList<String>(Arrays.asList(s.split("!")));

		DateFormat df = new SimpleDateFormat("mm/dd/yyyy");

		for (String string : myList) {

			System.out.println(string);

			try {
				// Date date = df.parse(string);
				System.out.println(df.parse(string));
			} catch (Exception e) {
				System.out.println("Not a date");
			}
			System.out.println("---------------------");

		}
		System.out.println(myList.size());
	}
}
