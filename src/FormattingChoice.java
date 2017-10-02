import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.application.Application;
import javafx.stage.Screen;
import javafx.stage.Stage;
import java.util.Optional;
import javafx.scene.control.ButtonType;
import javafx.geometry.Rectangle2D;

/**
 * The FormattingChoice class utilizes JavaFx and the start() method to prompt the user with dialog boxes and
 * instructions for entering input and choosing which format method, either println or printf,
 * it will be displayed by.
 * This class is a JavaFX application and extends the class Application.
 * This class contains a main() method that is overridden by a start() method because it is a JavaFX application.
 *
 * Users will be prompted with an introductory alert dialog of type INFORMATION,
 * and then asked to choose one of 5 data types which will be represented by an individual button of class
 * ButtonType in a dialog of type CONFIRMATION.
 * Then users will make a text entry in a TextInputDialog box which will store their entry as a string and either
 * keep it that way or parse it into the appropriate data type according to user selection.
 *
 * Created: 01OCT2017
 * email: email
 * @author Sapper
 * @version 1.0
 */
public class FormattingChoice extends Application {
    /**
     * The main() method is not used in this application but is included with a call to launch as a fallback.
     * @param args arguments passed from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * The start() method is the entry point for JavaFX applications, this start() method utilizes various
     * dialog boxes to provide instructions and obtain user input which is then processed according to a series
     * of 'if, else if, else' statements for branching control.
     * This method creates a Stage named primaryStage to display output.
     * This method positions the primaryStage object according to screen dimensions obtained through a Rectangle2D
     * object.
     * start() is public, so it can interact with any part of this or another application.
     * The start() method is abstract and must be overridden
     *
     * @param primaryStage the primary stage for this application, onto which
     *                     the application scene can be set. The primary stage will be embedded
     *                     in the browser if the application was launched as an applet.
     *                     Applications may create other stages, if needed, but they will not
     *                     be primary stages and will not be embedded in the browser.
     * @throws Exception indicates conditions that a reasonable application might want to catch.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Declaring variables to store width and height of the primaryStage object.
        double width = primaryStage.getWidth();
        double height = primaryStage.getHeight();

        //Obtain the dimensions of the screen and then center the primaryStage object on any screen.
        Rectangle2D screenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((screenBounds.getMaxX() - screenBounds.getMinX() - width) / 2);
        primaryStage.setY((screenBounds.getMaxY() - screenBounds.getMinY() - height) / 2);

        //introductory alert dialog
        Alert intro = new Alert(Alert.AlertType.INFORMATION);
        intro.setTitle("Introduction");
        intro.setHeaderText("Hello!");
        intro.setContentText("My name is Format-O-Tron and I am all about formatting options.\n" +
                "I am going to prompt you with a series of windows which will have either button " +
                "or input options.\n" +
                "First you will select a data type, and then you will select a formatting option\n" +
                "Just read each prompt carefully and you won't get lost! Let's begin!");
        intro.showAndWait();

        /*
        * Initializing a CONFIRMATION dialog to display with selectable buttons for the user to choose
        * what data type they want to work with.  The buttons are initialized as ButtonType objects and
        * text is assigned to be displayed within their default formatted spaces.
         */
        Alert dataTypeChoice = new Alert(Alert.AlertType.CONFIRMATION);
        dataTypeChoice.setTitle("Data Type Choice");
        dataTypeChoice.setHeaderText("The buttons below display different data type options.\n" +
                "1) Selecting the String data type will allow you to enter any phrase whether it is\n" +
                "letters, words, sentences, numbers, characters, or any combination of such.\n" +
                "2) Selecting the Character data type will allow you to enter any letter, in upper or lower case.\n" +
                "3) Selecting the Integer data type will allow you to enter any whole number less than 2^31-1\n" +
                "4) Selecting the Float data type will allow you to enter a decimal number less than 3.4*10^38-1\n" +
                "5) Selecting the Exponent data type will allow you to enter float(see above) that will be\n" +
                "formatted to scientific notation if the printf method is chosen later.");
        dataTypeChoice.setContentText("Select your preferred data type to continue on to the next " +
                "phase of formatting.");
        ButtonType stringType = new ButtonType("String");
        ButtonType charType = new ButtonType("Character");
        ButtonType intType = new ButtonType("Integer");
        ButtonType floatType = new ButtonType("Float");
        ButtonType expType = new ButtonType("Exponent");
        dataTypeChoice.getButtonTypes().setAll(stringType, charType, intType,
                floatType, expType);
        Optional<ButtonType> dataTypeResult = dataTypeChoice.showAndWait();


