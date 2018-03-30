package com.zww.activity.dao;

import com.zww.api.model.SignedSheet;

/**
 * 签到 Dao
 *
 */
public interface SignedSheetDao {

	void insert(SignedSheet signedSheet);

	SignedSheet selectOneByMemberID(String memberId);

    void updateByMemberId(String memberId);

	void updateNewDayByMemberId(String memberId);
}
