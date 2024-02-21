package views;

import model.characters.Direction;

//import java.awt.Color;

import model.characters.Fighter;
import model.characters.Hero;
import model.characters.Medic;
import model.characters.Zombie;
import model.collectibles.Supply;
import model.world.CharacterCell;
import model.world.CollectibleCell;
import model.world.TrapCell;

import java.io.File;

import engine.Game;
import exceptions.InvalidTargetException;
import exceptions.MovementException;
import exceptions.NoAvailableResourcesException;
import exceptions.NotEnoughActionsException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.paint.Color;

public class Main extends Application{
	Stage PrimaryStage;
	Scene StartPage;
	Scene InstructionsScene;
	Scene chooseUrCharachter;
	Scene MainPlay;
	Scene VictoryScene;
	Scene DefeatScene;
	
	BorderPane bp;
	StackPane stp;
	GridPane lbls;
	Button upB;
	Button downB;
	Button leftB;
	Button rightB;
	Button endTurnB;
	Button useSpecialB;
	Button cureB;
	Button attackB;
	Button lalalala;
	Button emptyCell;
	
	GridPane map;
	int o;
	int k;
	int tmp = 0;
	int tempX;
	int tempY;

	public void start (Stage PrimaryStage) throws Exception {
		Game.loadHeroes("Heroes.csv");
		this.PrimaryStage = PrimaryStage;
		//Music();
		SetScene1 ();
		Instructions();
		ChooseUrCharachter();
//		SetWinScreen();
//		SetLoseScreen();
		
		//setting up an icon & stage title
		Image icon = new Image("bird_icon.png");
		PrimaryStage.getIcons().add(icon);
		PrimaryStage.setTitle("The Last Of Us-Legacy");
		//PrimaryStage.setFullScreen(true);
		PrimaryStage.setScene(StartPage);
		PrimaryStage.show();
		
	}
	
	public void Music() {
		Media startMusic = new Media(getClass().getResource("/start_music.mp3").toExternalForm());
		MediaPlayer mediaPlayer = new MediaPlayer(startMusic);
		mediaPlayer.setAutoPlay(true);
		
	}
	
	
	public void SetScene1 () {
		StackPane startRoot = new StackPane();
		//setting the background for 1st scene
		Image window_start_screen = new Image("window_background.png");
		ImageView window_start_screen_imageView = new ImageView(window_start_screen);
		window_start_screen_imageView.setPreserveRatio(true);
		window_start_screen_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		window_start_screen_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
		startRoot.getChildren().add(window_start_screen_imageView);
		//Button from start to instructions
		Button startScreen_to_instructionsScreen_button = new Button();
		startScreen_to_instructionsScreen_button.setStyle("-fx-background-color: transparent");
		startScreen_to_instructionsScreen_button.setPrefSize(300, 100);
		startRoot.setAlignment(startScreen_to_instructionsScreen_button, javafx.geometry.Pos.BOTTOM_RIGHT);
		startRoot.getChildren().add(startScreen_to_instructionsScreen_button);
		//creat secen
		StartPage = new Scene(startRoot);
		//Button Function
		startScreen_to_instructionsScreen_button.setOnMouseClicked(clicked -> {
			PrimaryStage.setScene(InstructionsScene);
			//PrimaryStage.setFullScreen(true);
		});
		
	}
	
	public Scene Instructions() {
		StackPane InstructionRoot = new StackPane();
		Image Instructions = new Image("instructions_page.png");
		ImageView InstructionsView = new ImageView(Instructions);
		InstructionsView.setPreserveRatio(true);
		InstructionsView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		InstructionsView.fitHeightProperty().bind(PrimaryStage.heightProperty());
		InstructionRoot.getChildren().add(InstructionsView);
		//Next button
		Button NextButton = new Button();
		NextButton.setStyle("-fx-background-color: transparent");
		NextButton.setPrefSize(300, 100);
		InstructionRoot.setAlignment(NextButton, javafx.geometry.Pos.BOTTOM_RIGHT);
		InstructionRoot.getChildren().add(NextButton);
		InstructionsScene = new Scene(InstructionRoot);
		NextButton.setOnMouseClicked(clicked -> {
			PrimaryStage.setScene(chooseUrCharachter);
			//PrimaryStage.setFullScreen(true);
		});
		return InstructionsScene;
		
	}
	
