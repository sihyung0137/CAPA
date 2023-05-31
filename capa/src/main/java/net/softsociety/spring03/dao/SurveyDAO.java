package net.softsociety.spring03.dao;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.spring03.vo.Review;
import net.softsociety.spring03.vo.Survey;

/**
 * 설문지 관련 매퍼 인터페이스
 */
@Mapper
public interface SurveyDAO {

	ArrayList<Survey> selectSurveyList();

	Survey selectSurvey(int surveynum);

	int insertSurvey(Survey survey);

	int deleteSurvey(Survey survey);

	int writeReview(Review review);

	Review selectAvgReview(String company_name);

}
