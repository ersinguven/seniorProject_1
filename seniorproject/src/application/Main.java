package application;



import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;


public class Main extends Application{
    int tur=0;
    int pzv=0;
    int fill_Box=0;
    double pstX, pstY;
    double en,boy, wayX, wayY;
    double offsetX, offsetY, orgSceneX, orgSceneY;
    double newTranslateX, newTranslateY, orgTranslateX, orgTranslateY;
    double x, y, width, height;
    double msf, shortest=999999;
    Rectangle rectangle;
    //Stones
    Text label;
    //Ara cizgi
    Line line;
    //Sayilarin tutuldugu yer
    ArrayList<Text> text;
    //Istakaya atilan taslar
    ArrayList<Text> p1;
    ArrayList<Text> p1throw= new ArrayList<>();
    ArrayList<Text> p2;
    ArrayList<Text> p2throw= new ArrayList<>();
    ArrayList<Text> p3;
    ArrayList<Text> p3throw= new ArrayList<>();
    ArrayList<Text> p4;
    ArrayList<Text> p4throw= new ArrayList<>();

    //atilacak Taslarin konuldugu yer
    ArrayList<Rectangle> P1_Drop = new ArrayList<>();
    ArrayList<Rectangle> P2_Drop = new ArrayList<>();
    ArrayList<Rectangle> P3_Drop = new ArrayList<>();
    ArrayList<Rectangle> P4_Drop = new ArrayList<>();
    Random rnd = new Random();
    //Dropped place


    ArrayList<Line> cizgi=new ArrayList<>();
    ArrayList<Line> FirstStr=new ArrayList<>();
    //	boolean [] cntrl;



    Paint defeat=Color.RED;
    Paint kfr=Color.DARKGRAY;
    Button btn;
    Button ctr;
    Group root;
    Button nextPlayer;
    Button nxtply;
    Button getSide;
    Button getNew;

    String soundFile_1 = "C:\\Users\\ersin\\Desktop\\School\\Data Structure\\seniorproject\\src\\application\\click_2.mp3";
    String soundFile_2 = "C:\\Users\\ersin\\Desktop\\School\\Data Structure\\seniorproject\\src\\application\\click_3.mp3";
    String soundFile_3 = "C:\\Users\\ersin\\Desktop\\School\\Data Structure\\seniorproject\\src\\application\\turn_1.mp3";
    String soundFile_4 = "C:\\Users\\ersin\\Desktop\\School\\Data Structure\\seniorproject\\src\\application\\start.mp3";
    Media sound_1 = new Media(new File(soundFile_1).toURI().toString());
    Media sound_2 = new Media(new File(soundFile_2).toURI().toString());
    Media sound_3 = new Media(new File(soundFile_3).toURI().toString());
    Media sound_4 = new Media(new File(soundFile_4).toURI().toString());
    MediaPlayer mediaPlayer_1 = new MediaPlayer(sound_1);
    MediaPlayer mediaPlayer_2 = new MediaPlayer(sound_2);
    MediaPlayer mediaPlayer_3 = new MediaPlayer(sound_3);
    MediaPlayer mediaPlayer_4 = new MediaPlayer(sound_4);

