import javafx.application.Application;
import javafx.collections.MapChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 *
 * @author dean
 */
public class AudioPayer2 extends Application {
  private Media media;
  private MediaPlayer mediaPlayer;
  
  private Label artist;
  private Label album;
  private Label title;
  private Label year;
  private ImageView albumCover;

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {
    createControls();
    createMedia();
    
    final Scene scene = new Scene(createGridPane(), 800, 400);
  //  final URL stylesheet = getClass().getResource("media.css");
  //  scene.getStylesheets().add(stylesheet.toString());

    primaryStage.setScene(scene);
    primaryStage.setTitle("Audio Player 2");
    primaryStage.show();
  }

  private GridPane createGridPane() {
    final GridPane gp = new GridPane();
    gp.setPadding(new Insets(10));
    gp.setHgap(20);
    //gp.add(albumCover, 0, 0, 1, GridPane.REMAINING);
    gp.add(title, 1, 0);
    gp.add(artist, 1, 1);
    gp.add(album, 1, 2);
    gp.add(year, 1, 3);
    
    final ColumnConstraints c0 = new ColumnConstraints();
    final ColumnConstraints c1 = new ColumnConstraints();
    c1.setHgrow(Priority.ALWAYS);
    gp.getColumnConstraints().addAll(c0, c1);
    
    final RowConstraints r0 = new RowConstraints();
    r0.setValignment(VPos.TOP);
    gp.getRowConstraints().addAll(r0, r0, r0, r0);
    
    return gp;
  }

  private void createControls() {
    artist = new Label();
    artist.setId("artist");
    album = new Label();
    album.setId("album");
    title = new Label();
    title.setId("title");
    year = new Label();
    year.setId("year");
    
    final Reflection reflection = new Reflection();
    reflection.setFraction(0.2);

//    final URL url = getClass().getResource("resources/defaultAlbum.png");
 //   final Image image = new Image(url.toString());
    
//    albumCover = new ImageView(image);
 //   albumCover.setFitWidth(240);
  //  albumCover.setPreserveRatio(true);
   // albumCover.setSmooth(true);
   // albumCover.setEffect(reflection);
  }

  private void createMedia() {
    try {
      media = new Media("http://traffic.libsyn.com/dickwall/JavaPosse373.mp3");
      media.getMetadata().addListener(new MapChangeListener<String, Object>() {
        @Override
        public void onChanged(Change<? extends String, ? extends Object> ch) {
          if (ch.wasAdded()) {
            handleMetadata(ch.getKey(), ch.getValueAdded());
          }
        }
      });

      mediaPlayer = new MediaPlayer(media);
      mediaPlayer.setOnError(new Runnable() {
        @Override
        public void run() {
          final String errorMessage = media.getError().getMessage();
          // Handle errors during playback
          System.out.println("MediaPlayer Error: " + errorMessage);
        }
      });

      mediaPlayer.play();

    } catch (RuntimeException re) {
      // Handle construction errors
      System.out.println("Caught Exception: " + re.getMessage());
    }
  }

  private void handleMetadata(String key, Object value) {
    if (key.equals("album")) {
      album.setText(value.toString());
    } else if (key.equals("artist")) {
      artist.setText(value.toString());
    } if (key.equals("title")) {
      title.setText(value.toString());
    } if (key.equals("year")) {
      year.setText(value.toString());
    } if (key.equals("image")) {
      albumCover.setImage((Image)value);
    }
  }
}