	public Scene ChooseUrCharachter() {
		 StackPane choiceRoot = new StackPane ();
		 Image heroSelectPage = new Image("hero_select.png");
		 ImageView heroSelectPage_imageView = new ImageView(heroSelectPage);
		
		 // set the ImageView to preserve the aspect ratio of the image
		 heroSelectPage_imageView.setPreserveRatio(true);
		 // bind the size of the ImageView to the size of the Scene
		 heroSelectPage_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		 heroSelectPage_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
		 choiceRoot.getChildren().add(heroSelectPage_imageView);
		 
		
	     Button slectHeroScreen_to_mainGameScreen_button = new Button();
	     slectHeroScreen_to_mainGameScreen_button.setStyle("-fx-background-color: transparent");
	     slectHeroScreen_to_mainGameScreen_button.setPrefSize(200, 85);
		 StackPane.setAlignment(slectHeroScreen_to_mainGameScreen_button, javafx.geometry.Pos.BOTTOM_RIGHT);

		
		 //making the buttons for all heroes 
		 Button bill = new Button();
	     bill.setPrefSize(250, 250);
//	     Image billPic = new Image("billP.png");
//	     ImageView billPic_imageView = new ImageView(billPic);
//	     bill.setGraphic(billPic_imageView);
	     bill.setStyle("-fx-background-color: transparent");
	     
	     Button tommy = new Button();
	     tommy.setPrefSize(250, 250);
	     tommy.setStyle("-fx-background-color: transparent");

	     Button tess = new Button();
	     tess.setPrefSize(250, 250);
	     tess.setStyle("-fx-background-color: transparent");

	     Button riley = new Button();
	     riley.setPrefSize(250, 250);
	     riley.setStyle("-fx-background-color: transparent");

	     Button joel = new Button();
	     joel.setPrefSize(250, 250);
	     joel.setStyle("-fx-background-color: transparent");

	     Button henry = new Button();
	     henry.setPrefSize(250, 250);
	     henry.setStyle("-fx-background-color: transparent");

	     Button ellie = new Button();
	     ellie.setPrefSize(250, 250);
	     ellie.setStyle("-fx-background-color: transparent");

	     Button david = new Button();
	     david.setPrefSize(250, 250);
	     david.setStyle("-fx-background-color: transparent");
	     
	     StackPane mainRoot = new StackPane();
	     bp = new BorderPane();
	     	     
	     map = new GridPane();
	     map.setVgap(3);
	     map.setHgap(3);
	     Image mapBG = new Image("gameplaypic.png");
	     ImageView mapBG_imageView = new ImageView(mapBG);
	     //map.setLayoutX(400);
	     //map.setLayoutY(30);
	     
	     GridPane buttons = new GridPane();
	     
	     
	     //attack button
	     attackB = new Button();
	     attackB.setPrefSize(50, 50);
	     attackB.setStyle("-fx-background-color: transparent");
	     Image attackImage = new Image("attack.png");
	     ImageView attackImage_imageView = new ImageView(attackImage);
	     attackB.setGraphic(attackImage_imageView);
	     
	     //cure button
	     cureB = new Button();
	     cureB.setPrefSize(50, 50);
	     cureB.setStyle("-fx-background-color: transparent");
	     Image cureImage = new Image("cure.png");
	     ImageView cureImage_imageView = new ImageView(cureImage);
	     cureB.setGraphic(cureImage_imageView);
	     
	     //move buttons
	     
	     upB = new Button();
	     upB.setPrefSize(50, 50);
	     upB.setStyle("-fx-background-color: transparent");
	     Image upImage = new Image("up.png");
	     ImageView upImage_imageView = new ImageView(upImage);
	     upB.setGraphic(upImage_imageView);
	     
	     
	     
	     downB = new Button();
	     downB.setPrefSize(50, 50);
	     downB.setStyle("-fx-background-color: transparent");
	     Image downImage = new Image("down.png");
	     ImageView downImage_imageView = new ImageView(downImage);
	     downB.setGraphic(downImage_imageView);
	     
	     leftB = new Button();
	     leftB.setPrefSize(50, 50);
	     leftB.setStyle("-fx-background-color: transparent");
	     Image leftImage = new Image("left.png");
	     ImageView leftImage_imageView = new ImageView(leftImage);
	     leftB.setGraphic(leftImage_imageView);
	     
	     rightB = new Button();
	     rightB.setPrefSize(50, 50);
	     rightB.setStyle("-fx-background-color: transparent");
	     Image rightImage = new Image("right.png");
	     ImageView rightImage_imageView = new ImageView(rightImage);
	     rightB.setGraphic(rightImage_imageView);
	     
	     //Use special Ability
	     useSpecialB = new Button();
	     useSpecialB.setPrefSize(50, 50);
	     useSpecialB.setStyle("-fx-background-color: transparent");
	     Image specialImage = new Image("special.png");
	     ImageView specialImage_imageView = new ImageView(specialImage);
	     useSpecialB.setGraphic(specialImage_imageView);
	     
	     //end turn button
	     endTurnB = new Button();
	     endTurnB.setPrefSize(50, 50);
	     endTurnB.setStyle("-fx-background-color: white");
	     Image endTurnImage = new Image("endTurn.png");
	     ImageView endTurnImage_imageView = new ImageView(endTurnImage);
	     endTurnB.setGraphic(endTurnImage_imageView);
	     
	     //empty buttons
	     Button empty1 = new Button();
	     empty1.setPrefSize(50, 50);
	     empty1.setStyle("-fx-background-color: transparent");
	     Button empty2 = new Button();
	     empty2.setPrefSize(50, 50);
	     empty2.setStyle("-fx-background-color: transparent");
	     Button empty3 = new Button();
	     empty3.setPrefSize(50, 50);
	     empty3.setStyle("-fx-background-color: transparent");
	     Button empty4 = new Button();
	     empty4.setPrefSize(50, 50);
	     empty4.setStyle("-fx-background-color: transparent");
	     Button empty5 = new Button();
	     empty5.setPrefSize(50, 50);
	     empty5.setStyle("-fx-background-color: transparent");
	     Button empty6 = new Button();
	     empty6.setPrefSize(50, 50);
	     empty6.setStyle("-fx-background-color: transparent");
	     Button empty7 = new Button();
	     empty7.setPrefSize(50, 50);
	     empty7.setStyle("-fx-background-color: transparent");
	     Button empty8 = new Button();
	     empty8.setPrefSize(50, 50);
	     empty8.setStyle("-fx-background-color: transparent");
	     Button empty9 = new Button();
	     empty9.setPrefSize(50, 50);
	     empty9.setStyle("-fx-background-color: transparent");
	     Button empty10 = new Button();
	     empty10.setPrefSize(50, 50);
	     empty10.setStyle("-fx-background-color: transparent");
	     Button empty11 = new Button();
	     empty11.setPrefSize(50, 50);
	     empty11.setStyle("-fx-background-color: transparent");
	     Button empty12 = new Button();
	     empty12.setPrefSize(50, 50);
	     empty12.setStyle("-fx-background-color: transparent");
	     Button empty13 = new Button();
	     empty13.setPrefSize(50, 50);
	     empty13.setStyle("-fx-background-color: transparent");
	     Button empty14 = new Button();
	     empty14.setPrefSize(50, 50);
	     empty14.setStyle("-fx-background-color: transparent");
	     Button empty15 = new Button();
	     empty15.setPrefSize(50, 50);
	     empty15.setStyle("-fx-background-color: transparent");
	     Button empty16 = new Button();
	     empty16.setPrefSize(50, 50);
	     empty16.setStyle("-fx-background-color: transparent");
	     Button empty17 = new Button();
	     empty17.setPrefSize(50, 50);
	     empty17.setStyle("-fx-background-color: transparent");
	     
	     buttons.setHgap(3);
	     buttons.setVgap(3);
//	     buttons.addRow(0, attackB, useSpecialB, cureB);
//	     //buttons.addRow(1, empty2, empty3, empty4);
//	     buttons.addRow(2, empty5, upB, empty6);
//	     buttons.addRow(3, leftB, downB, rightB);
//	     buttons.addRow(4, empty7, empty8, empty9);
//	     //buttons.addRow(5, empty10, empty11, empty12);
//	     buttons.addRow(5, empty13, empty14, endTurnB);
	     buttons.add(empty1, 0, 0);
	     buttons.add(empty2, 0, 1);
	     buttons.add(empty3, 0, 2);
	     buttons.add(empty4, 0, 3);
	     buttons.add(empty7, 0, 4);
	     
	     buttons.add(upB, 1, 5);
	     buttons.add(leftB, 0, 6);
	     buttons.add(downB, 1, 6);
	     buttons.add(rightB, 2, 6);
	     
	     buttons.add(empty5, 0, 7);
	     
	     buttons.add(attackB, 0, 8);
	     buttons.add(useSpecialB, 1, 8);
	     buttons.add(cureB, 2, 8);
	     
	     buttons.add(empty6, 0, 9);
	     
	     buttons.add(endTurnB, 1, 10);

	     
	     bp.setRight(buttons);
	     
	     Image billFocus = new Image("bill.png");
		 ImageView billFocus_imageView = new ImageView(billFocus);
		 billFocus_imageView.setPreserveRatio(true);
		 billFocus_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		 billFocus_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
		 
		 Image henryFocus = new Image("henry.png");
		 ImageView henryFocus_imageView = new ImageView(henryFocus);
		 henryFocus_imageView.setPreserveRatio(true);
		 henryFocus_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		 henryFocus_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
		 
		 Image rileyFocus = new Image("riley.png");
		 ImageView rileyFocus_imageView = new ImageView(rileyFocus);
		 rileyFocus_imageView.setPreserveRatio(true);
		 rileyFocus_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		 rileyFocus_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
		 
		 Image joelFocus = new Image("joel.png");
		 ImageView joelFocus_imageView = new ImageView(joelFocus);
		 joelFocus_imageView.setPreserveRatio(true);
		 joelFocus_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		 joelFocus_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
		 
		 Image tessFocus = new Image("tess.png");
		 ImageView tessFocus_imageView = new ImageView(tessFocus);
		 tessFocus_imageView.setPreserveRatio(true);
		 tessFocus_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		 tessFocus_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
		 
		 Image davidFocus = new Image("david.png");
		 ImageView davidFocus_imageView = new ImageView(davidFocus);
		 davidFocus_imageView.setPreserveRatio(true);
		 davidFocus_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		 davidFocus_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
		 
		 Image ellieFocus = new Image("ellie.png");
		 ImageView ellieFocus_imageView = new ImageView(ellieFocus);
		 ellieFocus_imageView.setPreserveRatio(true);
		 ellieFocus_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		 ellieFocus_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
		 
		 Image tommyFocus = new Image("tommy.png");
		 ImageView tommyFocus_imageView = new ImageView(tommyFocus);
		 tommyFocus_imageView.setPreserveRatio(true);
		 tommyFocus_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
		 tommyFocus_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
	     
	     bill.setOnMouseClicked(clickedBill -> {
	    	 	choiceRoot.getChildren().remove(heroSelectPage_imageView);
	    	 	choiceRoot.getChildren().add(billFocus_imageView);
				choiceRoot.getChildren().add(slectHeroScreen_to_mainGameScreen_button);	
				Hero chosenOne = Game.availableHeroes.get(5);
				slectHeroScreen_to_mainGameScreen_button.setOnMouseClicked(onClickNext1 -> {
					Game.startGame(chosenOne);
					setColors(map);
					bp.setLeft(map);
					map.setScaleY(-1);
					mainRoot.getChildren().add(mapBG_imageView);
					mainRoot.getChildren().add(bp);
					MainPlay = new Scene(mainRoot);
					PrimaryStage.setScene(MainPlay);
					PrimaryStage.setFullScreen(true);
					PrimaryStage.show();
					//setMainPlay();
				});
		});
		tommy.setOnMouseClicked(clickedTommy -> {
				choiceRoot.getChildren().remove(heroSelectPage_imageView);
	    	 	choiceRoot.getChildren().add(tommyFocus_imageView);
				choiceRoot.getChildren().add(slectHeroScreen_to_mainGameScreen_button);
				Hero chosenOne = Game.availableHeroes.get(4);
				slectHeroScreen_to_mainGameScreen_button.setOnMouseClicked(onClickNext2 -> {
					Game.startGame(chosenOne);
					setColors(map);
					bp.setLeft(map);
					map.setScaleY(-1);
					mainRoot.getChildren().add(mapBG_imageView);
					mainRoot.getChildren().add(bp);
					MainPlay = new Scene(mainRoot);
					PrimaryStage.setScene(MainPlay);
					PrimaryStage.setFullScreen(true);
					PrimaryStage.show();
				});
		});
		tess.setOnMouseClicked(clickedTess -> {
				choiceRoot.getChildren().remove(heroSelectPage_imageView);
	    	 	choiceRoot.getChildren().add(tessFocus_imageView);
				choiceRoot.getChildren().add(slectHeroScreen_to_mainGameScreen_button);
				Hero chosenOne = Game.availableHeroes.get(2);
				slectHeroScreen_to_mainGameScreen_button.setOnMouseClicked(onClickNext3 -> {
					Game.startGame(chosenOne);
					setColors(map);
					bp.setLeft(map);
					map.setScaleY(-1);
					mainRoot.getChildren().add(mapBG_imageView);
					mainRoot.getChildren().add(bp);
					MainPlay = new Scene(mainRoot);
					PrimaryStage.setScene(MainPlay);
					PrimaryStage.setFullScreen(true);
					PrimaryStage.show();
				});
		});
		riley.setOnMouseClicked(clickedRiley -> {
				choiceRoot.getChildren().remove(heroSelectPage_imageView);
	    	 	choiceRoot.getChildren().add(rileyFocus_imageView);
				choiceRoot.getChildren().add(slectHeroScreen_to_mainGameScreen_button);
				Hero chosenOne = Game.availableHeroes.get(3);
				slectHeroScreen_to_mainGameScreen_button.setOnMouseClicked(onClickNext4 -> {
					Game.startGame(chosenOne);
					setColors(map);
					bp.setLeft(map);
					map.setScaleY(-1);
					mainRoot.getChildren().add(mapBG_imageView);
					mainRoot.getChildren().add(bp);
					MainPlay = new Scene(mainRoot);
					PrimaryStage.setScene(MainPlay);
					PrimaryStage.setFullScreen(true);
					PrimaryStage.show();
				});
		});
		joel.setOnMouseClicked(clickedJoel -> {
				choiceRoot.getChildren().remove(heroSelectPage_imageView);
	    	 	choiceRoot.getChildren().add(joelFocus_imageView);
				choiceRoot.getChildren().add(slectHeroScreen_to_mainGameScreen_button);
				Hero chosenOne = Game.availableHeroes.get(0);
				slectHeroScreen_to_mainGameScreen_button.setOnMouseClicked(onClickNext5 -> {
					Game.startGame(chosenOne);
					setColors(map);
					bp.setLeft(map);
					map.setScaleY(-1);
					mainRoot.getChildren().add(mapBG_imageView);
					mainRoot.getChildren().add(bp);
					MainPlay = new Scene(mainRoot);
					PrimaryStage.setScene(MainPlay);
					PrimaryStage.setFullScreen(true);
					PrimaryStage.show();
				});
		});
		henry.setOnMouseClicked(clickedHenry -> {
				choiceRoot.getChildren().remove(heroSelectPage_imageView);
	    	 	choiceRoot.getChildren().add(henryFocus_imageView);
				choiceRoot.getChildren().add(slectHeroScreen_to_mainGameScreen_button);
				Hero chosenOne = Game.availableHeroes.get(7);
				slectHeroScreen_to_mainGameScreen_button.setOnMouseClicked(onClickNext6 -> {
					Game.startGame(chosenOne);
					setColors(map);
					bp.setLeft(map);
					map.setScaleY(-1);
					mainRoot.getChildren().add(mapBG_imageView);
					mainRoot.getChildren().add(bp);
					MainPlay = new Scene(mainRoot);
					PrimaryStage.setScene(MainPlay);
					PrimaryStage.setFullScreen(true);
					PrimaryStage.show();
				});
		});
		ellie.setOnMouseClicked(clickedEllie -> {
				choiceRoot.getChildren().remove(heroSelectPage_imageView);
	    	 	choiceRoot.getChildren().add(ellieFocus_imageView);
				choiceRoot.getChildren().add(slectHeroScreen_to_mainGameScreen_button);
				Hero chosenOne = Game.availableHeroes.get(1);
				slectHeroScreen_to_mainGameScreen_button.setOnMouseClicked(onClickNext7 -> {
					Game.startGame(chosenOne);
					setColors(map);
					bp.setLeft(map);
					map.setScaleY(-1);
					mainRoot.getChildren().add(mapBG_imageView);
					mainRoot.getChildren().add(bp);
					MainPlay = new Scene(mainRoot);
					PrimaryStage.setScene(MainPlay);
					PrimaryStage.setFullScreen(true);
					PrimaryStage.show();
				});
		});
		david.setOnMouseClicked(clickedDavid -> {
				choiceRoot.getChildren().remove(heroSelectPage_imageView);
	    	 	choiceRoot.getChildren().add(davidFocus_imageView);
				choiceRoot.getChildren().add(slectHeroScreen_to_mainGameScreen_button);
				Hero chosenOne = Game.availableHeroes.get(6);
				slectHeroScreen_to_mainGameScreen_button.setOnMouseClicked(onClickNext8 -> {
					Game.startGame(chosenOne);
					setColors(map);
					bp.setLeft(map);
					map.setScaleY(-1);
					mainRoot.getChildren().add(mapBG_imageView);
					mainRoot.getChildren().add(bp);
					MainPlay = new Scene(mainRoot);
					PrimaryStage.setScene(MainPlay);
					PrimaryStage.setFullScreen(true);
					PrimaryStage.show();
				});
		});

	     GridPane heroGrid = new GridPane();
	     heroGrid.setHgap(100);
	     heroGrid.setVgap(75);
	     heroGrid.addRow(0, bill, tommy, tess, riley);
	     heroGrid.addRow(1, joel, henry, ellie, david);
	     heroGrid.setAlignment(Pos.CENTER);
		
	     choiceRoot.getChildren().add(heroGrid);
		 chooseUrCharachter = new Scene(choiceRoot);
		 return chooseUrCharachter;
	}
	
//	public Scene setMainPlay() {
//		StackPane mainRoot = new StackPane();
//		BorderPane bp = new BorderPane();
//		GridPane map = new GridPane();
//		map.setVgap(1);
//		map.setHgap(1);
//		bp.setLeft(map);
//		mainRoot.getChildren().add(bp);
//		MainPlay = new Scene(mainRoot);
		//setColors(map);
		//setLabels(map);
//		return MainPlay;
//	}
	