    @Override
    public void start(Stage primaryStage) {

        text=new ArrayList<>();

        root = new Group();

        double xcor=30.0f;
        double ycor=10.0f;
        double slide=71.0f;
        double Width= 40.0f;
        double Height= 50.0f;
        for(int a=1; a<107; a++) {

            if(a%13==0) {
                label =new Text(Integer.toString(13));
            }else if(a/13==8 && a%13!=0) {
                label =new Text(Integer.toString(0));
                label.setFill(Color.BLACK);
            }else {
                label =new Text(Integer.toString(a%13));
            }

            if(a>=1 && a<=26){
                label.setFill(Color.RED);
            }else if(a>=27 && a<=52){
                label.setFill(Color.GOLD);
            }else if(a>=53 && a<=79){
                label.setFill(Color.GREEN);
            }else if(a>=80 && a<=104){
                label.setFill(Color.BLUE);
            }

            label.setId(Integer.toString(a));
            label.setX(xcor+ Width/2);
            label.setY(ycor+ Height/2);


            label.setFont(Font.font("Verdana", 30));
            label.setOnMousePressed(labelpress);
            label.setOnMouseDragged(labeldragg);
            label.setOnMouseReleased(labelout);
            text.add(label);

            if(a%13==0 && a/13>0) {
                xcor=30.0f;
                ycor+=50;
            }else {
                xcor+=slide;
            }
        }
        double recx=230;
        double recy=700;
        en=1200;
        boy=200;
        //Big Istakasa
        Rectangle rectangle1= new Rectangle(recx, recy, en, boy);
        rectangle1.setFill(Color.BURLYWOOD);
        rectangle1.setStroke(Color.BLACK);
        Rectangle rectangle2= new Rectangle(recx+1250, recy-610, boy-50, en-500);
        rectangle2.setFill(Color.BURLYWOOD);
        rectangle2.setStroke(Color.BLACK);
        Rectangle rectangle3= new Rectangle(recx, recy-650, en, boy);
        rectangle3.setFill(Color.BURLYWOOD);
        rectangle3.setStroke(Color.BLACK);
        Rectangle rectangle4= new Rectangle(recx-200, recy-610, boy-50, en-500);
        rectangle4.setFill(Color.BURLYWOOD);
        rectangle4.setStroke(Color.BLACK);
        //Tas koyma yeri 3.player
        for(int c=1; c<=3; c++ ) {
            Rectangle P3_Stones =new Rectangle(recx, 260,80, 100);
            P3_Stones.setFill(Color.WHITE);
            P3_Drop.add(P3_Stones);
            recx+=90;
            root.getChildren().add(P3_Stones);
        }
        recx=230;
        //Tas koyma yeri 4.player
        for(int c=1; c<=3; c++ ) {
            Rectangle P4_Stones =new Rectangle(recx, 560,80, 100);
            P4_Stones.setFill(Color.WHITE);
            P4_Drop.add(P4_Stones);

            recx+=90;
            root.getChildren().add(P4_Stones);
        }
        recx=230;
        //Tas koyma yeri 1.player
        for(int c=1; c<=3; c++ ) {
            Rectangle P1_Stones =new Rectangle(recx+900, 560,80, 100);
            P1_Stones.setFill(Color.WHITE);
            P1_Drop.add(P1_Stones);
            recx+=90;
            root.getChildren().add(P1_Stones);
        }
        recx=230;
        //Tas koyma yeri 2.player
        for(int c=1; c<=3; c++ ) {
            Rectangle P2_Stones =new Rectangle(recx+900, 260,80, 100);
            P2_Stones.setFill(Color.WHITE);
            P2_Drop.add(P2_Stones);
            recx+=90;

            root.getChildren().add(P2_Stones);
        }
        recx=230;
        line= new Line(recx,recy+boy/2,recx+en,recy+boy/2);

        root.getChildren().addAll(rectangle1,rectangle2,rectangle3,rectangle4, line);
        for(int l=1; l<=30; l++) {

            line= new Line();
            //1.satir
            if(l%16==0 && l/16!=0) {
                recx=230;
                recy+=100;
                line.setStartX(recx);
                line.setStartY(recy);
                line.setEndX(recx);
                line.setEndY(recy+100);

            }else {
                line.setStartX(recx);
                line.setStartY(recy);
                line.setEndX(recx);
                line.setEndY(recy+100);
            }
            int id=l-1;
            line.setId(Integer.toString(id));
            cizgi.add(line);
            root.getChildren().add(line);
            recx+=80;
        }

        btn = new Button("Let's Start");
        btn.setOnAction(event);
        btn.setLayoutX(700);
        btn.setLayoutY(400);
        btn.setPrefWidth(200);
        btn.setPrefHeight(200);
        root.getChildren().add(btn);




        primaryStage.setResizable(false);
        primaryStage.setScene(new Scene(root, 1700,1000, Color.CHARTREUSE));

        primaryStage.setTitle("This Game real Name is= 183 game");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
    EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {

            mediaPlayer_4.play();

            root.getChildren().remove(btn);
            p1= new ArrayList<>();
            p2= new ArrayList<>();
            p3= new ArrayList<>();
            p4= new ArrayList<>();


            for(int b=0; b<=70; b++) {
                int stone= rnd.nextInt(text.size());
                if(b<=19) {
                    text.get(stone).setX(cizgi.get(b).getStartX()+30 );
                    text.get(stone).setY(cizgi.get(b).getStartY()+40 );


                    cizgi.get(b).setStroke(Color.RED);

                    p1.add(text.get(stone));
                    root.getChildren().add(p1.get(b));



                }
                else if(20<=b && b<=36) {
                    p2.add(text.get(stone));
                }else if(37<=b && b<=53) {
                    p3.add(text.get(stone));
                } else if(54<=b) {
                    p4.add(text.get(stone));
                }



                text.remove(stone);


            }
            for(Text sorun: p2) {
                System.out.println(sorun.getText());
            }



        }
    };
    EventHandler<MouseEvent> labelpress =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    mediaPlayer_1.stop();
                    mediaPlayer_1.play();

