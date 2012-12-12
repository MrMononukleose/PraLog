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
	public int[] d;//Verbrauche

	public Gut(String name, float pi, float c1i, float c2i, float c3i, float vi, int kmini, int kmaxi, int lmaxi, int[] d) {//Konstruktor
		this.name = name;
		this.pi = pi;
		this.c1i = c1i;
		this.c2i = c2i;
		this.c3i = c3i;
		this.vi = vi;
		this.kmini = kmini;
		this.kmaxi = kmaxi;
		this.lmaxi = lmaxi;
		this.d = d;
	}	
	
	public int getSum_d() {
		int sum_d = 0;
		for(int t=0;t<d.length;t++) {
			sum_d+=d[t];
		}
		return sum_d;		
	}
	
	public float getAvg_d() {
		float avg_d = getSum_d()/d.length;		
		return avg_d;
	}
	
	public int[] getMin_d() {
		int[] min_d= new int[2];
		min_d[0]=-1;
		min_d[1]=-1;
		for(int t=0;t<d.length;t++) {
			if(min_d[0]==-1) {
				min_d[0]=d[t];
				min_d[1]=t+1;
			} else {
				if(min_d[0]>d[t]) {
					min_d[0]=d[t];
					min_d[1]=t+1;
				}
			}
		}
		return min_d;
	}
	
	public int[] getMax_d() {
		int[] max_d= new int[2];
		max_d[0]=-1;
		max_d[1]=-1;
		for(int t=0;t<d.length;t++) {
			if(max_d[0]==-1) {
				max_d[0]=d[t];
				max_d[1]=t+1;
			} else {
				if(max_d[0]<d[t]) {
					max_d[0]=d[t];
					max_d[1]=t+1;
				}
			}
		}
		return max_d;
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
	
	public int[] getD() {
		return d;
	}

	public void setD(int[] d) {
		this.d = d;
	}
	
}
