import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;

public class Puzzle extends CardFrame {
	private boolean start;
	private int fWidth = getWidth();
	private int fHeight = getHeight();
	private GameOver gOver;
	private boolean index = true;

	Puzzle() {
		start = false;
		ImageIcon icon = new ImageIcon("icon/OK.jpg");
		this.setSize(200 + fWidth, 200 + fHeight);
		setIconImage(icon.getImage());
		setVisible(true);
	}

	private void startGame() {
		if (start)
			return;
		start = true;
		updateMenuBegin();
	}

	public void endGame() {
		updateMenuBegin();
		initMenuBackground();
		start = false;
		JOptionPane.showMessageDialog(null, "Time:" + gOver.getTime() + "s\n" + "Step:" + gOver.getStep());
		Grades g = new Grades(this);
		g.set((int) gOver.getTime(), gOver.getStep());
	}

	protected void FrameLostFocus() {
		if (start && index) {
			nextCard();
			if (gOver != null)
				gOver.pause();
			index = false;
		}
	}

	protected void FrameGetFocus() {
		nextCard();
		if (gOver != null)
			gOver.pause();
		index = true;
	}

	public void menuNewClick() {
		Split sp = Split.get();
		BufferedImage[][] image;
		if (!sp.set(getFilename()) || (image = sp.divid(getTypeE())) == null) {
			JOptionPane.showMessageDialog(null, "File " + getFilename() + " not exists!\nPlease select again~");
			return;
		}

		startGame();
		this.setSize(fWidth, fHeight);
		this.setVisible(true);
		int len = Split.level[getTypeE()];
		int row = image.length;
		int cal = image[0].length;

		gOver = new GameOver(this);
		JButton[][] button = new JButton[row][cal];
		Matrix matrix = new Matrix(button, panel[0], len, gOver);
		matrix.init(image);

		this.setSize(cal * len + fWidth, row * len + fHeight);
		this.setVisible(true);
	}

	public void menuGradesClick() {
		Grades g = new Grades(this);
		g.show();
	}

	public void menuShowClick() {
		new ShowImage(getFilename());
	}

	public void menuExitClick() {
		System.exit(0);
	}

	public void menuHelpClick() {

		String help1 = "这么简单你都不会玩吗？";
		JOptionPane.showMessageDialog(null, help1);
	}

	public void menuAboutClick() {
		String version = "版本: 2.0 \n";
		String author = "作者:  刘悦小组 \n";
		String email = "E-mail: 571735109@qq.com";
		JOptionPane.showMessageDialog(null, version + author + email);
	}

	public static void main(String[] argv) {
		new Puzzle();
	}
}
