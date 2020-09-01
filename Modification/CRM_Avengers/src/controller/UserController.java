package controller;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import model.Civil;
import service.dao.CivilDao;
import service.dao.UserDao;
import ui.FxCivil;

public class UserController {
	
	private CivilDao civilDao = new CivilDao();
		
	public void onInsert(Civil civil) {
		civilDao.insertCivil(civil);
	}
		
	public void onUpdate(Civil civil) {
		civilDao.updateCivil(civil);
		
	}
	
	public boolean onSuppr(Civil civil) {
		if(civilDao.onSupprCivil(civil)) {
			return true;
		}else {
			return false;
		}
	}
	
	public ArrayList<FxCivil> findAllCivil(){
		ArrayList<Civil> civils = civilDao.findAllCivil();
		ArrayList<FxCivil> fxCivils = new ArrayList<FxCivil>();
		for (Civil civil : civils) {
			String res="";
			boolean jamaisPass=true;
			FxCivil fxCivil = new FxCivil(civil);
			UserDao user = new UserDao();
			Connection conn = user.getConnection();
			Statement statement=null;
			try {
				String query2="";
				statement = conn.createStatement();
				String query="Select * FROM appartenir WHERE IdCiv = "+fxCivil.getCivil().getId();
				ResultSet rs = statement.executeQuery(query);
				while (rs.next()) {
					query2="Select  * From organisation WHERE idOrg = "+rs.getInt("idOrg");
					ResultSet rs2 = statement.executeQuery(query2);
					if (rs2.next()) {
						if (jamaisPass) {
							res=rs2.getString("nom");
							jamaisPass=false;
						}else {
							res+=" / "+rs2.getString("nom");
						}
					}
					fxCivil.setOrg(res);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			fxCivils.add(fxCivil);
		}
		return fxCivils;
	}
	
	
}

