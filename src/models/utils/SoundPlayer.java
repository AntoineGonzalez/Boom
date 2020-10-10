/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.utils;

import java.io.File;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;

/**
 * Classe permettant de jouer un son 
 * @author Erwann
 */
public class SoundPlayer {
    
    private File file;
    private String shootSound = "resources/sounds/shoot.wav";
    private String bombSound = "resources/sounds/bomb.wav";
    private String mineSound = "resources/sounds/mine.wav";
    private String moveSound = "resources/sounds/move.wav";
    private String shieldSound = "resources/sounds/shield.wav";
    
    private AudioInputStream stream;
    private AudioFormat format;
    private DataLine.Info info;
    private Clip clip;
            
    public SoundPlayer()
    {
        
    }
    
    /**
     * Classe permettant de joueur un son
     * @param sound 
     *  le son a jouer
     */
    public void playSound(Actions.Action sound)
    {
        try {
            this.file = new File("resources/sounds/"+sound+".wav");
            stream = AudioSystem.getAudioInputStream(file);
            format = stream.getFormat();
            info = new DataLine.Info(Clip.class, format);
            clip = (Clip) AudioSystem.getLine(info);
            clip.open(stream);
            clip.start();
        }
        catch (Exception e) {
            System.out.println("no file");
        }
    }

}
