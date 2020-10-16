package models;

import controllers.MainController;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.util.Duration;

import java.io.File;
import java.util.Observable;
import java.util.Random;

public class Hilo extends Observable implements Runnable {

    private int turno;
    private MediaView mediaView;
    private String name;
    private Media media;
    private MediaPlayer mediaPlayer;
    private File file;
    private Random random;
    private boolean hasTouched;
    private boolean iterate;

    public Hilo(boolean startMusic, String name){
        this.name = name;
        iterate = true;
        hasTouched = false;
        turno = 1;
        if(startMusic){
            gameMusic();
        }
        random = new Random();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1600);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        while(iterate){
            hasTouched = false;
            setChanged();
            notifyObservers(String.valueOf(turno));
            try{
                if(turno == 3){
                    Thread.sleep(700);
                    if(!hasTouched){
                        setChanged();
                        notifyObservers(String.valueOf(-1));
                    }
                } else {
                    Thread.sleep(random.nextInt(100) + 1000);
                }
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            if(turno !=3){
                turno = random.nextInt(3) + 1;
            } else {
                turno = random.nextInt(2) + 1;
            }

        }
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
    }

    public boolean updateScore(){
        boolean hasWon = false;
        if(turno == 3){
            hasWon = true;
            hasTouched = true;
        }
        return hasWon;
    }

    public void gameLost(){
        iterate = false;
    }

    public void gameMusic(){
        file = new File("src/music/game_start.mp3");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(0.1);
        mediaPlayer.setOnEndOfMedia(new Runnable() {
            public void run() {
                file = new File("src/music/game_music.mp3");
                media = new Media(file.toURI().toString());
                mediaPlayer = new MediaPlayer(media);
                mediaPlayer.setVolume(0.1);
                mediaPlayer.setOnEndOfMedia(new Runnable() {
                    public void run() {
                        mediaPlayer.seek(Duration.ZERO);
                    }
                });
                mediaPlayer.play();
            }
        });
        mediaPlayer.play();
    }

    @Override
    public String toString() {
        return name;
    }
}
