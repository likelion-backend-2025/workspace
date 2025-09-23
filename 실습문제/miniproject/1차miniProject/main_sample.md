



        while (true) {
            System.out.println("\n===== 🏦 은행 관리 시스템 =====");
            System.out.println("1. 고객 등록       2. 계좌 개설");
            System.out.println("3. 입금           4. 출금");
            System.out.println("5. 계좌 조회       6. 전체 고객 조회");
            System.out.println("7. 종료");
            System.out.print("메뉴를 선택하세요: ");

            String input = scanner.nextLine();
            int choice choice = Integer.parseInt(input);

            switch (choice) {
                case 1:
                    System.out.print("등록할 고객 이름: ");
                    String name = scanner.nextLine();
                    bank.addCustomer(name);
                    break;
                case 2:
                    System.out.print("계좌를 개설할 고객 ID: ");
                    long customerId = Long.parseLong(scanner.nextLine());
                    bank.openAccount(customerId);
                    break;
                case 3:
                    System.out.print("입금할 계좌번호: ");
                    String depositAccNum = scanner.nextLine();
                    System.out.print("입금액: ");
                    double depositAmount = Double.parseDouble(scanner.nextLine());
                    bank.deposit(depositAccNum, depositAmount);
                    break;
                case 4:
                    System.out.print("출금할 계좌번호: ");
                    String withdrawAccNum = scanner.nextLine();
                    System.out.print("출금액: ");
                    double withdrawAmount = Double.parseDouble(scanner.nextLine());
                    bank.withdraw(withdrawAccNum, withdrawAmount);
                    break;
                case 5:
                    System.out.print("조회할 계좌번호: ");
                    String findAccNum = scanner.nextLine();
                    Account account = bank.getAccountInfo(findAccNum);
                    if (account != null) {
                        System.out.println("--- 계좌 정보 ---");
                        System.out.println(account);
                        System.out.println("-----------------");
                    }
                    break;
                case 6:
                    bank.listAllCustomers();
                    break;
                case 7:
                    System.out.println("프로그램을 종료합니다.");
                    scanner.close();
                    return;
                default:
                    System.out.println("❌ 잘못된 메뉴 선택입니다. 1~7 사이의 숫자를 입력해주세요.");
            }
        }
