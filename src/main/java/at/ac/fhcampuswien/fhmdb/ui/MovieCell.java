package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.util.Arrays;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();

//added new label for genre
    private final Label genre = new Label();

//new labels for releaseYear and rating
    private final Label releaseYear = new Label();
    private final Label rating = new Label();

    //for later (probably):
    private final Label imgUrl = new Label();
    private final Label lengthInMinutes = new Label();
    private final Label directors = new Label();
    private final Label writers = new Label();
    private final Label mainCast = new Label();

    // Buttons
    private final JFXButton detailsBtn = new JFXButton("Details");
    private final Region region = new Region();
    private final JFXButton btn = new JFXButton();
    private final VBox vb = new VBox(title, detail, genre, releaseYear, rating, detailsBtn, btn);
    private final HBox layout = new HBox(vb, region, detailsBtn, btn);

    public MovieCell(ClickEventHandler addToWatchListClicked, String btnText) {
        super();
        btn.setOnMouseClicked(mouseEvent -> { addToWatchListClicked.onClick(getItem());
        });
        btn.setText(btnText);
    }
    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            //setText(null);
            setGraphic(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );
            genre.setText(movie.getGenres().toString().replace("[", "").replace("]", ""));
            releaseYear.setText("Released: " + String.valueOf(movie.getReleaseYear()));
            rating.setText("Rating: "+ String.valueOf(movie.getRating()));

            // Details Button
            detailsBtn.setOnAction(event -> {
                // create Details Dialog
                Dialog<String> dialog = new Dialog<String>();
                dialog.setTitle("Details");
                ButtonType type = new ButtonType("Ok", ButtonBar.ButtonData.OK_DONE);
                GridPane grid = new GridPane();

                Label title1 = new Label("Title: ");
                Label title2 = new Label(movie.getTitle());
                Label genres1 = new Label("Genres: ");
                Label genres2 = new Label(movie.getGenres().toString());
                Label release1 = new Label("Released: ");
                Label release2 = new Label("" + movie.getReleaseYear());
                //Label cast1 = new Label("Cast: ");
                //Label cast2 = new Label(Arrays.toString(movie.getMainCast()));
                Label length1 = new Label("Length: ");
                Label length2 = new Label("" + movie.getLengthInMinutes());
                Label rating1 = new Label("Rating: ");
                Label rating2 = new Label("" + movie.getRating());
                Label desc1 = new Label(movie.getDescription());
                desc1.setMaxWidth(500);
                desc1.setWrapText(true);
                desc1.setPadding(new Insets(100));
                Label desc2 = new Label("");

                // Style
                //grid.setStyle("-fx-background-color: #454545; -fx-text-fill: #454545;");
                //dialog.getDialogPane().setStyle("-fx-background-color: #000000;");
                dialog.getDialogPane().getScene().getStylesheets().add(this.getScene().getStylesheets().get(0));
                dialog.getDialogPane().getStyleClass().add("background-black");
                grid.getStyleClass().add("background-light-black");
                //ButtonBar buttonBar = (ButtonBar)dialog.getDialogPane().lookup(".button-bar");
                //buttonBar.getStyleClass().add("background-yellow");
                //buttonBar.getButtons().forEach(b->b.setStyle("-fx-background-color: #f5c518;"));
                //grid.setPadding(new Insets(10));

                grid.add(title1, 1, 1);
                grid.add(title2, 2, 1);
                grid.add(genres1, 1, 2);
                grid.add(genres2, 2, 2);
                grid.add(release1, 1, 3);
                grid.add(release2, 2, 3);
                //grid.add(cast1, 1, 4);
                //grid.add(cast2, 2, 4);
                grid.add(length1, 1, 5);
                grid.add(length2, 2, 5);
                grid.add(rating1, 1, 6);
                grid.add(rating2, 2, 6);
                grid.add(desc2, 1,7);
                grid.add(desc1, 1,8, 2, 1);
                dialog.getDialogPane().setContent(grid);
                grid.getChildren().forEach(c -> {
                    Label l = (Label)c;
                    l.setFont(Font.font("Arial",14));
                    l.getStyleClass().add("text-white");
                    l.setPadding(new Insets(2));
                });
                //dialog.setContentText("This is a sample dialog");
                dialog.getDialogPane().getButtonTypes().add(type);
                dialog.showAndWait();

            });

            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            genre.getStyleClass().add("text-white");
            releaseYear.getStyleClass().add("text-yellow");
            rating.getStyleClass().add("text-yellow");
            detailsBtn.getStyleClass().add("background-yellow");
            btn.getStyleClass().add("background-yellow");
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

            // layout
            title.fontProperty().set(title.getFont().font(20));
            detail.setMaxWidth(this.getScene().getWidth() - btn.getWidth() - detailsBtn.getWidth() - 250);
            detail.setWrapText(true);
            genre.setFont(Font.font("Arial", FontPosture.ITALIC, 10));  //makes genre italic
            HBox.setHgrow(region, Priority.ALWAYS);
            layout.setPadding(new Insets(10));
            layout.spacingProperty().set(10);
            layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);
            layout.setMaxWidth(this.getScene().getWidth() - 30);
            setGraphic(layout);
        }
    }
}

