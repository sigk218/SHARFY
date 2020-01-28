package com.ssafysns.model.service;

import java.util.List;
import java.util.Optional;

import com.ssafysns.model.dto.FollowHashtag;

public interface FollowHashtagService {
	public void insert(FollowHashtag followHashtag);

	public void delete(int no);

	public void update(FollowHashtag followHashtag);

	public Optional<FollowHashtag> search(int no);

	public List<FollowHashtag> searchById(String id);

	public List<FollowHashtag> searchAll();

	public int count();
}
