package com.edubridge.hospital.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edubridge.hospital.entity.Medicine;


@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {

}
