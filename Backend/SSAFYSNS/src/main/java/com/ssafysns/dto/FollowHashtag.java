package com.ssafysns.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@RequiredArgsConstructor
@ToString
@Setter
@Getter
@Entity(name="follow_hashtag")
public class FollowHashtag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(nullable = false, unique = true)
	private int no;

	@Column(length = 50)
	private String hashtag;

	@Column(length = 30, nullable = false)
	private String id;

	@Column(nullable = false)
	private boolean deleted;
}
