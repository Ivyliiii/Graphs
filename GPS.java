import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GPS {
	public final int height = 950, width = 830;
	int point_r = 20;
	int clicked_x, clicked_y;
	BufferedImage img = null;
	File myObj = new File("GPS_History");
	JLabel lb = null;
	String currentInfo = null;
	String name = "";
	String write = "";
	//Object result;
	LabeledGraph<String, Integer> graph = new LabeledGraph<String, Integer>();

	public GPS(){
		JFrame frame = new JFrame();
		frame.setSize(new Dimension(width, height));
		JPanel canvas = new JPanel() {
			public void paint(Graphics g) {
				try {
					img = ImageIO.read(new File("minecraft_map.jpg"));
				} catch (IOException e) {
					e.printStackTrace();
				}
				g.drawImage(img, 0, 0, null);
				for(LabeledGraph<String, Integer>.Vertex v : graph.vertices.values()) {
					g.setColor(Color.BLACK);
					g.fillOval(v.x, v.y, point_r, point_r);
					if(v.x >= 0 && v.y >= 0 && v.x < width-10 && v.y < height) {
						g.drawString(v.info, v.x-3, v.y-8);
					}
				}
			}
		};
		try {
			myObj.createNewFile();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		canvas.setPreferredSize(new Dimension(width, height));
		System.out.println(canvas.getX());
				
		canvas.addMouseListener(new MouseListener() {
			public void mouseClicked(MouseEvent e) {}
			public void mousePressed(MouseEvent e) {
				clicked_x = e.getX();
				clicked_y = e.getY();
				if(graph.isOn(clicked_x, clicked_y) != null) {
					System.out.println("on");
					if(currentInfo == null) {
						currentInfo = graph.isOn(clicked_x, clicked_y);
						System.out.println("new connect");
					}
					else {
						String str = graph.isOn(clicked_x, clicked_y);
						graph.connect(currentInfo, str, graph.distance(graph.vertices.get(currentInfo).x,graph.vertices.get(currentInfo).y, graph.vertices.get(str).x, graph.vertices.get(str).y));
						currentInfo = null;
						System.out.println("connected");
					}
				}
				else {
					String name = JOptionPane.showInputDialog(canvas, "Enter Location Name:");
					graph.addVertex(clicked_x, clicked_y, name);
					frame.getContentPane().repaint();
					write +=  name + " " + clicked_x + " " + clicked_y + " \n";
					writeFile();
				}
			}
			public void mouseReleased(MouseEvent e) {}
			public void mouseEntered(MouseEvent e) {}
			public void mouseExited(MouseEvent e) {}
		});

		
		canvas.setBackground(Color.BLACK);
		//canvas.add(lb);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.add(canvas);
		frame.setVisible(true);
	}
	
	public void writeFile() {
		try {
			FileWriter myWriter = new FileWriter("GPS_History");
			myWriter.write(write);
			myWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		GPS run = new GPS();
	}

}
