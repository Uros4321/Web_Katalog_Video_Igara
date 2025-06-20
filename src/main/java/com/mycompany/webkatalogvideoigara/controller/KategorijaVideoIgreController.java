package com.mycompany.webkatalogvideoigara.controller; 
 import com.mycompany.webkatalogvideoigara.model.KategorijaVideoIgre; 
 import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.webkatalogvideoigara.model.dto.KategorijaVideoIgreDTO; 
import org.springframework.stereotype.Controller; 
import com.mycompany.webkatalogvideoigara.service.GenericService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMethod;

@CrossOrigin(origins = {"http://localhost:3000","http://127.0.0.1:3000"}, methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS })
@Controller 
@RequestMapping("/api/kategorije")
public class KategorijaVideoIgreController extends GenericController<KategorijaVideoIgre,KategorijaVideoIgreDTO,GenericService<KategorijaVideoIgre, Integer>>{
public KategorijaVideoIgreController(GenericService<KategorijaVideoIgre, Integer> service){
 super(service);
}}