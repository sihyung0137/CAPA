package net.softsociety.spring03.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring03.vo.Board;

@Mapper
public interface BoardDAO {

	int createBoard(Board board);

}
