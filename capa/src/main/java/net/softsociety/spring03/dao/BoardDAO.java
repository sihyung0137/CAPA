package net.softsociety.spring03.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import net.softsociety.spring03.vo.Board;
import net.softsociety.spring03.vo.Post;

@Mapper
public interface BoardDAO {

	int createBoard(Board board);

//	ArrayList<Post> postlist(Post post);

	Post read(int postnum);

	int updateHits(int postnum);

	int deletePost(Post post);

	int updatePost(Post post);

	int deletePost2(Post post);

	int countPost(HashMap<String, String> map);

	ArrayList<Post> selectPostList(HashMap<String, String> map, RowBounds rb);


}
