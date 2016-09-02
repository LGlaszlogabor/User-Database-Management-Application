package gui;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import common.ColorCellRenderer;
import common.LanguageSelector;
import common.UserTableModel;
import model.User;
import repository.DAOFactory;
import repository.UserDAO;
import service.DatabaseService;
import xml.XMLExporter;
import xml.XMLImporter;

import javax.swing.JComboBox;
import javax.swing.JPanel;

public class Client extends JFrame{
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JTextField textField;
	private JTextField firstNameText;
	private JTextField lastNameText;
	private JTextField userNameText;
	private JTextField emaiText;
	private JTextField passwordText;
	private JTextField adressText;
	private boolean isEditing;
	private boolean isInserting;
	private JButton insertButton,updateButton,deleteButton;
	private JPanel editorPanel;
	private UserTableModel model;
	private LanguageSelector lan;
	private JLabel filterLabel;
	private JButton filterButton;
	private JButton cancelButton;
	private JButton saveButton;
	private JLabel adressLabel;
	private JLabel passwordLabel;
	private JLabel emailLabel;
	private JLabel userNameLabel;
	private JLabel lastNameLabel;
	private JLabel firstNameLabel;
	private JLabel languageLabel;
	private JComboBox<String> languageCombo;
	private JButton exitButton;
	private JButton loadButton;
	private JButton exportButton;
	private JButton importButton;
	private void validateEditing(){
		insertButton.setEnabled(!isEditing);
		updateButton.setEnabled(!isEditing);
		deleteButton.setEnabled(!isEditing);
		editorPanel.setVisible(isEditing);
	}
	
	private void updateTableModel(){
		model.update();
	}
	
	private void init(){
		languageLabel.setText(lan.getLanguage());
		filterLabel.setText(lan.getFilter());
		filterButton.setText(lan.getSearch());
		insertButton.setText(lan.getInsert());
		updateButton.setText(lan.getUpdate());
		deleteButton.setText(lan.getDelete());
		exitButton.setText(lan.getExit());
		firstNameLabel.setText(lan.getFirstName());
		lastNameLabel.setText(lan.getLastName());
		passwordLabel.setText(lan.getPassword());
		userNameLabel.setText(lan.getUpdate());
		saveButton.setText(lan.getSave());
		loadButton.setText(lan.getLoadButton());
		exportButton.setText(lan.getExportButton());
		importButton.setText(lan.getImportButton());
		cancelButton.setText(lan.getCancel());
		languageCombo.removeAllItems();
		languageCombo.addItem(lan.getEn());
		languageCombo.addItem(lan.getHu());
		languageCombo.addItem(lan.getRo());
		table.getColumnModel().getColumn(0).setHeaderValue(lan.getFirstName());
		table.getColumnModel().getColumn(1).setHeaderValue(lan.getLastName());
		table.getColumnModel().getColumn(2).setHeaderValue(lan.getUserName());
		table.getColumnModel().getColumn(3).setHeaderValue("Email");
		table.getColumnModel().getColumn(4).setHeaderValue(lan.getPassword());
		table.getColumnModel().getColumn(5).setHeaderValue(lan.getAdress());
		//model.setColumnNames(lan.getFirstName(), lan.getLastName(), lan.getUserName(), "Email", lan.getPassword(), lan.getAdress());
		//table.setModel(model);
		table.repaint();
	}
	
