package net.likelion.lionboard.user.service;


import net.likelion.lionboard.user.dao.UserDAO;
import net.likelion.lionboard.user.dao.UserRoleDAO;
import net.likelion.lionboard.user.dto.RoleDTO;
import net.likelion.lionboard.user.dto.UserDTO;
import net.likelion.lionboard.user.dto.UserRoleDTO;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 사용자 관련 비즈니스 로직을 처리하는 서비스
 */
public class UserService {
    private UserDAO userDAO;
    private UserRoleDAO userRoleDAO;

    public UserService() {
        this.userDAO = new UserDAO();
        this.userRoleDAO = new UserRoleDAO();
    }

    /**
     * 회원가입을 처리합니다.
     */
    public boolean registerUser(String name, String password, String email) {
        try {
            // 사용자명 중복 확인
            if (userDAO.isUsernameExists(name)) {
                System.out.println("❌ 이미 존재하는 사용자명입니다.");
                return false;
            }

            // 사용자 등록
            UserDTO user = new UserDTO(name, password, email);
            int userId = userDAO.insertUser(user);

            // 기본 USER 권한 부여 (role_id = 1)
            UserRoleDTO userRole = new UserRoleDTO(userId, 1);
            userRoleDAO.insertUserRole(userRole);

            System.out.println("✅ 회원가입이 완료되었습니다! 사용자 ID: " + userId);
            return true;

        } catch (Exception e) {
            System.err.println("❌ 회원가입 중 오류 발생: " + e.getMessage());
            return false;
        }
    }

    /**
     * 로그인을 처리합니다.
     */
    public UserDTO loginUser(String name, String password) {
        try {
            UserDTO user = userDAO.selectUserByUsernameAndPassword(name, password);

            if (user != null) {
                System.out.println("✅ 로그인 성공! 환영합니다, " + user.getName() + "님!");

                // 사용자 권한 조회
                List<RoleDTO> roles = userRoleDAO.selectRolesByUserId(user.getUserId());
                System.out.print("권한: ");
                for (RoleDTO role : roles) {
                    System.out.print(role.getName() + " ");
                }
                System.out.println();

                return user;
            } else {
                System.out.println("❌ 사용자명 또는 비밀번호가 잘못되었습니다.");
                return null;
            }

        } catch (Exception e) {
            System.err.println("❌ 로그인 중 오류 발생: " + e.getMessage());
            return null;
        }
    }

    /**
     * 사용자 정보를 조회합니다.
     */
    public UserDTO getUserInfo(int userId) {
        try {
            return userDAO.selectUserById(userId);
        } catch (Exception e) {
            System.err.println("❌ 사용자 정보 조회 중 오류 발생: " + e.getMessage());
            return null;
        }
    }

