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
import java.util.HashSet;
import java.util.Set;
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
		} else if (object instanceof Set) {
			Set<?> originalList = (Set<?>) object;
			Set<Object> copyList = new HashSet<>();
			for (Object listItem : originalList) {
				Object listItemCopy = deepCopy(listItem);
				copyList.add(listItemCopy);
			}
			return copyList;
		}else {
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
        
        private static Object deepUnravel(Object object) {
		if (object instanceof List) {
			Iterable originalList = (Iterable) object;
			List<Object> copyList = new ArrayList<>();
			for (Object listItem : originalList) {
				Object listItemCopy = deepUnravel(listItem);
				copyList.add(listItemCopy);
			}
			return copyList;
		} else if (object instanceof Set) {
			Iterable originalList = (Iterable) object;
			Set<Object> copyList = new HashSet<>();
			for (Object listItem : originalList) {
				Object listItemCopy = deepUnravel(listItem);
				copyList.add(listItemCopy);
			}
			return copyList;
                }else {
//                    System.out.println("EnteredPart2");
//			LOG.info("test 2.3");
//                        System.out.println("Object");
//                        System.out.println(object);
//                        System.out.println("++++++");
                        if(object==null){
                            return null;
                        }
			Object copy = null;
			try {
				// Uzimamo klasu objekta
				Class<?> dtoClass = object.getClass();
//                                System.out.println(dtoClass.toString());
				// Uzimamo odgovarajući DTO
				String objectClassName = dtoClass.getName().replace("model.dto.", "model.");
                                int index = objectClassName.lastIndexOf("DTO");
                                String objClassName = objectClassName.substring(0,index);
//                                System.out.println("cname");
//                                System.out.println(objClassName);
//                                System.out.println("cname");
				Class<?> objectClass = Class.forName(objClassName);
				// Kreiramo novu instancu DTO klase
				copy = objectClass.getDeclaredConstructor().newInstance();
				BeanUtils.copyProperties(object, copy);
			} catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException
					| ClassNotFoundException e) {
//                                    System.out.println("Error");
				e.printStackTrace();
			}
			return copy;
		}
	}

	private static boolean isComplexType(Class<?> type) {
//		System.out.println(type);
		return !type.getPackage().getName().startsWith("java") || Iterable.class.isAssignableFrom(type);
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
//		System.out.println("DTO type: "+dto.getClass());
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
//				System.out.println(pd.getPropertyType());
				if ("class".equals(propertyName)) {
					continue;
				}
				Field dtoField = dto.getClass().getDeclaredField(propertyName);
				dtoField.setAccessible(true);
				if(DTOObject.class.isAssignableFrom(dtoField.getType())) {
//					System.out.println("check successful");
				}
				

				
				Object dtoValue = dtoField.get(dto);
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
	
	public static <X> X staticConvertToEntity(Object o,Class<X> c) {
		X entity = null;
		try {
                        entity = c.getDeclaredConstructor().newInstance();
//                        System.out.println("Entity");
//                        System.out.println(entity);
//                        System.out.println("++++++");
			BeanInfo beanInfo = Introspector.getBeanInfo(c);
			for (PropertyDescriptor pd : beanInfo.getPropertyDescriptors()) {
				String propertyName = pd.getName();
				Field dtoField = null;
				if ("class".equals(propertyName)) {
					continue;
				}
				// Trazim isto to polje u običnoj klasi

				try { // Pokusavam da nadjem polje
					dtoField = o.getClass().getDeclaredField(propertyName);
					dtoField.setAccessible(true);
					// Ako ne nadjem trazim u nad klasi
				} catch (NoSuchFieldException e) {
					dtoField = o.getClass().getSuperclass().getDeclaredField(propertyName);
				}
				// Uzimam vrednost
				Object dtoValue = dtoField.get(o);
				// Ako nije iz paketa Java, računa se kao complex type i poziva se deepCopy
				// ako jeste samo se upisuje vrednost
//				System.out.println(entityField.getType()+"TEST");\
//                                System.out.println(dtoField.getName());
//                                System.out.println(dtoField.getType().toString());
//                                System.out.println("complex");
//                                System.out.println(GenericConverter.isComplexType(dtoField.getType()));
//                                System.out.println("");
				if (GenericConverter.isComplexType(dtoField.getType())) {
					Object entityValue = deepUnravel(dtoValue);
//                                        System.out.println(entityValue.getClass().toString());
					pd.getWriteMethod().invoke(entity, entityValue);
				} else {
//					System.out.println("dto "+dto+" entityValue "+entityValue);
					pd.getWriteMethod().invoke(entity, dtoValue);
				}
			}
//			
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
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | InstantiationException | NoSuchMethodException | SecurityException | IntrospectionException | NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
            // TODO Auto-generated catch block
            // TODO Auto-generated catch block
            // TODO Auto-generated catch block
            // TODO Auto-generated catch block
		
		return dto;
	}
}