	public void setColors(GridPane m){
		for(int x=0; x<15; x++){
			for(int y=0; y<15; y++){
				if(Game.map[x][y].isVisible() == true){
					if(Game.map[x][y] instanceof CharacterCell){
						if(((CharacterCell) Game.map[x][y]).getCharacter() instanceof Zombie){
							o = x;
							k = y;
							Image zombiePic = new Image("zombie.png");
							ImageView zombie_imageView = new ImageView(zombiePic);
							lalalala = new Button();
							lalalala.setPrefSize(60, 60);
							lalalala.setStyle("-fx-background-color: transparent");
							lalalala.setGraphic(zombie_imageView);
							Color color = Color.web("#A4031F");
							Rectangle box = new Rectangle(60,60,color);
							StackPane sp = new StackPane(box,lalalala);
							sp.setScaleY(-1);
							m.add(sp, x, y);
						}
						else if(((CharacterCell) Game.map[x][y]).getCharacter() instanceof Hero){
							//Label lbl = setLabels((Hero)(((CharacterCell) Game.map[x][y]).getCharacter()));
							final int horiz = x;
							final int vertic = y;
							String name = ((CharacterCell)Game.map[x][y]).getCharacter().getName();
							Text t = new Text("");
							if(name.equals("Joel Miller"))
								t.setText("JOEL");
							if(name.equals("Ellie Williams"))
								t.setText("ELLIE");
							if(name.equals("Tess"))
								t.setText("TESS");
							if(name.equals("Riley Abel"))
								t.setText("Riley");
							if(name.equals("Tommy Miller"))
								t.setText("TOMMY");
							if(name.equals("Bill"))
								t.setText("BILL");
							if(name.equals("David"))
								t.setText("DAVID");
							if(name.equals("Henry Burell"))
								t.setText("HENRY");
							t.setFont(Font.font("Arial", 15));
							Button b = new Button();
							tempX = x;
							tempY = y;
							b.setStyle("-fx-background-color: transparent");
							b.setPrefSize(60, 60);
							
							b.setOnMouseClicked(selectHero -> {
								upB.setOnMouseClicked(selectHeroUp -> {
//									if(Game.map[horiz][vertic+1] instanceof TrapCell) {
//										Alert alert = new Alert(Alert.AlertType.WARNING);
//										alert.initOwner(PrimaryStage);
//									    alert.setTitle("TRAP CELL");
//									    alert.setHeaderText("YOU'RE MOVING INTO A TRAP NOW !!!");
//									    alert.setContentText("THIS MEANS YOU'LL LOSE SOME HP");
//									    alert.showAndWait();
//									}
                                        try {
											((Hero) ((CharacterCell) Game.map[horiz][vertic]).getCharacter()).move(Direction.RIGHT);
										} catch (MovementException outOfMapE) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
										    alert.setTitle("MOVEMENT EXCEPTION");
										    alert.setHeaderText("YOU CANNOT MOVE UP NOW");
										    alert.setContentText(outOfMapE.getMessage());
										    alert.showAndWait();
										}
										catch (NotEnoughActionsException e) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
										    alert.setTitle("NOT ENOUGH ACTIONS AVAILABLE EXCEPTION");
										    alert.setHeaderText("YOU HAVE USED UP ALL YOUR ACTION POINTS FOR THIS TURN");
										    alert.setContentText(e.getMessage());
										    alert.showAndWait();
										}
//										int newX = horiz+1;
//										int newY = vertic;
//										if(Game.map[newX][newY] instanceof TrapCell) {
//											Alert alert = new Alert(Alert.AlertType.WARNING);
//											alert.initOwner(PrimaryStage);
//										    alert.setTitle("TRAP CELL");
//										    alert.setHeaderText("YOU MOVED INTO A TRAP");
//										    alert.setContentText("THIS MEANS YOU'VE LOST SOME HP");
//										    alert.showAndWait();
//										}
										
										if(Game.checkWin() == true) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
											
											// add an event filter to the window that owns the Alert
											alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
										    // allow the dialog to close
											event.consume();
										    // close the application
										    Platform.exit();
											});
											
										    alert.setTitle("congrats");
										    alert.setHeaderText("VICTORY!");
										    alert.setContentText("YOU WON");
										    alert.showAndWait();
											//PrimaryStage.setScene(VictoryScene);
										}
										if(Game.checkGameOver() == true) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
											
											// add an event filter to the window that owns the Alert
											alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
										    // allow the dialog to close
											event.consume();
										    // close the application
										    Platform.exit();
											});
											
										    alert.setTitle("game over");
										    alert.setHeaderText("YOU LOST :(");
										    alert.setContentText("THE ZOMBIES KILLED ALL YOUR HEROES");
										    alert.showAndWait();
											//PrimaryStage.setScene(DefeatScene);
										}
										
										setColors(map);
										
								});
								downB.setOnMouseClicked(selectHeroDown -> {
//									if(Game.map[horiz][vertic-1] instanceof TrapCell) {
//										Alert alert = new Alert(Alert.AlertType.WARNING);
//										alert.initOwner(PrimaryStage);
//									    alert.setTitle("TRAP CELL");
//									    alert.setHeaderText("YOU'RE MOVING INTO A TRAP NOW !!!");
//									    alert.setContentText("THIS MEANS YOU'LL LOSE SOME HP");
//									    alert.showAndWait();
//									}
									try {
										((Hero) ((CharacterCell) Game.map[horiz][vertic]).getCharacter()).move(Direction.LEFT);
									} catch (MovementException outOfMapE) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("MOVEMENT EXCEPTION");
									    alert.setHeaderText("YOU CANNOT MOVE DOWN NOW");
									    alert.setContentText(outOfMapE.getMessage());
									    alert.showAndWait();
									}
									catch (NotEnoughActionsException e) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("NOT ENOUGH ACTIONS AVAILABLE EXCEPTION");
									    alert.setHeaderText("YOU HAVE USED UP ALL YOUR ACTION POINTS FOR THIS TURN");
									    alert.setContentText(e.getMessage());
									    alert.showAndWait();
									}
//									int newX = horiz-1;
//									int newY = vertic;
//									if(Game.map[newX][newY] instanceof TrapCell) {
//										Alert alert = new Alert(Alert.AlertType.WARNING);
//										alert.initOwner(PrimaryStage);
//									    alert.setTitle("TRAP CELL");
//									    alert.setHeaderText("YOU MOVED INTO A TRAP");
//									    alert.setContentText("THIS MEANS YOU'VE LOST SOME HP");
//									    alert.showAndWait();
//									}
									
