package com.example.javaFx.table;
import java.util.ArrayList;
import java.util.Date;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class Person {
 
	private String firstname, lastname;
	private Date birthdate;
	
	public Person() {}
	
	public Person(String firstname, String lastname, Date birthdate){
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.birthdate = birthdate;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	public static ArrayList<TableColumn<Person, ?>> getColumn( TableView<Person> table ){
		int idx;
		
		ArrayList<TableColumn<Person, ?>> columns = new ArrayList<TableColumn<Person, ?>>();
		
		String[] columnNames 	= { "Firstname" , "Lastname" , "Birthdate" };
		String[] variableNames 	= { "firstname" , "lastname" , "birthdate" };
		Integer[] columnWidth = { 33 , 33 , 33 };
		
		idx = 0;
		TableColumn<Person, String> firstnameCol 	= new TableColumn<>(columnNames[idx++]);
		TableColumn<Person, String> lastnameCol 	= new TableColumn<>(columnNames[idx++]);
		TableColumn<Person, String> birthdateCol 	= new TableColumn<>(columnNames[idx++]);
		
		idx = 0;
		firstnameCol.prefWidthProperty().bind(table.widthProperty().divide(100/ columnWidth[idx++]));
		lastnameCol.prefWidthProperty().bind(table.widthProperty().divide(100/ columnWidth[idx++]));
		birthdateCol.prefWidthProperty().bind(table.widthProperty().divide(100/ columnWidth[idx++]));
		
		idx = 0;
		firstnameCol.setCellValueFactory( new PropertyValueFactory<Person, String>(variableNames[idx++]));
		lastnameCol.setCellValueFactory( new PropertyValueFactory<Person, String>(variableNames[idx++]));
		birthdateCol.setCellValueFactory( new PropertyValueFactory<Person, String>(variableNames[idx++]));
		
		columns.add(firstnameCol);
		columns.add(lastnameCol);
		columns.add(birthdateCol);
		
		return columns;
	}
}
