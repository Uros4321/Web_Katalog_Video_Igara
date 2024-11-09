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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author uros
 */
public class RepoGenerator {
    public static void generateRepositoryFile(String className) {
		String content = "package com.mycompany.webkatalogvideoigara.repository; "
				+ "\n import org.springframework.data.repository.CrudRepository; "
				+ "\n import com.mycompany.webkatalogvideoigara.model."+ className +"; "
				+ "\n public interface " + className
				+ "Repository extends CrudRepository<" + className+ ", Integer> {\n\n}";

		String fileName = "src/main/java/com/mycompany/webkatalogvideoigara/repository" + System.getProperty("file.separator") + className
				+ "Repository.java";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(content);
			System.out.println("Repository interface for " + className + " has been generated.");
		} catch (IOException e) {
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		List<String> repozitorijumi = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String className = "";
		while (true) {
			System.out.print("Enter repo name: ");
			className = reader.readLine();
			if (className.equals("make")) {
				break;
			}
			if (className.equals("")) {
				System.out.println("Enter repo name!!");
			} else {
				repozitorijumi.add(className);
			}
		}
		for (String r : repozitorijumi) {
			generateRepositoryFile(r);
		}
	}
}
