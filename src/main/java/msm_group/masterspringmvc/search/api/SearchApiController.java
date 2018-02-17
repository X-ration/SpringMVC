package msm_group.masterspringmvc.search.api;

import msm_group.masterspringmvc.linkedin.Job;
import msm_group.masterspringmvc.linkedin.LightJob;
import msm_group.masterspringmvc.search.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController  //返回类型序列化为合适的格式，默认为JSON。
@RequestMapping("/api/search")
public class SearchApiController {

    private SearchService searchService;

    @Autowired
    public SearchApiController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(value = "/{searchType}", method = RequestMethod.GET)
    public List<LightJob> search(@PathVariable String searchType, @MatrixVariable List<String> keywords) {
        return searchService.search(searchType, keywords);
    }

}
