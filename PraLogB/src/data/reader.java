package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class reader {

	public static void readIn(String URL) {
		String problemname="";
		float vol_max=0;
		float kap_b_max=0;
		int count = 1;
		try {
			BufferedReader in = new BufferedReader(new FileReader(URL));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				if (count==2) {
					problemname = zeile;
				}
				if (count==4) {
					String[] splitZeile = zeile.split(",");
					vol_max = Float.parseFloat(splitZeile[0]);
					kap_b_max = Float.parseFloat(splitZeile[1]);								
				}
					count++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(problemname);
		System.out.println(vol_max);
		System.out.println(kap_b_max);

	}
}
