/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.webkatalogvideoigara.model;

/**
 *
 * @author uros
 */
import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

import com.mycompany.webkatalogvideoigara.model.dto.DTOObject;
//import org.springframework.security.crypto.password.PasswordEncoder;

//import root.security.SecurityConfiguration;

public class GenericConverter<T, DTO> {

	// private final PasswordEncoder passwordEncoder =
	// SecurityConfiguration.getNonBeanPasswordEncoder();

	public static <X, Y> GenericConverter<X,Y> create(Class<X> x,Class<Y> y){
		return new GenericConverter<X,Y>();
		
	}
	public DTO convertToDTO(T entity, Class<DTO> dtoClass) { // deprecated, moving to static methods
		DTO dto = null;
		try {
			dto = dtoClass.getDeclaredConstructor().newInstance();
			// Kreiram klasu beanInfo koja daje sve detalje bean-a
			BeanInfo beanInfo = Introspector.getBeanInfo(dtoClass);
			// Prolazim kroz properti deskriptore
			for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
				// uzimam naziv propertija i ignorišem ako je property class
				String propertyName = pd.getName();
				Field entityField = null;
				if ("class".equals(propertyName)) {
					continue;
				}
				// Trazim isto to polje u običnoj klasi

				try { // Pokusavam da nadjem polje
					entityField = entity.getClass().getDeclaredField(propertyName);
					entityField.setAccessible(true);
					// Ako ne nadjem trazim u nad klasi
				} catch (NoSuchFieldException e) {
					entityField = entity.getClass().getSuperclass().getDeclaredField(propertyName);
				}
				// Uzimam vrednost
				Object entityValue = entityField.get(entity);
				// Ako nije iz paketa Java, računa se kao complex type i poziva se deepCopy
				// ako jeste samo se upisuje vrednost
//				System.out.println(entityField.getType()+"TEST");
				if (isComplexType(entityField.getType())) {
					Object dtoValue = deepCopy(entityValue);
					pd.getWriteMethod().invoke(dto, dtoValue);
				} else {
//					System.out.println("dto "+dto+" entityValue "+entityValue);
					pd.getWriteMethod().invoke(dto, entityValue);
				}
			}
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException
				| IntrospectionException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return dto;
	}

	private static Object deepCopy(Object object) {
		if (object instanceof List) {
			List<?> originalList = (List<?>) object;
			List<Object> copyList = new ArrayList<>();
			for (Object listItem : originalList) {
				Object listItemCopy = deepCopy(listItem);
				copyList.add(listItemCopy);
			}
			return copyList;
		} else {
//			LOG.info("test 2.3");
			Object copy = null;
			try {
				// Uzimamo klasu objekta
				Class<?> objectClass = object.getClass();
				// Uzimamo odgovarajući DTO
				String dtoClassName = objectClass.getName().replace("model.", "model.dto.");
				Class<?> dtoClass = Class.forName(dtoClassName + "DTO");
				// Kreiramo novu instancu DTO klase
				copy = dtoClass.getDeclaredConstructor().newInstance();
				BeanUtils.copyProperties(object, copy);
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException
					| ClassNotFoundException e) {

				e.printStackTrace();
			}
			return copy;
		}
	}

	private static boolean isComplexType(Class<?> type) {
//		System.out.println(type);
		return !type.getPackage().getName().startsWith("java") || List.class.isAssignableFrom(type);
	}

	public T convertToEntity(DTO dto, Class<T> entityClass)
			throws IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		T entity = null;

