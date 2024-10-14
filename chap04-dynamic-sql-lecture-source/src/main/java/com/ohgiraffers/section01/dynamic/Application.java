package com.ohgiraffers.section01.dynamic;

import com.ohgiraffers.common.SearchCriteria;

import java.util.*;

public class Application {

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

        do {
            System.out.println("* MY BATIS DYNAMIC SQL *");
            System.out.println("1. IF CHECK");
            System.out.println("2. CHOOSE(WHEN/OTHERWISE) CHECK");
            System.out.println("3. FOREACH CHECK");
            System.out.println("4. TRIM(WHERE/SET) CHECK");
            System.out.println("9. EXIT");
            System.out.print("SELECT MENU : ");

            int no = sc.nextInt();

            switch (no) {
                case 1:
                    ifSubMenu();
                    break;
                case 2:
                    chooseSubMenu();
                    break;
                case 3:
                    forEachSubMenu();
                    break;
                case 4:
                    trimSubMenu();
                    break;
                case 9:
                    System.out.println("PROGRAM EXIT");
                    return;
            }

        } while (true);
    }

    private static void trimSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("* TRIM SUBMENU *");
            System.out.println("1. 검색 조건이 있는 경우, 메뉴코드로 조회 (단, 없으면 전체조회)");
            System.out.println("2. 메뉴 혹은 카테고리 코드로 검색 (단, 메뉴와 카테고리 둘 다 일치하는 경우도 검색)");
            System.out.println("3. 원하는 메뉴 정보만 수정");
            System.out.println("9. 이전 메뉴");
            System.out.print("원하는 메뉴를 선택하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1 :
                    menuService.searchMenuByCodeOrSearchAll(inputAllOrOne());
                    break;
                case 2:
                    menuService.searchMenuByNameOrCategory(inputSearchCriteriaMap());
                    break;
                case 3:
                    menuService.modifyMenu(inputChange());
                    break;
                    
            }
        } while (true);
    }

    private static Map<String, Object> inputChange() {
        Scanner sc = new Scanner(System.in);
        System.out.print("변경 할 메뉴 코드를 입력하세요 : ");
        int code = sc.nextInt();
        System.out.print("변경 할 메뉴 이름를 입력하세요 : ");
        sc.nextLine();
        String name = sc.nextLine();
        System.out.print("변경 할 카테고리 코드를 입력하세요 : ");
        int categoryCode = sc.nextInt();
        System.out.print("판매 여부를 결정하세요 ( Y / N ) : ");
        sc.nextLine();
        String orderableStatus = sc.nextLine();


        Map<String, Object> criteria = new HashMap<>();
        criteria.put("code", code);
        criteria.put("name", name);
        criteria.put("categoryCode", categoryCode);
        criteria.put("orderableStatus", orderableStatus);

        return criteria;
    }

    // key 값은 거의 다 String 으로 사용하기 때문에 그냥 Map 의 키 값은 String 이라고 봐도 무방하다.
    private static Map<String, Object> inputSearchCriteriaMap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색 조건 (Category / Menu Name / both / null) : ");

        String condition = sc.nextLine();

        Map<String, Object> criteria = new HashMap<>();
        if ("Category".equals(condition)) {
            System.out.print("Category Code를 입력하세요 : ");
            int categoryValue = sc.nextInt();
            criteria.put("categoryValue", categoryValue);
        } else if ("Name".equals(condition)) {
            System.out.print("Menu Name을 입력하세요 : ");
            String nameValue = sc.nextLine();
            criteria.put("nameValue", nameValue);
        } else if ("both".equals(condition)) {
            System.out.print("Menu Name을 입력하세요 : ");
            String nameValue = sc.nextLine();
            System.out.print("Category Code를 입력하세요 : ");
            int categoryValue = sc.nextInt();

            criteria.put("categoryValue", categoryValue);
            criteria.put("nameValue", nameValue);
        }
        return criteria;
    }

    private static SearchCriteria inputAllOrOne() {

        // condition = 검색 조건 value = 값
        Scanner sc = new Scanner(System.in);
        System.out.print("검색 조건을 입력하세요. ( Y / N) : ");
        boolean hasSearchValue = "Y".equals(sc.nextLine()) ? true : false;

        SearchCriteria searchCriteria = new SearchCriteria();
        // y를 입력 했을 때 동작할 구문
        if (hasSearchValue) {
            System.out.print("검색할 메뉴 코드를 입력해주세요 : ");
            String code = sc.nextLine();
            searchCriteria.setCondition("menuCode");
            searchCriteria.setValue(code);
        }
        return searchCriteria;
    }

    private static void forEachSubMenu() {

    }

    private static void chooseSubMenu() {
        Scanner sc = new Scanner(System.in);
        MenuService menuService = new MenuService();

        do {
            System.out.println("* CHOOSE SUBMENU *");
            System.out.println("1. 카테고리 상위 분류별 메뉴");
            System.out.println("9. 이전 메뉴");
            System.out.print("원하는 메뉴를 선택하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1:
                    menuService.searchMenuBySuperCategory(inputSuperCategory());
                    break;
                case 9:
                    System.out.println("CHOOSE SUBMENU EXIT");
                    return;
            }

        } while (true);
    }

    private static SearchCriteria inputSuperCategory() {

        Scanner sc = new Scanner(System.in);
        System.out.print("상위 분류를 입력하세요 : ");
        String value = sc.nextLine();

        return new SearchCriteria("category", value);
    }

    private static void ifSubMenu() {
        Scanner sc = new Scanner(System.in);

        MenuService menuService = new MenuService();

        do {
            System.out.println("* IF SUBMENU *");
            System.out.println("1. 원하는 금액의 추천 메뉴 보여주기");
            System.out.println("2. 메뉴이름 / 카테고리명으로 검색해서 메뉴 보여주기");
            System.out.println("9. 이전 메뉴");
            System.out.print("원하는 메뉴를 선택하세요 : ");
            int no = sc.nextInt();

            switch (no) {
                case 1:
                    menuService.selectMenuByPrice(inputPrice());
                    break;
                case 2:
                    menuService.searchMenu(inputSearchCriteria());
                    break;
                case 9:
                    System.out.println("IF SUBMENU EXIT");
                    return;
            }


        } while (true);

    }

    // 클래스 타입으로 받음.
    private static SearchCriteria inputSearchCriteria() {
        Scanner sc = new Scanner(System.in);
        System.out.print("검색 기준을 선택해주세요. NAME / CATEGORY : ");
        String condition = sc.nextLine();
        System.out.print("검색어를 입력해주세요 : ");
        String value = sc.nextLine();

        return new SearchCriteria(condition, value); // 매개변수 있는 생성자를 통해서 초기화.
    }

    private static int inputPrice() {

        Scanner sc = new Scanner(System.in);
        System.out.print("검색하실 가격의 최대 금액을 입력 : ");
        int price = sc.nextInt();

        return price;

    }
    // 자료형을 받을 때, 기본 자료형은 안되기 때문에 int 의 상위 Integer 로 지정
    private static List<Integer> createRandomCodeList() {
        // 5개의 중복되지 않는 메뉴코드 생성
        Set<Integer> set = new HashSet<>();
        while (set.size() < 5) { // 리터럴로 24를 지정해두는건 공부용. 24를 변수로 지정하면 됨. 그러면 유연해짐.
            int temp = ((int) (Math.random() * 24)) + 37; // 시작값 37
            set.add(temp); // 한번 돌 때 마다, 추가해주기
            System.out.println(temp);
        }
        // return 타입이 list 니까, 동일하게 만듦.
        List<Integer> menuCodeList = new ArrayList<>(set); // 위에서 만든 set 을 집어 넣음.
        Collections.sort(menuCodeList);
        return menuCodeList; // 변수에 담은 후, collection 으로 정렬해서 리턴시켜줌.
    }

}