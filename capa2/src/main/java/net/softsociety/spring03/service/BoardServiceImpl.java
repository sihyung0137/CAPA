
 package net.softsociety.spring03.service;
 
 import org.springframework.beans.factory.annotation.Autowired; import
 org.springframework.stereotype.Service;

 import net.softsociety.spring03.dao.BoardDAO; import
net.softsociety.spring03.vo.Board;
 
 @Service public class BoardServiceImpl implements BoardService{
 
 @Autowired private BoardDAO dao;
 
 @Override public int createBoard(Board board) { int result =
 dao.createBoard(board); return result; }
 
 
 
 }

