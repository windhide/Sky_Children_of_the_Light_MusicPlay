package com.windhide.frame;

import com.windhide.entity.Tap.KeyTap;
import com.windhide.frame.document.WordDocument;
import com.windhide.util.StaticUtil;

import javax.swing.*;

public class KeySetFrame {

	private JFrame frame;
	private JTextField keyTapDo;
	private JTextField keyTapRe;
	private JTextField keyTapMi;
	private JTextField keyTapFa;
	private JTextField keyTapSo;
	private JTextField keyTapLa;
	private JTextField keyTapXi;
	private JTextField keyTapH_Do;
	private JTextField keyTapH_Re;
	private JTextField keyTapH_Mi;
	private JTextField keyTapH_Fa;
	private JTextField keyTapH_So;
	private JTextField keyTapH_La;
	private JTextField keyTapH_Xi;
	private JTextField keyTapHH_Do;

	public KeySetFrame() {
		frame = new JFrame();
		frame.setIconImage(StaticUtil.iconImage);
		frame.setTitle("按键设置");
		frame.setBounds(100, 100, 699, 275);
		frame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false); // 禁止调整窗口大小


		JPanel tapKeyEditPane = new JPanel();
		tapKeyEditPane.setBounds(10, 10, 671, 226);
		frame.getContentPane().add(tapKeyEditPane);
		tapKeyEditPane.setLayout(null);

		JLabel keyTapLabel1 = new JLabel("按键1 - Do");
		keyTapLabel1.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel1.setBounds(10, 10, 86, 25);
		tapKeyEditPane.add(keyTapLabel1);

