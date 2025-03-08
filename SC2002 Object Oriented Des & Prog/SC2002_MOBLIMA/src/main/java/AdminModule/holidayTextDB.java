package AdminModule;

import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class holidayTextDB {

	private final static String filename = "holidays.txt";

    // reading
	public static ArrayList readHolidays() throws IOException {
		// read String from text file
		ArrayList stringArray = (ArrayList)read();
		ArrayList alr = new ArrayList() ;// to store Holiday data

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				// create Holiday object from file data
				Holiday day = new Holiday(st);
				// add to Holiday list
				alr.add(day) ;
			}
			return alr ;
	}

  // saving
	public static void saveHoliday(List al) throws IOException {
		List alw = new ArrayList() ;// to store Holiday data

        for (int i = 0 ; i < al.size() ; i++) {
				Holiday date = (Holiday)al.get(i);
				StringBuilder st =  new StringBuilder() ;
				st.append(date.getDate().trim());
				alw.add(st.toString()) ;
			}
			write(alw);
	}

  /** Write fixed content to the given file. */
	public static void write(List data) throws IOException  {
		PrintWriter out = new PrintWriter(new FileWriter(filename));

		try {
			for (int i =0; i < data.size() ; i++) {
				out.println((String)data.get(i));
			}
		}
		finally {
			out.close();
		}
	}

  /** Read the contents of the given file. */
	public static List read() throws IOException {
		List data = new ArrayList() ;
		Scanner scanner = new Scanner(new FileInputStream(filename));
		try {
		while (scanner.hasNextLine()){
			data.add(scanner.nextLine());
		}
		}
		finally{
		scanner.close();
		}
		return data;
	}

	public static boolean isExistingHoliday(String date) throws IOException{
		ArrayList<Holiday> list = readHolidays();
		for(Holiday h : list){
			if(h.getDate().compareTo(date) == 0){
				return true;
			}
		}
		return false; //holiday does not exist
	}

	public static void printHolidays() throws IOException{
		ArrayList<Holiday> days = readHolidays();
		for(Holiday h : days){
			System.out.println(h.getDate());
		}
		System.out.println();
	}
}