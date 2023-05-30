package net.softsociety.spring03.dao;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring03.vo.Post;

@Mapper
public interface PostDAO {

	int writePost(Post post);

}
