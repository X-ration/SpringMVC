package msm_group.masterspringmvc.search;

import msm_group.masterspringmvc.linkedin.Job;
import msm_group.masterspringmvc.linkedin.LinkedIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SearchService {

    private LinkedIn linkedIn = LinkedIn.getInstance();

    public List<Job> search(String searchType, List<String> keywords) {
        List<Job> jobs = new ArrayList<>();
        if(keywords.size() > 0){
            Set<Job> set = new LinkedHashSet<>();
            for(String keyword : keywords){
                set.addAll(linkedIn.searchJobs(keyword));
                //jobs.addAll(linkedIn.searchJobs(keyword));
            }
            jobs.addAll(new ArrayList<>(set));
        }

        return jobs;
    }

}
