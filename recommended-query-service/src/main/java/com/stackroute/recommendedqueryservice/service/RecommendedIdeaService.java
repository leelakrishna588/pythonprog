package com.stackroute.recommendedqueryservice.service;

import com.stackroute.recommendedqueryservice.domain.Domain;
import com.stackroute.recommendedqueryservice.domain.Idea;
import com.stackroute.recommendedqueryservice.domain.IdeaHamster;
import com.stackroute.recommendedqueryservice.domain.SubDomain;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

public interface RecommendedIdeaService {
    List<Idea> findBySkill(String name);

    List<Idea> findByRole(String name);

    List<Idea> findByWorkedOnIdea(String name,String rname);

    List<Idea> findByAppliedOnIdea(String name,String rname);
}
