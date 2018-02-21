package msm_group.masterspringmvc.search;

import msm_group.masterspringmvc.linkedin.LightJob;

import java.util.List;

public interface LinkedInSearch {

    List<LightJob> search(String searchType, List<String> keywords);

}
