package com.ohgiraffers.section02.javaconfig.model.service;

import com.ohgiraffers.section02.javaconfig.model.dao.MenuMapper;
import com.ohgiraffers.section02.javaconfig.model.dto.MenuDTO;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

import static com.ohgiraffers.section02.javaconfig.common.Template.getSqlSession;

public class MenuService {

    private MenuMapper menuMapper; // 필드로 만듦.

    public List<MenuDTO> selectAllMenu() {

        // 1. SqlSession 생성
        SqlSession sqlSession = getSqlSession();

        // 2. dao 클래스 메서드 호출
        // xml 이 없기 때문에, dao 에서 쿼리문을 호출해야한다.
        // MenuMapper 의 인스턴스를 생성해야 사용할 수 있는데,
        // 인터페이스이기에 인스턴스 생성 불가능 ㅠㅠ
        
        // 3. Template 에 저장해둔 MenuMapper 를 get 메서드로 꺼내줌.
        menuMapper = sqlSession.getMapper(MenuMapper.class);

        List<MenuDTO> menuList = menuMapper.selectAllMenu();

        // 4. 사용한 sqlSession 닫기
        sqlSession.close();

        return menuList;
    }

//    public MenuDTO selectMenuByMenuCode(int code) {
//        return null;
//    }
//
//    public boolean insertNewMenu(MenuDTO newMenu) {
//        return false;
//    }
//
//    public boolean modifyMenu(MenuDTO modifyMenu) {
//        return false;
//    }
//
//    public boolean deleteMenu(int code) {
//        return false;
//    }
}
