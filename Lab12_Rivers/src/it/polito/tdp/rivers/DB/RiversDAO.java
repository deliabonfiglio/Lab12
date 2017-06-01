package it.polito.tdp.rivers.DB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.rivers.model.Flow;
import it.polito.tdp.rivers.model.FlowIdMap;
import it.polito.tdp.rivers.model.River;
import it.polito.tdp.rivers.model.RiverIdMap;

public class RiversDAO {

	public List<River> listAuthors(RiverIdMap riverMap) {
		
		final String sql = "SELECT * FROM river ";
		List<River> rivers = new ArrayList<River>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				River river = riverMap.get(rs.getInt("id"));
				
				if( river ==null){
					river = new River(rs.getInt("id"), rs.getString("name"));
					river = riverMap.put(river);
				}
				
				rivers.add(river);
			}
			conn.close();
			return rivers;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	public List<Flow> getFlowsOfRiver(River river, RiverIdMap riverIdMap, FlowIdMap flowIdMap) {
		
		final String sql = "SELECT * FROM flow 	WHERE river = ? ORDER BY day ";
		List<Flow> flows = new ArrayList<Flow>();
		
		try {
			Connection conn = DBConnect.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, river.getId());

			ResultSet rs = st.executeQuery();

			while (rs.next()) {
				River r = riverIdMap.get(river.getId());
		
				Flow f = flowIdMap.get(rs.getInt("id"));
				
				if( f == null){
					f = new Flow(rs.getInt("id"), rs.getDate("day").toLocalDate(), rs.getFloat("flow"), (rs.getInt("river")));
					f = flowIdMap.put(f);
					r.addFlow(f);
				}
				flows.add(f);
				
			}
			conn.close();
			return flows;

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
}
