package dustin.examples;  
      
    import java.math.BigDecimal;  
    import java.util.List;  
    import javafx.application.Application;  
    import javafx.event.ActionEvent;  
    import javafx.event.EventHandler;  
    import javafx.geometry.Pos;  
    import javafx.scene.Group;  
    import javafx.scene.Scene;  
    import javafx.scene.control.Button;  
    import javafx.scene.control.Label;  
    import javafx.scene.control.TextField;  
    import javafx.scene.control.TextFieldBuilder;  
    import javafx.scene.image.Image;  
    import javafx.scene.image.ImageView;  
    import javafx.scene.layout.HBox;  
    import javafx.scene.layout.VBox;  
    import javafx.scene.paint.Color;  
    import javafx.stage.Stage;  
      
    /** 
     * JavaFX-based application for solving quadratic equations. 
     *  
     * @author Dustin 
     */  
    public class QuadraticCalculator extends Application  
    {  
       /** Coefficient A used in quadratic formula. */  
       private TextField coefficientA =  
          TextFieldBuilder.create().promptText("Enter Coefficient A").build();  
      
       /** Coeffecient B used in quadratic forumal. */  
       private TextField coefficientB =  
          TextFieldBuilder.create().promptText("Enter Coefficient B").build();  
      
       /** Coeffecient C (constant) used in quadratic formula. */  
       private TextField coefficientC =  
          TextFieldBuilder.create().promptText("Enter Coefficient C").build();  
      
       /** First x-intercept. */  
       private TextField xIntercept1 =  
          TextFieldBuilder.create().disable(true).editable(false).build();  
      
       /** Second x-intercept. */  
       private TextField xIntercept2 =  
          TextFieldBuilder.create().disable(true).editable(false).build();  
      
       /** Axis of symmetry. */  
       private TextField symmetryAxis =  
          TextFieldBuilder.create().disable(true).editable(false).build();  
      
       /** 
        * Extract Image with provided name. 
        *  
        * @param imageName Name of image to be provided. 
        * @return Loaded image. 
        */  
       private Image getImage(final String imageName)  
       {  
          final String jarFileUrl =  
             this.getClass().getProtectionDomain().getCodeSource().getLocation().toString();  
          final String url = "jar:" + jarFileUrl + "!/" + imageName;  
          System.out.println(url);  
          return new Image(url, true);  
       }  
      
       /** 
        * Provide a read-only horizontal box with quadratic equation and quadratic 
        * formula and with a button that can be clicked to calculate solution to 
        * quadration equation with provided coefficients. 
        *  
        * @return Horizontal box with quadratic equation and quadratic formula. 
        */  
       private HBox buildEquationsBox()  
       {  
          final HBox equationsBox = new HBox();  
          equationsBox.setAlignment(Pos.CENTER);  
          equationsBox.setSpacing(50);  
          final Image quadraticEquation = getImage("quadraticEquation-transparent.png");  
          final ImageView equationView = new ImageView(quadraticEquation);  
          equationsBox.getChildren().add(equationView);  
          final Image quadraticFormula = getImage("quadraticFormula-transparent.png");  
          final ImageView formulaView = new ImageView(quadraticFormula);  
          equationsBox.getChildren().add(formulaView);  
          final Button calculateButton = new Button("Calculate");  
          calculateButton.setOnAction(  
             new EventHandler<ActionEvent>()  
             {  
                @Override
				public void handle(ActionEvent t)  
                {  
                   final BigDecimal a = extractBigDecimal(coefficientA.getText());  
                   final BigDecimal b = extractBigDecimal(coefficientB.getText());  
                   final BigDecimal c = extractBigDecimal(coefficientC.getText());  
                   try  
                   {  
                      final List<BigDecimal> intercepts =  
                         SimplisticQuadraticFormula.calculateXIntercepts(a, b, c);  
                      xIntercept1.setText(intercepts.get(0).toPlainString());  
                      xIntercept1.setDisable(false);  
                      if (intercepts.size() > 1)  
                      {  
                         xIntercept2.setText(intercepts.get(1).toEngineeringString());  
                         xIntercept2.setDisable(false);  
                      }  
                      else  
                      {  
                         xIntercept2.setText("-");  
                         xIntercept2.setDisable(true);  
                      }  
                      if (a.compareTo(new BigDecimal("0")) != 0)  
                      {  
                         final BigDecimal axis =  
                            SimplisticQuadraticFormula.calculateAxisOfSymmetry(a, b);  
                         symmetryAxis.setText(axis.toPlainString());  
                         symmetryAxis.setDisable(false);  
                      }  
                      else  
                      {  
                         symmetryAxis.setText("-");  
                         symmetryAxis.setDisable(true);  
                      }  
                   }  
                   catch (NumberFormatException nfe)  
                   {  
                      xIntercept1.setText("-");  
                      xIntercept1.setDisable(true);  
                      xIntercept2.setText("-");  
                      xIntercept2.setDisable(true);  
                      symmetryAxis.setText("-");  
                      symmetryAxis.setDisable(true);  
                   }  
                }  
             });  
          equationsBox.getChildren().add(calculateButton);  
          return equationsBox;  
       }  
      
       /** 
        * Converts provided String to BigDecimal. 
        *  
        * @param possibleNumber String to be converted to an instance of BigDecimal. 
        * @return The BigDecimal corresponding to the provided String or Double.NaN 
        *     if the conversion cannot be performed. 
        */  
       private BigDecimal extractBigDecimal(final String possibleNumber)  
       {  
          BigDecimal extractedNumber;  
          try  
          {  
             extractedNumber = new BigDecimal(possibleNumber);  
          }  
          catch (NumberFormatException nfe)  
          {  
             extractedNumber = null;  
          }  
          return extractedNumber;  
       }  
      
       /** 
        * Provide horizontal box with labels of coefficients and fields to enter 
        * coefficient values. 
        *  
        * @return Horizontal box for entering coefficients. 
        */  
       private HBox buildEntryBox()  
       {  
          final HBox entryBox = new HBox();  
          entryBox.setSpacing(10);  
          final Label aCoeff = new Label("a = ");  
          entryBox.getChildren().add(aCoeff);  
          entryBox.getChildren().add(this.coefficientA);  
          final Label bCoeff = new Label("b = ");  
          entryBox.getChildren().add(bCoeff);  
          entryBox.getChildren().add(this.coefficientB);  
          final Label cCoeff = new Label("c = ");  
          entryBox.getChildren().add(cCoeff);  
          entryBox.getChildren().add(this.coefficientC);  
          return entryBox;  
       }  
      
       /** 
        * Construct the output box with solutions based on quadratic formula. 
        *  
        * @return Output box with solutions of applying quadratic formula given 
        *    provided input coefficients. 
        */  
       private HBox buildOutputBox()  
       {  
          final HBox outputBox = new HBox();  
          outputBox.setSpacing(10);  
          final Label x1 = new Label("x1 = ");  
          outputBox.getChildren().add(x1);  
          outputBox.getChildren().add(this.xIntercept1);  
          final Label x2 = new Label("x2 = ");  
          outputBox.getChildren().add(x2);  
          outputBox.getChildren().add(this.xIntercept2);  
          final Label axis = new Label("axis = ");  
          outputBox.getChildren().add(axis);  
          outputBox.getChildren().add(this.symmetryAxis);  
          return outputBox;  
       }  
      
       /** 
        * Build overall presentation of application. 
        *  
        * @return Vertical box representing input and output of application. 
        */  
       private VBox buildOverallVerticalLayout()  
       {  
          final VBox vbox = new VBox();  
          vbox.setSpacing(25);  
          vbox.getChildren().add(buildEquationsBox());  
          vbox.getChildren().add(buildEntryBox());  
          vbox.getChildren().add(buildOutputBox());  
          vbox.setAlignment(Pos.CENTER);  
          return vbox;  
       }  
      
       /** 
        * Start the JavaFX application for solving quadratic equations. 
        *  
        * @param stage Primary stage. 
        * @throws Exception JavaFX-related exception. 
        */  
       @Override  
       public void start(final Stage stage) throws Exception  
       {  
          final Group groupRoot = new Group();  
          groupRoot.getChildren().add(buildOverallVerticalLayout());  
          final Scene scene = new Scene(groupRoot, 600, 150, Color.LIGHTGRAY);  
          stage.setTitle("Quadratic Formula: JavaFX Style");  
          stage.setScene(scene);  
          stage.show();  
       }  
      
       /** 
        * Main function for running the JavaFX-based quadratic equation solver. 
        *  
        * @param arguments  
        */  
       public static void main(final String[] arguments)  
       {  
          Application.launch(arguments);  
       }  
    }  