package com.sf.boxmaster;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

class Readmap {
	private int level, mx, my;
	private int[][] mymap = new int[20][20];
	FileReader r;
	BufferedReader br;
	String bb = "";
	int[] x;
	int c = 0;

	Readmap(int k) {
		level = k;
		String s;
		try {
			File f = new File("maps\\" + level + ".map");
			r = new FileReader(f);
			br = new BufferedReader(r);
		} catch (IOException e) {
			System.out.println(e);
		}
		try {
			while ((s = br.readLine()) != null) {
				bb = bb + s;

			}
		} catch (IOException g) {
			System.out.println(g);
		}
		byte[] d = bb.getBytes();
		int len = bb.length();
		int[] x = new int[len];
		for (int i = 0; i < bb.length(); i++)
			x[i] = d[i] - 48;
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 20; j++) {
				mymap[i][j] = x[c];
				if (mymap[i][j] == 5) {
					mx = j;
					my = i;
				}
				c++;
			}
		}
	}

	int[][] getmap() {
		return mymap;
	}

	int getmanX() {
		return mx;
	}

	int getmanY() {
		return my;
	}
}