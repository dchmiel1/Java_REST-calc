package controllers;

import java.util.function.Function;

import expression.Expression;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Model;
import rest.RestClient;

/**
 * Kontroler odpowiedzialny za odpowiednie reakcje i kierowanie do innych
 * metod/klas po naci�nieciu danego przycisku
 * 
 * @author Daniel_Chmielewiec
 *
 */
public class Controller {

	private final int MAX_LENGTH = 11;
	private final static long MAX_VALUE = 9999999;
	private boolean dotFlag = false;
	private String op = "";
	private String num1 = "";
	private boolean newNumberFlag = false;
	private boolean doubleOpFlag = false;
	private RestClient restClient;

	@FXML
	private Label label;

	/**
	 * Metoda inicjalizuj�ca - ustawia pocz�tkow� warto�� "0" na wy�wietlaczu
	 * kalkulatora
	 */
	public void initialize() {
		restClient = new RestClient();
		label.setText("0");
	}

	/**
	 * Metoda odpowiedzialna za dzia�anie po wci�nieciu przycisk�w liczbowych i '.'
	 * 
	 * @param e Zawiera informacje o nasta�ym "wydarzeniu" (wci�nieciu przycisku)
	 */
	@FXML
	public void numButton(ActionEvent e) {
		String buttonText = ((Button) e.getSource()).getText();

		if (newNumberFlag == true) {
			newNumberFlag = false;
			label.setText("0");
		}

		String value = label.getText();

		if (buttonText.equals("."))
			if (dotFlag == true)
				return;
			else
				dotFlag = true;

		if (value.length() == MAX_LENGTH)
			return;
		else if (value.equals("0") && !buttonText.equals("."))
			value = buttonText;
		else {
			value += buttonText;
		}
		doubleOpFlag = false;
		label.setText(value);
	}

	/**
	 * Metoda odpowiedzialna za dzia�anie po wci�nieciu przycisk�w operator�w
	 * dwuargumentowych
	 * 
	 * @param e Zawiera informacje o nasta�ym "wydarzeniu" (wci�nieciu przycisku)
	 * @exception IllegalArgumentException Zg�aszany gdy wynik przekroczy maksymaln�
	 *                                     dozwolon� warto��
	 */
	@FXML
	public void opButton2Arg(ActionEvent e) {
		Function<ActionEvent, String> getOp = x -> ((Button) x.getSource()).getText();

		if (op.equals("") && getOp.apply(e).equals("=") || doubleOpFlag) {
			if (doubleOpFlag && !getOp.apply(e).equals("="))
				op = getOp.apply(e);
			return;
		} else if (op.equals("")) {
			op = getOp.apply(e);
			num1 = label.getText();
			newNumberFlag = true;
			doubleOpFlag = true;
			dotFlag = false;
		} else {
			try {
				double result = restClient.goRest(new Expression(num1, op, label.getText()));
				//double result = Model.calculate(num1, label.getText(), op);
				if (result > MAX_VALUE)
					throw new IllegalArgumentException("Too big number!");
				num1 = Model.makeItSuitable(Double.toString(result));
				newNumberFlag = true;
				dotFlag = false;
				doubleOpFlag = true;
				label.setText(Model.makeItSuitable(Double.toString(result)));
				if (getOp.apply(e).equals("="))
					op = "";
				else
					op = getOp.apply(e);
			} catch (IllegalArgumentException exception) {
				label.setText("Error!");
				errorWindow(exception.getMessage());
				cButton();
			}
		}
	}

	/**
	 * Metoda odpowiedzialna za dzia�anie po wci�nieciu przycisk�w operator�w
	 * jednoargumentowych
	 * 
	 * @param e Zawiera informacje o nasta�ym "wydarzeniu" (wci�nieciu przycisku)
	 */
	@FXML
	public void opButton1Arg(ActionEvent e) {
		Function<ActionEvent, String> getId = x -> ((Button) x.getSource()).getId();
		
		if (label.getText().equals("") || label.getText().equals("Error!"))
			return;
		else {
			op = getId.apply(e);
			try {
				double result = restClient.goRest(new Expression(label.getText(), op));
				//double result = Model.calculate(label.getText(), op);
				num1 = Model.makeItSuitable(Double.toString(result));
				label.setText(num1);
				newNumberFlag = true;
				dotFlag = false;
				op = "";
			} catch (IllegalArgumentException exception) {
				label.setText("Error!");
				errorWindow(exception.getMessage());
				cButton();
			}
		}
	}

	/**
	 * Metoda odpowiadaj�ca za wci�niecia przycisku "C"
	 */
	@FXML
	public void cButton() {
		label.setText("0");
		dotFlag = false;
		num1 = "0";
		op = "";
		newNumberFlag = true;
	}

	/**
	 * Metoda wy�wietlaj�ca alert "Error"
	 * 
	 * @param errorMsg Wiadomo�� przekazana po wyst�pieniu b�edu wy�wietlana
	 *                 nast�pnie w alercie
	 */
	public static void errorWindow(String errorMsg) {
		Alert errorAlert = new Alert(Alert.AlertType.ERROR);
		errorAlert.setTitle("Error");
		errorAlert.setHeaderText(errorMsg);
		errorAlert.setContentText("Press OK to continue");
		errorAlert.showAndWait();
	}

}
