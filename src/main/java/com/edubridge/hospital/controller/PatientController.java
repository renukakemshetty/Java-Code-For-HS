package com.edubridge.hospital.controller;

import java.util.*;

import javax.management.AttributeNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edubridge.hospital.entity.Patient;
import com.edubridge.hospital.repository.PatientRepository;

@RestController
@CrossOrigin(allowedHeaders = "*",origins = "*")
public class PatientController {

	@Autowired
	private PatientRepository PatientRepository;
	
	@GetMapping("/patients")
	public List<Patient> getAllPatients(){
		
				
		return PatientRepository.findAll();
	}
	
	@PostMapping("/patients")
	public Patient createPatient(@RequestBody Patient patient) {
		System.out.println("Patient Details");
		System.out.println(patient.getName());
		return PatientRepository.save(patient);
	}
	
	@GetMapping("/patients/{id}")
	public ResponseEntity<Patient> getPatientById(@PathVariable Long id) throws AttributeNotFoundException {
		
		Patient Patient = PatientRepository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("ABCD" + id));
		
		return ResponseEntity.ok(Patient);
	}
	
	@PutMapping("/patients/{id}")
	public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient PatientDetails) throws AttributeNotFoundException{
		
		Patient Patient = PatientRepository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("ABCD" + id));
		
		Patient.setAge(PatientDetails.getAge());
		Patient.setName(PatientDetails.getName());
		Patient.setBlood(PatientDetails.getBlood());
		Patient.setDose(PatientDetails.getDose());
		Patient.setFees(PatientDetails.getFees());
		Patient.setPrescription(PatientDetails.getPrescription());
		Patient.setUrgency(PatientDetails.getUrgency());
		Patient.setId(PatientDetails.getId());
		
		Patient updatedPatient = PatientRepository.save(Patient);
		return ResponseEntity.ok(updatedPatient);
	}
	
	@DeleteMapping("/patients/{id}")
	public ResponseEntity<Map<String,Boolean>> deletePatient(@PathVariable Long id) throws AttributeNotFoundException{
		
		Patient Patient = PatientRepository.findById(id)
				.orElseThrow(() -> new AttributeNotFoundException("ABCD" + id));
		
		PatientRepository.delete(Patient);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	


}
