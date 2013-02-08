package GUI.jpanel;
//文本框
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

import javax.swing.tree.*;
import javax.swing.event.*;
import javax.swing.border.*;

/** 
 * Swing 组件测试程序 
 * 测试Swing所有组件及其相应的事件
 * @author 天翼.李 2003.4.17 晚23:14
 * @link http://www.robochina.org
 * @link robococde@etang.com
 */
public class TestJpanel extends JFrame
{ 
	/**
     * 主模块，初始化所有子模块，并设置主框架的相关属性
     */
    public TestJpanel()
    {
        // 初始化所有模块
        JTextPane textPane = new JTextPane();
        textPane.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        textPane.setText("编辑器,试着点击文本区，试着拉动分隔条。");
                    
        textPane.addMouseListener(new MouseAdapter () 
        {//点击后执行的方法
            public void mousePressed (MouseEvent e)
            {
                JTextPane textPane = (JTextPane)e.getSource();
                textPane.setText("编辑器点击命令成功");
                textPane.setText("java版本为:"+System.getProperty("java.version"));
            //    textField.setText(""+textPane.getText());
            }
        });
        
        // 设置主框架的布局
        Container c = this.getContentPane();
        // c.setLayout(new BorderLayout())
        
        c.add(textPane,BorderLayout.CENTER);
        
        // 利用无名内隐类，增加窗口事件
        this.addWindowListener(new WindowAdapter()
            {
                public void WindowClosing(WindowEvent e)
                {   
                    // 释放资源，退出程序
                    dispose();
                    System.exit(0);
                }
            });
            
        
        
        setSize(700,500);
        setTitle("文本框测试");
        // 隐藏frame的标题栏,此功暂时关闭，以方便使用window事件
        // setUndecorated(true);
        setLocation(200,150);
        show();        
    }
	public static void main(String args[])
	{

        new TestJpanel();      
	}
}
