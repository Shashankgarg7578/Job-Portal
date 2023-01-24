package com.jobportal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.jobportal.model.RecruiterEmployeeDetails;



public interface RecruiterEmployeeDetailsRepo extends JpaRepository<RecruiterEmployeeDetails, Integer> {
	//this is  used for Spring security
		@Query("select r from RecruiterEmployeeDetails As r where r.email = :email")
		public RecruiterEmployeeDetails getOneEmpRecByEmail(@Param("email") String email);

		//used for search employee on the basis of information or skills in particular job in recruiter module
		@Query("SELECT r FROM RecruiterEmployeeDetails r LEFT JOIN r.skillsEmployee s WHERE r.id IN (:ids) AND (r.email LIKE :keyword"
				+ "	 OR r.city LIKE :keyword"
				+ " OR r.phone LIKE :keyword OR r.gender LIKE :keyword OR r.name LIKE :keyword OR (s.skills is Null OR s.skills LIKE :keyword)) GROUP BY r.id")
		public List<RecruiterEmployeeDetails> getApplierByKeyword(List<Integer> ids, String keyword);

		
}