									if(Game.checkWin() == true) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
										
										// add an event filter to the window that owns the Alert
										alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
									    // allow the dialog to close
										event.consume();
									    // close the application
									    Platform.exit();
										});
										
									    alert.setTitle("congrats");
									    alert.setHeaderText("VICTORY!");
									    alert.setContentText("YOU WON");
									    alert.showAndWait();
										//PrimaryStage.setScene(VictoryScene);
									}
									if(Game.checkGameOver() == true) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
										
										// add an event filter to the window that owns the Alert
										alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
									    // allow the dialog to close
										event.consume();
									    // close the application
									    Platform.exit();
										});
										
									    alert.setTitle("game over");
									    alert.setHeaderText("YOU LOST :(");
									    alert.setContentText("THE ZOMBIES KILLED ALL YOUR HEROES");
									    alert.showAndWait();
										//PrimaryStage.setScene(DefeatScene);
									}
									setColors(map);
								});
								leftB.setOnMouseClicked(selectHeroLeft -> {
//									if(Game.map[horiz-1][vertic] instanceof TrapCell) {
//										Alert alert = new Alert(Alert.AlertType.WARNING);
//										alert.initOwner(PrimaryStage);
//									    alert.setTitle("TRAP CELL");
//									    alert.setHeaderText("YOU'RE MOVING INTO A TRAP NOW !!!");
//									    alert.setContentText("THIS MEANS YOU'LL LOSE SOME HP");
//									    alert.showAndWait();
//									}
									try {
										((Hero) ((CharacterCell) Game.map[horiz][vertic]).getCharacter()).move(Direction.DOWN);
									} catch (MovementException outOfMapE) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("MOVEMENT EXCEPTION");
									    alert.setHeaderText("YOU CANNOT MOVE LEFT NOW");
									    alert.setContentText(outOfMapE.getMessage());
									    alert.showAndWait();
									}
									catch (NotEnoughActionsException e) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("NOT ENOUGH ACTIONS AVAILABLE EXCEPTION");
									    alert.setHeaderText("YOU HAVE USED UP ALL YOUR ACTION POINTS FOR THIS TURN");
									    alert.setContentText(e.getMessage());
									    alert.showAndWait();
									}
