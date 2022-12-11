package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Discount;
public interface DiscountDao extends JpaRepository<Discount, Integer>{
	@Query("SELECT d FROM Discount d WHERE d.Deleteday = null")
	List<Discount> getListDiscount();
	
	@Query(value="Select * From Discount Where GETDATE() >= ApplyDay and GETDATE() <= Expiration and DeleteDay is null and Code LIKE ?1", nativeQuery = true)
	Discount getDiscountByCode(String code);
}
