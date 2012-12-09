package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class reader {

	public static void readIn(String URL) {
		String problemname="";//Name des Problems
		String gutname="";//Name des Gutes
		float vol_max = 0;// Max. Gesamtvolumen des Lagers
		float kap_b_max = 0;// Max. Kapitalbindung des Lagers
		float c1s = 0;// Bestellkosten Sammelbestellung
		int n = 0;// Anzahl Ladergüter
		int count = 1;
		try {
			BufferedReader in = new BufferedReader(new FileReader(URL));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				if (!zeile.substring(0, 1).equals("#")) {
					if (count == 1) {// Problemname einlesen
						problemname = zeile;
					}
					if (count == 2) {// Volmax, KapBmax,c1s einlesen
						String[] splitZeile = zeile.split(",");
						vol_max = Float.parseFloat(splitZeile[0]);
						kap_b_max = Float.parseFloat(splitZeile[1]);
						c1s = Float.parseFloat(splitZeile[2]);
					}
					if (count == 3) {// Anzahl Ladergüter
						n = Integer.parseInt(zeile);
					}
					if (count >= 4 && count <= 4+n-1) {
						String[] splitZeile = zeile.split(", ");
						Gut gut = new Gut(splitZeile[0],
								Float.parseFloat(splitZeile[1]),
								Float.parseFloat(splitZeile[2]),
								Float.parseFloat(splitZeile[3]),
								Float.parseFloat(splitZeile[4]),
								Float.parseFloat(splitZeile[5]),
								Integer.parseInt(splitZeile[6]),
								Integer.parseInt(splitZeile[7]),
								Integer.parseInt(splitZeile[8]));
						System.out.println(gut.getName());
					}

					count++;// Zeile hochzählen
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(problemname);
		System.out.println(vol_max);
		System.out.println(kap_b_max);
		System.out.println(c1s);
		System.out.println(n);
	}
}