//									if(Game.map[horiz-1][vertic] instanceof TrapCell) {
//										Alert alert = new Alert(Alert.AlertType.WARNING);
//										alert.initOwner(PrimaryStage);
//									    alert.setTitle("TRAP CELL");
//									    alert.setHeaderText("YOU MOVED INTO A TRAP");
//									    alert.setContentText("THIS MEANS YOU'VE LOST SOME HP");
//									    alert.showAndWait();
//									}
//									int newX = horiz;
//									int newY = vertic-1;
//									if(Game.map[newX][newY] instanceof TrapCell) {
//										Alert alert = new Alert(Alert.AlertType.WARNING);
//										alert.initOwner(PrimaryStage);
//									    alert.setTitle("TRAP CELL");
//									    alert.setHeaderText("YOU MOVED INTO A TRAP");
//									    alert.setContentText("THIS MEANS YOU'VE LOST SOME HP");
//									    alert.showAndWait();
//									}
									
									if(Game.checkWin() == true) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
										
										// add an event filter to the window that owns the Alert
										alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
									    // allow the dialog to close
										event.consume();
									    // close the application
									    Platform.exit();
										});
										
									    alert.setTitle("congrats");
									    alert.setHeaderText("VICTORY!");
									    alert.setContentText("YOU WON");
									    alert.showAndWait();
										//PrimaryStage.setScene(VictoryScene);
									}
									if(Game.checkGameOver() == true) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
										
										// add an event filter to the window that owns the Alert
										alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
									    // allow the dialog to close
										event.consume();
									    // close the application
									    Platform.exit();
										});
										
									    alert.setTitle("game over");
									    alert.setHeaderText("YOU LOST :(");
									    alert.setContentText("THE ZOMBIES KILLED ALL YOUR HEROES");
									    alert.showAndWait();
										//PrimaryStage.setScene(DefeatScene);
									}
									setColors(map);
								});
								rightB.setOnMouseClicked(selectHeroRight -> {
//									if(Game.map[horiz+1][vertic] instanceof TrapCell) {
//										Alert alert = new Alert(Alert.AlertType.WARNING);
//										alert.initOwner(PrimaryStage);
//									    alert.setTitle("TRAP CELL");
//									    alert.setHeaderText("YOU'RE MOVING INTO A TRAP NOW !!!");
//									    alert.setContentText("THIS MEANS YOU'LL LOSE SOME HP");
//									    alert.showAndWait();
//									}
									try {
										((Hero) ((CharacterCell) Game.map[horiz][vertic]).getCharacter()).move(Direction.UP);
									} catch (MovementException outOfMapE) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("MOVEMENT EXCEPTION");
									    alert.setHeaderText("YOU CANNOT MOVE RIGHT NOW");
									    alert.setContentText(outOfMapE.getMessage());
									    alert.showAndWait();
									}
									catch (NotEnoughActionsException e) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("NOT ENOUGH ACTIONS AVAILABLE EXCEPTION");
									    alert.setHeaderText("YOU HAVE USED UP ALL YOUR ACTION POINTS FOR THIS TURN");
									    alert.setContentText(e.getMessage());
									    alert.showAndWait();
									}
