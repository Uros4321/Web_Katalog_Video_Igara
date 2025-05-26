package com.mycompany.webkatalogvideoigara.repository;

import com.mycompany.webkatalogvideoigara.model.KategorijaVideoIgre;
import com.mycompany.webkatalogvideoigara.model.VideoIgra;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface VideoIgraRepository extends JpaRepository<VideoIgra, Integer> {
    @Query("SELECT g FROM VideoIgra g WHERE " +
            "NOT EXISTS (SELECT catI FROM KategorijaVideoIgre catI WHERE catI IN :includedCategories AND "+
            "NOT EXISTS (SELECT catM FROM g.kategorije_igre catM WHERE catM = catI)) AND "+
            "NOT EXISTS (SELECT catE FROM KategorijaVideoIgre catE WHERE catE IN :excludeCategories AND "+
            "EXISTS (SELECT catP FROM g.kategorije_igre catP WHERE catP = catE))")
    List<VideoIgra> findAllVideoIgraWithCategoriesAndWithoutCategories(@Param("includedCategories") List<KategorijaVideoIgre> includedCategories,@Param("excludeCategories") List<KategorijaVideoIgre> excludeCategories);
    
    @Query("SELECT g FROM VideoIgra g WHERE " +
            "NOT EXISTS (SELECT catI FROM KategorijaVideoIgre catI WHERE catI IN :includedCategories AND "+
            "NOT EXISTS (SELECT catM FROM g.kategorije_igre catM WHERE catM = catI))")
    List<VideoIgra> findAllVideoIgraWithCategories(@Param("includedCategories") List<KategorijaVideoIgre> includedCategories);
    
    @Query("SELECT g FROM VideoIgra g WHERE " +
            "NOT EXISTS (SELECT catE FROM KategorijaVideoIgre catE WHERE catE IN :excludeCategories AND "+
            "EXISTS (SELECT catP FROM g.kategorije_igre catP WHERE catP = catE))")
    List<VideoIgra> findAllVideoIgraWithoutCategories(@Param("excludeCategories") List<KategorijaVideoIgre> excludeCategories);
}
