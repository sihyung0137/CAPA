
package net.softsociety.spring03.service;

import java.util.ArrayList;

import net.softsociety.spring03.util.PageNavigator;
import net.softsociety.spring03.vo.Board;
import net.softsociety.spring03.vo.Post;

public interface BoardService {

int createBoard(Board board);


//ArrayList<Post> postlist(Post post);


Post read(int postnum);


int deletePost(Post post);


int updatePost(Post post);


int deletePost2(Post post);


PageNavigator getPageNavigator(int pagePerGroup, int countPerPage, int page, String type, String searchWord);


ArrayList<Post> list(PageNavigator navi, String type, String searchWord);


}

