package com.mycompany.webkatalogvideoigara.controller; 
 import com.mycompany.webkatalogvideoigara.model.Platforma; 
 import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.webkatalogvideoigara.model.dto.PlatformaDTO; 
import org.springframework.stereotype.Controller; 
import com.mycompany.webkatalogvideoigara.service.GenericService;
@Controller 
@RequestMapping("/api/platforme")
public class PlatformaController extends GenericController<Platforma,PlatformaDTO,GenericService<Platforma, Integer>>{
public PlatformaController(GenericService<Platforma, Integer> service){
 super(service);
}}