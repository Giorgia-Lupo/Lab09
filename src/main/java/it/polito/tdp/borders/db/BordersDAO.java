package it.polito.tdp.borders.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.borders.model.Border;
import it.polito.tdp.borders.model.Country;

public class BordersDAO {

	public List<Country> loadAllCountries(Map<Integer, Country> idMap) {

		String sql = "SELECT ccode, StateAbb, StateNme FROM country ORDER BY StateAbb";
		List<Country> result = new ArrayList<Country>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(idMap.get(rs.getInt("ccode"))==null) {
					Country c = new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
					idMap.put(c.getCCode(), c);
				result.add(c);
			}else {
				result.add(idMap.get(rs.getInt("ccode")));
				}
			}
			conn.close();
			return result;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}
	
	/*public List<Country> getBorderDatoAnno(Map<Integer, Country> idMap, int anno){
		String sql = "SELECT  * FROM contiguity WHERE YEAR=? ";
		List<Country> confini = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				if(idMap.get(rs.getInt("ccode"))==null) {
					Country c1 = new Country(rs.getInt("ccode"), rs.getString("StateAbb"), rs.getString("StateNme"));
				idMap.put(c1.getCCode(), c1);
				confini.add(c1);
				}else {
					confini.add(idMap.get(rs.getInt("ccode")));
				}
			}
			conn.close();
			return confini;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
	}*/

	public List<Border> getCountryPairs(Map<Integer, Country> idMap, int anno) {
		String sql = "SELECT c.state1no, c.state2no " + 
				"FROM contiguity AS c " + 
				"WHERE c.year<=? AND c.conttype=\"1\" " + 
				"ORDER BY c.state1no ";
		List<Border> confini = new ArrayList<>();
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setInt(1, anno);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				
				int code1 = rs.getInt("state1no");
				int code2 = rs.getInt("state2no");
				
				Country c1 = idMap.get(code1);
				Country c2 = idMap.get(code2);
				
				
				if(c1!=null && c2!=null) {
					Border b = new Border(c1, c2);
					confini.add(b);
				}
			}
			conn.close();
			return confini;

		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("Errore connessione al database");
			throw new RuntimeException("Error Connection Database");
		}
		
	}
}
