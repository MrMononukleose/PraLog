package data;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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
		// Gesamtverbrauch ueber alle Gueter
		float gesamtverbrauchAlleGueter = 0;
		// Gesamtwert ueber alle Gueter
		float gesamtvolAlleGueter = 0;
		// Gesamtvolumen ueber alle Gueter
		float gesamtwertAlleGueter = 0;
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
					}
					if (count >= 4 + n) {
						verbraeuche.add(zeile);
					}

					count++;// Zeile hochzählen
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Problemname: " + problemname);

		// Tabelle 1
		String[] cNames_t1 = new String[2];
		cNames_t1[0] = "";
		cNames_t1[1] = "";
		Object[][] overall_data = new Object[3][2];
		overall_data[0][0] = "Max. Gesamtvolumen des Lagers";
		overall_data[0][1] = vol_max;
		overall_data[1][0] = "Max. Kapitalbindung des Lagers";
		overall_data[1][1] = kap_b_max;
		overall_data[2][0] = "Bestellkosten Sammelbestellung";
		overall_data[2][1] = c1s;
		JTable table1 = new JTable(overall_data, cNames_t1);
		JLabel label_table1 = new JLabel(
				"Tabelle 1: Uebergreifende Parameter (Zeilen)");
		JPanel panel_table1 = new JPanel();
		panel_table1.setLayout(new BorderLayout());
		panel_table1.add(label_table1, BorderLayout.NORTH);
		panel_table1.add(table1, BorderLayout.SOUTH);
		
		
		// Tabelle 2
		String[] cNames_t2 = new String[n + 1];
		for (int i = 0; i < n + 1; i++) {
			if (i == 0) {
				cNames_t2[i] = "Parameter";
			} else {
				cNames_t2[i] = "";
			}
		}
		Object[][] data = new Object[9][n + 1];
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

		JFrame frame = new JFrame("Uebungsaufgabe");

		JTable table2 = new JTable(data, cNames_t2);
		JLabel label_table2 = new JLabel(
				"Tabelle 2: Parameter (Zeilen) der Gueter (Spalten)");
		JPanel panel_table2 = new JPanel();
		panel_table2.setLayout(new BorderLayout());
		panel_table2.add(label_table2, BorderLayout.NORTH);
		panel_table2.add(table2, BorderLayout.SOUTH);


		// Tabelle 3
		String[] cNames_t3 = new String[n + 1];
		for (int i = 0; i < n + 1; i++) {
			if (i != 0) {
				cNames_t3[i] = gut_name.get(i - 1);
			} else {
				cNames_t3[i] = "";
			}
		}
		int T = verbraeuche.size();
		Object[][] d = new Object[T][n + 1];
		for (int t = 0; t < T; t++) {
			int periode = t + 1;
			d[t][0] = "d" + periode + "i";
			String[] splitZeile = verbraeuche.get(t).split(", ");
			for (int j = 1; j <= n; j++) {
				d[t][j] = Integer.parseInt(splitZeile[j - 1]);
			}
		}

		JTable table3 = new JTable(d, cNames_t3);
		JLabel label_table3 = new JLabel(
				"Tabelle 3: Verbraeuche je Periode (Zeile) der Gueter (Spalten)");
		JPanel panel_table3 = new JPanel();
		panel_table3.setLayout(new BorderLayout());
		panel_table3.add(label_table3, BorderLayout.NORTH);
		panel_table3.add(table3, BorderLayout.SOUTH);

		
		
		//Tabelle4
		String[] cNames_t4 = new String[n + 1];
		for (int i = 0; i < n + 1; i++) {
			if (i != 0) {
				cNames_t4[i] = gut_name.get(i - 1);
			} else {
				cNames_t4[i] = "";
			}
		}
		
		Object[][] kenngroessen = new Object[7][n + 1];
		kenngroessen[0][0] = "Summe der Verbraeuche: ";
		kenngroessen[1][0] = "mittlerer Verbrauch: ";
		kenngroessen[2][0] = "minimaler Verbrauch(Periode): ";
		kenngroessen[3][0] = "maximaler Verbrauch(Periode): ";
		kenngroessen[4][0] = "Gesamtwert: ";
		kenngroessen[5][0] = "Gesamtvolumen: ";
		kenngroessen[6][0] = "Gesamtkosten: ";
		for (int i = 0; i < n; i++) {
			int[] d_this = new int[T];
			for (int t = 0; t < T; t++) {
				d_this[t] = Integer.parseInt(d[t][i + 1].toString());
			}
			Gut gut = new Gut(gut_name.get(i), pi.get(i), c1i.get(i),
					c2i.get(i), c3i.get(i), vi.get(i), kmini.get(i),
					kmaxi.get(i), lmaxi.get(i), d_this);
			float sum_d = gut.getSum_d();
			gesamtverbrauchAlleGueter += sum_d;
			int[] min_d = gut.getMin_d();
			int[] max_d = gut.getMax_d();
			float gesamtwert = gut.calcGesamtwert();
			gesamtwertAlleGueter += gesamtwert;
			float gesamtvolumen = gut.calcGesamtvolumen();
			gesamtvolAlleGueter += gesamtvolumen;
			kenngroessen[0][i+1] = sum_d;
			kenngroessen[1][i+1] = gut.getAvg_d();
			kenngroessen[2][i+1] = min_d[0] + "(" + min_d[1] + ")";
			kenngroessen[3][i+1] = max_d[0] + "(" + max_d[1] + ")";
			kenngroessen[4][i+1] = gut.calcGesamtwert();
			kenngroessen[5][i+1] = gut.calcGesamtvolumen();
			kenngroessen[6][i+1] = gut.calcKosten();
			
		}
		
		JTable table4 = new JTable(kenngroessen, cNames_t4);
		JLabel label_table4 = new JLabel(
				"Tabelle 4: Kenngroessen je Gut");
		JPanel panel_table4 = new JPanel();
		panel_table4.setLayout(new BorderLayout());
		panel_table4.add(label_table4, BorderLayout.NORTH);
		panel_table4.add(table4, BorderLayout.SOUTH);
		
		
		
		JLabel gesamtverbrauch =  new JLabel("Gesamtverbrauch ueber alle Gueter: "
				+ gesamtverbrauchAlleGueter);
		JLabel gesamtwert =  new JLabel("Gesamtwert ueber alle Gueter: "
				+ gesamtwertAlleGueter);
		JLabel gesamtvol =  new JLabel("Gesamtvolumen ueber alle Gueter: "
				+ gesamtvolAlleGueter);
		JPanel panel_5 = new JPanel();
		panel_5.setLayout(new GridLayout(0,1));
		panel_5.add(gesamtverbrauch);
		panel_5.add(gesamtwert);
		panel_5.add(gesamtvol);

		frame.setLayout(new GridLayout(0,1));
		frame.getContentPane().add(new JScrollPane(panel_table1));
		frame.getContentPane().add(new JScrollPane(panel_table2));
		frame.getContentPane().add(new JScrollPane(panel_table3));
		frame.getContentPane().add(new JScrollPane(panel_table4));
		frame.getContentPane().add(new JScrollPane(panel_5));
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);

	}
}