        /*
        * Begin a series of branch control statements based upon the user selection from the CONFIRMATION dialog
        * box.  The == boolean operator is used to determine which selection was made and to determine the
        * resulting action.
        * Each if and else if statement contains a nested if else statement which will print the user input
        * according to format selection.
         */
        if (dataTypeResult.get() == stringType) {
            TextInputDialog choseString = new TextInputDialog("enter whatever");
            choseString.setTitle("String Entry");
            choseString.setHeaderText("You chose to make an entry of type String!\n" +
                    "You can enter any combination of letters, numbers, and special characters you want\n" +
                    "because it will automatically be stored in memory as a string.");
            choseString.setContentText("please enter a string(sentence, characters, numbers, symbols, etc..:");
            Optional<String> choseStringEntry = choseString.showAndWait();
            String choseStringEntryDisplay = choseStringEntry.get();

            //The CONFIRMATION dialog to select output format
            Alert formatChoice = new Alert(Alert.AlertType.CONFIRMATION);
            formatChoice.setTitle("Format selection");
            formatChoice.setHeaderText("Now choose which method you want to use to output!\n" +
                    "1) Println means \'Print Line\' and selecting it will print your entry onto the screen\n" +
                    "just as you entered it." +
                    "2) Printf means \'Print Format\' and selecting it will print your entry onto the screen\n" +
                    "according to specific formatting instructions written into my code.\n" +
                    "NOTE: The output will appear the same to you for either option EXCEPT in the case of choosing\n" +
                    "the Exponent data type");
            formatChoice.setContentText("Choose your method");
            ButtonType printLine = new ButtonType("Println");
            ButtonType printFormat = new ButtonType("Printf");
            formatChoice.getButtonTypes().setAll(printLine, printFormat);
            Optional<ButtonType> formatResult = formatChoice.showAndWait();

            //nested if else to output format selection
            if (formatResult.get() == printLine) {
                System.out.println("Your entry with println: " + choseStringEntryDisplay);
            } else {
                System.out.printf("Your entry with printf: %s\n", choseStringEntryDisplay);
            }

        } else if (dataTypeResult.get() == charType) {
            TextInputDialog choseCharacter = new TextInputDialog("enter char");
            choseCharacter.setTitle("Character Entry");
            choseCharacter.setHeaderText("You chose to enter a character!\n" +
                    "You can enter any letter in the alphabet, in either upper or lower case,\n" +
                    "but only one so choose wisely!");
            choseCharacter.setContentText("Please enter one character in either upper or lower case:");
            Optional<String> choseCharacterEntry = choseCharacter.showAndWait();
            String choseCharacterEntryDisplay = choseCharacterEntry.get();
            char[] userChar = choseCharacterEntryDisplay.toCharArray();

            Alert formatChoice = new Alert(Alert.AlertType.CONFIRMATION);
            formatChoice.setTitle("Format selection");
            formatChoice.setHeaderText("Now choose which method you want to use to output!\n" +
                    "1) Println means \'Print Line\' and selecting it will print your entry onto the screen\n" +
                    "just as you entered it." +
                    "2) Printf means \'Print Format\' and selecting it will print your entry onto the screen\n" +
                    "according to specific formatting instructions written into my code.\n" +
                    "NOTE: The output will appear the same to you for either option EXCEPT in the case of choosing\n" +
                    "the Exponent data type");
            formatChoice.setContentText("Choose your method");
            ButtonType printLine = new ButtonType("Println");
            ButtonType printFormat = new ButtonType("Printf");
            formatChoice.getButtonTypes().setAll(printLine, printFormat);
            Optional<ButtonType> formatResult = formatChoice.showAndWait();

            if (formatResult.get() == printLine) {
                System.out.println("Your entry with println: " + userChar[0]);
            } else {
                System.out.printf("Your entry with printf:%2c\n", userChar[0]);
            }

        } else if (dataTypeResult.get() == intType) {
            TextInputDialog choseInt = new TextInputDialog("enter int");
            choseInt.setTitle("Integer Entry");
            choseInt.setHeaderText("You chose to enter an Integer!\n" +
                    "You can enter a whole number up to 2^32-1\n" +
                    "but no decimals!");
            choseInt.setContentText("Please enter an integer:");
            Optional<String> choseIntEntry = choseInt.showAndWait();
            String choseIntEntryDisplay = choseIntEntry.get();
            int userInt = Integer.parseInt(choseIntEntryDisplay);

            Alert formatChoice = new Alert(Alert.AlertType.CONFIRMATION);
            formatChoice.setTitle("Format selection");
            formatChoice.setHeaderText("Now choose which method you want to use to output!\n" +
                    "1) Println means \'Print Line\' and selecting it will print your entry onto the screen\n" +
                    "just as you entered it." +
                    "2) Printf means \'Print Format\' and selecting it will print your entry onto the screen\n" +
                    "according to specific formatting instructions written into my code.\n" +
                    "NOTE: The output will appear the same to you for either option EXCEPT in the case of choosing\n" +
                    "the Exponent data type");
            formatChoice.setContentText("Choose your method");
            ButtonType printLine = new ButtonType("Println");
            ButtonType printFormat = new ButtonType("Printf");
            formatChoice.getButtonTypes().setAll(printLine, printFormat);
            Optional<ButtonType> formatResult = formatChoice.showAndWait();

            if (formatResult.get() == printLine) {
                System.out.println("Your entry with println: " + userInt);
            } else {
                System.out.printf("Your entry with printf:%2d\n", userInt);
            }
        } else if (dataTypeResult.get() == floatType) {
            TextInputDialog choseFloat = new TextInputDialog("enter float");
            choseFloat.setTitle("Float Entry");
            choseFloat.setHeaderText("You chose to enter a floating point number!\n" +
                    "You can enter a whole number up to 2^32-1\n" +
                    "or a decimal number up to 3.4*10^38-1");
            choseFloat.setContentText("Please enter a whole number, or\n" +
                    "a floating point number as a whole number followed by a decimal \'.\' followed by\n" +
                    "another integer:");
            Optional<String> choseFloatEntry = choseFloat.showAndWait();
            String choseFloatEntryResult = choseFloatEntry.get();
            float userFloat = Float.parseFloat(choseFloatEntryResult);

            Alert formatChoice = new Alert(Alert.AlertType.CONFIRMATION);
            formatChoice.setTitle("Format selection");
            formatChoice.setHeaderText("Now choose which method you want to use to output!\n" +
                    "1) Println means \'Print Line\' and selecting it will print your entry onto the screen\n" +
                    "just as you entered it." +
                    "2) Printf means \'Print Format\' and selecting it will print your entry onto the screen\n" +
                    "according to specific formatting instructions written into my code.\n" +
                    "NOTE: The output will appear the same to you for either option EXCEPT in the case of choosing\n" +
                    "the Exponent data type");
            formatChoice.setContentText("Choose your method");
            ButtonType printLine = new ButtonType("Println");
            ButtonType printFormat = new ButtonType("Printf");
            formatChoice.getButtonTypes().setAll(printLine, printFormat);
            Optional<ButtonType> formatResult = formatChoice.showAndWait();

            if (formatResult.get() == printLine) {
                System.out.println("Your entry with println: " + userFloat);
            } else {
                System.out.printf("Your entry with printf:%1.2f\n", userFloat);
            }
        } else {
            TextInputDialog choseExpBase = new TextInputDialog("enter..exp");
            choseExpBase.setTitle("Exponential Selection");
            choseExpBase.setHeaderText("You chose to enter a number and have it displayed in scientific notation!\n" +
                    "You can enter a whole number up to 2^32-1\n" +
                    "or a decimal number up to 3.4*10^38-1\n" +
                    "Either way, your number will be displayed as \'x.xxe+xx\' with one digit followed by a decimal and\n" +
                    "three more digits followed by \'e\' which means \'times 10 to the\' followed by the power 10 will" +
                    "be lifted to.");
            choseExpBase.setContentText("Please enter a number, it can be whole or decimal:");
            Optional<String> choseExpBaseEntry = choseExpBase.showAndWait();
            String choseExpBaseEntryResult = choseExpBaseEntry.get();
            double userExponent = Double.parseDouble(choseExpBaseEntryResult);

            Alert formatChoice = new Alert(Alert.AlertType.CONFIRMATION);
            formatChoice.setTitle("Format selection");
            formatChoice.setHeaderText("Now choose which method you want to use to output!\n" +
                    "1) Println means \'Print Line\' and selecting it will print your entry onto the screen\n" +
                    "just as you entered it." +
                    "2) Printf means \'Print Format\' and selecting it will print your entry onto the screen\n" +
                    "according to specific formatting instructions written into my code.\n" +
                    "NOTE: The output will appear the same to you for either option EXCEPT in the case of choosing\n" +
                    "the Exponent data type.\n" +
                    "If you want to see your number printed in scientific notation, select printf, println will\n" +
                    "just display a failure message.");
            formatChoice.setContentText("Choose your method");
            ButtonType printLine = new ButtonType("Println");
            ButtonType printFormat = new ButtonType("Printf");
            formatChoice.getButtonTypes().setAll(printLine, printFormat);
            Optional<ButtonType> formatResult = formatChoice.showAndWait();

            if (formatResult.get() == printLine) {
                System.out.println("Your entry with println: " + userExponent + ", unfortunately I can't format " +
                        "this correctly with the println() method");
            } else {
                System.out.printf("Your entry with printf:%10.3e\n", userExponent);
            }
        }
        //the preferred way of exiting a JavaFX application according to Oracle Documentation guidance.
        Platform.exit();
    }

}


