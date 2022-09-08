package com.windhide.frame;

import com.windhide.util.PlayUtil;
import com.windhide.util.StaticUtil;
import com.windhide.util.TextMusicScoreUtil;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.stream.Collectors;

public class MainFrame {

	private JFrame frame;
	private JTextField searchTextField;
	private JButton playButton;
	private JButton stopButton;
	private JTable table;
	private JButton changeTapKey;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Runnable runnable = new Runnable() {
			@Override
			public void run() {
				new MainFrame();
			}
		};
		runnable.run();
	}

	/**
	 * Create the application.
	 */
	public MainFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("").getPath() + "images/icon.jpg"));
		frame.setTitle("小星弹琴软件");
		frame.setBounds(100, 100, 809, 626);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 54, 535, 509);
		frame.getContentPane().add(scrollPane);

		table = new JTable();
		reloadTable(table, null);
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("搜索歌曲:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 10, 87, 34);
		frame.getContentPane().add(lblNewLabel);

		searchTextField = new JTextField();
		searchTextField.setBounds(107, 10, 448, 28);
		frame.getContentPane().add(searchTextField);
		searchTextField.setColumns(10);


		playButton = new JButton("播放");
		playButton.setFont(new Font("宋体", Font.PLAIN, 17));
		playButton.setBounds(565, 16, 218, 50);
		playButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					String musicName = table.getValueAt(table.getSelectedRow(), 0) + ".txt";
					PlayUtil.textMusicPlay(musicName, StaticUtil.keyTap);
				} catch (Exception exception) {
					JOptionPane.showMessageDialog(null, "请先选择歌曲", "错误", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		frame.getContentPane().add(playButton);

		stopButton = new JButton("停止");
		stopButton.setFont(new Font("宋体", Font.PLAIN, 17));
		stopButton.setBounds(565, 76, 218, 50);
		frame.getContentPane().add(stopButton);

		changeTapKey = new JButton("改键位");
		changeTapKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new KeySetFrame();
			}
		});
		changeTapKey.setFont(new Font("宋体", Font.PLAIN, 17));
		changeTapKey.setBounds(565, 136, 218, 50);
		frame.getContentPane().add(changeTapKey);

		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				reloadTable(table, searchTextField.getText());
			}
		});
		frame.setVisible(true);
	}

	public void reloadTable(JTable table, String searchSongName) {
		TextMusicScoreUtil textMusicScoreUtil = new TextMusicScoreUtil();
		System.out.println(searchSongName);
		List<String> songNameList = null;
		if (searchSongName != null && searchSongName != "") {
			songNameList = textMusicScoreUtil.FileNameList().stream().filter(s -> s.contains(searchSongName)).collect(Collectors.toList());
		} else {
			songNameList = textMusicScoreUtil.FileNameList();
		}
		Object[][] dataList = new Object[songNameList.size()][1];
		for (int i = 0; i < songNameList.size(); i++) {
			dataList[i][0] = songNameList.get(i).replace(".txt", "");
		}
		DefaultTableModel data = new DefaultTableModel(dataList, new String[]{"歌名"});
		table.setModel(data);
	}
}