//									int newX = horiz;
//									int newY = vertic+1;
//									if(Game.map[newX][newY] instanceof TrapCell) {
//										Alert f = new Alert(Alert.AlertType.WARNING);
//										f.initOwner(PrimaryStage);
//									    f.setTitle("TRAP CELL");
//									    f.setHeaderText("YOU MOVED INTO A TRAP");
//									    f.setContentText("THIS MEANS YOU'VE LOST SOME HP");
//									    f.showAndWait();
//									}
									if(Game.checkWin() == true) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
										
										// add an event filter to the window that owns the Alert
										alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
									    // allow the dialog to close
										event.consume();
									    // close the application
									    Platform.exit();
										});
										
									    alert.setTitle("congrats");
									    alert.setHeaderText("VICTORY!");
									    alert.setContentText("YOU WON");
									    alert.showAndWait();
										//PrimaryStage.setScene(VictoryScene);
									}
									if(Game.checkGameOver() == true) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
										
										// add an event filter to the window that owns the Alert
										alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
									    // allow the dialog to close
										event.consume();
									    // close the application
									    Platform.exit();
										});
										
									    alert.setTitle("game over");
									    alert.setHeaderText("YOU LOST :(");
									    alert.setContentText("THE ZOMBIES KILLED ALL YOUR HEROES");
									    alert.showAndWait();
										//PrimaryStage.setScene(DefeatScene);
									}
									setColors(map);
								});
								endTurnB.setOnMouseClicked(endTurn -> {
									try {
										Game.endTurn();
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if(Game.checkWin() == true) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
										
										// add an event filter to the window that owns the Alert
										alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
									    // allow the dialog to close
										event.consume();
									    // close the application
									    Platform.exit();
										});
										
									    alert.setTitle("congrats");
									    alert.setHeaderText("VICTORY!");
									    alert.setContentText("YOU WON");
									    alert.showAndWait();
										//PrimaryStage.setScene(VictoryScene);
									}
									if(Game.checkGameOver() == true) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
										
										// add an event filter to the window that owns the Alert
										alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
									    // allow the dialog to close
										event.consume();
									    // close the application
									    Platform.exit();
										});
										
									    alert.setTitle("game over");
									    alert.setHeaderText("YOU LOST :(");
									    alert.setContentText("THE ZOMBIES KILLED ALL YOUR HEROES");
									    alert.showAndWait();
										//PrimaryStage.setScene(DefeatScene);
									}
									setColors(map);
								});
								useSpecialB.setOnMouseClicked(useS -> {
//									if(((CharacterCell)(Game.map[horiz][vertic])).getCharacter() instanceof Medic) {
//										
//									}
//									else {
//										
//									}
									try {
										((Hero)((CharacterCell) Game.map[horiz][vertic]).getCharacter()).useSpecial();
									} catch (NoAvailableResourcesException outOfMapE) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("NO AVAILABLE RESOURCES EXCEPTION");
									    alert.setHeaderText("YOU NEED A SUPPLY FOR THIS ACTION");
									    alert.setContentText(outOfMapE.getMessage());
									    alert.showAndWait();
									}
									catch (InvalidTargetException e) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("INVALID TARGET EXCEPTION");
									    alert.setHeaderText("YOU CANNOT DO THIS ACTION WITH THE TARGET YOU SELECTED");
									    alert.setContentText(e.getMessage());
									    alert.showAndWait();
									}
									if(Game.checkWin() == true) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
										
										// add an event filter to the window that owns the Alert
										alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
									    // allow the dialog to close
										event.consume();
									    // close the application
									    Platform.exit();
										});
										
									    alert.setTitle("congrats");
									    alert.setHeaderText("VICTORY!");
									    alert.setContentText("YOU WON");
									    alert.showAndWait();
										//PrimaryStage.setScene(VictoryScene);
									}
									if(Game.checkGameOver() == true) {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
										
										// add an event filter to the window that owns the Alert
										alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
									    // allow the dialog to close
										event.consume();
									    // close the application
									    Platform.exit();
										});
										
									    alert.setTitle("game over");
									    alert.setHeaderText("YOU LOST :(");
									    alert.setContentText("THE ZOMBIES KILLED ALL YOUR HEROES");
									    alert.showAndWait();
										//PrimaryStage.setScene(DefeatScene);
									}
									setColors(map);
								});
								cureB.setOnMouseClicked(useC -> {
									
									emptyCell.setOnMouseClicked(selectZombie2 -> {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("INVALID TARGET EXCEPTION");
									    alert.setHeaderText("THERE IS NO ZOMBIE TO CURE");
									    alert.setContentText("You should select a target to cure first.");
									    alert.showAndWait();
									});
									
									lalalala.setOnMouseClicked(selectZombie -> {
										((Hero)((CharacterCell) Game.map[horiz][vertic]).getCharacter()).setTarget(((CharacterCell) Game.map[o][k]).getCharacter());
										try {
											((Hero)((CharacterCell) Game.map[horiz][vertic]).getCharacter()).cure();
										} catch (NoAvailableResourcesException outOfMapE) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
										    alert.setTitle("NO AVAILABLE RESOURCES EXCEPTION");
										    alert.setHeaderText("YOU NEED A SUPPLY FOR THIS ACTION");
										    alert.setContentText(outOfMapE.getMessage());
										    alert.showAndWait();
										}
										catch (InvalidTargetException e) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
										    alert.setTitle("INVALID TARGET EXCEPTION");
										    alert.setHeaderText("YOU CANNOT DO THIS ACTION WITH THE TARGET YOU SELECTED");
										    alert.setContentText(e.getMessage());
										    alert.showAndWait();
										}
										catch (NotEnoughActionsException e) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
										    alert.setTitle("NOT ENOUGH ACTIONS AVAILABLE EXCEPTION");
										    alert.setHeaderText("YOU HAVE USED UP ALL YOUR ACTION POINTS FOR THIS TURN");
										    alert.setContentText(e.getMessage());
										    alert.showAndWait();
										}
										if(Game.checkWin() == true) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
											
											// add an event filter to the window that owns the Alert
											alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
										    // allow the dialog to close
											event.consume();
										    // close the application
										    Platform.exit();
											});
											
										    alert.setTitle("congrats");
										    alert.setHeaderText("VICTORY!");
										    alert.setContentText("YOU WON");
										    alert.showAndWait();
											//PrimaryStage.setScene(VictoryScene);
										}
										if(Game.checkGameOver() == true) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
											
											// add an event filter to the window that owns the Alert
											alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
										    // allow the dialog to close
											event.consume();
										    // close the application
										    Platform.exit();
											});
											
										    alert.setTitle("game over");
										    alert.setHeaderText("YOU LOST :(");
										    alert.setContentText("THE ZOMBIES KILLED ALL YOUR HEROES");
										    alert.showAndWait();
											//PrimaryStage.setScene(DefeatScene);
										}
										setColors(map);
									});
								});
								attackB.setOnMouseClicked(att -> {
//									int hero2X = horiz;
//									int hero2Y = vertic;
//									b.setOnMouseClicked(selectHero2 -> {
//										
//											Alert alert = new Alert(Alert.AlertType.WARNING);
//											alert.initOwner(PrimaryStage);
//										    alert.setTitle("INVALID TARGET EXCEPTION");
//										    alert.setHeaderText("YOU CANNOT ATTACK A FELLOW HERO");
//										    alert.setContentText("YOU CAN ONLY ATTACK ZOMBIES");
//										    alert.showAndWait();
//										
//									});
									b.setOnMouseClicked(selectHero2 -> {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("INVALID TARGET EXCEPTION");
									    alert.setHeaderText("YOU CANNOT ATTACK ANOTHER HERO");
									    alert.setContentText("You should select a target to attack first.");
									    alert.showAndWait();
									});
									
									emptyCell.setOnMouseClicked(selectZombie2 -> {
										Alert alert = new Alert(Alert.AlertType.WARNING);
										alert.initOwner(PrimaryStage);
									    alert.setTitle("INVALID TARGET EXCEPTION");
									    alert.setHeaderText("THERE IS NO ZOMBIE TO ATTACK");
									    alert.setContentText("You should select a target to attack first.");
									    alert.showAndWait();
									});
									
									
									lalalala.setOnMouseClicked(selectZombie2 -> {
										((Hero)((CharacterCell) Game.map[horiz][vertic]).getCharacter()).setTarget(((CharacterCell) Game.map[o][k]).getCharacter());
										try {
											((Hero)((CharacterCell) Game.map[horiz][vertic]).getCharacter()).attack();
											tmp++;
										} catch (InvalidTargetException e) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
										    alert.setTitle("INVALID TARGET EXCEPTION");
										    alert.setHeaderText("YOU CANNOT DO THIS ACTION WITH THE TARGET YOU SELECTED");
										    alert.setContentText(e.getMessage());
										    alert.showAndWait();
										}
										catch (NotEnoughActionsException e) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
										    alert.setTitle("NOT ENOUGH ACTIONS AVAILABLE EXCEPTION");
										    alert.setHeaderText("YOU HAVE USED UP ALL YOUR ACTION POINTS FOR THIS TURN");
										    alert.setContentText(e.getMessage());
										    alert.showAndWait();
										}
										if(Game.checkWin() == true) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
											
											// add an event filter to the window that owns the Alert
											alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
										    // allow the dialog to close
											event.consume();
										    // close the application
										    Platform.exit();
											});
											
										    alert.setTitle("congrats");
										    alert.setHeaderText("VICTORY!");
										    alert.setContentText("YOU WON");
										    alert.showAndWait();
											//PrimaryStage.setScene(VictoryScene);
										}
										if(Game.checkGameOver() == true) {
											Alert alert = new Alert(Alert.AlertType.WARNING);
											alert.initOwner(PrimaryStage);
											
											// add an event filter to the window that owns the Alert
											alert.getDialogPane().getScene().getWindow().addEventFilter(WindowEvent.WINDOW_CLOSE_REQUEST, event -> {
										    // allow the dialog to close
											event.consume();
										    // close the application
										    Platform.exit();
											});
											
										    alert.setTitle("game over");
										    alert.setHeaderText("YOU LOST :(");
										    alert.setContentText("THE ZOMBIES KILLED ALL YOUR HEROES");
										    alert.showAndWait();
											//PrimaryStage.setScene(DefeatScene);
										}
										
										setColors(map);
									});
								
									