    /**
     * 사용자가 관리자 권한을 가지고 있는지 확인
     */
    public boolean hasAdminRole(int userId) {
        try {
            return userRoleDAO.hasRole(userId, 2); // ROLE_ADMIN = 2
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 전체 사용자 목록 조회
     */
    public void listAllUsers() {
        try {
            List<UserDTO> users = userDAO.selectAllUsers();

            System.out.println("-".repeat(70));
            System.out.printf("%-5s %-20s %-15s %-20s %-10s\n", "ID", "이메일", "이름", "가입일", "권한");
            System.out.println("-".repeat(70));

            for (UserDTO user : users) {
                List<RoleDTO> roles = userRoleDAO.selectRolesByUserId(user.getUserId());
                String roleNames = roles.stream()
                        .map(RoleDTO::getName)
                        .collect(Collectors.joining(", "));

                String regdate = user.getRegdate() != null ?
                        user.getRegdate().format(DateTimeFormatter.ofPattern("MM-dd HH:mm")) : "알수없음";

                System.out.printf("%-5d %-20s %-15s %-20s %-10s\n",
                        user.getUserId(),
                        user.getEmail(),
                        user.getName(),
                        regdate,
                        roleNames);
            }
            System.out.println("-".repeat(70));

        } catch (Exception e) {
            System.err.println("❌ 사용자 목록 조회 중 오류: " + e.getMessage());
        }
    }

    /**
     * 관리자 권한 부여
     */
    public boolean grantAdminRole(int userId) {
        try {
            // 사용자 존재 확인
            UserDTO user = userDAO.selectUserById(userId);
            if (user == null) {
                System.out.println("❌ 존재하지 않는 사용자입니다.");
                return false;
            }

            // 이미 관리자 권한이 있는지 확인
            if (userRoleDAO.hasRole(userId, 2)) {
                System.out.println("⚠️ 이미 관리자 권한을 가지고 있습니다.");
                return false;
            }

            // 관리자 권한 부여
            UserRoleDTO adminRole = new UserRoleDTO(userId, 2);
            userRoleDAO.insertUserRole(adminRole);

            System.out.println("✅ " + user.getName() + "님에게 관리자 권한이 부여되었습니다.");
            return true;

        } catch (Exception e) {
            System.err.println("❌ 관리자 권한 부여 중 오류: " + e.getMessage());
            return false;
        }
    }

    /**
     * 관리자 권한 제거
     */
    public boolean revokeAdminRole(int userId) {
        try {
            // 사용자 존재 확인
            UserDTO user = userDAO.selectUserById(userId);
            if (user == null) {
                System.out.println("❌ 존재하지 않는 사용자입니다.");
                return false;
            }

            // 관리자 권한이 있는지 확인
            if (!userRoleDAO.hasRole(userId, 2)) {
                System.out.println("⚠️ 관리자 권한이 없습니다.");
                return false;
            }

            // 관리자 권한 제거
            userRoleDAO.deleteUserRole(userId, 2);

            System.out.println("✅ " + user.getName() + "님의 관리자 권한이 제거되었습니다.");
            return true;

        } catch (Exception e) {
            System.err.println("❌ 관리자 권한 제거 중 오류: " + e.getMessage());
            return false;
        }
    }

    /**
     * 시스템 통계 조회
     */
    public void showSystemStats() {
        try {
            int totalUsers = userDAO.getTotalUserCount();
            int adminCount = userRoleDAO.getUserCountByRole(2);

            System.out.println("-".repeat(30));
            System.out.println("전체 사용자 수: " + totalUsers + "명");
            System.out.println("관리자 수: " + adminCount + "명");
            System.out.println("-".repeat(30));

        } catch (Exception e) {
            System.err.println("❌ 사용자 통계 조회 중 오류: " + e.getMessage());
        }
    }



    /**
     * 사용자의 권한 목록을 조회합니다.
     * @param userId 사용자 ID
     * @return 권한 이름 목록 (예: ["ROLE_USER", "ROLE_ADMIN"])
     */
    public List<String> getUserRoles(int userId) {
        try {
            List<String> roles = userDAO.selectUserRoles(userId);

            // ROLE_ 접두사를 제거하여 사용자 친화적으로 변환
            return roles.stream()
                    .map(role -> role.replace("ROLE_", ""))
                    .map(role -> {
                        switch (role) {
                            case "USER": return "일반사용자";
                            case "ADMIN": return "관리자";
                            default: return role;
                        }
                    })
                    .toList();

        } catch (Exception e) {
            throw new RuntimeException("사용자 권한 조회 중 오류 발생: " + e.getMessage(), e);
        }
    }

    /**
     * 사용자의 원본 권한 목록을 조회합니다. (시스템 내부용)
     * @param userId 사용자 ID
     * @return 원본 권한 이름 목록 (예: ["ROLE_USER", "ROLE_ADMIN"])
     */
    public List<String> getUserRolesRaw(int userId) {
        try {
            return userDAO.selectUserRoles(userId);
        } catch (Exception e) {
            throw new RuntimeException("사용자 권한 조회 중 오류 발생: " + e.getMessage(), e);
        }
    }
}
