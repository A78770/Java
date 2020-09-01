package service.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.swing.JOptionPane;

import model.Civil;

public class UserDao {
	
	public Connection getConnection() {
		String url = "jdbc:mariadb://localhost:3306/CRM_AVENGERS";
		Properties props = new Properties();
		props.setProperty("user", "root");
		props.setProperty("password", "");
		try {
			return DriverManager.getConnection(url, props);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}


	public void updateCivil(Civil civil) {
		System.out.println("update BDD, etc... (" + civil.getFirstName() + ")");
		Connection connection = getConnection();
		
		Statement statement=null;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone( ZoneId.systemDefault() );
		
		try {
			statement = connection.createStatement();
			String query = "UPDATE civil ";
			query += "SET nom = '"+civil.getFirstName()+"', ";
			query += "prenom = '"+civil.getLastName()+"', ";
			query += "Login = '"+civil.getLogin()+"', ";
			query += "civilite = '"+civil.getCivility()+"', ";
			query += "coordonees = '"+civil.getContactInfo()+"', ";
			query += "nationalite = '"+civil.getNationality()+"', ";
			query += "dateNaissance = '"+formatter.format(civil.getBirthday())+"', ";
			if (civil.getDeathDate()!=null) {
				query += "dateDeces = '"+formatter.format(civil.getDeathDate())+"', ";
			}
			if (civil.getCommentaire()!=null) {
				query += "commentaire = '"+civil.getCommentaire()+"', ";
			}
			query += "dateModification = '"+formatter.format(Instant.now())+"' ";
			query += "WHERE IdCiv = '"+civil.getId()+"'";
			statement.executeQuery(query);

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		
	}
	
	public void insertCivil(Civil civil) {
		System.out.println("insert BDD, etc... (" + civil.getFirstName() + ")");
		Connection connection = getConnection();
		
		Statement statement=null;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd").withZone( ZoneId.systemDefault() );
		
		try {
			statement = connection.createStatement();
			String query = "INSERT INTO civil (nom, prenom, Login, civilite, coordonees, nationalite, dateNaissance";
			if (civil.getDeathDate()!=null) {
				query += ", dateDeces";
			}
			if (civil.getCommentaire()!="") {
				query += ", commentaire";
			}
			query += ", dateAjout) VALUES ('"+civil.getFirstName()+"', '"+civil.getLastName()+"', '"+civil.getLogin()+"', " 
					+ "'"+civil.getCivility()+"', '"+civil.getContactInfo()+"', '"+civil.getNationality()+"', '"+formatter.format(civil.getBirthday())+"',";
			if (civil.getDeathDate()!=null) {
				query += "'"+formatter.format(civil.getDeathDate())+"', ";
			}
			if (civil.getCommentaire()!="") {
				query += "'"+civil.getCommentaire()+"', ";
			}
			query += "'"+formatter.format(Instant.now())+"')";
			statement.executeUpdate(query,Statement.RETURN_GENERATED_KEYS);
			
			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                civil.setId(generatedKeys.getInt(1));
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
	        }
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean onSupprCivil(Civil civil) {
		System.out.println("Suppr BDD, etc... (" + civil.getFirstName() + ")");
		Connection connection = getConnection();
		boolean veutSupp = true;
		boolean shex = false;
		Statement statement=null;
		try {
			statement = connection.createStatement();
			String query2= "SELECT * FROM superheros WHERE IdCiv = "+civil.getId();
			ResultSet result2 = statement.executeQuery(query2);
			if (result2.next()) {
				shex=true;
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int dialogResult = JOptionPane.showConfirmDialog (null, "Voulez-vous supprimer le super héros lié à ce civil?","Attention",dialogButton);
				if (dialogResult == JOptionPane.YES_OPTION) {
					veutSupp=true;
				}else {
					veutSupp=false;
				}
			}
			String query="SELECT * FROM droits WHERE IDcivil="+civil.getId();
			ResultSet result = statement.executeQuery(query);
			if(result.next()) {
				query="SELECT COUNT(*) as total FROM droits WHERE droit=3";
				result=statement.executeQuery(query);
				if (result.next()) {
					if(result.getInt("total")==1) {
						query="SELECT * FROM droits WHERE droit=3 AND IDcivil="+civil.getId();
						result=statement.executeQuery(query);
						if(result.next()) {
							JOptionPane.showMessageDialog(null, "Suppression impossible, cet utilisateur est le seul administrateur", "Information", JOptionPane.INFORMATION_MESSAGE);
							return false;
						}else {
							if (veutSupp) {
								if (shex) {
									query="DELETE FROM superheros where IdCiv="+civil.getId();
									statement.executeQuery(query);
								}
								query="DELETE FROM appartenir where IdCiv="+civil.getId();
								statement.executeQuery(query);
								query="DELETE FROM droits where IDcivil="+civil.getId();
								statement.executeQuery(query);
								query = "DELETE FROM civil where IdCiv="+civil.getId();
								statement.executeQuery(query);
								JOptionPane.showMessageDialog(null, "Suppression réussie", "Information", JOptionPane.INFORMATION_MESSAGE);
								return true;
							}else {
								JOptionPane.showMessageDialog(null, "Suppression annulée, vous ne voulez pas supprimer le super héros lié", "Information", JOptionPane.INFORMATION_MESSAGE);
								return false;
							}
						}
					}else {
						if (veutSupp) {
							if (shex) {
								query="DELETE FROM superheros where IdCiv="+civil.getId();
								statement.executeQuery(query);
							}
						query="DELETE FROM appartenir where IdCiv="+civil.getId();
						statement.executeQuery(query);
						query="DELETE FROM droits where IDcivil="+civil.getId();
						statement.executeQuery(query);
						query = "DELETE FROM civil where IdCiv="+civil.getId();
						statement.executeQuery(query);
						JOptionPane.showMessageDialog(null, "Suppression réussie", "Information", JOptionPane.INFORMATION_MESSAGE);
						return true;
						}else {
							JOptionPane.showMessageDialog(null, "Suppression annulée, vous ne voulez pas supprimer le super héros lié", "Information", JOptionPane.INFORMATION_MESSAGE);
							return false;
						}
					}	
				}else {
					return false;
				}
			}else {
				if (veutSupp) {
					if (shex) {
						query="DELETE FROM superheros where IdCiv="+civil.getId();
						statement.executeQuery(query);
					}
				query="DELETE FROM appartenir where IdCiv="+civil.getId();
				statement.executeQuery(query);
				query = "DELETE FROM civil where IdCiv="+civil.getId();
				statement.executeQuery(query);
				JOptionPane.showMessageDialog(null, "Suppression réussie", "Information", JOptionPane.INFORMATION_MESSAGE);
				return true;
				}else {
					JOptionPane.showMessageDialog(null, "Suppression annulée, vous ne voulez pas supprimer le super héros lié", "Information", JOptionPane.INFORMATION_MESSAGE);
					return false;
				}
			}
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
		/*try {
			Connection conn = DriverManager.getConnection(url, user, mdp);
			//Création d'un objet Statement
		      Statement state = conn.createStatement();
		      //L'objet ResultSet contient le résultat de la requête SQL
		      ResultSet result = state.executeQuery("SELECT Login FROM civil");
		      //On récupère les MetaData
		      ResultSetMetaData resultMeta = result.getMetaData();
		      
		      while(result.next()){         
		        for(int i = 1; i <= resultMeta.getColumnCount(); i++)
		        	Log.add(result.getObject(i).toString());

		      }

		      result.close();
		      state.close();
		         
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
}

