package mybatis.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CountryExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public CountryExample() {
        oredCriteria = new ArrayList<>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andCodeIsNull() {
            addCriterion("Code is null");
            return (Criteria) this;
        }

        public Criteria andCodeIsNotNull() {
            addCriterion("Code is not null");
            return (Criteria) this;
        }

        public Criteria andCodeEqualTo(String value) {
            addCriterion("Code =", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotEqualTo(String value) {
            addCriterion("Code <>", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThan(String value) {
            addCriterion("Code >", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeGreaterThanOrEqualTo(String value) {
            addCriterion("Code >=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThan(String value) {
            addCriterion("Code <", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLessThanOrEqualTo(String value) {
            addCriterion("Code <=", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeLike(String value) {
            addCriterion("Code like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotLike(String value) {
            addCriterion("Code not like", value, "code");
            return (Criteria) this;
        }

        public Criteria andCodeIn(List<String> values) {
            addCriterion("Code in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotIn(List<String> values) {
            addCriterion("Code not in", values, "code");
            return (Criteria) this;
        }

        public Criteria andCodeBetween(String value1, String value2) {
            addCriterion("Code between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andCodeNotBetween(String value1, String value2) {
            addCriterion("Code not between", value1, value2, "code");
            return (Criteria) this;
        }

        public Criteria andNameIsNull() {
            addCriterion("Name is null");
            return (Criteria) this;
        }

        public Criteria andNameIsNotNull() {
            addCriterion("Name is not null");
            return (Criteria) this;
        }

        public Criteria andNameEqualTo(String value) {
            addCriterion("Name =", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotEqualTo(String value) {
            addCriterion("Name <>", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThan(String value) {
            addCriterion("Name >", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameGreaterThanOrEqualTo(String value) {
            addCriterion("Name >=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThan(String value) {
            addCriterion("Name <", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLessThanOrEqualTo(String value) {
            addCriterion("Name <=", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameLike(String value) {
            addCriterion("Name like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotLike(String value) {
            addCriterion("Name not like", value, "name");
            return (Criteria) this;
        }

        public Criteria andNameIn(List<String> values) {
            addCriterion("Name in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotIn(List<String> values) {
            addCriterion("Name not in", values, "name");
            return (Criteria) this;
        }

        public Criteria andNameBetween(String value1, String value2) {
            addCriterion("Name between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andNameNotBetween(String value1, String value2) {
            addCriterion("Name not between", value1, value2, "name");
            return (Criteria) this;
        }

        public Criteria andContinentIsNull() {
            addCriterion("Continent is null");
            return (Criteria) this;
        }

        public Criteria andContinentIsNotNull() {
            addCriterion("Continent is not null");
            return (Criteria) this;
        }

        public Criteria andContinentEqualTo(String value) {
            addCriterion("Continent =", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentNotEqualTo(String value) {
            addCriterion("Continent <>", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentGreaterThan(String value) {
            addCriterion("Continent >", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentGreaterThanOrEqualTo(String value) {
            addCriterion("Continent >=", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentLessThan(String value) {
            addCriterion("Continent <", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentLessThanOrEqualTo(String value) {
            addCriterion("Continent <=", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentLike(String value) {
            addCriterion("Continent like", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentNotLike(String value) {
            addCriterion("Continent not like", value, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentIn(List<String> values) {
            addCriterion("Continent in", values, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentNotIn(List<String> values) {
            addCriterion("Continent not in", values, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentBetween(String value1, String value2) {
            addCriterion("Continent between", value1, value2, "continent");
            return (Criteria) this;
        }

        public Criteria andContinentNotBetween(String value1, String value2) {
            addCriterion("Continent not between", value1, value2, "continent");
            return (Criteria) this;
        }

        public Criteria andRegionIsNull() {
            addCriterion("Region is null");
            return (Criteria) this;
        }

        public Criteria andRegionIsNotNull() {
            addCriterion("Region is not null");
            return (Criteria) this;
        }

        public Criteria andRegionEqualTo(String value) {
            addCriterion("Region =", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotEqualTo(String value) {
            addCriterion("Region <>", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThan(String value) {
            addCriterion("Region >", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionGreaterThanOrEqualTo(String value) {
            addCriterion("Region >=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThan(String value) {
            addCriterion("Region <", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLessThanOrEqualTo(String value) {
            addCriterion("Region <=", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionLike(String value) {
            addCriterion("Region like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotLike(String value) {
            addCriterion("Region not like", value, "region");
            return (Criteria) this;
        }

        public Criteria andRegionIn(List<String> values) {
            addCriterion("Region in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotIn(List<String> values) {
            addCriterion("Region not in", values, "region");
            return (Criteria) this;
        }

        public Criteria andRegionBetween(String value1, String value2) {
            addCriterion("Region between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andRegionNotBetween(String value1, String value2) {
            addCriterion("Region not between", value1, value2, "region");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaIsNull() {
            addCriterion("SurfaceArea is null");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaIsNotNull() {
            addCriterion("SurfaceArea is not null");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaEqualTo(BigDecimal value) {
            addCriterion("SurfaceArea =", value, "surfacearea");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaNotEqualTo(BigDecimal value) {
            addCriterion("SurfaceArea <>", value, "surfacearea");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaGreaterThan(BigDecimal value) {
            addCriterion("SurfaceArea >", value, "surfacearea");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("SurfaceArea >=", value, "surfacearea");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaLessThan(BigDecimal value) {
            addCriterion("SurfaceArea <", value, "surfacearea");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaLessThanOrEqualTo(BigDecimal value) {
            addCriterion("SurfaceArea <=", value, "surfacearea");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaIn(List<BigDecimal> values) {
            addCriterion("SurfaceArea in", values, "surfacearea");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaNotIn(List<BigDecimal> values) {
            addCriterion("SurfaceArea not in", values, "surfacearea");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SurfaceArea between", value1, value2, "surfacearea");
            return (Criteria) this;
        }

        public Criteria andSurfaceareaNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("SurfaceArea not between", value1, value2, "surfacearea");
            return (Criteria) this;
        }

        public Criteria andIndepyearIsNull() {
            addCriterion("IndepYear is null");
            return (Criteria) this;
        }

        public Criteria andIndepyearIsNotNull() {
            addCriterion("IndepYear is not null");
            return (Criteria) this;
        }

        public Criteria andIndepyearEqualTo(Short value) {
            addCriterion("IndepYear =", value, "indepyear");
            return (Criteria) this;
        }

        public Criteria andIndepyearNotEqualTo(Short value) {
            addCriterion("IndepYear <>", value, "indepyear");
            return (Criteria) this;
        }

        public Criteria andIndepyearGreaterThan(Short value) {
            addCriterion("IndepYear >", value, "indepyear");
            return (Criteria) this;
        }

        public Criteria andIndepyearGreaterThanOrEqualTo(Short value) {
            addCriterion("IndepYear >=", value, "indepyear");
            return (Criteria) this;
        }

        public Criteria andIndepyearLessThan(Short value) {
            addCriterion("IndepYear <", value, "indepyear");
            return (Criteria) this;
        }

        public Criteria andIndepyearLessThanOrEqualTo(Short value) {
            addCriterion("IndepYear <=", value, "indepyear");
            return (Criteria) this;
        }

        public Criteria andIndepyearIn(List<Short> values) {
            addCriterion("IndepYear in", values, "indepyear");
            return (Criteria) this;
        }

        public Criteria andIndepyearNotIn(List<Short> values) {
            addCriterion("IndepYear not in", values, "indepyear");
            return (Criteria) this;
        }

        public Criteria andIndepyearBetween(Short value1, Short value2) {
            addCriterion("IndepYear between", value1, value2, "indepyear");
            return (Criteria) this;
        }

        public Criteria andIndepyearNotBetween(Short value1, Short value2) {
            addCriterion("IndepYear not between", value1, value2, "indepyear");
            return (Criteria) this;
        }

        public Criteria andPopulationIsNull() {
            addCriterion("Population is null");
            return (Criteria) this;
        }

        public Criteria andPopulationIsNotNull() {
            addCriterion("Population is not null");
            return (Criteria) this;
        }

        public Criteria andPopulationEqualTo(Integer value) {
            addCriterion("Population =", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotEqualTo(Integer value) {
            addCriterion("Population <>", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationGreaterThan(Integer value) {
            addCriterion("Population >", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationGreaterThanOrEqualTo(Integer value) {
            addCriterion("Population >=", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationLessThan(Integer value) {
            addCriterion("Population <", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationLessThanOrEqualTo(Integer value) {
            addCriterion("Population <=", value, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationIn(List<Integer> values) {
            addCriterion("Population in", values, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotIn(List<Integer> values) {
            addCriterion("Population not in", values, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationBetween(Integer value1, Integer value2) {
            addCriterion("Population between", value1, value2, "population");
            return (Criteria) this;
        }

        public Criteria andPopulationNotBetween(Integer value1, Integer value2) {
            addCriterion("Population not between", value1, value2, "population");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyIsNull() {
            addCriterion("LifeExpectancy is null");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyIsNotNull() {
            addCriterion("LifeExpectancy is not null");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyEqualTo(BigDecimal value) {
            addCriterion("LifeExpectancy =", value, "lifeexpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyNotEqualTo(BigDecimal value) {
            addCriterion("LifeExpectancy <>", value, "lifeexpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyGreaterThan(BigDecimal value) {
            addCriterion("LifeExpectancy >", value, "lifeexpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("LifeExpectancy >=", value, "lifeexpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyLessThan(BigDecimal value) {
            addCriterion("LifeExpectancy <", value, "lifeexpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyLessThanOrEqualTo(BigDecimal value) {
            addCriterion("LifeExpectancy <=", value, "lifeexpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyIn(List<BigDecimal> values) {
            addCriterion("LifeExpectancy in", values, "lifeexpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyNotIn(List<BigDecimal> values) {
            addCriterion("LifeExpectancy not in", values, "lifeexpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LifeExpectancy between", value1, value2, "lifeexpectancy");
            return (Criteria) this;
        }

        public Criteria andLifeexpectancyNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("LifeExpectancy not between", value1, value2, "lifeexpectancy");
            return (Criteria) this;
        }

        public Criteria andGnpIsNull() {
            addCriterion("GNP is null");
            return (Criteria) this;
        }

        public Criteria andGnpIsNotNull() {
            addCriterion("GNP is not null");
            return (Criteria) this;
        }

        public Criteria andGnpEqualTo(BigDecimal value) {
            addCriterion("GNP =", value, "gnp");
            return (Criteria) this;
        }

        public Criteria andGnpNotEqualTo(BigDecimal value) {
            addCriterion("GNP <>", value, "gnp");
            return (Criteria) this;
        }

        public Criteria andGnpGreaterThan(BigDecimal value) {
            addCriterion("GNP >", value, "gnp");
            return (Criteria) this;
        }

        public Criteria andGnpGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("GNP >=", value, "gnp");
            return (Criteria) this;
        }

        public Criteria andGnpLessThan(BigDecimal value) {
            addCriterion("GNP <", value, "gnp");
            return (Criteria) this;
        }

        public Criteria andGnpLessThanOrEqualTo(BigDecimal value) {
            addCriterion("GNP <=", value, "gnp");
            return (Criteria) this;
        }

        public Criteria andGnpIn(List<BigDecimal> values) {
            addCriterion("GNP in", values, "gnp");
            return (Criteria) this;
        }

        public Criteria andGnpNotIn(List<BigDecimal> values) {
            addCriterion("GNP not in", values, "gnp");
            return (Criteria) this;
        }

        public Criteria andGnpBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GNP between", value1, value2, "gnp");
            return (Criteria) this;
        }

        public Criteria andGnpNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GNP not between", value1, value2, "gnp");
            return (Criteria) this;
        }

        public Criteria andGnpoldIsNull() {
            addCriterion("GNPOld is null");
            return (Criteria) this;
        }

        public Criteria andGnpoldIsNotNull() {
            addCriterion("GNPOld is not null");
            return (Criteria) this;
        }

        public Criteria andGnpoldEqualTo(BigDecimal value) {
            addCriterion("GNPOld =", value, "gnpold");
            return (Criteria) this;
        }

        public Criteria andGnpoldNotEqualTo(BigDecimal value) {
            addCriterion("GNPOld <>", value, "gnpold");
            return (Criteria) this;
        }

        public Criteria andGnpoldGreaterThan(BigDecimal value) {
            addCriterion("GNPOld >", value, "gnpold");
            return (Criteria) this;
        }

        public Criteria andGnpoldGreaterThanOrEqualTo(BigDecimal value) {
            addCriterion("GNPOld >=", value, "gnpold");
            return (Criteria) this;
        }

        public Criteria andGnpoldLessThan(BigDecimal value) {
            addCriterion("GNPOld <", value, "gnpold");
            return (Criteria) this;
        }

        public Criteria andGnpoldLessThanOrEqualTo(BigDecimal value) {
            addCriterion("GNPOld <=", value, "gnpold");
            return (Criteria) this;
        }

        public Criteria andGnpoldIn(List<BigDecimal> values) {
            addCriterion("GNPOld in", values, "gnpold");
            return (Criteria) this;
        }

        public Criteria andGnpoldNotIn(List<BigDecimal> values) {
            addCriterion("GNPOld not in", values, "gnpold");
            return (Criteria) this;
        }

        public Criteria andGnpoldBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GNPOld between", value1, value2, "gnpold");
            return (Criteria) this;
        }

        public Criteria andGnpoldNotBetween(BigDecimal value1, BigDecimal value2) {
            addCriterion("GNPOld not between", value1, value2, "gnpold");
            return (Criteria) this;
        }

        public Criteria andLocalnameIsNull() {
            addCriterion("LocalName is null");
            return (Criteria) this;
        }

        public Criteria andLocalnameIsNotNull() {
            addCriterion("LocalName is not null");
            return (Criteria) this;
        }

        public Criteria andLocalnameEqualTo(String value) {
            addCriterion("LocalName =", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameNotEqualTo(String value) {
            addCriterion("LocalName <>", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameGreaterThan(String value) {
            addCriterion("LocalName >", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameGreaterThanOrEqualTo(String value) {
            addCriterion("LocalName >=", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameLessThan(String value) {
            addCriterion("LocalName <", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameLessThanOrEqualTo(String value) {
            addCriterion("LocalName <=", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameLike(String value) {
            addCriterion("LocalName like", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameNotLike(String value) {
            addCriterion("LocalName not like", value, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameIn(List<String> values) {
            addCriterion("LocalName in", values, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameNotIn(List<String> values) {
            addCriterion("LocalName not in", values, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameBetween(String value1, String value2) {
            addCriterion("LocalName between", value1, value2, "localname");
            return (Criteria) this;
        }

        public Criteria andLocalnameNotBetween(String value1, String value2) {
            addCriterion("LocalName not between", value1, value2, "localname");
            return (Criteria) this;
        }

        public Criteria andGovernmentformIsNull() {
            addCriterion("GovernmentForm is null");
            return (Criteria) this;
        }

        public Criteria andGovernmentformIsNotNull() {
            addCriterion("GovernmentForm is not null");
            return (Criteria) this;
        }

        public Criteria andGovernmentformEqualTo(String value) {
            addCriterion("GovernmentForm =", value, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformNotEqualTo(String value) {
            addCriterion("GovernmentForm <>", value, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformGreaterThan(String value) {
            addCriterion("GovernmentForm >", value, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformGreaterThanOrEqualTo(String value) {
            addCriterion("GovernmentForm >=", value, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformLessThan(String value) {
            addCriterion("GovernmentForm <", value, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformLessThanOrEqualTo(String value) {
            addCriterion("GovernmentForm <=", value, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformLike(String value) {
            addCriterion("GovernmentForm like", value, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformNotLike(String value) {
            addCriterion("GovernmentForm not like", value, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformIn(List<String> values) {
            addCriterion("GovernmentForm in", values, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformNotIn(List<String> values) {
            addCriterion("GovernmentForm not in", values, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformBetween(String value1, String value2) {
            addCriterion("GovernmentForm between", value1, value2, "governmentform");
            return (Criteria) this;
        }

        public Criteria andGovernmentformNotBetween(String value1, String value2) {
            addCriterion("GovernmentForm not between", value1, value2, "governmentform");
            return (Criteria) this;
        }

        public Criteria andHeadofstateIsNull() {
            addCriterion("HeadOfState is null");
            return (Criteria) this;
        }

        public Criteria andHeadofstateIsNotNull() {
            addCriterion("HeadOfState is not null");
            return (Criteria) this;
        }

        public Criteria andHeadofstateEqualTo(String value) {
            addCriterion("HeadOfState =", value, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateNotEqualTo(String value) {
            addCriterion("HeadOfState <>", value, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateGreaterThan(String value) {
            addCriterion("HeadOfState >", value, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateGreaterThanOrEqualTo(String value) {
            addCriterion("HeadOfState >=", value, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateLessThan(String value) {
            addCriterion("HeadOfState <", value, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateLessThanOrEqualTo(String value) {
            addCriterion("HeadOfState <=", value, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateLike(String value) {
            addCriterion("HeadOfState like", value, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateNotLike(String value) {
            addCriterion("HeadOfState not like", value, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateIn(List<String> values) {
            addCriterion("HeadOfState in", values, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateNotIn(List<String> values) {
            addCriterion("HeadOfState not in", values, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateBetween(String value1, String value2) {
            addCriterion("HeadOfState between", value1, value2, "headofstate");
            return (Criteria) this;
        }

        public Criteria andHeadofstateNotBetween(String value1, String value2) {
            addCriterion("HeadOfState not between", value1, value2, "headofstate");
            return (Criteria) this;
        }

        public Criteria andCapitalIsNull() {
            addCriterion("Capital is null");
            return (Criteria) this;
        }

        public Criteria andCapitalIsNotNull() {
            addCriterion("Capital is not null");
            return (Criteria) this;
        }

        public Criteria andCapitalEqualTo(Integer value) {
            addCriterion("Capital =", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotEqualTo(Integer value) {
            addCriterion("Capital <>", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalGreaterThan(Integer value) {
            addCriterion("Capital >", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalGreaterThanOrEqualTo(Integer value) {
            addCriterion("Capital >=", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLessThan(Integer value) {
            addCriterion("Capital <", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalLessThanOrEqualTo(Integer value) {
            addCriterion("Capital <=", value, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalIn(List<Integer> values) {
            addCriterion("Capital in", values, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotIn(List<Integer> values) {
            addCriterion("Capital not in", values, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalBetween(Integer value1, Integer value2) {
            addCriterion("Capital between", value1, value2, "capital");
            return (Criteria) this;
        }

        public Criteria andCapitalNotBetween(Integer value1, Integer value2) {
            addCriterion("Capital not between", value1, value2, "capital");
            return (Criteria) this;
        }

        public Criteria andCode2IsNull() {
            addCriterion("Code2 is null");
            return (Criteria) this;
        }

        public Criteria andCode2IsNotNull() {
            addCriterion("Code2 is not null");
            return (Criteria) this;
        }

        public Criteria andCode2EqualTo(String value) {
            addCriterion("Code2 =", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotEqualTo(String value) {
            addCriterion("Code2 <>", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2GreaterThan(String value) {
            addCriterion("Code2 >", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2GreaterThanOrEqualTo(String value) {
            addCriterion("Code2 >=", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2LessThan(String value) {
            addCriterion("Code2 <", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2LessThanOrEqualTo(String value) {
            addCriterion("Code2 <=", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2Like(String value) {
            addCriterion("Code2 like", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotLike(String value) {
            addCriterion("Code2 not like", value, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2In(List<String> values) {
            addCriterion("Code2 in", values, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotIn(List<String> values) {
            addCriterion("Code2 not in", values, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2Between(String value1, String value2) {
            addCriterion("Code2 between", value1, value2, "code2");
            return (Criteria) this;
        }

        public Criteria andCode2NotBetween(String value1, String value2) {
            addCriterion("Code2 not between", value1, value2, "code2");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {
        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}