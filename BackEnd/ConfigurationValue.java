package BackEnd;

import java.util.Objects;

public class ConfigurationValue {

    private final long m;
    private final long n;
    private final long init_plan_min ;
    private final long init_plan_sec ;
    private final long init_budget ;
    private final long init_center_dep ;
    private final long plan_rev_min ;
    private final long plan_rev_sec ;
    private final long rev_cost ;
    private final long max_dep ;
    private final long interest_pct ;

    public ConfigurationValue(long m, long n, long init_plan_min, long init_plan_sec, long init_budget, long init_center_dep, long plan_rev_min, long plan_rev_sec, long rev_cost, long max_dep, long interest_pct) {
        this.m = m;
        this.n = n;
        this.init_plan_min = init_plan_min;
        this.init_plan_sec = init_plan_sec;
        this.init_budget = init_budget;
        this.init_center_dep = init_center_dep;
        this.plan_rev_min = plan_rev_min;
        this.plan_rev_sec = plan_rev_sec;
        this.rev_cost = rev_cost;
        this.max_dep = max_dep;
        this.interest_pct = interest_pct;
    }
    public ConfigurationValue(ConfigurationValue configurationValue)
    {
        this.m = configurationValue.getM();
        this.n = configurationValue.getN();
        this.init_plan_min = configurationValue.getInit_plan_min();
        this.init_plan_sec = configurationValue.getInit_plan_sec();
        this.init_budget = configurationValue.getInit_budget();
        this.init_center_dep = configurationValue.getInit_center_dep();
        this.plan_rev_min = configurationValue.getPlan_rev_min();
        this.plan_rev_sec = configurationValue.getPlan_rev_sec();
        this.rev_cost = configurationValue.getRev_cost();
        this.max_dep = configurationValue.getMax_dep();
        this.interest_pct = configurationValue.getInterest_pct();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConfigurationValue that)) return false;
        return getM() == that.getM() && getN() == that.getN() && getInit_plan_min() == that.getInit_plan_min() && getInit_plan_sec() == that.getInit_plan_sec() && getInit_budget() == that.getInit_budget() && getInit_center_dep() == that.getInit_center_dep() && getPlan_rev_min() == that.getPlan_rev_min() && getPlan_rev_sec() == that.getPlan_rev_sec() && getRev_cost() == that.getRev_cost() && getMax_dep() == that.getMax_dep() && getInterest_pct() == that.getInterest_pct();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getM(), getN(), getInit_plan_min(), getInit_plan_sec(), getInit_budget(), getInit_center_dep(), getPlan_rev_min(), getPlan_rev_sec(), getRev_cost(), getMax_dep(), getInterest_pct());
    }

    public long getM() {
        return this.m;
    }

    public long getN() {
        return this.n;
    }

    public long getInit_plan_min() {
        return this.init_plan_min;
    }

    public long getInit_plan_sec() {
        return this.init_plan_sec;
    }

    public long getInit_budget() {
        return this.init_budget;
    }

    public long getInit_center_dep() {
        return this.init_center_dep;
    }

    public long getPlan_rev_min() {
        return this.plan_rev_min;
    }

    public long getPlan_rev_sec() {
        return this.plan_rev_sec;
    }

    public long getRev_cost() {
        return this.rev_cost;
    }

    public long getMax_dep() {
        return this.max_dep;
    }

    public long getInterest_pct() {
        return this.interest_pct;
    }
}
