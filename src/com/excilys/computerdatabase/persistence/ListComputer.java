package com.excilys.computerdatabase.persistence;


import java.sql.*;

public class ListComputer {
	public ListComputer() {
		try {
	Class.forName("com.mysql.jdbc.Driver");
	System.out.println("Driver O.K.");
	String url = "jdbc:mysql://127.0.0.1:3306/computer-database-db?zeroDateTimeBehavior=convertToNull";
	Connection conn = DriverManager.getConnection(url,"root","");
    System.out.println("Connexion effective !"); 
	

    //Création d'un objet Statement
    Statement state = conn.createStatement();
    //L'objet ResultSet contient le résultat de la requête SQL

    ResultSet result = state.executeQuery("SELECT * FROM computer");
    //On récupère les MetaData
    ResultSetMetaData resultMeta = result.getMetaData();
       
    System.out.println("\n**********************************");
    //On affiche le nom des colonnes
    for(int i = 1; i <= resultMeta.getColumnCount(); i++)
      System.out.print("\t" + resultMeta.getColumnName(i).toUpperCase() + "\t *");
 
    System.out.println("\n**********************************");
       
    while(result.next()){         
      for(int i = 1; i <= resultMeta.getColumnCount(); i++)
    	  {
    if (result.getObject(i)!=null){ System.out.print("\t" + result.getString(i) + "\t |");}
       else { System.out.print("\t" + "******" + "\t |");}
    	  }
      System.out.println("\n---------------------------------");
	
	
    }
	
	result.close();
	conn.close();
		}
		catch(Exception e){
			e.printStackTrace();
		}
}
}