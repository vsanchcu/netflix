package com.vsanchcu.netflix.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.internal.SessionImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Sets;
import com.vsanchcu.netflix.entity.Award;
import com.vsanchcu.netflix.entity.TvShowAward;

@Repository
public class AwardRepositoryCustomImpl implements AwardRepositoryCustom {

	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Award> findByTvShowAwardColTvShowId(final Long tvShowId) {
		final List<Award> awards = new ArrayList<>();
		final Connection connection = entityManager.unwrap(SessionImpl.class).connection();
		try {
			final PreparedStatement pstmt = connection.prepareStatement(
						"SELECT A.*, TVA.* FROM TVSHOWS_AWARDS TVA "
					+ 	"INNER JOIN TV_SHOWS TV ON TVA.TV_SHOW_ID = TV.ID "
					+ 	"INNER JOIN AWARDS A ON TVA.AWARD_ID = A.ID "
					+ 	"WHERE TV.ID = ?");
			pstmt.setLong(1, tvShowId);
			final ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				final Award award = new Award();
				award.setId(rs.getLong("ID"));
				award.setName(rs.getString("NAME"));
				final TvShowAward tvShowAward = new TvShowAward();
//				tvShowAward.setAward(award);
				tvShowAward.setYear(rs.getDate("YEAR"));
				award.setTvShowAwardCol(Sets.newHashSet(tvShowAward));
				awards.add(award);
			}
			rs.close();
			pstmt.close();
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return awards;
	}

}
