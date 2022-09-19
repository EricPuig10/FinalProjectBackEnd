package com.app.finalproject.repositories;

import com.app.finalproject.models.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByOrderById();

    /*
    @Query("select i from Image i where upper(i.imgUrl) = upper(:imgUrl)")
    List<Image> findByImgUrl(@Param("imgUrl") String imgUrl); */
}
