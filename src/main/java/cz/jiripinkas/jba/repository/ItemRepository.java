package cz.jiripinkas.jba.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cz.jiripinkas.jba.entity.Blog;
import cz.jiripinkas.jba.entity.Item;

public interface ItemRepository extends JpaRepository<Item, Integer> {
	List<Item> findByBlog(Blog blog,Pageable pageable);

	List<Item> findByBlog(Blog blog);
}
