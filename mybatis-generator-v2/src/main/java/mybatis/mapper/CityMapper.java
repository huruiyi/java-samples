package mybatis.mapper;

import java.util.List;
import mybatis.model.City;
import mybatis.model.CityExample;
import org.apache.ibatis.annotations.Param;

public interface CityMapper {
    long countByExample(CityExample example);

    int deleteByExample(CityExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(City row);

    int insertSelective(City row);

    List<City> selectByExample(CityExample example);

    City selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("row") City row, @Param("example") CityExample example);

    int updateByExample(@Param("row") City row, @Param("example") CityExample example);

    int updateByPrimaryKeySelective(City row);

    int updateByPrimaryKey(City row);
}