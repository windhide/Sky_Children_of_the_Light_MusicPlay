package com.windhide.frame;

import com.windhide.entity.Tap.KeyTap;
import com.windhide.util.StaticUtil;
import com.windhide.util.TextMusicScoreUtil;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class MainFrame extends JFrame{

    private JFrame frame;
    private JTextField searchTextField;
    private JButton playButton, stopButton, editButton, computerButton;
    private JScrollPane scrollPane;
    private JTable table;
    private JButton changeTapKey;
    public JSlider playBar,delayBar,extBar;
    public JLabel playBarMusicName, lblNewLabel;

    public JRadioButton rb1,rb2,extRb1,extRb2;

    public MainFrame() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setIconImage(StaticUtil.iconImage);
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
        playButton.setBounds(420, 14, 100, 50);
        playButton.addActionListener(e -> {
            try {
                String musicName = table.getValueAt(table.getSelectedRow(), 0) + ".txt";
                StaticUtil.isSystemPlay = true;
                new TextMusicScoreUtil().playTextMusic(musicName);
            } catch (Exception exception) {
                if (StaticUtil.keyTap == null) {
                    JOptionPane.showMessageDialog(null, "没有设置按键！", "错误", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (StaticUtil.playThread != null) {
                        StaticUtil.playThread.stop();
                    }
                    StaticUtil.playThread = null;
                    StaticUtil.playRunnable = null;
                    StaticUtil.nowPlayMusic = null;
                    JOptionPane.showMessageDialog(null, "请先选择歌曲或是歌曲无法解析", "错误", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        frame.getContentPane().add(playButton);

        stopButton = new JButton("停止");
        stopButton.setFont(new Font("宋体", Font.PLAIN, 17));
        stopButton.setBounds(530, 14, 100, 50);
        frame.getContentPane().add(stopButton);
        stopButton.addActionListener(e -> {
            try {
                StaticUtil.playThread.stop();
                StaticUtil.playThread = null;
                StaticUtil.playRunnable = null;
            } catch (Exception exception) {
                JOptionPane.showMessageDialog(null, "没有正在进行的歌曲", null, JOptionPane.WARNING_MESSAGE);
            } finally {
                StaticUtil.nowPlayMusic = null;
            }
            playBarMusicName.setText("当前没有播放的歌曲");
            //重置进度条时间
            playBar.setValue(0);
        });

        editButton = new JButton("选择外部文件播放");
        editButton.setFont(new Font("宋体", Font.PLAIN, 17));
        editButton.setBounds(420,70,210,50);
        editButton.addActionListener(e ->{
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileFilter(new FileFilter() {
                @Override
                public boolean accept(File f) {
                    String fName = f.getName().toUpperCase();
                    if (fName.endsWith(".TXT") || fName.endsWith(".JS") || f.isDirectory()) {
                        return true;
                    } else {
                        return false;
                    }
                }

                @Override
                public String getDescription() {
                    return "(*.txt) & (*.js)";
                }
            });
            int option = fileChooser.showDialog(MainFrame.this,"选择");
            if(option==JFileChooser.APPROVE_OPTION){
                File file = fileChooser.getSelectedFile();
                String fileName = file.getAbsolutePath();
                try {
                    StaticUtil.isSystemPlay = false;
                    new TextMusicScoreUtil().playTextMusic(fileName);
                } catch (Exception exception) {
                    if (StaticUtil.playThread != null) {
                        StaticUtil.playThread.stop();
                    }
                    StaticUtil.playThread = null;
                    StaticUtil.playRunnable = null;
                    StaticUtil.nowPlayMusic = null;
                    JOptionPane.showMessageDialog(null, "请先选择歌曲或是歌曲无法解析", "错误", JOptionPane.WARNING_MESSAGE);
                }

            }
        });
        frame.getContentPane().add(editButton);


        computerButton = new JButton("电脑键");
        computerButton.setFont(new Font("宋体", Font.PLAIN, 17));
        computerButton.setBounds(530, 413,100,50);
        computerButton.addActionListener(e ->{
            KeyTap keyTapUtil = new KeyTap();
            keyTapUtil.Do = "y";
            keyTapUtil.Re = "u";
            keyTapUtil.Mi = "i";
            keyTapUtil.Fa = "o";
            keyTapUtil.So = "p";
            keyTapUtil.La = "h";
            keyTapUtil.Xi = "j";
            keyTapUtil.H_Do = "k";
            keyTapUtil.H_Re = "l";
            keyTapUtil.H_Mi = ";";
            keyTapUtil.H_Fa = "n";
            keyTapUtil.H_So = "m";
            keyTapUtil.H_La = ",";
            keyTapUtil.H_Xi = ".";
            keyTapUtil.HH_Do = "/";
            StaticUtil.keyTap = keyTapUtil;
            JOptionPane.showMessageDialog(null, "按键设置成功", "成功", JOptionPane.INFORMATION_MESSAGE);
        });
        frame.getContentPane().add(computerButton);


        changeTapKey = new JButton("改键位");
        changeTapKey.addActionListener(e -> new KeySetFrame());
        changeTapKey.setFont(new Font("宋体", Font.PLAIN, 17));
        changeTapKey.setBounds(420, 413, 100, 50);
        frame.getContentPane().add(changeTapKey);

        searchTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                reloadTable(table, searchTextField.getText());
            }
        });

        delayBar = new JSlider(0,100,1);
        delayBar.setValue(10);
        delayBar.setBounds(420, 220, 215, 50);
        delayBar.setMajorTickSpacing(10);
        delayBar.setPaintTicks(true);
        delayBar.setPaintLabels(true);
        delayBar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                JSlider source = (JSlider) e.getSource();
                StaticUtil.delay = source.getValue();
                rb1.doClick();
            }
        });
        frame.getContentPane().add(delayBar);


        rb1=new JRadioButton("自定义延时");
        rb2=new JRadioButton("随机延时");
        rb1.setBounds(420, 270, 100, 30);
        rb2.setBounds(565, 270, 80, 30);
        ButtonGroup gp=new ButtonGroup();
        gp.add(rb1);
        gp.add(rb2);
        frame.getContentPane().add(rb1);
        frame.getContentPane().add(rb2);
        rb1.addActionListener(e->{
            StaticUtil.delay = delayBar.getValue();
            StaticUtil.isRandom = false;
        });
        rb2.addActionListener(e->{
            StaticUtil.isRandom = true;
        });


//        extBar = new JSlider(0,100,1);
//        extBar.setValue(10);
//        extBar.setBounds(420, 320, 215, 50);
//        extBar.setMajorTickSpacing(10);
//        extBar.setPaintTicks(true);
//        extBar.setPaintLabels(true);
//        extBar.addMouseListener(new MouseAdapter() {
//            @Override
//            public void mouseReleased(MouseEvent e) {
//                JSlider source = (JSlider) e.getSource();
//                StaticUtil.delay = source.getValue();
//                rb1.doClick();
//            }
//        });
//        frame.getContentPane().add(extBar);
//
//        extRb1=new JRadioButton("自定义长按");
//        extRb2=new JRadioButton("随机长按");
//        extRb1.setBounds(420, 370, 100, 30);
//        extRb2.setBounds(565, 370, 80, 30);
//        ButtonGroup extGp = new ButtonGroup();
//        extGp.add(extRb1);
//        extGp.add(extRb2);
//        frame.getContentPane().add(extRb1);
//        frame.getContentPane().add(extRb2);
//        extRb1.addActionListener(e->{
//            StaticUtil.delay = delayBar.getValue();
//            StaticUtil.extIsRandom = false;
//        });
//        extRb2.addActionListener(e->{
//            StaticUtil.extIsRandom = true;
//        });


        playBar = new JSlider(0,100,1);
        playBar.setValue(0);
        playBar.setBounds(420, 159, 215, 50);
        playBar.setMajorTickSpacing(10);
        playBar.setPaintTicks(true);
        playBar.setPaintLabels(true);
        playBar.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseReleased(MouseEvent e) {
                JSlider source = (JSlider) e.getSource();
                if (!(source.getValueIsAdjusting())) {
                    BigDecimal percentage = new BigDecimal((float) source.getValue() / 100);
                    percentage = percentage.setScale(2, RoundingMode.HALF_UP);
                    percentage = percentage.multiply(new BigDecimal(StaticUtil.musicPlayMaxIndex));
                    setMusicPlayIndex(percentage.intValue());
                }
            }
        });
        frame.getContentPane().add(playBar);

        playBarMusicName = new JLabel("当前没有播放的歌曲");
        playBarMusicName.setHorizontalAlignment(SwingConstants.CENTER);
        playBarMusicName.setBounds(420, 134, 218, 25);
        frame.getContentPane().add(playBarMusicName);
        frame.setVisible(true);
        rb1.doClick();
    }

    public void reloadTable(JTable table, String searchSongName) {
        TextMusicScoreUtil textMusicScoreUtil = new TextMusicScoreUtil();
        List<String> songNameList = null;
        if (searchSongName != null && !searchSongName.equals("")) {
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

    public synchronized void setMusicPlayIndex(int index){
        StaticUtil.musicPlayIndex = index;
    }

}
