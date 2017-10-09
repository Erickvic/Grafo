package representar;

import java.util.ArrayList;

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
	
//	public static boolean orientado;
//	public static boolean valorado;
//	public static ArrayList<String> vertices = new ArrayList<String>();
//	public static ArrayList<ArrayList<String>> arestas = new ArrayList<ArrayList<String>>();
//	public static int[][] matrizAd;
//	public static int[][] matrizInc;
//	public static ArrayList<ArrayList<String>> ListaAd;
	public static Grafo g = new Grafo();
	private Label label_1;
	private Button btnDijkstra;
	private Button btnBellmanford;
	private Button btnFloydwarshall;
	private Button btnPrimjarnik;
	

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
					Grafo.setOrientado(true);
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
					Grafo.setOrientado(true);
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
					Grafo.setValorado(true);
					btnDijkstra.setEnabled(true);
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
					Grafo.setValorado(false);	
					btnDijkstra.setEnabled(false);
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
		
		
		// Adicionar Vértice -----------------------------------------------------------------------------------------------------------------
		btnVertice = new Button(this, SWT.NONE);
		btnVertice.setEnabled(false);
		btnVertice.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String ver = JOptionPane.showInputDialog("Vértice: ");
				if(ver != null && !ver.equals("")){
					g.addVertice(ver);
				}else{
					JOptionPane.showMessageDialog(null, "Valor inválido");
				}
				btnSim.setEnabled(false);
				btnSim2.setEnabled(false);
				btnNo.setEnabled(false);
				btnNo2.setEnabled(false);
	
				preencheTabela1();
			
			}
		});
		btnVertice.setBounds(20, 193, 141, 33);
		btnVertice.setText("Adicionar V\u00E9rtice");
		
		
		//Adicionar Aresta -----------------------------------------------------------------------------------------------------------
		btnAresta = new Button(this, SWT.NONE);
		btnAresta.setEnabled(false);
		btnAresta.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ArrayList<Integer> arestaTemp = new ArrayList<Integer>();
				String aresta1;	
				String aresta2;	
				int valor = 1;
				boolean origem = false;
				
				aresta1 = JOptionPane.showInputDialog(g.ListarVertices(origem, false));
				origem = true;			
				aresta2 = JOptionPane.showInputDialog(g.ListarVertices(origem, false));	
				
				if(Grafo.isValorado()){
					String temp = JOptionPane.showInputDialog("Valor: ");
					valor = temp.isEmpty()? 1: Integer.parseInt(temp);
				}
				valor= Grafo.isValorado()? valor : 1;		
				
				arestaTemp.add(Integer.parseInt(aresta1)-1);
				arestaTemp.add(Integer.parseInt(aresta2)-1);	
				arestaTemp.add(valor);
				
				g.addAresta(arestaTemp);				
							
				btnSim.setEnabled(false);
				btnSim2.setEnabled(false);
				btnNo.setEnabled(false);
				btnNo2.setEnabled(false);							
				preencheTabela2();
							
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
				ArrayList<ArrayList<Integer>> arestas = new ArrayList<ArrayList<Integer>>();
				arestas = Grafo.getListaArestas();
				if(Grafo.isValorado()){
					msg += "Vértice 1    |    Vértice 2    |    Valor    |\n";
					for (ArrayList<Integer> arest : arestas) {
						msg+=  "         "+Grafo.getVertices().get(arest.get(0))+"          |          "+Grafo.getVertices().get(arest.get(1))+"            |      "+arest.get(2)+ "      |\n";
						msg+= "_______________________________\n";
					}
				}else{
					msg += "Vértice 1    |    Vértice 2    |\n";
					for (ArrayList<Integer> arest : arestas) {
						msg+=  "         "+Grafo.getVertices().get(arest.get(0))+"          |          "+Grafo.getVertices().get(arest.get(1))+"            |\n";
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
				ArrayList<ArrayList<ArrayList<Integer>>> ListaAd = new ArrayList<ArrayList<ArrayList<Integer>>>();
				ListaAd = Grafo.getListaAd();
				int x = 0;
				for (ArrayList<ArrayList<Integer>> vert : ListaAd) {
					msg+= Grafo.getVertices().get(x)+" > ";
					for (ArrayList<Integer> vert2 : vert) {
						msg += Grafo.getVertices().get(vert2.get(0))+" | ";
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
				
				for(int i = 0; i < Grafo.getVertices().size(); i++){
					msg += Grafo.getVertices().get(i)+" | ";
				}
				msg+= "\n";
				for(int i = 0; i < Grafo.getVertices().size(); i++){
					msg += Grafo.getVertices().get(i)+" ";
					for(int j = 0; j < Grafo.getVertices().size(); j++){
						msg += Grafo.getMatrizAd().get(i).get(j)+" | ";			
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
				
				for(int i = 0; i < Grafo.getListaArestas().size(); i++){
					msg += "e"+(i+1)+" | ";
				}
				msg+= "\n";
				for(int i = 0; i < Grafo.getVertices().size(); i++){
					msg += Grafo.getVertices().get(i)+"  ";
					for(int j = 0; j < Grafo.getMatrizInc().size(); j++){
						msg += Grafo.getMatrizInc().get(j).get(i)+"  |  ";			
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
				g.LimpaGrafo();
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
		
		label_1 = new Label(this, SWT.SEPARATOR | SWT.HORIZONTAL);
		label_1.setBounds(10, 462, 882, 2);
		
		btnDijkstra = new Button(this, SWT.NONE);
		btnDijkstra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int inicio = (Integer.parseInt(JOptionPane.showInputDialog(g.ListarVertices(false, true)))-1);
				g.Dijkstra(inicio);
			}
		});
		btnDijkstra.setText("Dijkstra");
		btnDijkstra.setBounds(46, 485, 141, 33);
		
		btnBellmanford = new Button(this, SWT.NONE);
		btnBellmanford.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				int inicio = (Integer.parseInt(JOptionPane.showInputDialog(g.ListarVertices(false, true)))-1);
				g.BellmanFord(inicio);
			}
		});
		btnBellmanford.setText("Bellman-Ford");
		btnBellmanford.setBounds(207, 485, 141, 33);
		
		btnFloydwarshall = new Button(this, SWT.NONE);
		btnFloydwarshall.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				g.FloydWarshall();
			}
		});
		btnFloydwarshall.setText("Floyd-Warshall");
		btnFloydwarshall.setBounds(368, 485, 141, 33);
		
		btnPrimjarnik = new Button(this, SWT.NONE);
		btnPrimjarnik.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				g.PrimJarnik();
			}
		});
		btnPrimjarnik.setText("Prim-Jarnik");
		btnPrimjarnik.setBounds(723, 485, 141, 33);
		
	}

	/**
	 * Create contents of the shell.
	 */
	protected void createContents() {
		setText("Grafos");
		setSize(929, 577);

	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
	
	public void preencheTabela1(){
		int x = 1;
		table.setItemCount(0);
		for (String vert : Grafo.getVertices()) {
			TableItem it = new TableItem(table, SWT.NONE);
			String[] toArray = new String[]{x+"",vert};
			it.setText(toArray);
			x++;
		}
	}
	
	public void preencheTabela2(){
		table_1.setItemCount(0);	
		for (ArrayList<Integer> arest : Grafo.getListaArestas()) {
			TableItem it = new TableItem(table_1, SWT.NONE);
			String[] toArray = Grafo.isValorado()? new String[]{Grafo.getVertices().get(arest.get(0))+"",Grafo.getVertices().get(arest.get(1))+"",arest.get(2)+""} : new String[]{Grafo.getVertices().get(arest.get(0))+"",Grafo.getVertices().get(arest.get(1))+""};
			it.setText(toArray);
		}
	}
}
