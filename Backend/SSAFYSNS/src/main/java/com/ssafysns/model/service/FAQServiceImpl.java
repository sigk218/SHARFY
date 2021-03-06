package com.ssafysns.model.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafysns.model.dto.FAQ;
import com.ssafysns.repository.FAQRepository;

@Service
public class FAQServiceImpl implements FAQService {
	
	@Autowired
	FAQRepository faqRepository;

	@Override
	public void insert(FAQ faq) {
		try {
			faqRepository.save(faq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void update(FAQ faq) {
		
		try {
			faqRepository.save(faq);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	@Transactional
	public void delete(int no) {
		try {
			faqRepository.updateDeleted(no);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<FAQ> search(String id) {
		List<FAQ> faqs = null;
		try {
			faqs =  faqRepository.findById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return faqs;
	}

	@Override
	public List<FAQ> searchAll() {
		List<FAQ> faqs = null;
		try {
			faqs =  faqRepository.findAll();
		} catch(Exception e) {
			e.printStackTrace();
		}
		return faqs;
	}

}
