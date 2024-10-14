package com.ohgiraffers.section01.dynamic;

import com.ohgiraffers.common.MenuDTO;
import com.ohgiraffers.common.SearchCriteria;
import org.apache.ibatis.session.SqlSession;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ohgiraffers.common.Template.getSqlSession;


public class MenuService {

private DynamicSqlMapper mapper;

    public void selectMenuByPrice(int price) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        /* comment.
        *   우리가 지금 하려고하는 것이 price 를 query 문에 전달해서 금액에 따라
        *   적합한 메뉴를 추천해줄 것이다.
        *   기본 자료형으로는 조건문의 값을 비교할 수 없기 때문에, 인스턴스화를 진행해야 한다.
        *   hashMap 을 사용해서 key 값으로 접근을 하거나,
        *   DTO 를 사용해서 getter 메서드로 접근을 한다. */

        // int 의 wrapper 클래스
        Map<String, Integer> map = new HashMap<>();
        map.put("price", price); // auto boxing 되는 중

        // 우리가 전달한 값에 해당하는 메뉴들을 조회
        List<MenuDTO> menuList = mapper.selectMenuByPrice(map);

        if(menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 없습니다.");
        }

        sqlSession.close();

    }

    public void searchMenu(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        // 클래스로 값을 넘김.
        List<MenuDTO> menuList = mapper.searchMenu(searchCriteria);

        if(menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 없습니다.");
        }

        sqlSession.close();
    }

    public void searchMenuBySuperCategory(SearchCriteria searchCriteria) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuBySuperCategory(searchCriteria);

        if(menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 없습니다.");
        }

        sqlSession.close();

    }

    public void searchMenuByRandomCode(List<Integer> randomCodeList) {

        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        Map<String, List<Integer>> criteria = new HashMap<>(); // 키 값은 String, List 타입으로 반환
        // Map 형식으로 우리가 만든 5개의 랜덤코드 리스트 집어넣기
        criteria.put("randomCodeList", randomCodeList); // 키 값, value 값

        List<MenuDTO> menuList = mapper.searchMenuByRandomCode(criteria);

        if(menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 없습니다.");
        }

        sqlSession.close();

    }

    public void searchMenuByCodeOrSearchAll(SearchCriteria searchCriteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByCodeOrSearchAll(searchCriteria);

        if(menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 없습니다.");
        }

        sqlSession.close();
    }

    public void searchMenuByNameOrCategory(Map<String, Object> criteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        List<MenuDTO> menuList = mapper.searchMenuByNameOrCategory(criteria);

        if(menuList != null && menuList.size() > 0) {
            for (MenuDTO menu : menuList) {
                System.out.println(menu);
            }
        } else {
            System.out.println("검색 결과가 없습니다.");
        }

        sqlSession.close();
    }

    public void modifyMenu(Map<String, Object> criteria) {
        SqlSession sqlSession = getSqlSession();
        mapper = sqlSession.getMapper(DynamicSqlMapper.class);

        int result = mapper.modifyMenu(criteria);
        System.out.println(result);

        if ( result > 0 ) {
            System.out.println("메뉴 수정이 완료되었습니다.");
            sqlSession.commit();
        } else {
            System.out.println("메뉴 수정에 실패했습니다.");
            sqlSession.rollback();
        }

        sqlSession.close();
    }
}
