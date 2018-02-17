package msm_group.masterspringmvc.linkedin;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LinkedIn {

    private static LinkedIn linkedIn = null;
    private List<Job> jobs;

    private LinkedIn() {
        jobs = generateJobs();
    }

    private List<Job> generateJobs() {
        List<Job> jobs = new ArrayList<>();
        String fileName = "data/jobs";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while((line = bufferedReader.readLine()) != null) {
                String[] splits = line.split("::");
                Job job = new Job(splits[0],splits[1],splits[2],splits[3],simpleDateFormat.parse(splits[4]),splits[5],Integer.valueOf(splits[6]),splits[7]);
                jobs.add(job);
            }
            fileReader.close();
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return jobs;
    }

    public static LinkedIn getInstance() {
        if(linkedIn == null) {
            linkedIn = new LinkedIn();
        }
        return linkedIn;
    }

    public List<Job> searchJobs(String keyword) {
        return jobs.stream().filter((Job j) -> (j.getCompany().contains(keyword)
                || j.getDescription().contains(keyword) || j.getTitle().contains(keyword)
                || j.getLocation().contains(keyword)))
                .collect(Collectors.toList());
    }

}
