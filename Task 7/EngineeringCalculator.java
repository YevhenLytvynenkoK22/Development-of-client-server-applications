import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class EngineeringCalculator extends Application {
    
    private TextField display;
    private Label memoryIndicator;
    private String currentValue = "0";
    private String operator = "";
    private double firstOperand = 0;
    private double memory = 0;
    private boolean startNewNumber = true;
    private boolean invMode = false;
    private String angleMode = "DEG";
    
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        
        MenuBar menuBar = createMenuBar();
        root.setTop(menuBar);
        
        VBox mainContainer = new VBox(5);
        mainContainer.setPadding(new Insets(5));
        mainContainer.setStyle("-fx-background-color: #F0F0F0;");
        
        VBox displayPanel = createDisplayPanel();
        GridPane buttonPanel = createButtonPanel();
        
        mainContainer.getChildren().addAll(displayPanel, buttonPanel);
        root.setCenter(mainContainer);
        
        Scene scene = new Scene(root, 420, 540);
        primaryStage.setTitle("Калькулятор");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    
    private MenuBar createMenuBar() {
        MenuBar menuBar = new MenuBar();
        
        Menu viewMenu = new Menu("Вид");
        
        RadioMenuItem standardItem = new RadioMenuItem("Звичайний");
        RadioMenuItem scientificItem = new RadioMenuItem("Інженерний");
        RadioMenuItem programmerItem = new RadioMenuItem("Програміст");
        RadioMenuItem statisticsItem = new RadioMenuItem("Статистика");
        
        scientificItem.setSelected(true);
        
        ToggleGroup viewGroup = new ToggleGroup();
        standardItem.setToggleGroup(viewGroup);
        scientificItem.setToggleGroup(viewGroup);
        programmerItem.setToggleGroup(viewGroup);
        statisticsItem.setToggleGroup(viewGroup);
        
        SeparatorMenuItem separator1 = new SeparatorMenuItem();
        CheckMenuItem digitGroupingItem = new CheckMenuItem("Групування розрядів");
        SeparatorMenuItem separator2 = new SeparatorMenuItem();
        
        Menu historyMenu = new Menu("Журнал");
        MenuItem viewHistoryItem = new MenuItem("Переглянути журнал");
        historyMenu.getItems().add(viewHistoryItem);
        
        viewMenu.getItems().addAll(
            standardItem, scientificItem, programmerItem, statisticsItem,
            separator1, digitGroupingItem, separator2, historyMenu
        );
        
        Menu editMenu = new Menu("Правка");
        MenuItem copyItem = new MenuItem("Копіювати\tCtrl+C");
        MenuItem pasteItem = new MenuItem("Вставити\tCtrl+V");
        
        copyItem.setOnAction(e -> {
            javafx.scene.input.Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
            javafx.scene.input.ClipboardContent content = new javafx.scene.input.ClipboardContent();
            content.putString(display.getText());
            clipboard.setContent(content);
        });
        
        pasteItem.setOnAction(e -> {
            javafx.scene.input.Clipboard clipboard = javafx.scene.input.Clipboard.getSystemClipboard();
            if (clipboard.hasString()) {
                try {
                    String text = clipboard.getString();
                    double value = Double.parseDouble(text);
                    currentValue = text;
                    display.setText(currentValue);
                } catch (NumberFormatException ex) {
                }
            }
        });
        
        editMenu.getItems().addAll(copyItem, pasteItem);
        
        Menu helpMenu = new Menu("Довідка");
        MenuItem viewHelpItem = new MenuItem("Перегляд довідки");
        SeparatorMenuItem separator3 = new SeparatorMenuItem();
        MenuItem aboutItem = new MenuItem("Про калькулятор");
        helpMenu.getItems().addAll(viewHelpItem, separator3, aboutItem);
        
        menuBar.getMenus().addAll(viewMenu, editMenu, helpMenu);
        return menuBar;
    }
    
    private VBox createDisplayPanel() {
        VBox panel = new VBox(2);
        panel.setPadding(new Insets(5, 5, 10, 5));
        
        memoryIndicator = new Label("");
        memoryIndicator.setFont(Font.font("Arial", 10));
        memoryIndicator.setPrefHeight(15);
        
        display = new TextField("0");
        display.setEditable(false);
        display.setAlignment(Pos.CENTER_RIGHT);
        display.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        display.setPrefHeight(40);
        display.setStyle("-fx-background-color: white; -fx-border-color: #A0A0A0; -fx-border-width: 1;");
        
        panel.getChildren().addAll(memoryIndicator, display);
        return panel;
    }
    
    private GridPane createButtonPanel() {
        GridPane grid = new GridPane();
        grid.setHgap(3);
        grid.setVgap(3);
        grid.setPadding(new Insets(0, 5, 5, 5));
        
        HBox radioBox = new HBox(15);
        radioBox.setPadding(new Insets(5));
        ToggleGroup angleGroup = new ToggleGroup();
        
        CheckBox invCheckBox = new CheckBox("Inv");
        RadioButton degRadio = new RadioButton("Град");
        RadioButton radRadio = new RadioButton("Рад");
        RadioButton gradRadio = new RadioButton("Грд");
        
        invCheckBox.setOnAction(e -> invMode = invCheckBox.isSelected());
        
        degRadio.setToggleGroup(angleGroup);
        radRadio.setToggleGroup(angleGroup);
        gradRadio.setToggleGroup(angleGroup);
        degRadio.setSelected(true);
        
        degRadio.setOnAction(e -> angleMode = "DEG");
        radRadio.setOnAction(e -> angleMode = "RAD");
        gradRadio.setOnAction(e -> angleMode = "GRAD");
        
        radioBox.getChildren().addAll(invCheckBox, degRadio, radRadio, gradRadio);
        grid.add(radioBox, 0, 0, 6, 1);
        
        String[] row2 = {"MC", "MR", "MS", "M+", "M-"};
        for (int i = 0; i < row2.length; i++) {
            grid.add(createButton(row2[i], 50, 25), i, 1);
        }
        grid.add(createButton("←", 70, 25), 5, 1);
        
        String[] row3 = {"sinh", "sin", "x²", "n!", "("};
        for (int i = 0; i < row3.length; i++) {
            grid.add(createButton(row3[i], 50, 25), i, 2);
        }
        grid.add(createButton("CE", 70, 25), 5, 2);
        
        String[] row4 = {"cosh", "cos", "x³", "1/x", ")"};
        for (int i = 0; i < row4.length; i++) {
            grid.add(createButton(row4[i], 50, 25), i, 3);
        }
        grid.add(createButton("C", 70, 25), 5, 3);
        
        String[] row5 = {"tanh", "tan", "xʸ", "ln", "±"};
        for (int i = 0; i < row5.length; i++) {
            grid.add(createButton(row5[i], 50, 25), i, 4);
        }
        grid.add(createButton("÷", 70, 25), 5, 4);
        
        String[] row6 = {"log₁₀x", "log", "10ˣ", "eˣ", "Mod"};
        for (int i = 0; i < row6.length; i++) {
            grid.add(createButton(row6[i], 50, 25), i, 5);
        }
        grid.add(createButton("√", 70, 25), 5, 5);
        
        grid.add(createButton("dms", 50, 25), 0, 6);
        grid.add(createButton("π", 50, 25), 1, 6);
        grid.add(createButton("7", 50, 25), 2, 6);
        grid.add(createButton("8", 50, 25), 3, 6);
        grid.add(createButton("9", 50, 25), 4, 6);
        grid.add(createButton("×", 70, 25), 5, 6);
        
        grid.add(createButton("exp", 50, 25), 0, 7);
        grid.add(createButton("F-E", 50, 25), 1, 7);
        grid.add(createButton("4", 50, 25), 2, 7);
        grid.add(createButton("5", 50, 25), 3, 7);
        grid.add(createButton("6", 50, 25), 4, 7);
        grid.add(createButton("−", 70, 25), 5, 7);
        
        grid.add(createButton("And", 50, 25), 0, 8);
        grid.add(createButton("Or", 50, 25), 1, 8);
        grid.add(createButton("1", 50, 25), 2, 8);
        grid.add(createButton("2", 50, 25), 3, 8);
        grid.add(createButton("3", 50, 25), 4, 8);
        grid.add(createButton("+", 70, 25), 5, 8);
        
        grid.add(createButton("Xor", 50, 25), 0, 9);
        grid.add(createButton("Not", 50, 25), 1, 9);
        grid.add(createButton("0", 105, 25), 2, 9, 2, 1);
        grid.add(createButton(",", 50, 25), 4, 9);
        grid.add(createButton("=", 70, 25), 5, 9);
        
        return grid;
    }
    
    private Button createButton(String text, double width, double height) {
        Button button = new Button(text);
        button.setPrefSize(width, height);
        button.setMinSize(width, height);
        button.setMaxSize(width, height);
        button.setFont(Font.font("Arial", 11));
        
        if (text.matches("[0-9]")) {
            button.setStyle("-fx-background-color: #E0E0FF; -fx-border-color: #808080;");
        } else if (text.matches("[+−×÷=√]")) {
            button.setStyle("-fx-background-color: #FFE0E0; -fx-border-color: #808080;");
        } else {
            button.setStyle("-fx-background-color: #F0F0F0; -fx-border-color: #808080;");
        }
        
        button.setOnAction(e -> handleButtonClick(text));
        return button;
    }
    
    private void handleButtonClick(String text) {
        try {
            if (text.matches("[0-9]")) {
                if (startNewNumber || currentValue.equals("0")) {
                    currentValue = text;
                    startNewNumber = false;
                } else {
                    currentValue += text;
                }
                display.setText(currentValue);
                return;
            }
            
            double value = Double.parseDouble(currentValue);
            
            switch (text) {
                case "C":
                    currentValue = "0";
                    operator = "";
                    firstOperand = 0;
                    startNewNumber = true;
                    display.setText(currentValue);
                    break;
                    
                case "CE":
                    currentValue = "0";
                    startNewNumber = true;
                    display.setText(currentValue);
                    break;
                    
                case "←":
                    if (currentValue.length() > 1) {
                        currentValue = currentValue.substring(0, currentValue.length() - 1);
                    } else {
                        currentValue = "0";
                    }
                    display.setText(currentValue);
                    break;
                    
                case "±":
                    value = -value;
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    break;
                    
                case ",":
                    if (!currentValue.contains(".")) {
                        currentValue += ".";
                        display.setText(currentValue);
                    }
                    break;
                    
                case "MC":
                    memory = 0;
                    memoryIndicator.setText("");
                    break;
                    
                case "MR":
                    currentValue = String.valueOf(memory);
                    display.setText(formatResult(memory));
                    startNewNumber = true;
                    break;
                    
                case "MS":
                    memory = value;
                    memoryIndicator.setText("M");
                    break;
                    
                case "M+":
                    memory += value;
                    memoryIndicator.setText("M");
                    break;
                    
                case "M-":
                    memory -= value;
                    memoryIndicator.setText("M");
                    break;
                    
                case "√":
                    value = Math.sqrt(value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "x²":
                    value = value * value;
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "x³":
                    value = value * value * value;
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "1/x":
                    value = 1.0 / value;
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "n!":
                    value = factorial((int)value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "sin":
                    value = invMode ? Math.asin(value) : Math.sin(toRadians(value));
                    if (invMode) value = fromRadians(value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "cos":
                    value = invMode ? Math.acos(value) : Math.cos(toRadians(value));
                    if (invMode) value = fromRadians(value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "tan":
                    value = invMode ? Math.atan(value) : Math.tan(toRadians(value));
                    if (invMode) value = fromRadians(value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "sinh":
                    value = invMode ? asinh(value) : Math.sinh(value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "cosh":
                    value = invMode ? acosh(value) : Math.cosh(value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "tanh":
                    value = invMode ? atanh(value) : Math.tanh(value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "ln":
                    value = invMode ? Math.exp(value) : Math.log(value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "log":
                case "log₁₀x":
                    value = invMode ? Math.pow(10, value) : Math.log10(value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "10ˣ":
                    value = Math.pow(10, value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "eˣ":
                    value = Math.exp(value);
                    currentValue = String.valueOf(value);
                    display.setText(formatResult(value));
                    startNewNumber = true;
                    break;
                    
                case "π":
                    currentValue = String.valueOf(Math.PI);
                    display.setText(formatResult(Math.PI));
                    startNewNumber = true;
                    break;
                    
                case "+":
                case "−":
                case "×":
                case "÷":
                case "xʸ":
                case "Mod":
                    if (!operator.isEmpty()) {
                        calculate();
                    } else {
                        firstOperand = value;
                    }
                    operator = text;
                    startNewNumber = true;
                    break;
                    
                case "=":
                    calculate();
                    operator = "";
                    startNewNumber = true;
                    break;
            }
        } catch (Exception e) {
            display.setText("Помилка");
            currentValue = "0";
            startNewNumber = true;
        }
    }
    
    private void calculate() {
        double value = Double.parseDouble(currentValue);
        double result = 0;
        
        switch (operator) {
            case "+":
                result = firstOperand + value;
                break;
            case "−":
                result = firstOperand - value;
                break;
            case "×":
                result = firstOperand * value;
                break;
            case "÷":
                result = firstOperand / value;
                break;
            case "xʸ":
                result = Math.pow(firstOperand, value);
                break;
            case "Mod":
                result = firstOperand % value;
                break;
            default:
                return;
        }
        
        currentValue = String.valueOf(result);
        display.setText(formatResult(result));
        firstOperand = result;
    }
    
    private double toRadians(double value) {
        switch (angleMode) {
            case "DEG":
                return Math.toRadians(value);
            case "GRAD":
                return value * Math.PI / 200.0;
            default:
                return value;
        }
    }
    
    private double fromRadians(double value) {
        switch (angleMode) {
            case "DEG":
                return Math.toDegrees(value);
            case "GRAD":
                return value * 200.0 / Math.PI;
            default:
                return value;
        }
    }
    
    private double asinh(double x) {
        return Math.log(x + Math.sqrt(x * x + 1));
    }
    
    private double acosh(double x) {
        return Math.log(x + Math.sqrt(x * x - 1));
    }
    
    private double atanh(double x) {
        return 0.5 * Math.log((1 + x) / (1 - x));
    }
    
    private double factorial(int n) {
        if (n < 0) return Double.NaN;
        if (n == 0 || n == 1) return 1;
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
    
    private String formatResult(double value) {
        if (value == (long) value) {
            return String.format("%d", (long) value);
        } else {
            return String.valueOf(value);
        }
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
