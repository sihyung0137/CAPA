
 package net.softsociety.spring03.service;
 
 import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired; import
 org.springframework.stereotype.Service;

 import net.softsociety.spring03.dao.BoardDAO; import
net.softsociety.spring03.vo.Board;
import net.softsociety.spring03.vo.Post;
 
 @Service public class BoardServiceImpl implements BoardService{
 
 @Autowired private BoardDAO dao;
 
 @Override public int createBoard(Board board) { int result =
 dao.createBoard(board); return result; }


@Override
public ArrayList<Post> postlist(Post post) {
	/* ArrayList<Post> list = dao.postlist(post); */
	return dao.postlist(post);
}


@Override
public Post read(int postnum) {
	Post post = dao.read(postnum);
	int result = dao.updateHits(postnum);
	return post;
}


@Override
public int deletePost(Post post) {
	int result = dao.deletePost(post);
	return result;
}


@Override
public int updatePost(Post post) {
	int result = dao.updatePost(post);
	return result;
}


@Override
public int deletePost2(Post post) {
	int result = dao.deletePost2(post);
	return result;
}
 
 
 
 }

