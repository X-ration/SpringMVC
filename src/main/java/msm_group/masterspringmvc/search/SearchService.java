package msm_group.masterspringmvc.search;

import msm_group.masterspringmvc.linkedin.Job;
import msm_group.masterspringmvc.linkedin.LightJob;
import msm_group.masterspringmvc.linkedin.LinkedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private LinkedIn linkedIn = LinkedIn.getInstance();

    public List<LightJob> search(String searchType, List<String> keywords) {
        List<LightJob> jobs = new ArrayList<>();
        if(keywords.size() > 0){
            Set<LightJob> set = new LinkedHashSet<>();
            for(String keyword : keywords){
                set.addAll(linkedIn.searchJobs(keyword)
                        .stream()
                        .map(LightJob::toLightJob)   //lambda
                        .collect(Collectors.toList()));
                //jobs.addAll(linkedIn.searchJobs(keyword));
            }
            jobs.addAll(new ArrayList<>(set));
        }
        System.out.println("keywords:"+keywords);

        return jobs;
    }

}
