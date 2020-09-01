package ui;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.ZoneOffset;
import java.util.ArrayList;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import organisation.Organisation;
import service.dao.UserDao;

public class OrganisationController implements Initializable{
	@FXML
	private TableView<FxOrg> tabOrg;
	@FXML
	private TableColumn<FxOrg, String> colNom;
	@FXML
	private TableColumn<FxOrg, String> colSieSoc;
	@FXML
	private TableColumn<FxOrg, String> colDir;
	@FXML
	private TableColumn<FxOrg, String> colMembre;
	@FXML
	private TableColumn<FxOrg, String> colComm;
	@FXML
	private TableColumn<FxOrg, String> colDateAj;
	@FXML
	private Button btnAAjt;
	@FXML
	private Button btnSupp;
	@FXML
	private Button btnAnn;
	@FXML
	private Button btn_raf;
	private ObservableList<FxOrg> data = FXCollections.observableArrayList(findAllFx());
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		tabOrg.setEditable(true);
		colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
		colSieSoc.setCellValueFactory(new PropertyValueFactory<>("SiegeSocial"));
		colDir.setCellValueFactory(new PropertyValueFactory<>("Dirigeant"));
		colMembre.setCellValueFactory(new PropertyValueFactory<>("Membres"));
		colComm.setCellValueFactory(new PropertyValueFactory<>("Commentaire"));
		colDateAj.setCellValueFactory(new PropertyValueFactory<>("DateAjout"));
		tabOrg.setItems(data);
		tabOrg.setOnMouseClicked( event -> {
			if( event.getClickCount() == 2 && tabOrg.getSelectionModel().getSelectedItem()!=null) {
					Organisation org = tabOrg.getSelectionModel().getSelectedItem().getOrg();
					 try {	
						 	FXMLLoader loader = new FXMLLoader(getClass().getResource("FichOrg.fxml"));
							FichOrgController fch = new FichOrgController(org);
							loader.setController(fch);
						 	Parent page = loader.load() ;
							Scene scene4 = new Scene(page);
							Main.window.setScene(scene4);
							Main.window.setTitle("Fiche organisation");
							Main.window.setWidth(400);
							Main.window.setHeight(400);
						} catch (IOException g) {
							// TODO Auto-generated catch block
							g.printStackTrace();
						}
			}});
	}
	public ArrayList<Organisation> findAllOrg(){
		ArrayList<Organisation> list = new ArrayList<Organisation>();
		UserDao user = new UserDao();
		Connection conn = user.getConnection();
		Statement statement=null;
		// TODO
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery("select * from organisation");
			while(result.next()) {
				Organisation org = new Organisation(result.getString("nom"),result.getString("siegeSocial"),result.getString("dirigeant"),result.getInt("membres"),result.getString("commentaire"),result.getDate("dateAjout").toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant(),result.getInt("idOrg"));	
				System.out.println(result.getString("nom")+" "+result.getDate("dateAjout").toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant());
				list.add(org);
			}
			return list;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public ArrayList<FxOrg> findAllFx(){
		ArrayList<FxOrg> liste = new ArrayList<FxOrg>();
		ArrayList<Organisation> list = findAllOrg();
		for (Organisation org : list) {
			FxOrg fxorg = new FxOrg(org);
			liste.add(fxorg);
		}
		return liste;
	}
	public void btnrafh(ActionEvent e) {
		data.clear();
		data = FXCollections.observableArrayList(findAllFx());
		tabOrg.setEditable(true);
		colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
		colSieSoc.setCellValueFactory(new PropertyValueFactory<>("SiegeSocial"));
		colDir.setCellValueFactory(new PropertyValueFactory<>("Dirigeant"));
		colMembre.setCellValueFactory(new PropertyValueFactory<>("Membres"));
		colComm.setCellValueFactory(new PropertyValueFactory<>("Commentaire"));
		colDateAj.setCellValueFactory(new PropertyValueFactory<>("DateAjout"));
		tabOrg.setItems(data);
	}
	public void btnajth(ActionEvent e) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("FichOrg.fxml"));
			FichOrgController fch = new FichOrgController(null);
			loader.setController(fch);
		 	Parent page = loader.load() ;
		 	Scene scene4 = new Scene(page);
			Main.window.setScene(scene4);
			Main.window.setTitle("Fiche organisation");
			Main.window.setWidth(400);
			Main.window.setHeight(400);
		} catch (IOException g) {
			// TODO Auto-generated catch block
			g.printStackTrace();
		}
	}
	public void btnannh(ActionEvent e) {
		try {
			Parent page =(Parent) FXMLLoader.load(getClass().getResource("Menu.fxml"));
			Scene scene4 = new Scene(page);
			Main.window.setScene(scene4);
			Main.window.setTitle("Menu");
			Main.window.setWidth(600);
			Main.window.setHeight(300);
		} catch (IOException k) {
			// TODO Auto-generated catch block
			k.printStackTrace();
		}
	}
	public void btnsupph(ActionEvent e) {
		if (tabOrg.getSelectionModel().getSelectedItem()!=null) {
			Organisation org = tabOrg.getSelectionModel().getSelectedItem().getOrg();
			System.out.println(org.getId());
			UserDao user = new UserDao();
			Connection conn = user.getConnection();
			Statement statement=null;
			// TODO
			try {
				statement = conn.createStatement();
				ResultSet result = statement.executeQuery("select * from appartenir where idOrg = "+org.getId());
				if(result.next()) {
					JOptionPane.showMessageDialog(null, "Vous ne pouvez pas supprimer une organisation à laquelle des civils sont liés", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else {
					statement.executeQuery("Delete from `organisation` where idOrg = "+org.getId());
					JOptionPane.showMessageDialog(null, "Suppression réussie", "Information", JOptionPane.INFORMATION_MESSAGE);
					rafraichissement();
				}
			} catch (SQLException q) {
				throw new RuntimeException(q);
			}
		}else {
			JOptionPane.showMessageDialog(null, "Veuillez choisir une ligne de la table svp", "Information", JOptionPane.INFORMATION_MESSAGE);
		}	
	}
	public void rafraichissement() {
		data.clear();
		data = FXCollections.observableArrayList(findAllFx());
		tabOrg.setEditable(true);
		colNom.setCellValueFactory(new PropertyValueFactory<>("Nom"));
		colSieSoc.setCellValueFactory(new PropertyValueFactory<>("SiegeSocial"));
		colDir.setCellValueFactory(new PropertyValueFactory<>("Dirigeant"));
		colMembre.setCellValueFactory(new PropertyValueFactory<>("Membres"));
		colComm.setCellValueFactory(new PropertyValueFactory<>("Commentaire"));
		colDateAj.setCellValueFactory(new PropertyValueFactory<>("DateAjout"));
		tabOrg.setItems(data);
	}
}	

