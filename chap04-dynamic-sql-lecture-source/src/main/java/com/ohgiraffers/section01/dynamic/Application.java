package com.ohgiraffers.section01.dynamic;

import com.ohgiraffers.common.SearchCriteria;
import java.util.Scanner;

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
                case 9:
                    System.out.println("PROGRAM EXIT");
                    return;
            }

        } while (true);
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

}