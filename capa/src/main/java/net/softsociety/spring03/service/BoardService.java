
package net.softsociety.spring03.service;

import java.util.ArrayList;

import net.softsociety.spring03.vo.Board;
import net.softsociety.spring03.vo.Post;

public interface BoardService {

int createBoard(Board board);


ArrayList<Post> postlist(Post post);


}