//									if(tmp ==0 ) {
//										Alert alert = new Alert(Alert.AlertType.WARNING);
//										alert.initOwner(PrimaryStage);
//									    alert.setTitle("INVALID TARGET EXCEPTION");
//									    alert.setHeaderText("YOU CANNOT ATTACK A FELLOW HERO");
//									    alert.setContentText("YOU CAN ONLY ATTACK ZOMBIES");
//									    alert.showAndWait();
//									}
									
								});
						});
								
								Color naples_yellow = Color.web("#F5D64D");
								Rectangle box = new Rectangle(60,60,naples_yellow);
								StackPane sp = new StackPane(box,t,b);
								sp.setScaleY(-1);
								m.add(sp, x, y);
								
							}
							else{
								//Color ivory = Color.web("#F5FBEF");
								emptyCell = new Button();
								emptyCell.setPrefSize(60, 60);
								emptyCell.setStyle("-fx-background-color: transparent");
								Rectangle box = new Rectangle(60,60,Color.WHITE);
								StackPane sp = new StackPane(box, emptyCell);
								m.add(sp, x, y);
							}
					}
					else if(Game.map[x][y] instanceof CollectibleCell){
						if(((CollectibleCell) Game.map[x][y]).getCollectible() instanceof Supply){
							Image supplyPic = new Image("supply.png");
							ImageView supply_imageView = new ImageView(supplyPic);
							Color color = Color.web("#61A5C2");
							Rectangle box = new Rectangle(60,60,color);
							StackPane sp = new StackPane(box);
							sp.getChildren().add(supply_imageView);
							sp.setScaleY(-1);
							m.add(sp, x, y);
						}
						else {
							Image vaccinePic = new Image("vaccine.png");
							ImageView vaccine_imageView = new ImageView(vaccinePic);
							Color color = Color.web("#86A873");
							Rectangle box = new Rectangle(60,60,color);
							StackPane sp = new StackPane(box);
							sp.getChildren().add(vaccine_imageView);
							sp.setScaleY(-1);
							m.add(sp, x, y);
						}
					}
					else if(Game.map[x][y] instanceof TrapCell){
						//Color ivory = Color.web("#F5FBEF");
						Rectangle box = new Rectangle(60,60,Color.WHITE);
						m.add(box, x, y);
					}
				}
				else{ 
					Color black_olive = Color.web("#343633");
					Color jet = Color.web("#2D2D2A");
					Color outer_space = Color.web("#484A47");
					Rectangle box = new Rectangle(60,60,outer_space);
					m.add(box, x, y);		
				}
			}
	   }
	   int numberOfRemainingHeroes = Game.heroes.size();
	   int fack = 0;
	   GridPane labelsGrid = new GridPane();
	   while(numberOfRemainingHeroes > 0) {
		   Label label = setLabels(Game.heroes.get(numberOfRemainingHeroes-1));
		   labelsGrid.addRow(fack, label);
//		   bp.setCenter(label);
		   numberOfRemainingHeroes--;
		   fack++;
	   }
	   bp.setCenter(labelsGrid);
	   
