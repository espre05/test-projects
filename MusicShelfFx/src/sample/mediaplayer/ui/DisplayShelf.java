package sample.mediaplayer.ui;

import java.util.List;

import sample.mediaplayer.domain.Album;
import sample.mediaplayer.service.AlbumService;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.ScrollBar;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
    /**
     * A ui control which displays a browsable display shelf of images
     */
    public  class DisplayShelf extends Region {
        private static final Duration DURATION = Duration.millis(500);
        private static final Interpolator INTERPOLATOR = Interpolator.EASE_BOTH;
        private static final double SPACING = 50;
        private static final double LEFT_OFFSET = -110;
        private static final double RIGHT_OFFSET = 110;
        private static final double SCALE_SMALL = 0.7;
        private PerspectiveImage[] items;
        private Pane centeredPane = new Pane();
        private Pane leftPane = new Pane();
        private Pane centerPane = new Pane();
        private Pane rightPane = new Pane();
        private int centerIndex = 0;
        private Timeline timeline;
        private ScrollBar scrollBar = new ScrollBar();
        private boolean localChange = false;
        private Rectangle clipRectangle = new Rectangle();
        
        private AlbumService albumService;
		private ChangeListener<Album> albumListener;
		List<Album> albumList;

        public DisplayShelf(AlbumService aService) throws Exception{
        	setId("displayShelf");
        	this.albumService =aService;
        	
        	albumList  = albumService.getAlbumList();
        	// set clip
            setClip(clipRectangle);
            // set background gradient using css
//            setStyle("-fx-background-color: linear-gradient(to bottom," +
//                    " black 60, #141414 60.1%, black 100%);");
            // style scroll bar color
            //scrollBar.setStyle("-fx-base: #202020; -fx-background: #202020;");
            scrollBar.setId("scrollBar");
            // create items
            items = new PerspectiveImage[albumList.size()];
            for (int i=0; i<albumList.size(); i++) {
            	final Album album = albumList.get(i);
                //final PerspectiveImage persImageitem =
                        items[i] = new PerspectiveImage(album.getTitle(), album.getCoverImage().getPath());
                //final double index = i;
//                persImageitem.setOnMouseClicked(new EventHandler<MouseEvent>() {
//                    @Override
//					public void handle(MouseEvent me) {
//                        localChange = true;
//                        scrollBar.setValue(index);
//                        localChange = false;
//                        shiftToCenter(persImageitem);
//                        System.out.println("AlbumCover Clicked: " + persImageitem.getTitle() );
//                    }
//                });
            }
            
            // setup scroll bar
            scrollBar.setMax(items.length-1);
            scrollBar.setVisibleAmount(1);
            scrollBar.setUnitIncrement(1);
            scrollBar.setBlockIncrement(1);
            scrollBar.valueProperty().addListener(new ChangeListener<Number>() {

				@Override
				public void changed(
						ObservableValue<? extends Number> paramObservableValue,
						Number oldVal, Number newVal) {
					System.out.printf("Scroll clicked: oldVal:%s newVal:%s\n",oldVal,newVal);
					int diff = oldVal.intValue() - newVal.intValue();
					shiftToCenter(items[(int)scrollBar.getValue()]); update();
				}

            });
            // create content
            centeredPane.getChildren().addAll(leftPane, rightPane, centerPane);
            getChildren().addAll(centeredPane,scrollBar);
            // listen for keyboard events
            setFocusTraversable(true);
            

            final MyMousePressEvent myMousePressEvt = new MyMousePressEvent();
            setOnMousePressed(new EventHandler<MouseEvent>(){

				@Override
				public void handle(MouseEvent mousePressEvent) {
					myMousePressEvt.mousePressX = mousePressEvent.getX();
					myMousePressEvt.mousePressY = mousePressEvent.getY();
					double centerPt = (getWidth()-PerspectiveImage.WIDTH)/2;
					double distance = mousePressEvent.getX()-centerPt;
					boolean leftPressFlag =  distance <0;
					boolean rightPressFlag = distance >0;
					int amt = (int) Math.round(Math.abs(distance) / (PerspectiveImage.WIDTH ));
					KeyCode kc = null;
					if(leftPressFlag && !rightPressFlag){
						kc = KeyCode.LEFT;
						amt++; // compensate for 
					}
					if(!leftPressFlag && rightPressFlag){
						kc = KeyCode.RIGHT;
					}
					for(int i= 0; i < amt; i++){
					shiftScrollBarStatus(kc);
					}
					System.out.printf("left:%s right:%s center:%s  x:%s dist:%s amt:%s \n", leftPressFlag, rightPressFlag,centerPt, mousePressEvent.getX(), distance ,amt);
				
				}});

            setOnKeyPressed(new EventHandler<KeyEvent>() {
                @Override
				public void handle(KeyEvent ke) {
                	if( ke.getCode() == KeyCode.LEFT || ke.getCode() == KeyCode.RIGHT ){
                	shiftScrollBarStatus(ke.getCode() );
                	}
                }
            });
            // update
            update();
        }

        void shiftScrollBarStatus(KeyCode ke){
        	int shiftVal = 0;
            if (ke == KeyCode.LEFT) {
            	shiftVal = 1;
            } else if (ke  == KeyCode.RIGHT) {
            	shiftVal = -1;
            }
        	shiftImage(shiftVal);

        }
        public void changeFocusToAlbum(int i){
        	shiftImage(centerIndex -i);
        }
        public void shiftImage(int shiftAmount) {
        	int oldAlbumIndex = centerIndex;

        	if (centerIndex <= 0 && shiftAmount > 0) return;
            if (centerIndex >= items.length - 1 && shiftAmount < 0) return;
            centerIndex -= shiftAmount;
            update();

            localChange = true;
            scrollBar.setValue(centerIndex);
            localChange = false;
            this.albumListener.changed(null, albumList.get(oldAlbumIndex), albumList.get(centerIndex));
        }    

		@Override protected void layoutChildren() {
            // update clip to our size
            clipRectangle.setWidth(getWidth());
            clipRectangle.setHeight(getHeight());
            // keep centered centered
            centeredPane.setLayoutY((getHeight() - PerspectiveImage.HEIGHT) / 2);
            centeredPane.setLayoutX((getWidth() - PerspectiveImage.WIDTH) / 2);
            // position scroll bar at bottom
            scrollBar.setLayoutX(10);
            scrollBar.setLayoutY(getHeight()-25);
            scrollBar.resize(getWidth()-20,15);
        }

        private void update() {
            // move items to new homes in groups
            leftPane.getChildren().clear();
            centerPane.getChildren().clear();
            rightPane.getChildren().clear();
            for (int i = 0; i < centerIndex; i++) {
                leftPane.getChildren().add(items[i]);
            }
            centerPane.getChildren().add(items[centerIndex]);
            for (int i = items.length - 1; i > centerIndex; i--) {
                rightPane.getChildren().add(items[i]);
            }
            // stop old timeline if there is one running
            if (timeline!=null) timeline.stop();
            // create timeline to animate to new positions
            timeline = new Timeline();
            // add keyframes for left items
            final ObservableList<KeyFrame> keyFrames = timeline.getKeyFrames();
            for (int i = 0; i < leftPane.getChildren().size(); i++) {
                final PerspectiveImage pImg = items[i];
                double newX = -leftPane.getChildren().size() *
                        SPACING + SPACING * i + LEFT_OFFSET;
                keyFrames.add(new KeyFrame(DURATION,
                    new KeyValue(pImg.translateXProperty(), newX, INTERPOLATOR),
                    new KeyValue(pImg.scaleXProperty(), SCALE_SMALL, INTERPOLATOR),
                    new KeyValue(pImg.scaleYProperty(), SCALE_SMALL, INTERPOLATOR),
                    new KeyValue(pImg.angle, 45.0, INTERPOLATOR)));
            }
            // add keyframe for center item
            final PerspectiveImage centerPImg = items[centerIndex];
            keyFrames.add(new KeyFrame(DURATION,
                    new KeyValue(centerPImg.translateXProperty(), 0, INTERPOLATOR),
                    new KeyValue(centerPImg.scaleXProperty(), 1.0, INTERPOLATOR),
                    new KeyValue(centerPImg.scaleYProperty(), 1.0, INTERPOLATOR),
                    new KeyValue(centerPImg.angle, 90.0, INTERPOLATOR)));
            // add keyframes for right items
            for (int i = 0; i < rightPane.getChildren().size(); i++) {
                final PerspectiveImage rightPImg = items[items.length - i - 1];
                final double newX = rightPane.getChildren().size() *
                        SPACING - SPACING * i + RIGHT_OFFSET;
                keyFrames.add(new KeyFrame(DURATION,
                        new KeyValue(rightPImg.translateXProperty(), newX, INTERPOLATOR),
                        new KeyValue(rightPImg.scaleXProperty(), SCALE_SMALL, INTERPOLATOR),
                        new KeyValue(rightPImg.scaleYProperty(), SCALE_SMALL, INTERPOLATOR),
                        new KeyValue(rightPImg.angle, 135.0, INTERPOLATOR)));
            }
            // play animation
            timeline.play();
        }

        private void shiftToCenter(PerspectiveImage persImg) {
            for (int i = 0; i < leftPane.getChildren().size(); i++) {
                if (leftPane.getChildren().get(i) == persImg) {
                    int shiftAmount = leftPane.getChildren().size() - i;
                    shiftImage(shiftAmount);
                    return;
                }
            }
            if (centerPane.getChildren().get(0) == persImg) {
                return;
            }
            for (int i = 0; i < rightPane.getChildren().size(); i++) {
                if (rightPane.getChildren().get(i) == persImg) {
                    int shiftAmount = -(rightPane.getChildren().size() - i);
                    shiftImage(shiftAmount);
                    return;
                }
            }
        }



		public void addAlbumChangeListener(ChangeListener<Album> albumListen) {
			this.albumListener = albumListen;// .changed(paramObservableValue, paramT1, newAlbum)
		}
    }

    class  MyMousePressEvent{
    	enum DIRECTION{ TO_LEFT, TO_RIGHT };
    	double mousePressX;
    	double mousePressY;
    	public boolean isLeft(double newX, double newY){
    		return (newX - mousePressX) > 5;
    	}
    };
