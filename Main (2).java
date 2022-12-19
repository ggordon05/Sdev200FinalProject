/*
*Gerald Gordon
*Final Project
*12/17/2022
*/

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Pos;
import javafx.event.EventHandler;
import javafx.event.ActionEvent;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.geometry.Insets;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;



public class Main extends Application { 
  private TextField tfName = new TextField();
    private TextField tfServings = new TextField();
    private TextField tfDate = new TextField();
    private RadioButton rbBreakfast = new RadioButton("Breakfast each $15.00");
    private RadioButton rbLunch = new RadioButton("Lunch each $17.00");
    private RadioButton rbDinner = new RadioButton("Dinner each $20.00");
    private ToggleGroup tgType = new ToggleGroup();
    private Label lbTotal = new Label("$0.00");
    private static double total = 0.00;
    private Button btUpdate = new Button("Update Cost");
    private Button btConfirm = new Button("Confirm Order");
    private Button btReset = new Button("Reset");
    private Stage stageWarn = new Stage();
    private Stage stage2 = new Stage();
    private Staff staffBrek = new Staff("Sandy","Luken","Breakfast");
    private Staff staffLunch = new Staff("Jeff","Saturday","Lunch");
    private Staff staffDin = new Staff("Bob","Burger","Dinner");
    private Prices pricesAll = new Prices(15.00,17.00,20.00);
  
  @Override
  public void start(Stage primaryStage) {

    //// container for all layouts
    VBox vBox = new VBox(10);
    vBox.setPadding(new Insets(10,10,10,10));
    //// info HBox
    HBox hbInfo = new HBox(10);
    hbInfo.setAlignment(Pos.CENTER);
    Text tInfo = new Text("Order Information");
    //// set text in text boxes
    tfName.setPromptText("First and Last Name");
    tfServings.setPromptText("Number of Servings");
    tfDate.setPromptText("XX/XX/XXX");
    tfName.setFocusTraversable(false);
    tfServings.setFocusTraversable(false);
    tfDate.setFocusTraversable(false);
    hbInfo.getChildren().addAll(tInfo,tfName,tfServings,tfDate);

    //// set up type Hbox
    HBox hbType = new HBox(5);
    hbType.setAlignment(Pos.CENTER);
    //// add radios to toggle group
    rbBreakfast.setToggleGroup(tgType);
    rbLunch.setToggleGroup(tgType);
    rbDinner.setToggleGroup(tgType);
    Text tType = new Text("Types");
    hbType.getChildren().addAll(tType,rbBreakfast,rbLunch,rbDinner);

    //// set up buttons and cost
    HBox finish = new HBox(10);
    finish.setAlignment(Pos.CENTER);
    Label lbCost = new Label("Cost");
    //// label total will show total cost
    finish.getChildren().addAll(lbCost,lbTotal,btUpdate,btConfirm,btReset);

    //// register command buttons with handlers
    btUpdate.setOnAction(e -> update()); // call update method

    btConfirm.setOnAction(e -> {
      // prevent order if info is missing
      if(tfName.getText().isEmpty() || tfServings.getText().isEmpty() || tfDate.getText().isEmpty()){
        Label lbWarn = new Label("You must enter Name, Servings, and Date");
        stageWarn.setTitle("ALERT");
        stageWarn.setScene(new Scene(lbWarn,320,60));
        stageWarn.show();
      }
      else if (rbBreakfast.isSelected()) {
        String order = "Here is your order...\n";
        order += update();
        order += "\nYour catering will be on: ";
        order += tfDate.getText() + "\n";
        order += "It will be prepared for " + tfServings.getText() + " people. By our chef " + staffBrek.fname;
        order += "\n Thank you for your order " + tfName.getText() + "!";
        // create text area
        TextArea taOrder = new TextArea();
        taOrder.setText(order);
        // display the text in new Stage
        stage2.setTitle("Your Catering Order");
        stage2.setScene(new Scene(taOrder,350,125));
        stage2.show();
      }
      else if (rbLunch.isSelected()) {
        String order = "Here is your order...\n";
        order += update();
        order += "\nYour catering will be on: ";
        order += tfDate.getText() + "\n";
        order += "It will be prepared for " + tfServings.getText() + " people. By our chef " + staffLunch.fname;
        order += "\n Thank you for your order " + tfName.getText() + "!";
        // create text area
        TextArea taOrder = new TextArea();
        taOrder.setText(order);
        // display the text in new Stage
        stage2.setTitle("Your Catering Order");
        stage2.setScene(new Scene(taOrder,350,125));
        stage2.show();
      }
      else {
        String order = "Here is your order...\n";
        order += update();
        order += "\nYour catering will be on: ";
        order += tfDate.getText() + "\n";
        order += "It will be prepared for " + tfServings.getText() + " people. By our chef " + staffDin.fname;
        order += "\n Thank you for your order " + tfName.getText() + "!";
        // create text area
        TextArea taOrder = new TextArea();
        taOrder.setText(order);
        // display the text in new Stage
        stage2.setTitle("Your Catering Order");
        stage2.setScene(new Scene(taOrder,350,125));
        stage2.show();
      }
    });
    btReset.setOnAction(e -> reset()); // call reset method

    //// Add all Hbox to the Vbox
    vBox.getChildren().addAll(hbInfo,hbType,finish);

    Scene scene = new Scene(vBox,650,300);
    primaryStage.setTitle("Catering Information");
    primaryStage.setScene(scene);
    primaryStage.show();
  
  }

  public String update() {
    total = 0;
    String summary = "";
    if (rbBreakfast.isSelected()){
      total += (pricesAll.brekPrice) * (Integer.parseInt(tfServings.getText()));
      summary += "Total is: $" + total;
  
      lbTotal.setText(String.format("$%.2f",total));
      return summary;
    }
    else if (rbLunch.isSelected()){
      total += (pricesAll.lunchPrice) * (Integer.parseInt(tfServings.getText()));
      summary += "Total is: $" + total;
  
      lbTotal.setText(String.format("$%.2f",total));
      return summary;
    }
    else {
      total += (pricesAll.dinnerPrice) * (Integer.parseInt(tfServings.getText()));
      summary += "Total is: $" + total;
  
      lbTotal.setText(String.format("$%.2f",total));
      return summary;
    }
  }
  public void reset() {
    tfName.clear();
    tfServings.clear();
    tfDate.clear();
    rbBreakfast.setSelected(false);
    rbLunch.setSelected(false);
    rbDinner.setSelected(false);
    lbTotal.setText("$0.00");
    stage2.close();
  }
 
    
  public static void main(String[] args) {
    launch(args);
  }
} 
