package it.polito.tdp.borders.db;

import java.util.List;
import java.util.*;

import it.polito.tdp.borders.model.Country;

public class TestDAO {

	public static void main(String[] args) {

		BordersDAO dao = new BordersDAO();

		System.out.println("Lista di tutte le nazioni:");
		Map<Integer, Country> idMap = new TreeMap<>();
		List<Country> countries = dao.loadAllCountries(idMap);
		System.out.println(countries+"\n");
		
		System.out.println("+++++++++");
		System.out.println(dao.getCountryPairs(idMap, 1920));
	}
}
