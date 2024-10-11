package com.ohgiraffers.section03.remix.model.dao;

import com.ohgiraffers.section03.remix.model.dto.MenuDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public interface MenuMapper {

    // 여기서는 메서드 딱 하나만 남겨줌.
    // 서비스에서 selectAllMenu 를 호출하면, xml 에서 쿼리문이 동작하게 만듦.
    List<MenuDTO> selectAllMenu();

    MenuDTO selectMenuByMenuCode(int code);

    int insertNewMenu(MenuDTO newMenu);
}
