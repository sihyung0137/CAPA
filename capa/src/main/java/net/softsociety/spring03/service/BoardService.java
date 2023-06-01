
package net.softsociety.spring03.service;

import java.util.ArrayList;

import net.softsociety.spring03.vo.Board;
import net.softsociety.spring03.vo.Post;

public interface BoardService {

int createBoard(Board board);


ArrayList<Post> postlist(Post post);


Post read(int postnum);


int deletePost(Post post);


int updatePost(Post post);


int deletePost2(Post post);


}

