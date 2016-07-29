import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Renderer extends Applet implements Runnable {
	static int[][] tilemap;
	static int rows, columns;
	Thread animator;
	int frame;
	int delay;
	
	@Override
	public void init() {
		setSize(800, 480);
		setBackground(Color.BLACK);
		delay = 100;
	}
	
	@Override
	public void start() {
		animator = new Thread(this);
		animator.start();
	}
	
	@Override
	public void stop() {
		animator = null;
	}
	
	@Override
	public void run() {
		long startTime = System.currentTimeMillis();
		
		// Animation Loop
		while (Thread.currentThread() == animator) {
			frame++;
			createTilemap();
			repaint();
			
			try {
				startTime += delay;
				Thread.sleep(Math.max(0,  startTime-System.currentTimeMillis()));
			} catch (InterruptedException e) {
				break;
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				int mod_i = 16 * i;
				int mod_j = 16 * j;
				
				switch (tilemap[i][j]) {
				case 0: 
					g.setColor(Color.RED);
					g.fillRect(mod_i, mod_j, 16, 16);
					break;
				case 1:
					g.setColor(Color.BLUE);
					g.fillRect(mod_i,  mod_j, 16, 16);
					break;
				case 2:
					g.setColor(Color.YELLOW);
					g.fillRect(mod_i, mod_j, 16, 16);
					break;
				case 3:
					g.setColor(Color.WHITE);
					g.fillRect(mod_i, mod_j, 16, 16);
					break;
				case 4:
					g.setColor(Color.GREEN);
					g.fillRect(mod_i, mod_j, 16, 16);
					break;
				}
			}
		}
	}

	private void createTilemap() {
		tilemap = new int[50][30];
		rows = tilemap.length;
		columns = tilemap[49].length;
		
		Random r = new Random();
		
		for (int i = 0; i < rows; i++){
			for (int j = 0; j < columns; j++) {
				tilemap[i][j] = r.nextInt(5);
			}
		}
	}
}
