// RT 

package br.ufmg.rt.transito;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Mapa {
	
	private static int LINES = 10;
	private static int COLUMN = 10;
	private Radar [][] matriz;
	
	private LinkedList <Radar> 	radares;
	private LinkedList <Via> 	vias;
	
	private String [] allRadares = new String [LINES * COLUMN];
	
	public Mapa(){
		
		matriz 	= new Radar[LINES][COLUMN];
		radares = new LinkedList<Radar>();
		vias 	= new LinkedList<Via>();

	}
	
	//------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------
	// 
	public void criarMapa(){

		StringTokenizer [] allLines = new StringTokenizer[19];
		int lenght = readMapFile();
		
		/*System.out.println("lenght => " + lenght);
		System.out.println();System.out.println();*/

		for (int i = 0; i < LINES; i++) {


			boolean firstRadar = true;
			String str = allRadares[i];

			if ( !str.isEmpty()){


				Radar radarAnterior = null;
				Radar radar = null;

				StringTokenizer st = new StringTokenizer(str);

				// guardo todos os tokens de cada linha em um array
				allLines[i]= st;

				int j = 0;
				while (st.hasMoreTokens()) {

					String strToken = st.nextToken().toLowerCase();
					
					radar = new Radar(strToken);
					
					matriz[i][j]=radar;
					j++;

					if (firstRadar){

						// adiciono o radar na lista de radares para criar um grafo
						radares.add(radar);
						firstRadar = false;
					}
					else {

						radares.add(radar);
						// cria uma aresta/via não direcionada, ou seja, dupla direção
						addVia(radarAnterior, radar); // 
						addVia(radar, radarAnterior);
					}
					radarAnterior = null;
					radarAnterior = radar;
				} // end while
			}// end for
			

			
		}
		
		// adicionar arestas perpendiculares
		for (int i = 0; i < LINES-1; i++) {
			for (int j = 0; j < COLUMN; j++) {
				
				if ((i+1)<LINES){
					// cria uma aresta/via não direcionada, ou seja, dupla direção
					addVia(matriz[i][j], matriz[i+1][j]); // 
					addVia(matriz[i+1][j], matriz[i][j]);
				}
			}
		}

		System.out.println("O mapa completo com todos os radares e suas respectivas conexões: ");
		System.out.println();
		System.out.println(this.toString());
	}
	
	//====================================================================
	//====================================================================
	public void addRadar(String id) {
		
		Radar r = new Radar(id);
		if (!radares.contains(r)){
			radares.add(r);
		}
	 }
	
	//====================================================================
	//====================================================================
		public void addVia(Radar origem, Radar destino) {
			
				Via v = new Via(origem, destino);
				vias.add(v);

				origem.addEmanatingVia(v);
				origem.addSuccessors(destino);
				destino.addPredecessors(origem);

		 }
		     
		//====================================================================
		//====================================================================
	     public String toString() {
	         String str = "";
	         
	         Iterator<Radar> it = radares.iterator();
	         
	         while (it.hasNext()){
	        	 
	        	 Radar r = (Radar)it.next();
	        	 
	        	 str += r.getName() + " -> ";
	        	 
	        	 Iterator<Via> itVia = r.getEmanatingList().iterator();
	        	
	        	 while (itVia.hasNext()){
		        	 
		        	 Via v = (Via)itVia.next();
		        	 
		        	 str += v.getDestino().getName() + ", ";
	        	 }
	        	 
	        	 str += "\n";
	         }

	         return str;
	    
	     }
	 	//====================================================================
	  	//====================================================================
	 	public int getNumberOfVias() {
	 		
	 		return vias.size();
	 	}

	  	//====================================================================
	  	//====================================================================
	 	public int getNumberOfRadares() {
	 		return radares.size();
	 	}

	  	//====================================================================
	  	//====================================================================
	 	public Radar getRadar(int index) {
	 		
	 		return radares.get(index);
	 	}
	  	//====================================================================
	  	//====================================================================
	 	public Via getVia(int index) {
	 		
	 		return vias.get(index);

	 	}

	 	//====================================================================
	  	//====================================================================
	 	public boolean isVia(Via via) {
 		
	 		return vias.contains(via);
	 	}

	  	//====================================================================
	  	//====================================================================
	 	public boolean isDirected() {
	 		
	 		return false;
	 	}

	  	//====================================================================
	  	//====================================================================
	 	public boolean isConnected() {
	 		
	 		return false;
	 	}

	  	//====================================================================
	  	//====================================================================
	 	public boolean isCyclic() {
	 		
	 		return false;
	 	}

/*	  	//====================================================================
	  	//====================================================================
		// Esse método busca o menor caminho entre dois radares. Para esse cálculo é
		// usado o algorítmo de Busca em Largura que, apesar de ser um algorítmo guloso,
		// garante o resultado ótimo.
	     public int buscaEmLargura(Radar origem, String wordDestino) {
	     	
	     	Iterator<Radar> itRadares = radares.values().iterator();
	     	
	     	while (itRadares.hasNext()){
	     		
	     		// inicializar todos os atributos (pai, distancia e isVisited) de todos os radares
	     		Radar rCurrent = (Radar) itRadares.next();

	     		// quando for o primeiro radar (root), seta a distancia para zero 
	     		if (rCurrent.getName().contains(origem.getName()))
	     				
	     			rCurrent.setDistancia(0); // distância dele mesmo é zero
	     			
	     		else rCurrent.setDistancia(9999); // infinito
	     			
	     		// ainda não sabe quem é o pai
	     		rCurrent.setParent(null);
	     		
	     		// ainda não foi visitado
	     		rCurrent.setVisited(false);

	     	}
	  
	        	// cria uma fila e insere o primeiro vértice ( ou vértice inicial)
	     	Queue<Radar> queue = new LinkedList<Radar>();
	     	
	     	// adiciona o primeiro vértice na fila
	     	queue.add(origem);
	     	
	     	// configura o pai e a distancia do primeiro vértice
	     	origem.setParent(null);
	     	origem.setDistancia(0);
	      	
	      	
	     	// enquanto houver radares na fila
	     	while (!queue.isEmpty()){
	     		
	     		// remove o vértice da fila
	     		Radar v = (Radar) queue.remove(); 
	     		
	     		// configura que ele foi visitado
	     		v.setVisited(true); 
	     		
	     		// solicita a lista dos vértices adjacentes/sucessores do vértice removida da fila 
	     		Iterator <Radar> itSucessors = v.getSucessorsList().iterator(); 
	     		
	     		// percorre a lista dos vértices adjacentes/sucessores e os coloca na fila
	     		while (itSucessors.hasNext()){
	     			
	     			Radar vAdj = (Radar) itSucessors.next();
	     			
	     			// verifica se o vértice adjacente já foi visitado (ou enfileirado)
	     			if(!vAdj.isVisited()){
	     				
	     				// seta que o vértice foi visitado agora
	     				vAdj.setVisited(true);
	     				//enqueued [(vAdj.getNumber() -1)] = true;
	     				
	     				// seta o pai dos vértices adjacentes
	     				vAdj.setParent(v);
	     				
	     				// seta a distancia, em nro de vias, do vértice pai até o vértice adjacente
	     				vAdj.setDistancia(v.getDistancia() + 1);
	     				
	     				// adiciona o vértice na fila
	         			queue.add(vAdj);
	     				
	     			}//end if
	     		}// end while
	     		
	     		// depois que todos os vértices adjacentes do vértice v foram visitados e incluídos na fila,
	     		// o vértice v passa a ser conhecido e visitado e por tanto, deve ser removido da fila
	     		queue.remove(v);
	 			
	     	}// end while queue 	
	     	
	     	
	     	if (radares.containsKey(wordDestino)){
	      		return ((Radar)radares.get(wordDestino)).getDistancia();
	     	} else return -1;
	  	}*/
	
	//------------------------------------------------------------------------------------------
	//------------------------------------------------------------------------------------------
	// Esse método ler o mapa onde todos os radares, seus nomes, estão configurados.
	private int readMapFile(){

		String path = "C:\\Documents and Settings\\kattiana\\My Documents\\Real Time\\MapaDeRadares10x10.txt";
		
		int i=0;
		try {
			BufferedReader in = new BufferedReader(new FileReader(path));
			String str="";
			while (in.ready()) {
				str = in.readLine();
				
				if (!str.isEmpty()){
					
					this.allRadares[i++] = str;
					
				}
			}
			in.close();
			} catch (IOException e) {
			
				e.printStackTrace();
				System.out.println();
			}
		
		return i;
	}
}
