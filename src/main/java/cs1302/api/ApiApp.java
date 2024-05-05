package cs1302.api;

import cs1302.api.JokeResponse;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.TilePane;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import java.lang.Runnable;
import java.lang.Thread;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.HttpURLConnection;
import java.net.http.HttpResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpClient;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * REPLACE WITH NON-SHOUTING DESCRIPTION OF YOUR APP.
 */
public class ApiApp extends Application {

    public static final HttpClient HTTP_CLIENT = HttpClient.newBuilder()
        .version(HttpClient.Version.HTTP_2)
        .followRedirects(HttpClient.Redirect.NORMAL)
        .build();

    public static Gson GSON = new GsonBuilder()
        .setPrettyPrinting()
        .create();

    Stage stage;
    Scene scene;
    VBox userUses;
    HBox screenLook;
    HBox userJ;
    HBox hForJoke;
    HBox hForMeme;
    Label typeTheJoke;
    Label nothing;
    Label nothing2;
    Label jokeInfo;
    Label printedJoke;
    Label memeInfo;
    Label exa;
    Label theJo;
    Label theJo2;
    Label yoda;
    TextField userInput;
    Button getJoke;
    Button findMeme;
    ImageView meme;
    Image defaults;
    String query;
    String jsonString;
    JokeResponse jokeResponse;
    YodaResponse yodaResponse;
    YodaContent yodaContent;

    /**
     * Constructs an {@code ApiApp} object. This default (i.e., no argument)
     * constructor is executed in Step 2 of the JavaFX Application Life-Cycle.
     */
    public ApiApp() {
        screenLook = new HBox();
        userJ = new HBox();
        hForJoke = new HBox(150);
        hForMeme = new HBox(150);
        userUses = new VBox(20);
        exa = new Label("   Example Input: java. You can also leave the search bar blank" +
            " for a random joke!");
        nothing = new Label(" ");
        nothing2 = new Label(" ");
        theJo = new Label(" ");
        theJo2 = new Label(" ");
        yoda = new Label(" ");
        typeTheJoke = new Label("   Type the joke!   ");
        jokeInfo = new Label("   Clicking the Get Joke button will return a joke related " +
            "to the input.");
        memeInfo = new Label("   Clicking the Yoda Translate button will return the exact same" +
        " sentence, but in the way that Yoda speaks!");
        userInput = new TextField();
        getJoke = new Button("Get Joke");
        findMeme = new Button("Yoda Translate");
        defaults = new Image("file:resources/readme-banner.png");
        meme = new ImageView(defaults);
    } // ApiApp

    /** {@inheritDoc} */
    @Override
    public void start(Stage stage) {

        this.stage = stage;

        // demonstrate how to load local asset using "file:resources/"
        Image defaults = new Image("file:resources/readme-banner.png");
        ImageView banner = new ImageView(defaults);
        banner.setPreserveRatio(true);
        banner.setFitWidth(640);

        // some labels to display information
        Label notice = new Label("Modify the starter code to suit your needs.");
        memeInfo.setWrapText(true);
        theJo.setFont(new Font("Arial", 20));
        theJo.setWrapText(true);
        yoda.setFont(new Font("Arial", 20));
        yoda.setWrapText(true);
        getJoke.setPrefWidth(200);
        getJoke.setPrefHeight(10);
        findMeme.setPrefWidth(200);
        findMeme.setPrefHeight(10);
        findMeme.setDisable(true);
        meme.setFitWidth(500);
        meme.setFitHeight(500);


        // setup scene
        hForMeme.getChildren().addAll(nothing2, findMeme);
        hForJoke.getChildren().addAll(nothing, getJoke);
        userJ.getChildren().addAll(typeTheJoke, userInput);
        userUses.getChildren().addAll(userJ, jokeInfo, exa, hForJoke, theJo, memeInfo, hForMeme,
            yoda);
        screenLook.getChildren().addAll(userUses, meme);
        scene = new Scene(screenLook);

        // setup stage
        stage.setTitle("ApiApp!");
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> Platform.exit());
        stage.sizeToScene();
        stage.show();


        getJoke.setOnAction(e -> getJoke());
        findMeme.setOnAction(e -> findMeme());
    } // start

    /**
     * This method is conducted when the user clicks the Find Meme Button.
     */
    private void findMeme() {

        try {
            theJo2.setText(theJo.getText());
            String yodaS = URLEncoder.encode(theJo.getText(), StandardCharsets.UTF_8);
            String URLyoda = "https://api.funtranslations.com/translate/yoda.json?text=" + yodaS;
            System.out.println(URLyoda);

            HttpRequest request2 = HttpRequest.newBuilder()
                .uri(URI.create(URLyoda))
                .build();
            HttpResponse<String> response2 = HTTP_CLIENT
                .send(request2, BodyHandlers.ofString());

            String jsonString2 = response2.body();

            yodaResponse = GSON
                .fromJson(jsonString2, YodaResponse.class);
            System.out.println(jsonString2);

            if (yodaResponse != null && yodaResponse.contents != null) {
                String translatedText = yodaResponse.contents.translated;
                yoda.setText(translatedText);
            } else {
                throw new IllegalStateException("Unexpected response format");
            }
        } catch (IOException | InterruptedException e) {
            System.err.println("Caught!");
        } catch (IllegalStateException ex) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("Error");
            errorAlert.setContentText(ex.getMessage() + "\n\n" + "Yoda can't translate all that!" +
                " Get a new joke that's a little shorter for Yoda to say.");
            errorAlert.showAndWait();
            findMeme.setDisable(true);

        }
    }


    /**
     * Pressing the get Joke button will return a joke related to user Input.
     */
    private void getJoke() {

        try {
            buildQuery();

            HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(query))
                .build();

            HttpResponse<String> response = HTTP_CLIENT
                .send(request, BodyHandlers.ofString());

            jsonString = response.body();
            System.out.println(jsonString.trim());
            findMeme.setDisable(false);

            jokeResponse = GSON
                .fromJson(jsonString, JokeResponse.class);

            theJo.setText(jokeResponse.joke);
            yoda.setText(" ");
            if (jokeResponse.error) {
                throw new IllegalArgumentException("No Jokes were found.");
            }
        } catch (IOException | InterruptedException ex) {
            System.err.println("Exception caught!");
        } catch (IllegalArgumentException e) {
            makeAlertBox(e);
        }
    } // getJoke

    /**
     * This method makes an alert box when no jokes are found.
     * @param e the Exception to be used
     */
    private void makeAlertBox(Exception e) {
        Alert errorAlert = new Alert(AlertType.ERROR);
        errorAlert.setHeaderText("Error");
        errorAlert.setContentText("URI" + query + "\n\n" + "No Jokes were found.");
        errorAlert.showAndWait();
        findMeme.setDisable(true);
    }

    /**
     * This is the method that is used to build a query.
     */
    private void buildQuery() {
        String keyboard = userInput.getCharacters().toString();
        String url = "https://v2.jokeapi.dev/joke/Any?blacklistFlags=nsfw,religious,racist," +
            "sexist,explicit&type=single&contains=";
        query = new String(url + keyboard);
        System.out.println(query);
    }
} // ApiApp