		keyTapDo = new JTextField();
		keyTapDo.setColumns(1);
		keyTapDo.setBounds(20, 45, 66, 21);
		keyTapDo.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapDo);

		JLabel keyTapLabel2 = new JLabel("按键2 - Re");
		keyTapLabel2.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel2.setBounds(106, 10, 86, 25);
		tapKeyEditPane.add(keyTapLabel2);

		keyTapRe = new JTextField();
		keyTapRe.setColumns(1);
		keyTapRe.setBounds(116, 45, 66, 21);
		keyTapRe.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapRe);

		JLabel keyTapLabel3 = new JLabel("按键3 - Mi");
		keyTapLabel3.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel3.setBounds(202, 10, 86, 25);
		tapKeyEditPane.add(keyTapLabel3);

		keyTapMi = new JTextField();
		keyTapMi.setColumns(1);
		keyTapMi.setBounds(212, 45, 66, 21);
		keyTapMi.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapMi);

		JLabel keyTapLabel4 = new JLabel("按键4 - Fa");
		keyTapLabel4.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel4.setBounds(298, 10, 86, 25);
		tapKeyEditPane.add(keyTapLabel4);

		keyTapFa = new JTextField();
		keyTapFa.setColumns(1);
		keyTapFa.setBounds(308, 45, 66, 21);
		keyTapFa.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapFa);

		JLabel keyTapLabel5 = new JLabel("按键5 - So");
		keyTapLabel5.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel5.setBounds(394, 10, 86, 25);
		tapKeyEditPane.add(keyTapLabel5);

		keyTapSo = new JTextField();
		keyTapSo.setColumns(1);
		keyTapSo.setBounds(404, 45, 66, 21);
		keyTapSo.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapSo);

		JLabel keyTapLabel6 = new JLabel("按键2-1 - La");
		keyTapLabel6.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel6.setBounds(10, 76, 86, 25);
		tapKeyEditPane.add(keyTapLabel6);

		keyTapLa = new JTextField();
		keyTapLa.setColumns(1);
		keyTapLa.setBounds(20, 111, 66, 21);
		keyTapLa.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapLa);

		JLabel keyTapLabel7 = new JLabel("按键2-2 - Xi");
		keyTapLabel7.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel7.setBounds(106, 76, 86, 25);
		tapKeyEditPane.add(keyTapLabel7);

		keyTapXi = new JTextField();
		keyTapXi.setColumns(1);
		keyTapXi.setBounds(116, 111, 66, 21);
		keyTapXi.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapXi);

		JLabel keyTapLabel8 = new JLabel("按键2-3 - H_Do");
		keyTapLabel8.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel8.setBounds(202, 76, 86, 25);
		tapKeyEditPane.add(keyTapLabel8);

		keyTapH_Do = new JTextField();
		keyTapH_Do.setColumns(1);
		keyTapH_Do.setBounds(212, 111, 66, 21);
		keyTapH_Do.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapH_Do);

		JLabel keyTapLabel9 = new JLabel("按键2-4 - H_Re");
		keyTapLabel9.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel9.setBounds(298, 76, 86, 25);
		tapKeyEditPane.add(keyTapLabel9);

		keyTapH_Re = new JTextField();
		keyTapH_Re.setColumns(1);
		keyTapH_Re.setBounds(308, 111, 66, 21);
		keyTapH_Re.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapH_Re);

		JLabel keyTapLabel10 = new JLabel("按键2-5 - H_Mi");
		keyTapLabel10.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel10.setBounds(394, 76, 86, 25);
		tapKeyEditPane.add(keyTapLabel10);

		keyTapH_Mi = new JTextField();
		keyTapH_Mi.setColumns(1);
		keyTapH_Mi.setBounds(404, 111, 66, 21);
		keyTapH_Mi.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapH_Mi);

		JLabel keyTapLabel11 = new JLabel("按键3-1 - H_Fa");
		keyTapLabel11.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel11.setBounds(10, 142, 86, 25);
		tapKeyEditPane.add(keyTapLabel11);

		keyTapH_Fa = new JTextField();
		keyTapH_Fa.setColumns(1);
		keyTapH_Fa.setBounds(20, 177, 66, 21);
		keyTapH_Fa.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapH_Fa);

		JLabel keyTapLabel12 = new JLabel("按键3-2 - H_So");
		keyTapLabel12.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel12.setBounds(106, 142, 86, 25);
		tapKeyEditPane.add(keyTapLabel12);

		keyTapH_So = new JTextField();
		keyTapH_So.setColumns(1);
		keyTapH_So.setBounds(116, 177, 66, 21);
		keyTapH_So.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapH_So);

		JLabel keyTapLabel13 = new JLabel("按键3-3 - H_La");
		keyTapLabel13.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel13.setBounds(202, 142, 86, 25);
		tapKeyEditPane.add(keyTapLabel13);

		keyTapH_La = new JTextField();
		keyTapH_La.setColumns(1);
		keyTapH_La.setBounds(212, 177, 66, 21);
		keyTapH_La.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapH_La);

		JLabel keyTapLabel14 = new JLabel("按键3-4 - H_Xi");
		keyTapLabel14.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel14.setBounds(298, 142, 86, 25);
		tapKeyEditPane.add(keyTapLabel14);

		keyTapH_Xi = new JTextField();
		keyTapH_Xi.setColumns(1);
		keyTapH_Xi.setBounds(308, 177, 66, 21);
		keyTapH_Xi.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapH_Xi);

		JLabel keyTapLabel15 = new JLabel("按键3-5 - HH_Do");
		keyTapLabel15.setHorizontalAlignment(SwingConstants.CENTER);
		keyTapLabel15.setBounds(394, 142, 116, 25);
		tapKeyEditPane.add(keyTapLabel15);

		keyTapHH_Do = new JTextField();
		keyTapHH_Do.setColumns(1);
		keyTapHH_Do.setBounds(404, 177, 66, 21);
		keyTapHH_Do.setDocument(new WordDocument());
		tapKeyEditPane.add(keyTapHH_Do);

		JButton saveKeyTap_1 = new JButton("保存按键");
		saveKeyTap_1.addActionListener(e -> {
			KeyTap keyTapUtil = new KeyTap();
			keyTapUtil.Do = keyTapDo.getText();
			keyTapUtil.Re = keyTapRe.getText();
			keyTapUtil.Mi = keyTapMi.getText();
			keyTapUtil.Fa = keyTapFa.getText();
			keyTapUtil.So = keyTapSo.getText();
			keyTapUtil.La = keyTapLa.getText();
			keyTapUtil.Xi = keyTapXi.getText();
			keyTapUtil.H_Do = keyTapH_Do.getText();
			keyTapUtil.H_Re = keyTapH_Re.getText();
			keyTapUtil.H_Mi = keyTapH_Mi.getText();
			keyTapUtil.H_Fa = keyTapH_Fa.getText();
			keyTapUtil.H_So = keyTapH_So.getText();
			keyTapUtil.H_La = keyTapH_La.getText();
			keyTapUtil.H_Xi = keyTapH_Xi.getText();
			keyTapUtil.HH_Do = keyTapHH_Do.getText();
			StaticUtil.keyTap = keyTapUtil;
			JOptionPane.showMessageDialog(null, "按键设置成功", "成功", JOptionPane.INFORMATION_MESSAGE);
		});
		saveKeyTap_1.setBounds(510, 10, 151, 51);
		tapKeyEditPane.add(saveKeyTap_1);

		JButton saveKeyTap_1_1 = new JButton("关闭");
		saveKeyTap_1_1.addActionListener(e -> frame.setVisible(false));
		saveKeyTap_1_1.setBounds(510, 142, 151, 51);
		tapKeyEditPane.add(saveKeyTap_1_1);

		JButton clearKeyTap = new JButton("重置按键");
		clearKeyTap.addActionListener(e -> {
			keyTapDo.setText("");
			keyTapRe.setText("");
			keyTapMi.setText("");
			keyTapFa.setText("");
			keyTapSo.setText("");
			keyTapLa.setText("");
			keyTapXi.setText("");
			keyTapH_Do.setText("");
			keyTapH_Re.setText("");
			keyTapH_Mi.setText("");
			keyTapH_Fa.setText("");
			keyTapH_So.setText("");
			keyTapH_La.setText("");
			keyTapH_Xi.setText("");
			keyTapHH_Do.setText("");
		});
		clearKeyTap.setBounds(510, 76, 151, 51);
		tapKeyEditPane.add(clearKeyTap);

		// 如果有重新载入
		reloadKeyTap();

		frame.setVisible(true);
	}

	public void reloadKeyTap() {
		if (StaticUtil.keyTap != null) {
			KeyTap keyTap = StaticUtil.keyTap;
			keyTapDo.setText(keyTap.Do);
			keyTapRe.setText(keyTap.Re);
			keyTapMi.setText(keyTap.Mi);
			keyTapFa.setText(keyTap.Fa);
			keyTapSo.setText(keyTap.So);
			keyTapLa.setText(keyTap.La);
			keyTapXi.setText(keyTap.Xi);
			keyTapH_Do.setText(keyTap.H_Do);
			keyTapH_Re.setText(keyTap.H_Re);
			keyTapH_Mi.setText(keyTap.H_Mi);
			keyTapH_Fa.setText(keyTap.H_Fa);
			keyTapH_So.setText(keyTap.H_So);
			keyTapH_La.setText(keyTap.H_La);
			keyTapH_Xi.setText(keyTap.H_Xi);
			keyTapHH_Do.setText(keyTap.HH_Do);
		}
	}

	public static void main(String[] args) {
		new KeySetFrame();
	}

}
