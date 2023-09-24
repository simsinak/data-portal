package com.sina.repository;

import com.sina.entity.FileDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Sina Askarnejad
 */
@Repository
public interface FileDetailDao extends JpaRepository<FileDetail, Long> {

    Optional<FileDetail> findFileDetailByCode(String code);
}
