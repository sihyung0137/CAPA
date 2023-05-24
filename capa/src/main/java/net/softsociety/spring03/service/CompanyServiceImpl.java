package net.softsociety.spring03.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.spring03.dao.CompanyDAO;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService{
	@Autowired
	CompanyDAO companyDAO;
}
