package it.polito.tdp.borders.model;

import java.util.*;

import org.jgrapht.Graph;
import org.jgrapht.Graphs;
import org.jgrapht.alg.connectivity.ConnectivityInspector;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;
import org.jgrapht.traverse.BreadthFirstIterator;
import org.jgrapht.traverse.DepthFirstIterator;
import org.jgrapht.traverse.GraphIterator;

import it.polito.tdp.borders.db.BordersDAO;

public class Model {
	
	private Map<Integer, Country> idMap;
	BordersDAO dao;
	private Graph<Country, DefaultEdge> grafo;
	//List<Country> countries;
	

	public Model() {		
		dao = new BordersDAO();
		idMap = new HashMap<>();
		dao.loadAllCountries(idMap);
	}
		
 /* public List<Country> getCountries() {
		return dao.loadAllCountries(idMap);
	}
 */

	public void creaGrafo(int anno) {
		this.grafo = new SimpleGraph<Country, DefaultEdge>(DefaultEdge.class);
			
		List<Border> confini = new ArrayList<>(dao.getCountryPairs(idMap, anno));
		
		//Aggiungo vertici e archi
		for(Border b : confini) {
			//se esiste confine nell'anno dato-->aggiungo Country come vertice
			this.grafo.addVertex(b.getStato1());
			this.grafo.addVertex(b.getStato2());
			this.grafo.addEdge(b.getStato1(), b.getStato2());
		}
		
		//countries = new ArrayList<>(this.grafo.vertexSet());
	}
	
	public Set<Country> getVertex(){
		return this.grafo.vertexSet();
	}
	public int vertexNumber() {
		return this.grafo.vertexSet().size();
	}
	
	public int edgeNumber() {
		return this.grafo.edgeSet().size();
	}
	
	public Collection<String> getCountry(){
		int dimensione=0;
		String s="";
		List<String> stringa=new LinkedList<>();
		for(Country c : this.grafo.vertexSet()) {
    		dimensione=this.grafo.degreeOf(c);
    		s=c.getStateAbb()+"- numero confini: "+dimensione+"\n";
    		stringa.add(s);
		}
		return stringa ;
	}
	
	/*public int numConfini(Country stato) {
		int dimensione=0;
		for(DefaultEdge e : this.grafo.edgeSet()) {
			if(stato.equals(this.grafo.getEdgeSource(e))||stato.equals(this.grafo.getEdgeTarget(e)))
				dimensione++;				
		}
		return dimensione; //numero di confini dello stato passato come parametro
	}*/
	
	public int componentiConnesse() {
		ConnectivityInspector<Country, DefaultEdge> connesse = new ConnectivityInspector<Country, DefaultEdge>(this.grafo);
		return connesse.connectedSets().size();
	}
	
	/*
	//ITERATORE
	public List<Country> visitaGrafo(Country stato) {
		
		// Creo due liste: quella dei noti visitati ..
		List<Country> visitati = new ArrayList<>();
		// .. e quella dei nodi da visitare
		List<Country> daVisitare = new ArrayList<>();
		
		// Aggiungo alla lista dei vertici visitati il nodo di partenza.
		visitati.add(stato); //inizializzo la mappa dallo stato passato come parametro
		
		// Aggiungo ai vertici da visitare tutti i vertici collegati a quello inserito
		daVisitare.addAll(Graphs.neighborListOf(grafo, stato));
		
		while(!daVisitare.isEmpty()) {
			
			// Rimuovi il vertice in testa alla coda
			Country stemp = daVisitare.remove(0);
			
			// Aggiungi il nodo alla lista di quelli visitati
			visitati.add(stemp);
			
			// Ottieni tutti i vicini di un nodo
			List<Country> vicini = Graphs.neighborListOf(grafo, stemp);
			
			// Rimuovi da questa lista tutti quelli che hai già visitato..
			vicini.removeAll(visitati);
			
			// .. e quelli che sai già che devi visitare.
			vicini.removeAll(daVisitare);
			
			// Aggiungi i rimanenenti alla coda di quelli che devi visitare.
			daVisitare.addAll(vicini);
		}
		// Ritorna la lista di tutti i nodi raggiungibili
		return visitati;
	}
	
	//Libreria JGraphT
	public List<Country> visitaAmpiezza (Country stato){
		List<Country> visita = new ArrayList<>();
		GraphIterator<Country, DefaultEdge> bfv = new BreadthFirstIterator<Country, DefaultEdge>(grafo, stato);
		while(bfv.hasNext())
			visita.add(bfv.next());
		return visita;
	}
	
	public List<Country> visitaProfondita (Country stato){
		List<Country> visita = new ArrayList<>();
		GraphIterator<Country, DefaultEdge> dfv = new DepthFirstIterator<>(grafo, stato);
		while(dfv.hasNext())
			visita.add(dfv.next());
		return visita;
	}
	
	
	//RICORSIONE
	public List<Country> getRicorsiva(Country statoSelezionato){
		
		List<Country> parziale = new ArrayList<>();
		ricorsiva(parziale,statoSelezionato);
		return parziale;
	}
	
	public void ricorsiva(List<Country> parziale,Country stato) {
		parziale.add(stato);
		
		for(Country c : Graphs.neighborListOf(grafo, stato)) {
			if(!parziale.contains(c)) {
				ricorsiva(parziale, c);
			}
		}
		
	}*/
	
	public Set<Country> trovaVicini(Country c){

		ConnectivityInspector<Country,DefaultEdge> connessi = new ConnectivityInspector<>(this.grafo);

		return connessi.connectedSetOf(c);

	}
}
