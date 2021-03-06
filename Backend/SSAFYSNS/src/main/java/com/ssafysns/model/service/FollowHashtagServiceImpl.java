package com.ssafysns.model.service;

import java.util.Optional;
import java.util.StringTokenizer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.ssafysns.exception.FollowHashtagException;
import com.ssafysns.exception.TabHashtagException;
import com.ssafysns.model.dto.FollowHashtag;
import com.ssafysns.repository.FollowHashtagRepository;

@Service
public class FollowHashtagServiceImpl implements FollowHashtagService {

	@Autowired
	FollowHashtagRepository followHashtagRepository;

	@Override
	public boolean update(FollowHashtag followHashtag) {
		try {
			// FollowHashtag의 insert, delete, update가 모두 이루어짐.
			String id = followHashtag.getId();
			Optional<FollowHashtag> find = followHashtagRepository.findByUserId(id);
			String hashtag = followHashtag.getHashtag();
			int tagCount = StringUtils.countOccurrencesOf(hashtag, "#"); // count: tag 개수
//			System.out.println(tagCount);
			if (tagCount > 20)
				return false;
			if (find.isPresent()) {
				followHashtagRepository.updateByUserId(id, followHashtag.getHashtag());
			} else {
				followHashtagRepository.save(followHashtag);
			}
			return true;

		} catch (Exception e) {
			e.printStackTrace();
			throw new TabHashtagException("TabHashtag 수정 중 오류가 발생했습니다.");
		}
	}

	public Optional<FollowHashtag> searchById(String id) {
		try {
			return followHashtagRepository.findByUserId(id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new FollowHashtagException("FollowHashtag 조회 중 오류가 발생했습니다.");
		}
	}

//	@Override
//	public void insert(FollowHashtag followHashtag) {
//		try {
//
//			Optional<FollowHashtag> find = followHashtagRepository.findByUserIdAndHashtag(followHashtag.getId(),
//					followHashtag.getHashtag());
//			if (find.isPresent()) {
//				throw new FollowHashtagException("FollowHashtag 등록 중 오류가 발생했습니다. : 이미 존재하는 hashtag입니다.");
//			} else {
//				followHashtagRepository.save(followHashtag);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FollowHashtagException("FollowHashtag 등록 중 오류가 발생했습니다.");
//		}
//	}

//	@Override
//	public void delete(int no) {
//		try {
//			followHashtagRepository.deleteById(no);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FollowHashtagException("FollowHashtag 삭제 중 오류가 발생했습니다.");
//		}
//	}

//	@Override
//	public void deleteById(String id) {
//		try {
//			followHashtagRepository.deleteByUserId(id);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new TabHashtagException("FollowHashtag 삭제 중 오류가 발생했습니다.");
//		}
//	}

//	public void deleteByIdAndHashtag(String id, String hashtag) {
//		try {
//			followHashtagRepository.deleteByUserIdAndHashtag(id, hashtag);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new TabHashtagException("FollowHashtag 삭제 중 오류가 발생했습니다.");
//		}
//	}

//	@Override
//	public Optional<FollowHashtag> search(int no) {
//		try {
//			return followHashtagRepository.findById(no);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FollowHashtagException("FollowHashtag 조회 중 오류가 발생했습니다.");
//		}
//	}

//	@Override
//	public List<FollowHashtag> searchAll() {
//		try {
//			return followHashtagRepository.findAll();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FollowHashtagException("FollowHashtag 목록 조회 중 오류가 발생했습니다.");
//		}
//	}

//	@Override
//	public int count() {
//		try {
//			return (int) followHashtagRepository.count();
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new FollowHashtagException();
//		}
//	}

}
