//a simple new year's countdown animation with sound

//created by nathan mccloud

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.FontFormatException;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.swing.JSlider;


import javax.swing.Timer;

@SuppressWarnings({ "serial", "unused" })
public class countDown extends JComponent implements KeyListener {
		//instance variables
		protected Timer timer;
		protected Timer timer2;
		protected Timer timer3;
		//sound variables
		private static AudioInputStream clipNameAIS;
		private static Clip clipNameClip;
		//colors
		protected Color gold= new Color(212,175,55);
		//counters
		private int timeCount=10;
		private int millitimeCount=100;
		private int colorCount=1;		
		//random var
		private Random rand=new Random();

	
	@Override
	//lines
	public void paintComponent(Graphics g){
		
		g.setColor(gold);
		
		try {
			Font f=Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/LLDCL___.ttf"));//font created by Markus Schröppel @www.dafont.com/lldcl.font
			g.setFont(f.deriveFont(Font.PLAIN, 200f));} catch (FontFormatException | IOException ex) {
             ex.printStackTrace();
         }
		g.drawString(":"+Integer.toString(millitimeCount), 1000, 400);
		
		try {
			Font f=Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/LLDCL___.ttf"));
			g.setFont(f.deriveFont(Font.PLAIN, 500f));} catch (FontFormatException | IOException ex) {
             ex.printStackTrace();
         }
		g.drawString(Integer.toString(timeCount), 600, 600);
		if(millitimeCount==0){
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(gold);
				for(int i=0;i<=250;i++){	
				g.fillOval(0+rand.nextInt(getWidth()),0+rand.nextInt(getHeight()),10,10);
				g.drawLine(0+rand.nextInt(getWidth()),0+rand.nextInt(getHeight()),100,500);}
				
			g.setColor(Color.pink);
			for(int i=0;i<=250;i++){
			g.drawLine(0+rand.nextInt(getWidth()),0+rand.nextInt(getHeight()),100,500);
			}
			g.drawString("HAPPY",0, 400);
			try {
				Font f=Font.createFont(Font.TRUETYPE_FONT, getClass().getResourceAsStream("/LLDCL___.ttf"));
				g.setFont(f.deriveFont(Font.PLAIN, 290f));} catch (FontFormatException | IOException ex) {
	             ex.printStackTrace();
	         }
			g.drawString("NEW YEAR!", 0, 800);}
		}
		//gui
		public countDown(){
		setBackground(Color.BLACK);
		addKeyListener(this);
		setFocusable(true);

				
		//timer
		timer=new Timer(1000, new TimerCallBack());
		timer.start();

		//1/100th second timer
		timer2=new Timer(100, new TimerCallBack2());
		timer2.start();
		
		//background timer
		timer3=new Timer(100, new TimerCallBack3());
		timer3.start();
		
				}
		
		
		//timer callback
		protected class TimerCallBack implements ActionListener{
			public void actionPerformed(ActionEvent e){
				timeCount--;
				repaint();
				if(timeCount==0){
					timer.stop();
				}
			}
		}
		//timer callback 2
		protected class TimerCallBack2 implements ActionListener{
			public void actionPerformed(ActionEvent e){
				millitimeCount--;
				repaint();
				if(millitimeCount==0){
					timer2.stop();
				}
			}
			
		}		
		
		//timer callback 3
		protected class TimerCallBack3 implements ActionListener{
			public void actionPerformed(ActionEvent e){
				repaint();}
					
			}
		
		
	//main method
	public static void main(String[] args) {
		//construction of sound
			try{
				clipNameAIS = AudioSystem.getAudioInputStream(new File("24 clock countdown (10 seconds).wav"));//24 owned by 21st Century Fox, explosion sound created by Diman4ik from https://www.youtube.com/watch?v=MdO3_r6juRU 
				clipNameClip = AudioSystem.getClip();
		
				clipNameClip.open(clipNameAIS);}
			catch(Exception e){System.out.println("Failure to load sound");}
		
			//play sound file
			clipNameClip.setFramePosition(0);//plays sound from beginning
			clipNameClip.start();//plays sound once
		
		//JFrame construction
		JFrame frame = new JFrame("New Years Countdown");
		countDown canvas = new countDown();
		frame.add(canvas);
		frame.setSize(1000, 800);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		frame.setVisible(true);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

	}
		
	@Override
	public void keyPressed(KeyEvent e) {		
	}
	@Override
	public void keyReleased(KeyEvent e) {
	}
	@Override
	public void keyTyped(KeyEvent e) {
		Graphics g = getGraphics();
		char k = e.getKeyChar();
		if (k=='r'||k=='R')
		{
			//resets timer on pressing 'r'
			clipNameClip.setFramePosition(0);
			clipNameClip.start();
			timeCount=10;
			millitimeCount=100;
			timer.restart();
			timer2.restart();
		}
		else if(k=='b'||k=='B')
		{	
			//toggles gold to pink on pressing 'b'
			colorCount++;
			if(colorCount%2==0){
			gold=Color.pink;}
			else{gold=new Color(212,175,55);}
			repaint();
		}
		
	}
	}


