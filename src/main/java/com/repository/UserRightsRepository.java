package com.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.model.SA.UserRightsMST;

@Repository
public interface UserRightsRepository extends JpaRepository<UserRightsMST, Integer>{

	@Query(value = """
	        SELECT
	            m.moduleid,
	            m.modulename,
	            m.icon AS moduleicon,
	            p.pagetypeid,
	            p.mastername,
	            p.icon AS mastericon,
	            p.filename,
	            ur.addopn,
	            ur.editopn,
	            ur.viewopn,
	            ur.deleteopn
	        FROM userrightsmst ur
	        JOIN employee e
	            ON e.roleid = ur.roleid
	        JOIN modulemst m
	            ON m.moduleid = ur.moduleid
	        JOIN PageTypeMST p
	            ON p.pagetypeid = ur.pagetypeid
	        WHERE e.userid = :userid
	        ORDER BY m.moduleid, p.pagetypeid
	        """, nativeQuery = true)
	    List<Object[]> findUserMenu(@Param("userid") Integer userid);
	    
	    
	    @Query(value = """
	            SELECT COUNT(*)
	            FROM userrightsmst ur
	            JOIN employee e
	                ON e.roleid = ur.roleid
	            WHERE e.userid = :userid
	              AND ur.pagetypeid = :pagetypeid
	              AND (
	                    (:operation = 'ADD' AND ur.addopn = 1)
	                 OR (:operation = 'EDIT' AND ur.editopn = 1)
	                 OR (:operation = 'VIEW' AND ur.viewopn = 1)
	                 OR (:operation = 'DELETE' AND ur.deleteopn = 1)
	              )
	            """, nativeQuery = true)
	    int checkPermission(
	            @Param("userid") String userid,
	            @Param("pagetypeid") Integer pagetypeid,
	            @Param("operation") String operation);
}
