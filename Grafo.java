package representar;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Random;

import javax.swing.JOptionPane;

public class Grafo {
	
	private static ArrayList<String> vertices = new ArrayList<String>();
	private static ArrayList<ArrayList<Integer>> listaArestas = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> matrizAd = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> matrizInc = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<ArrayList<Integer>>> ListaAd = new ArrayList<ArrayList<ArrayList<Integer>>>();
	private static boolean orientado;
	private static boolean valorado;
//	Dijkstra
	private static ArrayList<Integer> dist = new ArrayList<Integer>();
	private static ArrayList<Integer> anterior = new ArrayList<Integer>();
//	Bellman-Ford
	private static ArrayList<Integer> distBF = new ArrayList<Integer>();
	private static ArrayList<Integer> anteriorBF = new ArrayList<Integer>();
//	Floyd-Warshall
	private static ArrayList<ArrayList<Integer>> distFW = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> anteriorFW = new ArrayList<ArrayList<Integer>>();
//	Prim-Jarnik
	private static ArrayList<Integer> distPJ = new ArrayList<Integer>();
	private static ArrayList<Integer> anteriorPJ = new ArrayList<Integer>();
	private static ArrayList<Integer> vertsAdd = new ArrayList<Integer>();
//	Kruskal
	private static ArrayList<ArrayList<Integer>> Uniao = new ArrayList<ArrayList<Integer>>();
	private static ArrayList<ArrayList<Integer>> arestAdd = new ArrayList<ArrayList<Integer>>();

	
	

	
	public void addVertice(String vert){
		vertices.add(vert);
		
		ArrayList<Integer> aresTemp = new ArrayList<Integer>();
		for(int i =0 ; i<vertices.size(); i++){
			aresTemp.add(0);
		}
		matrizAd.add(aresTemp);
		for(int i =0 ; i<vertices.size(); i++){
			matrizAd.get(i).add(vertices.size()-1, 0);
		}
		
		ArrayList<ArrayList<Integer>> novoVert = new ArrayList<ArrayList<Integer>>();
		ListaAd.add(novoVert);
	}
	
	public void addAresta(ArrayList<Integer> ares){
//		Lista de Arestas
		listaArestas.add(ares);
		
//		Matriz Adjacência
		if(ares.get(2) == 0){
			ares.set(2, 1);
		}
		matrizAd.get(ares.get(0)).set(ares.get(1), 1 * ares.get(2));
		if(!orientado){
			matrizAd.get(ares.get(1)).set(ares.get(0), 1 * ares.get(2));
		}
		
//		Matriz Incidência
		ArrayList<Integer> tempInc = new ArrayList<Integer>();
		for(int i = 0; i<vertices.size();i++){
			tempInc.add(i, 0);
		}
		for(int i =0 ; i<matrizInc.size(); i++){
			matrizInc.get(i).add(vertices.size(), 0);
		}
		tempInc.set(ares.get(0), 1* ares.get(2));
		int inc = orientado? -1: 1;
		tempInc.set(ares.get(1), inc*ares.get(2));
		matrizInc.add(tempInc);
		
//		Lista de Adjacência
		ArrayList<Integer> temp = new ArrayList<Integer>();
		ArrayList<Integer> temp2 = new ArrayList<Integer>();
		temp.add(ares.get(1));
		temp.add(ares.get(2));
		ListaAd.get(ares.get(0)).add(temp);
		if(!orientado){
			temp2.add(ares.get(0));
			temp2.add(ares.get(2));
			ListaAd.get(ares.get(1)).add(temp2);
		}		

	}
	
