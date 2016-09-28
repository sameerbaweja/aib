package com.afteribuy.repositories;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import com.afteribuy.entities.Make;
import com.afteribuy.entities.ProductType;
import com.afteribuy.entities.RequestStatus;
import com.afteribuy.entities.RequestType;

@Repository
public class MasterDataRepository {
	protected EntityManager entityManager;
	
    public EntityManager getEntityManager() {
        return this.entityManager;
    }
    
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<ProductType> getProductTypes() throws DataAccessException {
        Query query = getEntityManager().createQuery("select pt from ProductType pt");
        List<ProductType> resultList = query.getResultList();
        return resultList;
    }

    public List<Make> getMakes() throws DataAccessException {
        Query query = getEntityManager().createQuery("select m from Make m");
        List<Make> resultList = query.getResultList();
        return resultList;
    }

    public List<RequestType> getRequestTypes() throws DataAccessException {
        //Query query = getEntityManager().createQuery("select rt from RequestType rt");
        TypedQuery<RequestType> query = getEntityManager().createNamedQuery("RequestType.findAll", RequestType.class);
        List<RequestType> resultList = query.getResultList();
        System.out.println("resultList size:"+resultList.size());
        return resultList;
    }

    public List<RequestStatus> getRequestStatusValues() throws DataAccessException {
        //Query query = getEntityManager().createQuery("select rs from RequestStatus rs");
        TypedQuery<RequestStatus> query = getEntityManager().createNamedQuery("RequestStatus.findAll", RequestStatus.class);
        
        List<RequestStatus> resultList = query.getResultList();
        System.out.println("getRequestStatusValues :"+resultList.size());
        return resultList;
    }

    public long getProductCount(Integer userId) throws DataAccessException {
        Query query = getEntityManager().createQuery("select count(p.productId) from Product p LEFT JOIN p.user WHERE p.user.userId = :userId");
        query.setParameter("userId", userId);
        long count = (long)query.getSingleResult();
        return count;
    }

    public long getBrandCount(Integer userId) throws DataAccessException {
        Query query = getEntityManager().createQuery("select count(distinct p.make) from Product p LEFT JOIN p.make LEFT JOIN p.user WHERE p.user.userId = :userId");
        query.setParameter("userId", userId);
        long count = (long)query.getSingleResult();
        return count;
    }

    public long getResolvedRequestCount(Integer userId) throws DataAccessException {
        String sql = "select count(r.requestId) from Request r LEFT JOIN r.product p LEFT JOIN p.user WHERE p.user.userId = :userId" +
        				" AND r.requestStatus.requestStatusId = " + RequestStatus.STATUS_RESOLVED;
    	Query query = getEntityManager().createQuery(sql);
        query.setParameter("userId", userId);
        long count = (long)query.getSingleResult();
        return count;
    }

    public long getUnResolvedRequestCount(Integer userId) throws DataAccessException {
        String sql = "select count(r.requestId) from Request r LEFT JOIN r.product p LEFT JOIN p.user WHERE p.user.userId = :userId " +
				" AND (r.requestStatus.requestStatusId = " + RequestStatus.STATUS_ASSIGNED + " OR r.requestStatus.requestStatusId = " + RequestStatus.STATUS_ASSIGNEDOEM + 
				" OR r.requestStatus.requestStatusId = " + RequestStatus.STATUS_ASSIGNEDSERVICECENTER + " OR r.requestStatus.requestStatusId = " + RequestStatus.STATUS_OEMINPROGRESS +
				" OR r.requestStatus.requestStatusId = " + RequestStatus.STATUS_OPEN + ")";
        Query query = getEntityManager().createQuery(sql);
        query.setParameter("userId", userId);
        long count = (long)query.getSingleResult();
        return count;
    }
}
