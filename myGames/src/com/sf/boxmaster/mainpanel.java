package com.sf.boxmaster;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

class mainpanel extends JPanel implements KeyListener {
	int max = 50;
	int[][] map, maptmp;
	int manX, manY, boxnum;
	Image[] myImage;
	Readmap Levelmap;
	Readmap Levelmaptmp;
	int len = 30;
	public int level = 1;
	Stack mystack = new Stack();

	mainpanel() {
		setBounds(15, 50, 600, 600);
		setBackground(Color.white);
		addKeyListener(this);
		myImage = new Image[10];
		for (int i = 0; i < 10; i++) {
			myImage[i] = Toolkit.getDefaultToolkit().getImage(
					"pic\\" + i + ".gif");
		}

		setVisible(true);
	}

	void Tuixiangzi(int i) {
		Levelmap = new Readmap(i);
		Levelmaptmp = new Readmap(i);
		map = Levelmap.getmap();
		manX = Levelmap.getmanX();
		manY = Levelmap.getmanY();
		maptmp = Levelmaptmp.getmap();
		repaint();
	}

	int maxlevel() {
		return max;
	}

	public void paint(Graphics g) {
		for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				g.drawImage(myImage[map[j][i]], i * len, j * len, this);
			}
		g.setColor(new Color(0, 0, 0));
		g.setFont(new Font("楷体_2312", Font.BOLD, 30));
		g.drawString("现在是第", 150, 40);
		g.drawString(String.valueOf(level), 310, 40);
		g.drawString("关", 360, 40);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			moveup();
		}
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			movedown();
		}
		if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			moveleft();
		}
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			moveright();
		}
		if (iswin()) {
			if (level == max) {
				JOptionPane.showMessageDialog(this, "恭喜您通过最后一关！！！");
			} else {
				String msg = "恭喜您通过第" + level + "关!!!\n是否要进入下一关？";
				int type = JOptionPane.YES_NO_OPTION;
				String title = "过关";
				int choice = 0;
				choice = JOptionPane.showConfirmDialog(null, msg, title, type);
				if (choice == 1)
					System.exit(0);
				else if (choice == 0) {
					level++;
					Tuixiangzi(level);
				}
			}
			mystack.removeAllElements();
		}
	}

	public void keyTyped(KeyEvent e) {
	}

	public void keyReleased(KeyEvent e) {
	}

	boolean isMystackEmpty() {
		return mystack.isEmpty();
	}

	int back() {
		return (Integer) mystack.pop();
	}

	void remove() {
		mystack.removeAllElements();
	}

	void moveup() {
		if (map[manY - 1][manX] == 2 || map[manY - 1][manX] == 4) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
				map[manY][manX] = 4;
			else
				map[manY][manX] = 2;
			map[manY - 1][manX] = 8;
			repaint();
			manY--;
			mystack.push(10);
		} else if (map[manY - 1][manX] == 3) {
			if (map[manY - 2][manX] == 4) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY - 1][manX] = 8;
				map[manY - 2][manX] = 9;
				repaint();
				manY--;
				mystack.push(11);
			} else if (map[manY - 2][manX] == 2) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY - 1][manX] = 8;
				map[manY - 2][manX] = 3;
				repaint();
				manY--;
				mystack.push(11);
			} else {
				map[manY][manX] = 8;
				repaint();
			}
		} else if (map[manY - 1][manX] == 9) {
			if (map[manY - 2][manX] == 4) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY - 1][manX] = 8;
				map[manY - 2][manX] = 9;
				repaint();
				manY--;
				mystack.push(11);
			} else if (map[manY - 2][manX] == 2) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY - 1][manX] = 8;
				map[manY - 2][manX] = 3;
				repaint();
				manY--;
				mystack.push(11);
			} else {
				map[manY][manX] = 8;
				repaint();
			}
		}
		if (map[manY - 1][manX] == 1) {
			map[manY][manX] = 8;
			repaint();
		}
	}

	void backup(int t) {
		int n = t;
		if (n == 10) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9) {
				map[manY][manX] = 4;
			} else
				map[manY][manX] = 2;
		} else if (n == 11) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9) {
				map[manY][manX] = 9;
			} else
				map[manY][manX] = 3;
			if (maptmp[manY - 1][manX] == 4 || maptmp[manY - 1][manX] == 9) {
				map[manY - 1][manX] = 4;
			} else
				map[manY - 1][manX] = 2;
		}
		map[manY + 1][manX] = 8;
		repaint();
		manY++;
	}

	void movedown() {
		if (map[manY + 1][manX] == 2 || map[manY + 1][manX] == 4) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
				map[manY][manX] = 4;
			else
				map[manY][manX] = 2;
			map[manY + 1][manX] = 5;
			repaint();
			manY++;
			mystack.push(20);
		} else if (map[manY + 1][manX] == 3) {
			if (map[manY + 2][manX] == 4) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY + 1][manX] = 5;
				map[manY + 2][manX] = 9;
				repaint();
				manY++;
				mystack.push(21);
			} else if (map[manY + 2][manX] == 2) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY + 1][manX] = 5;
				map[manY + 2][manX] = 3;
				repaint();
				manY++;
				mystack.push(21);
			} else {
				map[manY][manX] = 5;
				repaint();
			}
		} else if (map[manY + 1][manX] == 9) {
			if (map[manY + 2][manX] == 4) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY + 1][manX] = 5;
				map[manY + 2][manX] = 9;
				repaint();
				manY++;
				mystack.push(21);
			} else if (map[manY + 2][manX] == 2) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY + 1][manX] = 5;
				map[manY + 2][manX] = 3;
				repaint();
				manY++;
				mystack.push(21);
			} else {
				map[manY][manX] = 5;
				repaint();
			}
		} else if (map[manY + 1][manX] == 1) {
			map[manY][manX] = 5;
			repaint();
		}
	}

	void backdown(int t) {
		int n = t;
		if (n == 20) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9) {
				map[manY][manX] = 4;
			} else
				map[manY][manX] = 2;
		} else if (n == 21) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9) {
				map[manY][manX] = 9;
			} else
				map[manY][manX] = 3;
			if (maptmp[manY + 1][manX] == 4 || maptmp[manY + 1][manX] == 9) {
				map[manY + 1][manX] = 4;
			} else
				map[manY + 1][manX] = 2;
		}
		map[manY - 1][manX] = 5;
		repaint();
		manY--;
	}

	void moveleft() {
		if (map[manY][manX - 1] == 2 || map[manY][manX - 1] == 4) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
				map[manY][manX] = 4;
			else
				map[manY][manX] = 2;
			map[manY][manX - 1] = 6;
			repaint();
			manX--;
			mystack.push(30);
		} else if (map[manY][manX - 1] == 3) {
			if (map[manY][manX - 2] == 4) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX - 1] = 6;
				map[manY][manX - 2] = 9;
				repaint();
				manX--;
				mystack.push(31);
			} else if (map[manY][manX - 2] == 2) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX - 1] = 6;
				map[manY][manX - 2] = 3;
				repaint();
				manX--;
				mystack.push(31);
			} else {
				map[manY][manX] = 6;
				repaint();
			}
		} else if (map[manY][manX - 1] == 9) {
			if (map[manY][manX - 2] == 4) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX - 1] = 6;
				map[manY][manX - 2] = 9;
				repaint();
				manX--;
				mystack.push(31);
			} else if (map[manY][manX - 2] == 2) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX - 1] = 6;
				map[manY][manX - 2] = 3;
				repaint();
				manX--;
				mystack.push(31);
			} else {
				map[manY][manX] = 6;
				repaint();
			}
		} else if (map[manY][manX - 1] == 1) {
			map[manY][manX] = 6;
			repaint();
		}
	}

	void backleft(int t) {
		int n = t;
		if (n == 30) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9) {
				map[manY][manX] = 4;
			} else
				map[manY][manX] = 2;
		} else if (n == 31) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9) {
				map[manY][manX] = 9;
			} else
				map[manY][manX] = 3;
			if (maptmp[manY][manX - 1] == 4 || maptmp[manY][manX - 1] == 9) {
				map[manY][manX - 1] = 4;
			} else
				map[manY][manX - 1] = 2;
		}
		map[manY][manX + 1] = 6;
		repaint();
		manX++;
	}

	void moveright() {
		if (map[manY][manX + 1] == 2 || map[manY][manX + 1] == 4) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
				map[manY][manX] = 4;
			else
				map[manY][manX] = 2;
			map[manY][manX + 1] = 7;
			repaint();
			manX++;
			mystack.push(40);
		} else if (map[manY][manX + 1] == 3) {
			if (map[manY][manX + 2] == 4) {
				if (maptmp[manY][manX] == 4)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX + 1] = 7;
				map[manY][manX + 2] = 9;
				repaint();
				manX++;
				mystack.push(41);
			} else if (map[manY][manX + 2] == 2) {
				if (maptmp[manY][manX] == 4)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX + 1] = 7;
				map[manY][manX + 2] = 3;
				repaint();
				manX++;
				mystack.push(41);
			} else {
				map[manY][manX] = 7;
				repaint();
			}
		} else if (map[manY][manX + 1] == 9) {
			if (map[manY][manX + 2] == 4) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX + 1] = 7;
				map[manY][manX + 2] = 9;
				repaint();
				manX++;
				mystack.push(41);
			} else if (map[manY][manX + 2] == 2) {
				if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9)
					map[manY][manX] = 4;
				else
					map[manY][manX] = 2;
				map[manY][manX + 1] = 7;
				map[manY][manX + 2] = 3;
				repaint();
				manX++;
				mystack.push(41);
			} else {
				map[manY][manX] = 7;
				repaint();
			}
		} else if (map[manY][manX + 1] == 1) {
			map[manY][manX] = 7;
			repaint();
		}
	}

	void backright(int t) {
		int n = t;
		if (n == 40) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9) {
				map[manY][manX] = 4;
			} else
				map[manY][manX] = 2;
		} else if (n == 41) {
			if (maptmp[manY][manX] == 4 || maptmp[manY][manX] == 9) {
				map[manY][manX] = 9;
			} else
				map[manY][manX] = 3;
			if (maptmp[manY][manX + 1] == 4 || maptmp[manY][manX + 1] == 9) {
				map[manY][manX + 1] = 4;
			} else
				map[manY][manX + 1] = 2;
		}
		map[manY][manX - 1] = 7;
		repaint();
		manX--;
	}

	boolean iswin() {
		boolean num = false;
		out: for (int i = 0; i < 20; i++)
			for (int j = 0; j < 20; j++) {
				if (maptmp[i][j] == 4 || maptmp[i][j] == 9)
					if (map[i][j] == 9)
						num = true;
					else {
						num = false;
						break out;
					}
			}
		return num;
	}
}
