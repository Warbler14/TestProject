package com.example.view;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Magnifier  extends Frame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3118551624241127457L;
	
	
	//private Button bt1 = new Button("확대할까요");
	//private Button bt2 = new Button("축소할까요");
	private Panel p = new Panel();

	private BorderLayout bl = new BorderLayout();
	private FlowLayout fl = new FlowLayout(FlowLayout.RIGHT);

	private Image img; // 이미지관련
	private int size = 100; // 기본크기

	public Magnifier(String title) {
		super(title);

		// 로고만드는 메소드
		//Toolkit tk = Toolkit.getDefaultToolkit();
		//Image img2 = tk.getImage("nex.gif");
		//this.setIconImage(img2);

		this.init(); // 화면구성용 메소드
		this.start(); // 이벤트용 메소드

		// window의 크기 위치조정
		super.setSize(640, 480);
		/*
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		Dimension frm = super.getSize();
		int xpos = (int) (screen.getWidth() / 2 - frm.getWidth() / 2);
		int ypos = (int) (screen.getHeight() / 2 - frm.getHeight() / 2);
		super.setLocation(xpos, ypos);
		super.setResizable(false);
		*/
		super.setVisible(true);
	}
	 
	public void init() {
		img = Toolkit.getDefaultToolkit().getImage(Magnifier.class.getResource("").getPath()+"screen-capture.png");

		try {
			Robot robot = new Robot();
		 
			Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
			BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
			
			bufferedImage.getGraphics().drawImage(img, 50, 50, size, size, this);
			
		} catch (AWTException  ex) {
	        System.err.println(ex);
	    }
		
		//img = Toolkit.getDefaultToolkit().getImage("java.gif"); // 이미지객체생성

		// 버튼생성
		//this.setLayout(bl);
		//p.setLayout(fl);
		//p.add(bt1);
		//p.add(bt2);
		//this.add("North", p);

	}
	 
	public void start() {
		// window의 X버튼을 누르면 window를 종료하라
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});

		//bt1.addActionListener(this); // 이벤트
		//bt2.addActionListener(this); // 이벤트
	}

	public void paint(Graphics g) {
		//g.drawImage(img, 50, 50, size, size, this); // 위치 크기조정
		g.drawImage(img, 50, 80, 100, 100, this);
	}
	
	
	public static void getScreen () {
		try {
            Robot robot = new Robot();
 
            Rectangle rectangle = new Rectangle(Toolkit.getDefaultToolkit().getScreenSize());
            BufferedImage bufferedImage = robot.createScreenCapture(rectangle);
            
            
            File file = new File("screen-capture.png");
            boolean status = ImageIO.write(bufferedImage, "png", file);
            
            System.out.println("Screen Captured ? " + status + " File Path:- " + file.getAbsolutePath());
 
        } catch (AWTException | IOException ex) {
            System.err.println(ex);
        }
	}
		
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		if (e.getSource() == bt1) { // bt1을 누르면
			size = 150; // 이미지를 150으로 확대
		} else if (e.getSource() == bt2) { // bt2를 누르면
			size = 100; // 이미지를 100으로 축소
		}
		this.repaint(); // 다시그려라
		*/
	}

	
	
	public static void main(String[] args) {
		//getScreen () ;
		Magnifier ex = new Magnifier("test");
	}

}
