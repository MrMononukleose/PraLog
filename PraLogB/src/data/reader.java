package data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class reader {

	public static void readIn(String URL) {
		String problemname = "";// Name des Problems
		String gutname = "";// Name des Gutes

		float vol_max = 0;// Max. Gesamtvolumen des Lagers
		float kap_b_max = 0;// Max. Kapitalbindung des Lagers
		float c1s = 0;// Bestellkosten Sammelbestellung
		int n = 0;// Anzahl Ladergüter
		// Name des Gutes
		ArrayList<String> gut_name = new ArrayList<String>();
		// Einstandspreis (je Stueck)
		ArrayList<Float> pi = new ArrayList<Float>();
		// Bestellfixe Kosten
		ArrayList<Float> c1i = new ArrayList<Float>();
		// Lagerkostensatz
		ArrayList<Float> c2i = new ArrayList<Float>();
		// Fehlmengenkostensatz
		ArrayList<Float> c3i = new ArrayList<Float>();
		// Volumen des Produkts (je Stueck)
		ArrayList<Float> vi = new ArrayList<Float>();
		// Minimaler Bestand (in Stueck)
		ArrayList<Integer> kmini = new ArrayList<Integer>();
		// Maximaler Bestand (in Stueck)
		ArrayList<Integer> kmaxi = new ArrayList<Integer>();
		// Maximale Lagerdauer (in Perioden)
		ArrayList<Integer> lmaxi = new ArrayList<Integer>();
		// Verbräuche
		ArrayList<String> verbraeuche = new ArrayList<String>();
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
					if (count >= 4 && count <= 4 + n - 1) {
						String[] splitZeile = zeile.split(", ");
						gut_name.add(splitZeile[0]);
						pi.add(Float.parseFloat(splitZeile[1]));
						c1i.add(Float.parseFloat(splitZeile[2]));
						c2i.add(Float.parseFloat(splitZeile[3]));
						c3i.add(Float.parseFloat(splitZeile[4]));
						vi.add(Float.parseFloat(splitZeile[5]));
						kmini.add(Integer.parseInt(splitZeile[6]));
						kmaxi.add(Integer.parseInt(splitZeile[7]));
						lmaxi.add(Integer.parseInt(splitZeile[8]));

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
					if(count >= 4+n) {
						verbraeuche.add(zeile);
					}

					count++;// Zeile hochzählen
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Problemname: " + problemname);
		
		//Tabelle 1		
		System.out.println("Tabelle 1:");
		System.out.println(vol_max);
		System.out.println(kap_b_max);
		System.out.println(c1s);
		
		//Tabelle 2
		String[] columnNames = new String[n + 1];
		for (int i = 0; i < n + 1; i++) {
			if (i == 0) {
				columnNames[i] = "Parameter";
			} else {
				columnNames[i] = "";
			}
		}
		Object[][] data = new Object[9][n+1];
		data[0][0] = "Name";
		data[1][0] = "pi";
		data[2][0] = "c1i";
		data[3][0] = "c2i";
		data[4][0] = "c3i";
		data[5][0] = "vi";
		data[6][0] = "kmini";
		data[7][0] = "kmaxi";
		data[8][0] = "lmaxi";
		for (int j = 1; j <= n; j++) {
			data[0][j] = gut_name.get(j - 1);
			data[1][j] = pi.get(j - 1);
			data[2][j] = c1i.get(j - 1);
			data[3][j] = c2i.get(j - 1);
			data[4][j] = c3i.get(j - 1);
			data[5][j] = vi.get(j - 1);
			data[6][j] = kmini.get(j - 1);
			data[7][j] = kmaxi.get(j - 1);
			data[8][j] = lmaxi.get(j - 1);
		}
		/*JTable table = new JTable(data, columnNames);

		JFrame frame = new JFrame("Tabellen");
		frame.getContentPane().add(new JScrollPane(table));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);*/
		
		//Tabelle 3
		String[] cNames = new String[n];
		for (int i = 0; i < n; i++) {
				cNames[i] = gut_name.get(i);
			 
		}
		int T = verbraeuche.size();
		System.out.println("T:" + T);
		Integer[][] d = new Integer[T][n]; 
		for(int i=0;i<T;i++) {
			String[] splitZeile = verbraeuche.get(i).split(", ");
			for(int j=0;j<n;j++) {
				d[i][j] = Integer.parseInt(splitZeile[j]);
			}
		}
		JTable table = new JTable(d, cNames);

		JFrame frame = new JFrame("Tabellen");
		frame.getContentPane().add(new JScrollPane(table));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);


	}
}
