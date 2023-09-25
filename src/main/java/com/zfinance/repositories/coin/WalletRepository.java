package com.zfinance.repositories.coin;

import java.util.List;

import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.query.Param;

import com.zfinance.orm.coin.Wallet;

public interface WalletRepository extends CassandraRepository<Wallet, String> {

	@Query("SELECT * FROM zfin_wallet " + " WHERE organization_id = :p_organization_id " + " ALLOW FILTERING ")
	List<Wallet> findAllByOrganizationId(@Param("p_organization_id") String organizationId);

	@Query("SELECT * FROM zfin_wallet " + " WHERE user_id = :p_user_id " + " ALLOW FILTERING ")
	List<Wallet> findAllByUserId(@Param("p_user_id") String userId);

}
