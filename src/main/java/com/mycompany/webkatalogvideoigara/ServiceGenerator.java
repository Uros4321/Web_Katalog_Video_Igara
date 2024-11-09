/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webkatalogvideoigara;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author uros
 */
public class ServiceGenerator {
    public static void generateRepositoryFile(String className) {
		String fileName = "src/main/java/com/mycompany/webkatalogvideoigara/service" + System.getProperty("file.separator") + className + "Service.java";
		String content = "package com.mycompany.webkatalogvideoigara.service; " + "\nimport org.springframework.stereotype.Service; "
				+ "\nimport com.mycompany.webkatalogvideoigara.model." + className + ";" + "\nimport com.mycompany.webkatalogvideoigara.repository." + className
				+ "Repository;  " + "\n@Service" + "\npublic class " + className + "Service extends GenericService<"
				+ className + ", Integer> {" + "\npublic " + className + "Service(" + className
				+ "Repository repository){" + "super(repository);\n}\n}";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(content);
			System.out.println("Service for " + className + " has been generated.");
		} catch (IOException e) {
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
		}
	}

	public static List<String> listFileNamesWithoutExtension(String directoryPath) {
		File folder = new File(directoryPath);
		System.out.println(directoryPath);
		File[] listOfFiles = folder.listFiles();
		List<String> fileNames = new ArrayList<>();

		if (listOfFiles != null) {
			for (File file : listOfFiles) {
				if (file.isFile()) {
					String fileName = file.getName();
					if (fileName.endsWith(".java")) {
						fileName = fileName.substring(0, fileName.length() - 5);
					}
					fileNames.add(fileName);
				}
			}
		}else {
			System.out.println("list of files is null");
		}
		return fileNames;
	}

	public static void main(String[] args) throws IOException {
		List<String> servisi = new ArrayList<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String className = "";
		System.out.println("1. Make services for all classes \n2. Make services by name");
		className = reader.readLine();
		String filePath = "src/main/java/com/mycompany/webkatalogvideoigara/model";
		if (className.equals("2")) {
			while (true) {
				System.out.print("Enter service name: ");
				className = reader.readLine();

				if (className.equals("make")) {
					break;
				}
				if (className.equals("")) {
					System.out.println("Enter service name!!");
				} else {
					servisi.add(className);
				}
			}
		} else if (className.equals("1")) {

			servisi = listFileNamesWithoutExtension(filePath);
			System.out.println(servisi);
		}
		for (String r : servisi) {
//			System.out.println(r);
			generateRepositoryFile(r);
		}
	}
}
