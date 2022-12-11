package poly.store.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import poly.store.entity.Blog;

public interface BlogDao extends JpaRepository<Blog, Integer>{

}
