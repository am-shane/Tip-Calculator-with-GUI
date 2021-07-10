//use property listeners to perform the calculations whenever the
//user modifies the bill amount or changes the custom tip percentage. Also
//uses a property binding to update the Label that displays the tip
//percentage.

package application;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class TipCalculatorController {
	// formatters for currency and percentages
	private static final NumberFormat currency = NumberFormat.getCurrencyInstance();
	private static final NumberFormat percent = NumberFormat.getPercentInstance();
	
	private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default
	
	// GUI controls defined in FXML and used by the controller's code
	@FXML
	private TextField amountTextField;
	
	@FXML
	private Label tipPercentageLabel;
	
	@FXML
	private Slider tipPercentageSlider;
	
	@FXML
	private TextField tipTextField;
	
	@FXML
	private TextField totalTextField;
	
	//calculates all values
	public void calculateEmptyFields() {
		try {
			BigDecimal amount = new BigDecimal(amountTextField.getText()); //find the bill amount

			BigDecimal tip = amount.multiply(tipPercentage); //calculate the tip
			BigDecimal total = amount.add(tip); //add the tip to the total
		
			//set the textfields
			tipTextField.setText(currency.format(tip));
			totalTextField.setText(currency.format(total));
		}
		
		catch (NumberFormatException ex) {
			//reprompt if exception is caught
			amountTextField.setText("enter amount");
			amountTextField.selectAll();
			amountTextField.requestFocus();
		}
		
	}
	
	// called by FXMLLoader to initialize the controller
	public void initialize() {
		// 0-4 rounds down, 5-9 rounds up
		currency.setRoundingMode(RoundingMode.HALF_UP);
		// listener for changes to tipPercentageSlider's value
		tipPercentageSlider.valueProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number oldValue, Number newValue) {
				tipPercentage = BigDecimal.valueOf(newValue.intValue() / 100.0); //if the slider changes find the new tip %
				tipPercentageLabel.setText(percent.format(tipPercentage)); //set the textfield
				calculateEmptyFields(); //calculate and set the empty fields
			}
		} );
		
		amountTextField.textProperty().addListener(new ChangeListener<String>() {
			public void changed(ObservableValue<? extends String> ov, String oldValue, String newValue) { //if the ammount field changes
				calculateEmptyFields(); //calculate and set the empty fields
			}
		} );
		
	}
}
