package it.polito.tdp.borders.model;

import java.util.List;

public class TestModel {

	public static void main(String[] args) {

		Model model = new Model();

		System.out.println("TestModel -- TODO");
		
		System.out.println("Creo il grafo relativo al 2000");
		model.creaGrafo(2000);
		
	//	List<Country> countries = model.getCountries();
		//System.out.println(countries);
	//	System.out.format("Trovate %d nazioni\n", countries.size());
		
		System.out.println(model.getVertex());
		
		System.out.println("++++++");
		
		System.out.println(model.edgeNumber());

//		System.out.format("Numero componenti connesse: %d\n", model.getNumberOfConnectedComponents());
		
//		Map<Country, Integer> stats = model.getCountryCounts();
//		for (Country country : stats.keySet())
//			System.out.format("%s %d\n", country, stats.get(country));	
		
		System.out.println("++++++");
		
		//System.out.println(model.getCountry());
		
		System.out.println("++++++");
		
		System.out.println(model.componentiConnesse());
		
		System.out.println("++++++");
		
		Country c = new Country(2, "USA", "United States of America");
		/*System.out.println(model.visitaGrafo(c));
		System.out.println(model.visitaAmpiezza(c));
		System.out.println(model.visitaProfondita(c));
		System.out.println(model.getRicorsiva(c));*/
	}

}
