package net.softsociety.spring03.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring03.vo.Board;
import net.softsociety.spring03.vo.Post;

@Mapper
public interface BoardDAO {

	int createBoard(Board board);

	ArrayList<Post> postlist(Post post);


}
