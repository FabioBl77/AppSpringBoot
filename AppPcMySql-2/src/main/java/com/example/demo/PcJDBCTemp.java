package com.example.demo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.stereotype.Component;


@Component
public class PcJDBCTemp {
    
    
    private JdbcTemplate jdbcTemplateObject;

    @Autowired
    public void setJdbcTemplateObject(JdbcTemplate jdbcTemplateObject) {
        this.jdbcTemplateObject = jdbcTemplateObject;
    }

    public int insertComputer(String marca ,String tipologia, String modello, String descrizione, int qnt, String urlImg, double prezzo) {
        String query = "INSERT INTO magazzino (marca, tipologia, modello, descrizione, qnt, url, prezzo) VALUES (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplateObject.update(query, marca,  tipologia, modello, descrizione, qnt, urlImg, prezzo);
    }

    public int updateCognomeId(int id, String cognome) {
        String query = "UPDATE dip SET cognome = ? WHERE id = ?";
        return jdbcTemplateObject.update(query, cognome, id);
    }
    
    public int updateCognome(String vecchioCognome, String cognome) {
        String query = "UPDATE dip SET cognome = ? WHERE cognome = ?";
        return jdbcTemplateObject.update(query, cognome, vecchioCognome);
    }
    
    public int updateNome(String nome, String cognome) {
        String query = "UPDATE dip SET nome = ? WHERE cognome = ?";
        return jdbcTemplateObject.update(query, nome, cognome);
    }
    
    public int updateMansione2(int id, String mansione) {
        String query = "UPDATE dip SET mansione = ? WHERE id = ?";
        return jdbcTemplateObject.update(query, mansione, id);
    }
    public int updateMansione(String mansione, String cognome) {
        String query = "UPDATE dip SET mansione = ? WHERE cognome = ?";
        return jdbcTemplateObject.update(query, mansione, cognome);
    }

    public int updateStipendio(int id, double stipendio) {
        String query = "UPDATE dip SET stipendio = ? WHERE id = ?";
        return jdbcTemplateObject.update(query, stipendio, id);
    }

    public int deleteOrologio(int id) {
        String query = "DELETE FROM orologi WHERE id = ?";
        return jdbcTemplateObject.update(query, id);
    }
    
    public int deleteDipendente(String cognome) {
        String query = "DELETE FROM dip WHERE cognome = ?";
        return jdbcTemplateObject.update(query, cognome);
    }
    public int updatePezzi(int pezzi, int id) {
        String query = "UPDATE magazzino SET qnt = qnt + ? WHERE id = ?";
        return jdbcTemplateObject.update(query, pezzi, id);
    }
    public int scalaPezzi(int pezzi, int id) {
        String query = "UPDATE magazzino SET qnt = qnt - ? WHERE id = ?";
        return jdbcTemplateObject.update(query, pezzi, id);
    }
    
    
    public ArrayList<Computer> ritornaComputer(){
    	ResultSet rs1 = null;
    	
    	
            String query = "SELECT * FROM magazzino";
            return jdbcTemplateObject.query(query, new ResultSetExtractor<ArrayList<Computer>>() {
                @Override
                public ArrayList<Computer> extractData(ResultSet rs) throws SQLException {
                	ArrayList <Computer> listaPc = new ArrayList<>();
                    while (rs.next()) {
                    	Computer pc = new Computer();
                        pc.setId(rs.getInt("id"));
                    	pc.setMarca(rs.getString("marca"));
                    	pc.setTipologia(rs.getString("tipologia"));
                    	pc.setModello(rs.getString("modello"));
                    	pc.setDescrizione(rs.getString("descrizione"));
                    	pc.setQnt(rs.getInt("qnt"));
                    	pc.setUrlImg(rs.getString("url"));
                    	pc.setPrezzo(rs.getDouble("prezzo"));
                        listaPc.add(pc);
                    }
                    return listaPc;
                }
            });
        }
    // Metodo per eseguire query DDL
    public void executeDDLQuery(String query) {
        jdbcTemplateObject.execute(query);
    }
}
