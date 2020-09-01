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

import controller.UserController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Civil;
import model.SuperVilain;
import service.dao.UserDao;

public class FicheSVController implements Initializable{
	@FXML
	private Slider score;
	@FXML
	private TextField comm;
	@FXML
	private TextField ptfai;
	@FXML
	private TextField pouvoir;
	@FXML
	private TextField name;
	@FXML
	private TableView<FxCivil> tabciv;
	@FXML
	private TableColumn<FxCivil, String> colnom;
	@FXML
	private TableColumn<FxCivil, String> colprenom;
	@FXML
	private TextField nom;
	@FXML
	private TextField prenom;
	@FXML
	private Button btnfiltre;
	@FXML
	private Button btnfval;
	@FXML
	private Button btnann;
	@FXML
	private TextField civchoi;
	private SuperVilain SV;
	
	public int getIdciv() {
		return idciv;
	}
	public void setIdciv(int idciv) {
		this.idciv = idciv;
	}
	private int idciv=0;
	// Event Listener on Button[#btnfiltre].onAction
	@FXML
	public void btnfiltreh(ActionEvent event) {
		// TODO Autogenerated
		if (nom.getText().length()!=0 || prenom.getText().length()!=0) {
			ObservableList<FxCivil> dat =FXCollections.observableArrayList(remptb());
			tabciv.setItems(dat);
		}else {
			UserController user = new UserController();
			ObservableList<FxCivil> data = FXCollections.observableArrayList(user.findAllCivil());
			tabciv.setItems(data);
		}
	}
	public ObservableList<FxCivil> remptb(){
		ArrayList<Civil> data = new ArrayList<Civil>();
		ObservableList<FxCivil> dat =FXCollections.observableArrayList();
		String query="";
		if (nom.getText().length()!=0 && prenom.getText().length()!=0) {
			query="select * from civil WHERE nom LIKE '%"+nom.getText()+"%' AND prenom LIKE '%"+prenom.getText()+"%'";
		}
		if (nom.getText().length()==0 && prenom.getText().length()!=0) {
			query="select * FROM civil WHERE prenom LIKE  '%"+prenom.getText()+"%'";
		}
		if (nom.getText().length()!=0 && prenom.getText().length()==0) {
			query="select * FROM civil WHERE nom LIKE  '%"+nom.getText()+"%'";
		}
		UserDao user = new UserDao();
		Connection conn = user.getConnection();
		Statement statement=null;
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query);
			while(result.next()) {
				Civil civ = new Civil(result.getString("nom"),result.getString("prenom"),result.getString("civilite"),result.getString("coordonees"),result.getDate("dateNaissance").toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant(),result.getString("nationalite"),null,null,result.getDate("dateAjout").toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant(),null,null,result.getInt("IdCiv"));
				if (result.getDate("dateDeces")!=null) {
					civ.setDeathDate(result.getDate("dateDeces").toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant());
				}
				if (result.getString("Login")!=null) {
					civ.setLogin(result.getString("Login"));
				}
				if (result.getString("commentaire")!=null) {
					civ.setCommentaire(result.getString("commentaire"));
				}
				if (result.getDate("dateModification")!=null) {
					civ.setModifDate(result.getDate("dateModification").toLocalDate().atStartOfDay(ZoneOffset.UTC).toInstant());
				}
				data.add(civ);
			}
			for (Civil civ : data) {
				FxCivil fxciv = new FxCivil(civ);
				dat.add(fxciv);
			}
			return dat;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	// Event Listener on Button[#btnfval].onAction
	@FXML
	public void btnfvalh(ActionEvent event) {
		// TODO Autogenerated
		boolean shex=false;
		if (name.getText().length()==0 || pouvoir.getText().length()==0 || ptfai.getText().length()==0) {
			JOptionPane.showMessageDialog(null, "Remplissez tous les champs suivis d'* svp", "Erreur", JOptionPane.ERROR_MESSAGE);	
		}else {
			if (getIdciv()!=0) {
				UserDao user = new UserDao();
				Connection conn = user.getConnection();
				Statement statement=null;
				String query="SELECT * FROM superheros WHERE IdCiv = "+idciv;
				try {
					statement=conn.createStatement();
					ResultSet rs = statement.executeQuery(query);
					if (rs.next()) {
						shex=true;
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			if (shex==false) {
				if (SV!=null) {
					UserDao user = new UserDao();
					Connection conn = user.getConnection();
					Statement statement=null;
					String query="UPDATE supervilain SET nom ='"+name.getText()+"', pouvoir = '"+pouvoir.getText()+"', pointFaible = '"+ptfai.getText()+"', degresMalveillance = "+(int)score.getValue();
					if (getIdciv()!=0) {
						query+=", IdCiv = "+getIdciv();
					}
					if (comm.getText().length()!=0) {
						query+=", commentaire = '"+comm.getText()+"'";
					}
					query+=" WHERE idSV = "+SV.getIdSV();
					try {
						statement = conn.createStatement();
						statement.executeQuery(query);
						JOptionPane.showMessageDialog(null, "Modification r�ussie", "Changement", JOptionPane.INFORMATION_MESSAGE);
						try {
							Parent page =(Parent) FXMLLoader.load(getClass().getResource("SuperVilains.fxml"));
							Scene scene4 = new Scene(page);
							Main.window.setScene(scene4);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Main.window.setTitle("Super vilains");
						Main.window.setWidth(910);
						Main.window.setHeight(650);
					} catch (SQLException e) {
						throw new RuntimeException(e);
					}
				}else {
					if (getIdciv()!=0) {
						UserDao user = new UserDao();
						Connection conn = user.getConnection();
						Statement statement=null;
						String query="";
						query = "SELECT * from supervilain WHERE IdCiv ="+idciv;
						try {
							statement = conn.createStatement();
							ResultSet result = statement.executeQuery(query);
							if (result.next()) {
								query = "UPDATE supervilain SET nom = '"+name.getText()+"', pouvoir = '"+pouvoir.getText()+"',degresMalveillance ="+(int)score.getValue()+", pointFaible= '"+ptfai.getText()+"'";
								if (comm.getText().length()!=0) {
									query +=",commentaire = '"+comm.getText()+"'";
								} 
								query+=" WHERE IdCiv = "+idciv;
								statement.executeQuery(query);
								JOptionPane.showMessageDialog(null, "Changement r�ussi", "Changement", JOptionPane.INFORMATION_MESSAGE);
								try {
									Parent page =(Parent) FXMLLoader.load(getClass().getResource("SuperVilains.fxml"));
									Scene scene4 = new Scene(page);
									Main.window.setScene(scene4);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								Main.window.setTitle("Super vilains");
								Main.window.setWidth(910);
								Main.window.setHeight(650);
							}else {
								query = "INSERT INTO supervilain (nom,pouvoir,degresMalveillance,pointFaible,IdCiv";
								if (comm.getText().length()!=0) {
									query +=",commentaire";
								}
								query+=") VALUES ('"+name.getText()+"','"+pouvoir.getText()+"',"+(int)score.getValue()+",'"+ptfai.getText()+"',"+idciv;
								if (comm.getText().length()!=0) {
									query +=",'"+comm.getText()+"'";
								}
								query+=")";
								try {
									statement = conn.createStatement();
									statement.executeQuery(query);
									JOptionPane.showMessageDialog(null, "Ajout r�ussi", "Ajout", JOptionPane.INFORMATION_MESSAGE);
									try {
										Parent page =(Parent) FXMLLoader.load(getClass().getResource("SuperVilains.fxml"));
										Scene scene4 = new Scene(page);
										Main.window.setScene(scene4);
									} catch (IOException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									Main.window.setTitle("Super vilains");
									Main.window.setWidth(910);
									Main.window.setHeight(650);
								} catch (SQLException e) {
									throw new RuntimeException(e);
								}
							}
						} catch (SQLException e) {
							throw new RuntimeException(e);
						}	
					}else {
						UserDao user = new UserDao();
						Connection conn = user.getConnection();
						Statement statement=null;
						String query="INSERT INTO supervilain (nom, pouvoir, pointFaible, degresMalveillance";
						if (comm.getText().length()!=0) {
							query+=",commentaire";
						}
						query+=") VALUES ('"+name.getText()+"','"+pouvoir.getText()+"','"+ptfai.getText()+"',"+(int)score.getValue();
						if (comm.getText().length()!=0) {
							query+=",'"+comm.getText()+"'";
						}
						query+=")";
						try {
							statement = conn.createStatement();
							statement.executeQuery(query);
							JOptionPane.showMessageDialog(null, "Ajout r�ussi", "Ajout", JOptionPane.INFORMATION_MESSAGE);
							try {
								Parent page =(Parent) FXMLLoader.load(getClass().getResource("SuperVilains.fxml"));
								Scene scene4 = new Scene(page);
								Main.window.setScene(scene4);
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							Main.window.setTitle("Super vilains");
							Main.window.setWidth(910);
							Main.window.setHeight(650);
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				}
			}else {
				JOptionPane.showMessageDialog(null, "Ajout impossible, un super h�ros est d�j� li� � ce civil!", "Erreur", JOptionPane.INFORMATION_MESSAGE);
				try {
					Parent page =(Parent) FXMLLoader.load(getClass().getResource("SuperVilains.fxml"));
					Scene scene4 = new Scene(page);
					Main.window.setScene(scene4);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Main.window.setTitle("Super vilains");
				Main.window.setWidth(910);
				Main.window.setHeight(650);
			}
		}
	}
	// Event Listener on Button[#btnann].onAction
	@FXML
	public void btnannh(ActionEvent event) {
		// TODO Autogenerated
		try {
			Parent page =(Parent) FXMLLoader.load(getClass().getResource("SuperVilains.fxml"));
			Scene scene4 = new Scene(page);
			Main.window.setScene(scene4);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Main.window.setTitle("Super vilains");
		Main.window.setWidth(910);
		Main.window.setHeight(650);
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		UserController user = new UserController();
		ObservableList<FxCivil> data = FXCollections.observableArrayList(user.findAllCivil());
		tabciv.setEditable(true);
		colprenom.setCellValueFactory(new PropertyValueFactory<>("lastName"));
		colnom.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		tabciv.setItems(data);
		tabciv.setOnMouseClicked( event -> {
			if( event.getClickCount() == 2 && tabciv.getSelectionModel().getSelectedItem()!=null) {
			  setIdciv(tabciv.getSelectionModel().getSelectedItem().getCivil().getId());
			  civchoi.setText(tabciv.getSelectionModel().getSelectedItem().getFirstName()+" "+tabciv.getSelectionModel().getSelectedItem().getLastName());
			}});
	}
	public void remplissage(SuperVilain SV) {
		name.setText(SV.getNom());
		pouvoir.setText(SV.getPouvoir());
		ptfai.setText(SV.getPointFaible());
		score.setValue((double)SV.getDegresMalveillance());
		if (SV.getCommentaire2()!=null) {
			comm.setText(SV.getCommentaire2());
		}
		if(SV.getId()!=0) {
			idciv=SV.getId();
		}
		UserDao user = new UserDao();
		Connection conn = user.getConnection();
		Statement statement=null;
		String query2="SELECT * FROM supervilain WHERE idSV = "+SV.getIdSV();
		try {
			statement = conn.createStatement();
			ResultSet result = statement.executeQuery(query2);
			if (result.next()) {
				if (result.getInt("idCiv")!=0) {
					setIdciv(result.getInt("idCiv"));
					SV.setId(result.getInt("idCiv"));
				}else {
					setIdciv(0);
					SV.setId(0);
				}
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		if (SV.getId()!=0) {
			String query="SELECT * FROM civil WHERE IdCiv = "+SV.getId();
			try {
				statement = conn.createStatement();
				ResultSet result = statement.executeQuery(query);
				if (result.next()) {
					civchoi.setText(result.getString("nom")+" "+result.getString("prenom"));
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}	
		}
	}
	public FicheSVController(SuperVilain SV) {
		this.SV=SV;
	}
}