package com.iggi.example.lov.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.iggi.example.lov.data.entity.LovData;
import com.iggi.example.lov.data.entity.PrimaryKeyLovTypeAndLovDbValue;

@Repository
public interface LovDataRepository extends JpaRepository<LovData, PrimaryKeyLovTypeAndLovDbValue> {

    List<LovData> findLovDataByLovType(String lovType);

}
