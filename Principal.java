package representar;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Principal extends Shell {
	private Table table;
	private Table table_1;
	private Button btnAresta;
	private Button btnVertice;
	private Button btnRepre2;
	private Button btnRepre1;
	private Button btnRepre4;
	private Button btnRepre3;
	private Button btnSim2;
	private Button btnNo2;
	private Button btnSim;
	private Button btnNo;
	
	public TableItem it; 
	
	public static boolean orientado;
	public static boolean valorado;
	public static ArrayList<String> vertices = new ArrayList<String>();
	public static ArrayList<ArrayList<String>> arestas = new ArrayList<ArrayList<String>>();
	public static int[][] matrizAd;
	public static int[][] matrizInc;
	public static ArrayList<ArrayList<String>> ListaAd;


	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String args[]) {
		try {
			Display display = Display.getDefault();
			Principal shell = new Principal(display);
			shell.open();
			shell.layout();
			while (!shell.isDisposed()) {
				if (!display.readAndDispatch()) {
					display.sleep();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the shell.
	 * @param display
	 */
	public Principal(Display display) {
		super(display, SWT.SHELL_TRIM);
		
		Group grpOrientado = new Group(this, SWT.NONE);
		grpOrientado.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		grpOrientado.setText("Orientado?");
		grpOrientado.setBounds(20, 20, 251, 57);
		
		Button button = new Button(grpOrientado, SWT.RADIO);
		button.setEnabled(false);
		button.setSize(90, 16);
		button.setSelection(true);
		button.setVisible(false);
		
		btnSim = new Button(grpOrientado, SWT.RADIO);
		btnSim.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnSim.getSelection()){
					orientado = true;
			}
				if((btnSim.getSelection() || btnNo.getSelection()) && (btnSim2.getSelection() || btnNo2.getSelection())){
					btnVertice.setEnabled(true);
					btnAresta.setEnabled(true);
				}
			}
		});
		btnSim.setBounds(10, 25, 90, 16);
		btnSim.setText("Sim");
		
		btnNo = new Button(grpOrientado, SWT.RADIO);
		btnNo.setSelection(true);
		btnNo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnNo.getSelection()){
					orientado = true;
			}
				if((btnSim.getSelection() || btnNo.getSelection()) && (btnSim2.getSelection() || btnNo2.getSelection())){
					btnVertice.setEnabled(true);
					btnAresta.setEnabled(true);
				}
			}
		});
		btnNo.setBounds(106, 25, 90, 16);
		btnNo.setText("N\u00E3o");
		
		Group grpValorado = new Group(this, SWT.NONE);
		grpValorado.setText("Valorado?");
		grpValorado.setFont(SWTResourceManager.getFont("Segoe UI", 12, SWT.NORMAL));
		grpValorado.setBounds(20, 104, 251, 57);
		
		btnSim2 = new Button(grpValorado, SWT.RADIO);
		btnSim2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnSim2.getSelection()){
					valorado = true;
			}
				if((btnSim.getSelection() || btnNo.getSelection()) && (btnSim2.getSelection() || btnNo2.getSelection())){
					btnVertice.setEnabled(true);
					btnAresta.setEnabled(true);
				}
			}
		});
		btnSim2.setText("Sim");
		btnSim2.setBounds(10, 25, 90, 16);
		
		btnNo2 = new Button(grpValorado, SWT.RADIO);
		btnNo2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(btnNo2.getSelection()){
					valorado = false;	
			}
				if((btnSim.getSelection() || btnNo.getSelection()) && (btnSim2.getSelection() || btnNo2.getSelection())){
					btnVertice.setEnabled(true);
					btnAresta.setEnabled(true);
				}
			}
		});
		btnNo2.setText("N\u00E3o");
		btnNo2.setBounds(106, 25, 90, 16);
		
		table = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setBounds(339, 20, 196, 326);
		
		TableColumn tblclmnNewColumn = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn.setWidth(57);
		
		TableColumn tblclmnNewColumn_1 = new TableColumn(table, SWT.NONE);
		tblclmnNewColumn_1.setWidth(134);
		tblclmnNewColumn_1.setText("V\u00E9rtices");
		
		table_1 = new Table(this, SWT.BORDER | SWT.FULL_SELECTION);
		table_1.setLinesVisible(true);
		table_1.setHeaderVisible(true);
		table_1.setBounds(595, 20, 287, 326);
		
		TableColumn tblclmnNewColumn_2 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_2.setWidth(95);
		tblclmnNewColumn_2.setText("V\u00E9rtice 1");
		
		TableColumn tblclmnNewColumn_3 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_3.setWidth(95);
		tblclmnNewColumn_3.setText("V\u00E9rtice 2");
		
		TableColumn tblclmnNewColumn_4 = new TableColumn(table_1, SWT.NONE);
		tblclmnNewColumn_4.setWidth(91);
		tblclmnNewColumn_4.setText("Valor");
		
		
		// Adicionar Vértice -------------------------------------------------------------------------------------
		btnVertice = new Button(this, SWT.NONE);
		btnVertice.setEnabled(false);
		btnVertice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String ver = JOptionPane.showInputDialog("Vértice: ");
				if(ver.isEmpty()){
					JOptionPane.showMessageDialog(null, "Valor inválido");
				}else{
				vertices.add(ver);
				
				btnSim.setEnabled(false);
				btnSim2.setEnabled(false);
				btnNo.setEnabled(false);
				btnNo2.setEnabled(false);
	
				preencheTabela1();
			}
			}
		});
		btnVertice.setBounds(20, 193, 141, 33);
		btnVertice.setText("Adicionar V\u00E9rtice");
		
		
		//Adicionar Aresta ------------------------------------------------------------------------------------
		btnAresta = new Button(this, SWT.NONE);
		btnAresta.setEnabled(false);
		btnAresta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<String> arestaTemp = new ArrayList<String>();	
				String ar1;
				String ar2;	
					ar1 = JOptionPane.showInputDialog("Vértice 1: ");	
					if(ar1.isEmpty()){
						JOptionPane.showMessageDialog(null, "Valor inválido");
					}else{		
						ar2 = JOptionPane.showInputDialog("Vértice 2: ");
						if(ar2.isEmpty()){
							JOptionPane.showMessageDialog(null, "Valor inválido");
						}else{
							arestaTemp.add(ar1);
							arestaTemp.add(ar2);	
							if(valorado){
								String valor = JOptionPane.showInputDialog("Valor: ");
								if(!valor.isEmpty()){
								arestaTemp.add(valor);
								}else{
									arestaTemp.add("0");
								}
							}else{					
								arestaTemp.add(null);
								}	
							
							arestas.add(arestaTemp);
							matrizAdjacencia();
							listaAdjacencia();
							MatrizIncidencia();
							
							btnSim.setEnabled(false);
							btnSim2.setEnabled(false);
							btnNo.setEnabled(false);
							btnNo2.setEnabled(false);
							
							preencheTabela2();
							}
					}				
				
			}
		});
		btnAresta.setText("Adicionar Aresta");
		btnAresta.setBounds(20, 246, 141, 33);
		
		Label label = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label.setBounds(10, 381, 882, 2);
		
		btnRepre1 = new Button(this, SWT.NONE);
		btnRepre1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String msg = "";
				if(valorado){
					msg += "Vértice 1    |    Vértice 2    |    Valor    |\n";
					for (ArrayList<String> arest : arestas) {
						msg+=  "         "+arest.get(0)+"          |          "+arest.get(1)+"            |      "+arest.get(2)+ "      |\n";
						msg+= "_______________________________\n";
					}
				}else{
					msg += "Vértice 1    |    Vértice 2    |\n";
					for (ArrayList<String> arest : arestas) {
						msg+=  "         "+arest.get(0)+"          |          "+arest.get(1)+"            |\n";
						msg+= "_______________________\n";
					}
				}
				JOptionPane.showMessageDialog(null, msg);
			}
		});
		btnRepre1.setText("Lista de Arestas");
		btnRepre1.setBounds(46, 406, 141, 33);
		
		btnRepre2 = new Button(this, SWT.NONE);
		btnRepre2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String msg = " ";
				int x = 0;
				for (ArrayList<String> vert : ListaAd) {
					msg+= vertices.get(x)+" > ";
					for (String vert2 : vert) {
						msg += vert2+" | ";
					}
					msg+= "\n";
					x++;
				}
				JOptionPane.showMessageDialog(null, msg);
			}
		});
		btnRepre2.setText("Lista de Adjac\u00EAncia");
		btnRepre2.setBounds(476, 406, 141, 33);
		
		btnRepre3 = new Button(this, SWT.NONE);
		btnRepre3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String msg = "   ";
				
				for(int i = 0; i < vertices.size(); i++){
					msg += vertices.get(i)+" | ";
				}
				msg+= "\n";
				for(int i = 0; i < vertices.size(); i++){
					msg += vertices.get(i)+" ";
					for(int j = 0; j < vertices.size(); j++){
						msg += matrizAd[i][j]+" | ";			
					}
					msg += "\n";
				}
				JOptionPane.showMessageDialog(null, msg);
			}
		});
		btnRepre3.setText("Matriz de Adjac\u00EAncia");
		btnRepre3.setBounds(253, 406, 141, 33);
		
		btnRepre4 = new Button(this, SWT.NONE);
		btnRepre4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String msg = "   ";
				
				for(int i = 0; i < arestas.size(); i++){
					msg += "e"+(i+1)+" | ";
				}
				msg+= "\n";
				for(int i = 0; i < vertices.size(); i++){
					msg += vertices.get(i)+"  ";
					for(int j = 0; j < arestas.size(); j++){
						msg += matrizInc[i][j]+"  |  ";			
					}
					msg += "\n";
				}
				JOptionPane.showMessageDialog(null, msg);
			}
		});
		btnRepre4.setText("Matriz de Incid\u00EAncia");
		btnRepre4.setBounds(684, 406, 141, 33);
		createContents();
		it = new TableItem(table, SWT.NONE);
		
		Button btnLimparGrafo = new Button(this, SWT.NONE);
		btnLimparGrafo.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				vertices = new ArrayList<String>();
				arestas = new ArrayList<ArrayList<String>>();
				preencheTabela1();
				preencheTabela2();
				
				btnSim.setEnabled(true);
				btnSim2.setEnabled(true);
				btnNo.setEnabled(true);
				btnNo2.setEnabled(true);
				btnVertice.setEnabled(false);
				btnAresta.setEnabled(false);
			}
		});
		btnLimparGrafo.setBounds(20, 339, 75, 25);
		btnLimparGrafo.setText("Limpar Grafo");
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Grafos");
		setSize(929, 502);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void preencheTabela1(){
		int x = 1;
		table.setItemCount(0);
		for (String vert : vertices) {
			TableItem it = new TableItem(table, SWT.NONE);
			String[] toArray = new String[]{x+"",vert};
			it.setText(toArray);
			x++;
		}
	}
	
	public void preencheTabela2(){
		table_1.setItemCount(0);
		for (ArrayList<String> arest : arestas) {
			TableItem it = new TableItem(table_1, SWT.NONE);
			String[] toArray = new String[]{arest.get(0),arest.get(1),arest.get(2)};
			it.setText(toArray);
		}
	}
	
	//Matriz de Adjacência ---------------------------------------------------------------------------------------
	public void matrizAdjacencia(){
		matrizAd = new int[vertices.size()][vertices.size()];
		if(orientado){
		for(int i = 0; i < vertices.size(); i++){
			for(int j = 0; j < vertices.size(); j++){
				for (ArrayList<String> arest : arestas) {
					if(vertices.get(i).equals(arest.get(0)) && vertices.get(j).equals(arest.get(1))){
						if(valorado){
							matrizAd[i][j] = Integer.parseInt(arest.get(2));
						}else{						
							matrizAd[i][j] = 1;
						}
					}
					
				}
			}
		}
		}else{
			for(int i = 0; i < vertices.size(); i++){
				for(int j = 0; j < vertices.size(); j++){
					for (ArrayList<String> arest : arestas) {
						if((vertices.get(i).equals(arest.get(0)) && vertices.get(j).equals(arest.get(1))) || (vertices.get(i).equals(arest.get(1)) && vertices.get(j).equals(arest.get(0)))){
							if(valorado){
								matrizAd[i][j] = Integer.parseInt(arest.get(2));
							}else{						
								matrizAd[i][j] = 1;
							}
						}
						
					}
				}
			}
		}

	}
	
	// Lista de Incidência --------------------------------------------------------------------------------------
	public void listaAdjacencia(){
		ListaAd = new ArrayList<ArrayList<String>>();
		for (String vert : vertices) {
			ArrayList<String> vertTemp =  new ArrayList<String>();
			for (ArrayList<String> arest : arestas) {
				if(orientado){
					if(vert.equals(arest.get(0))){
						vertTemp.add(arest.get(1));
					}
				}else{
					if(vert.equals(arest.get(0))){
						vertTemp.add(arest.get(1));
					}
					if(vert.equals(arest.get(1))){
						vertTemp.add(arest.get(0));
					}
				}
			}
			ListaAd.add(vertTemp);
		}
	}
	
	// Matriz de Incidência ------------------------------------------------------------------------------------------
	public void MatrizIncidencia(){
		matrizInc = new int[vertices.size()][arestas.size()];
		int i = 0;
		for (String vert : vertices) {			
			int j = 0;
			for (ArrayList<String> arest : arestas) {
				if(orientado){
					if(vert.equals(arest.get(0))){
						matrizInc[i][j] = 1;
					}
					if(vert.equals(arest.get(1))){
						matrizInc[i][j] = -1;
					}
				}else{
					if(vert.equals(arest.get(0))){
						matrizInc[i][j] = 1;
					}
					if(vert.equals(arest.get(1))){
						matrizInc[i][j] = 1;
					}		
				}
				j++;
			}
			i++;
		}
	}
	
	public void Dijkstra(int inicio){
		ArrayList<Integer> dist = new ArrayList<Integer>();
		ArrayList<String> anterior = new ArrayList<String>();
		PriorityQueue<Integer> fila = new PriorityQueue<Integer>();
		
		for (int i = 0; i<vertices.size(); i++) {			
			dist.set(i, Integer.MAX_VALUE);
			anterior.set(i, null);
		}
		dist.set(inicio, 0);
		fila.addAll(dist);
		
		while(!fila.isEmpty()){
			
			
		}
		
	}
		
}
