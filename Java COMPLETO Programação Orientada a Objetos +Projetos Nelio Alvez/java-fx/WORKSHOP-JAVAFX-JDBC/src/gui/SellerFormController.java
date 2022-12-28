package gui;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;

import db.DbException;
import gui.listeners.DataChangeListener;
import gui.util.Alerts;
import gui.util.Contraints;
import gui.util.Utils;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import model.entities.Department;
import model.entities.Seller;
import model.exception.ValidationException;
import model.services.DepartmentService;
import model.services.SellerService;

public class SellerFormController implements Initializable {

	private Seller entity;

	private SellerService sellerService;

	private DepartmentService departmentService;

	public void setEntity(Seller e) {
		this.entity = e;
	}

	public void setServices(SellerService sellerService, DepartmentService departmentService) {
		this.sellerService = sellerService;
		this.departmentService = departmentService;
	}

	private List<DataChangeListener> dataChengeListeners = new ArrayList();

	public void subscribeDataChangeListener(DataChangeListener listener) {
		this.dataChengeListeners.add(listener);
	}

	@FXML
	private Button btnSave;
	@FXML
	private Button btnCancel;
	@FXML
	private TextField txtId;
	@FXML
	private TextField txtName;
	@FXML
	private TextField txtEmail;
	@FXML
	private DatePicker txtBrithDate;
	@FXML
	private TextField txtBaseSalary;
	@FXML
	private Label txtErrorName;
	@FXML
	private Label txtErrorEmail;
	@FXML
	private Label txtErrorBirthDate;
	@FXML
	private Label txtErrorBaseSalar;
	@FXML
	private ComboBox<Department> cmbDepartment;

	private ObservableList<Department> obsList;

	@FXML
	public void actionBtnSave(ActionEvent e) {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		if (sellerService == null) {
			throw new IllegalStateException("Service was null");
		}
		try {
			entity = getFormData();
			this.sellerService.saveOrUpdate(entity);
			nofifyDataChangeListener();
			Utils.currentStage(e).close();
		} catch (ValidationException ex) {
			setErrosMessages(ex.getErros());
		} catch (DbException ex) {
			Alerts.showAlert("Error saving obkect", null, ex.getMessage(), AlertType.ERROR);
		}
	}

	private void nofifyDataChangeListener() {
		for (DataChangeListener dataChangeListener : dataChengeListeners) {
			dataChangeListener.onDataChanger();
		}

	}

	private Seller getFormData() {
		ValidationException exception = new ValidationException("Validation Error");

		Seller obj = new Seller();
		obj.setId(Utils.tryParseToInt(txtId.getText()));
		if (txtName.getText() == null || txtName.getText().trim().equals("")) {
			exception.addErro("name", "Fild can1t be empty.");
		} else {
			txtErrorName.setText("");
			obj.setName(txtName.getText());
		}

		if (txtEmail.getText() == null || txtEmail.getText().trim().equals("")) {
			exception.addErro("email", "Fild can1t be empty.");
		} else {
			txtErrorEmail.setText("");
			obj.setEmail(txtEmail.getText());
		}
		if (txtBrithDate.getValue() == null) {
			exception.addErro("birthDate", "Fild can1t be empty.");
		} else {
			txtErrorBirthDate.setText("");
			Instant instant = Instant.from(txtBrithDate.getValue().atStartOfDay(ZoneId.systemDefault()));
			obj.setBirthDate(Date.from(instant));
		}
		if (txtBaseSalary.getText() == null || txtBaseSalary.getText().trim().equals("")) {
			exception.addErro("baseSalar", "Fild can1t be empty.");
		} else {
			txtErrorBaseSalar.setText("");
			obj.setBaseSalary(Utils.tryParseToDouble(txtBaseSalary.getText()));
		}
		obj.setDepartment(cmbDepartment.getValue());
		if (exception.getErros().size() > 0) {
			throw exception;
		}

		return obj;
	}

	@FXML
	public void actionBtnCancel(ActionEvent e) {
		Utils.currentStage(e).close();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeNodes();
	}

	private void initializeNodes() {
		Contraints.setTextFieldInteger(txtId);
		Contraints.setTextFieldMaxLength(txtName, 70);
		Contraints.setTextFieldDouble(txtBaseSalary);
		Contraints.setTextFieldMaxLength(txtEmail, 60);
		Utils.formatDatePicker(txtBrithDate, "dd/MM/yyyy");
		initializeComboBoxDepartment();
	}

	public void updateFormData() {
		if (entity == null) {
			throw new IllegalStateException("Entity was null");
		}
		txtId.setText(String.valueOf(entity.getId()));
		txtName.setText(entity.getName());
		txtEmail.setText(entity.getEmail());
		txtBaseSalary.setText(String.format("%.2f", entity.getBaseSalary()));
		Locale.setDefault(Locale.US);
		if (entity.getBirthDate() != null)
			txtBrithDate.setValue(LocalDate.ofInstant(entity.getBirthDate().toInstant(), ZoneId.systemDefault()));

		if (entity.getDepartment() == null) {
			cmbDepartment.getSelectionModel().selectFirst();
		} else
			cmbDepartment.setValue(entity.getDepartment());
	}

	private void setErrosMessages(Map<String, String> erros) {
		Set<String> fiels = erros.keySet();
		if (fiels.contains("name")) {
			txtErrorName.setText(erros.get("name"));
		}
		if (fiels.contains("email")) {
			txtErrorEmail.setText(erros.get("email"));
		}
		if (fiels.contains("birthDate")) {
			txtErrorBirthDate.setText(erros.get("birthDate"));
		}
		if (fiels.contains("baseSalar")) {
			txtErrorBaseSalar.setText(erros.get("baseSalar"));
		}
	}

	public void loadAssociatedObjects() {
		List<Department> list = departmentService.findAll();
		obsList = FXCollections.observableArrayList(list);
		cmbDepartment.setItems(obsList);
	}

	private void initializeComboBoxDepartment() {
		Callback<ListView<Department>, ListCell<Department>> factory = lv -> new ListCell<Department>() {
			@Override
			protected void updateItem(Department item, boolean empty) {
				super.updateItem(item, empty);
				setText(empty ? "" : item.getName());
			}
		};
		cmbDepartment.setCellFactory(factory);
		cmbDepartment.setButtonCell(factory.call(null));
	}
}
