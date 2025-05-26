package com.mycompany.webkatalogvideoigara.controller;

import com.mycompany.webkatalogvideoigara.model.GenericConverter;
import com.mycompany.webkatalogvideoigara.model.KategorijaVideoIgre;
import com.mycompany.webkatalogvideoigara.model.VideoIgra;
import org.springframework.web.bind.annotation.RequestMapping;
import com.mycompany.webkatalogvideoigara.model.dto.VideoIgraDTO;
import org.springframework.stereotype.Controller;
import com.mycompany.webkatalogvideoigara.service.GenericService;
import com.mycompany.webkatalogvideoigara.service.KategorijaVideoIgreService;
import com.mycompany.webkatalogvideoigara.service.VideoIgraService;
import java.util.ArrayList;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequestMapping("/api/igre")
public class VideoIgraController extends GenericController<VideoIgra, VideoIgraDTO, VideoIgraService> {

    private final KategorijaVideoIgreService katS;

    public VideoIgraController(VideoIgraService service, KategorijaVideoIgreService katS) {
        super(service);
        this.katS = katS;
    }

    @GetMapping("/api/igre/kategorije/{cats}")
    public ResponseEntity<Iterable<VideoIgraDTO>> filterCategories(@PathVariable String cats) {
        String[] sts = cats.split("|");
        String inc;
        String exc;
        if(sts.length==1){
            inc = sts[0];
            exc = "";
        }else{
            inc = sts[0];
            exc = sts[1];
            exc = exc.replace("|", "");
        }
        if(" ".equals(inc)){
            inc="";
        }
        ArrayList<KategorijaVideoIgre> incA = new ArrayList<>();
        if(!inc.equals("")){
        for(String incID:inc.split(",")){
            incA.add(katS.findById(Integer.valueOf(incID)).get());
        }}
        ArrayList<KategorijaVideoIgre> excA = new ArrayList<>();
        if(!exc.equals("")){
        for(String excID:exc.split(",")){
            excA.add(katS.findById(Integer.valueOf(excID)).get());
        }}
        Iterable<VideoIgra> igre = service.filterCategories(incA, excA);
        ArrayList<VideoIgraDTO> dtos = new ArrayList<>();
        for (VideoIgra entitet : igre) {
            GenericConverter<VideoIgra, VideoIgraDTO> genConv = new GenericConverter<>();
            VideoIgraDTO dto = genConv.convertToDTO(entitet, VideoIgraDTO.class);
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos,HttpStatus.OK);
    }
}
