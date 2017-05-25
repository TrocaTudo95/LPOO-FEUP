package dkeep.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Color;

public class PrettyPanel extends JPanel {
	public static final int DEFAULT_IMG_SIZE = 50;

	private static final long serialVersionUID = 1L;
	private HashMap<Character,BufferedImage> char_to_img = new HashMap<Character,BufferedImage>();
	private String current_map;
	private BufferedImage lost_game;
	private BufferedImage won_game;
	private int map_width = 0;
	private int map_height = 0;
	private Boolean lost;
	private Boolean won;
	
	
	/**
	 * Create the panel.
	 */
	public PrettyPanel(){
		lost=false;
		won=false;
		setBackground(Color.WHITE);
		loadGuardImage();  	   loadHeroImage();
		loadOgreImage();   	   loadWallImage();
		loadFloorImage();      loadStairImage();
		loadLeverImage();      loadDoorImage();
		loadHero_armedImage(); loadOgre_stunedImage();
		loadGuard_stunImage(); loadFire();
		loadLostImage(); 	   loadWinImage();
		loadlLeverFire(); 	   loadHero_key();
	}
	
	public PrettyPanel(String map) {
		this();
		this.current_map = map;
		this.updateMapSize();
		this.setBounds(0, 0,DEFAULT_IMG_SIZE*this.map_width, DEFAULT_IMG_SIZE*this.map_height);
	}
	
	public void updateCurrentMap(String map){
		this.current_map = map;
	}
	
	public void updateMapSize(){
		this.map_height = (this.map_width = 0);
		boolean first_line=false;
		for (int i=0; i< current_map.length();i++){
			if(current_map.charAt(i)=='\n'){
				map_height++;
				first_line=true;
			}
			else if(!first_line)
				map_width++;
		}
	}
	
	public void  gameOver(boolean b){
		this.lost=b;
	}
	
	public void  gameWon(boolean b){
		this.won=b;
	}
	
	public int getMapWidth(){
		return this.map_width;
	}
	
	public int getMapHeight(){
		return this.map_height;
	}
	
	public void paint(Graphics g){
		super.paint(g);
		updateMapSize();
		int x = 0, y = 0;
		for(int i = 0 ; i < this.current_map.length() ; i++){
			if(this.current_map.charAt(i) == '\n'){
				y+=(getHeight()/map_height); x=0;
				continue;
			}
			g.drawImage( this.char_to_img.get( this.current_map.charAt(i) ) , x , y ,(getWidth()/map_width),(getHeight()/map_height),null);
			x+=(getWidth()/map_width);
		}
		if(lost)
			lostMessage(g);
		else if(won)
			winMessage(g);
	}

	private void lostMessage(Graphics g){
		g.drawImage(lost_game, (getWidth()/4),(getHeight()/4), (getWidth()/2), (getHeight()/2),null);
	}
	private void winMessage(Graphics g){
		g.drawImage(won_game, (getWidth()/4),(getHeight()/4), (getWidth()/2), (getHeight()/2),null);
	}
	

	private void loadGuardImage(){
		try {                
			this.char_to_img.put(new Character('G') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Guard.png")));
		} catch (IOException ex) {
			System.out.println("Error reading guard image!");
		}
	}
	private void loadLostImage(){
		try {                
			lost_game=ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/lost_message.png"));
		} catch (IOException ex) {
			System.out.println("Error reading lost image!");
		}
	}
	private void loadWinImage(){
		try {                
			won_game=ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/won_message.png"));
		} catch (IOException ex) {
			System.out.println("Error reading lost image!");
		}
	}
	private void loadGuard_stunImage(){
		try {                
			this.char_to_img.put(new Character('g') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Guard_stun.png")));
		} catch (IOException ex) {
			System.out.println("Error reading guard_Stun image!");
		}
	}	
	private void loadHeroImage(){
		try {                
			this.char_to_img.put(new Character('H') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Hero.png")));
		} catch (IOException ex) {
			System.out.println("Error reading hero image!");
		}
	}
	private void loadHero_key(){
		try {                
			this.char_to_img.put(new Character('K') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Hero_with_key.png")));
		} catch (IOException ex) {
			System.out.println("Error reading hero_with_key image!");
		}
	}
	private void loadHero_armedImage(){
		try {                
			this.char_to_img.put(new Character('A') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Hero_armed.png")));
		} catch (IOException ex) {
			System.out.println("Error reading hero image!");
		}
	}
	private void loadFire(){
		try {                
			this.char_to_img.put(new Character('*') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Fire.png")));
		} catch (IOException ex) {
			System.out.println("Error reading hero image!");
		}
	}
	private void loadlLeverFire(){
		try {                
			this.char_to_img.put(new Character('$') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Lever_fire.png")));
		} catch (IOException ex) {
			System.out.println("Error reading Lever_fire image!");
		}
	}
	private void loadOgreImage(){
		try {                
			this.char_to_img.put(new Character('O') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Ogre.png")));
		} catch (IOException ex) {
			System.out.println("Error reading ogre image!");
		}
	}
	private void loadOgre_stunedImage(){
		try {                
			this.char_to_img.put(new Character('8') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Ogre_stuned.png")));
		} catch (IOException ex) {
			System.out.println("Error reading ogre image!");
		}
	}
	private void loadWallImage(){
		try {                
			this.char_to_img.put(new Character('X') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Wall.png")));
		} catch (IOException ex) {
			System.out.println("Error reading wall image!");
		}
	}
	private void loadFloorImage(){
		try {                
			this.char_to_img.put(new Character(' ') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Floor.png")));
		} catch (IOException ex) {
			System.out.println("Error reading floor image!");
		}
	}
	private void loadDoorImage(){
		try {                
			this.char_to_img.put(new Character('I') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Door.png")));
		} catch (IOException ex) {
			System.out.println("Error reading door image!");
		}
	}
	private void loadStairImage(){
		try {                
			this.char_to_img.put(new Character('S') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Floor.png")));
		} catch (IOException ex) {
			System.out.println("Error reading stair image!");
		}
	}
	private void loadLeverImage(){
		try {                
			this.char_to_img.put(new Character('k') ,ImageIO.read(new File(System.getProperty("user.dir")+"/imgs/Lever.png")));
		} catch (IOException ex) {
			System.out.println("Error reading lever image!");
		}
	}
}