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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author uros
 */
public class ControllerGenerator {
    public static void generateFile(String className, String apiName) {

		String content = "package application.controller; \n import application.model." + className + "; \n "
				+ "import org.springframework.web.bind.annotation.RequestMapping;\n" + "import application.model.dto." + className
				+ "DTO; \n" + "import org.springframework.stereotype.Controller; \n"
				+ "import application.service.GenericService;\n" + "@Controller \n" + "@RequestMapping(\"/api/" + apiName + "\")\n"
				+ "public class " + className + "Controller extends GenericController<" + className + "," + className
				+ "DTO," + "GenericService<" + className + ", Integer>>{\n" + "public " + className
				+ "Controller(GenericService<" + className + ", Integer> service){\n super(service);\n}}";

		String fileName = "src/main/java/com/mycompany/webkatalogvideoigara/controller" + System.getProperty("file.separator") + className
				+ "Controller.java";

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
			writer.write(content);
			System.out.println("Controller for " + className + " has been generated.");
		} catch (IOException e) {
			System.err.println("An error occurred while writing to the file: " + e.getMessage());
		}
	}

	public static void main(String[] args) throws IOException {
		Map<String, String> mapa = new HashMap<>();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String className = "";
		String apiName = "";
		while (true) {
			System.out.print("Enter controller name: ");
			className = reader.readLine();
			if (className.equals("make")) {
				break;
			}
			if (className.equals("")) {
				System.out.println("Enter class name!!");
			} else {
				System.out.print("Enter api name: ");
				apiName = reader.readLine();
				mapa.put(className, apiName);
			}
		}

		for (Map.Entry<String, String> entry : mapa.entrySet()) {
			generateFile(entry.getKey(), entry.getValue());
		}
	}
}
