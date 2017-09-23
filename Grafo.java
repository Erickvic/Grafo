package representar;

import java.util.ArrayList;

public class Grafo {
	
	private String[] vertices;
	private String[] arestas;
	private static int[][] matrizAd;
	private static int[][] matrizInc;
	private static ArrayList<ArrayList<String>> ListaAd;
	private static boolean orientado;
	private static boolean valorado;
	
	
	
	public String[] getVertices() {
		return vertices;
	}
	public void setVertices(String[] vertices) {
		this.vertices = vertices;
	}
	public String[] getArestas() {
		return arestas;
	}
	public void setArestas(String[] arestas) {
		this.arestas = arestas;
	}
	public static int[][] getMatrizAd() {
		return matrizAd;
	}
	public static void setMatrizAd(int[][] matrizAd) {
		Grafo.matrizAd = matrizAd;
	}
	public static int[][] getMatrizInc() {
		return matrizInc;
	}
	public static void setMatrizInc(int[][] matrizInc) {
		Grafo.matrizInc = matrizInc;
	}
	public static ArrayList<ArrayList<String>> getListaAd() {
		return ListaAd;
	}
	public static void setListaAd(ArrayList<ArrayList<String>> listaAd) {
		ListaAd = listaAd;
	}
	public static boolean isOrientado() {
		return orientado;
	}
	public static void setOrientado(boolean orientado) {
		Grafo.orientado = orientado;
	}
	public static boolean isValorado() {
		return valorado;
	}
	public static void setValorado(boolean valorado) {
		Grafo.valorado = valorado;
	}
	
}
