package net.softsociety.spring03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.spring03.dao.PostDAO;
import net.softsociety.spring03.vo.Post;
 
@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostDAO dao;
	
	@Override
	public int writePost(Post post) {
		int result = dao.writePost(post);
		return result;
	}

}
