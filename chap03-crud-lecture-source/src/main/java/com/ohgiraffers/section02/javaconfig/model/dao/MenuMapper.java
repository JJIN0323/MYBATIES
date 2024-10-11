package com.ohgiraffers.section02.javaconfig.model.dao;

import com.ohgiraffers.section02.javaconfig.model.dto.MenuDTO;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

// XML 에서 했던, Mapper 파일을 JAVA 형식으로 변환한것. 즉, 동일함.
// 그래서, 쿼리문이 꼭 필요하다.
public interface MenuMapper {

    @Results(id = "menuResultMap", value = {
            @Result(id = true, property = "menuCode", column = "MENU_CODE"),
            @Result(property = "menuName", column = "MENU_NAME"),
            @Result(property = "menuPrice", column = "MENU_PRICE"),
            @Result(property = "categoryCode", column = "CATEGORY_CODE"),
            @Result(property = "orderableStatus", column = "ORDERABLE_STATUS")
    }) // 이걸 아래 select 에 적용해야함 ㅠㅠ

    @Select(
            "SELECT " +
            "MENU_CODE\n" + // 이 컬럼에 해당하는 값을 DTO 에 MAPPING 해줘야함.
            ", MENU_NAME\n" +
            ", MENU_PRICE\n" +
            ", CATEGORY_CODE\n" +
            ", ORDERABLE_STATUS\n" +
                    "FROM TBL_MENU\n" +
                    "WHERE ORDERABLE_STATUS = 'Y'\n" +
                    "ORDER BY MENU_CODE")

//    @ResultMap("menuResultMap") // 이걸 사용하겠다 하고 선언해줌.
    // 인터페이스이기 때문에, 추상형임.
    List<MenuDTO> selectAllMenu();

}
