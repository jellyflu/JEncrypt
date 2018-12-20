package com.tingcream.javaEncrypt.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import com.tingcream.javaEncrypt.model.DESedeHelper;
import com.tingcream.javaEncrypt.util.FileUtils;

import java.awt.Font;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;

/**
 * JEncrypt 3des加解密工具
 * @author jelly
 */
public class JEncrypFrame extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  DESedeHelper desedeHelper=new DESedeHelper();
	
//	private String opendir;//最近打开的目录
//	private String savedir;//最近保存的目录
	
 	private  String  recentDir;//最近访问的目录 （打开、保存文件）
	
	private JPanel contentPane;
	private JTextField textField_key;
	private JLabel label2;
	private JLabel label3;
	private JButton button2;
	private JButton button3;
	private JButton button4;
	private JButton button5;
	private JButton button6;
	private JButton button7;
	private JButton button_encrypt;
	private JButton button_decrypt; 
	private JScrollPane scrollPane_src;
	private JTextArea textArea_src;
	private JScrollPane scrollPane_cipher;
	private JTextArea textArea_cipher;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JEncrypFrame frame = new JEncrypFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);//主窗体居中
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JEncrypFrame() {
		setFont(new Font("微软雅黑", Font.PLAIN, 20));
		setResizable(false);
		setTitle("JEncrypt加解密工具(3des)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 613);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JLabel label1 = new JLabel("密钥key:");
		label1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		textField_key = new JTextField();
		textField_key.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		textField_key.setColumns(10);
		
		JButton button1 = new JButton("清空");
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 textField_key.setText("");//清空文本框
			}
		});
		button1.setIcon(new ImageIcon(JEncrypFrame.class.getResource("/com/tingcream/javaEncrypt/img/editclear_24px.png")));
		button1.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		label2 = new JLabel("原     文:");
		label2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		label3 = new JLabel("密     文:");
		label3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		button2 = new JButton("打开");
		button2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile(textArea_src);
			}
		});
		button2.setIcon(new ImageIcon(JEncrypFrame.class.getResource("/com/tingcream/javaEncrypt/img/open_24px.png")));
		button2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		button3 = new JButton("保存");
		button3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile(textArea_src);
			}
		});
		button3.setIcon(new ImageIcon(JEncrypFrame.class.getResource("/com/tingcream/javaEncrypt/img/save_24px.png")));
		button3.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		button4 = new JButton("清空");
		button4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_src.setText("");//清空文本域  
			}
		});
		button4.setIcon(new ImageIcon(JEncrypFrame.class.getResource("/com/tingcream/javaEncrypt/img/editclear_24px.png")));
		button4.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		button5 = new JButton("打开");
		button5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openFile(textArea_cipher);
			}
		});
		button5.setIcon(new ImageIcon(JEncrypFrame.class.getResource("/com/tingcream/javaEncrypt/img/open_24px.png")));
		button5.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		button6 = new JButton("保存");
		button6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveFile(textArea_cipher);
			}
		});
		button6.setIcon(new ImageIcon(JEncrypFrame.class.getResource("/com/tingcream/javaEncrypt/img/save_24px.png")));
		button6.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		button7 = new JButton("清空");
		button7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textArea_cipher.setText("");//清空文本域  密文
			}
		});
		button7.setIcon(new ImageIcon(JEncrypFrame.class.getResource("/com/tingcream/javaEncrypt/img/editclear_24px.png")));
		button7.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		button_encrypt = new JButton("加密↓");
		button_encrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				encrypt(e);
			}
		});
	
		button_encrypt.setIcon(new ImageIcon(JEncrypFrame.class.getResource("/com/tingcream/javaEncrypt/img/encrypt_3_24px.png")));
		button_encrypt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		button_decrypt = new JButton("解密↑");
		button_decrypt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				decrypt(e);
			}
		});
		button_decrypt.setIcon(new ImageIcon(JEncrypFrame.class.getResource("/com/tingcream/javaEncrypt/img/decrypted_24px.png")));
		button_decrypt.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		
		scrollPane_src = new JScrollPane();
		
		scrollPane_cipher = new JScrollPane();
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING, false)
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(label1)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(textField_key))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addComponent(label2, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(ComponentPlacement.UNRELATED)
								.addComponent(scrollPane_src, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE))
							.addGroup(gl_contentPane.createSequentialGroup()
								.addGap(94)
								.addComponent(button_encrypt)
								.addGap(18)
								.addComponent(button_decrypt)))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(label3, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(scrollPane_cipher, GroupLayout.PREFERRED_SIZE, 476, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.TRAILING, false)
						.addComponent(button7, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button6, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button4, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button3, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button2, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(button1, GroupLayout.DEFAULT_SIZE, 121, Short.MAX_VALUE)
						.addComponent(button5, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(33))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
						.addComponent(label1)
						.addComponent(textField_key, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(button1))
					.addGap(39)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
								.addComponent(label2, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_contentPane.createSequentialGroup()
									.addComponent(button2, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
									.addPreferredGap(ComponentPlacement.UNRELATED)
									.addComponent(button3)))
							.addPreferredGap(ComponentPlacement.UNRELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(button4)
							.addGap(97)
							.addComponent(button5)
							.addGap(18)
							.addComponent(button6, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(button7, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
							.addGap(124))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(scrollPane_src, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(button_encrypt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE)
								.addComponent(button_decrypt, GroupLayout.PREFERRED_SIZE, 37, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(scrollPane_cipher, GroupLayout.PREFERRED_SIZE, 163, GroupLayout.PREFERRED_SIZE)
								.addComponent(label3, GroupLayout.PREFERRED_SIZE, 28, GroupLayout.PREFERRED_SIZE))
							.addContainerGap())))
		);
		
		textArea_cipher = new JTextArea();
		textArea_cipher.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		scrollPane_cipher.setViewportView(textArea_cipher);
		
		textArea_src = new JTextArea();
		textArea_src.setFont(new Font("微软雅黑", Font.PLAIN, 20));
		scrollPane_src.setViewportView(textArea_src);
		contentPane.setLayout(gl_contentPane);
	}
	
	
	/**
	 * 加密按钮 点击
	 * @author jelly
	 */
	protected void encrypt(ActionEvent e) {
		 String key= this.textField_key.getText();
		 String src=this.textArea_src.getText();
		 if(key==null||key.trim().equals("")) {
			//提示消息
			 JOptionPane.showMessageDialog(null, "密钥key不能为空!"); 
			 return ;
		 }
		 if(src==null||src.trim().equals("")) {
			 //提示消息
			 JOptionPane.showMessageDialog(null, "原文不能为空!"); 
			 return ;
		 }
		 try {
			 
		 String cipherText=	desedeHelper.encode3Des(key, src);//加密得到密文
		 this.textArea_cipher.setText(cipherText);
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
		}
		
	}
	/**
	 * 解密按钮 点击
	 * @author jelly
	 */
	protected void decrypt(ActionEvent e) {
		
		String key= this.textField_key.getText();
		 String cipher=this.textArea_cipher.getText();
		 if(key==null||key.trim().equals("")) {
			//提示消息
			 JOptionPane.showMessageDialog(null, "密钥key不能为空!"); 
			 return ;
		 }
		 if(cipher==null||cipher.trim().equals("")) {
			 //提示消息
			 JOptionPane.showMessageDialog(null, "密文不能为空!"); 
			 return ;
		 }
		 try {
		 String src=	desedeHelper.decode3Des(key, cipher);//解密得到原文
		 this.textArea_src.setText(src);
		} catch (Exception e1) {
			e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.getMessage(), "错误", JOptionPane.ERROR_MESSAGE);
		}
		
	}

	/**
	 * 文件打开
	 * @param textArea 文本域
	 */
	void openFile(JTextArea textArea ){//打开文件
		 InputStreamReader  reader = null;
		try {
		 JFileChooser wjopen=null;
		 if(recentDir!=null&&recentDir.trim().length()>0){
			 wjopen=new JFileChooser(recentDir);
		 }else{
			 wjopen=new JFileChooser();
		 }
		wjopen.setDialogTitle("文件打开");
		wjopen.addChoosableFileFilter(new FileNameExtensionFilter("文本文件(txt)","txt"));
		wjopen.addChoosableFileFilter(new FileNameExtensionFilter("java文件(java)","java"));
		wjopen.addChoosableFileFilter(new FileNameExtensionFilter("c#文件(cs)","cs"));
		wjopen.addChoosableFileFilter(new FileNameExtensionFilter("c文件(c)","c"));
		wjopen.addChoosableFileFilter(new FileNameExtensionFilter("c++文件(cpp)","cpp"));
		wjopen.addChoosableFileFilter(new FileNameExtensionFilter("php文件(php)","php"));
		wjopen.addChoosableFileFilter(new FileNameExtensionFilter("JavaScript文件(js)","js"));
		wjopen.addChoosableFileFilter(new FileNameExtensionFilter("sql文件(sql)","sql"));
		
		wjopen.setMultiSelectionEnabled(true);
		
		wjopen.setDialogType(JFileChooser.OPEN_DIALOG);
		int value=wjopen.showOpenDialog(null);
		 wjopen.setVisible(true);
		 if(value==JFileChooser.APPROVE_OPTION){//用户点击了打开
			  File[] openfiles= wjopen.getSelectedFiles();//选择的文件
			  if(openfiles!=null){
				  
				  textArea.setText("");
				  this.recentDir=openfiles[0].getParent();
				  for(int i=0;i<openfiles.length;i++){
					   String encoding= FileUtils.getFileEncoding(openfiles[i].getAbsolutePath());
						
						  reader=new InputStreamReader(new FileInputStream(openfiles[i]), encoding);
						   char[] buf=new char[1024];
						   int len=0;
						   while((len=reader.read(buf))>0){
							   textArea.append (new String(buf,0,len));
						   }
				   }
			  }
		 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		 finally {//关流操作
            try {
                if (reader != null) {
               	 reader.close();
                }
            }
            catch (Exception e) {
                  System.out.println("InputStreamReader close IOException" + e.getStackTrace());
            }
      }
	}

	/**
	 * 文件保存
	 * @param textArea  文本域
	 */
	void saveFile(JTextArea textArea){//保存到文件
		BufferedWriter writer=null;
		
		try {
			 JFileChooser wjsave=null;
			 if(recentDir!=null&&recentDir.trim().length()>0){
				 wjsave=new JFileChooser(recentDir);
			 }else{
				 wjsave=new JFileChooser();
			 }
			 wjsave.setDialogTitle("保存到文件");
				
				wjsave.addChoosableFileFilter(new FileNameExtensionFilter("文本文件(txt)","txt"));
				wjsave.addChoosableFileFilter(new FileNameExtensionFilter("java文件(java)","java"));
				wjsave.addChoosableFileFilter(new FileNameExtensionFilter("c#文件(cs)","cs"));
				wjsave.addChoosableFileFilter(new FileNameExtensionFilter("c文件(c)","c"));
				wjsave.addChoosableFileFilter(new FileNameExtensionFilter("c++文件(cpp)","cpp"));
				wjsave.addChoosableFileFilter(new FileNameExtensionFilter("php文件(php)","php"));
				wjsave.addChoosableFileFilter(new FileNameExtensionFilter("JavaScript文件(js)","js"));
				wjsave.addChoosableFileFilter(new FileNameExtensionFilter("sql文件(sql)","sql"));
				
			 
			 wjsave.setDialogType(JFileChooser.SAVE_DIALOG);
			int value= wjsave.showSaveDialog(null);
			 wjsave.setVisible(true);
			 if(value==JFileChooser.APPROVE_OPTION){//用户点击了保存
				   File savefile= wjsave.getSelectedFile();// 保存的文件
				   recentDir= savefile.getParent();//将文件目录保存到成员变量
				  writer = new BufferedWriter(new FileWriter(savefile));
				  String result= textArea.getText();
				  String[] temp=result.split("[\\r\\n]");
				  for (int i = 0; i < temp.length; i++) {
					  writer.write(temp[i]);
					  writer.write("\r\n");
				  }
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally {//关流操作
           try {
               if (writer != null) {
               	writer.close();
               }
           }
           catch (Exception e) {
                 System.out.println("BufferedWriter close IOException" + e.getStackTrace());
           }
     }
	}
	
}
