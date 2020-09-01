package ui;


import java.io.IOException;

import javax.swing.JOptionPane;

import controller.UserController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.Civil;
import service.dao.CivilDao;



public class Main extends Application implements EventHandler<ActionEvent>{
	
	public static Stage window;
	public static Scene scene1, scene2;
	Button button;
	TextField userLogin;
	PasswordField mdpUser;
	JOptionPane jop;
	private UserController userController = new UserController();
	private CivilDao civildao = new CivilDao();
	private ImageView img = new ImageView();
	private TableView<FxCivil> table = new TableView<FxCivil>();
	private ObservableList<FxCivil> data = FXCollections.observableArrayList(userController.findAllCivil());
			/*new FxCivils("Jacob", "Smith", "jacob.smith@example.com", "BlackWidow"),
			new FxCivils("Isabella", "Johnson", "isabella.johnson@example.com","Ironman"),
			new FxCivils("Ethan", "Williams", "ethan.williams@example.com","CaptainAmerica"),
			new FxCivils("Emma", "Jones", "emma.jones@example.com","Groot"),
			new FxCivils("Michael", "Brown", "michael.brown@example.com","Rocket"));*/
	final HBox hb = new HBox();

	public static void main(String[] args) {
		launch(args);
	}
 
	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) {
			window = stage;
			Image image  = new Image("/icons/unnamed.png");
			img.setImage(image);
			window.getIcons().add(image);
			Label label1 = new Label("Connexion au CRM des Avengers");
			label1.setFont(new Font("Arial",20));
			Label label2 = new Label("Login");
			label2.setFont(new Font("Arial",12));
			final TextField user = new TextField();
			userLogin=user;
			final PasswordField mdp = new PasswordField();
			mdp.setPromptText("Votre mot de passe");
			mdpUser=mdp;
			Label label3 = new Label("Mot de passe");
			label3.setFont(new Font("Arial",12));
			Button button1 = new Button("Connexion");
			button=button1;
			button1.setOnAction(this::handle);
			VBox layout1 = new VBox(20);
			layout1.getChildren().addAll(label1,img,label2,user,label3,mdp,button1);
			scene1 = new Scene(layout1,450,550);
			
			
			scene2 = new Scene(new Group());
			window.setTitle("Civils");
			window.setWidth(450);
			window.setHeight(550);
			
			final Label label = new Label("Civils");
			label.setFont(new Font("Arial", 20));
			
			table.setEditable(true);
			Callback<TableColumn<FxCivil, String>, TableCell<FxCivil, String>> cellFactory = new Callback<TableColumn<FxCivil, String>, TableCell<FxCivil, String>>() {
				public TableCell<FxCivil, String> call(TableColumn<FxCivil, String> p) {
					return new CivilCell();
				}
			};
			
			TableColumn<FxCivil, String> firstNameCol = createColumn(cellFactory, "firstName", "Nom",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setFirstName(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});
			
			TableColumn<FxCivil, String> lastNameCol = createColumn(cellFactory, "lastName", "Prénom",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setLastName(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});
			
			TableColumn<FxCivil, String> loginCol = createColumn(cellFactory, "login", "Login",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setLogin(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});
			TableColumn<FxCivil, String> civiliteCol = createColumn(cellFactory, "civilite", "Civilite",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setCivilite(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});
			TableColumn<FxCivil, String> coordCol = createColumn(cellFactory, "coordonnees", "Coordonnees",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setCoordonnees(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});
			TableColumn<FxCivil, String> natCol = createColumn(cellFactory, "nationalite", "Nationalite",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setNationalite(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});
			TableColumn<FxCivil, String> bthCol = createColumn(cellFactory, "dateNaissance", "Date de naissance",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setDateNaissance(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});
			TableColumn<FxCivil, String> dthCol = createColumn(cellFactory, "dateDeces", "Date de décès",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setDateDeces(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});
			TableColumn<FxCivil, String> commCol = createColumn(cellFactory, "commentaire", "Commentaire",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setCommentaire(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});
			TableColumn<FxCivil, String> ajoutCol = createColumn(cellFactory, "dateAjt", "Date d'ajout",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setDateAjt(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});	
			TableColumn<FxCivil, String> modifCol = createColumn(cellFactory, "dateMod", "Date de modification",
				new EventHandler<CellEditEvent<FxCivil, String>>() {
					@Override
					public void handle(CellEditEvent<FxCivil, String> t) {
						FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
						fxcivils.setDateMod(t.getNewValue());
						userController.onUpdate(fxcivils.getCivil());
					}
				});
			TableColumn<FxCivil, String> orgCol = createColumn(cellFactory, "org", "Organisation(s)",
					new EventHandler<CellEditEvent<FxCivil, String>>() {
						@Override
						public void handle(CellEditEvent<FxCivil, String> t) {
							FxCivil fxcivils = (FxCivil) t.getTableView().getItems().get(t.getTablePosition().getRow());
							fxcivils.setDateMod(t.getNewValue());
							userController.onUpdate(fxcivils.getCivil());
						}
					});
			orgCol.setMinWidth(200);
			table.setOnMouseClicked( event -> {
				if( event.getClickCount() == 2 && table.getSelectionModel().getSelectedItem()!=null) {
				  
				   try {
					    FxCivil fxcivils = table.getSelectionModel().getSelectedItem();
					    Civil civil = fxcivils.getCivil();
					    FXMLLoader loader = new FXMLLoader(getClass().getResource("FicheCiv.fxml"));
						FicheCivController fch = new FicheCivController(civil);
						loader.setController(fch);
					 	Parent page = loader.load() ;
						Scene scene4 = new Scene(page);
						Main.window.setScene(scene4);
						Main.window.setTitle("Fiche civil : "+civil.getLastName()+" "+civil.getFirstName());
						Main.window.setWidth(350);
						Main.window.setHeight(590);
					} catch (IOException g) {
						// TODO Auto-generated catch block
						g.printStackTrace();
					}
				}});
			
			table.setItems(data);
			table.getColumns().addAll(firstNameCol, lastNameCol,loginCol,civiliteCol,coordCol,bthCol,dthCol,natCol,commCol,ajoutCol,modifCol,orgCol);		
			table.setEditable(false);
			/*final TextField addFirstName = new TextField();
			addFirstName.setPromptText("First Name");
			addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
			final TextField addLastName = new TextField();
			addLastName.setMaxWidth(lastNameCol.getPrefWidth());
			addLastName.setPromptText("Last Name");
			final TextField addLogin = new TextField();
			addLogin.setMaxWidth(loginCol.getPrefWidth());
			addLogin.setPromptText("Login");
			final TextField addCivilite = new TextField();
			addCivilite.setMaxWidth(loginCol.getPrefWidth());
			addCivilite.setPromptText("Civilité");
			final TextField addCoord = new TextField();
			addCoord.setMaxWidth(loginCol.getPrefWidth());
			addCoord.setPromptText("Coordonnées");
			final TextField addNat = new TextField();
			addNat.setMaxWidth(loginCol.getPrefWidth());
			addNat.setPromptText("Nationalité");
			final DatePicker dob = new DatePicker();
			dob.setValue(LocalDate.now());
			dob.setShowWeekNumbers(true);
			final DatePicker deathPicker = new DatePicker();
			deathPicker.setShowWeekNumbers(true);
			final TextField addComm = new TextField();
			addComm.setMaxWidth(loginCol.getPrefWidth());
			addComm.setPromptText("Commentaire");
			
			final Button addButton = new Button("Ajouter civil");
			addButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					if (!addFirstName.getText().equals("") || !addLastName.getText().equals("")
							|| !addLogin.getText().equals("")) {
						FxCivil fxcivils = new FxCivil(addFirstName.getText(), addLastName.getText(), addLogin.getText(),addCivilite.getText(),addCoord.getText(),addNat.getText(),dob.getValue().atStartOfDay(ZoneOffset.UTC).toInstant(),null,"",Instant.now(), null);
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone( ZoneId.systemDefault() );
						System.out.println(deathPicker.getValue());
						if (deathPicker.getValue()!=null) {
							String tmp = formatter.format(deathPicker.getValue().atStartOfDay(ZoneOffset.UTC).toInstant());
							System.out.println(tmp);
							fxcivils.setDateDeces(tmp);
						}
						if (addComm.getText()!="") {
							fxcivils.setCommentaire(addComm.getText());
						}
						data.add(fxcivils);
						addFirstName.clear();
						addLastName.clear();				
						addLogin.clear();
						addCivilite.clear();
						addCoord.clear();
						addNat.clear();
						addComm.clear();
						userController.onInsert(fxcivils.getCivil());
					}
					else {
						System.out.println("ajout impossible (Manque information).");
					}
				}
			});*/
			final Button supprButton = new Button("Supprimer");
			supprButton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					FxCivil selectedCivil = table.getSelectionModel().getSelectedItem();
				       if (selectedCivil != null) {
				    	   if(userController.onSuppr(selectedCivil.getCivil())) {
				    		   table.getItems().remove(selectedCivil);
				    	   }
				       }
		
				}
			});
			final Button annBouton = new Button("Annuler");
			annBouton.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
						Parent page =(Parent) FXMLLoader.load(getClass().getResource("Menu.fxml"));
						Scene scene4 = new Scene(page);
						Main.window.setScene(scene4);
						Main.window.setTitle("Menu");
						Main.window.setWidth(600);
						Main.window.setHeight(340);
					} catch (IOException g) {
						// TODO Auto-generated catch block
						g.printStackTrace();
					}
				}
			});
			final Button Ajouter = new Button("Ajouter");
			Ajouter.setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					try {
					    FXMLLoader loader = new FXMLLoader(getClass().getResource("FicheCiv.fxml"));
						FicheCivController fch = new FicheCivController(null);
						loader.setController(fch);
					 	Parent page = loader.load();
						Scene scene4 = new Scene(page);
						Main.window.setScene(scene4);
						Main.window.setTitle("Fiche civil");
						Main.window.setWidth(350);
						Main.window.setHeight(590);
					} catch (IOException g) {
						// TODO Auto-generated catch block
						g.printStackTrace();
					}
				}
			});
			final Button Refresh = new Button("Rafraîchir");
			Refresh.setOnAction(new EventHandler<ActionEvent>() {
				public void handle(ActionEvent e) {
					refreshtable();
				}	
			});
			
			hb.getChildren().addAll(supprButton,annBouton,Ajouter,Refresh);
			hb.setSpacing(3);

			final VBox vbox = new VBox();
			vbox.setSpacing(5);
			vbox.setPadding(new Insets(10, 0, 0, 10));
			vbox.getChildren().addAll(label, table,hb);

			((Group) scene2.getRoot()).getChildren().addAll(vbox);
			window.setScene(scene1);
			window.show();
		}
			
			
			public TableColumn<FxCivil, String> createColumn(  Callback<TableColumn<FxCivil, String>, TableCell<FxCivil, String>> cellFactory,String colName,String colLabel,EventHandler<CellEditEvent<FxCivil, String>> handler) {

				TableColumn<FxCivil, String> col = new TableColumn<>(colLabel);
				col.setMinWidth(108);
				col.setCellValueFactory(new PropertyValueFactory<FxCivil, String>(colName));
				col.setCellFactory(cellFactory);
				col.setOnEditCommit(handler);
				return col;
			}

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				if (civildao.canConnect(userLogin.getText(),mdpUser.getText())){
					if (civildao.recidco(userLogin.getText())!=0) {
						civildao.setIdco(civildao.recidco(userLogin.getText()));
					}
					System.out.println("Bienvenue " + userLogin.getText());
					/*window.setWidth(1250);
					window.setHeight(550);
					window.setScene(scene2);*/
					try {
						Parent page =(Parent) FXMLLoader.load(getClass().getResource("Menu.fxml"));
						Scene scene4 = new Scene(page);
						window.setScene(scene4);
						window.setTitle("Menu");
						window.setWidth(600);
						window.setHeight(340);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

					
				}
				else {
					JOptionPane.showMessageDialog(null, "Erreur de login ou de mot de passe, impossible de se connecter", "Erreur", JOptionPane.ERROR_MESSAGE);
				}

			}
			
			@FXML
			private Button btnciv;
			@FXML
			private Button btnorg;
			@FXML
			private Button btndroits;
			@FXML
			public void handleAppaBtnCiv(ActionEvent e) {
				if (civildao.droits(civildao.getIdco())=="Association") {
					JOptionPane.showMessageDialog(null, "Vos droits ne vous permettent pas cette action", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				{	
					Main.window.setScene(Main.scene2);
					window.setWidth(1450);
					window.setHeight(550);
					/*try {
						FXMLLoader loader = new FXMLLoader();
					    loader.setLocation(Main.class.getResource("Civil.fxml"));
					    AnchorPane page = (AnchorPane) loader.load();
						//Parent page =(Parent) FXMLLoader.load(getClass().getResource("Civil.fxml"));
						Scene scene5 = new Scene(page);
						window.setScene(scene5);
						window.setTitle("Civil");
						window.setWidth(1200);
						window.setHeight(900);
					} catch (IOException f) {
						// TODO Auto-generated catch block
						f.printStackTrace();
					}*/
				}
			}
			@FXML
			public void handleAppaBtnOrg(ActionEvent e) {
				if (civildao.droits(civildao.getIdco())=="Civil") {
					JOptionPane.showMessageDialog(null, "Vos droits ne vous permettent pas cette action", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						Parent page =(Parent) FXMLLoader.load(getClass().getResource("Organisation.fxml"));
						Scene scene4 = new Scene(page);
						Main.window.setScene(scene4);
						Main.window.setTitle("Organisations");
						Main.window.setWidth(910);
						Main.window.setHeight(680);
					} catch (IOException m) {
						// TODO Auto-generated catch block
						m.printStackTrace();
					}
				}
			}
			@FXML
			public void handleBtnDrt(ActionEvent e) {
				if (civildao.droits(civildao.getIdco())!="Admin") {
					JOptionPane.showMessageDialog(null, "Vos droits ne vous permettent pas cette action", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						Parent page =(Parent) FXMLLoader.load(getClass().getResource("Droits.fxml"));
						Scene scene4 = new Scene(page);
						Main.window.setScene(scene4);
						Main.window.setTitle("Menu");
						Main.window.setWidth(600);
						Main.window.setHeight(300);
					} catch (IOException m) {
						// TODO Auto-generated catch block
						m.printStackTrace();
					}
				}
			}
			
			@FXML
			private Button btn_mdp;
			@FXML
			public void handlebtnmdp(ActionEvent e) {
				try {
					Parent page =(Parent) FXMLLoader.load(getClass().getResource("Cgtmdp.fxml"));
					Scene scene4 = new Scene(page);
					Main.window.setScene(scene4);
					Main.window.setTitle("Changement mot de passe");
					Main.window.setWidth(350);
					Main.window.setHeight(350);
				} catch (IOException m) {
					// TODO Auto-generated catch block
					m.printStackTrace();
				}
			}
			public void refreshtable() {
				data.clear();
				data = FXCollections.observableArrayList(userController.findAllCivil());
				table.setItems(data);
			}
			@FXML 
			private Button btnsh;
			@FXML
			private Button btnsv;
			@FXML
			public void btnshh(ActionEvent e) {
				if (civildao.droits(civildao.getIdco())=="Association") {
					JOptionPane.showMessageDialog(null, "Vos droits ne vous permettent pas cette action", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						Parent page =(Parent) FXMLLoader.load(getClass().getResource("SuperHeros.fxml"));
						Scene scene4 = new Scene(page);
						Main.window.setScene(scene4);
						Main.window.setTitle("Super héros");
						Main.window.setWidth(900);
						Main.window.setHeight(650);
					} catch (IOException m) {
						// TODO Auto-generated catch block
						m.printStackTrace();
					}
				}
			}
			@FXML
			public void btnsvh(ActionEvent e) {
				if (civildao.droits(civildao.getIdco())=="Association") {
					JOptionPane.showMessageDialog(null, "Vos droits ne vous permettent pas cette action", "Erreur", JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try {
						Parent page =(Parent) FXMLLoader.load(getClass().getResource("SuperVilains.fxml"));
						Scene scene4 = new Scene(page);
						Main.window.setScene(scene4);
						Main.window.setTitle("Super vilains");
						Main.window.setWidth(900);
						Main.window.setHeight(650);
					} catch (IOException m) {
						// TODO Auto-generated catch block
						m.printStackTrace();
					}
				}
			}
}
			

