package com.fivechess.view;

import com.fivechess.model.Chess;

import javax.management.MBeanAttributeInfo;
import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 * 棋盘页面父类
 * 初始化棋盘页面
 * @author: admin
 */
public class ChessBoard extends JPanel implements MouseMotionListener,MouseListener{
    public static final int GAME_OVER=0; //结束游戏
    public static final int DRAWCHESS=2;
    public static final int COLS=15;
    public static final int RAWS=15;//棋盘的行数和列数
    public int result=1; //结果，为0则结束游戏
    public Image whiteChess;
    //黑白棋子
    public Image chessBoardImage;
    public Image blackChess;
    //鼠标经过显示图片
    public Image position;
    //棋子的横坐标
    public int chessX;
    //棋子的纵坐标
    public int chessY;
    //鼠标坐标
    public int mousex;
    public int mousey;
    
    public JLabel timecount;
    //棋盘上隐形的坐标，每一个小区域代表一个数组元素
    public int chess[][]=new int[COLS][RAWS];
    public int clickable;

    /**
     * 构造函数，初始化棋盘的图片，初始化数组
     * @param
     */

    ChessBoard() {
    	//加载棋盘、黑棋、白棋、位置图片
        chessBoardImage = Toolkit.getDefaultToolkit().getImage("images/chessB.jpg");
        // System.out.println("chessBoardImageHeight:____" + chessBoardImage.getHeight(null));
        // System.out.println("chessBoardImageWidth:____" + chessBoardImage.getWidth(null));

        whiteChess = Toolkit.getDefaultToolkit().getImage("images/white.png");
        blackChess=Toolkit.getDefaultToolkit().getImage("images/black.png");
        position=Toolkit.getDefaultToolkit().getImage("images/position.gif");
        initArray();
        addMouseListener(this);
        addMouseMotionListener(this);
    }
    
    /**
     * 设置面板是否可以点击
     * @param clickable
     */
    public void setClickable(int clickable)
    {
        this.clickable=clickable;
    }
    /**
     * 初始化数组为BLANK
     */
    public void initArray()
    {
        for(int i=0;i<15;i++)
        {
            for(int j=0;j<15;j++)
            {
                chess[i][j]= Chess.BLANK;
            }
        }
    }
    /**
     * 从父类继承的方法，自动调用，绘画图形
     * @param g 该参数是绘制图形的句柄
     */
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        g.drawImage(chessBoardImage, 35, 35,null);
        //页面可点击，则显示预下棋框
        if(clickable==MainBoard.CAN_CLICK_INFO)  
        	this.showPosition(g);
        //对应位置显示黑棋、白棋
        for (int i = 0; i < 15; i++) {
            for (int j = 0; j < 15; j++) {
                if (chess[i][j] == Chess.BLACK) {
                    g.drawImage(blackChess, 41 + i * 35 , 41 + j * 35 , null);
                } else if (chess[i][j] == Chess.WHITE) {
                    g.drawImage(whiteChess, 41 + i * 35 , 41 + j * 35 , null);
                }
            }
        }
        // System.out.println("Debug paintComponent");
    }
    
    /**
     * 坐标图片跟随鼠标显示
     * @param g
     */
    private void showPosition(Graphics g)
    {
    	// int FrameWidth=getWidth(); //面板宽度
        // int FrameHeight=getHeight(); //面板高度

        int chessBoardImageHeight = chessBoardImage.getHeight(null);
        int chessBoardImageWidth  = chessBoardImage.getWidth(null);

        int boundr = chessBoardImageWidth - 5; 
        int boundb = chessBoardImageHeight - 5;

        int px = ((mousex - 40 < 0 ? 0 : mousex - 40) / 35) * 35 + 40;//?越界bug
        int py = ((mousey - 40 < 0 ? 0 : mousey - 40) / 35) * 35 + 40;

        if(px > boundr)
            px = boundr;
        if(py > boundb)
            py = boundb;

        // System.out.println("Debug showPosition");
        g.drawImage(position,px,py,34,34,null);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }
    /**
     * 按下鼠标时，记录鼠标的位置，并改写数组的数值，重新绘制图形
     * @param e
     */
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