	public void Dijkstra(int inicio){
		dist = new ArrayList<Integer>();
		anterior = new ArrayList<Integer>();
		PriorityQueue<Integer> fila = new PriorityQueue<Integer>();
		
		for (int i = 0; i<vertices.size(); i++) {			
			dist.add(i, Integer.MAX_VALUE/2);
			anterior.add(i, null);
		}
		dist.set(inicio, 0);
		fila.add(inicio);
		
		while(!fila.isEmpty()){
			int u = fila.poll();
			
				for (ArrayList<Integer> adj : Grafo.getListaAd().get(u)) {
					if(dist.get(u)+ adj.get(1) < dist.get(adj.get(0))){
						fila.add(adj.get(0));
						dist.set(adj.get(0), dist.get(u) + adj.get(1));
						anterior.set(adj.get(0), u);
					}
				}
		}
		MostraDijkstra();
	}
	public void MostraDijkstra(){
		String msg= "Vértice | Distâcia | Caminho \n";
		for(int i =0; i<vertices.size();i++){
			String path = (anterior.get(i) != null && !anterior.get(i).equals(""))? Grafo.getVertices().get(anterior.get(i)): "-";
			String distac = (dist.get(i) >= Integer.MAX_VALUE/2)? "--": dist.get(i)+""; 
			msg += "   "+Grafo.getVertices().get(i)+"         |       "+distac+"        |        "+path+"\n";
		}
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public void BellmanFord(int inicio){
		distBF = new ArrayList<Integer>();
		anteriorBF = new ArrayList<Integer>();
		boolean mudou = false;
		for (int i = 0; i<vertices.size(); i++) {			
			distBF.add(i, Integer.MAX_VALUE/2);
			anteriorBF.add(i, null);
		}
		distBF.set(inicio, 0);
		for (int i = 0; i<vertices.size(); i++) {	
			mudou = false;
			for (ArrayList<Integer> arest : listaArestas) {				
				if(distBF.get(arest.get(1)) > distBF.get(arest.get(0))+arest.get(2)){
					distBF.set(arest.get(1), distBF.get(arest.get(0))+arest.get(2));
					anteriorBF.set(arest.get(1), arest.get(0));
					mudou = true;
				}
			}
			if(!mudou){
				break;
			}
		}
		mudou = false;
		for (ArrayList<Integer> arest : listaArestas) {
			if(distBF.get(arest.get(1)) > distBF.get(arest.get(0))+arest.get(2)){
				mudou = true;
				break;
			}
		}
		if(mudou){
			JOptionPane.showMessageDialog(null, "Ciclo Negativo!");
		}else{
			MostraBellmanFord();
		}
	}	
	public void MostraBellmanFord(){
		String msg= "Vértice | Distâcia | Caminho \n";
		for(int i =0; i<vertices.size();i++){
			String path = (anteriorBF.get(i) != null && !anteriorBF.get(i).equals(""))? Grafo.getVertices().get(anteriorBF.get(i)): "-";
			String distac = (distBF.get(i) >= Integer.MAX_VALUE/3)? "--": distBF.get(i)+""; 
			msg += "   "+Grafo.getVertices().get(i)+"         |       "+distac+"        |        "+path+"\n";
		}
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public void FloydWarshall(){
		distFW = new ArrayList<ArrayList<Integer>>();
		anteriorFW = new ArrayList<ArrayList<Integer>>();
		
		for(int i=0; i< vertices.size();i++){
			ArrayList<Integer> tempDist = new ArrayList<Integer>();
			ArrayList<Integer> tempAnt = new ArrayList<Integer>();
			for(int j=0; j< vertices.size();j++){
				tempDist.add(j , Integer.MAX_VALUE/2);
				tempAnt.add(j , null);
			}			
			distFW.add(i, tempDist);
			anteriorFW.add(i, tempAnt);
			distFW.get(i).add(i,0);
		}
		for (ArrayList<Integer> arest : listaArestas) {
			distFW.get(arest.get(0)).set(arest.get(1), arest.get(2));
			anteriorFW.get(arest.get(0)).set(arest.get(1), arest.get(0));
		}
		
		for(int k= 0 ; k < vertices.size();k++){
			for(int i= 0; i < vertices.size();i++){
				for(int j= 0 ; j < vertices.size();j++){
					if(distFW.get(i).get(k)+distFW.get(k).get(j) < distFW.get(i).get(j)){
						distFW.get(i).set(j, distFW.get(i).get(k)+distFW.get(k).get(j));
						anteriorFW.get(i).set(j, k);
					}
				}
			}
		}
		mostraFloydWarshall();
	}	
	public void mostraFloydWarshall(){
		String msg1= "   ";
		String msg2= "   ";
		for(int i =0; i<vertices.size();i++){
			msg1 += "   |   "+Grafo.getVertices().get(i);
			msg2 += "   |   "+Grafo.getVertices().get(i);
		}
		msg1 += "\n";
		msg2 += "\n";
		for(int i =0; i<vertices.size();i++){
			msg1 += Grafo.getVertices().get(i)+"";
			msg2 += Grafo.getVertices().get(i)+"";
			for(int j =0; j<vertices.size();j++){
				String path = (anteriorFW.get(i).get(j) != null && !anteriorFW.get(i).get(j).equals(""))? Grafo.getVertices().get(anteriorFW.get(i).get(j)): "-";
				String distac = (distFW.get(i).get(j) >= Integer.MAX_VALUE/3)? "--": distFW.get(i).get(j)+""; 
				msg1 += "   |   "+distac+"";
				msg2 += "   |   "+path+"";
			}
			msg1 += "\n";
			msg2 += "\n";
		}
		String msgF = msg1+"\n\n"+msg2;
		JOptionPane.showMessageDialog(null, msgF);
	}
	
	public void Kruskal(){
		Uniao = new ArrayList<ArrayList<Integer>>();
		arestAdd = new ArrayList<ArrayList<Integer>>();
		quickSort(listaArestas, 0, listaArestas.size()-1);
		ArrayList<Integer> temp = new ArrayList<Integer>();
		for(int i = 0; i<vertices.size();i++){
			temp = new ArrayList<Integer>();
			for(int j = 0; j<vertices.size();j++){
				if(i == j){
					temp.add(i);					
				}
			}	
			Uniao.add(i,temp);			
		}
		
			for (ArrayList<Integer> arest : listaArestas) {
				
//					União
					boolean existe = false;
					for (Integer vert : Uniao.get(arest.get(0))){
						if(vert.equals(arest.get(1))){
							existe = true;
						}
						if(!existe){
							for (Integer v : Uniao.get(arest.get(0))) {
								Uniao.get(v).add(arest.get(1));
								addVerts(arest);
							}
							for (Integer v : Uniao.get(arest.get(1))) {
								Uniao.get(v).add(arest.get(0));
								addVerts(arest);
							}
						}
					}
			}		
		mostraKruskal();
	}
	public void addVerts(ArrayList<Integer> arest){
		boolean existe = false;
		for (ArrayList<Integer> aresAdd : arestAdd) {
			if(arest.get(0) == aresAdd.get(0) && arest.get(1) == aresAdd.get(1)){
				existe = true;
			}
		}
		if(!existe){
			arestAdd.add(arest);
		}		
	}
	public void mostraKruskal(){
		
	}
	
	public static void quickSort(ArrayList<ArrayList<Integer>> arest, int esquerda, int direita) {
		int esq = esquerda;
		int dir = direita;
		int pivo = arest.get((esq + dir) / 2).get(2);
		ArrayList<Integer> troca;
		while (esq <= dir) {
			while (arest.get(esq).get(2) < pivo) {
				esq = esq + 1;
			}
			while (arest.get(dir).get(2) > pivo) {
				dir = dir - 1;
			}
			if (esq <= dir) {
				troca = arest.get(esq);
				arest.set(esq,arest.get(dir));
				arest.set(dir, troca);
				esq = esq + 1;
				dir = dir - 1;
			}
		}
		if (dir > esquerda)
			quickSort(arest, esquerda, dir);
		if (esq < direita)
			quickSort(arest, esq, direita);
	}
	
	public void PrimJarnik(){
		distPJ = new ArrayList<Integer>();
		anteriorPJ = new ArrayList<Integer>();
		PriorityQueue<Integer> fila = new PriorityQueue<Integer>();
		vertsAdd = new ArrayList<Integer>();
		Random random = new Random();
		int inicio = random.nextInt(vertices.size()-1);
		
		for(int i =0; i< vertices.size();i++){
			distPJ.add(i, Integer.MAX_VALUE/2);
			anteriorPJ.add(i, null);
		}
		distPJ.set(inicio, 0);
		fila.add(inicio);
		vertsAdd.add(inicio);
			for(int i = 0;i<vertices.size()-1;i++){				
			int u = fila.poll();
			for (ArrayList<Integer> adj : Grafo.getListaAd().get(u)) {
				boolean existe = false;
				for (Integer queue : vertsAdd) {
					if(queue.equals(adj.get(0))){
						existe = true;
					}
				}
				if(!existe){
					fila.add(adj.get(0));						
				}
				if(adj.get(1) < distPJ.get(adj.get(0)) && !existe){
						anteriorPJ.set(adj.get(0), u);
						distPJ.set(adj.get(0), adj.get(1));
						vertsAdd.add(adj.get(0));
				}								
			}				
			
		}
		mostraPrimJarnik();
	}
	public void mostraPrimJarnik(){
		String msg= "Vértice | Distâcia | Caminho \n";
		int custo =0;
		for(int i =0; i<vertices.size();i++){
			String path = (anteriorPJ.get(vertsAdd.get(i)) != null && !anteriorPJ.get(vertsAdd.get(i)).equals(""))? Grafo.getVertices().get(anteriorPJ.get(vertsAdd.get(i))): "-";
			String distac = (distPJ.get(vertsAdd.get(i)) >= Integer.MAX_VALUE/3)? "--": distPJ.get(vertsAdd.get(i))+""; 
			msg += "   "+Grafo.getVertices().get(vertsAdd.get(i))+"         |       "+distac+"        |        "+path+"\n";
			custo += distPJ.get(vertsAdd.get(i));
		}
		msg += "\nCusto = "+custo;
		JOptionPane.showMessageDialog(null, msg);
	}
	
	public void LimpaGrafo(){
		vertices = new ArrayList<String>();
		listaArestas = new ArrayList<ArrayList<Integer>>();
		matrizAd = new ArrayList<ArrayList<Integer>>();
		matrizInc = new ArrayList<ArrayList<Integer>>();
		ListaAd = new ArrayList<ArrayList<ArrayList<Integer>>>();
		
	}
	
	
	public String ListarVertices(boolean origem, boolean dijkstra){
		String msg = "";
		msg = origem?"Selecione o Vértice de Destino: \n":"Selecione o Vértice de Origem: \n";
		if(dijkstra){
			msg = "Selecione o Vértice Inicial: \n";
		}
		for(int i = 0; i<vertices.size();i++){
			msg += (i+1)+" - "+vertices.get(i)+"\n";
		}
		return msg;
	}
	
	public static ArrayList<String> getVertices() {
		return vertices;
	}
	public static void setVertices(ArrayList<String> vertices) {
		Grafo.vertices = vertices;
	}
	public static ArrayList<ArrayList<Integer>> getListaArestas() {
		return listaArestas;
	}
	public static void setListaArestas(ArrayList<ArrayList<Integer>> listaArestas) {
		Grafo.listaArestas = listaArestas;
	}
	public static ArrayList<ArrayList<Integer>> getMatrizAd() {
		return matrizAd;
	}
	public static void setMatrizAd(ArrayList<ArrayList<Integer>> matrizAd) {
		Grafo.matrizAd = matrizAd;
	}
	public static ArrayList<ArrayList<Integer>> getMatrizInc() {
		return matrizInc;
	}
	public static void setMatrizInc(ArrayList<ArrayList<Integer>> matrizInc) {
		Grafo.matrizInc = matrizInc;
	}
	public static ArrayList<ArrayList<ArrayList<Integer>>> getListaAd() {
		return ListaAd;
	}
	public static void setListaAd(ArrayList<ArrayList<ArrayList<Integer>>> listaAd) {
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
