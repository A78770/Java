package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javax.swing.JOptionPane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import service.dao.UserDao;

public class DroitsController implements Initializable{
	@FXML
	private ComboBox<String> combo_uti;
	@FXML
	private ComboBox<String> combo_droits;
	@FXML
	private Button btn_val;
	@FXML
	private Button btn_ann;
	@FXML
	private Button supprdrt;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
			UserDao user = new UserDao();
			ObservableList<String> liste = FXCollections.observableArrayList("Administrateur", "Civils", "Organisations");
			ObservableList<String> nom = FXCollections.observableArrayList();
			combo_droits.setItems(liste);
			Connection connection = user.getConnection();
			Statement statement=null;
			
			try {
				statement = connection.createStatement();
				String query = "select * from civil where Login IS NOT NULL";
				ResultSet result =statement.executeQuery(query);
				while(result.next()) {
					nom.add(result.getString("Login"));
				}
				combo_uti.setItems(nom);
				
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
	}
	@FXML
	public void handleuti(ActionEvent e) {
	}
	@FXML
	public void handlebtnvaldrt(ActionEvent e) {
		int iduti=0;
		int i=0;
		boolean mod=false;
		if(combo_droits.getValue()!=null || combo_uti.getValue()!=null) {
			UserDao user = new UserDao();
			Connection connection = user.getConnection();
			Statement statement=null;
			try {
				statement = connection.createStatement();
				String query = "select * from civil where Login='"+combo_uti.getValue()+"'";
				ResultSet result =statement.executeQuery(query);
				while(result.next()) {
					iduti=result.getInt("IdCiv");
				}
				query="select * from droits WHERE IDcivil = '"+iduti+"'";
				result=statement.executeQuery(query);
				if(result.next()) {
					i++;
					if (result.getInt("droit")==3) {
						query="SELECT COUNT (*) AS total FROM droits WHERE droit=3";
						result=statement.executeQuery(query);
						if (result.next()) {
							if(result.getInt("total")==1) {
								JOptionPane.showMessageDialog(null, "Modification impossible, il doit y avoir au moins un administrateur", "Information", JOptionPane.INFORMATION_MESSAGE);
							}else {
								query="UPDATE droits SET droit = ";
								if(combo_droits.getValue()=="Administrateur") {
									query+= "'3'";
								}
								if(combo_droits.getValue()=="Civils") {
									query+= "'1'";
								}
								if(combo_droits.getValue()=="Organisations") {
									query+= "'2'";
								}
								query+=" WHERE IDcivil="+iduti;
								statement.executeQuery(query);
								JOptionPane.showMessageDialog(null, "Droits changés/ajoutés", "Réussite", JOptionPane.INFORMATION_MESSAGE);
								mod=true;
							}
						}
					}else {
						query="UPDATE droits SET droit = ";
						if(combo_droits.getValue()=="Administrateur") {
							query+= "'3'";
						}
						if(combo_droits.getValue()=="Civils") {
							query+= "'1'";
						}
						if(combo_droits.getValue()=="Organisations") {
							query+= "'2'";
						}
						query+=" WHERE IDcivil="+iduti;
						statement.executeQuery(query);
						JOptionPane.showMessageDialog(null, "Droits changés/ajoutés", "Réussite", JOptionPane.INFORMATION_MESSAGE);
						mod=true;
					}
					
				}
				if (i==0) {
					query="INSERT INTO droits (IDcivil, droit) VALUES ('"+iduti+"',";
					if(combo_droits.getValue()=="Administrateur") {
						query+= "'3')";
					}
					if(combo_droits.getValue()=="Civils") {
						query+= "'1')";
					}
					if(combo_droits.getValue()=="Organisations") {
						query+= "'2')";
					}
					statement.executeQuery(query);
					JOptionPane.showMessageDialog(null, "Droits changés/ajoutés", "Réussite", JOptionPane.INFORMATION_MESSAGE);
					mod=true;
				}
				if (mod) {
					try {
						Parent page =(Parent) FXMLLoader.load(getClass().getResource("Menu.fxml"));
						Scene scene4 = new Scene(page);
						Main.window.setScene(scene4);
						Main.window.setTitle("Menu");
						Main.window.setWidth(600);
						Main.window.setHeight(300);
					} catch (IOException g) {
						// TODO Auto-generated catch block
						g.printStackTrace();
					}
				}
			} catch (SQLException f) {
				throw new RuntimeException(f);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Veuillez choisir un utilisateur et des droits svp", "Réussite", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	@FXML
	public void handlebtnann(ActionEvent e) {
		try {
			Parent page =(Parent) FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene4 = new Scene(page);
			Main.window.setScene(scene4);
			Main.window.setTitle("Menu");
			Main.window.setWidth(600);
			Main.window.setHeight(300);
		} catch (IOException g) {
			// TODO Auto-generated catch block
			g.printStackTrace();
		}

	}
	public void handlesupp(ActionEvent e) {
		if (combo_uti.getValue()==null) {
			JOptionPane.showMessageDialog(null, "Veuillez choisir un utilisateur svp", "Information", JOptionPane.INFORMATION_MESSAGE);
		}else {
			int iduti=0;
			UserDao user = new UserDao();
			Connection connection = user.getConnection();
			Statement statement=null;
			try {
				statement = connection.createStatement();
				String query = "select * from civil where Login='"+combo_uti.getValue()+"'";
				ResultSet result =statement.executeQuery(query);
				if(result.next()) {
					iduti=result.getInt("IdCiv");
				}
				query = "select * from droits where IDcivil = "+iduti;
				result =statement.executeQuery(query);
				if(result.next()) {
					if(result.getInt("droit")==3) {
						query="SELECT COUNT(*) as total FROM droits WHERE droit = '3'";
						result =statement.executeQuery(query);
						if(result.next()) {
							if (result.getInt("total")==1){
								JOptionPane.showMessageDialog(null, "Suppression impossible, il doit y avoir au moins un administrateur", "Information", JOptionPane.INFORMATION_MESSAGE);
							}else {
								query = "DELETE FROM droits WHERE IDcivil = '"+iduti+"' AND droit = '3'";
								statement.executeQuery(query);
								JOptionPane.showMessageDialog(null, "Suppression réussie", "Information", JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}else {
						query = "DELETE FROM droits WHERE IDcivil = '"+iduti+"' AND droit = '"+result.getInt("droit")+"'";
						statement.executeQuery(query);
						JOptionPane.showMessageDialog(null, "Suppression réussie", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
				}else {
					JOptionPane.showMessageDialog(null, "L'utilisateur n'a pas de droits, suppression impossible", "Information", JOptionPane.INFORMATION_MESSAGE);
				}
			}
			catch (SQLException k) {
				throw new RuntimeException(k);
			}
		}
		}
}
