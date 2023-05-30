package net.softsociety.spring03.service;

import java.util.ArrayList;

import net.softsociety.spring03.vo.Review;
import net.softsociety.spring03.vo.Survey;

public interface SurveyService {

	ArrayList<Survey> surveyList();

	Survey read(int surveynum);

	int write(Survey survey);

	int delete(Survey survey);

	int writeReview(Review review);

}