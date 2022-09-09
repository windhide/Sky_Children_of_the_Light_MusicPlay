package com.windhide.frame;

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
	private JButton playButton, stopButton;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton changeTapKey;
	private JProgressBar playBar;
	private JLabel playBarMusicName, lblNewLabel;

	public MainFrame() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(this.getClass().getClassLoader().getResource("").getPath() + "images/icon.jpg"));
		frame.setTitle("小星弹琴软件");
		frame.setBounds(100, 100, 661, 512);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false); // 禁止调整窗口大小

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 54, 400, 411);
		frame.getContentPane().add(scrollPane);

		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false; // 禁止编辑表格
			}
		};
		reloadTable(table, null);
		scrollPane.setViewportView(table);

		lblNewLabel = new JLabel("搜索歌曲:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(10, 10, 73, 34);
		frame.getContentPane().add(lblNewLabel);

		searchTextField = new JTextField();
		searchTextField.setBounds(99, 14, 311, 28);
		frame.getContentPane().add(searchTextField);
		searchTextField.setColumns(10);


		playButton = new JButton("播放");
		playButton.setFont(new Font("宋体", Font.PLAIN, 17));
		playButton.setBounds(420, 14, 218, 50);
		playButton.addActionListener(e -> {
			try {
				String musicName = table.getValueAt(table.getSelectedRow(), 0) + ".txt";
				new TextMusicScoreUtil().playTextMusic(musicName);
			} catch (Exception exception) {
				exception.printStackTrace();
				JOptionPane.showMessageDialog(null, "请先选择歌曲", "错误", JOptionPane.WARNING_MESSAGE);
			}
		});
		frame.getContentPane().add(playButton);

		stopButton = new JButton("停止");
		stopButton.setFont(new Font("宋体", Font.PLAIN, 17));
		stopButton.setBounds(420, 74, 218, 50);
		frame.getContentPane().add(stopButton);
		stopButton.addActionListener((ActionListener) e -> {
			StaticUtil.playThread.stop();
			playBarMusicName.setText("当前没有播放的歌曲");
			StaticUtil.nowPlayTime = 0;
			StaticUtil.endPlayTime = 0;
			//重置进度条时间
			playBar.setValue(0);
		});

		changeTapKey = new JButton("改键位");
		changeTapKey.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new KeySetFrame();
			}
		});
		changeTapKey.setFont(new Font("宋体", Font.PLAIN, 17));
		changeTapKey.setBounds(420, 413, 218, 50);
		frame.getContentPane().add(changeTapKey);

		searchTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				reloadTable(table, searchTextField.getText());
			}
		});

		playBar = new JProgressBar();
		playBar.setValue(50);
		playBar.setBounds(420, 159, 215, 14);
		frame.getContentPane().add(playBar);

		playBarMusicName = new JLabel("当前没有播放的歌曲");
		playBarMusicName.setHorizontalAlignment(SwingConstants.CENTER);
		playBarMusicName.setBounds(420, 134, 218, 15);
		frame.getContentPane().add(playBarMusicName);

		frame.setVisible(true);
	}

	public void reloadTable(JTable table, String searchSongName) {
		TextMusicScoreUtil textMusicScoreUtil = new TextMusicScoreUtil();
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
