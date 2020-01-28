package com.ssafysns.model.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafysns.model.dto.FollowHashtag;
import com.ssafysns.model.dto.FollowHashtagException;
import com.ssafysns.repository.FollowHashtagRepository;

@Service
public class FollowHashtagServiceImpl implements FollowHashtagService {

	@Autowired
	FollowHashtagRepository followHashtagRepository;

	@Override
	public void insert(FollowHashtag followHashtag) {
		try {
			followHashtagRepository.save(followHashtag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FollowHashtagException("FollowHashtag 등록 중 오류가 발생했습니다.");
		}
	}

	@Override
	public void delete(int no) {
		try {
			followHashtagRepository.deleteById(no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FollowHashtagException("FollowHashtag 삭제 중 오류가 발생했습니다.");
		}
	}

	@Override
	public void update(FollowHashtag followHashtag) {
		try {
			followHashtagRepository.save(followHashtag);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FollowHashtagException("FollowHashtag 수정 중 오류가 발생했습니다.");
		}
	}

	@Override
	public Optional<FollowHashtag> search(int no) {
		try {
			return followHashtagRepository.findById(no);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FollowHashtagException("FollowHashtag 조회 중 오류가 발생했습니다.");
		}
	}

	public List<FollowHashtag> searchById(String id) {
		try {
			return followHashtagRepository.findByUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FollowHashtagException("FollowHashtag 조회 중 오류가 발생했습니다.");
		}
	}

	@Override
	public List<FollowHashtag> searchAll() {
		try {
			return followHashtagRepository.findAll();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FollowHashtagException("FollowHashtag 목록 조회 중 오류가 발생했습니다.");
		}
	}

	@Override
	public int count() {
		try {
			return (int) followHashtagRepository.count();
		} catch (Exception e) {
			e.printStackTrace();
			throw new FollowHashtagException();
		}
	}

}
