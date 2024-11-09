/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webkatalogvideoigara;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author uros
 */
public class DTOGenerator {
    public static void generateFile(String className, ArrayList<String> class2) throws IOException {


		String DTOPath = "src/main/java/com/mycompany/webkatalogvideoigara/model/dto" + System.getProperty("file.separator") + className
				+ "DTO.java";
		String classPath = "src/main/java/com/mycompany/webkatalogvideoigara/model/" + System.getProperty("file.separator") + className+".java";
		String content = new String(Files.readAllBytes(Paths.get(classPath)));
		content = content.replaceAll(".*@\\w+.*\\(.*\\).*", "");
		content = content.replaceAll("com.mycompany.webkatalogvideoigara.model", "com.mycompany.webkatalogvideoigara.model.dto");
        for(String r : class2) {
        	if(r==className)
        		continue;
        content = content.replaceAll("\\b" + r + "\\b", r + "DTO");
        }
        content = content.replaceAll("\\b" + className + "\\b", className + "DTO");

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(DTOPath))) {
			writer.write(content);
			System.out.println("DTO for " + className + " has been generated.");
		} catch (IOException e) {
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		List<String> klase = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String className = "";
		ArrayList<String> class2 = new ArrayList<>();
		
		while (true) {
			System.out.print("Enter class2 name: ");
			String class2Name = reader.readLine();
			if (class2Name.equals("next")) {
				break;
			} else {
				class2.add(class2Name);
			}
		}
		
		while (true) {
			System.out.print("Enter class name: ");
			className = reader.readLine();
			if (className.equals("make")) {
				break;
			}
			if (className.equals("")) {
				System.out.println("Enter class name!!");
			} else {
				klase.add(className);
			}
		}
		for (String r : klase) {
			generateFile(r,class2);
		}
	
	}
}