                    orgSceneX = t.getSceneX();
                    orgSceneY = t.getSceneY();
                    orgTranslateX = ((Text)(t.getSource())).getTranslateX();
                    orgTranslateY = ((Text)(t.getSource())).getTranslateY();
                    //System.out.println(orgSceneX+" "+orgSceneY);
                    for(int ln=0; ln<cizgi.size(); ln++) {

                        wayX=orgSceneX-cizgi.get(ln).getStartX();
                        wayY=orgSceneY-cizgi.get(ln).getStartY();
                        msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));

                        if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {

                            pzv=ln;

                            break;

                        }else if(msf<=shortest) {
                            shortest=msf;
                            pzv=ln;

                        }


                    }
                    //System.out.println(cizgi.get(pzv).getId()+" çizgi Using"+cizgi.get(pzv).getStartX()+" "+cizgi.get(pzv).getStartY());

                }
            };

    EventHandler<MouseEvent> labeldragg =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    offsetX = t.getSceneX() - orgSceneX;
                    offsetY = t.getSceneY() - orgSceneY;
                    newTranslateX = orgTranslateX + offsetX;
                    newTranslateY = orgTranslateY + offsetY;

                    ((Text)(t.getSource())).setTranslateX(newTranslateX);
                    ((Text)(t.getSource())).setTranslateY(newTranslateY);


                }
            };

    EventHandler<MouseEvent> labelout =
            new EventHandler<MouseEvent>() {

                @Override
                public void handle(MouseEvent t) {

                    mediaPlayer_2.stop();
                    mediaPlayer_2.play();

                    double prspointX=t.getSceneX();
                    double prspointY=t.getSceneY();
                    double ne=5;

                    int shrt = 0;

                    double shortest=999999;
                    msf=0;
                    MouseButton button = t.getButton();
                    if(button==MouseButton.PRIMARY){

                        for(int ln=0; ln<cizgi.size(); ln++) {

                            wayX=prspointX-cizgi.get(ln).getStartX();
                            wayY=prspointY-cizgi.get(ln).getStartY();
                            msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));

                            if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
                                pstX=cizgi.get(ln).getStartX()-newTranslateX;
                                pstY=cizgi.get(ln).getStartY()-newTranslateY;
                                if(cizgi.get(ln).getStroke().equals(defeat)) {
                                    pstX=cizgi.get(pzv).getStartX()-newTranslateX;
                                    pstY=cizgi.get(pzv).getStartY()-newTranslateY;
                                    ne=0;

                                    break;
                                }else {
                                    System.out.println("buradasýn");
                                    ((Text)(t.getSource())).setX((pstX +30));
                                    ((Text)(t.getSource())).setY((pstY+ 40));

                                    ne=1;
                                    shrt=ln;

                                    break;
                                }
                            }else if(msf<=shortest) {
                                shortest=msf;
                                pstX=cizgi.get(ln).getStartX()-newTranslateX;
                                pstY=cizgi.get(ln).getStartY()-newTranslateY;

                                if(cizgi.get(ln).getStroke().equals(defeat)) {
                                    System.out.println("var birþeyler");
                                    pstX=cizgi.get(pzv).getStartX()-newTranslateX;
                                    pstY=cizgi.get(pzv).getStartY()-newTranslateY;
                                    ne=0;

                                }else {
                                    shrt=ln;
                                    ne=-1;

                                }


                            }


                        }
                        ((Text)(t.getSource())).setX((pstX+30));
                        ((Text)(t.getSource())).setY((pstY+40));

                        //System.out.println(cizgi.get(shrt).getStroke());
                        if(ne==1) {
                            System.out.println("yeni kutunun içine girdi");
                            cizgi.get(shrt).setStroke(Color.RED);
                            cizgi.get(pzv).setStroke(Color.BLACK);
                        }else if(ne==-1) {
                            System.out.println("uzak mesafeden geldi");
                            cizgi.get(shrt).setStroke(Color.RED);
                            cizgi.get(pzv).setStroke(Color.BLACK);
                        }else if(ne==0) {
                            System.out.println("ayný kutuya döndü");
                        }else {
                            System.out.println("cafer aða");
                        }
                        //System.out.println("added="+cizgi.get(shrt));
                        //	System.out.println("number="+((Text)(t.getSource())));



                    }else if(button==MouseButton.SECONDARY){
                        //System.out.println("RÝGHT button clicked");

                        for(int ln=0; ln<P1_Drop.size(); ln++) {
                            wayX=prspointX-P1_Drop.get(ln).getX();
                            wayY=prspointY-P1_Drop.get(ln).getY();
                            msf= Math.sqrt(Math.pow(wayX, 2) + Math.pow(wayY, 2));
                            if((wayX>=0 && wayX<=80) && (wayY>=0 && wayY<=100)) {
                                pstX=P1_Drop.get(ln).getX()-newTranslateX;
                                pstY=P1_Drop.get(ln).getY()-newTranslateY;
                                if(P1_Drop.get(ln).getFill().equals(kfr)) {
                                    pstX=cizgi.get(pzv).getStartX()-newTranslateX;
                                    pstY=cizgi.get(pzv).getStartY()-newTranslateY;
                                }else {
                                    System.out.println("buradasýn");
                                    ((Text)(t.getSource())).setX((pstX +30));
                                    ((Text)(t.getSource())).setY((pstY+ 40));

                                    ne=1;
                                    shrt=ln;

                                    break;
                                }
                            }else if(msf<=shortest) {
                                shortest=msf;
                                pstX=P1_Drop.get(ln).getX()-newTranslateX;
                                pstY=P1_Drop.get(ln).getY()-newTranslateY;

                                if(P1_Drop.get(ln).getFill().equals(kfr)) {

                                    pstX=cizgi.get(pzv).getStartX()-newTranslateX;
                                    pstY=cizgi.get(pzv).getStartY()-newTranslateY;
                                    ne=0;

                                }else {
                                    shrt=ln;
                                    ne=-1;

                                }


                            }
                        }
                        ((Text)(t.getSource())).setX((pstX+30));
                        ((Text)(t.getSource())).setY((pstY+40));
                        P1_Drop.get(shrt).setFill(Color.DARKGRAY);
                        cizgi.get(pzv).setStroke(Color.BLACK);
                        fill_Box++;
                        if(fill_Box==3) {

                            nextPlayer=new Button("Next player");
                            nextPlayer.setOnAction(destro);
                            nextPlayer.setLayoutX(700);
                            nextPlayer.setLayoutY(400);
                            nextPlayer.setPrefWidth(200);
                            nextPlayer.setPrefHeight(200);
                            root.getChildren().add(nextPlayer);
                        }


//TUR KONTROLÜ YAP
                        //System.out.println("number="+((Text)(t.getSource())));
                        p1throw.add(((Text)(t.getSource())));
                        p1.remove(((Text)(t.getSource())));
                    }else if(button==MouseButton.MIDDLE){
                        System.out.println("MIDDLE button clicked");
				/*
				root.getChildren().removeAll(P1_Drop);
				root.getChildren().removeAll(P2_Drop);
				root.getChildren().removeAll(P3_Drop);
				root.getChildren().removeAll(P4_Drop);
				root.getChildren().addAll(P1_Drop);
				root.getChildren().addAll(P2_Drop);
				root.getChildren().addAll(P3_Drop);
				root.getChildren().addAll(P4_Drop);
				*/
                    }
                }
            };
    EventHandler<ActionEvent> destro = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            mediaPlayer_3.stop();
            mediaPlayer_3.play();
            root.getChildren().remove(nextPlayer);
            System.out.println("P2 TÝME");
            p3throw.clear();
            cotacter(p1throw, p2, p2throw, P2_Drop, P1_Drop);
            p4throw.clear();
            tasSayisi(text);
            System.out.println("P3 TÝME");
            cotacter(p2throw, p3, p3throw, P3_Drop, P2_Drop);
            p1throw.clear();
            tasSayisi(text);
            System.out.println("P4 TÝME");
            cotacter(p3throw, p4, p4throw, P4_Drop, P3_Drop);
            p2throw.clear();
            tasSayisi(text);
            fill_Box=0;
            tur++;
            if(tur>0) {
                getSide= new Button("get Dropped one");

                getSide.setOnAction(getter);
                getSide.setLayoutX(600);
                getSide.setLayoutY(400);
                getSide.setPrefWidth(200);
                getSide.setPrefHeight(200);
                root.getChildren().add(getSide);
                getNew= new Button("get New ones");
                getNew.setOnAction(newer);
                getNew.setLayoutX(900);
                getNew.setLayoutY(400);
                getNew.setPrefWidth(200);
                getNew.setPrefHeight(200);
                root.getChildren().add(getNew);
            }
        }

        private void tasSayisi(ArrayList<Text> text) {
            if(text.size()<=5) {
                System.out.println("son turlar");
            }

        }
    };
    private void cotacter(ArrayList<Text> taker, ArrayList<Text> player, ArrayList<Text> throwes,
                          ArrayList<Rectangle> dropped, ArrayList<Rectangle> takerdropped) {
        int choice2= rnd.nextInt(2)+1;
        //int choice2= 1;
        //int choice2= 2;
        System.out.println("answer="+choice2);
        if(choice2==1) {
            System.out.println("yerden aldý");
            player.addAll(taker);
            root.getChildren().removeAll(taker);
        }else if(choice2==2) {
            System.out.println("yeni taþ aldý");
            for(int a=0; a<3; a++) {
                //int sayi=rnd.nextInt(text.size());
                //throwes.add(player.get(sayi));
                player.add(text.get(0));
                text.remove(0);

            }
        }

        for(int m=0; m<3; m++) {
            int sayi=rnd.nextInt(player.size());
            throwes.add(player.get(sayi));
            player.remove(sayi);
            //throwes.add(player.get(player.size()-1));
            //player.remove(player.size()-1);

        }
        System.out.println(throwes);
        System.out.println("throwes size="+throwes.size());
        for(int h=0; h<throwes.size(); h++) {

            throwes.get(h).setX(dropped.get(h).getX()+30);
            throwes.get(h).setY(dropped.get(h).getY()+40);
            dropped.get(h).setFill(kfr);
        }
        System.out.println("throwes size="+throwes.size());
        for(int j=0; j<throwes.size(); j++) {
            if(taker.contains(throwes.get(j))) {
                System.out.println("burada bir sýkýntý var");
                //p2throw.get(j).setTranslateX(P2_Drop.get(j).getX()-P2_Drop.get(j).getX());
                //p2throw.get(j).setTranslateY(P2_Drop.get(j).getY()-P2_Drop.get(j).getY());
                //throwes.get(j).setTranslateX(dropped.get(j).getX()-dropped.get(j).getX());
                //throwes.get(j).setTranslateY(dropped.get(j).getY()-dropped.get(j).getY());
                throwes.get(j).setX(dropped.get(j).getX()+30);
                throwes.get(j).setY(dropped.get(j).getY()+40);
            }
        }

        root.getChildren().addAll(throwes);
/*	ArrayList<Integer>p2_nums= new ArrayList<>();
	for(int kl=0; kl<p2.size(); kl++) {
		int mat=Integer.parseInt(p2.get(kl).getText());
		System.out.println("gelenler="+mat);
		p2_nums.add(mat);
	}
		System.out.println("sayýlar="+p2_nums);*/

        //throwes.clear();

        for(int l=0; l<takerdropped.size(); l++) {
            takerdropped.get(l).setFill(Color.WHITE);
        }
    }
    EventHandler<ActionEvent> getter = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {

            root.getChildren().removeAll(getNew, getSide);

            for(int o=0; o<p4throw.size(); o++) {
                p4throw.get(o).setX(cizgi.get(cizgi.size()-1-o).getStartX()+30);
                p4throw.get(o).setY(cizgi.get(cizgi.size()-1-o).getStartY()+40);
                cizgi.get(cizgi.size()-1-o).setStroke(defeat);
                p1.add(p4throw.get(o));

                P4_Drop.get(o).setFill(Color.WHITE);
            }

        }
    };
    EventHandler<ActionEvent> newer = new EventHandler<ActionEvent>() {
        public void handle(ActionEvent e)
        {
            root.getChildren().removeAll(getNew, getSide);
            for(int o=0; o<3; o++) {
                p1.add(text.get(0));
                text.get(0).setX(cizgi.get(cizgi.size()-1-o).getStartX()+30);
                text.get(0).setY(cizgi.get(cizgi.size()-1-o).getStartY()+40);
                cizgi.get(cizgi.size()-1-o).setStroke(defeat);
                P4_Drop.get(o).setFill(Color.WHITE);
                root.getChildren().add(text.get(0));
                text.remove(0);
            }
        }
    };
}
