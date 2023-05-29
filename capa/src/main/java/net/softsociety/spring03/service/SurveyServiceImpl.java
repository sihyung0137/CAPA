package net.softsociety.spring03.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import net.softsociety.spring03.dao.SurveyDAO;
import net.softsociety.spring03.vo.Survey;

@Service
public class SurveyServiceImpl implements SurveyService {

	@Autowired
	SurveyDAO surveyDAO;

	@Override
	public ArrayList<Survey> surveyList() {
		ArrayList<Survey> surveyList = surveyDAO.selectSurveyList();
		return surveyList;
	}

	@Override
	public Survey read(int surveynum) {
		Survey survey = surveyDAO.selectSurvey(surveynum);
		return survey;
	}

	@Override
	public int write(Survey survey) {
		int result = surveyDAO.insertSurvey(survey);
		return result;
	}

	@Override
	public int delete(Survey survey) {
		int result = surveyDAO.deleteSurvey(survey);
		return result;
	}
	
	
	
	
}