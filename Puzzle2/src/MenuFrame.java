import java.io.File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.ButtonGroup;
import javax.swing.KeyStroke;

public abstract class MenuFrame	extends JFrame
{
	public final static int heightTitle =30;
	public final static int widthTitle = 5;
	public final static int heightMenu = 25;

	private JMenu [] m = {
		new JMenu("开始"),
		new JMenu("选项"),
		new JMenu("关于我们")
	};
	private JMenu [] mm = {
		new JMenu("难度"),
		new JMenu("图片")
	};
	private boolean update;
	private int type;
	private String filename;
	protected ButtonGroup bgrp = new ButtonGroup();
	protected ButtonGroup fgrp = new ButtonGroup();

	public MenuFrame()
	{
		super("拼图游戏");

		addMenu();

		this.setResizable(false);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setSize(widthTitle, heightTitle+heightMenu);
		this.setLocation(
			this.getToolkit().getScreenSize().width/3 - this.getWidth()/3,
			this.getToolkit().getScreenSize().height/3- this.getHeight()/3
		);
	}

	private void addMenu()
	{
		JMenuBar mBar = new JMenuBar();
		this.setJMenuBar(mBar);

		mBar.add(m[0]);
		mBar.add(m[1]);
		mBar.add(m[2]);

		m[0].setMnemonic('B');
		m[1].setMnemonic('O');
		m[2].setMnemonic('A');

		initMenuBegin();
		initMenuOption();
		initMenuAbout();
		update = false;
	}

	public abstract void menuNewClick();
	public abstract void menuGradesClick();
	public abstract void menuShowClick();
	public abstract void menuExitClick();

	private void menuLevelClick(String nm)
	{
		type = 3;
		if (nm.equals("困难"))
			type = 0;
		else if (nm.equals("正常"))
			type = 1;
		else if (nm.equals("简单"))
			type = 2;
	}

	private void menuBackgroundClick(String nm)
	{
		filename = nm;
	}

	public abstract void menuHelpClick();
	public abstract void menuAboutClick();

	public int getTypeE()
	{
		return type;
	}

	public String getFilename()
	{
		return filename;
	}

	private void initMenuBegin()
	{
		JMenuItem [] mI = {
			new JMenuItem("新游戏"),
			new JMenuItem("记录"),
			new JMenuItem("离开")
		};

		mI[0].setMnemonic('N');
		mI[1].setMnemonic('G');
		mI[2].setMnemonic('E');

		mI[0].setAccelerator(KeyStroke.getKeyStroke("ctrl N"));
		mI[2].setAccelerator(KeyStroke.getKeyStroke("ctrl W"));

		mI[0].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					menuNewClick();
				}
			}
		);
		mI[1].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					menuGradesClick();
				}
			}
		);
		mI[2].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					menuExitClick();
				}
			}
		);

		m[0].add(mI[0]);
		m[0].add(mI[1]);
		m[0].add(mI[2]);
		m[0].insertSeparator(2);
	}

	private void initMenuOption()
	{

		mm[0].setMnemonic('L');
		mm[1].setMnemonic('B');
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
		m[1].add(mm[0]);
		m[1].add(mm[1]);

		initMenuLevel();
		initMenuBackground();
	}

	private void initMenuLevel()
	{
		JRadioButtonMenuItem [] mi = {
			new JRadioButtonMenuItem("困难"),
			new JRadioButtonMenuItem("正常"),
			new JRadioButtonMenuItem("简单")
		};

		for (int i = 0; i < 3; i++)
		{
			mi[i].addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JRadioButtonMenuItem mi = (JRadioButtonMenuItem)e.getSource();
						menuLevelClick(mi.getText());
					}
				}
			);
			bgrp.add(mi[i]);
			mm[0].add(mi[i]);
		}
		mi[1].setSelected(true);
		menuLevelClick(mi[1].getText());
	}

	public void initMenuBackground()
	{
		mm[1].removeAll();
		File bkg = new File(Arg.path);
		if (!bkg.exists())
			return;

		File [] list = bkg.listFiles();
		JRadioButtonMenuItem mi;
		int j = 0;
		for (int i = 0; i < list.length; i++)
		{
			if (!list[i].isFile() || list[i].isHidden())
				continue;
			mi = new JRadioButtonMenuItem(list[i].getName());
			mi.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e)
					{
						JRadioButtonMenuItem mi = (JRadioButtonMenuItem)e.getSource();
						menuBackgroundClick(mi.getText());
					}
				}
			);
			fgrp.add(mi);
			mm[1].add(mi);
			mi.setSelected(j == 0);
			if (j == 0)
				menuBackgroundClick(mi.getText());
			j++;
		}
	}

	private void initMenuAbout()
	{
		JMenuItem [] mI = {
			new JMenuItem("帮助"),
			new JMenuItem("关于我们")
		};

		mI[0].setMnemonic('H');
		mI[1].setMnemonic('A');

		mI[0].setAccelerator(KeyStroke.getKeyStroke("F1"));

		mI[0].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					menuHelpClick();
				}
			}
		);
		mI[1].addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					menuAboutClick();
				}
			}
		);

		m[2].add(mI[0]);
		m[2].add(mI[1]);
	}

	public void updateMenuBegin()
	{
		update = !update;
		if (!update)
		{
			m[0].remove(2);
			return;
		}

		JMenuItem mi = new JMenuItem("Show(S)");
		mi.setMnemonic('S');
		mi.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					menuShowClick();
				}
			}
		);
		m[0].insert(mi, 2);
	}
}