	Client(){
		lan = new LanguageSelector();
		
		isEditing = false;
		table = new JTable();
		getContentPane().setLayout(null);
		
		JScrollPane tablePane = new JScrollPane();
		tablePane.setBounds(10, 11, 511, 256);
		model = new UserTableModel();
		table.setModel(model);
		table.setDefaultRenderer(Color.class, new ColorCellRenderer());
		tablePane.setViewportView(table);
		getContentPane().add(tablePane);
		
		filterLabel = new JLabel(lan.getFilter());
		filterLabel.setBounds(542, 151, 58, 14);
		getContentPane().add(filterLabel);
		
		textField = new JTextField();
		textField.setBounds(582, 148, 137, 20);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		filterButton = new JButton(lan.getSearch());
		filterButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				model.updateModel(DatabaseService.getInstance().getUsersByFilter(textField.getText()));
				table.updateUI();
			}
		});
		filterButton.setBounds(729, 147, 95, 23);
		getContentPane().add(filterButton);
		
		insertButton = new JButton(lan.getInsert());
		insertButton.setBounds(531, 176, 115, 23);
		getContentPane().add(insertButton);
		insertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isEditing = true;
				isInserting = true;
				validateEditing();
				firstNameText.setText("");
				lastNameText.setText("");
				userNameText.setText("");
				emaiText.setText("");
				passwordText.setText("");
				adressText.setText("");
			}
		});
		
		updateButton = new JButton(lan.getUpdate());
		updateButton.setBounds(531, 210, 115, 23);
		getContentPane().add(updateButton);
		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1){
					isEditing = true;
					isInserting = false;
					validateEditing();
					firstNameText.setText((String) table.getValueAt(row, 0));
					lastNameText.setText((String) table.getValueAt(row, 1));
					userNameText.setText((String) table.getValueAt(row, 2));
					emaiText.setText((String) table.getValueAt(row, 3));
					passwordText.setText((String) table.getValueAt(row, 4));
					adressText.setText((String) table.getValueAt(row, 5));
				}
				else{
					JOptionPane.showMessageDialog(null, lan.getErrUpd(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		deleteButton = new JButton(lan.getDelete());
		deleteButton.setBounds(531, 244, 115, 23);
		getContentPane().add(deleteButton);
		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				if(row != -1){
					DatabaseService.getInstance().deleteUser(((UserTableModel) table.getModel()).getRow(row));
					updateTableModel();
					table.updateUI();
				}
				else{
					JOptionPane.showMessageDialog(null, lan.getErrIns(), "Error!", JOptionPane.ERROR_MESSAGE);
				}			
			}
		});
		
		exitButton = new JButton(lan.getExit());
		exitButton.setBounds(715, 409, 109, 23);
		getContentPane().add(exitButton);
		JFrame frame = this;
		exitButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		
		languageCombo = new JComboBox<String>();
		languageCombo.addItem(lan.getEn());
		languageCombo.addItem(lan.getHu());
		languageCombo.addItem(lan.getRo());
		languageCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(languageCombo.getSelectedItem().equals(lan.getEn())){	
					lan.changeLanguage("en","EN");
					init();
				}else if(languageCombo.getSelectedItem().equals(lan.getHu())){
					lan.changeLanguage("hu","HU");
					init();
				}else if(languageCombo.getSelectedItem().equals(lan.getRo())){
					lan.changeLanguage("ro","RO");
					init();
				}
			}
		});
		languageCombo.setBounds(628, 15, 89, 20);
		getContentPane().add(languageCombo);
		
		languageLabel = new JLabel(lan.getLanguage());
		languageLabel.setBounds(558, 18, 77, 14);
		getContentPane().add(languageLabel);		
		
		editorPanel = new JPanel();
		editorPanel.setBounds(10, 278, 608, 138);
		getContentPane().add(editorPanel);
		editorPanel.setLayout(null);
		
		firstNameText = new JTextField();
		firstNameText.setBounds(10, 28, 86, 20);
		editorPanel.add(firstNameText);
		firstNameText.setColumns(10);
		
		firstNameLabel = new JLabel(lan.getFirstName());
		firstNameLabel.setBounds(10, 11, 66, 14);
		editorPanel.add(firstNameLabel);
		
		lastNameText = new JTextField();
		lastNameText.setBounds(106, 28, 86, 20);
		editorPanel.add(lastNameText);
		lastNameText.setColumns(10);
		
		lastNameLabel = new JLabel(lan.getLastName());
		lastNameLabel.setBounds(106, 11, 66, 14);
		editorPanel.add(lastNameLabel);
		
		userNameText = new JTextField();
		userNameText.setBounds(202, 28, 86, 20);
		editorPanel.add(userNameText);
		userNameText.setColumns(10);
		
		userNameLabel = new JLabel(lan.getUserName());
		userNameLabel.setBounds(202, 11, 66, 14);
		editorPanel.add(userNameLabel);
		
		emaiText = new JTextField();
		emaiText.setBounds(298, 28, 86, 20);
		editorPanel.add(emaiText);
		emaiText.setColumns(10);
		
		emailLabel = new JLabel("Email:");
		emailLabel.setBounds(298, 11, 46, 14);
		editorPanel.add(emailLabel);
		
		passwordText = new JTextField();
		passwordText.setBounds(394, 28, 86, 20);
		editorPanel.add(passwordText);
		passwordText.setColumns(10);
		
		passwordLabel = new JLabel(lan.getPassword());
		passwordLabel.setBounds(394, 11, 75, 14);
		editorPanel.add(passwordLabel);
		
		adressText = new JTextField();
		adressText.setBounds(490, 28, 86, 20);
		editorPanel.add(adressText);
		adressText.setColumns(10);
		
		adressLabel = new JLabel(lan.getAdress());
		adressLabel.setBounds(490, 11, 75, 14);
		editorPanel.add(adressLabel);
		
		saveButton = new JButton(lan.getSave());
		saveButton.setBounds(404, 59, 89, 23);
		editorPanel.add(saveButton);
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(firstNameText.getText().isEmpty() || lastNameText.getText().isEmpty() ||
							userNameText.getText().isEmpty()|| emaiText.getText().isEmpty()||
							passwordText.getText().isEmpty() || adressText.getText().isEmpty()){
						JOptionPane.showMessageDialog(null, lan.getErrSave(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					if(isInserting){
						DatabaseService.getInstance().insertUser(new User(firstNameText.getText(),lastNameText.getText(),userNameText.getText(),
												emaiText.getText(),passwordText.getText(),adressText.getText()));
					}
					else{
						DatabaseService.getInstance().updateUser(new User(((UserTableModel) table.getModel()).getRow(table.getSelectedRow()).getId(),firstNameText.getText(),lastNameText.getText(),userNameText.getText(),
								emaiText.getText(),passwordText.getText(),adressText.getText()));
					}
					updateTableModel();
					table.updateUI();
					isEditing = false;
					validateEditing();
				}
			}
		});
		
		cancelButton = new JButton(lan.getCancel());
		cancelButton.setBounds(500, 59, 89, 23);
		editorPanel.add(cancelButton);
		
		loadButton = new JButton(lan.getLoadButton());
		loadButton.setBounds(587, 61, 167, 23);
		loadButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(isEditing){
					JOptionPane.showMessageDialog(null, lan.getErrLoad(), "Error!", JOptionPane.ERROR_MESSAGE);
				}
				else{
					model = new UserTableModel();
					table.setModel(model);
					textField.setText("");
				}
			}
		});
		getContentPane().add(loadButton);
		
		exportButton = new JButton(lan.getExportButton());
		exportButton.setBounds(539, 95, 137, 23);
		exportButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XMLExporter exporter = new XMLExporter(model.getUserList());	
				exporter.exportData(new File("lib/User.xml"));
			}
		});
		getContentPane().add(exportButton);
		
		importButton = new JButton(lan.getImportButton());
		importButton.setBounds(673, 95, 151, 23);
		importButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				XMLImporter importer = new XMLImporter(new File("lib/User.xml"));
				model.updateModel(importer.getUserList());	
				table.setModel(model);
				table.updateUI();
			}
		});
		getContentPane().add(importButton);
		editorPanel.setVisible(isEditing);
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				isEditing = false;
				validateEditing();
			}
		});
	}
	
	public void openUI(){
		setBounds(100,100,855,490);
		setResizable(false);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	
	public static void main(String args[]){
		new Client().openUI();
	}
}
