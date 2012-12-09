package data;

public class Gut {
	
	public String name;//Name des Gutes
	public float pi;//Einstandspreis
	public float c1i;//Bestellfixe Kosten
	public float c2i;//Lagerkostensatz
	public float c3i;//Fehlmengenkostensatz
	public float vi;//Volumen des Produkts (je Stueck)
	public int kmini;//Minimaler Bestand (in Stueck)
	public int kmaxi;//Maximaler Bestand (in Stueck)
	public int lmaxi;//Maximale Lagerdauer (in Perioden)
	
	public Gut(String name, float pi, float c1i, float c2i, float c3i, float vi, int kmini, int kmaxi, int lmaxi) {//Konstruktor
		this.name = name;
		this.pi = pi;
		this.c1i = c1i;
		this.c2i = c2i;
		this.c3i = c3i;
		this.vi = vi;
		this.kmini = kmini;
		this.kmaxi = kmaxi;
		this.lmaxi = lmaxi;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPi() {
		return pi;
	}

	public void setPi(float pi) {
		this.pi = pi;
	}

	public float getC1i() {
		return c1i;
	}

	public void setC1i(float c1i) {
		this.c1i = c1i;
	}

	public float getC2i() {
		return c2i;
	}

	public void setC2i(float c2i) {
		this.c2i = c2i;
	}

	public float getC3i() {
		return c3i;
	}

	public void setC3i(float c3i) {
		this.c3i = c3i;
	}

	public float getVi() {
		return vi;
	}

	public void setVi(float vi) {
		this.vi = vi;
	}

	public int getKmini() {
		return kmini;
	}

	public void setKmini(int kmini) {
		this.kmini = kmini;
	}

	public int getKmaxi() {
		return kmaxi;
	}

	public void setKmaxi(int kmaxi) {
		this.kmaxi = kmaxi;
	}

	public int getLmaxi() {
		return lmaxi;
	}

	public void setLmaxi(int lmaxi) {
		this.lmaxi = lmaxi;
	}
	
}
