package com.example.demo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;

@Component
public class PcOrdJDBCTemp {

	private JdbcTemplate jdbcTemplateObject;

	@Autowired
	public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
		this.jdbcTemplateObject = jdbcTemplateObject;
	}

	public int updatePezzi(int pezzi, String modello) {
		String query = "UPDATE pcord SET qnt = qnt + ? WHERE modello = ?";
		return jdbcTemplateObject.update(query, pezzi, modello);
	}

	public ArrayList<PcOrd> ritornaOrdPc() {
		ResultSet rs1 = null;

		String query = "SELECT * FROM pcord";
		return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<PcOrd>>() {
			@Override
			public ArrayList<PcOrd> extractData(ResultSet rs) throws SQLException {
				ArrayList<PcOrd> lista = new ArrayList<>();
				while (rs.next()) {
					PcOrd o1 = new PcOrd();
					o1.setId(rs.getInt("id"));
					o1.setQnt(rs.getInt("qnt"));
					o1.setModello(rs.getString("modello"));

					lista.add(o1);
				}
				return lista;
			}
		});

	}
}