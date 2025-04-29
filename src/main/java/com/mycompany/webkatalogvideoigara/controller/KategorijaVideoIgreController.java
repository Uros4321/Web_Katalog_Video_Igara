package com.mycompany.webkatalogvideoigara.controller; 
 import com.mycompany.webkatalogvideoigara.model.KategorijaVideoIgre; 
 import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.webkatalogvideoigara.model.dto.KategorijaVideoIgreDTO; 
import org.springframework.stereotype.Controller; 
import com.mycompany.webkatalogvideoigara.service.GenericService;
@Controller 
@RequestMapping("/api/kategorije")
public class KategorijaVideoIgreController extends GenericController<KategorijaVideoIgre,KategorijaVideoIgreDTO,GenericService<KategorijaVideoIgre, Integer>>{
public KategorijaVideoIgreController(GenericService<KategorijaVideoIgre, Integer> service){
 super(service);
}}