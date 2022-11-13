package com.masai.service;

import java.util.List;

import com.masai.exception.AdminException;
import com.masai.exception.CustomerException;
import com.masai.exception.PlanterException;
import com.masai.model.Planter;

public interface PlanterService {
	
public Planter addPlanter(Planter planter,String user)throws AdminException;
    
    public Planter registerPlanter(Integer planter, Integer plantId, Integer seedId,String user) throws PlanterException,AdminException;
    public Planter updatePlanter(Planter planter,String user) throws PlanterException,AdminException;
    public Planter deletePlanter(Integer planterId,String user) throws PlanterException,AdminException;
    public Planter viewPlanter(Integer planterId,String user) throws PlanterException,AdminException,CustomerException;
    public Planter viewPlanter(String planterShape,String user) throws PlanterException,AdminException,CustomerException;
    
    public List<Planter> viewAllPlanters(String user) throws PlanterException,AdminException,CustomerException;
    public List<Planter> viewAllPlanters(Integer minCost, Integer maxCost,String user) throws PlanterException,AdminException,CustomerException;
	
}
