package ui;

import controller.UserController;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Callback;

public class DemoTableView extends Application {

	private UserController userController = new UserController();
	private TableView<FxPerson> table = new TableView<FxPerson>();
	private final ObservableList<FxPerson> data = FXCollections.observableArrayList(
			new FxPerson("Jacob", "Smith", "jacob.smith@example.com"),
			new FxPerson("Isabella", "Johnson", "isabella.johnson@example.com"),
			new FxPerson("Ethan", "Williams", "ethan.williams@example.com"),
			new FxPerson("Emma", "Jones", "emma.jones@example.com"),
			new FxPerson("Michael", "Brown", "michael.brown@example.com"));
	final HBox hb = new HBox();

	public static void main(String[] args) {
		launch(args);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void start(Stage stage) {
		Scene scene = new Scene(new Group());
		stage.setTitle("Users");
		stage.setWidth(450);
		stage.setHeight(550);

		final Label label = new Label("Address Book");
		label.setFont(new Font("Arial", 20));

		table.setEditable(true);
		Callback<TableColumn<FxPerson, String>, TableCell<FxPerson, String>> cellFactory = new Callback<TableColumn<FxPerson, String>, TableCell<FxPerson, String>>() {
			public TableCell<FxPerson, String> call(TableColumn<FxPerson, String> p) {
				return new PersonCell();
			}
		};

		TableColumn<FxPerson, String> firstNameCol = createColumn(cellFactory, "firstName", "First Name",
				new EventHandler<CellEditEvent<FxPerson, String>>() {
					@Override
					public void handle(CellEditEvent<FxPerson, String> t) {
						FxPerson fxPerson = (FxPerson) t.getTableView().getItems().get(t.getTablePosition().getRow());
						fxPerson.setFirstName(t.getNewValue());
						userController.onUpdate(fxPerson.getPerson());
					}
				});

		TableColumn<FxPerson, String> lastNameCol = createColumn(cellFactory, "lastName", "Last Name",
				new EventHandler<CellEditEvent<FxPerson, String>>() {
					@Override
					public void handle(CellEditEvent<FxPerson, String> t) {
						FxPerson fxPerson = (FxPerson) t.getTableView().getItems().get(t.getTablePosition().getRow());
						fxPerson.setLastName(t.getNewValue());
						userController.onUpdate(fxPerson.getPerson());
					}
				});

		TableColumn<FxPerson, String> emailCol = createColumn(cellFactory, "email", "Email",
				new EventHandler<CellEditEvent<FxPerson, String>>() {
					@Override
					public void handle(CellEditEvent<FxPerson, String> t) {
						FxPerson fxPerson = (FxPerson) t.getTableView().getItems().get(t.getTablePosition().getRow());
						fxPerson.setEmail(t.getNewValue());
						userController.onUpdate(fxPerson.getPerson());
					}
				});
		table.setItems(data);
		table.getColumns().addAll(firstNameCol, lastNameCol, emailCol);

		final TextField addFirstName = new TextField();
		addFirstName.setPromptText("First Name");
		addFirstName.setMaxWidth(firstNameCol.getPrefWidth());
		final TextField addLastName = new TextField();
		addLastName.setMaxWidth(lastNameCol.getPrefWidth());
		addLastName.setPromptText("Last Name");
		final TextField addEmail = new TextField();
		addEmail.setMaxWidth(emailCol.getPrefWidth());
		addEmail.setPromptText("Email");

		final Button addButton = new Button("Add");
		addButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent e) {
				if (!addFirstName.getText().equals("") || !addLastName.getText().equals("")
						|| !addEmail.getText().equals("")) {
					FxPerson fxPerson = new FxPerson(addFirstName.getText(), addLastName.getText(), addEmail.getText());
					data.add(fxPerson);
					addFirstName.clear();
					addLastName.clear();
					addEmail.clear();
					userController.onInsert(fxPerson.getPerson());
				}
				else {
					System.out.println("ajout impossible (aucune info).");
				}
			}
		});

		hb.getChildren().addAll(addFirstName, addLastName, addEmail, addButton);
		hb.setSpacing(3);

		final VBox vbox = new VBox();
		vbox.setSpacing(5);
		vbox.setPadding(new Insets(10, 0, 0, 10));
		vbox.getChildren().addAll(label, table, hb);

		((Group) scene.getRoot()).getChildren().addAll(vbox);

		stage.setScene(scene);
		stage.show();
	}

	public TableColumn<FxPerson, String> createColumn(  Callback<TableColumn<FxPerson, String>, TableCell<FxPerson, String>> cellFactory,
														String colName,
														String colLabel,
														EventHandler<CellEditEvent<FxPerson, String>> handler) {

		TableColumn<FxPerson, String> col = new TableColumn<>(colLabel);
		col.setMinWidth(100);
		col.setCellValueFactory(new PropertyValueFactory<FxPerson, String>(colName));
		col.setCellFactory(cellFactory);
		col.setOnEditCommit(handler);
		return col;
	}
	
}