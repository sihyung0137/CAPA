
 package net.softsociety.spring03.service;
 
 import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import
 org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.dao.BoardDAO;
import net.softsociety.spring03.util.PageNavigator;
import
net.softsociety.spring03.vo.Board;
import net.softsociety.spring03.vo.Post;
 
@Transactional
@Slf4j
@Service
public class BoardServiceImpl implements BoardService{
 
 @Autowired private BoardDAO dao;
 
 @Override public int createBoard(Board board) { int result =
 dao.createBoard(board); return result; }


//@Override
//public ArrayList<Post> postlist(Post post) {
//	ArrayList<Post> list = dao.postlist(post);
//	return dao.postlist(post);
//}


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


@Override
public PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord) {
	HashMap<String, String> map = getMap(type, searchWord);
	int total = dao.countPost(map);
	PageNavigator navi = new PageNavigator(pagePerGroup, countPerPage, page, total);
	return navi;
}



private HashMap<String, String> getMap(String type, String searchWord) {
	HashMap<String, String> map = new HashMap<>();
	map.put("type", type);
	map.put("searchWord", searchWord);
	return map;
}


@Override
public ArrayList<Post> list(PageNavigator navi, String type, String searchWord) {
	HashMap<String, String> map = getMap(type, searchWord);
	RowBounds rb = new RowBounds(navi.getStartRecord(), navi.getCountPerPage());
	log.debug("map : {}", map);	ArrayList<Post> postlist = dao.selectPostList(map, rb);
	return postlist;
}
 
 
 
 }

