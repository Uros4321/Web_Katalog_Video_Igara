package com.mycompany.webkatalogvideoigara.controller; 
 import com.mycompany.webkatalogvideoigara.model.Platforma; 
 import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.webkatalogvideoigara.model.dto.PlatformaDTO; 
import org.springframework.stereotype.Controller; 
import com.mycompany.webkatalogvideoigara.service.GenericService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Controller 
@RequestMapping("/api/platforme")
public class PlatformaController extends GenericController<Platforma,PlatformaDTO,GenericService<Platforma, Integer>>{
public PlatformaController(GenericService<Platforma, Integer> service){
 super(service);
}}