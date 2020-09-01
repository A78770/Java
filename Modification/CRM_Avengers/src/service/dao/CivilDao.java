package service.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneOffset;
import java.util.ArrayList;

import model.Civil;
 

public class CivilDao extends UserDao{
	
	public static int idco;
	
	public int getIdco() {
		return idco;
	}

	public void setIdco(int idco) {
		CivilDao.idco = idco;
	}
	
	public boolean canConnect(String login, String mdp) {
		boolean canConnect=false;
		Connection connection = getConnection();
		
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from civil where login = " + "'" +login+ "' AND motDePasse = '"+mdp+"'");
			if (result.next()) {
				canConnect=true;
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return canConnect;
	}
	
	public int recidco (String login) {
		int idco=0;
		Connection connection = getConnection();
		
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from civil where login = " + "'" +login+ "'");
			if (result.next()) {
				idco=result.getInt("IdCiv");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return idco;
	}
	
	public ArrayList<Civil> findAllCivil() {
		ArrayList<Civil> listeCivil = new ArrayList<Civil>();
		Connection connection = getConnection();
		
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select * from civil");
			while(result.next()) {
				Civil civil = new Civil(result.getString("nom"),result.getString("prenom"),result.getString("civilite"),result.getString("coordonees"),result.getDate("dateNaissance").toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant(),
						result.getString("nationalite"),null,result.getString("commentaire"),result.getDate("dateAjout").toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant(),null,result.getString("Login"),result.getInt("IdCiv"));
				if (result.getDate("dateDeces")!=null) {
					civil.setDeathDate(result.getDate("dateDeces").toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant());
				}
				if (result.getDate("dateModification")!=null) {
					civil.setModifDate(result.getDate("dateModification").toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant());
				}
				
				
				listeCivil.add(civil);
			}
			return listeCivil;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public String droits(int idciv) {
		String drt="";
		Connection connection = getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = connection.createStatement();
			ResultSet result = statement.executeQuery("select droit from droits where IDcivil = " + "'" +idciv+ "'");
			if (result.next()) {
				if (result.getInt("droit")==1) {
					drt="Civil";
				}
				if (result.getInt("droit")==2) {
					drt="Association";
				}
				if (result.getInt("droit")==3) {
					drt="Admin";
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		return drt;
	}

	
	
}