//	   GridPane ass = new GridPane();
//	   int numberOfRemainingHeroes = Game.heroes.size();
//	   int index = 0;
//	   while(numberOfRemainingHeroes > 0) {
//		   System.out.println("i am here broooooo");
//		   ass.addRow(index, setLabels(Game.heroes.get(numberOfRemainingHeroes)));
//		   System.out.println("i am here agaiannn");
//		   index++;
//		   numberOfRemainingHeroes--;
//	   }
//	   bp.setRight(ass);
//		ass.getChildren().add(setLabels(Game.heroes.get(index)));

		
} 
	
	
	public Label setLabels(Hero h){
		//name
		String n = h.getName();
		//currentHp
		String cHP = h.getCurrentHp() + "";
		//action points available
		String aa = h.getActionsAvailable() + "";
		//max actions
		String ma = h.getMaxActions() + "";
		//attackDmg
		String aDMG = h.getAttackDmg() + "";
		//supplies
		String sCount = h.getSupplyInventory().size() + "";
		//vaccines
		String vCount = h.getVaccineInventory().size() + "";
		//type of hero
		String type = "";
		if(h instanceof Fighter)
			type = "FIGHTER";
		else if(h instanceof Medic)
			type = "MEDIC";
		else
			type = "EXPLORER";
		
		Label info = new Label();
		info.setText("         Name: " + n + "\n" +
					 "         Type: " + type + "\n" +
				     "         Current HP: " + cHP + "\n" +
					 "         Action pts available: " + aa + "\n" +
				     "         Max actions: " + ma + "\n" +
					 "         Attack dmg: " + aDMG + "\n" +
				     "         Supplies available: " + sCount + "\n" +
					 "         Vaccines availale: " + vCount + "\n" +
				     "                 ");
		info.setFont(Font.font("Verdana", FontWeight.EXTRA_BOLD, 17));
		Color labelColor = Color.web("#DA5902");
		info.setTextFill(labelColor);
		return info;
	}
//	public Scene SetWinScreen () {
//		StackPane victoryRoot = new StackPane();
//		//setting the background for 1st scene
//		Image victoryPic = new Image("VICTORY.png");
//		ImageView victoryPic_imageView = new ImageView(victoryPic);
//		victoryPic_imageView.setPreserveRatio(true);
//		victoryPic_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
//		victoryPic_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
//		victoryRoot.getChildren().add(victoryPic_imageView);
//		VictoryScene = new Scene(victoryRoot);
//		
//		return VictoryScene;
//	}
//	public Scene SetLoseScreen () {
//		StackPane defeatRoot = new StackPane();
//		//setting the background for 1st scene
//		Image defeatPic = new Image("DEFEAT.png");
//		ImageView defeatPic_imageView = new ImageView(defeatPic);
//		defeatPic_imageView.setPreserveRatio(true);
//		defeatPic_imageView.fitWidthProperty().bind(PrimaryStage.widthProperty());
//		defeatPic_imageView.fitHeightProperty().bind(PrimaryStage.heightProperty());
//		defeatRoot.getChildren().add(defeatPic_imageView);
//		DefeatScene = new Scene(defeatRoot);
//		
//		return DefeatScene;
//	}
	
	public static void main (String [] args) {
		launch(args);
	}
	
	
}
