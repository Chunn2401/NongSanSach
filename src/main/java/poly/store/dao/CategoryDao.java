package poly.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import poly.store.entity.Category;

public interface CategoryDao extends JpaRepository<Category, Integer> {
	@Query("SELECT c FROM Category c WHERE c.Deleteday = null ")
	// + "ORDER BY c.name DESC")
	List<Category> getListCategory();

	@Query("SELECT c FROM Category c WHERE c.Deleteday = null AND c.Namesearch LIKE ?1 ")
		// + "ORDER BY c.name DESC")
	Category getCategoryByNameSearch(String nameSearch);
}