/*
        Alert formatChoice = new Alert(Alert.AlertType.CONFIRMATION);
        formatChoice.setTitle("title..");
        formatChoice.setHeaderText("header..");
        formatChoice.setContentText("Choose your option");
        ButtonType printLine = new ButtonType("Println");
        ButtonType printFormat = new ButtonType("Printf");
        formatChoice.getButtonTypes().setAll(printLine, printFormat);
        Optional<ButtonType> formatResult = formatChoice.showAndWait();

        Alert chosePrintln = new Alert(Alert.AlertType.INFORMATION);
        chosePrintln.setContentText("You chose println");

        Alert chosePrintf = new Alert(Alert.AlertType.INFORMATION);
        chosePrintf.setContentText("You chose printf");



		if ((formatResult.get() == printLine) && (dataTypeResult.get() == stringType)) {
			chosePrintln.showAndWait();
			System.out.println(choseStringEntryDisplay);
		} else {
			chosePrintf.showAndWait();
		}

        //System.out.println(testing);
        */


        /*
        TextInputDialog enter = new TextInputDialog("enter..");
        enter.setTitle("title");
        enter.setHeaderText("header");
        enter.setContentText("please enter....");
        Optional<String> entry = enter.showAndWait();
        String userEntry = ("\'" + entry.get() + "\'");

        Alert youEntered = new Alert(Alert.AlertType.INFORMATION);
        youEntered.setContentText("You entered " + userEntry);

        if (entry.isPresent()) {
            youEntered.showAndWait();
        } else {
            System.out.println("you suck");
        }

        //String stringEntry = entry.get();
        //	byte byteEntry = Byte.parseByte(entry.get());
        //	int intEntry = Integer.parseInt(entry.get());
        //	double doubleEntry = Double.parseDouble(entry.get());
        */