		try {
			entity = entityClass.getDeclaredConstructor().newInstance();
			BeanUtils.copyProperties(dto, entity); // Funkcionise za drzavu, ne funkcionise za mesto
			// ako ima lozinka entity, enkriptuje sifru bcryptom
//	        if (entity instanceof LozinkaEntity) {
//	            LozinkaEntity passwordEntity = (LozinkaEntity) entity;
//	            String rawPassword = passwordEntity.getLozinka();
//	            if (rawPassword != null && !rawPassword.isEmpty()) {
//	                String hashedPassword = passwordEncoder.encode(rawPassword);
//	                passwordEntity.setLozinka(hashedPassword);
//	            }
//	        }
		} catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
			System.out.println(dto);
			System.out.println(entity);
			e.printStackTrace();
		}

		return entity;
	}

	public T convertToEntity2(Object dto, Class<T> entityClass) {
		T entity = null;
		System.out.println("DTO type: "+dto.getClass());
//        try
//        {
//            System.out.println(type.getDeclaredConstructor().newInstance());
//        }
//        catch (Exception e)
//        {
//            // Oops, no default constructor
//            throw new RuntimeException(e);
//        }
		try {
			entity = entityClass.getDeclaredConstructor().newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(entityClass);
			for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
				String propertyName = pd.getName();
				System.out.println(pd.getPropertyType());
				if ("class".equals(propertyName)) {
					continue;
				}
				Field dtoField = dto.getClass().getDeclaredField(propertyName);
				dtoField.setAccessible(true);
				if(DTOObject.class.isAssignableFrom(dtoField.getType())) {
					System.out.println("check successful");
				}
				

				
				Object dtoValue = dtoField.get(dto);
				System.out.println(dtoField.getType());
				Field entityField = entity.getClass().getDeclaredField(propertyName);
				entityField.setAccessible(true);
				entityField.set(entity, dtoValue);
			}
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException
				| IntrospectionException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return entity;
	}
	
	public static <X> X staticConvertToEntity(Object o,Class<X> c) {
		X entity = null;
		try {
			entity = c.getDeclaredConstructor().newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(c);
			for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
				String propertyName = pd.getName();
//				System.out.println(pd.getPropertyType());
				if ("class".equals(propertyName)) {
					continue;
				}
				Field dtoField = o.getClass().getDeclaredField(propertyName);
				dtoField.setAccessible(true);
				if(DTOObject.class.isAssignableFrom(dtoField.getType())) {
//					System.out.println("check successful");
					Object dtoValue = dtoField.get(o);
					Object newValue = GenericConverter.staticConvertToEntity(dtoValue,pd.getPropertyType());
					Field entityField = entity.getClass().getDeclaredField(propertyName);
					entityField.setAccessible(true);
					entityField.set(entity, newValue);
					continue;
				}
				

				
				Object dtoValue = dtoField.get(o);
//				System.out.println(dtoField.getType());
				Field entityField = entity.getClass().getDeclaredField(propertyName);
				entityField.setAccessible(true);
				entityField.set(entity, dtoValue);
			}
		} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException
				| IntrospectionException | NoSuchFieldException e) {
			e.printStackTrace();
		}
		return entity;
	}
	public static <XDTO> XDTO staticConvertToDTO(Object o,Class<XDTO> dtoClass) {
		XDTO dto = null;
		try {
			dto = dtoClass.getDeclaredConstructor().newInstance();
			BeanInfo beanInfo = Introspector.getBeanInfo(dtoClass);
			for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
				String propertyName = pd.getName();
				Field entityField = null;
				if ("class".equals(propertyName)) {
					continue;
				}
				// Trazim isto to polje u običnoj klasi

				try { // Pokusavam da nadjem polje
					entityField = o.getClass().getDeclaredField(propertyName);
					entityField.setAccessible(true);
					// Ako ne nadjem trazim u nad klasi
				} catch (NoSuchFieldException e) {
					entityField = o.getClass().getSuperclass().getDeclaredField(propertyName);
				}
				// Uzimam vrednost
				Object entityValue = entityField.get(o);
				// Ako nije iz paketa Java, računa se kao complex type i poziva se deepCopy
				// ako jeste samo se upisuje vrednost
//				System.out.println(entityField.getType()+"TEST");
				if (GenericConverter.isComplexType(entityField.getType())) {
					Object dtoValue = deepCopy(entityValue);
					pd.getWriteMethod().invoke(dto, dtoValue);
				} else {
//					System.out.println("dto "+dto+" entityValue "+entityValue);
					pd.getWriteMethod().invoke(dto, entityValue);
				}
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchMethodException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SecurityException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IntrospectionException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (NoSuchFieldException e1) {
			e1.printStackTrace();
		}
		
		return dto;
	}
}
