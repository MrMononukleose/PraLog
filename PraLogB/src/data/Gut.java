package data;

public class Gut {
	
	public String name;//Name des Gutes
	public float p;//Einstandspreis
	public float c1;//Bestellfixe Kosten
	public float c2;//Lagerkostensatz
	public float c3;//Fehlmengenkostensatz
	public float v;//Volumen des Produkts (je Stueck)
	public int kmini;//Minimaler Bestand (in Stueck)
	public int kmaxi;//Maximaler Bestand (in Stueck)
	public int lmaxi;//Maximale Lagerdauer (in Perioden)
	public int[] d;//Verbrauche
	public int[] z;//Lagerbestaende am Ende einer Periode
	public int[] bestellpolitik;

	public Gut(String name, float p, float c1, float c2, float c3, float v, int kmini, int kmaxi, int lmaxi, int[] d) {//Konstruktor
		this.name = name;
		this.p = p;
		this.c1 = c1;
		this.c2 = c2;
		this.c3 = c3;
		this.v = v;
		this.kmini = kmini;
		this.kmaxi = kmaxi;
		this.lmaxi = lmaxi;
		this.d = d;
	}	
	
	public void setBestellpolitik(int[] bestellpolitik) {
		for(int i=0;i<d.length;i++) {
			if(i==0 || i%3==0) {
				this.bestellpolitik[i] = 1;
			} else {
				this.bestellpolitik[i] = 0;
			}
		}
	}
	
	public int calcKosten() {
		int c=0;
		for(int i=0;i<d.length;i++) {
			if(i==0 || i%3==0) {
				int x = calcBestellmenge(i+1);
				if(x>0) {
					c+=c1;
				}
			}
			c+=d[i]*c2*p;
		}
		
		return c;
	}
	
	public int calcBestellmenge(int t) {
		int x=0;
		for(int i=t;i<t+3;i++) {
			x+=d[i-1];
		}
		return x;
	}
	
	public float calcGesamtwert() {
		float gesamtwert=0;
		for(int i=0;i<d.length;i++) {
			gesamtwert+=p*d[i];
		}
		return gesamtwert;
	}
	
	public float calcGesamtvolumen() {
		float gesamtvolumen=0;
		for(int i=0;i<d.length;i++) {
			gesamtvolumen+=v*d[i];
		}
		return gesamtvolumen;
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

	

	public float getP() {
		return p;
	}

	public void setP(float p) {
		this.p = p;
	}

	public float getC1() {
		return c1;
	}

	public void setC1(float c1) {
		this.c1 = c1;
	}

	public float getC2() {
		return c2;
	}

	public void setC2(float c2) {
		this.c2 = c2;
	}

	public float getC3() {
		return c3;
	}

	public void setC3(float c3) {
		this.c3 = c3;
	}

	public float getV() {
		return v;
	}

	public void setV(float v) {
		this.v = v;
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
