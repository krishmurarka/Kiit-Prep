package com.kiitinterveiwPrep.Interview.KIT.Repositories;

import com.kiitinterveiwPrep.Interview.KIT.Entities.Category;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Company;
import com.kiitinterveiwPrep.Interview.KIT.Entities.Post;
import com.kiitinterveiwPrep.Interview.KIT.Entities.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post,Integer> {


    Page<Post> findAllByUser(User user, Pageable pageable);
    Page<Post> findAllByCategory(Category category, Pageable pageable);
    Page<Post> findAllByCompany(Company company,Pageable pageable);

}
