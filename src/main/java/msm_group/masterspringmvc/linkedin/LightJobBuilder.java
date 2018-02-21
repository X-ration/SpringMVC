package msm_group.masterspringmvc.linkedin;

public class LightJobBuilder {

    private final LightJob lightJob;

    public LightJobBuilder() {
        this.lightJob = new LightJob();
    }

    public LightJobBuilder title(String title) {
        lightJob.setTitle(title);
        return this;
    }

    public LightJob build() {
        return lightJob;
    }

}
